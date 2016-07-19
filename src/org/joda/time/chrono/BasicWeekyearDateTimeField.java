package org.joda.time.chrono;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.ImpreciseDateTimeField;

final class BasicWeekyearDateTimeField
  extends ImpreciseDateTimeField
{
  private static final long WEEK_53 = 31449600000L;
  private static final long serialVersionUID = 6215066916806820644L;
  private final BasicChronology iChronology;
  
  BasicWeekyearDateTimeField(BasicChronology paramBasicChronology)
  {
    super(DateTimeFieldType.weekyear(), paramBasicChronology.getAverageMillisPerYear());
    iChronology = paramBasicChronology;
  }
  
  private Object readResolve()
  {
    return iChronology.weekyear();
  }
  
  public long add(long paramLong, int paramInt)
  {
    if (paramInt == 0) {
      return paramLong;
    }
    return set(paramLong, get(paramLong) + paramInt);
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    return add(paramLong1, FieldUtils.safeToInt(paramLong2));
  }
  
  public long addWrapField(long paramLong, int paramInt)
  {
    return add(paramLong, paramInt);
  }
  
  public int get(long paramLong)
  {
    return iChronology.getWeekyear(paramLong);
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    if (paramLong1 < paramLong2) {
      return -getDifference(paramLong2, paramLong1);
    }
    int i = get(paramLong1);
    int j = get(paramLong2);
    long l = remainder(paramLong1);
    paramLong2 = remainder(paramLong2);
    paramLong1 = paramLong2;
    if (paramLong2 >= 31449600000L)
    {
      paramLong1 = paramLong2;
      if (iChronology.getWeeksInYear(i) <= 52) {
        paramLong1 = paramLong2 - 604800000L;
      }
    }
    j = i - j;
    i = j;
    if (l < paramLong1) {
      i = j - 1;
    }
    return i;
  }
  
  public int getLeapAmount(long paramLong)
  {
    return iChronology.getWeeksInYear(iChronology.getWeekyear(paramLong)) - 52;
  }
  
  public DurationField getLeapDurationField()
  {
    return iChronology.weeks();
  }
  
  public int getMaximumValue()
  {
    return iChronology.getMaxYear();
  }
  
  public int getMinimumValue()
  {
    return iChronology.getMinYear();
  }
  
  public DurationField getRangeDurationField()
  {
    return null;
  }
  
  public boolean isLeap(long paramLong)
  {
    return iChronology.getWeeksInYear(iChronology.getWeekyear(paramLong)) > 52;
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
    long l = iChronology.weekOfWeekyear().roundFloor(paramLong);
    int i = iChronology.getWeekOfWeekyear(l);
    paramLong = l;
    if (i > 1) {
      paramLong = l - 604800000L * (i - 1);
    }
    return paramLong;
  }
  
  public long set(long paramLong, int paramInt)
  {
    FieldUtils.verifyValueBounds(this, Math.abs(paramInt), iChronology.getMinYear(), iChronology.getMaxYear());
    int i = get(paramLong);
    if (i == paramInt) {
      return paramLong;
    }
    int m = iChronology.getDayOfWeek(paramLong);
    i = iChronology.getWeeksInYear(i);
    int j = iChronology.getWeeksInYear(paramInt);
    long l;
    if (j < i)
    {
      i = j;
      int k = iChronology.getWeekOfWeekyear(paramLong);
      j = k;
      if (k > i) {
        j = i;
      }
      l = iChronology.setYear(paramLong, paramInt);
      i = get(l);
      if (i >= paramInt) {
        break label174;
      }
      paramLong = l + 604800000L;
    }
    for (;;)
    {
      l = j - iChronology.getWeekOfWeekyear(paramLong);
      return iChronology.dayOfWeek().set(paramLong + l * 604800000L, m);
      break;
      label174:
      paramLong = l;
      if (i > paramInt) {
        paramLong = l - 604800000L;
      }
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.BasicWeekyearDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */