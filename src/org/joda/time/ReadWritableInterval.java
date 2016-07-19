package org.joda.time;

public abstract interface ReadWritableInterval
  extends ReadableInterval
{
  public abstract void setChronology(Chronology paramChronology);
  
  public abstract void setDurationAfterStart(ReadableDuration paramReadableDuration);
  
  public abstract void setDurationBeforeEnd(ReadableDuration paramReadableDuration);
  
  public abstract void setEnd(ReadableInstant paramReadableInstant);
  
  public abstract void setEndMillis(long paramLong);
  
  public abstract void setInterval(long paramLong1, long paramLong2);
  
  public abstract void setInterval(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2);
  
  public abstract void setInterval(ReadableInterval paramReadableInterval);
  
  public abstract void setPeriodAfterStart(ReadablePeriod paramReadablePeriod);
  
  public abstract void setPeriodBeforeEnd(ReadablePeriod paramReadablePeriod);
  
  public abstract void setStart(ReadableInstant paramReadableInstant);
  
  public abstract void setStartMillis(long paramLong);
}

/* Location:
 * Qualified Name:     org.joda.time.ReadWritableInterval
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */