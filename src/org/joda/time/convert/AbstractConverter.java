package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.PeriodType;
import org.joda.time.ReadablePartial;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormatter;

public abstract class AbstractConverter
  implements Converter
{
  public Chronology getChronology(Object paramObject, Chronology paramChronology)
  {
    return DateTimeUtils.getChronology(paramChronology);
  }
  
  public Chronology getChronology(Object paramObject, DateTimeZone paramDateTimeZone)
  {
    return ISOChronology.getInstance(paramDateTimeZone);
  }
  
  public long getInstantMillis(Object paramObject, Chronology paramChronology)
  {
    return DateTimeUtils.currentTimeMillis();
  }
  
  public int[] getPartialValues(ReadablePartial paramReadablePartial, Object paramObject, Chronology paramChronology)
  {
    return paramChronology.get(paramReadablePartial, getInstantMillis(paramObject, paramChronology));
  }
  
  public int[] getPartialValues(ReadablePartial paramReadablePartial, Object paramObject, Chronology paramChronology, DateTimeFormatter paramDateTimeFormatter)
  {
    return getPartialValues(paramReadablePartial, paramObject, paramChronology);
  }
  
  public PeriodType getPeriodType(Object paramObject)
  {
    return PeriodType.standard();
  }
  
  public boolean isReadableInterval(Object paramObject, Chronology paramChronology)
  {
    return false;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("Converter[");
    if (getSupportedType() == null) {}
    for (String str = "null";; str = getSupportedType().getName()) {
      return str + "]";
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.convert.AbstractConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */