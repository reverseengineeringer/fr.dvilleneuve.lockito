package org.joda.convert;

 enum JDKStringConverter$5
{
  JDKStringConverter$5(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return new Long(paramString);
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */