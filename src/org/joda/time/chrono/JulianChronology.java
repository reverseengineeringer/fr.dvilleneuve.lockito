package org.joda.time.chrono;

import java.util.HashMap;
import java.util.Map;
import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.field.SkipDateTimeField;

public final class JulianChronology
  extends BasicGJChronology
{
  private static final JulianChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
  private static final int MAX_YEAR = 292272992;
  private static final long MILLIS_PER_MONTH = 2629800000L;
  private static final long MILLIS_PER_YEAR = 31557600000L;
  private static final int MIN_YEAR = -292269054;
  private static final Map<DateTimeZone, JulianChronology[]> cCache = new HashMap();
  private static final long serialVersionUID = -8731039522547897247L;
  
  JulianChronology(Chronology paramChronology, Object paramObject, int paramInt)
  {
    super(paramChronology, paramObject, paramInt);
  }
  
  static int adjustYearForSet(int paramInt)
  {
    int i = paramInt;
    if (paramInt <= 0)
    {
      if (paramInt == 0) {
        throw new IllegalFieldValueException(DateTimeFieldType.year(), Integer.valueOf(paramInt), null, null);
      }
      i = paramInt + 1;
    }
    return i;
  }
  
  public static JulianChronology getInstance()
  {
    return getInstance(DateTimeZone.getDefault(), 4);
  }
  
  public static JulianChronology getInstance(DateTimeZone paramDateTimeZone)
  {
    return getInstance(paramDateTimeZone, 4);
  }
  
  public static JulianChronology getInstance(DateTimeZone paramDateTimeZone, int paramInt)
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
        paramDateTimeZone = (JulianChronology[])cCache.get(localDateTimeZone);
        localObject1 = paramDateTimeZone;
        if (paramDateTimeZone == null)
        {
          localObject1 = new JulianChronology[7];
          cCache.put(localDateTimeZone, localObject1);
        }
        Object localObject2 = localObject1[(paramInt - 1)];
        paramDateTimeZone = (DateTimeZone)localObject2;
        if (localObject2 == null)
        {
          if (localDateTimeZone == DateTimeZone.UTC)
          {
            paramDateTimeZone = new JulianChronology(null, null, paramInt);
            break label122;
          }
        }
        else {
          return paramDateTimeZone;
        }
      }
      paramDateTimeZone = new JulianChronology(ZonedChronology.getInstance(getInstance(DateTimeZone.UTC, paramInt), localDateTimeZone), null, paramInt);
      label122:
      localObject1[(paramInt - 1)] = paramDateTimeZone;
    }
  }
  
  public static JulianChronology getInstanceUTC()
  {
    return INSTANCE_UTC;
  }
  
  private Object readResolve()
  {
    Chronology localChronology = getBase();
    int j = getMinimumDaysInFirstWeek();
    int i = j;
    if (j == 0) {
      i = 4;
    }
    if (localChronology == null) {
      return getInstance(DateTimeZone.UTC, i);
    }
    return getInstance(localChronology.getZone(), i);
  }
  
  protected void assemble(AssembledChronology.Fields paramFields)
  {
    if (getBase() == null)
    {
      super.assemble(paramFields);
      year = new SkipDateTimeField(this, year);
      weekyear = new SkipDateTimeField(this, weekyear);
    }
  }
  
  long calculateFirstDayOfYearMillis(int paramInt)
  {
    int k = paramInt - 1968;
    int i;
    if (k <= 0) {
      i = k + 3 >> 2;
    }
    for (;;)
    {
      return (k * 365L + i) * 86400000L - 62035200000L;
      int j = k >> 2;
      i = j;
      if (!isLeapYear(paramInt)) {
        i = j + 1;
      }
    }
  }
  
  long getApproxMillisAtEpochDividedByTwo()
  {
    return 31083663600000L;
  }
  
  long getAverageMillisPerMonth()
  {
    return 2629800000L;
  }
  
  long getAverageMillisPerYear()
  {
    return 31557600000L;
  }
  
  long getAverageMillisPerYearDividedByTwo()
  {
    return 15778800000L;
  }
  
  long getDateMidnightMillis(int paramInt1, int paramInt2, int paramInt3)
    throws IllegalArgumentException
  {
    return super.getDateMidnightMillis(adjustYearForSet(paramInt1), paramInt2, paramInt3);
  }
  
  int getMaxYear()
  {
    return 292272992;
  }
  
  int getMinYear()
  {
    return -292269054;
  }
  
  boolean isLeapYear(int paramInt)
  {
    return (paramInt & 0x3) == 0;
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
 * Qualified Name:     org.joda.time.chrono.JulianChronology
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */