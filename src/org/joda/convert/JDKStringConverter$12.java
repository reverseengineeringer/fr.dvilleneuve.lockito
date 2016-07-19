package org.joda.convert;

 enum JDKStringConverter$12
{
  JDKStringConverter$12(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    if ("true".equalsIgnoreCase(paramString)) {
      return Boolean.TRUE;
    }
    if ("false".equalsIgnoreCase(paramString)) {
      return Boolean.FALSE;
    }
    throw new IllegalArgumentException("Boolean value must be 'true' or 'false', case insensitive");
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.12
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */