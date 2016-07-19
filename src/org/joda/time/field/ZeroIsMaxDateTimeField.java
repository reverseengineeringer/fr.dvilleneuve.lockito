package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;

public final class ZeroIsMaxDateTimeField
  extends DecoratedDateTimeField
{
  private static final long serialVersionUID = 961749798233026866L;
  
  public ZeroIsMaxDateTimeField(DateTimeField paramDateTimeField, DateTimeFieldType paramDateTimeFieldType)
  {
    super(paramDateTimeField, paramDateTimeFieldType);
    if (paramDateTimeField.getMinimumValue() != 0) {
      throw new IllegalArgumentException("Wrapped field's minumum value must be zero");
    }
  }
  
  public long add(long paramLong, int paramInt)
  {
    return getWrappedField().add(paramLong, paramInt);
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    return getWrappedField().add(paramLong1, paramLong2);
  }
  
  public long addWrapField(long paramLong, int paramInt)
  {
    return getWrappedField().addWrapField(paramLong, paramInt);
  }
  
  public int[] addWrapField(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    return getWrappedField().addWrapField(paramReadablePartial, paramInt1, paramArrayOfInt, paramInt2);
  }
  
  public int get(long paramLong)
  {
    int j = getWrappedField().get(paramLong);
    int i = j;
    if (j == 0) {
      i = getMaximumValue();
    }
    return i;
  }
  
  public int getDifference(long paramLong1, long paramLong2)
  {
    return getWrappedField().getDifference(paramLong1, paramLong2);
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    return getWrappedField().getDifferenceAsLong(paramLong1, paramLong2);
  }
  
  public int getLeapAmount(long paramLong)
  {
    return getWrappedField().getLeapAmount(paramLong);
  }
  
  public DurationField getLeapDurationField()
  {
    return getWrappedField().getLeapDurationField();
  }
  
  public int getMaximumValue()
  {
    return getWrappedField().getMaximumValue() + 1;
  }
  
  public int getMaximumValue(long paramLong)
  {
    return getWrappedField().getMaximumValue(paramLong) + 1;
  }
  
  public int getMaximumValue(ReadablePartial paramReadablePartial)
  {
    return getWrappedField().getMaximumValue(paramReadablePartial) + 1;
  }
  
  public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
  {
    return getWrappedField().getMaximumValue(paramReadablePartial, paramArrayOfInt) + 1;
  }
  
  public int getMinimumValue()
  {
    return 1;
  }
  
  public int getMinimumValue(long paramLong)
  {
    return 1;
  }
  
  public int getMinimumValue(ReadablePartial paramReadablePartial)
  {
    return 1;
  }
  
  public int getMinimumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
  {
    return 1;
  }
  
  public boolean isLeap(long paramLong)
  {
    return getWrappedField().isLeap(paramLong);
  }
  
  public long remainder(long paramLong)
  {
    return getWrappedField().remainder(paramLong);
  }
  
  public long roundCeiling(long paramLong)
  {
    return getWrappedField().roundCeiling(paramLong);
  }
  
  public long roundFloor(long paramLong)
  {
    return getWrappedField().roundFloor(paramLong);
  }
  
  public long roundHalfCeiling(long paramLong)
  {
    return getWrappedField().roundHalfCeiling(paramLong);
  }
  
  public long roundHalfEven(long paramLong)
  {
    return getWrappedField().roundHalfEven(paramLong);
  }
  
  public long roundHalfFloor(long paramLong)
  {
    return getWrappedField().roundHalfFloor(paramLong);
  }
  
  public long set(long paramLong, int paramInt)
  {
    int j = getMaximumValue();
    FieldUtils.verifyValueBounds(this, paramInt, 1, j);
    int i = paramInt;
    if (paramInt == j) {
      i = 0;
    }
    return getWrappedField().set(paramLong, i);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.ZeroIsMaxDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */