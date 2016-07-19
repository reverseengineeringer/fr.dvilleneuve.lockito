package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BaseInterval;
import org.joda.time.field.FieldUtils;

public class MutableInterval
  extends BaseInterval
  implements ReadWritableInterval, Cloneable, Serializable
{
  private static final long serialVersionUID = -5982824024992428470L;
  
  public MutableInterval()
  {
    super(0L, 0L, null);
  }
  
  public MutableInterval(long paramLong1, long paramLong2)
  {
    super(paramLong1, paramLong2, null);
  }
  
  public MutableInterval(long paramLong1, long paramLong2, Chronology paramChronology)
  {
    super(paramLong1, paramLong2, paramChronology);
  }
  
  public MutableInterval(Object paramObject)
  {
    super(paramObject, null);
  }
  
  public MutableInterval(Object paramObject, Chronology paramChronology)
  {
    super(paramObject, paramChronology);
  }
  
  public MutableInterval(ReadableDuration paramReadableDuration, ReadableInstant paramReadableInstant)
  {
    super(paramReadableDuration, paramReadableInstant);
  }
  
  public MutableInterval(ReadableInstant paramReadableInstant, ReadableDuration paramReadableDuration)
  {
    super(paramReadableInstant, paramReadableDuration);
  }
  
  public MutableInterval(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
  {
    super(paramReadableInstant1, paramReadableInstant2);
  }
  
  public MutableInterval(ReadableInstant paramReadableInstant, ReadablePeriod paramReadablePeriod)
  {
    super(paramReadableInstant, paramReadablePeriod);
  }
  
  public MutableInterval(ReadablePeriod paramReadablePeriod, ReadableInstant paramReadableInstant)
  {
    super(paramReadablePeriod, paramReadableInstant);
  }
  
  public static MutableInterval parse(String paramString)
  {
    return new MutableInterval(paramString);
  }
  
  public Object clone()
  {
    try
    {
      Object localObject = super.clone();
      return localObject;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new InternalError("Clone error");
    }
  }
  
  public MutableInterval copy()
  {
    return (MutableInterval)clone();
  }
  
  public void setChronology(Chronology paramChronology)
  {
    super.setInterval(getStartMillis(), getEndMillis(), paramChronology);
  }
  
  public void setDurationAfterStart(long paramLong)
  {
    setEndMillis(FieldUtils.safeAdd(getStartMillis(), paramLong));
  }
  
  public void setDurationAfterStart(ReadableDuration paramReadableDuration)
  {
    long l = DateTimeUtils.getDurationMillis(paramReadableDuration);
    setEndMillis(FieldUtils.safeAdd(getStartMillis(), l));
  }
  
  public void setDurationBeforeEnd(long paramLong)
  {
    setStartMillis(FieldUtils.safeAdd(getEndMillis(), -paramLong));
  }
  
  public void setDurationBeforeEnd(ReadableDuration paramReadableDuration)
  {
    long l = DateTimeUtils.getDurationMillis(paramReadableDuration);
    setStartMillis(FieldUtils.safeAdd(getEndMillis(), -l));
  }
  
  public void setEnd(ReadableInstant paramReadableInstant)
  {
    long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
    super.setInterval(getStartMillis(), l, getChronology());
  }
  
  public void setEndMillis(long paramLong)
  {
    super.setInterval(getStartMillis(), paramLong, getChronology());
  }
  
  public void setInterval(long paramLong1, long paramLong2)
  {
    super.setInterval(paramLong1, paramLong2, getChronology());
  }
  
  public void setInterval(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
  {
    if ((paramReadableInstant1 == null) && (paramReadableInstant2 == null))
    {
      long l = DateTimeUtils.currentTimeMillis();
      setInterval(l, l);
      return;
    }
    super.setInterval(DateTimeUtils.getInstantMillis(paramReadableInstant1), DateTimeUtils.getInstantMillis(paramReadableInstant2), DateTimeUtils.getInstantChronology(paramReadableInstant1));
  }
  
  public void setInterval(ReadableInterval paramReadableInterval)
  {
    if (paramReadableInterval == null) {
      throw new IllegalArgumentException("Interval must not be null");
    }
    super.setInterval(paramReadableInterval.getStartMillis(), paramReadableInterval.getEndMillis(), paramReadableInterval.getChronology());
  }
  
  public void setPeriodAfterStart(ReadablePeriod paramReadablePeriod)
  {
    if (paramReadablePeriod == null)
    {
      setEndMillis(getStartMillis());
      return;
    }
    setEndMillis(getChronology().add(paramReadablePeriod, getStartMillis(), 1));
  }
  
  public void setPeriodBeforeEnd(ReadablePeriod paramReadablePeriod)
  {
    if (paramReadablePeriod == null)
    {
      setStartMillis(getEndMillis());
      return;
    }
    setStartMillis(getChronology().add(paramReadablePeriod, getEndMillis(), -1));
  }
  
  public void setStart(ReadableInstant paramReadableInstant)
  {
    super.setInterval(DateTimeUtils.getInstantMillis(paramReadableInstant), getEndMillis(), getChronology());
  }
  
  public void setStartMillis(long paramLong)
  {
    super.setInterval(paramLong, getEndMillis(), getChronology());
  }
}

/* Location:
 * Qualified Name:     org.joda.time.MutableInterval
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */