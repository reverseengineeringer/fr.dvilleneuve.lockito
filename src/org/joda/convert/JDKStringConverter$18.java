package org.joda.convert;

import java.util.concurrent.atomic.AtomicInteger;

 enum JDKStringConverter$18
{
  JDKStringConverter$18(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return new AtomicInteger(Integer.parseInt(paramString));
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.18
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */