package org.joda.time.convert;

import org.joda.time.Chronology;

class LongConverter
  extends AbstractConverter
  implements InstantConverter, PartialConverter, DurationConverter
{
  static final LongConverter INSTANCE = new LongConverter();
  
  public long getDurationMillis(Object paramObject)
  {
    return ((Long)paramObject).longValue();
  }
  
  public long getInstantMillis(Object paramObject, Chronology paramChronology)
  {
    return ((Long)paramObject).longValue();
  }
  
  public Class<?> getSupportedType()
  {
    return Long.class;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.convert.LongConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */