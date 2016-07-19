package com.joanzapata.iconify.fonts;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

public class FontAwesomeModule
  implements IconFontDescriptor
{
  public Icon[] characters()
  {
    return FontAwesomeIcons.values();
  }
  
  public String ttfFileName()
  {
    return "iconify/android-iconify-fontawesome.ttf";
  }
}

/* Location:
 * Qualified Name:     com.joanzapata.iconify.fonts.FontAwesomeModule
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */