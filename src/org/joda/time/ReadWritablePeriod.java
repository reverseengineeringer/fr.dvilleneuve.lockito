package org.joda.time;

public abstract interface ReadWritablePeriod
  extends ReadablePeriod
{
  public abstract void add(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8);
  
  public abstract void add(DurationFieldType paramDurationFieldType, int paramInt);
  
  public abstract void add(ReadableInterval paramReadableInterval);
  
  public abstract void add(ReadablePeriod paramReadablePeriod);
  
  public abstract void addDays(int paramInt);
  
  public abstract void addHours(int paramInt);
  
  public abstract void addMillis(int paramInt);
  
  public abstract void addMinutes(int paramInt);
  
  public abstract void addMonths(int paramInt);
  
  public abstract void addSeconds(int paramInt);
  
  public abstract void addWeeks(int paramInt);
  
  public abstract void addYears(int paramInt);
  
  public abstract void clear();
  
  public abstract void set(DurationFieldType paramDurationFieldType, int paramInt);
  
  public abstract void setDays(int paramInt);
  
  public abstract void setHours(int paramInt);
  
  public abstract void setMillis(int paramInt);
  
  public abstract void setMinutes(int paramInt);
  
  public abstract void setMonths(int paramInt);
  
  public abstract void setPeriod(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8);
  
  public abstract void setPeriod(ReadableInterval paramReadableInterval);
  
  public abstract void setPeriod(ReadablePeriod paramReadablePeriod);
  
  public abstract void setSeconds(int paramInt);
  
  public abstract void setValue(int paramInt1, int paramInt2);
  
  public abstract void setWeeks(int paramInt);
  
  public abstract void setYears(int paramInt);
}

/* Location:
 * Qualified Name:     org.joda.time.ReadWritablePeriod
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */