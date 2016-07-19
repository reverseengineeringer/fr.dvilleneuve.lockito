package org.joda.convert;

import java.util.concurrent.atomic.AtomicLong;

 enum JDKStringConverter$17
{
  JDKStringConverter$17(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return new AtomicLong(Long.parseLong(paramString));
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.17
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */