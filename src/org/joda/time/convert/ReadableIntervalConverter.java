package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadableInterval;

class ReadableIntervalConverter
  extends AbstractConverter
  implements IntervalConverter, DurationConverter, PeriodConverter
{
  static final ReadableIntervalConverter INSTANCE = new ReadableIntervalConverter();
  
  public long getDurationMillis(Object paramObject)
  {
    return ((ReadableInterval)paramObject).toDurationMillis();
  }
  
  public Class<?> getSupportedType()
  {
    return ReadableInterval.class;
  }
  
  public boolean isReadableInterval(Object paramObject, Chronology paramChronology)
  {
    return true;
  }
  
  public void setInto(ReadWritableInterval paramReadWritableInterval, Object paramObject, Chronology paramChronology)
  {
    paramObject = (ReadableInterval)paramObject;
    paramReadWritableInterval.setInterval((ReadableInterval)paramObject);
    if (paramChronology != null)
    {
      paramReadWritableInterval.setChronology(paramChronology);
      return;
    }
    paramReadWritableInterval.setChronology(((ReadableInterval)paramObject).getChronology());
  }
  
  public void setInto(ReadWritablePeriod paramReadWritablePeriod, Object paramObject, Chronology paramChronology)
  {
    paramObject = (ReadableInterval)paramObject;
    if (paramChronology != null) {}
    for (;;)
    {
      paramObject = paramChronology.get(paramReadWritablePeriod, ((ReadableInterval)paramObject).getStartMillis(), ((ReadableInterval)paramObject).getEndMillis());
      int i = 0;
      while (i < paramObject.length)
      {
        paramReadWritablePeriod.setValue(i, paramObject[i]);
        i += 1;
      }
      paramChronology = DateTimeUtils.getIntervalChronology((ReadableInterval)paramObject);
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.convert.ReadableIntervalConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */