package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

class DateTimeFormatterBuilder$CharacterLiteral
  implements DateTimePrinter, DateTimeParser
{
  private final char iValue;
  
  DateTimeFormatterBuilder$CharacterLiteral(char paramChar)
  {
    iValue = paramChar;
  }
  
  public int estimateParsedLength()
  {
    return 1;
  }
  
  public int estimatePrintedLength()
  {
    return 1;
  }
  
  public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
  {
    if (paramInt >= paramString.length()) {
      return paramInt ^ 0xFFFFFFFF;
    }
    char c2 = paramString.charAt(paramInt);
    char c1 = iValue;
    if (c2 != c1)
    {
      c2 = Character.toUpperCase(c2);
      c1 = Character.toUpperCase(c1);
      if ((c2 != c1) && (Character.toLowerCase(c2) != Character.toLowerCase(c1))) {
        return paramInt ^ 0xFFFFFFFF;
      }
    }
    return paramInt + 1;
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
 * Qualified Name:     org.joda.time.format.DateTimeFormatterBuilder.CharacterLiteral
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */