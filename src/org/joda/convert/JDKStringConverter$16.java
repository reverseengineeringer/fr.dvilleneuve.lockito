package org.joda.convert;

import java.math.BigDecimal;

 enum JDKStringConverter$16
{
  JDKStringConverter$16(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return new BigDecimal(paramString);
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.16
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */