package org.joda.convert;

 enum JDKStringConverter$4
{
  JDKStringConverter$4(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return new StringBuilder(paramString);
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */