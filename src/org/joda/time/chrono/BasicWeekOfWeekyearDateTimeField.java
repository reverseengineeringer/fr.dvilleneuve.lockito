package org.joda.time.chrono;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.PreciseDurationDateTimeField;

final class BasicWeekOfWeekyearDateTimeField
  extends PreciseDurationDateTimeField
{
  private static final long serialVersionUID = -1587436826395135328L;
  private final BasicChronology iChronology;
  
  BasicWeekOfWeekyearDateTimeField(BasicChronology paramBasicChronology, DurationField paramDurationField)
  {
    super(DateTimeFieldType.weekOfWeekyear(), paramDurationField);
    iChronology = paramBasicChronology;
  }
  
  private Object readResolve()
  {
    return iChronology.weekOfWeekyear();
  }
  
  public int get(long paramLong)
  {
    return iChronology.getWeekOfWeekyear(paramLong);
  }
  
  public int getMaximumValue()
  {
    return 53;
  }
  
  public int getMaximumValue(long paramLong)
  {
    int i = iChronology.getWeekyear(paramLong);
    return iChronology.getWeeksInYear(i);
  }
  
  public int getMaximumValue(ReadablePartial paramReadablePartial)
  {
    if (paramReadablePartial.isSupported(DateTimeFieldType.weekyear()))
    {
      int i = paramReadablePartial.get(DateTimeFieldType.weekyear());
      return iChronology.getWeeksInYear(i);
    }
    return 53;
  }
  
  public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
  {
    int j = paramReadablePartial.size();
    int i = 0;
    while (i < j)
    {
      if (paramReadablePartial.getFieldType(i) == DateTimeFieldType.weekyear())
      {
        i = paramArrayOfInt[i];
        return iChronology.getWeeksInYear(i);
      }
      i += 1;
    }
    return 53;
  }
  
  protected int getMaximumValueForSet(long paramLong, int paramInt)
  {
    int i = 52;
    if (paramInt > 52) {
      i = getMaximumValue(paramLong);
    }
    return i;
  }
  
  public int getMinimumValue()
  {
    return 1;
  }
  
  public DurationField getRangeDurationField()
  {
    return iChronology.weekyears();
  }
  
  public long remainder(long paramLong)
  {
    return super.remainder(259200000L + paramLong);
  }
  
  public long roundCeiling(long paramLong)
  {
    return super.roundCeiling(paramLong + 259200000L) - 259200000L;
  }
  
  public long roundFloor(long paramLong)
  {
    return super.roundFloor(paramLong + 259200000L) - 259200000L;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.BasicWeekOfWeekyearDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */