package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.MillisDurationField;
import org.joda.time.field.PreciseDateTimeField;

class DateTimeFormatterBuilder$Fraction
  implements DateTimePrinter, DateTimeParser
{
  private final DateTimeFieldType iFieldType;
  protected int iMaxDigits;
  protected int iMinDigits;
  
  protected DateTimeFormatterBuilder$Fraction(DateTimeFieldType paramDateTimeFieldType, int paramInt1, int paramInt2)
  {
    iFieldType = paramDateTimeFieldType;
    int i = paramInt2;
    if (paramInt2 > 18) {
      i = 18;
    }
    iMinDigits = paramInt1;
    iMaxDigits = i;
  }
  
  private long[] getFractionData(long paramLong, DateTimeField paramDateTimeField)
  {
    long l2 = paramDateTimeField.getDurationField().getUnitMillis();
    int i = iMaxDigits;
    for (;;)
    {
      long l1;
      switch (i)
      {
      default: 
        l1 = 1L;
      }
      while (l2 * l1 / l1 == l2)
      {
        return new long[] { paramLong * l1 / l2, i };
        l1 = 10L;
        continue;
        l1 = 100L;
        continue;
        l1 = 1000L;
        continue;
        l1 = 10000L;
        continue;
        l1 = 100000L;
        continue;
        l1 = 1000000L;
        continue;
        l1 = 10000000L;
        continue;
        l1 = 100000000L;
        continue;
        l1 = 1000000000L;
        continue;
        l1 = 10000000000L;
        continue;
        l1 = 100000000000L;
        continue;
        l1 = 1000000000000L;
        continue;
        l1 = 10000000000000L;
        continue;
        l1 = 100000000000000L;
        continue;
        l1 = 1000000000000000L;
        continue;
        l1 = 10000000000000000L;
        continue;
        l1 = 100000000000000000L;
        continue;
        l1 = 1000000000000000000L;
      }
      i -= 1;
    }
  }
  
  public int estimateParsedLength()
  {
    return iMaxDigits;
  }
  
  public int estimatePrintedLength()
  {
    return iMaxDigits;
  }
  
  public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
  {
    DateTimeField localDateTimeField = iFieldType.getField(paramDateTimeParserBucket.getChronology());
    int j = Math.min(iMaxDigits, paramString.length() - paramInt);
    long l1 = 0L;
    long l2 = localDateTimeField.getDurationField().getUnitMillis() * 10L;
    int i = 0;
    for (;;)
    {
      int k;
      if (i < j)
      {
        k = paramString.charAt(paramInt + i);
        if ((k >= 48) && (k <= 57)) {}
      }
      else
      {
        l1 /= 10L;
        if (i != 0) {
          break;
        }
        return paramInt ^ 0xFFFFFFFF;
      }
      i += 1;
      l2 /= 10L;
      l1 += (k - 48) * l2;
    }
    if (l1 > 2147483647L) {
      return paramInt ^ 0xFFFFFFFF;
    }
    paramDateTimeParserBucket.saveField(new PreciseDateTimeField(DateTimeFieldType.millisOfSecond(), MillisDurationField.INSTANCE, localDateTimeField.getDurationField()), (int)l1);
    return paramInt + i;
  }
  
  public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    throws IOException
  {
    printTo(null, paramWriter, paramLong, paramChronology);
  }
  
  public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
    throws IOException
  {
    printTo(null, paramWriter, paramReadablePartial.getChronology().set(paramReadablePartial, 0L), paramReadablePartial.getChronology());
  }
  
  public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
  {
    try
    {
      printTo(paramStringBuffer, null, paramLong, paramChronology);
      return;
    }
    catch (IOException paramStringBuffer) {}
  }
  
  protected void printTo(StringBuffer paramStringBuffer, Writer paramWriter, long paramLong, Chronology paramChronology)
    throws IOException
  {
    paramChronology = iFieldType.getField(paramChronology);
    int i = iMinDigits;
    for (;;)
    {
      try
      {
        paramLong = paramChronology.remainder(paramLong);
        if (paramLong != 0L) {
          break label99;
        }
        j = i;
        if (paramStringBuffer != null)
        {
          i -= 1;
          if (i >= 0)
          {
            paramStringBuffer.append('0');
            continue;
          }
        }
        j -= 1;
      }
      catch (RuntimeException paramChronology)
      {
        if (paramStringBuffer != null)
        {
          DateTimeFormatterBuilder.appendUnknownString(paramStringBuffer, i);
          return;
        }
        DateTimeFormatterBuilder.printUnknownString(paramWriter, i);
        return;
      }
      while (j >= 0) {
        paramWriter.write(48);
      }
      continue;
      label99:
      paramChronology = getFractionData(paramLong, paramChronology);
      paramLong = paramChronology[0];
      int j = (int)paramChronology[1];
      int m;
      int k;
      if ((0x7FFFFFFF & paramLong) == paramLong)
      {
        paramChronology = Integer.toString((int)paramLong);
        m = paramChronology.length();
        k = i;
        i = j;
        label152:
        if (m >= i) {
          break label203;
        }
        if (paramStringBuffer == null) {
          break label194;
        }
        paramStringBuffer.append('0');
      }
      for (;;)
      {
        k -= 1;
        i -= 1;
        break label152;
        paramChronology = Long.toString(paramLong);
        break;
        label194:
        paramWriter.write(48);
      }
      label203:
      if (k >= i) {
        break;
      }
      j = m;
      for (;;)
      {
        if ((k >= i) || (j <= 1) || (paramChronology.charAt(j - 1) != '0'))
        {
          if (j >= paramChronology.length()) {
            break label331;
          }
          if (paramStringBuffer == null) {
            break label301;
          }
          i = 0;
          while (i < j)
          {
            paramStringBuffer.append(paramChronology.charAt(i));
            i += 1;
          }
          break;
        }
        i -= 1;
        j -= 1;
      }
      label301:
      i = 0;
      while (i < j)
      {
        paramWriter.write(paramChronology.charAt(i));
        i += 1;
      }
    }
    label331:
    if (paramStringBuffer != null)
    {
      paramStringBuffer.append(paramChronology);
      return;
    }
    paramWriter.write(paramChronology);
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
  {
    long l = paramReadablePartial.getChronology().set(paramReadablePartial, 0L);
    try
    {
      printTo(paramStringBuffer, null, l, paramReadablePartial.getChronology());
      return;
    }
    catch (IOException paramStringBuffer) {}
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormatterBuilder.Fraction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */