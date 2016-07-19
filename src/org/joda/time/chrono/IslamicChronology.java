package org.joda.time.chrono;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;

public final class IslamicChronology
  extends BasicChronology
{
  public static final int AH = 1;
  private static final int CYCLE = 30;
  private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("AH");
  private static final IslamicChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
  public static final LeapYearPatternType LEAP_YEAR_15_BASED = new LeapYearPatternType(0, 623158436);
  public static final LeapYearPatternType LEAP_YEAR_16_BASED = new LeapYearPatternType(1, 623191204);
  public static final LeapYearPatternType LEAP_YEAR_HABASH_AL_HASIB;
  public static final LeapYearPatternType LEAP_YEAR_INDIAN = new LeapYearPatternType(2, 690562340);
  private static final int LONG_MONTH_LENGTH = 30;
  private static final int MAX_YEAR = 292271022;
  private static final long MILLIS_PER_CYCLE = 918518400000L;
  private static final long MILLIS_PER_LONG_MONTH = 2592000000L;
  private static final long MILLIS_PER_LONG_YEAR = 30672000000L;
  private static final long MILLIS_PER_MONTH = 2551440384L;
  private static final long MILLIS_PER_MONTH_PAIR = 5097600000L;
  private static final long MILLIS_PER_SHORT_YEAR = 30585600000L;
  private static final long MILLIS_PER_YEAR = 30617280288L;
  private static final long MILLIS_YEAR_1 = -42521587200000L;
  private static final int MIN_YEAR = -292269337;
  private static final int MONTH_PAIR_LENGTH = 59;
  private static final int SHORT_MONTH_LENGTH = 29;
  private static final Map<DateTimeZone, IslamicChronology[]> cCache;
  private static final long serialVersionUID = -3663823829888L;
  private final LeapYearPatternType iLeapYears;
  
  static
  {
    LEAP_YEAR_HABASH_AL_HASIB = new LeapYearPatternType(3, 153692453);
    cCache = new HashMap();
  }
  
  IslamicChronology(Chronology paramChronology, Object paramObject, LeapYearPatternType paramLeapYearPatternType)
  {
    super(paramChronology, paramObject, 4);
    iLeapYears = paramLeapYearPatternType;
  }
  
  public static IslamicChronology getInstance()
  {
    return getInstance(DateTimeZone.getDefault(), LEAP_YEAR_16_BASED);
  }
  
  public static IslamicChronology getInstance(DateTimeZone paramDateTimeZone)
  {
    return getInstance(paramDateTimeZone, LEAP_YEAR_16_BASED);
  }
  
  public static IslamicChronology getInstance(DateTimeZone paramDateTimeZone, LeapYearPatternType paramLeapYearPatternType)
  {
    DateTimeZone localDateTimeZone = paramDateTimeZone;
    if (paramDateTimeZone == null) {
      localDateTimeZone = DateTimeZone.getDefault();
    }
    synchronized (cCache)
    {
      paramDateTimeZone = (IslamicChronology[])cCache.get(localDateTimeZone);
      Object localObject1 = paramDateTimeZone;
      if (paramDateTimeZone == null)
      {
        localObject1 = new IslamicChronology[4];
        cCache.put(localDateTimeZone, localObject1);
      }
      Object localObject2 = localObject1[index];
      paramDateTimeZone = (DateTimeZone)localObject2;
      if (localObject2 == null)
      {
        if (localDateTimeZone == DateTimeZone.UTC)
        {
          paramDateTimeZone = new IslamicChronology(null, null, paramLeapYearPatternType);
          paramDateTimeZone = new IslamicChronology(LimitChronology.getInstance(paramDateTimeZone, new DateTime(1, 1, 1, 0, 0, 0, 0, paramDateTimeZone), null), null, paramLeapYearPatternType);
          localObject1[index] = paramDateTimeZone;
        }
      }
      else {
        return paramDateTimeZone;
      }
      paramDateTimeZone = new IslamicChronology(ZonedChronology.getInstance(getInstance(DateTimeZone.UTC, paramLeapYearPatternType), localDateTimeZone), null, paramLeapYearPatternType);
    }
  }
  
  public static IslamicChronology getInstanceUTC()
  {
    return INSTANCE_UTC;
  }
  
  private Object readResolve()
  {
    Chronology localChronology = getBase();
    if (localChronology == null) {
      return getInstanceUTC();
    }
    return getInstance(localChronology.getZone());
  }
  
  protected void assemble(AssembledChronology.Fields paramFields)
  {
    if (getBase() == null)
    {
      super.assemble(paramFields);
      era = ERA_FIELD;
      monthOfYear = new BasicMonthOfYearDateTimeField(this, 12);
      months = monthOfYear.getDurationField();
    }
  }
  
  long calculateFirstDayOfYearMillis(int paramInt)
  {
    if (paramInt > 292271022) {
      throw new ArithmeticException("Year is too large: " + paramInt + " > " + 292271022);
    }
    if (paramInt < -292269337) {
      throw new ArithmeticException("Year is too small: " + paramInt + " < " + -292269337);
    }
    int i = paramInt - 1;
    long l1 = -42521587200000L + 918518400000L * (i / 30);
    paramInt = 1;
    if (paramInt < i % 30 + 1)
    {
      if (isLeapYear(paramInt)) {}
      for (long l2 = 30672000000L;; l2 = 30585600000L)
      {
        l1 += l2;
        paramInt += 1;
        break;
      }
    }
    return l1;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    IslamicChronology localIslamicChronology;
    do
    {
      return true;
      if (!(paramObject instanceof IslamicChronology)) {
        break;
      }
      localIslamicChronology = (IslamicChronology)paramObject;
    } while ((getLeapYearPatternTypeindex == getLeapYearPatternTypeindex) && (super.equals(paramObject)));
    return false;
    return false;
  }
  
  long getApproxMillisAtEpochDividedByTwo()
  {
    return 21260793600000L;
  }
  
  long getAverageMillisPerMonth()
  {
    return 2551440384L;
  }
  
  long getAverageMillisPerYear()
  {
    return 30617280288L;
  }
  
  long getAverageMillisPerYearDividedByTwo()
  {
    return 15308640144L;
  }
  
  int getDayOfMonth(long paramLong)
  {
    int i = getDayOfYear(paramLong) - 1;
    if (i == 354) {
      return 30;
    }
    return i % 59 % 30 + 1;
  }
  
  int getDaysInMonthMax()
  {
    return 30;
  }
  
  int getDaysInMonthMax(int paramInt)
  {
    if (paramInt == 12) {}
    while ((paramInt - 1) % 2 == 0) {
      return 30;
    }
    return 29;
  }
  
  int getDaysInYear(int paramInt)
  {
    if (isLeapYear(paramInt)) {
      return 355;
    }
    return 354;
  }
  
  int getDaysInYearMax()
  {
    return 355;
  }
  
  int getDaysInYearMonth(int paramInt1, int paramInt2)
  {
    if ((paramInt2 == 12) && (isLeapYear(paramInt1))) {}
    while ((paramInt2 - 1) % 2 == 0) {
      return 30;
    }
    return 29;
  }
  
  public LeapYearPatternType getLeapYearPatternType()
  {
    return iLeapYears;
  }
  
  int getMaxYear()
  {
    return 292271022;
  }
  
  int getMinYear()
  {
    return 1;
  }
  
  int getMonthOfYear(long paramLong, int paramInt)
  {
    paramInt = (int)((paramLong - getYearMillis(paramInt)) / 86400000L);
    if (paramInt == 354) {
      return 12;
    }
    return paramInt * 2 / 59 + 1;
  }
  
  long getTotalMillisByYearMonth(int paramInt1, int paramInt2)
  {
    paramInt1 = paramInt2 - 1;
    if (paramInt1 % 2 == 1) {
      return paramInt1 / 2 * 5097600000L + 2592000000L;
    }
    return paramInt1 / 2 * 5097600000L;
  }
  
  int getYear(long paramLong)
  {
    long l = paramLong + 42521587200000L;
    paramLong = l / 918518400000L;
    l %= 918518400000L;
    int i = (int)(30L * paramLong + 1L);
    if (isLeapYear(i))
    {
      paramLong = 30672000000L;
      if (l < paramLong) {
        return i;
      }
      l -= paramLong;
      i += 1;
      if (!isLeapYear(i)) {
        break label82;
      }
    }
    label82:
    for (paramLong = 30672000000L;; paramLong = 30585600000L)
    {
      break;
      paramLong = 30585600000L;
      break;
    }
    return i;
  }
  
  long getYearDifference(long paramLong1, long paramLong2)
  {
    int i = getYear(paramLong1);
    int j = getYear(paramLong2);
    long l1 = getYearMillis(i);
    long l2 = getYearMillis(j);
    j = i - j;
    i = j;
    if (paramLong1 - l1 < paramLong2 - l2) {
      i = j - 1;
    }
    return i;
  }
  
  public int hashCode()
  {
    return super.hashCode() * 13 + getLeapYearPatternType().hashCode();
  }
  
  boolean isLeapYear(int paramInt)
  {
    return iLeapYears.isLeapYear(paramInt);
  }
  
  long setYear(long paramLong, int paramInt)
  {
    int j = getDayOfYear(paramLong, getYear(paramLong));
    int k = getMillisOfDay(paramLong);
    int i = j;
    if (j > 354)
    {
      i = j;
      if (!isLeapYear(paramInt)) {
        i = j - 1;
      }
    }
    return getYearMonthDayMillis(paramInt, 1, i) + k;
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
  
  public static class LeapYearPatternType
    implements Serializable
  {
    private static final long serialVersionUID = 26581275372698L;
    final byte index;
    final int pattern;
    
    LeapYearPatternType(int paramInt1, int paramInt2)
    {
      index = ((byte)paramInt1);
      pattern = paramInt2;
    }
    
    private Object readResolve()
    {
      switch (index)
      {
      default: 
        return this;
      case 0: 
        return IslamicChronology.LEAP_YEAR_15_BASED;
      case 1: 
        return IslamicChronology.LEAP_YEAR_16_BASED;
      case 2: 
        return IslamicChronology.LEAP_YEAR_INDIAN;
      }
      return IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if ((paramObject instanceof LeapYearPatternType))
      {
        bool1 = bool2;
        if (index == index) {
          bool1 = true;
        }
      }
      return bool1;
    }
    
    public int hashCode()
    {
      return index;
    }
    
    boolean isLeapYear(int paramInt)
    {
      return (pattern & 1 << paramInt % 30) > 0;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.IslamicChronology
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */