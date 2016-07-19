package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInstant;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.DurationConverter;
import org.joda.time.field.FieldUtils;

public abstract class BaseDuration
  extends AbstractDuration
  implements ReadableDuration, Serializable
{
  private static final long serialVersionUID = 2581698638990L;
  private volatile long iMillis;
  
  protected BaseDuration(long paramLong)
  {
    iMillis = paramLong;
  }
  
  protected BaseDuration(long paramLong1, long paramLong2)
  {
    iMillis = FieldUtils.safeAdd(paramLong2, -paramLong1);
  }
  
  protected BaseDuration(Object paramObject)
  {
    iMillis = ConverterManager.getInstance().getDurationConverter(paramObject).getDurationMillis(paramObject);
  }
  
  protected BaseDuration(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
  {
    if (paramReadableInstant1 == paramReadableInstant2)
    {
      iMillis = 0L;
      return;
    }
    long l = DateTimeUtils.getInstantMillis(paramReadableInstant1);
    iMillis = FieldUtils.safeAdd(DateTimeUtils.getInstantMillis(paramReadableInstant2), -l);
  }
  
  public long getMillis()
  {
    return iMillis;
  }
  
  protected void setMillis(long paramLong)
  {
    iMillis = paramLong;
  }
  
  public Interval toIntervalFrom(ReadableInstant paramReadableInstant)
  {
    return new Interval(paramReadableInstant, this);
  }
  
  public Interval toIntervalTo(ReadableInstant paramReadableInstant)
  {
    return new Interval(this, paramReadableInstant);
  }
  
  public Period toPeriod(Chronology paramChronology)
  {
    return new Period(getMillis(), paramChronology);
  }
  
  public Period toPeriod(PeriodType paramPeriodType)
  {
    return new Period(getMillis(), paramPeriodType);
  }
  
  public Period toPeriod(PeriodType paramPeriodType, Chronology paramChronology)
  {
    return new Period(getMillis(), paramPeriodType, paramChronology);
  }
  
  public Period toPeriodFrom(ReadableInstant paramReadableInstant)
  {
    return new Period(paramReadableInstant, this);
  }
  
  public Period toPeriodFrom(ReadableInstant paramReadableInstant, PeriodType paramPeriodType)
  {
    return new Period(paramReadableInstant, this, paramPeriodType);
  }
  
  public Period toPeriodTo(ReadableInstant paramReadableInstant)
  {
    return new Period(this, paramReadableInstant);
  }
  
  public Period toPeriodTo(ReadableInstant paramReadableInstant, PeriodType paramPeriodType)
  {
    return new Period(this, paramReadableInstant, paramPeriodType);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.base.BaseDuration
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */