package org.joda.time;

public abstract class Chronology
{
  public abstract long add(long paramLong1, long paramLong2, int paramInt);
  
  public abstract long add(ReadablePeriod paramReadablePeriod, long paramLong, int paramInt);
  
  public abstract DurationField centuries();
  
  public abstract DateTimeField centuryOfEra();
  
  public abstract DateTimeField clockhourOfDay();
  
  public abstract DateTimeField clockhourOfHalfday();
  
  public abstract DateTimeField dayOfMonth();
  
  public abstract DateTimeField dayOfWeek();
  
  public abstract DateTimeField dayOfYear();
  
  public abstract DurationField days();
  
  public abstract DateTimeField era();
  
  public abstract DurationField eras();
  
  public abstract int[] get(ReadablePartial paramReadablePartial, long paramLong);
  
  public abstract int[] get(ReadablePeriod paramReadablePeriod, long paramLong);
  
  public abstract int[] get(ReadablePeriod paramReadablePeriod, long paramLong1, long paramLong2);
  
  public abstract long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7);
  
  public abstract long getDateTimeMillis(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract DateTimeZone getZone();
  
  public abstract DateTimeField halfdayOfDay();
  
  public abstract DurationField halfdays();
  
  public abstract DateTimeField hourOfDay();
  
  public abstract DateTimeField hourOfHalfday();
  
  public abstract DurationField hours();
  
  public abstract DurationField millis();
  
  public abstract DateTimeField millisOfDay();
  
  public abstract DateTimeField millisOfSecond();
  
  public abstract DateTimeField minuteOfDay();
  
  public abstract DateTimeField minuteOfHour();
  
  public abstract DurationField minutes();
  
  public abstract DateTimeField monthOfYear();
  
  public abstract DurationField months();
  
  public abstract DateTimeField secondOfDay();
  
  public abstract DateTimeField secondOfMinute();
  
  public abstract DurationField seconds();
  
  public abstract long set(ReadablePartial paramReadablePartial, long paramLong);
  
  public abstract String toString();
  
  public abstract void validate(ReadablePartial paramReadablePartial, int[] paramArrayOfInt);
  
  public abstract DateTimeField weekOfWeekyear();
  
  public abstract DurationField weeks();
  
  public abstract DateTimeField weekyear();
  
  public abstract DateTimeField weekyearOfCentury();
  
  public abstract DurationField weekyears();
  
  public abstract Chronology withUTC();
  
  public abstract Chronology withZone(DateTimeZone paramDateTimeZone);
  
  public abstract DateTimeField year();
  
  public abstract DateTimeField yearOfCentury();
  
  public abstract DateTimeField yearOfEra();
  
  public abstract DurationField years();
}

/* Location:
 * Qualified Name:     org.joda.time.Chronology
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */