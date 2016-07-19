package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.Duration;
import org.joda.time.DurationFieldType;
import org.joda.time.MutablePeriod;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.PeriodConverter;
import org.joda.time.field.FieldUtils;

public abstract class BasePeriod
  extends AbstractPeriod
  implements ReadablePeriod, Serializable
{
  private static final ReadablePeriod DUMMY_PERIOD = new AbstractPeriod()
  {
    public PeriodType getPeriodType()
    {
      return PeriodType.time();
    }
    
    public int getValue(int paramAnonymousInt)
    {
      return 0;
    }
  };
  private static final long serialVersionUID = -2110953284060001145L;
  private final PeriodType iType;
  private final int[] iValues;
  
  protected BasePeriod(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, PeriodType paramPeriodType)
  {
    iType = checkPeriodType(paramPeriodType);
    iValues = setPeriodInternal(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8);
  }
  
  protected BasePeriod(long paramLong)
  {
    iType = PeriodType.standard();
    int[] arrayOfInt = ISOChronology.getInstanceUTC().get(DUMMY_PERIOD, paramLong);
    iValues = new int[8];
    System.arraycopy(arrayOfInt, 0, iValues, 4, 4);
  }
  
  protected BasePeriod(long paramLong1, long paramLong2, PeriodType paramPeriodType, Chronology paramChronology)
  {
    paramPeriodType = checkPeriodType(paramPeriodType);
    paramChronology = DateTimeUtils.getChronology(paramChronology);
    iType = paramPeriodType;
    iValues = paramChronology.get(this, paramLong1, paramLong2);
  }
  
  protected BasePeriod(long paramLong, PeriodType paramPeriodType, Chronology paramChronology)
  {
    paramPeriodType = checkPeriodType(paramPeriodType);
    paramChronology = DateTimeUtils.getChronology(paramChronology);
    iType = paramPeriodType;
    iValues = paramChronology.get(this, paramLong);
  }
  
  protected BasePeriod(Object paramObject, PeriodType paramPeriodType, Chronology paramChronology)
  {
    PeriodConverter localPeriodConverter = ConverterManager.getInstance().getPeriodConverter(paramObject);
    PeriodType localPeriodType = paramPeriodType;
    if (paramPeriodType == null) {
      localPeriodType = localPeriodConverter.getPeriodType(paramObject);
    }
    paramPeriodType = checkPeriodType(localPeriodType);
    iType = paramPeriodType;
    if ((this instanceof ReadWritablePeriod))
    {
      iValues = new int[size()];
      paramPeriodType = DateTimeUtils.getChronology(paramChronology);
      localPeriodConverter.setInto((ReadWritablePeriod)this, paramObject, paramPeriodType);
      return;
    }
    iValues = new MutablePeriod(paramObject, paramPeriodType, paramChronology).getValues();
  }
  
  protected BasePeriod(ReadableDuration paramReadableDuration, ReadableInstant paramReadableInstant, PeriodType paramPeriodType)
  {
    paramPeriodType = checkPeriodType(paramPeriodType);
    long l2 = DateTimeUtils.getDurationMillis(paramReadableDuration);
    long l1 = DateTimeUtils.getInstantMillis(paramReadableInstant);
    l2 = FieldUtils.safeSubtract(l1, l2);
    paramReadableDuration = DateTimeUtils.getInstantChronology(paramReadableInstant);
    iType = paramPeriodType;
    iValues = paramReadableDuration.get(this, l2, l1);
  }
  
  protected BasePeriod(ReadableInstant paramReadableInstant, ReadableDuration paramReadableDuration, PeriodType paramPeriodType)
  {
    paramPeriodType = checkPeriodType(paramPeriodType);
    long l1 = DateTimeUtils.getInstantMillis(paramReadableInstant);
    long l2 = FieldUtils.safeAdd(l1, DateTimeUtils.getDurationMillis(paramReadableDuration));
    paramReadableInstant = DateTimeUtils.getInstantChronology(paramReadableInstant);
    iType = paramPeriodType;
    iValues = paramReadableInstant.get(this, l1, l2);
  }
  
  protected BasePeriod(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2, PeriodType paramPeriodType)
  {
    paramPeriodType = checkPeriodType(paramPeriodType);
    if ((paramReadableInstant1 == null) && (paramReadableInstant2 == null))
    {
      iType = paramPeriodType;
      iValues = new int[size()];
      return;
    }
    long l1 = DateTimeUtils.getInstantMillis(paramReadableInstant1);
    long l2 = DateTimeUtils.getInstantMillis(paramReadableInstant2);
    paramReadableInstant1 = DateTimeUtils.getIntervalChronology(paramReadableInstant1, paramReadableInstant2);
    iType = paramPeriodType;
    iValues = paramReadableInstant1.get(this, l1, l2);
  }
  
  protected BasePeriod(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2, PeriodType paramPeriodType)
  {
    if ((paramReadablePartial1 == null) || (paramReadablePartial2 == null)) {
      throw new IllegalArgumentException("ReadablePartial objects must not be null");
    }
    if (((paramReadablePartial1 instanceof BaseLocal)) && ((paramReadablePartial2 instanceof BaseLocal)) && (paramReadablePartial1.getClass() == paramReadablePartial2.getClass()))
    {
      paramPeriodType = checkPeriodType(paramPeriodType);
      long l1 = ((BaseLocal)paramReadablePartial1).getLocalMillis();
      long l2 = ((BaseLocal)paramReadablePartial2).getLocalMillis();
      paramReadablePartial1 = DateTimeUtils.getChronology(paramReadablePartial1.getChronology());
      iType = paramPeriodType;
      iValues = paramReadablePartial1.get(this, l1, l2);
      return;
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
    iType = checkPeriodType(paramPeriodType);
    paramPeriodType = DateTimeUtils.getChronology(paramReadablePartial1.getChronology()).withUTC();
    iValues = paramPeriodType.get(this, paramPeriodType.set(paramReadablePartial1, 0L), paramPeriodType.set(paramReadablePartial2, 0L));
  }
  
  protected BasePeriod(int[] paramArrayOfInt, PeriodType paramPeriodType)
  {
    iType = paramPeriodType;
    iValues = paramArrayOfInt;
  }
  
  private void checkAndUpdate(DurationFieldType paramDurationFieldType, int[] paramArrayOfInt, int paramInt)
  {
    int i = indexOf(paramDurationFieldType);
    if (i == -1)
    {
      if (paramInt != 0) {
        throw new IllegalArgumentException("Period does not support field '" + paramDurationFieldType.getName() + "'");
      }
    }
    else {
      paramArrayOfInt[i] = paramInt;
    }
  }
  
  private void setPeriodInternal(ReadablePeriod paramReadablePeriod)
  {
    int[] arrayOfInt = new int[size()];
    int i = 0;
    int j = paramReadablePeriod.size();
    while (i < j)
    {
      checkAndUpdate(paramReadablePeriod.getFieldType(i), arrayOfInt, paramReadablePeriod.getValue(i));
      i += 1;
    }
    setValues(arrayOfInt);
  }
  
  private int[] setPeriodInternal(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    int[] arrayOfInt = new int[size()];
    checkAndUpdate(DurationFieldType.years(), arrayOfInt, paramInt1);
    checkAndUpdate(DurationFieldType.months(), arrayOfInt, paramInt2);
    checkAndUpdate(DurationFieldType.weeks(), arrayOfInt, paramInt3);
    checkAndUpdate(DurationFieldType.days(), arrayOfInt, paramInt4);
    checkAndUpdate(DurationFieldType.hours(), arrayOfInt, paramInt5);
    checkAndUpdate(DurationFieldType.minutes(), arrayOfInt, paramInt6);
    checkAndUpdate(DurationFieldType.seconds(), arrayOfInt, paramInt7);
    checkAndUpdate(DurationFieldType.millis(), arrayOfInt, paramInt8);
    return arrayOfInt;
  }
  
  protected void addField(DurationFieldType paramDurationFieldType, int paramInt)
  {
    addFieldInto(iValues, paramDurationFieldType, paramInt);
  }
  
  protected void addFieldInto(int[] paramArrayOfInt, DurationFieldType paramDurationFieldType, int paramInt)
  {
    int i = indexOf(paramDurationFieldType);
    if (i == -1)
    {
      if ((paramInt != 0) || (paramDurationFieldType == null)) {
        throw new IllegalArgumentException("Period does not support field '" + paramDurationFieldType + "'");
      }
    }
    else {
      paramArrayOfInt[i] = FieldUtils.safeAdd(paramArrayOfInt[i], paramInt);
    }
  }
  
  protected void addPeriod(ReadablePeriod paramReadablePeriod)
  {
    if (paramReadablePeriod != null) {
      setValues(addPeriodInto(getValues(), paramReadablePeriod));
    }
  }
  
  protected int[] addPeriodInto(int[] paramArrayOfInt, ReadablePeriod paramReadablePeriod)
  {
    int i = 0;
    int j = paramReadablePeriod.size();
    while (i < j)
    {
      DurationFieldType localDurationFieldType = paramReadablePeriod.getFieldType(i);
      int k = paramReadablePeriod.getValue(i);
      if (k != 0)
      {
        int m = indexOf(localDurationFieldType);
        if (m == -1) {
          throw new IllegalArgumentException("Period does not support field '" + localDurationFieldType.getName() + "'");
        }
        paramArrayOfInt[m] = FieldUtils.safeAdd(getValue(m), k);
      }
      i += 1;
    }
    return paramArrayOfInt;
  }
  
  protected PeriodType checkPeriodType(PeriodType paramPeriodType)
  {
    return DateTimeUtils.getPeriodType(paramPeriodType);
  }
  
  public PeriodType getPeriodType()
  {
    return iType;
  }
  
  public int getValue(int paramInt)
  {
    return iValues[paramInt];
  }
  
  protected void mergePeriod(ReadablePeriod paramReadablePeriod)
  {
    if (paramReadablePeriod != null) {
      setValues(mergePeriodInto(getValues(), paramReadablePeriod));
    }
  }
  
  protected int[] mergePeriodInto(int[] paramArrayOfInt, ReadablePeriod paramReadablePeriod)
  {
    int i = 0;
    int j = paramReadablePeriod.size();
    while (i < j)
    {
      checkAndUpdate(paramReadablePeriod.getFieldType(i), paramArrayOfInt, paramReadablePeriod.getValue(i));
      i += 1;
    }
    return paramArrayOfInt;
  }
  
  protected void setField(DurationFieldType paramDurationFieldType, int paramInt)
  {
    setFieldInto(iValues, paramDurationFieldType, paramInt);
  }
  
  protected void setFieldInto(int[] paramArrayOfInt, DurationFieldType paramDurationFieldType, int paramInt)
  {
    int i = indexOf(paramDurationFieldType);
    if (i == -1)
    {
      if ((paramInt != 0) || (paramDurationFieldType == null)) {
        throw new IllegalArgumentException("Period does not support field '" + paramDurationFieldType + "'");
      }
    }
    else {
      paramArrayOfInt[i] = paramInt;
    }
  }
  
  protected void setPeriod(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    setValues(setPeriodInternal(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8));
  }
  
  protected void setPeriod(ReadablePeriod paramReadablePeriod)
  {
    if (paramReadablePeriod == null)
    {
      setValues(new int[size()]);
      return;
    }
    setPeriodInternal(paramReadablePeriod);
  }
  
  protected void setValue(int paramInt1, int paramInt2)
  {
    iValues[paramInt1] = paramInt2;
  }
  
  protected void setValues(int[] paramArrayOfInt)
  {
    System.arraycopy(paramArrayOfInt, 0, iValues, 0, iValues.length);
  }
  
  public Duration toDurationFrom(ReadableInstant paramReadableInstant)
  {
    long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
    return new Duration(l, DateTimeUtils.getInstantChronology(paramReadableInstant).add(this, l, 1));
  }
  
  public Duration toDurationTo(ReadableInstant paramReadableInstant)
  {
    long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
    return new Duration(DateTimeUtils.getInstantChronology(paramReadableInstant).add(this, l, -1), l);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.base.BasePeriod
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */