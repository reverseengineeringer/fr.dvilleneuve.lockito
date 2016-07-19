package org.joda.convert;

import java.util.UUID;

 enum JDKStringConverter$25
{
  JDKStringConverter$25(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return UUID.fromString(paramString);
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.25
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */