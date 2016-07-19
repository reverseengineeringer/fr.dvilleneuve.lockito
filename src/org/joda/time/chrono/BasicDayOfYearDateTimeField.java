package org.joda.time.chrono;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.PreciseDurationDateTimeField;

final class BasicDayOfYearDateTimeField
  extends PreciseDurationDateTimeField
{
  private static final long serialVersionUID = -6821236822336841037L;
  private final BasicChronology iChronology;
  
  BasicDayOfYearDateTimeField(BasicChronology paramBasicChronology, DurationField paramDurationField)
  {
    super(DateTimeFieldType.dayOfYear(), paramDurationField);
    iChronology = paramBasicChronology;
  }
  
  private Object readResolve()
  {
    return iChronology.dayOfYear();
  }
  
  public int get(long paramLong)
  {
    return iChronology.getDayOfYear(paramLong);
  }
  
  public int getMaximumValue()
  {
    return iChronology.getDaysInYearMax();
  }
  
  public int getMaximumValue(long paramLong)
  {
    int i = iChronology.getYear(paramLong);
    return iChronology.getDaysInYear(i);
  }
  
  public int getMaximumValue(ReadablePartial paramReadablePartial)
  {
    if (paramReadablePartial.isSupported(DateTimeFieldType.year()))
    {
      int i = paramReadablePartial.get(DateTimeFieldType.year());
      return iChronology.getDaysInYear(i);
    }
    return iChronology.getDaysInYearMax();
  }
  
  public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
  {
    int j = paramReadablePartial.size();
    int i = 0;
    while (i < j)
    {
      if (paramReadablePartial.getFieldType(i) == DateTimeFieldType.year())
      {
        i = paramArrayOfInt[i];
        return iChronology.getDaysInYear(i);
      }
      i += 1;
    }
    return iChronology.getDaysInYearMax();
  }
  
  protected int getMaximumValueForSet(long paramLong, int paramInt)
  {
    int i = iChronology.getDaysInYearMax() - 1;
    if ((paramInt > i) || (paramInt < 1)) {
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
    return iChronology.years();
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.BasicDayOfYearDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */