package org.joda.time.format;

import org.joda.time.DateTimeFieldType;

class DateTimeFormatterBuilder$FixedNumber
  extends DateTimeFormatterBuilder.PaddedNumber
{
  protected DateTimeFormatterBuilder$FixedNumber(DateTimeFieldType paramDateTimeFieldType, int paramInt, boolean paramBoolean)
  {
    super(paramDateTimeFieldType, paramInt, paramBoolean, paramInt);
  }
  
  public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
  {
    int k = super.parseInto(paramDateTimeParserBucket, paramString, paramInt);
    if (k < 0) {}
    int i;
    do
    {
      int j;
      do
      {
        return k;
        j = paramInt + iMaxParsedDigits;
      } while (k == j);
      i = j;
      if (iSigned)
      {
        paramInt = paramString.charAt(paramInt);
        if (paramInt != 45)
        {
          i = j;
          if (paramInt != 43) {}
        }
        else
        {
          i = j + 1;
        }
      }
      if (k > i) {
        return i + 1 ^ 0xFFFFFFFF;
      }
    } while (k >= i);
    return k ^ 0xFFFFFFFF;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormatterBuilder.FixedNumber
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */