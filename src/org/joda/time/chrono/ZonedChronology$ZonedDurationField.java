package org.joda.time.chrono;

import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.field.BaseDurationField;

class ZonedChronology$ZonedDurationField
  extends BaseDurationField
{
  private static final long serialVersionUID = -485345310999208286L;
  final DurationField iField;
  final boolean iTimeField;
  final DateTimeZone iZone;
  
  ZonedChronology$ZonedDurationField(DurationField paramDurationField, DateTimeZone paramDateTimeZone)
  {
    super(paramDurationField.getType());
    if (!paramDurationField.isSupported()) {
      throw new IllegalArgumentException();
    }
    iField = paramDurationField;
    iTimeField = ZonedChronology.useTimeArithmetic(paramDurationField);
    iZone = paramDateTimeZone;
  }
  
  private long addOffset(long paramLong)
  {
    return iZone.convertUTCToLocal(paramLong);
  }
  
  private int getOffsetFromLocalToSubtract(long paramLong)
  {
    int i = iZone.getOffsetFromLocal(paramLong);
    if (((paramLong ^ paramLong - i) < 0L) && ((i ^ paramLong) < 0L)) {
      throw new ArithmeticException("Subtracting time zone offset caused overflow");
    }
    return i;
  }
  
  private int getOffsetToAdd(long paramLong)
  {
    int i = iZone.getOffset(paramLong);
    if (((paramLong ^ paramLong + i) < 0L) && ((i ^ paramLong) >= 0L)) {
      throw new ArithmeticException("Adding time zone offset caused overflow");
    }
    return i;
  }
  
  public long add(long paramLong, int paramInt)
  {
    int i = getOffsetToAdd(paramLong);
    paramLong = iField.add(i + paramLong, paramInt);
    if (iTimeField) {}
    for (paramInt = i;; paramInt = getOffsetFromLocalToSubtract(paramLong)) {
      return paramLong - paramInt;
    }
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    int i = getOffsetToAdd(paramLong1);
    paramLong1 = iField.add(i + paramLong1, paramLong2);
    if (iTimeField) {}
    for (;;)
    {
      return paramLong1 - i;
      i = getOffsetFromLocalToSubtract(paramLong1);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof ZonedDurationField)) {
        break;
      }
      paramObject = (ZonedDurationField)paramObject;
    } while ((iField.equals(iField)) && (iZone.equals(iZone)));
    return false;
    return false;
  }
  
  public int getDifference(long paramLong1, long paramLong2)
  {
    int j = getOffsetToAdd(paramLong2);
    DurationField localDurationField = iField;
    if (iTimeField) {}
    for (int i = j;; i = getOffsetToAdd(paramLong1)) {
      return localDurationField.getDifference(i + paramLong1, j + paramLong2);
    }
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    int j = getOffsetToAdd(paramLong2);
    DurationField localDurationField = iField;
    if (iTimeField) {}
    for (int i = j;; i = getOffsetToAdd(paramLong1)) {
      return localDurationField.getDifferenceAsLong(i + paramLong1, j + paramLong2);
    }
  }
  
  public long getMillis(int paramInt, long paramLong)
  {
    return iField.getMillis(paramInt, addOffset(paramLong));
  }
  
  public long getMillis(long paramLong1, long paramLong2)
  {
    return iField.getMillis(paramLong1, addOffset(paramLong2));
  }
  
  public long getUnitMillis()
  {
    return iField.getUnitMillis();
  }
  
  public int getValue(long paramLong1, long paramLong2)
  {
    return iField.getValue(paramLong1, addOffset(paramLong2));
  }
  
  public long getValueAsLong(long paramLong1, long paramLong2)
  {
    return iField.getValueAsLong(paramLong1, addOffset(paramLong2));
  }
  
  public int hashCode()
  {
    return iField.hashCode() ^ iZone.hashCode();
  }
  
  public boolean isPrecise()
  {
    if (iTimeField) {
      return iField.isPrecise();
    }
    return (iField.isPrecise()) && (iZone.isFixed());
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.ZonedChronology.ZonedDurationField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */