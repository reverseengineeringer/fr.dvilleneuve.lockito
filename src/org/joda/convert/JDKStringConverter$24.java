package org.joda.convert;

import java.util.TimeZone;

 enum JDKStringConverter$24
{
  JDKStringConverter$24(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return TimeZone.getTimeZone(paramString);
  }
  
  public String convertToString(Object paramObject)
  {
    return ((TimeZone)paramObject).getID();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */