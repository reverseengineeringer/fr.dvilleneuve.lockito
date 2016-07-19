package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.common.android.DialogFragmentAccessor;
import com.facebook.stetho.common.android.FragmentCompat;
import com.facebook.stetho.inspector.elements.AttributeAccumulator;
import com.facebook.stetho.inspector.elements.ChainedDescriptor;
import com.facebook.stetho.inspector.elements.Descriptor;
import com.facebook.stetho.inspector.elements.Descriptor.Host;
import com.facebook.stetho.inspector.elements.DescriptorMap;
import com.facebook.stetho.inspector.elements.NodeType;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import javax.annotation.Nullable;

final class DialogFragmentDescriptor
  extends Descriptor
  implements ChainedDescriptor, HighlightableDescriptor
{
  private final DialogFragmentAccessor mAccessor;
  private Descriptor mSuper;
  
  private DialogFragmentDescriptor(FragmentCompat paramFragmentCompat)
  {
    mAccessor = paramFragmentCompat.forDialogFragment();
  }
  
  private static void maybeRegister(DescriptorMap paramDescriptorMap, @Nullable FragmentCompat paramFragmentCompat)
  {
    if (paramFragmentCompat != null)
    {
      Class localClass = paramFragmentCompat.getDialogFragmentClass();
      LogUtil.d("Adding support for %s", new Object[] { localClass });
      paramDescriptorMap.register(localClass, new DialogFragmentDescriptor(paramFragmentCompat));
    }
  }
  
  public static DescriptorMap register(DescriptorMap paramDescriptorMap)
  {
    maybeRegister(paramDescriptorMap, FragmentCompat.getSupportLibInstance());
    maybeRegister(paramDescriptorMap, FragmentCompat.getFrameworkInstance());
    return paramDescriptorMap;
  }
  
  public void getAttributes(Object paramObject, AttributeAccumulator paramAttributeAccumulator)
  {
    mSuper.getAttributes(paramObject, paramAttributeAccumulator);
  }
  
  public void getChildren(Object paramObject, Accumulator<Object> paramAccumulator)
  {
    paramAccumulator.store(mAccessor.getDialog(paramObject));
  }
  
  public String getLocalName(Object paramObject)
  {
    return mSuper.getLocalName(paramObject);
  }
  
  public String getNodeName(Object paramObject)
  {
    return mSuper.getNodeName(paramObject);
  }
  
  public NodeType getNodeType(Object paramObject)
  {
    return mSuper.getNodeType(paramObject);
  }
  
  @Nullable
  public String getNodeValue(Object paramObject)
  {
    return mSuper.getNodeValue(paramObject);
  }
  
  public void getStyles(Object paramObject, StyleAccumulator paramStyleAccumulator) {}
  
  @Nullable
  public View getViewForHighlighting(Object paramObject)
  {
    Descriptor.Host localHost = getHost();
    if ((localHost instanceof AndroidDescriptorHost))
    {
      paramObject = mAccessor.getDialog(paramObject);
      return ((AndroidDescriptorHost)localHost).getHighlightingView(paramObject);
    }
    return null;
  }
  
  public void hook(Object paramObject)
  {
    mSuper.hook(paramObject);
  }
  
  public void setAttributesAsText(Object paramObject, String paramString)
  {
    mSuper.setAttributesAsText(paramObject, paramString);
  }
  
  public void setSuper(Descriptor paramDescriptor)
  {
    Util.throwIfNull(paramDescriptor);
    if (paramDescriptor != mSuper)
    {
      if (mSuper != null) {
        throw new IllegalStateException();
      }
      mSuper = paramDescriptor;
    }
  }
  
  public void unhook(Object paramObject)
  {
    mSuper.unhook(paramObject);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.DialogFragmentDescriptor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */