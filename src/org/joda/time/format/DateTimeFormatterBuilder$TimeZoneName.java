package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

class DateTimeFormatterBuilder$TimeZoneName
  implements DateTimePrinter, DateTimeParser
{
  static final int LONG_NAME = 0;
  static final int SHORT_NAME = 1;
  private final Map<String, DateTimeZone> iParseLookup;
  private final int iType;
  
  DateTimeFormatterBuilder$TimeZoneName(int paramInt, Map<String, DateTimeZone> paramMap)
  {
    iType = paramInt;
    iParseLookup = paramMap;
  }
  
  private String print(long paramLong, DateTimeZone paramDateTimeZone, Locale paramLocale)
  {
    if (paramDateTimeZone == null) {
      return "";
    }
    switch (iType)
    {
    default: 
      return "";
    case 0: 
      return paramDateTimeZone.getName(paramLong, paramLocale);
    }
    return paramDateTimeZone.getShortName(paramLong, paramLocale);
  }
  
  public int estimateParsedLength()
  {
    if (iType == 1) {
      return 4;
    }
    return 20;
  }
  
  public int estimatePrintedLength()
  {
    if (iType == 1) {
      return 4;
    }
    return 20;
  }
  
  public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
  {
    Map localMap = iParseLookup;
    if (localMap != null) {}
    for (;;)
    {
      String str2 = paramString.substring(paramInt);
      paramString = null;
      Iterator localIterator = localMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        if ((str2.startsWith(str1)) && ((paramString == null) || (str1.length() > paramString.length()))) {
          paramString = str1;
        }
      }
      localMap = DateTimeUtils.getDefaultTimeZoneNames();
    }
    if (paramString != null)
    {
      paramDateTimeParserBucket.setZone((DateTimeZone)localMap.get(paramString));
      return paramString.length() + paramInt;
    }
    return paramInt ^ 0xFFFFFFFF;
  }
  
  public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    throws IOException
  {
    paramWriter.write(print(paramLong - paramInt, paramDateTimeZone, paramLocale));
  }
  
  public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
    throws IOException
  {}
  
  public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
  {
    paramStringBuffer.append(print(paramLong - paramInt, paramDateTimeZone, paramLocale));
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale) {}
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormatterBuilder.TimeZoneName
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */