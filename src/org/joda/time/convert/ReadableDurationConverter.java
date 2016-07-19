package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadableDuration;

class ReadableDurationConverter
  extends AbstractConverter
  implements DurationConverter, PeriodConverter
{
  static final ReadableDurationConverter INSTANCE = new ReadableDurationConverter();
  
  public long getDurationMillis(Object paramObject)
  {
    return ((ReadableDuration)paramObject).getMillis();
  }
  
  public Class<?> getSupportedType()
  {
    return ReadableDuration.class;
  }
  
  public void setInto(ReadWritablePeriod paramReadWritablePeriod, Object paramObject, Chronology paramChronology)
  {
    paramObject = (ReadableDuration)paramObject;
    paramObject = DateTimeUtils.getChronology(paramChronology).get(paramReadWritablePeriod, ((ReadableDuration)paramObject).getMillis());
    int i = 0;
    while (i < paramObject.length)
    {
      paramReadWritablePeriod.setValue(i, paramObject[i]);
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.convert.ReadableDurationConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */