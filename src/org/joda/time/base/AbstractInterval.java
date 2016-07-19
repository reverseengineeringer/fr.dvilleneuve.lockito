package org.joda.time.base;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.MutableInterval;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadableInterval;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public abstract class AbstractInterval
  implements ReadableInterval
{
  protected void checkInterval(long paramLong1, long paramLong2)
  {
    if (paramLong2 < paramLong1) {
      throw new IllegalArgumentException("The end instant must be greater or equal to the start");
    }
  }
  
  public boolean contains(long paramLong)
  {
    long l1 = getStartMillis();
    long l2 = getEndMillis();
    return (paramLong >= l1) && (paramLong < l2);
  }
  
  public boolean contains(ReadableInstant paramReadableInstant)
  {
    if (paramReadableInstant == null) {
      return containsNow();
    }
    return contains(paramReadableInstant.getMillis());
  }
  
  public boolean contains(ReadableInterval paramReadableInterval)
  {
    if (paramReadableInterval == null) {
      return containsNow();
    }
    long l1 = paramReadableInterval.getStartMillis();
    long l2 = paramReadableInterval.getEndMillis();
    long l3 = getStartMillis();
    long l4 = getEndMillis();
    return (l3 <= l1) && (l1 < l4) && (l2 <= l4);
  }
  
  public boolean containsNow()
  {
    return contains(DateTimeUtils.currentTimeMillis());
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof ReadableInterval)) {
        return false;
      }
      paramObject = (ReadableInterval)paramObject;
    } while ((getStartMillis() == ((ReadableInterval)paramObject).getStartMillis()) && (getEndMillis() == ((ReadableInterval)paramObject).getEndMillis()) && (FieldUtils.equals(getChronology(), ((ReadableInterval)paramObject).getChronology())));
    return false;
  }
  
  public DateTime getEnd()
  {
    return new DateTime(getEndMillis(), getChronology());
  }
  
  public DateTime getStart()
  {
    return new DateTime(getStartMillis(), getChronology());
  }
  
  public int hashCode()
  {
    long l1 = getStartMillis();
    long l2 = getEndMillis();
    return (((int)(l1 >>> 32 ^ l1) + 3007) * 31 + (int)(l2 >>> 32 ^ l2)) * 31 + getChronology().hashCode();
  }
  
  public boolean isAfter(long paramLong)
  {
    return getStartMillis() > paramLong;
  }
  
  public boolean isAfter(ReadableInstant paramReadableInstant)
  {
    if (paramReadableInstant == null) {
      return isAfterNow();
    }
    return isAfter(paramReadableInstant.getMillis());
  }
  
  public boolean isAfter(ReadableInterval paramReadableInterval)
  {
    if (paramReadableInterval == null) {}
    for (long l = DateTimeUtils.currentTimeMillis(); getStartMillis() >= l; l = paramReadableInterval.getEndMillis()) {
      return true;
    }
    return false;
  }
  
  public boolean isAfterNow()
  {
    return isAfter(DateTimeUtils.currentTimeMillis());
  }
  
  public boolean isBefore(long paramLong)
  {
    return getEndMillis() <= paramLong;
  }
  
  public boolean isBefore(ReadableInstant paramReadableInstant)
  {
    if (paramReadableInstant == null) {
      return isBeforeNow();
    }
    return isBefore(paramReadableInstant.getMillis());
  }
  
  public boolean isBefore(ReadableInterval paramReadableInterval)
  {
    if (paramReadableInterval == null) {
      return isBeforeNow();
    }
    return isBefore(paramReadableInterval.getStartMillis());
  }
  
  public boolean isBeforeNow()
  {
    return isBefore(DateTimeUtils.currentTimeMillis());
  }
  
  public boolean isEqual(ReadableInterval paramReadableInterval)
  {
    return (getStartMillis() == paramReadableInterval.getStartMillis()) && (getEndMillis() == paramReadableInterval.getEndMillis());
  }
  
  public boolean overlaps(ReadableInterval paramReadableInterval)
  {
    long l1 = getStartMillis();
    long l2 = getEndMillis();
    long l3;
    if (paramReadableInterval == null)
    {
      l3 = DateTimeUtils.currentTimeMillis();
      if ((l1 >= l3) || (l3 >= l2)) {}
    }
    do
    {
      return true;
      return false;
      l3 = paramReadableInterval.getStartMillis();
    } while ((l1 < paramReadableInterval.getEndMillis()) && (l3 < l2));
    return false;
  }
  
  public Duration toDuration()
  {
    long l = toDurationMillis();
    if (l == 0L) {
      return Duration.ZERO;
    }
    return new Duration(l);
  }
  
  public long toDurationMillis()
  {
    return FieldUtils.safeAdd(getEndMillis(), -getStartMillis());
  }
  
  public Interval toInterval()
  {
    return new Interval(getStartMillis(), getEndMillis(), getChronology());
  }
  
  public MutableInterval toMutableInterval()
  {
    return new MutableInterval(getStartMillis(), getEndMillis(), getChronology());
  }
  
  public Period toPeriod()
  {
    return new Period(getStartMillis(), getEndMillis(), getChronology());
  }
  
  public Period toPeriod(PeriodType paramPeriodType)
  {
    return new Period(getStartMillis(), getEndMillis(), paramPeriodType, getChronology());
  }
  
  public String toString()
  {
    DateTimeFormatter localDateTimeFormatter = ISODateTimeFormat.dateTime().withChronology(getChronology());
    StringBuffer localStringBuffer = new StringBuffer(48);
    localDateTimeFormatter.printTo(localStringBuffer, getStartMillis());
    localStringBuffer.append('/');
    localDateTimeFormatter.printTo(localStringBuffer, getEndMillis());
    return localStringBuffer.toString();
  }
}

/* Location:
 * Qualified Name:     org.joda.time.base.AbstractInterval
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */