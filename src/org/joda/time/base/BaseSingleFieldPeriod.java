package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.MutablePeriod;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.FieldUtils;

public abstract class BaseSingleFieldPeriod
  implements ReadablePeriod, Comparable<BaseSingleFieldPeriod>, Serializable
{
  private static final long START_1972 = 63072000000L;
  private static final long serialVersionUID = 9386874258972L;
  private volatile int iPeriod;
  
  protected BaseSingleFieldPeriod(int paramInt)
  {
    iPeriod = paramInt;
  }
  
  protected static int between(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2, DurationFieldType paramDurationFieldType)
  {
    if ((paramReadableInstant1 == null) || (paramReadableInstant2 == null)) {
      throw new IllegalArgumentException("ReadableInstant objects must not be null");
    }
    return paramDurationFieldType.getField(DateTimeUtils.getInstantChronology(paramReadableInstant1)).getDifference(paramReadableInstant2.getMillis(), paramReadableInstant1.getMillis());
  }
  
  protected static int between(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2, ReadablePeriod paramReadablePeriod)
  {
    if ((paramReadablePartial1 == null) || (paramReadablePartial2 == null)) {
      throw new IllegalArgumentException("ReadablePartial objects must not be null");
    }
    if (paramReadablePartial1.size() != paramReadablePartial2.size()) {
      throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
    }
    int i = 0;
    int j = paramReadablePartial1.size();
    while (i < j)
    {
      if (paramReadablePartial1.getFieldType(i) != paramReadablePartial2.getFieldType(i)) {
        throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
      }
      i += 1;
    }
    if (!DateTimeUtils.isContiguous(paramReadablePartial1)) {
      throw new IllegalArgumentException("ReadablePartial objects must be contiguous");
    }
    Chronology localChronology = DateTimeUtils.getChronology(paramReadablePartial1.getChronology()).withUTC();
    return localChronology.get(paramReadablePeriod, localChronology.set(paramReadablePartial1, 63072000000L), localChronology.set(paramReadablePartial2, 63072000000L))[0];
  }
  
  protected static int standardPeriodIn(ReadablePeriod paramReadablePeriod, long paramLong)
  {
    if (paramReadablePeriod == null) {
      return 0;
    }
    ISOChronology localISOChronology = ISOChronology.getInstanceUTC();
    long l1 = 0L;
    int i = 0;
    while (i < paramReadablePeriod.size())
    {
      int j = paramReadablePeriod.getValue(i);
      long l2 = l1;
      if (j != 0)
      {
        DurationField localDurationField = paramReadablePeriod.getFieldType(i).getField(localISOChronology);
        if (!localDurationField.isPrecise()) {
          throw new IllegalArgumentException("Cannot convert period to duration as " + localDurationField.getName() + " is not precise in the period " + paramReadablePeriod);
        }
        l2 = FieldUtils.safeAdd(l1, FieldUtils.safeMultiply(localDurationField.getUnitMillis(), j));
      }
      i += 1;
      l1 = l2;
    }
    return FieldUtils.safeToInt(l1 / paramLong);
  }
  
  public int compareTo(BaseSingleFieldPeriod paramBaseSingleFieldPeriod)
  {
    if (paramBaseSingleFieldPeriod.getClass() != getClass()) {
      throw new ClassCastException(getClass() + " cannot be compared to " + paramBaseSingleFieldPeriod.getClass());
    }
    int i = paramBaseSingleFieldPeriod.getValue();
    int j = getValue();
    if (j > i) {
      return 1;
    }
    if (j < i) {
      return -1;
    }
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof ReadablePeriod)) {
        return false;
      }
      paramObject = (ReadablePeriod)paramObject;
    } while ((((ReadablePeriod)paramObject).getPeriodType() == getPeriodType()) && (((ReadablePeriod)paramObject).getValue(0) == getValue()));
    return false;
  }
  
  public int get(DurationFieldType paramDurationFieldType)
  {
    if (paramDurationFieldType == getFieldType()) {
      return getValue();
    }
    return 0;
  }
  
  public abstract DurationFieldType getFieldType();
  
  public DurationFieldType getFieldType(int paramInt)
  {
    if (paramInt != 0) {
      throw new IndexOutOfBoundsException(String.valueOf(paramInt));
    }
    return getFieldType();
  }
  
  public abstract PeriodType getPeriodType();
  
  protected int getValue()
  {
    return iPeriod;
  }
  
  public int getValue(int paramInt)
  {
    if (paramInt != 0) {
      throw new IndexOutOfBoundsException(String.valueOf(paramInt));
    }
    return getValue();
  }
  
  public int hashCode()
  {
    return (getValue() + 459) * 27 + getFieldType().hashCode();
  }
  
  public boolean isSupported(DurationFieldType paramDurationFieldType)
  {
    return paramDurationFieldType == getFieldType();
  }
  
  protected void setValue(int paramInt)
  {
    iPeriod = paramInt;
  }
  
  public int size()
  {
    return 1;
  }
  
  public MutablePeriod toMutablePeriod()
  {
    MutablePeriod localMutablePeriod = new MutablePeriod();
    localMutablePeriod.add(this);
    return localMutablePeriod;
  }
  
  public Period toPeriod()
  {
    return Period.ZERO.withFields(this);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.base.BaseSingleFieldPeriod
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */