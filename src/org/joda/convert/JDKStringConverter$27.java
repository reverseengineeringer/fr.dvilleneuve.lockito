package org.joda.convert;

import java.net.URI;

 enum JDKStringConverter$27
{
  JDKStringConverter$27(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return URI.create(paramString);
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.27
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */