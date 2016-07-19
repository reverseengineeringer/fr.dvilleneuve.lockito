package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormatter;

public abstract interface PartialConverter
  extends Converter
{
  public abstract Chronology getChronology(Object paramObject, Chronology paramChronology);
  
  public abstract Chronology getChronology(Object paramObject, DateTimeZone paramDateTimeZone);
  
  public abstract int[] getPartialValues(ReadablePartial paramReadablePartial, Object paramObject, Chronology paramChronology);
  
  public abstract int[] getPartialValues(ReadablePartial paramReadablePartial, Object paramObject, Chronology paramChronology, DateTimeFormatter paramDateTimeFormatter);
}

/* Location:
 * Qualified Name:     org.joda.time.convert.PartialConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */