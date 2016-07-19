package org.joda.time.chrono;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.PreciseDurationDateTimeField;

final class BasicDayOfMonthDateTimeField
  extends PreciseDurationDateTimeField
{
  private static final long serialVersionUID = -4677223814028011723L;
  private final BasicChronology iChronology;
  
  BasicDayOfMonthDateTimeField(BasicChronology paramBasicChronology, DurationField paramDurationField)
  {
    super(DateTimeFieldType.dayOfMonth(), paramDurationField);
    iChronology = paramBasicChronology;
  }
  
  private Object readResolve()
  {
    return iChronology.dayOfMonth();
  }
  
  public int get(long paramLong)
  {
    return iChronology.getDayOfMonth(paramLong);
  }
  
  public int getMaximumValue()
  {
    return iChronology.getDaysInMonthMax();
  }
  
  public int getMaximumValue(long paramLong)
  {
    return iChronology.getDaysInMonthMax(paramLong);
  }
  
  public int getMaximumValue(ReadablePartial paramReadablePartial)
  {
    if (paramReadablePartial.isSupported(DateTimeFieldType.monthOfYear()))
    {
      int i = paramReadablePartial.get(DateTimeFieldType.monthOfYear());
      if (paramReadablePartial.isSupported(DateTimeFieldType.year()))
      {
        int j = paramReadablePartial.get(DateTimeFieldType.year());
        return iChronology.getDaysInYearMonth(j, i);
      }
      return iChronology.getDaysInMonthMax(i);
    }
    return getMaximumValue();
  }
  
  public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
  {
    int j = paramReadablePartial.size();
    int i = 0;
    while (i < j)
    {
      if (paramReadablePartial.getFieldType(i) == DateTimeFieldType.monthOfYear())
      {
        int k = paramArrayOfInt[i];
        i = 0;
        while (i < j)
        {
          if (paramReadablePartial.getFieldType(i) == DateTimeFieldType.year())
          {
            i = paramArrayOfInt[i];
            return iChronology.getDaysInYearMonth(i, k);
          }
          i += 1;
        }
        return iChronology.getDaysInMonthMax(k);
      }
      i += 1;
    }
    return getMaximumValue();
  }
  
  protected int getMaximumValueForSet(long paramLong, int paramInt)
  {
    return iChronology.getDaysInMonthMaxForSet(paramLong, paramInt);
  }
  
  public int getMinimumValue()
  {
    return 1;
  }
  
  public DurationField getRangeDurationField()
  {
    return iChronology.months();
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.BasicDayOfMonthDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */