package org.joda.convert;

 enum JDKStringConverter$6
{
  JDKStringConverter$6(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return new Integer(paramString);
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */