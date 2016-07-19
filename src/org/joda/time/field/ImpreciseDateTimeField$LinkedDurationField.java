package org.joda.time.field;

import org.joda.time.DurationFieldType;

final class ImpreciseDateTimeField$LinkedDurationField
  extends BaseDurationField
{
  private static final long serialVersionUID = -203813474600094134L;
  
  ImpreciseDateTimeField$LinkedDurationField(ImpreciseDateTimeField paramImpreciseDateTimeField, DurationFieldType paramDurationFieldType)
  {
    super(paramDurationFieldType);
  }
  
  public long add(long paramLong, int paramInt)
  {
    return this$0.add(paramLong, paramInt);
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    return this$0.add(paramLong1, paramLong2);
  }
  
  public int getDifference(long paramLong1, long paramLong2)
  {
    return this$0.getDifference(paramLong1, paramLong2);
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    return this$0.getDifferenceAsLong(paramLong1, paramLong2);
  }
  
  public long getMillis(int paramInt, long paramLong)
  {
    return this$0.add(paramLong, paramInt) - paramLong;
  }
  
  public long getMillis(long paramLong1, long paramLong2)
  {
    return this$0.add(paramLong2, paramLong1) - paramLong2;
  }
  
  public long getUnitMillis()
  {
    return this$0.iUnitMillis;
  }
  
  public int getValue(long paramLong1, long paramLong2)
  {
    return this$0.getDifference(paramLong2 + paramLong1, paramLong2);
  }
  
  public long getValueAsLong(long paramLong1, long paramLong2)
  {
    return this$0.getDifferenceAsLong(paramLong2 + paramLong1, paramLong2);
  }
  
  public boolean isPrecise()
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.ImpreciseDateTimeField.LinkedDurationField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */