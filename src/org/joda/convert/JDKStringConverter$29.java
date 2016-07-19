package org.joda.convert;

import java.io.File;

 enum JDKStringConverter$29
{
  JDKStringConverter$29(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return new File(paramString);
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.29
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */