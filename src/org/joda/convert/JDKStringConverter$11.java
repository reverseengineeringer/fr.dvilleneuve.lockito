package org.joda.convert;

 enum JDKStringConverter$11
{
  JDKStringConverter$11(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return paramString.toCharArray();
  }
  
  public String convertToString(Object paramObject)
  {
    return new String((char[])paramObject);
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.11
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */