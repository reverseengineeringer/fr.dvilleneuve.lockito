package org.joda.convert;

import java.math.BigInteger;

 enum JDKStringConverter$15
{
  JDKStringConverter$15(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return new BigInteger(paramString);
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.15
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */