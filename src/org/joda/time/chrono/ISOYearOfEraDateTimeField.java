package org.joda.time.chrono;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.DecoratedDateTimeField;
import org.joda.time.field.FieldUtils;

class ISOYearOfEraDateTimeField
  extends DecoratedDateTimeField
{
  static final DateTimeField INSTANCE = new ISOYearOfEraDateTimeField();
  private static final long serialVersionUID = 7037524068969447317L;
  
  private ISOYearOfEraDateTimeField()
  {
    super(GregorianChronology.getInstanceUTC().year(), DateTimeFieldType.yearOfEra());
  }
  
  private Object readResolve()
  {
    return INSTANCE;
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
    if (j < 0) {
      i = -j;
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
  
  public int getMaximumValue()
  {
    return getWrappedField().getMaximumValue();
  }
  
  public int getMinimumValue()
  {
    return 0;
  }
  
  public DurationField getRangeDurationField()
  {
    return GregorianChronology.getInstanceUTC().eras();
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
  
  public long set(long paramLong, int paramInt)
  {
    FieldUtils.verifyValueBounds(this, paramInt, 0, getMaximumValue());
    int i = paramInt;
    if (getWrappedField().get(paramLong) < 0) {
      i = -paramInt;
    }
    return super.set(paramLong, i);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.ISOYearOfEraDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */