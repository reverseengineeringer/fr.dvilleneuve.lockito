package org.joda.time;

public abstract interface ReadablePartial
  extends Comparable<ReadablePartial>
{
  public abstract boolean equals(Object paramObject);
  
  public abstract int get(DateTimeFieldType paramDateTimeFieldType);
  
  public abstract Chronology getChronology();
  
  public abstract DateTimeField getField(int paramInt);
  
  public abstract DateTimeFieldType getFieldType(int paramInt);
  
  public abstract int getValue(int paramInt);
  
  public abstract int hashCode();
  
  public abstract boolean isSupported(DateTimeFieldType paramDateTimeFieldType);
  
  public abstract int size();
  
  public abstract DateTime toDateTime(ReadableInstant paramReadableInstant);
  
  public abstract String toString();
}

/* Location:
 * Qualified Name:     org.joda.time.ReadablePartial
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */