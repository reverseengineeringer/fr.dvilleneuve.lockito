package org.joda.convert;

import java.net.InetAddress;
import java.net.UnknownHostException;

 enum JDKStringConverter$28
{
  JDKStringConverter$28(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    try
    {
      paramClass = InetAddress.getByName(paramString);
      return paramClass;
    }
    catch (UnknownHostException paramClass)
    {
      throw new RuntimeException(paramClass);
    }
  }
  
  public String convertToString(Object paramObject)
  {
    return ((InetAddress)paramObject).getHostAddress();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.28
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */