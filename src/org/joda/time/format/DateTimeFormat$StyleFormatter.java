package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

class DateTimeFormat$StyleFormatter
  implements DateTimePrinter, DateTimeParser
{
  private static final Map<String, DateTimeFormatter> cCache = new HashMap();
  private final int iDateStyle;
  private final int iTimeStyle;
  private final int iType;
  
  DateTimeFormat$StyleFormatter(int paramInt1, int paramInt2, int paramInt3)
  {
    iDateStyle = paramInt1;
    iTimeStyle = paramInt2;
    iType = paramInt3;
  }
  
  private DateTimeFormatter getFormatter(Locale paramLocale)
  {
    Locale localLocale = paramLocale;
    if (paramLocale == null) {
      localLocale = Locale.getDefault();
    }
    String str = Integer.toString(iType + (iDateStyle << 4) + (iTimeStyle << 8)) + localLocale.toString();
    synchronized (cCache)
    {
      DateTimeFormatter localDateTimeFormatter = (DateTimeFormatter)cCache.get(str);
      paramLocale = localDateTimeFormatter;
      if (localDateTimeFormatter == null)
      {
        paramLocale = DateTimeFormat.forPattern(getPattern(localLocale));
        cCache.put(str, paramLocale);
      }
      return paramLocale;
    }
  }
  
  public int estimateParsedLength()
  {
    return 40;
  }
  
  public int estimatePrintedLength()
  {
    return 40;
  }
  
  String getPattern(Locale paramLocale)
  {
    DateFormat localDateFormat = null;
    switch (iType)
    {
    }
    while (!(localDateFormat instanceof SimpleDateFormat))
    {
      throw new IllegalArgumentException("No datetime pattern for locale: " + paramLocale);
      localDateFormat = DateFormat.getDateInstance(iDateStyle, paramLocale);
      continue;
      localDateFormat = DateFormat.getTimeInstance(iTimeStyle, paramLocale);
      continue;
      localDateFormat = DateFormat.getDateTimeInstance(iDateStyle, iTimeStyle, paramLocale);
    }
    return ((SimpleDateFormat)localDateFormat).toPattern();
  }
  
  public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
  {
    return getFormatter(paramDateTimeParserBucket.getLocale()).getParser().parseInto(paramDateTimeParserBucket, paramString, paramInt);
  }
  
  public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    throws IOException
  {
    getFormatter(paramLocale).getPrinter().printTo(paramWriter, paramLong, paramChronology, paramInt, paramDateTimeZone, paramLocale);
  }
  
  public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
    throws IOException
  {
    getFormatter(paramLocale).getPrinter().printTo(paramWriter, paramReadablePartial, paramLocale);
  }
  
  public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
  {
    getFormatter(paramLocale).getPrinter().printTo(paramStringBuffer, paramLong, paramChronology, paramInt, paramDateTimeZone, paramLocale);
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
  {
    getFormatter(paramLocale).getPrinter().printTo(paramStringBuffer, paramReadablePartial, paramLocale);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormat.StyleFormatter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */