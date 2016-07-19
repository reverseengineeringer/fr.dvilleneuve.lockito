package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

 enum DateTimeFormatterBuilder$TimeZoneId
  implements DateTimePrinter, DateTimeParser
{
  static final Set<String> ALL_IDS;
  static final int MAX_LENGTH;
  
  static
  {
    $VALUES = new TimeZoneId[] { INSTANCE };
    ALL_IDS = DateTimeZone.getAvailableIDs();
    int i = 0;
    Iterator localIterator = ALL_IDS.iterator();
    while (localIterator.hasNext()) {
      i = Math.max(i, ((String)localIterator.next()).length());
    }
    MAX_LENGTH = i;
  }
  
  private DateTimeFormatterBuilder$TimeZoneId() {}
  
  public int estimateParsedLength()
  {
    return MAX_LENGTH;
  }
  
  public int estimatePrintedLength()
  {
    return MAX_LENGTH;
  }
  
  public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
  {
    String str2 = paramString.substring(paramInt);
    paramString = null;
    Iterator localIterator = ALL_IDS.iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      if ((str2.startsWith(str1)) && ((paramString == null) || (str1.length() > paramString.length()))) {
        paramString = str1;
      }
    }
    if (paramString != null)
    {
      paramDateTimeParserBucket.setZone(DateTimeZone.forID(paramString));
      return paramString.length() + paramInt;
    }
    return paramInt ^ 0xFFFFFFFF;
  }
  
  public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    throws IOException
  {
    if (paramDateTimeZone != null) {}
    for (paramChronology = paramDateTimeZone.getID();; paramChronology = "")
    {
      paramWriter.write(paramChronology);
      return;
    }
  }
  
  public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
    throws IOException
  {}
  
  public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
  {
    if (paramDateTimeZone != null) {}
    for (paramChronology = paramDateTimeZone.getID();; paramChronology = "")
    {
      paramStringBuffer.append(paramChronology);
      return;
    }
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale) {}
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormatterBuilder.TimeZoneId
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */