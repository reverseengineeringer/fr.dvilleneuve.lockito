package org.joda.time.chrono;

import java.util.HashMap;
import java.util.Map;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.field.SkipDateTimeField;

public final class EthiopicChronology
  extends BasicFixedMonthChronology
{
  public static final int EE = 1;
  private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("EE");
  private static final EthiopicChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
  private static final int MAX_YEAR = 292272984;
  private static final int MIN_YEAR = -292269337;
  private static final Map<DateTimeZone, EthiopicChronology[]> cCache = new HashMap();
  private static final long serialVersionUID = -5972804258688333942L;
  
  EthiopicChronology(Chronology paramChronology, Object paramObject, int paramInt)
  {
    super(paramChronology, paramObject, paramInt);
  }
  
  public static EthiopicChronology getInstance()
  {
    return getInstance(DateTimeZone.getDefault(), 4);
  }
  
  public static EthiopicChronology getInstance(DateTimeZone paramDateTimeZone)
  {
    return getInstance(paramDateTimeZone, 4);
  }
  
  public static EthiopicChronology getInstance(DateTimeZone paramDateTimeZone, int paramInt)
  {
    DateTimeZone localDateTimeZone = paramDateTimeZone;
    if (paramDateTimeZone == null) {
      localDateTimeZone = DateTimeZone.getDefault();
    }
    for (;;)
    {
      Object localObject1;
      synchronized (cCache)
      {
        paramDateTimeZone = (EthiopicChronology[])cCache.get(localDateTimeZone);
        localObject1 = paramDateTimeZone;
        if (paramDateTimeZone == null)
        {
          localObject1 = new EthiopicChronology[7];
          cCache.put(localDateTimeZone, localObject1);
        }
        Object localObject2 = localObject1[(paramInt - 1)];
        paramDateTimeZone = (DateTimeZone)localObject2;
        if (localObject2 == null)
        {
          if (localDateTimeZone == DateTimeZone.UTC)
          {
            paramDateTimeZone = new EthiopicChronology(null, null, paramInt);
            paramDateTimeZone = new EthiopicChronology(LimitChronology.getInstance(paramDateTimeZone, new DateTime(1, 1, 1, 0, 0, 0, 0, paramDateTimeZone), null), null, paramInt);
            break label152;
          }
        }
        else {
          return paramDateTimeZone;
        }
      }
      paramDateTimeZone = new EthiopicChronology(ZonedChronology.getInstance(getInstance(DateTimeZone.UTC, paramInt), localDateTimeZone), null, paramInt);
      label152:
      localObject1[(paramInt - 1)] = paramDateTimeZone;
    }
  }
  
  public static EthiopicChronology getInstanceUTC()
  {
    return INSTANCE_UTC;
  }
  
  private Object readResolve()
  {
    Chronology localChronology = getBase();
    if (localChronology == null) {
      return getInstance(DateTimeZone.UTC, getMinimumDaysInFirstWeek());
    }
    return getInstance(localChronology.getZone(), getMinimumDaysInFirstWeek());
  }
  
  protected void assemble(AssembledChronology.Fields paramFields)
  {
    if (getBase() == null)
    {
      super.assemble(paramFields);
      year = new SkipDateTimeField(this, year);
      weekyear = new SkipDateTimeField(this, weekyear);
      era = ERA_FIELD;
      monthOfYear = new BasicMonthOfYearDateTimeField(this, 13);
      months = monthOfYear.getDurationField();
    }
  }
  
  long calculateFirstDayOfYearMillis(int paramInt)
  {
    int k = paramInt - 1963;
    int i;
    if (k <= 0) {
      i = k + 3 >> 2;
    }
    for (;;)
    {
      return 21859200000L + (k * 365L + i) * 86400000L;
      int j = k >> 2;
      i = j;
      if (!isLeapYear(paramInt)) {
        i = j + 1;
      }
    }
  }
  
  long getApproxMillisAtEpochDividedByTwo()
  {
    return 30962844000000L;
  }
  
  int getMaxYear()
  {
    return 292272984;
  }
  
  int getMinYear()
  {
    return -292269337;
  }
  
  public Chronology withUTC()
  {
    return INSTANCE_UTC;
  }
  
  public Chronology withZone(DateTimeZone paramDateTimeZone)
  {
    DateTimeZone localDateTimeZone = paramDateTimeZone;
    if (paramDateTimeZone == null) {
      localDateTimeZone = DateTimeZone.getDefault();
    }
    if (localDateTimeZone == getZone()) {
      return this;
    }
    return getInstance(localDateTimeZone);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.EthiopicChronology
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */