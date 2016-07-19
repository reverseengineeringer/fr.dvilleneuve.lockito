package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableInstant;
import org.joda.time.chrono.ISOChronology;

class ReadableInstantConverter
  extends AbstractConverter
  implements InstantConverter, PartialConverter
{
  static final ReadableInstantConverter INSTANCE = new ReadableInstantConverter();
  
  public Chronology getChronology(Object paramObject, Chronology paramChronology)
  {
    Chronology localChronology = paramChronology;
    if (paramChronology == null) {
      localChronology = DateTimeUtils.getChronology(((ReadableInstant)paramObject).getChronology());
    }
    return localChronology;
  }
  
  public Chronology getChronology(Object paramObject, DateTimeZone paramDateTimeZone)
  {
    Chronology localChronology = ((ReadableInstant)paramObject).getChronology();
    if (localChronology == null) {
      return ISOChronology.getInstance(paramDateTimeZone);
    }
    paramObject = localChronology;
    if (localChronology.getZone() != paramDateTimeZone)
    {
      localChronology = localChronology.withZone(paramDateTimeZone);
      paramObject = localChronology;
      if (localChronology == null) {
        return ISOChronology.getInstance(paramDateTimeZone);
      }
    }
    return (Chronology)paramObject;
  }
  
  public long getInstantMillis(Object paramObject, Chronology paramChronology)
  {
    return ((ReadableInstant)paramObject).getMillis();
  }
  
  public Class<?> getSupportedType()
  {
    return ReadableInstant.class;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.convert.ReadableInstantConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */