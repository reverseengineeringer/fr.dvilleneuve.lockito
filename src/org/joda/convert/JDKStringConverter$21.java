package org.joda.convert;

 enum JDKStringConverter$21
{
  JDKStringConverter$21(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    try
    {
      paramClass = getClass().getClassLoader().loadClass(paramString);
      return paramClass;
    }
    catch (ClassNotFoundException paramClass)
    {
      throw new RuntimeException("Unable to create class: " + paramString, paramClass);
    }
  }
  
  public String convertToString(Object paramObject)
  {
    return ((Class)paramObject).getName();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */