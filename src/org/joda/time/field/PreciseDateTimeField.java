package org.joda.time.field;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

public class PreciseDateTimeField
  extends PreciseDurationDateTimeField
{
  private static final long serialVersionUID = -5586801265774496376L;
  private final int iRange;
  private final DurationField iRangeField;
  
  public PreciseDateTimeField(DateTimeFieldType paramDateTimeFieldType, DurationField paramDurationField1, DurationField paramDurationField2)
  {
    super(paramDateTimeFieldType, paramDurationField1);
    if (!paramDurationField2.isPrecise()) {
      throw new IllegalArgumentException("Range duration field must be precise");
    }
    iRange = ((int)(paramDurationField2.getUnitMillis() / getUnitMillis()));
    if (iRange < 2) {
      throw new IllegalArgumentException("The effective range must be at least 2");
    }
    iRangeField = paramDurationField2;
  }
  
  public long addWrapField(long paramLong, int paramInt)
  {
    int i = get(paramLong);
    return (FieldUtils.getWrappedValue(i, paramInt, getMinimumValue(), getMaximumValue()) - i) * getUnitMillis() + paramLong;
  }
  
  public int get(long paramLong)
  {
    if (paramLong >= 0L) {
      return (int)(paramLong / getUnitMillis() % iRange);
    }
    return iRange - 1 + (int)((1L + paramLong) / getUnitMillis() % iRange);
  }
  
  public int getMaximumValue()
  {
    return iRange - 1;
  }
  
  public int getRange()
  {
    return iRange;
  }
  
  public DurationField getRangeDurationField()
  {
    return iRangeField;
  }
  
  public long set(long paramLong, int paramInt)
  {
    FieldUtils.verifyValueBounds(this, paramInt, getMinimumValue(), getMaximumValue());
    return (paramInt - get(paramLong)) * iUnitMillis + paramLong;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.PreciseDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */