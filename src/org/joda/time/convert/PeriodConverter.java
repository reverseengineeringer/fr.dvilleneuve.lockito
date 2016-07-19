package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;

public abstract interface PeriodConverter
  extends Converter
{
  public abstract PeriodType getPeriodType(Object paramObject);
  
  public abstract void setInto(ReadWritablePeriod paramReadWritablePeriod, Object paramObject, Chronology paramChronology);
}

/* Location:
 * Qualified Name:     org.joda.time.convert.PeriodConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */