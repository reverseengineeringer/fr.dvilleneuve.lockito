package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

class DateTimeFormatterBuilder$PaddedNumber
  extends DateTimeFormatterBuilder.NumberFormatter
{
  protected final int iMinPrintedDigits;
  
  protected DateTimeFormatterBuilder$PaddedNumber(DateTimeFieldType paramDateTimeFieldType, int paramInt1, boolean paramBoolean, int paramInt2)
  {
    super(paramDateTimeFieldType, paramInt1, paramBoolean);
    iMinPrintedDigits = paramInt2;
  }
  
  public int estimatePrintedLength()
  {
    return iMaxParsedDigits;
  }
  
  public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    throws IOException
  {
    try
    {
      FormatUtils.writePaddedInteger(paramWriter, iFieldType.getField(paramChronology).get(paramLong), iMinPrintedDigits);
      return;
    }
    catch (RuntimeException paramChronology)
    {
      DateTimeFormatterBuilder.printUnknownString(paramWriter, iMinPrintedDigits);
    }
  }
  
  public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
    throws IOException
  {
    if (paramReadablePartial.isSupported(iFieldType)) {
      try
      {
        FormatUtils.writePaddedInteger(paramWriter, paramReadablePartial.get(iFieldType), iMinPrintedDigits);
        return;
      }
      catch (RuntimeException paramReadablePartial)
      {
        DateTimeFormatterBuilder.printUnknownString(paramWriter, iMinPrintedDigits);
        return;
      }
    }
    DateTimeFormatterBuilder.printUnknownString(paramWriter, iMinPrintedDigits);
  }
  
  public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
  {
    try
    {
      FormatUtils.appendPaddedInteger(paramStringBuffer, iFieldType.getField(paramChronology).get(paramLong), iMinPrintedDigits);
      return;
    }
    catch (RuntimeException paramChronology)
    {
      DateTimeFormatterBuilder.appendUnknownString(paramStringBuffer, iMinPrintedDigits);
    }
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
  {
    if (paramReadablePartial.isSupported(iFieldType)) {
      try
      {
        FormatUtils.appendPaddedInteger(paramStringBuffer, paramReadablePartial.get(iFieldType), iMinPrintedDigits);
        return;
      }
      catch (RuntimeException paramReadablePartial)
      {
        DateTimeFormatterBuilder.appendUnknownString(paramStringBuffer, iMinPrintedDigits);
        return;
      }
    }
    DateTimeFormatterBuilder.appendUnknownString(paramStringBuffer, iMinPrintedDigits);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormatterBuilder.PaddedNumber
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */