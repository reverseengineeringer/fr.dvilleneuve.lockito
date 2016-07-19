package org.joda.time;

public abstract interface ReadableInterval
{
  public abstract boolean contains(ReadableInstant paramReadableInstant);
  
  public abstract boolean contains(ReadableInterval paramReadableInterval);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract Chronology getChronology();
  
  public abstract DateTime getEnd();
  
  public abstract long getEndMillis();
  
  public abstract DateTime getStart();
  
  public abstract long getStartMillis();
  
  public abstract int hashCode();
  
  public abstract boolean isAfter(ReadableInstant paramReadableInstant);
  
  public abstract boolean isAfter(ReadableInterval paramReadableInterval);
  
  public abstract boolean isBefore(ReadableInstant paramReadableInstant);
  
  public abstract boolean isBefore(ReadableInterval paramReadableInterval);
  
  public abstract boolean overlaps(ReadableInterval paramReadableInterval);
  
  public abstract Duration toDuration();
  
  public abstract long toDurationMillis();
  
  public abstract Interval toInterval();
  
  public abstract MutableInterval toMutableInterval();
  
  public abstract Period toPeriod();
  
  public abstract Period toPeriod(PeriodType paramPeriodType);
  
  public abstract String toString();
}

/* Location:
 * Qualified Name:     org.joda.time.ReadableInterval
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */