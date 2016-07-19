package org.joda.time.field;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public abstract class ImpreciseDateTimeField
  extends BaseDateTimeField
{
  private static final long serialVersionUID = 7190739608550251860L;
  private final DurationField iDurationField;
  final long iUnitMillis;
  
  public ImpreciseDateTimeField(DateTimeFieldType paramDateTimeFieldType, long paramLong)
  {
    super(paramDateTimeFieldType);
    iUnitMillis = paramLong;
    iDurationField = new LinkedDurationField(paramDateTimeFieldType.getDurationType());
  }
  
  public abstract long add(long paramLong, int paramInt);
  
  public abstract long add(long paramLong1, long paramLong2);
  
  public abstract int get(long paramLong);
  
  public int getDifference(long paramLong1, long paramLong2)
  {
    return FieldUtils.safeToInt(getDifferenceAsLong(paramLong1, paramLong2));
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    long l2;
    if (paramLong1 < paramLong2) {
      l2 = -getDifferenceAsLong(paramLong2, paramLong1);
    }
    long l1;
    do
    {
      return l2;
      l1 = (paramLong1 - paramLong2) / iUnitMillis;
      if (add(paramLong2, l1) < paramLong1)
      {
        do
        {
          l2 = l1 + 1L;
          l1 = l2;
        } while (add(paramLong2, l2) <= paramLong1);
        return l2 - 1L;
      }
      l2 = l1;
    } while (add(paramLong2, l1) <= paramLong1);
    do
    {
      l2 = l1 - 1L;
      l1 = l2;
    } while (add(paramLong2, l2) > paramLong1);
    return l2;
  }
  
  public final DurationField getDurationField()
  {
    return iDurationField;
  }
  
  protected final long getDurationUnitMillis()
  {
    return iUnitMillis;
  }
  
  public abstract DurationField getRangeDurationField();
  
  public abstract long roundFloor(long paramLong);
  
  public abstract long set(long paramLong, int paramInt);
  
  private final class LinkedDurationField
    extends BaseDurationField
  {
    private static final long serialVersionUID = -203813474600094134L;
    
    LinkedDurationField(DurationFieldType paramDurationFieldType)
    {
      super();
    }
    
    public long add(long paramLong, int paramInt)
    {
      return ImpreciseDateTimeField.this.add(paramLong, paramInt);
    }
    
    public long add(long paramLong1, long paramLong2)
    {
      return ImpreciseDateTimeField.this.add(paramLong1, paramLong2);
    }
    
    public int getDifference(long paramLong1, long paramLong2)
    {
      return ImpreciseDateTimeField.this.getDifference(paramLong1, paramLong2);
    }
    
    public long getDifferenceAsLong(long paramLong1, long paramLong2)
    {
      return ImpreciseDateTimeField.this.getDifferenceAsLong(paramLong1, paramLong2);
    }
    
    public long getMillis(int paramInt, long paramLong)
    {
      return ImpreciseDateTimeField.this.add(paramLong, paramInt) - paramLong;
    }
    
    public long getMillis(long paramLong1, long paramLong2)
    {
      return ImpreciseDateTimeField.this.add(paramLong2, paramLong1) - paramLong2;
    }
    
    public long getUnitMillis()
    {
      return iUnitMillis;
    }
    
    public int getValue(long paramLong1, long paramLong2)
    {
      return ImpreciseDateTimeField.this.getDifference(paramLong2 + paramLong1, paramLong2);
    }
    
    public long getValueAsLong(long paramLong1, long paramLong2)
    {
      return ImpreciseDateTimeField.this.getDifferenceAsLong(paramLong2 + paramLong1, paramLong2);
    }
    
    public boolean isPrecise()
    {
      return false;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.ImpreciseDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */