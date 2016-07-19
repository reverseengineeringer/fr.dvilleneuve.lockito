package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

class DateTimeFormatterBuilder$UnpaddedNumber
  extends DateTimeFormatterBuilder.NumberFormatter
{
  protected DateTimeFormatterBuilder$UnpaddedNumber(DateTimeFieldType paramDateTimeFieldType, int paramInt, boolean paramBoolean)
  {
    super(paramDateTimeFieldType, paramInt, paramBoolean);
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
      FormatUtils.writeUnpaddedInteger(paramWriter, iFieldType.getField(paramChronology).get(paramLong));
      return;
    }
    catch (RuntimeException paramChronology)
    {
      paramWriter.write(65533);
    }
  }
  
  public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
    throws IOException
  {
    if (paramReadablePartial.isSupported(iFieldType)) {
      try
      {
        FormatUtils.writeUnpaddedInteger(paramWriter, paramReadablePartial.get(iFieldType));
        return;
      }
      catch (RuntimeException paramReadablePartial)
      {
        paramWriter.write(65533);
        return;
      }
    }
    paramWriter.write(65533);
  }
  
  public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
  {
    try
    {
      FormatUtils.appendUnpaddedInteger(paramStringBuffer, iFieldType.getField(paramChronology).get(paramLong));
      return;
    }
    catch (RuntimeException paramChronology)
    {
      paramStringBuffer.append(65533);
    }
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
  {
    if (paramReadablePartial.isSupported(iFieldType)) {
      try
      {
        FormatUtils.appendUnpaddedInteger(paramStringBuffer, paramReadablePartial.get(iFieldType));
        return;
      }
      catch (RuntimeException paramReadablePartial)
      {
        paramStringBuffer.append(65533);
        return;
      }
    }
    paramStringBuffer.append(65533);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormatterBuilder.UnpaddedNumber
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */