package org.joda.convert;

import java.net.MalformedURLException;
import java.net.URL;

 enum JDKStringConverter$26
{
  JDKStringConverter$26(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    try
    {
      paramClass = new URL(paramString);
      return paramClass;
    }
    catch (MalformedURLException paramClass)
    {
      throw new RuntimeException(paramClass.getMessage(), paramClass);
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.26
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */