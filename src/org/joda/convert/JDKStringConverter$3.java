package org.joda.convert;

 enum JDKStringConverter$3
{
  JDKStringConverter$3(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return new StringBuffer(paramString);
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */