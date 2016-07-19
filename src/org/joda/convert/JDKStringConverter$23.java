package org.joda.convert;

import java.util.Currency;

 enum JDKStringConverter$23
{
  JDKStringConverter$23(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return Currency.getInstance(paramString);
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.23
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */