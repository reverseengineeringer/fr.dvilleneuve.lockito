package org.joda.time.format;

import org.joda.time.DateTimeFieldType;

abstract class DateTimeFormatterBuilder$NumberFormatter
  implements DateTimePrinter, DateTimeParser
{
  protected final DateTimeFieldType iFieldType;
  protected final int iMaxParsedDigits;
  protected final boolean iSigned;
  
  DateTimeFormatterBuilder$NumberFormatter(DateTimeFieldType paramDateTimeFieldType, int paramInt, boolean paramBoolean)
  {
    iFieldType = paramDateTimeFieldType;
    iMaxParsedDigits = paramInt;
    iSigned = paramBoolean;
  }
  
  public int estimateParsedLength()
  {
    return iMaxParsedDigits;
  }
  
  public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
  {
    int m = Math.min(iMaxParsedDigits, paramString.length() - paramInt);
    int i = 0;
    int j = 0;
    int k;
    int n;
    for (;;)
    {
      k = i;
      if (j < m)
      {
        n = paramString.charAt(paramInt + j);
        if ((j != 0) || ((n != 45) && (n != 43)) || (!iSigned)) {
          break label176;
        }
        if (n != 45) {
          break label134;
        }
        i = 1;
        label78:
        k = i;
        if (j + 1 < m)
        {
          n = paramString.charAt(paramInt + j + 1);
          k = i;
          if (n >= 48)
          {
            if (n <= 57) {
              break label140;
            }
            k = i;
          }
        }
      }
      label134:
      label140:
      label176:
      do
      {
        do
        {
          if (j != 0) {
            break label207;
          }
          return paramInt ^ 0xFFFFFFFF;
          i = 0;
          break label78;
          if (i != 0) {
            j += 1;
          }
          for (;;)
          {
            m = Math.min(m + 1, paramString.length() - paramInt);
            break;
            paramInt += 1;
          }
          k = i;
        } while (n < 48);
        k = i;
      } while (n > 57);
      j += 1;
    }
    label207:
    if (j >= 9)
    {
      j = paramInt + j;
      i = Integer.parseInt(paramString.substring(paramInt, j));
      paramDateTimeParserBucket.saveField(iFieldType, i);
      return j;
    }
    if (k != 0) {}
    for (i = paramInt + 1;; i = paramInt)
    {
      try
      {
        m = paramString.charAt(i);
        n = m - 48;
        m = paramInt + j;
        i += 1;
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
      j = m;
      if (k == 0) {
        break;
      }
      i = -paramInt;
      j = m;
      break;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormatterBuilder.NumberFormatter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */