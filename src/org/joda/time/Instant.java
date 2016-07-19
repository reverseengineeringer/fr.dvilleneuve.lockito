package org.joda.time;

import java.io.Serializable;
import org.joda.convert.FromString;
import org.joda.time.base.AbstractInstant;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.InstantConverter;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class Instant
  extends AbstractInstant
  implements ReadableInstant, Serializable
{
  private static final long serialVersionUID = 3299096530934209741L;
  private final long iMillis;
  
  public Instant()
  {
    iMillis = DateTimeUtils.currentTimeMillis();
  }
  
  public Instant(long paramLong)
  {
    iMillis = paramLong;
  }
  
  public Instant(Object paramObject)
  {
    iMillis = ConverterManager.getInstance().getInstantConverter(paramObject).getInstantMillis(paramObject, ISOChronology.getInstanceUTC());
  }
  
  public static Instant now()
  {
    return new Instant();
  }
  
  @FromString
  public static Instant parse(String paramString)
  {
    return parse(paramString, ISODateTimeFormat.dateTimeParser());
  }
  
  public static Instant parse(String paramString, DateTimeFormatter paramDateTimeFormatter)
  {
    return paramDateTimeFormatter.parseDateTime(paramString).toInstant();
  }
  
  public Chronology getChronology()
  {
    return ISOChronology.getInstanceUTC();
  }
  
  public long getMillis()
  {
    return iMillis;
  }
  
  public Instant minus(long paramLong)
  {
    return withDurationAdded(paramLong, -1);
  }
  
  public Instant minus(ReadableDuration paramReadableDuration)
  {
    return withDurationAdded(paramReadableDuration, -1);
  }
  
  public Instant plus(long paramLong)
  {
    return withDurationAdded(paramLong, 1);
  }
  
  public Instant plus(ReadableDuration paramReadableDuration)
  {
    return withDurationAdded(paramReadableDuration, 1);
  }
  
  public DateTime toDateTime()
  {
    return new DateTime(getMillis(), ISOChronology.getInstance());
  }
  
  @Deprecated
  public DateTime toDateTimeISO()
  {
    return toDateTime();
  }
  
  public Instant toInstant()
  {
    return this;
  }
  
  public MutableDateTime toMutableDateTime()
  {
    return new MutableDateTime(getMillis(), ISOChronology.getInstance());
  }
  
  @Deprecated
  public MutableDateTime toMutableDateTimeISO()
  {
    return toMutableDateTime();
  }
  
  public Instant withDurationAdded(long paramLong, int paramInt)
  {
    if ((paramLong == 0L) || (paramInt == 0)) {
      return this;
    }
    return withMillis(getChronology().add(getMillis(), paramLong, paramInt));
  }
  
  public Instant withDurationAdded(ReadableDuration paramReadableDuration, int paramInt)
  {
    if ((paramReadableDuration == null) || (paramInt == 0)) {
      return this;
    }
    return withDurationAdded(paramReadableDuration.getMillis(), paramInt);
  }
  
  public Instant withMillis(long paramLong)
  {
    if (paramLong == iMillis) {
      return this;
    }
    return new Instant(paramLong);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.Instant
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */