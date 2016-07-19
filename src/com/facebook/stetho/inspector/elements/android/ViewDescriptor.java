package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewDebug.FlagToString;
import android.view.ViewDebug.IntToString;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.StringUtil;
import com.facebook.stetho.common.android.ResourcesUtil;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.AttributeAccumulator;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import com.facebook.stetho.inspector.helper.IntegerFormatter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

final class ViewDescriptor
  extends AbstractChainedDescriptor<View>
  implements HighlightableDescriptor
{
  private static final String ID_NAME = "id";
  private static final String NONE_MAPPING = "<no mapping>";
  private static final String NONE_VALUE = "(none)";
  private final MethodInvoker mMethodInvoker;
  @Nullable
  @GuardedBy("this")
  private volatile List<ViewCSSProperty> mViewProperties;
  @Nullable
  private Pattern mWordBoundaryPattern;
  
  public ViewDescriptor()
  {
    this(new MethodInvoker());
  }
  
  public ViewDescriptor(MethodInvoker paramMethodInvoker)
  {
    mMethodInvoker = paramMethodInvoker;
  }
  
  private static boolean canFlagsBeMappedToString(@Nullable ViewDebug.ExportedProperty paramExportedProperty)
  {
    return (paramExportedProperty != null) && (paramExportedProperty.flagMapping() != null) && (paramExportedProperty.flagMapping().length > 0);
  }
  
  private static boolean canIntBeMappedToString(@Nullable ViewDebug.ExportedProperty paramExportedProperty)
  {
    return (paramExportedProperty != null) && (paramExportedProperty.mapping() != null) && (paramExportedProperty.mapping().length > 0);
  }
  
  private static String capitalize(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0) || (Character.isTitleCase(paramString.charAt(0)))) {
      return paramString;
    }
    paramString = new StringBuilder(paramString);
    paramString.setCharAt(0, Character.toTitleCase(paramString.charAt(0)));
    return paramString.toString();
  }
  
  private String convertViewPropertyNameToCSSName(String paramString)
  {
    paramString = getWordBoundaryPattern().split(paramString);
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < paramString.length)
    {
      if ((paramString[i].equals("get")) || (paramString[i].equals("m"))) {}
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append(paramString[i].toLowerCase());
        if (i < paramString.length - 1) {
          localStringBuilder.append('-');
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  @Nullable
  private static String getIdAttribute(View paramView)
  {
    int i = paramView.getId();
    if (i == -1) {
      return null;
    }
    return ResourcesUtil.getIdStringQuietly(paramView, paramView.getResources(), i);
  }
  
  private void getIdStyle(View paramView, StyleAccumulator paramStyleAccumulator)
  {
    paramView = getIdAttribute(paramView);
    if (paramView == null)
    {
      paramStyleAccumulator.store("id", "(none)", false);
      return;
    }
    paramStyleAccumulator.store("id", paramView, false);
  }
  
  private void getStyleFromFloat(String paramString, Float paramFloat, @Nullable ViewDebug.ExportedProperty paramExportedProperty, StyleAccumulator paramStyleAccumulator)
  {
    paramStyleAccumulator.store(paramString, String.valueOf(paramFloat), isDefaultValue(paramFloat));
  }
  
  private void getStyleFromInteger(String paramString, Integer paramInteger, @Nullable ViewDebug.ExportedProperty paramExportedProperty, StyleAccumulator paramStyleAccumulator)
  {
    String str = IntegerFormatter.getInstance().format(paramInteger, paramExportedProperty);
    if (canIntBeMappedToString(paramExportedProperty))
    {
      paramStyleAccumulator.store(paramString, str + " (" + mapIntToStringUsingAnnotation(paramInteger.intValue(), paramExportedProperty) + ")", false);
      return;
    }
    if (canFlagsBeMappedToString(paramExportedProperty))
    {
      paramStyleAccumulator.store(paramString, str + " (" + mapFlagsToStringUsingAnnotation(paramInteger.intValue(), paramExportedProperty) + ")", false);
      return;
    }
    paramStyleAccumulator.store(paramString, str, isDefaultValue(paramInteger, paramExportedProperty));
  }
  
  private void getStyleFromValue(View paramView, String paramString, Object paramObject, @Nullable ViewDebug.ExportedProperty paramExportedProperty, StyleAccumulator paramStyleAccumulator)
  {
    if (paramString.equals("id")) {
      getIdStyle(paramView, paramStyleAccumulator);
    }
    do
    {
      return;
      if ((paramObject instanceof Integer))
      {
        getStyleFromInteger(paramString, (Integer)paramObject, paramExportedProperty, paramStyleAccumulator);
        return;
      }
    } while (!(paramObject instanceof Float));
    getStyleFromFloat(paramString, (Float)paramObject, paramExportedProperty, paramStyleAccumulator);
  }
  
  private List<ViewCSSProperty> getViewProperties()
  {
    int j = 0;
    if (mViewProperties == null) {}
    for (;;)
    {
      int i;
      try
      {
        if (mViewProperties == null)
        {
          ArrayList localArrayList = new ArrayList();
          Object localObject2 = View.class.getDeclaredMethods();
          int k = localObject2.length;
          i = 0;
          Method localMethod;
          ViewDebug.ExportedProperty localExportedProperty;
          if (i < k)
          {
            localMethod = localObject2[i];
            localExportedProperty = (ViewDebug.ExportedProperty)localMethod.getAnnotation(ViewDebug.ExportedProperty.class);
            if (localExportedProperty != null) {
              localArrayList.add(new MethodBackedCSSProperty(localMethod, convertViewPropertyNameToCSSName(localMethod.getName()), localExportedProperty));
            }
          }
          else
          {
            localObject2 = View.class.getDeclaredFields();
            k = localObject2.length;
            i = j;
            if (i < k)
            {
              localMethod = localObject2[i];
              localExportedProperty = (ViewDebug.ExportedProperty)localMethod.getAnnotation(ViewDebug.ExportedProperty.class);
              if (localExportedProperty == null) {
                break label203;
              }
              localArrayList.add(new FieldBackedCSSProperty(localMethod, convertViewPropertyNameToCSSName(localMethod.getName()), localExportedProperty));
              break label203;
            }
            mViewProperties = Collections.unmodifiableList(localArrayList);
          }
        }
        else
        {
          return mViewProperties;
        }
      }
      finally {}
      i += 1;
      continue;
      label203:
      i += 1;
    }
  }
  
  private Pattern getWordBoundaryPattern()
  {
    if (mWordBoundaryPattern == null) {
      mWordBoundaryPattern = Pattern.compile("(?<=\\p{Lower})(?=\\p{Upper})");
    }
    return mWordBoundaryPattern;
  }
  
  private static boolean isDefaultValue(Float paramFloat)
  {
    return paramFloat.floatValue() == 0.0F;
  }
  
  private static boolean isDefaultValue(Integer paramInteger, @Nullable ViewDebug.ExportedProperty paramExportedProperty)
  {
    if ((canFlagsBeMappedToString(paramExportedProperty)) || (canIntBeMappedToString(paramExportedProperty))) {}
    while (paramInteger.intValue() != 0) {
      return false;
    }
    return true;
  }
  
  private static String mapFlagsToStringUsingAnnotation(int paramInt, @Nullable ViewDebug.ExportedProperty paramExportedProperty)
  {
    if (!canFlagsBeMappedToString(paramExportedProperty)) {
      throw new IllegalStateException("Cannot map using this annotation");
    }
    Object localObject = null;
    int j = 0;
    ViewDebug.FlagToString[] arrayOfFlagToString = paramExportedProperty.flagMapping();
    int m = arrayOfFlagToString.length;
    int i = 0;
    paramExportedProperty = (ViewDebug.ExportedProperty)localObject;
    if (i < m)
    {
      ViewDebug.FlagToString localFlagToString = arrayOfFlagToString[i];
      boolean bool2 = localFlagToString.outputIf();
      if ((localFlagToString.mask() & paramInt) == localFlagToString.equals()) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        int k = j;
        localObject = paramExportedProperty;
        if (bool2 == bool1)
        {
          localObject = paramExportedProperty;
          if (paramExportedProperty == null) {
            localObject = new StringBuilder();
          }
          if (j != 0) {
            ((StringBuilder)localObject).append(" | ");
          }
          ((StringBuilder)localObject).append(localFlagToString.name());
          k = 1;
        }
        i += 1;
        j = k;
        paramExportedProperty = (ViewDebug.ExportedProperty)localObject;
        break;
      }
    }
    if (j != 0) {
      return paramExportedProperty.toString();
    }
    return "<no mapping>";
  }
  
  private static String mapIntToStringUsingAnnotation(int paramInt, @Nullable ViewDebug.ExportedProperty paramExportedProperty)
  {
    if (!canIntBeMappedToString(paramExportedProperty)) {
      throw new IllegalStateException("Cannot map using this annotation");
    }
    paramExportedProperty = paramExportedProperty.mapping();
    int j = paramExportedProperty.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramExportedProperty[i];
      if (((ViewDebug.IntToString)localObject).from() == paramInt) {
        return ((ViewDebug.IntToString)localObject).to();
      }
      i += 1;
    }
    return "<no mapping>";
  }
  
  public View getViewForHighlighting(Object paramObject)
  {
    return (View)paramObject;
  }
  
  protected void onGetAttributes(View paramView, AttributeAccumulator paramAttributeAccumulator)
  {
    paramView = getIdAttribute(paramView);
    if (paramView != null) {
      paramAttributeAccumulator.store("id", paramView);
    }
  }
  
  protected String onGetNodeName(View paramView)
  {
    paramView = paramView.getClass().getName();
    return StringUtil.removePrefix(paramView, "android.view.", StringUtil.removePrefix(paramView, "android.widget."));
  }
  
  protected void onGetStyles(View paramView, StyleAccumulator paramStyleAccumulator)
  {
    List localList = getViewProperties();
    int i = 0;
    int j = localList.size();
    for (;;)
    {
      ViewCSSProperty localViewCSSProperty;
      if (i < j) {
        localViewCSSProperty = (ViewCSSProperty)localList.get(i);
      }
      try
      {
        getStyleFromValue(paramView, localViewCSSProperty.getCSSName(), localViewCSSProperty.getValue(paramView), localViewCSSProperty.getAnnotation(), paramStyleAccumulator);
        i += 1;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        for (;;)
        {
          LogUtil.e(localIllegalAccessException, "failed to get style property " + localViewCSSProperty.getCSSName() + " of element= " + paramView.toString());
        }
        return;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        for (;;) {}
      }
    }
  }
  
  protected void onSetAttributesAsText(View paramView, String paramString)
  {
    paramString = parseSetAttributesAsTextArg(paramString).entrySet().iterator();
    while (paramString.hasNext())
    {
      Object localObject = (Map.Entry)paramString.next();
      String str = "set" + capitalize((String)((Map.Entry)localObject).getKey());
      localObject = (String)((Map.Entry)localObject).getValue();
      mMethodInvoker.invoke(paramView, str, (String)localObject);
    }
  }
  
  private final class FieldBackedCSSProperty
    extends ViewDescriptor.ViewCSSProperty
  {
    private final Field mField;
    
    public FieldBackedCSSProperty(Field paramField, @Nullable String paramString, ViewDebug.ExportedProperty paramExportedProperty)
    {
      super(paramString, paramExportedProperty);
      mField = paramField;
      mField.setAccessible(true);
    }
    
    public Object getValue(View paramView)
      throws InvocationTargetException, IllegalAccessException
    {
      return mField.get(paramView);
    }
  }
  
  private final class MethodBackedCSSProperty
    extends ViewDescriptor.ViewCSSProperty
  {
    private final Method mMethod;
    
    public MethodBackedCSSProperty(Method paramMethod, @Nullable String paramString, ViewDebug.ExportedProperty paramExportedProperty)
    {
      super(paramString, paramExportedProperty);
      mMethod = paramMethod;
      mMethod.setAccessible(true);
    }
    
    public Object getValue(View paramView)
      throws InvocationTargetException, IllegalAccessException
    {
      return mMethod.invoke(paramView, new Object[0]);
    }
  }
  
  private abstract class ViewCSSProperty
  {
    private final ViewDebug.ExportedProperty mAnnotation;
    private final String mCSSName;
    
    public ViewCSSProperty(@Nullable String paramString, ViewDebug.ExportedProperty paramExportedProperty)
    {
      mCSSName = paramString;
      mAnnotation = paramExportedProperty;
    }
    
    @Nullable
    public final ViewDebug.ExportedProperty getAnnotation()
    {
      return mAnnotation;
    }
    
    public final String getCSSName()
    {
      return mCSSName;
    }
    
    public abstract Object getValue(View paramView)
      throws InvocationTargetException, IllegalAccessException;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewDescriptor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */