package org.joda.time.field;

import org.joda.time.DurationFieldType;

public class PreciseDurationField
  extends BaseDurationField
{
  private static final long serialVersionUID = -8346152187724495365L;
  private final long iUnitMillis;
  
  public PreciseDurationField(DurationFieldType paramDurationFieldType, long paramLong)
  {
    super(paramDurationFieldType);
    iUnitMillis = paramLong;
  }
  
  public long add(long paramLong, int paramInt)
  {
    return FieldUtils.safeAdd(paramLong, paramInt * iUnitMillis);
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    return FieldUtils.safeAdd(paramLong1, FieldUtils.safeMultiply(paramLong2, iUnitMillis));
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof PreciseDurationField)) {
        break;
      }
      paramObject = (PreciseDurationField)paramObject;
    } while ((getType() == ((PreciseDurationField)paramObject).getType()) && (iUnitMillis == iUnitMillis));
    return false;
    return false;
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    return FieldUtils.safeSubtract(paramLong1, paramLong2) / iUnitMillis;
  }
  
  public long getMillis(int paramInt, long paramLong)
  {
    return paramInt * iUnitMillis;
  }
  
  public long getMillis(long paramLong1, long paramLong2)
  {
    return FieldUtils.safeMultiply(paramLong1, iUnitMillis);
  }
  
  public final long getUnitMillis()
  {
    return iUnitMillis;
  }
  
  public long getValueAsLong(long paramLong1, long paramLong2)
  {
    return paramLong1 / iUnitMillis;
  }
  
  public int hashCode()
  {
    long l = iUnitMillis;
    return (int)(l >>> 32 ^ l) + getType().hashCode();
  }
  
  public final boolean isPrecise()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.PreciseDurationField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */