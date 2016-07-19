package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.MutableInterval;
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadableInterval;
import org.joda.time.ReadablePeriod;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.IntervalConverter;
import org.joda.time.field.FieldUtils;

public abstract class BaseInterval
  extends AbstractInterval
  implements ReadableInterval, Serializable
{
  private static final long serialVersionUID = 576586928732749278L;
  private volatile Chronology iChronology;
  private volatile long iEndMillis;
  private volatile long iStartMillis;
  
  protected BaseInterval(long paramLong1, long paramLong2, Chronology paramChronology)
  {
    iChronology = DateTimeUtils.getChronology(paramChronology);
    checkInterval(paramLong1, paramLong2);
    iStartMillis = paramLong1;
    iEndMillis = paramLong2;
  }
  
  protected BaseInterval(Object paramObject, Chronology paramChronology)
  {
    IntervalConverter localIntervalConverter = ConverterManager.getInstance().getIntervalConverter(paramObject);
    if (localIntervalConverter.isReadableInterval(paramObject, paramChronology))
    {
      paramObject = (ReadableInterval)paramObject;
      if (paramChronology != null)
      {
        iChronology = paramChronology;
        iStartMillis = ((ReadableInterval)paramObject).getStartMillis();
        iEndMillis = ((ReadableInterval)paramObject).getEndMillis();
      }
    }
    for (;;)
    {
      checkInterval(iStartMillis, iEndMillis);
      return;
      paramChronology = ((ReadableInterval)paramObject).getChronology();
      break;
      if ((this instanceof ReadWritableInterval))
      {
        localIntervalConverter.setInto((ReadWritableInterval)this, paramObject, paramChronology);
      }
      else
      {
        MutableInterval localMutableInterval = new MutableInterval();
        localIntervalConverter.setInto(localMutableInterval, paramObject, paramChronology);
        iChronology = localMutableInterval.getChronology();
        iStartMillis = localMutableInterval.getStartMillis();
        iEndMillis = localMutableInterval.getEndMillis();
      }
    }
  }
  
  protected BaseInterval(ReadableDuration paramReadableDuration, ReadableInstant paramReadableInstant)
  {
    iChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
    iEndMillis = DateTimeUtils.getInstantMillis(paramReadableInstant);
    long l = DateTimeUtils.getDurationMillis(paramReadableDuration);
    iStartMillis = FieldUtils.safeAdd(iEndMillis, -l);
    checkInterval(iStartMillis, iEndMillis);
  }
  
  protected BaseInterval(ReadableInstant paramReadableInstant, ReadableDuration paramReadableDuration)
  {
    iChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
    iStartMillis = DateTimeUtils.getInstantMillis(paramReadableInstant);
    long l = DateTimeUtils.getDurationMillis(paramReadableDuration);
    iEndMillis = FieldUtils.safeAdd(iStartMillis, l);
    checkInterval(iStartMillis, iEndMillis);
  }
  
  protected BaseInterval(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
  {
    if ((paramReadableInstant1 == null) && (paramReadableInstant2 == null))
    {
      long l = DateTimeUtils.currentTimeMillis();
      iEndMillis = l;
      iStartMillis = l;
      iChronology = ISOChronology.getInstance();
      return;
    }
    iChronology = DateTimeUtils.getInstantChronology(paramReadableInstant1);
    iStartMillis = DateTimeUtils.getInstantMillis(paramReadableInstant1);
    iEndMillis = DateTimeUtils.getInstantMillis(paramReadableInstant2);
    checkInterval(iStartMillis, iEndMillis);
  }
  
  protected BaseInterval(ReadableInstant paramReadableInstant, ReadablePeriod paramReadablePeriod)
  {
    Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
    iChronology = localChronology;
    iStartMillis = DateTimeUtils.getInstantMillis(paramReadableInstant);
    if (paramReadablePeriod == null) {}
    for (iEndMillis = iStartMillis;; iEndMillis = localChronology.add(paramReadablePeriod, iStartMillis, 1))
    {
      checkInterval(iStartMillis, iEndMillis);
      return;
    }
  }
  
  protected BaseInterval(ReadablePeriod paramReadablePeriod, ReadableInstant paramReadableInstant)
  {
    Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
    iChronology = localChronology;
    iEndMillis = DateTimeUtils.getInstantMillis(paramReadableInstant);
    if (paramReadablePeriod == null) {}
    for (iStartMillis = iEndMillis;; iStartMillis = localChronology.add(paramReadablePeriod, iEndMillis, -1))
    {
      checkInterval(iStartMillis, iEndMillis);
      return;
    }
  }
  
  public Chronology getChronology()
  {
    return iChronology;
  }
  
  public long getEndMillis()
  {
    return iEndMillis;
  }
  
  public long getStartMillis()
  {
    return iStartMillis;
  }
  
  protected void setInterval(long paramLong1, long paramLong2, Chronology paramChronology)
  {
    checkInterval(paramLong1, paramLong2);
    iStartMillis = paramLong1;
    iEndMillis = paramLong2;
    iChronology = DateTimeUtils.getChronology(paramChronology);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.base.BaseInterval
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */