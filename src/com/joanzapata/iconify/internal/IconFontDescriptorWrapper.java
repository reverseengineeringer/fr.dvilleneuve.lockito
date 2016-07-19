package com.joanzapata.iconify.internal;

import android.content.Context;
import android.graphics.Typeface;
import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IconFontDescriptorWrapper
{
  private Typeface cachedTypeface;
  private final IconFontDescriptor iconFontDescriptor;
  private final Map<String, Icon> iconsByKey;
  
  public IconFontDescriptorWrapper(IconFontDescriptor paramIconFontDescriptor)
  {
    iconFontDescriptor = paramIconFontDescriptor;
    iconsByKey = new HashMap();
    paramIconFontDescriptor = paramIconFontDescriptor.characters();
    int i = 0;
    int j = paramIconFontDescriptor.length;
    while (i < j)
    {
      Object localObject = paramIconFontDescriptor[i];
      iconsByKey.put(((Icon)localObject).key(), localObject);
      i += 1;
    }
  }
  
  public Icon getIcon(String paramString)
  {
    return (Icon)iconsByKey.get(paramString);
  }
  
  public IconFontDescriptor getIconFontDescriptor()
  {
    return iconFontDescriptor;
  }
  
  public Typeface getTypeface(Context paramContext)
  {
    if (cachedTypeface != null) {
      return cachedTypeface;
    }
    try
    {
      if (cachedTypeface != null)
      {
        paramContext = cachedTypeface;
        return paramContext;
      }
    }
    finally {}
    cachedTypeface = Typeface.createFromAsset(paramContext.getAssets(), iconFontDescriptor.ttfFileName());
    paramContext = cachedTypeface;
    return paramContext;
  }
  
  public boolean hasIcon(Icon paramIcon)
  {
    return iconsByKey.values().contains(paramIcon);
  }
}

/* Location:
 * Qualified Name:     com.joanzapata.iconify.internal.IconFontDescriptorWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */