package org.joda.time.field;

import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public class ScaledDurationField
  extends DecoratedDurationField
{
  private static final long serialVersionUID = -3205227092378684157L;
  private final int iScalar;
  
  public ScaledDurationField(DurationField paramDurationField, DurationFieldType paramDurationFieldType, int paramInt)
  {
    super(paramDurationField, paramDurationFieldType);
    if ((paramInt == 0) || (paramInt == 1)) {
      throw new IllegalArgumentException("The scalar must not be 0 or 1");
    }
    iScalar = paramInt;
  }
  
  public long add(long paramLong, int paramInt)
  {
    long l1 = paramInt;
    long l2 = iScalar;
    return getWrappedField().add(paramLong, l1 * l2);
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    paramLong2 = FieldUtils.safeMultiply(paramLong2, iScalar);
    return getWrappedField().add(paramLong1, paramLong2);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof ScaledDurationField)) {
        break;
      }
      paramObject = (ScaledDurationField)paramObject;
    } while ((getWrappedField().equals(((ScaledDurationField)paramObject).getWrappedField())) && (getType() == ((ScaledDurationField)paramObject).getType()) && (iScalar == iScalar));
    return false;
    return false;
  }
  
  public int getDifference(long paramLong1, long paramLong2)
  {
    return getWrappedField().getDifference(paramLong1, paramLong2) / iScalar;
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    return getWrappedField().getDifferenceAsLong(paramLong1, paramLong2) / iScalar;
  }
  
  public long getMillis(int paramInt)
  {
    long l1 = paramInt;
    long l2 = iScalar;
    return getWrappedField().getMillis(l1 * l2);
  }
  
  public long getMillis(int paramInt, long paramLong)
  {
    long l1 = paramInt;
    long l2 = iScalar;
    return getWrappedField().getMillis(l1 * l2, paramLong);
  }
  
  public long getMillis(long paramLong)
  {
    paramLong = FieldUtils.safeMultiply(paramLong, iScalar);
    return getWrappedField().getMillis(paramLong);
  }
  
  public long getMillis(long paramLong1, long paramLong2)
  {
    paramLong1 = FieldUtils.safeMultiply(paramLong1, iScalar);
    return getWrappedField().getMillis(paramLong1, paramLong2);
  }
  
  public int getScalar()
  {
    return iScalar;
  }
  
  public long getUnitMillis()
  {
    return getWrappedField().getUnitMillis() * iScalar;
  }
  
  public int getValue(long paramLong)
  {
    return getWrappedField().getValue(paramLong) / iScalar;
  }
  
  public int getValue(long paramLong1, long paramLong2)
  {
    return getWrappedField().getValue(paramLong1, paramLong2) / iScalar;
  }
  
  public long getValueAsLong(long paramLong)
  {
    return getWrappedField().getValueAsLong(paramLong) / iScalar;
  }
  
  public long getValueAsLong(long paramLong1, long paramLong2)
  {
    return getWrappedField().getValueAsLong(paramLong1, paramLong2) / iScalar;
  }
  
  public int hashCode()
  {
    long l = iScalar;
    return (int)(l >>> 32 ^ l) + getType().hashCode() + getWrappedField().hashCode();
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.ScaledDurationField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */