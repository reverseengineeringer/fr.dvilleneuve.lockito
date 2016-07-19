package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;

public abstract interface InstantConverter
  extends Converter
{
  public abstract Chronology getChronology(Object paramObject, Chronology paramChronology);
  
  public abstract Chronology getChronology(Object paramObject, DateTimeZone paramDateTimeZone);
  
  public abstract long getInstantMillis(Object paramObject, Chronology paramChronology);
}

/* Location:
 * Qualified Name:     org.joda.time.convert.InstantConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */