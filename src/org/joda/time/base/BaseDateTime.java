package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableDateTime;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.InstantConverter;

public abstract class BaseDateTime
  extends AbstractDateTime
  implements ReadableDateTime, Serializable
{
  private static final long serialVersionUID = -6728882245981L;
  private volatile Chronology iChronology;
  private volatile long iMillis;
  
  public BaseDateTime()
  {
    this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
  }
  
  public BaseDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    this(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, ISOChronology.getInstance());
  }
  
  public BaseDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Chronology paramChronology)
  {
    iChronology = checkChronology(paramChronology);
    iMillis = checkInstant(iChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7), iChronology);
  }
  
  public BaseDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, DateTimeZone paramDateTimeZone)
  {
    this(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, ISOChronology.getInstance(paramDateTimeZone));
  }
  
  public BaseDateTime(long paramLong)
  {
    this(paramLong, ISOChronology.getInstance());
  }
  
  public BaseDateTime(long paramLong, Chronology paramChronology)
  {
    iChronology = checkChronology(paramChronology);
    iMillis = checkInstant(paramLong, iChronology);
  }
  
  public BaseDateTime(long paramLong, DateTimeZone paramDateTimeZone)
  {
    this(paramLong, ISOChronology.getInstance(paramDateTimeZone));
  }
  
  public BaseDateTime(Object paramObject, Chronology paramChronology)
  {
    InstantConverter localInstantConverter = ConverterManager.getInstance().getInstantConverter(paramObject);
    iChronology = checkChronology(localInstantConverter.getChronology(paramObject, paramChronology));
    iMillis = checkInstant(localInstantConverter.getInstantMillis(paramObject, paramChronology), iChronology);
  }
  
  public BaseDateTime(Object paramObject, DateTimeZone paramDateTimeZone)
  {
    InstantConverter localInstantConverter = ConverterManager.getInstance().getInstantConverter(paramObject);
    paramDateTimeZone = checkChronology(localInstantConverter.getChronology(paramObject, paramDateTimeZone));
    iChronology = paramDateTimeZone;
    iMillis = checkInstant(localInstantConverter.getInstantMillis(paramObject, paramDateTimeZone), paramDateTimeZone);
  }
  
  public BaseDateTime(Chronology paramChronology)
  {
    this(DateTimeUtils.currentTimeMillis(), paramChronology);
  }
  
  public BaseDateTime(DateTimeZone paramDateTimeZone)
  {
    this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance(paramDateTimeZone));
  }
  
  protected Chronology checkChronology(Chronology paramChronology)
  {
    return DateTimeUtils.getChronology(paramChronology);
  }
  
  protected long checkInstant(long paramLong, Chronology paramChronology)
  {
    return paramLong;
  }
  
  public Chronology getChronology()
  {
    return iChronology;
  }
  
  public long getMillis()
  {
    return iMillis;
  }
  
  protected void setChronology(Chronology paramChronology)
  {
    iChronology = checkChronology(paramChronology);
  }
  
  protected void setMillis(long paramLong)
  {
    iMillis = checkInstant(paramLong, iChronology);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.base.BaseDateTime
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */