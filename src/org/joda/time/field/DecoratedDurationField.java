package org.joda.time.field;

import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public class DecoratedDurationField
  extends BaseDurationField
{
  private static final long serialVersionUID = 8019982251647420015L;
  private final DurationField iField;
  
  public DecoratedDurationField(DurationField paramDurationField, DurationFieldType paramDurationFieldType)
  {
    super(paramDurationFieldType);
    if (paramDurationField == null) {
      throw new IllegalArgumentException("The field must not be null");
    }
    if (!paramDurationField.isSupported()) {
      throw new IllegalArgumentException("The field must be supported");
    }
    iField = paramDurationField;
  }
  
  public long add(long paramLong, int paramInt)
  {
    return iField.add(paramLong, paramInt);
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    return iField.add(paramLong1, paramLong2);
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    return iField.getDifferenceAsLong(paramLong1, paramLong2);
  }
  
  public long getMillis(int paramInt, long paramLong)
  {
    return iField.getMillis(paramInt, paramLong);
  }
  
  public long getMillis(long paramLong1, long paramLong2)
  {
    return iField.getMillis(paramLong1, paramLong2);
  }
  
  public long getUnitMillis()
  {
    return iField.getUnitMillis();
  }
  
  public long getValueAsLong(long paramLong1, long paramLong2)
  {
    return iField.getValueAsLong(paramLong1, paramLong2);
  }
  
  public final DurationField getWrappedField()
  {
    return iField;
  }
  
  public boolean isPrecise()
  {
    return iField.isPrecise();
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.DecoratedDurationField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */