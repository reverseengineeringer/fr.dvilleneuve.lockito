package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

class DateTimeFormatterBuilder$TwoDigitYear
  implements DateTimePrinter, DateTimeParser
{
  private final boolean iLenientParse;
  private final int iPivot;
  private final DateTimeFieldType iType;
  
  DateTimeFormatterBuilder$TwoDigitYear(DateTimeFieldType paramDateTimeFieldType, int paramInt, boolean paramBoolean)
  {
    iType = paramDateTimeFieldType;
    iPivot = paramInt;
    iLenientParse = paramBoolean;
  }
  
  private int getTwoDigitYear(long paramLong, Chronology paramChronology)
  {
    try
    {
      int j = iType.getField(paramChronology).get(paramLong);
      int i = j;
      if (j < 0) {
        i = -j;
      }
      return i % 100;
    }
    catch (RuntimeException paramChronology) {}
    return -1;
  }
  
  private int getTwoDigitYear(ReadablePartial paramReadablePartial)
  {
    if (paramReadablePartial.isSupported(iType)) {
      try
      {
        int j = paramReadablePartial.get(iType);
        int i = j;
        if (j < 0) {
          i = -j;
        }
        return i % 100;
      }
      catch (RuntimeException paramReadablePartial) {}
    }
    return -1;
  }
  
  public int estimateParsedLength()
  {
    if (iLenientParse) {
      return 4;
    }
    return 2;
  }
  
  public int estimatePrintedLength()
  {
    return 2;
  }
  
  public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
  {
    int k = paramString.length() - paramInt;
    int i;
    int m;
    int j;
    int n;
    if (!iLenientParse)
    {
      i = paramInt;
      if (Math.min(2, k) < 2) {
        return paramInt ^ 0xFFFFFFFF;
      }
    }
    else
    {
      m = 0;
      j = 0;
      i = 0;
      for (;;)
      {
        if (i < k)
        {
          n = paramString.charAt(paramInt + i);
          if ((i == 0) && ((n == 45) || (n == 43)))
          {
            m = 1;
            if (n == 45) {}
            for (j = 1;; j = 0)
            {
              if (j == 0) {
                break label110;
              }
              i += 1;
              break;
            }
            label110:
            k -= 1;
            paramInt += 1;
            continue;
          }
          if ((n >= 48) && (n <= 57)) {}
        }
        else
        {
          if (i != 0) {
            break;
          }
          return paramInt ^ 0xFFFFFFFF;
        }
        i += 1;
      }
      if ((m != 0) || (i != 2))
      {
        if (i >= 9)
        {
          k = paramInt + i;
          i = Integer.parseInt(paramString.substring(paramInt, k));
          paramDateTimeParserBucket.saveField(iType, i);
          return k;
        }
        if (j == 0) {
          break label472;
        }
      }
    }
    label466:
    label472:
    for (k = paramInt + 1;; k = paramInt)
    {
      try
      {
        m = paramString.charAt(k);
        n = m - 48;
        m = paramInt + i;
        i = k + 1;
        paramInt = n;
        while (i < m)
        {
          paramInt = (paramInt << 3) + (paramInt << 1) + paramString.charAt(i) - 48;
          i += 1;
        }
        i = paramInt;
      }
      catch (StringIndexOutOfBoundsException paramDateTimeParserBucket)
      {
        return paramInt ^ 0xFFFFFFFF;
      }
      k = m;
      if (j == 0) {
        break;
      }
      i = -paramInt;
      k = m;
      break;
      i = paramInt;
      paramInt = paramString.charAt(i);
      if ((paramInt < 48) || (paramInt > 57)) {
        return i ^ 0xFFFFFFFF;
      }
      paramInt -= 48;
      j = paramString.charAt(i + 1);
      if ((j < 48) || (j > 57)) {
        return i ^ 0xFFFFFFFF;
      }
      k = (paramInt << 3) + (paramInt << 1) + j - 48;
      paramInt = iPivot;
      if (paramDateTimeParserBucket.getPivotYear() != null) {
        paramInt = paramDateTimeParserBucket.getPivotYear().intValue();
      }
      m = paramInt - 50;
      if (m >= 0)
      {
        paramInt = m % 100;
        if (k >= paramInt) {
          break label466;
        }
      }
      for (j = 100;; j = 0)
      {
        paramDateTimeParserBucket.saveField(iType, k + (j + m - paramInt));
        return i + 2;
        paramInt = (m + 1) % 100 + 99;
        break;
      }
    }
  }
  
  public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    throws IOException
  {
    paramInt = getTwoDigitYear(paramLong, paramChronology);
    if (paramInt < 0)
    {
      paramWriter.write(65533);
      paramWriter.write(65533);
      return;
    }
    FormatUtils.writePaddedInteger(paramWriter, paramInt, 2);
  }
  
  public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
    throws IOException
  {
    int i = getTwoDigitYear(paramReadablePartial);
    if (i < 0)
    {
      paramWriter.write(65533);
      paramWriter.write(65533);
      return;
    }
    FormatUtils.writePaddedInteger(paramWriter, i, 2);
  }
  
  public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
  {
    paramInt = getTwoDigitYear(paramLong, paramChronology);
    if (paramInt < 0)
    {
      paramStringBuffer.append(65533);
      paramStringBuffer.append(65533);
      return;
    }
    FormatUtils.appendPaddedInteger(paramStringBuffer, paramInt, 2);
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
  {
    int i = getTwoDigitYear(paramReadablePartial);
    if (i < 0)
    {
      paramStringBuffer.append(65533);
      paramStringBuffer.append(65533);
      return;
    }
    FormatUtils.appendPaddedInteger(paramStringBuffer, i, 2);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormatterBuilder.TwoDigitYear
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */