package org.joda.convert;

import java.util.Locale;

 enum JDKStringConverter$20
{
  JDKStringConverter$20(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    paramClass = paramString.split("_", 3);
    switch (paramClass.length)
    {
    default: 
      throw new IllegalArgumentException("Unable to parse Locale: " + paramString);
    case 1: 
      return new Locale(paramClass[0]);
    case 2: 
      return new Locale(paramClass[0], paramClass[1]);
    }
    return new Locale(paramClass[0], paramClass[1], paramClass[2]);
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.20
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */