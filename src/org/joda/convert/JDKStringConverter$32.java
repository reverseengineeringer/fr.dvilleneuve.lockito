package org.joda.convert;

 enum JDKStringConverter$32
{
  JDKStringConverter$32(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class paramClass, String paramString)
  {
    return Enum.valueOf(paramClass, paramString);
  }
  
  public String convertToString(Object paramObject)
  {
    return ((Enum)paramObject).name();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.32
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */