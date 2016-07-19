package org.joda.convert;

import javax.xml.bind.DatatypeConverter;

 enum JDKStringConverter$9
{
  JDKStringConverter$9(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    return DatatypeConverter.parseBase64Binary(paramString);
  }
  
  public String convertToString(Object paramObject)
  {
    return DatatypeConverter.printBase64Binary((byte[])paramObject);
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.9
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */