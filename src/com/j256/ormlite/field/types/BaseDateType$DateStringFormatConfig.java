package com.j256.ormlite.field.types;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BaseDateType$DateStringFormatConfig
{
  final String dateFormatStr;
  private final ThreadLocal<DateFormat> threadLocal = new ThreadLocal()
  {
    protected DateFormat initialValue()
    {
      return new SimpleDateFormat(dateFormatStr);
    }
  };
  
  public BaseDateType$DateStringFormatConfig(String paramString)
  {
    dateFormatStr = paramString;
  }
  
  public DateFormat getDateFormat()
  {
    return (DateFormat)threadLocal.get();
  }
  
  public String toString()
  {
    return dateFormatStr;
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.field.types.BaseDateType.DateStringFormatConfig
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */