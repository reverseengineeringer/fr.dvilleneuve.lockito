package org.joda.time.format;

class DateTimeFormatterBuilder$MatchingParser
  implements DateTimeParser
{
  private final int iParsedLengthEstimate;
  private final DateTimeParser[] iParsers;
  
  DateTimeFormatterBuilder$MatchingParser(DateTimeParser[] paramArrayOfDateTimeParser)
  {
    iParsers = paramArrayOfDateTimeParser;
    int j = 0;
    int i = paramArrayOfDateTimeParser.length;
    for (;;)
    {
      int k = i - 1;
      if (k < 0) {
        break;
      }
      DateTimeParser localDateTimeParser = paramArrayOfDateTimeParser[k];
      i = k;
      if (localDateTimeParser != null)
      {
        int m = localDateTimeParser.estimateParsedLength();
        i = k;
        if (m > j)
        {
          j = m;
          i = k;
        }
      }
    }
    iParsedLengthEstimate = j;
  }
  
  public int estimateParsedLength()
  {
    return iParsedLengthEstimate;
  }
  
  public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
  {
    DateTimeParser[] arrayOfDateTimeParser = iParsers;
    int i3 = arrayOfDateTimeParser.length;
    Object localObject3 = paramDateTimeParserBucket.saveState();
    int i1 = 0;
    int i = paramInt;
    Object localObject1 = null;
    int k = paramInt;
    int j = 0;
    int m = i1;
    Object localObject2;
    if (j < i3)
    {
      localObject2 = arrayOfDateTimeParser[j];
      if (localObject2 == null)
      {
        if (i <= paramInt) {
          return paramInt;
        }
        m = 1;
      }
    }
    else
    {
      if ((i <= paramInt) && ((i != paramInt) || (m == 0))) {
        break label265;
      }
      if (localObject1 != null) {
        paramDateTimeParserBucket.restoreState(localObject1);
      }
      return i;
    }
    int i2 = ((DateTimeParser)localObject2).parseInto(paramDateTimeParserBucket, paramString, paramInt);
    int n;
    if (i2 >= paramInt)
    {
      m = k;
      n = i;
      localObject2 = localObject1;
      if (i2 > i)
      {
        if ((i2 >= paramString.length()) || (j + 1 >= i3) || (arrayOfDateTimeParser[(j + 1)] == null)) {
          return i2;
        }
        n = i2;
        localObject2 = paramDateTimeParserBucket.saveState();
        m = k;
      }
    }
    for (;;)
    {
      paramDateTimeParserBucket.restoreState(localObject3);
      j += 1;
      k = m;
      i = n;
      localObject1 = localObject2;
      break;
      m = k;
      n = i;
      localObject2 = localObject1;
      if (i2 < 0)
      {
        i2 ^= 0xFFFFFFFF;
        m = k;
        n = i;
        localObject2 = localObject1;
        if (i2 > k)
        {
          m = i2;
          n = i;
          localObject2 = localObject1;
        }
      }
    }
    label265:
    return k ^ 0xFFFFFFFF;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormatterBuilder.MatchingParser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */