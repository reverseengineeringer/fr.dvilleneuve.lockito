package org.joda.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;

 enum JDKStringConverter$30
{
  JDKStringConverter$30(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    if (paramString.length() != 29) {
      throw new IllegalArgumentException("Unable to parse date: " + paramString);
    }
    paramClass = paramString.substring(0, 26) + paramString.substring(27);
    paramString = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    try
    {
      paramClass = paramString.parseObject(paramClass);
      return paramClass;
    }
    catch (ParseException paramClass)
    {
      throw new RuntimeException(paramClass);
    }
  }
  
  public String convertToString(Object paramObject)
  {
    paramObject = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(paramObject);
    return ((String)paramObject).substring(0, 26) + ":" + ((String)paramObject).substring(26);
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.30
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */