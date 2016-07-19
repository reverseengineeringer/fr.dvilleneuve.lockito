package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

class DateTimeFormatterBuilder$StringLiteral
  implements DateTimePrinter, DateTimeParser
{
  private final String iValue;
  
  DateTimeFormatterBuilder$StringLiteral(String paramString)
  {
    iValue = paramString;
  }
  
  public int estimateParsedLength()
  {
    return iValue.length();
  }
  
  public int estimatePrintedLength()
  {
    return iValue.length();
  }
  
  public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
  {
    if (paramString.regionMatches(true, paramInt, iValue, 0, iValue.length())) {
      return iValue.length() + paramInt;
    }
    return paramInt ^ 0xFFFFFFFF;
  }
  
  public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    throws IOException
  {
    paramWriter.write(iValue);
  }
  
  public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
    throws IOException
  {
    paramWriter.write(iValue);
  }
  
  public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
  {
    paramStringBuffer.append(iValue);
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
  {
    paramStringBuffer.append(iValue);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormatterBuilder.StringLiteral
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */