package org.joda.convert;

 enum JDKStringConverter$22
{
  JDKStringConverter$22(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return Package.getPackage(paramString);
  }
  
  public String convertToString(Object paramObject)
  {
    return ((Package)paramObject).getName();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.22
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */