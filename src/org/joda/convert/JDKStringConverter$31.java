package org.joda.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

 enum JDKStringConverter$31
{
  JDKStringConverter$31(Class paramClass)
  {
    super(paramString, paramInt, paramClass, null);
  }
  
  public Object convertFromString(Class<?> paramClass, String paramString)
  {
    if ((paramString.length() < 31) || (paramString.charAt(26) != ':') || (paramString.charAt(29) != '[') || (paramString.charAt(paramString.length() - 1) != ']')) {
      throw new IllegalArgumentException("Unable to parse date: " + paramString);
    }
    paramClass = TimeZone.getTimeZone(paramString.substring(30, paramString.length() - 1));
    paramString = paramString.substring(0, 26) + paramString.substring(27, 29);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    paramClass = new GregorianCalendar(paramClass);
    paramClass.setTimeInMillis(0L);
    localSimpleDateFormat.setCalendar(paramClass);
    try
    {
      localSimpleDateFormat.parseObject(paramString);
      paramClass = localSimpleDateFormat.getCalendar();
      return paramClass;
    }
    catch (ParseException paramClass)
    {
      throw new RuntimeException(paramClass);
    }
  }
  
  public String convertToString(Object paramObject)
  {
    if (!(paramObject instanceof GregorianCalendar)) {
      throw new RuntimeException("Unable to convert calendar as it is not a GregorianCalendar");
    }
    paramObject = (GregorianCalendar)paramObject;
    Object localObject = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    ((SimpleDateFormat)localObject).setCalendar((Calendar)paramObject);
    localObject = ((SimpleDateFormat)localObject).format(((GregorianCalendar)paramObject).getTime());
    return ((String)localObject).substring(0, 26) + ":" + ((String)localObject).substring(26) + "[" + ((GregorianCalendar)paramObject).getTimeZone().getID() + "]";
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.JDKStringConverter.31
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */