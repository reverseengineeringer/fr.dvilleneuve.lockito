package org.joda.time.chrono;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.ImpreciseDateTimeField;

class BasicMonthOfYearDateTimeField
  extends ImpreciseDateTimeField
{
  private static final int MIN = 1;
  private static final long serialVersionUID = -8258715387168736L;
  private final BasicChronology iChronology;
  private final int iLeapMonth;
  private final int iMax;
  
  BasicMonthOfYearDateTimeField(BasicChronology paramBasicChronology, int paramInt)
  {
    super(DateTimeFieldType.monthOfYear(), paramBasicChronology.getAverageMillisPerMonth());
    iChronology = paramBasicChronology;
    iMax = iChronology.getMaxMonth();
    iLeapMonth = paramInt;
  }
  
  private Object readResolve()
  {
    return iChronology.monthOfYear();
  }
  
  public long add(long paramLong, int paramInt)
  {
    if (paramInt == 0) {
      return paramLong;
    }
    long l = iChronology.getMillisOfDay(paramLong);
    int m = iChronology.getYear(paramLong);
    int n = iChronology.getMonthOfYear(paramLong, m);
    int i = n - 1 + paramInt;
    if (i >= 0)
    {
      paramInt = m + i / iMax;
      i = i % iMax + 1;
    }
    for (;;)
    {
      int k = iChronology.getDayOfMonth(paramLong, m, n);
      m = iChronology.getDaysInYearMonth(paramInt, i);
      int j = k;
      if (k > m) {
        j = m;
      }
      return iChronology.getYearMonthDayMillis(paramInt, i, j) + l;
      j = i / iMax + m - 1;
      i = Math.abs(i) % iMax;
      paramInt = i;
      if (i == 0) {
        paramInt = iMax;
      }
      k = iMax - paramInt + 1;
      i = k;
      paramInt = j;
      if (k == 1)
      {
        paramInt = j + 1;
        i = k;
      }
    }
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    int i = (int)paramLong2;
    if (i == paramLong2) {
      return add(paramLong1, i);
    }
    long l5 = iChronology.getMillisOfDay(paramLong1);
    int k = iChronology.getYear(paramLong1);
    int i1 = iChronology.getMonthOfYear(paramLong1, k);
    long l2 = i1 - 1 + paramLong2;
    long l1;
    if (l2 >= 0L)
    {
      l1 = k + l2 / iMax;
      l2 = l2 % iMax + 1L;
    }
    while ((l1 < iChronology.getMinYear()) || (l1 > iChronology.getMaxYear()))
    {
      throw new IllegalArgumentException("Magnitude of add amount is too large: " + paramLong2);
      long l3 = k + l2 / iMax - 1L;
      j = (int)(Math.abs(l2) % iMax);
      i = j;
      if (j == 0) {
        i = iMax;
      }
      long l4 = iMax - i + 1;
      l2 = l4;
      l1 = l3;
      if (l4 == 1L)
      {
        l1 = l3 + 1L;
        l2 = l4;
      }
    }
    int m = (int)l1;
    int n = (int)l2;
    int j = iChronology.getDayOfMonth(paramLong1, k, i1);
    k = iChronology.getDaysInYearMonth(m, n);
    i = j;
    if (j > k) {
      i = k;
    }
    return iChronology.getYearMonthDayMillis(m, n, i) + l5;
  }
  
  public int[] add(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    if (paramInt2 == 0) {
      return paramArrayOfInt;
    }
    if ((paramReadablePartial.size() > 0) && (paramReadablePartial.getFieldType(0).equals(DateTimeFieldType.monthOfYear())) && (paramInt1 == 0)) {
      return set(paramReadablePartial, 0, paramArrayOfInt, (paramInt2 % 12 + (paramReadablePartial.getValue(0) - 1) + 12) % 12 + 1);
    }
    if (DateTimeUtils.isContiguous(paramReadablePartial))
    {
      long l = 0L;
      paramInt1 = 0;
      int i = paramReadablePartial.size();
      while (paramInt1 < i)
      {
        l = paramReadablePartial.getFieldType(paramInt1).getField(iChronology).set(l, paramArrayOfInt[paramInt1]);
        paramInt1 += 1;
      }
      l = add(l, paramInt2);
      return iChronology.get(paramReadablePartial, l);
    }
    return super.add(paramReadablePartial, paramInt1, paramArrayOfInt, paramInt2);
  }
  
  public long addWrapField(long paramLong, int paramInt)
  {
    return set(paramLong, FieldUtils.getWrappedValue(get(paramLong), paramInt, 1, iMax));
  }
  
  public int get(long paramLong)
  {
    return iChronology.getMonthOfYear(paramLong);
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    if (paramLong1 < paramLong2) {
      paramLong2 = -getDifference(paramLong2, paramLong1);
    }
    int i;
    int j;
    int k;
    int m;
    long l2;
    long l1;
    do
    {
      return paramLong2;
      i = iChronology.getYear(paramLong1);
      j = iChronology.getMonthOfYear(paramLong1, i);
      k = iChronology.getYear(paramLong2);
      m = iChronology.getMonthOfYear(paramLong2, k);
      l2 = (i - k) * iMax + j - m;
      int n = iChronology.getDayOfMonth(paramLong1, i, j);
      l1 = paramLong2;
      if (n == iChronology.getDaysInYearMonth(i, j))
      {
        l1 = paramLong2;
        if (iChronology.getDayOfMonth(paramLong2, k, m) > n) {
          l1 = iChronology.dayOfMonth().set(paramLong2, n);
        }
      }
      paramLong2 = l2;
    } while (paramLong1 - iChronology.getYearMonthMillis(i, j) >= l1 - iChronology.getYearMonthMillis(k, m));
    return l2 - 1L;
  }
  
  public int getLeapAmount(long paramLong)
  {
    if (isLeap(paramLong)) {
      return 1;
    }
    return 0;
  }
  
  public DurationField getLeapDurationField()
  {
    return iChronology.days();
  }
  
  public int getMaximumValue()
  {
    return iMax;
  }
  
  public int getMinimumValue()
  {
    return 1;
  }
  
  public DurationField getRangeDurationField()
  {
    return iChronology.years();
  }
  
  public boolean isLeap(long paramLong)
  {
    boolean bool2 = false;
    int i = iChronology.getYear(paramLong);
    boolean bool1 = bool2;
    if (iChronology.isLeapYear(i))
    {
      bool1 = bool2;
      if (iChronology.getMonthOfYear(paramLong, i) == iLeapMonth) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean isLenient()
  {
    return false;
  }
  
  public long remainder(long paramLong)
  {
    return paramLong - roundFloor(paramLong);
  }
  
  public long roundFloor(long paramLong)
  {
    int i = iChronology.getYear(paramLong);
    int j = iChronology.getMonthOfYear(paramLong, i);
    return iChronology.getYearMonthMillis(i, j);
  }
  
  public long set(long paramLong, int paramInt)
  {
    FieldUtils.verifyValueBounds(this, paramInt, 1, iMax);
    int m = iChronology.getYear(paramLong);
    int j = iChronology.getDayOfMonth(paramLong, m);
    int k = iChronology.getDaysInYearMonth(m, paramInt);
    int i = j;
    if (j > k) {
      i = k;
    }
    return iChronology.getYearMonthDayMillis(m, paramInt, i) + iChronology.getMillisOfDay(paramLong);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.BasicMonthOfYearDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */