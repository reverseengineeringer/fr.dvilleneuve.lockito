package com.joanzapata.iconify;

import android.content.Context;
import android.widget.TextView;
import com.joanzapata.iconify.internal.IconFontDescriptorWrapper;
import com.joanzapata.iconify.internal.ParsingUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Iconify
{
  private static List<IconFontDescriptorWrapper> iconFontDescriptors = new ArrayList();
  
  private static void addIconFontDescriptor(IconFontDescriptor paramIconFontDescriptor)
  {
    Iterator localIterator = iconFontDescriptors.iterator();
    while (localIterator.hasNext()) {
      if (((IconFontDescriptorWrapper)localIterator.next()).getIconFontDescriptor().ttfFileName().equals(paramIconFontDescriptor.ttfFileName())) {
        return;
      }
    }
    iconFontDescriptors.add(new IconFontDescriptorWrapper(paramIconFontDescriptor));
  }
  
  public static void addIcons(TextView... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    if (i < j)
    {
      TextView localTextView = paramVarArgs[i];
      if (localTextView == null) {}
      for (;;)
      {
        i += 1;
        break;
        localTextView.setText(compute(localTextView.getContext(), localTextView.getText(), localTextView));
      }
    }
  }
  
  public static CharSequence compute(Context paramContext, CharSequence paramCharSequence)
  {
    return compute(paramContext, paramCharSequence, null);
  }
  
  public static CharSequence compute(Context paramContext, CharSequence paramCharSequence, TextView paramTextView)
  {
    return ParsingUtil.parse(paramContext, iconFontDescriptors, paramCharSequence, paramTextView);
  }
  
  public static Icon findIconForKey(String paramString)
  {
    int i = 0;
    int j = iconFontDescriptors.size();
    while (i < j)
    {
      Icon localIcon = ((IconFontDescriptorWrapper)iconFontDescriptors.get(i)).getIcon(paramString);
      if (localIcon != null) {
        return localIcon;
      }
      i += 1;
    }
    return null;
  }
  
  public static IconFontDescriptorWrapper findTypefaceOf(Icon paramIcon)
  {
    Iterator localIterator = iconFontDescriptors.iterator();
    while (localIterator.hasNext())
    {
      IconFontDescriptorWrapper localIconFontDescriptorWrapper = (IconFontDescriptorWrapper)localIterator.next();
      if (localIconFontDescriptorWrapper.hasIcon(paramIcon)) {
        return localIconFontDescriptorWrapper;
      }
    }
    return null;
  }
  
  public static IconifyInitializer with(IconFontDescriptor paramIconFontDescriptor)
  {
    return new IconifyInitializer(paramIconFontDescriptor);
  }
  
  public static class IconifyInitializer
  {
    public IconifyInitializer(IconFontDescriptor paramIconFontDescriptor)
    {
      Iconify.addIconFontDescriptor(paramIconFontDescriptor);
    }
    
    public IconifyInitializer with(IconFontDescriptor paramIconFontDescriptor)
    {
      Iconify.addIconFontDescriptor(paramIconFontDescriptor);
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.joanzapata.iconify.Iconify
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */