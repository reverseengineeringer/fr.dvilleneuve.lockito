package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.ReadWritableInterval;

public abstract interface IntervalConverter
  extends Converter
{
  public abstract boolean isReadableInterval(Object paramObject, Chronology paramChronology);
  
  public abstract void setInto(ReadWritableInterval paramReadWritableInterval, Object paramObject, Chronology paramChronology);
}

/* Location:
 * Qualified Name:     org.joda.time.convert.IntervalConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */