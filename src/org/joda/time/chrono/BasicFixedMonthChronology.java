package org.joda.time.chrono;

import org.joda.time.Chronology;

abstract class BasicFixedMonthChronology
  extends BasicChronology
{
  static final long MILLIS_PER_MONTH = 2592000000L;
  static final long MILLIS_PER_YEAR = 31557600000L;
  static final int MONTH_LENGTH = 30;
  private static final long serialVersionUID = 261387371998L;
  
  BasicFixedMonthChronology(Chronology paramChronology, Object paramObject, int paramInt)
  {
    super(paramChronology, paramObject, paramInt);
  }
  
  long getAverageMillisPerMonth()
  {
    return 2592000000L;
  }
  
  long getAverageMillisPerYear()
  {
    return 31557600000L;
  }
  
  long getAverageMillisPerYearDividedByTwo()
  {
    return 15778800000L;
  }
  
  int getDayOfMonth(long paramLong)
  {
    return (getDayOfYear(paramLong) - 1) % 30 + 1;
  }
  
  int getDaysInMonthMax()
  {
    return 30;
  }
  
  int getDaysInMonthMax(int paramInt)
  {
    if (paramInt != 13) {
      return 30;
    }
    return 6;
  }
  
  int getDaysInYearMonth(int paramInt1, int paramInt2)
  {
    if (paramInt2 != 13) {
      return 30;
    }
    if (isLeapYear(paramInt1)) {
      return 6;
    }
    return 5;
  }
  
  int getMaxMonth()
  {
    return 13;
  }
  
  int getMonthOfYear(long paramLong)
  {
    return (getDayOfYear(paramLong) - 1) / 30 + 1;
  }
  
  int getMonthOfYear(long paramLong, int paramInt)
  {
    return (int)((paramLong - getYearMillis(paramInt)) / 2592000000L) + 1;
  }
  
  long getTotalMillisByYearMonth(int paramInt1, int paramInt2)
  {
    return (paramInt2 - 1) * 2592000000L;
  }
  
  long getYearDifference(long paramLong1, long paramLong2)
  {
    int i = getYear(paramLong1);
    int j = getYear(paramLong2);
    long l1 = getYearMillis(i);
    long l2 = getYearMillis(j);
    j = i - j;
    i = j;
    if (paramLong1 - l1 < paramLong2 - l2) {
      i = j - 1;
    }
    return i;
  }
  
  boolean isLeapYear(int paramInt)
  {
    return (paramInt & 0x3) == 3;
  }
  
  long setYear(long paramLong, int paramInt)
  {
    int j = getDayOfYear(paramLong, getYear(paramLong));
    int k = getMillisOfDay(paramLong);
    int i = j;
    if (j > 365)
    {
      i = j;
      if (!isLeapYear(paramInt)) {
        i = j - 1;
      }
    }
    return getYearMonthDayMillis(paramInt, 1, i) + k;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.BasicFixedMonthChronology
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */