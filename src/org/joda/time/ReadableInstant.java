package org.joda.time;

public abstract interface ReadableInstant
  extends Comparable<ReadableInstant>
{
  public abstract boolean equals(Object paramObject);
  
  public abstract int get(DateTimeFieldType paramDateTimeFieldType);
  
  public abstract Chronology getChronology();
  
  public abstract long getMillis();
  
  public abstract DateTimeZone getZone();
  
  public abstract int hashCode();
  
  public abstract boolean isAfter(ReadableInstant paramReadableInstant);
  
  public abstract boolean isBefore(ReadableInstant paramReadableInstant);
  
  public abstract boolean isEqual(ReadableInstant paramReadableInstant);
  
  public abstract boolean isSupported(DateTimeFieldType paramDateTimeFieldType);
  
  public abstract Instant toInstant();
  
  public abstract String toString();
}

/* Location:
 * Qualified Name:     org.joda.time.ReadableInstant
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */