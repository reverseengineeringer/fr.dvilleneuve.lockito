package org.joda.convert;

import java.util.concurrent.atomic.AtomicBoolean;

 enum JDKStringConverter$19
{
  JDKStringConverter$19(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    if ("true".equalsIgnoreCase(paramString)) {
      return new AtomicBoolean(true);
    }
    if ("false".equalsIgnoreCase(paramString)) {
      return new AtomicBoolean(false);
    }
    throw new IllegalArgumentException("Boolean value must be 'true' or 'false', case insensitive");
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.19
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */