package org.joda.convert;

 enum JDKStringConverter$10
{
  JDKStringConverter$10(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    if (paramString.length() != 1) {
      throw new IllegalArgumentException("Character value must be a string length 1");
    }
    return new Character(paramString.charAt(0));
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.10
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */