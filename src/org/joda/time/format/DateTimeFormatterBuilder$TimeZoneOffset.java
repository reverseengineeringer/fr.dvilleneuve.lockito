package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

class DateTimeFormatterBuilder$TimeZoneOffset
  implements DateTimePrinter, DateTimeParser
{
  private final int iMaxFields;
  private final int iMinFields;
  private final boolean iShowSeparators;
  private final String iZeroOffsetParseText;
  private final String iZeroOffsetPrintText;
  
  DateTimeFormatterBuilder$TimeZoneOffset(String paramString1, String paramString2, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    iZeroOffsetPrintText = paramString1;
    iZeroOffsetParseText = paramString2;
    iShowSeparators = paramBoolean;
    if ((paramInt1 <= 0) || (paramInt2 < paramInt1)) {
      throw new IllegalArgumentException();
    }
    int i = paramInt1;
    if (paramInt1 > 4)
    {
      i = 4;
      paramInt2 = 4;
    }
    iMinFields = i;
    iMaxFields = paramInt2;
  }
  
  private int digitCount(String paramString, int paramInt1, int paramInt2)
  {
    paramInt2 = Math.min(paramString.length() - paramInt1, paramInt2);
    int i = 0;
    for (;;)
    {
      if (paramInt2 > 0)
      {
        int j = paramString.charAt(paramInt1 + i);
        if ((j >= 48) && (j <= 57)) {}
      }
      else
      {
        return i;
      }
      i += 1;
      paramInt2 -= 1;
    }
  }
  
  public int estimateParsedLength()
  {
    return estimatePrintedLength();
  }
  
  public int estimatePrintedLength()
  {
    int j = iMinFields + 1 << 1;
    int i = j;
    if (iShowSeparators) {
      i = j + (iMinFields - 1);
    }
    j = i;
    if (iZeroOffsetPrintText != null)
    {
      j = i;
      if (iZeroOffsetPrintText.length() > i) {
        j = iZeroOffsetPrintText.length();
      }
    }
    return j;
  }
  
  public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
  {
    int i = paramString.length() - paramInt;
    if (iZeroOffsetParseText != null)
    {
      if (iZeroOffsetParseText.length() != 0) {
        break label71;
      }
      if (i <= 0) {
        break label61;
      }
      j = paramString.charAt(paramInt);
      if ((j != 45) && (j != 43)) {
        break label61;
      }
    }
    while (i <= 1)
    {
      return paramInt ^ 0xFFFFFFFF;
      label61:
      paramDateTimeParserBucket.setOffset(Integer.valueOf(0));
      return paramInt;
      label71:
      if (paramString.regionMatches(true, paramInt, iZeroOffsetParseText, 0, iZeroOffsetParseText.length()))
      {
        paramDateTimeParserBucket.setOffset(Integer.valueOf(0));
        return iZeroOffsetParseText.length() + paramInt;
      }
    }
    int j = paramString.charAt(paramInt);
    if (j == 45) {}
    for (int k = 1;; k = 0)
    {
      paramInt += 1;
      if (digitCount(paramString, paramInt, 2) >= 2) {
        break label163;
      }
      return paramInt ^ 0xFFFFFFFF;
      if (j != 43) {
        break;
      }
    }
    return paramInt ^ 0xFFFFFFFF;
    label163:
    j = FormatUtils.parseTwoDigits(paramString, paramInt);
    if (j > 23) {
      return paramInt ^ 0xFFFFFFFF;
    }
    int i1 = j * 3600000;
    int n = i - 1 - 2;
    j = paramInt + 2;
    if (n <= 0)
    {
      paramInt = j;
      i = i1;
    }
    for (;;)
    {
      j = i;
      if (k != 0) {
        j = -i;
      }
      paramDateTimeParserBucket.setOffset(Integer.valueOf(j));
      return paramInt;
      int m = paramString.charAt(j);
      if (m == 58)
      {
        m = 1;
        n -= 1;
        j += 1;
      }
      for (;;)
      {
        i2 = digitCount(paramString, j, 2);
        if (i2 == 0)
        {
          i = i1;
          paramInt = j;
          if (m == 0) {
            break;
          }
        }
        if (i2 >= 2) {
          break label340;
        }
        return j ^ 0xFFFFFFFF;
        i = i1;
        paramInt = j;
        if (m < 48) {
          break;
        }
        i = i1;
        paramInt = j;
        if (m > 57) {
          break;
        }
        m = 0;
      }
      label340:
      paramInt = FormatUtils.parseTwoDigits(paramString, j);
      if (paramInt > 59) {
        return j ^ 0xFFFFFFFF;
      }
      int i2 = i1 + 60000 * paramInt;
      int i3 = n - 2;
      i1 = j + 2;
      i = i2;
      paramInt = i1;
      if (i3 > 0)
      {
        n = i3;
        j = i1;
        if (m != 0)
        {
          i = i2;
          paramInt = i1;
          if (paramString.charAt(i1) == ':')
          {
            n = i3 - 1;
            j = i1 + 1;
          }
        }
        else
        {
          i1 = digitCount(paramString, j, 2);
          if (i1 == 0)
          {
            i = i2;
            paramInt = j;
            if (m == 0) {}
          }
          else
          {
            if (i1 < 2) {
              return j ^ 0xFFFFFFFF;
            }
            paramInt = FormatUtils.parseTwoDigits(paramString, j);
            if (paramInt > 59) {
              return j ^ 0xFFFFFFFF;
            }
            i1 = i2 + paramInt * 1000;
            i2 = n - 2;
            n = j + 2;
            i = i1;
            paramInt = n;
            if (i2 > 0)
            {
              j = n;
              if (m != 0)
              {
                if (paramString.charAt(n) != '.')
                {
                  i = i1;
                  paramInt = n;
                  if (paramString.charAt(n) != ',') {}
                }
                else
                {
                  j = n + 1;
                }
              }
              else
              {
                n = digitCount(paramString, j, 3);
                if (n == 0)
                {
                  i = i1;
                  paramInt = j;
                  if (m == 0) {}
                }
                else
                {
                  if (n < 1) {
                    return j ^ 0xFFFFFFFF;
                  }
                  paramInt = j + 1;
                  i = i1 + (paramString.charAt(j) - '0') * 100;
                  if (n > 1)
                  {
                    j = paramInt + 1;
                    m = i + (paramString.charAt(paramInt) - '0') * 10;
                    i = m;
                    paramInt = j;
                    if (n > 2)
                    {
                      i = m + (paramString.charAt(j) - '0');
                      paramInt = j + 1;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    throws IOException
  {
    if (paramDateTimeZone == null) {
      return;
    }
    if ((paramInt == 0) && (iZeroOffsetPrintText != null))
    {
      paramWriter.write(iZeroOffsetPrintText);
      return;
    }
    if (paramInt >= 0) {
      paramWriter.write(43);
    }
    for (;;)
    {
      int i = paramInt / 3600000;
      FormatUtils.writePaddedInteger(paramWriter, i, 2);
      if (iMaxFields == 1) {
        break;
      }
      paramInt -= 3600000 * i;
      if ((paramInt == 0) && (iMinFields == 1)) {
        break;
      }
      i = paramInt / 60000;
      if (iShowSeparators) {
        paramWriter.write(58);
      }
      FormatUtils.writePaddedInteger(paramWriter, i, 2);
      if (iMaxFields == 2) {
        break;
      }
      paramInt -= 60000 * i;
      if ((paramInt == 0) && (iMinFields == 2)) {
        break;
      }
      i = paramInt / 1000;
      if (iShowSeparators) {
        paramWriter.write(58);
      }
      FormatUtils.writePaddedInteger(paramWriter, i, 2);
      if (iMaxFields == 3) {
        break;
      }
      paramInt -= i * 1000;
      if ((paramInt == 0) && (iMinFields == 3)) {
        break;
      }
      if (iShowSeparators) {
        paramWriter.write(46);
      }
      FormatUtils.writePaddedInteger(paramWriter, paramInt, 3);
      return;
      paramWriter.write(45);
      paramInt = -paramInt;
    }
  }
  
  public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
    throws IOException
  {}
  
  public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
  {
    if (paramDateTimeZone == null) {
      return;
    }
    if ((paramInt == 0) && (iZeroOffsetPrintText != null))
    {
      paramStringBuffer.append(iZeroOffsetPrintText);
      return;
    }
    if (paramInt >= 0) {
      paramStringBuffer.append('+');
    }
    for (;;)
    {
      int i = paramInt / 3600000;
      FormatUtils.appendPaddedInteger(paramStringBuffer, i, 2);
      if (iMaxFields == 1) {
        break;
      }
      paramInt -= 3600000 * i;
      if ((paramInt == 0) && (iMinFields <= 1)) {
        break;
      }
      i = paramInt / 60000;
      if (iShowSeparators) {
        paramStringBuffer.append(':');
      }
      FormatUtils.appendPaddedInteger(paramStringBuffer, i, 2);
      if (iMaxFields == 2) {
        break;
      }
      paramInt -= 60000 * i;
      if ((paramInt == 0) && (iMinFields <= 2)) {
        break;
      }
      i = paramInt / 1000;
      if (iShowSeparators) {
        paramStringBuffer.append(':');
      }
      FormatUtils.appendPaddedInteger(paramStringBuffer, i, 2);
      if (iMaxFields == 3) {
        break;
      }
      paramInt -= i * 1000;
      if ((paramInt == 0) && (iMinFields <= 3)) {
        break;
      }
      if (iShowSeparators) {
        paramStringBuffer.append('.');
      }
      FormatUtils.appendPaddedInteger(paramStringBuffer, paramInt, 3);
      return;
      paramStringBuffer.append('-');
      paramInt = -paramInt;
    }
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale) {}
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormatterBuilder.TimeZoneOffset
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */