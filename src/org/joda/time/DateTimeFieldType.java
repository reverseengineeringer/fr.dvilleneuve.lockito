package org.joda.time;

import java.io.Serializable;

public abstract class DateTimeFieldType
  implements Serializable
{
  static final byte CENTURY_OF_ERA = 3;
  private static final DateTimeFieldType CENTURY_OF_ERA_TYPE;
  static final byte CLOCKHOUR_OF_DAY = 16;
  private static final DateTimeFieldType CLOCKHOUR_OF_DAY_TYPE;
  static final byte CLOCKHOUR_OF_HALFDAY = 15;
  private static final DateTimeFieldType CLOCKHOUR_OF_HALFDAY_TYPE;
  static final byte DAY_OF_MONTH = 8;
  private static final DateTimeFieldType DAY_OF_MONTH_TYPE;
  static final byte DAY_OF_WEEK = 12;
  private static final DateTimeFieldType DAY_OF_WEEK_TYPE;
  static final byte DAY_OF_YEAR = 6;
  private static final DateTimeFieldType DAY_OF_YEAR_TYPE;
  static final byte ERA = 1;
  private static final DateTimeFieldType ERA_TYPE = new StandardDateTimeFieldType("era", (byte)1, DurationFieldType.eras(), null);
  static final byte HALFDAY_OF_DAY = 13;
  private static final DateTimeFieldType HALFDAY_OF_DAY_TYPE;
  static final byte HOUR_OF_DAY = 17;
  private static final DateTimeFieldType HOUR_OF_DAY_TYPE;
  static final byte HOUR_OF_HALFDAY = 14;
  private static final DateTimeFieldType HOUR_OF_HALFDAY_TYPE;
  static final byte MILLIS_OF_DAY = 22;
  private static final DateTimeFieldType MILLIS_OF_DAY_TYPE = new StandardDateTimeFieldType("millisOfDay", (byte)22, DurationFieldType.millis(), DurationFieldType.days());
  static final byte MILLIS_OF_SECOND = 23;
  private static final DateTimeFieldType MILLIS_OF_SECOND_TYPE = new StandardDateTimeFieldType("millisOfSecond", (byte)23, DurationFieldType.millis(), DurationFieldType.seconds());
  static final byte MINUTE_OF_DAY = 18;
  private static final DateTimeFieldType MINUTE_OF_DAY_TYPE;
  static final byte MINUTE_OF_HOUR = 19;
  private static final DateTimeFieldType MINUTE_OF_HOUR_TYPE;
  static final byte MONTH_OF_YEAR = 7;
  private static final DateTimeFieldType MONTH_OF_YEAR_TYPE;
  static final byte SECOND_OF_DAY = 20;
  private static final DateTimeFieldType SECOND_OF_DAY_TYPE;
  static final byte SECOND_OF_MINUTE = 21;
  private static final DateTimeFieldType SECOND_OF_MINUTE_TYPE;
  static final byte WEEKYEAR = 10;
  static final byte WEEKYEAR_OF_CENTURY = 9;
  private static final DateTimeFieldType WEEKYEAR_OF_CENTURY_TYPE;
  private static final DateTimeFieldType WEEKYEAR_TYPE;
  static final byte WEEK_OF_WEEKYEAR = 11;
  private static final DateTimeFieldType WEEK_OF_WEEKYEAR_TYPE;
  static final byte YEAR = 5;
  static final byte YEAR_OF_CENTURY = 4;
  private static final DateTimeFieldType YEAR_OF_CENTURY_TYPE;
  static final byte YEAR_OF_ERA = 2;
  private static final DateTimeFieldType YEAR_OF_ERA_TYPE = new StandardDateTimeFieldType("yearOfEra", (byte)2, DurationFieldType.years(), DurationFieldType.eras());
  private static final DateTimeFieldType YEAR_TYPE;
  private static final long serialVersionUID = -42615285973990L;
  private final String iName;
  
  static
  {
    CENTURY_OF_ERA_TYPE = new StandardDateTimeFieldType("centuryOfEra", (byte)3, DurationFieldType.centuries(), DurationFieldType.eras());
    YEAR_OF_CENTURY_TYPE = new StandardDateTimeFieldType("yearOfCentury", (byte)4, DurationFieldType.years(), DurationFieldType.centuries());
    YEAR_TYPE = new StandardDateTimeFieldType("year", (byte)5, DurationFieldType.years(), null);
    DAY_OF_YEAR_TYPE = new StandardDateTimeFieldType("dayOfYear", (byte)6, DurationFieldType.days(), DurationFieldType.years());
    MONTH_OF_YEAR_TYPE = new StandardDateTimeFieldType("monthOfYear", (byte)7, DurationFieldType.months(), DurationFieldType.years());
    DAY_OF_MONTH_TYPE = new StandardDateTimeFieldType("dayOfMonth", (byte)8, DurationFieldType.days(), DurationFieldType.months());
    WEEKYEAR_OF_CENTURY_TYPE = new StandardDateTimeFieldType("weekyearOfCentury", (byte)9, DurationFieldType.weekyears(), DurationFieldType.centuries());
    WEEKYEAR_TYPE = new StandardDateTimeFieldType("weekyear", (byte)10, DurationFieldType.weekyears(), null);
    WEEK_OF_WEEKYEAR_TYPE = new StandardDateTimeFieldType("weekOfWeekyear", (byte)11, DurationFieldType.weeks(), DurationFieldType.weekyears());
    DAY_OF_WEEK_TYPE = new StandardDateTimeFieldType("dayOfWeek", (byte)12, DurationFieldType.days(), DurationFieldType.weeks());
    HALFDAY_OF_DAY_TYPE = new StandardDateTimeFieldType("halfdayOfDay", (byte)13, DurationFieldType.halfdays(), DurationFieldType.days());
    HOUR_OF_HALFDAY_TYPE = new StandardDateTimeFieldType("hourOfHalfday", (byte)14, DurationFieldType.hours(), DurationFieldType.halfdays());
    CLOCKHOUR_OF_HALFDAY_TYPE = new StandardDateTimeFieldType("clockhourOfHalfday", (byte)15, DurationFieldType.hours(), DurationFieldType.halfdays());
    CLOCKHOUR_OF_DAY_TYPE = new StandardDateTimeFieldType("clockhourOfDay", (byte)16, DurationFieldType.hours(), DurationFieldType.days());
    HOUR_OF_DAY_TYPE = new StandardDateTimeFieldType("hourOfDay", (byte)17, DurationFieldType.hours(), DurationFieldType.days());
    MINUTE_OF_DAY_TYPE = new StandardDateTimeFieldType("minuteOfDay", (byte)18, DurationFieldType.minutes(), DurationFieldType.days());
    MINUTE_OF_HOUR_TYPE = new StandardDateTimeFieldType("minuteOfHour", (byte)19, DurationFieldType.minutes(), DurationFieldType.hours());
    SECOND_OF_DAY_TYPE = new StandardDateTimeFieldType("secondOfDay", (byte)20, DurationFieldType.seconds(), DurationFieldType.days());
    SECOND_OF_MINUTE_TYPE = new StandardDateTimeFieldType("secondOfMinute", (byte)21, DurationFieldType.seconds(), DurationFieldType.minutes());
  }
  
  protected DateTimeFieldType(String paramString)
  {
    iName = paramString;
  }
  
  public static DateTimeFieldType centuryOfEra()
  {
    return CENTURY_OF_ERA_TYPE;
  }
  
  public static DateTimeFieldType clockhourOfDay()
  {
    return CLOCKHOUR_OF_DAY_TYPE;
  }
  
  public static DateTimeFieldType clockhourOfHalfday()
  {
    return CLOCKHOUR_OF_HALFDAY_TYPE;
  }
  
  public static DateTimeFieldType dayOfMonth()
  {
    return DAY_OF_MONTH_TYPE;
  }
  
  public static DateTimeFieldType dayOfWeek()
  {
    return DAY_OF_WEEK_TYPE;
  }
  
  public static DateTimeFieldType dayOfYear()
  {
    return DAY_OF_YEAR_TYPE;
  }
  
  public static DateTimeFieldType era()
  {
    return ERA_TYPE;
  }
  
  public static DateTimeFieldType halfdayOfDay()
  {
    return HALFDAY_OF_DAY_TYPE;
  }
  
  public static DateTimeFieldType hourOfDay()
  {
    return HOUR_OF_DAY_TYPE;
  }
  
  public static DateTimeFieldType hourOfHalfday()
  {
    return HOUR_OF_HALFDAY_TYPE;
  }
  
  public static DateTimeFieldType millisOfDay()
  {
    return MILLIS_OF_DAY_TYPE;
  }
  
  public static DateTimeFieldType millisOfSecond()
  {
    return MILLIS_OF_SECOND_TYPE;
  }
  
  public static DateTimeFieldType minuteOfDay()
  {
    return MINUTE_OF_DAY_TYPE;
  }
  
  public static DateTimeFieldType minuteOfHour()
  {
    return MINUTE_OF_HOUR_TYPE;
  }
  
  public static DateTimeFieldType monthOfYear()
  {
    return MONTH_OF_YEAR_TYPE;
  }
  
  public static DateTimeFieldType secondOfDay()
  {
    return SECOND_OF_DAY_TYPE;
  }
  
  public static DateTimeFieldType secondOfMinute()
  {
    return SECOND_OF_MINUTE_TYPE;
  }
  
  public static DateTimeFieldType weekOfWeekyear()
  {
    return WEEK_OF_WEEKYEAR_TYPE;
  }
  
  public static DateTimeFieldType weekyear()
  {
    return WEEKYEAR_TYPE;
  }
  
  public static DateTimeFieldType weekyearOfCentury()
  {
    return WEEKYEAR_OF_CENTURY_TYPE;
  }
  
  public static DateTimeFieldType year()
  {
    return YEAR_TYPE;
  }
  
  public static DateTimeFieldType yearOfCentury()
  {
    return YEAR_OF_CENTURY_TYPE;
  }
  
  public static DateTimeFieldType yearOfEra()
  {
    return YEAR_OF_ERA_TYPE;
  }
  
  public abstract DurationFieldType getDurationType();
  
  public abstract DateTimeField getField(Chronology paramChronology);
  
  public String getName()
  {
    return iName;
  }
  
  public abstract DurationFieldType getRangeDurationType();
  
  public boolean isSupported(Chronology paramChronology)
  {
    return getField(paramChronology).isSupported();
  }
  
  public String toString()
  {
    return getName();
  }
  
  private static class StandardDateTimeFieldType
    extends DateTimeFieldType
  {
    private static final long serialVersionUID = -9937958251642L;
    private final byte iOrdinal;
    private final transient DurationFieldType iRangeType;
    private final transient DurationFieldType iUnitType;
    
    StandardDateTimeFieldType(String paramString, byte paramByte, DurationFieldType paramDurationFieldType1, DurationFieldType paramDurationFieldType2)
    {
      super();
      iOrdinal = paramByte;
      iUnitType = paramDurationFieldType1;
      iRangeType = paramDurationFieldType2;
    }
    
    private Object readResolve()
    {
      switch (iOrdinal)
      {
      default: 
        return this;
      case 1: 
        return DateTimeFieldType.ERA_TYPE;
      case 2: 
        return DateTimeFieldType.YEAR_OF_ERA_TYPE;
      case 3: 
        return DateTimeFieldType.CENTURY_OF_ERA_TYPE;
      case 4: 
        return DateTimeFieldType.YEAR_OF_CENTURY_TYPE;
      case 5: 
        return DateTimeFieldType.YEAR_TYPE;
      case 6: 
        return DateTimeFieldType.DAY_OF_YEAR_TYPE;
      case 7: 
        return DateTimeFieldType.MONTH_OF_YEAR_TYPE;
      case 8: 
        return DateTimeFieldType.DAY_OF_MONTH_TYPE;
      case 9: 
        return DateTimeFieldType.WEEKYEAR_OF_CENTURY_TYPE;
      case 10: 
        return DateTimeFieldType.WEEKYEAR_TYPE;
      case 11: 
        return DateTimeFieldType.WEEK_OF_WEEKYEAR_TYPE;
      case 12: 
        return DateTimeFieldType.DAY_OF_WEEK_TYPE;
      case 13: 
        return DateTimeFieldType.HALFDAY_OF_DAY_TYPE;
      case 14: 
        return DateTimeFieldType.HOUR_OF_HALFDAY_TYPE;
      case 15: 
        return DateTimeFieldType.CLOCKHOUR_OF_HALFDAY_TYPE;
      case 16: 
        return DateTimeFieldType.CLOCKHOUR_OF_DAY_TYPE;
      case 17: 
        return DateTimeFieldType.HOUR_OF_DAY_TYPE;
      case 18: 
        return DateTimeFieldType.MINUTE_OF_DAY_TYPE;
      case 19: 
        return DateTimeFieldType.MINUTE_OF_HOUR_TYPE;
      case 20: 
        return DateTimeFieldType.SECOND_OF_DAY_TYPE;
      case 21: 
        return DateTimeFieldType.SECOND_OF_MINUTE_TYPE;
      case 22: 
        return DateTimeFieldType.MILLIS_OF_DAY_TYPE;
      }
      return DateTimeFieldType.MILLIS_OF_SECOND_TYPE;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof StandardDateTimeFieldType)) {
          break;
        }
      } while (iOrdinal == iOrdinal);
      return false;
      return false;
    }
    
    public DurationFieldType getDurationType()
    {
      return iUnitType;
    }
    
    public DateTimeField getField(Chronology paramChronology)
    {
      paramChronology = DateTimeUtils.getChronology(paramChronology);
      switch (iOrdinal)
      {
      default: 
        throw new InternalError();
      case 1: 
        return paramChronology.era();
      case 2: 
        return paramChronology.yearOfEra();
      case 3: 
        return paramChronology.centuryOfEra();
      case 4: 
        return paramChronology.yearOfCentury();
      case 5: 
        return paramChronology.year();
      case 6: 
        return paramChronology.dayOfYear();
      case 7: 
        return paramChronology.monthOfYear();
      case 8: 
        return paramChronology.dayOfMonth();
      case 9: 
        return paramChronology.weekyearOfCentury();
      case 10: 
        return paramChronology.weekyear();
      case 11: 
        return paramChronology.weekOfWeekyear();
      case 12: 
        return paramChronology.dayOfWeek();
      case 13: 
        return paramChronology.halfdayOfDay();
      case 14: 
        return paramChronology.hourOfHalfday();
      case 15: 
        return paramChronology.clockhourOfHalfday();
      case 16: 
        return paramChronology.clockhourOfDay();
      case 17: 
        return paramChronology.hourOfDay();
      case 18: 
        return paramChronology.minuteOfDay();
      case 19: 
        return paramChronology.minuteOfHour();
      case 20: 
        return paramChronology.secondOfDay();
      case 21: 
        return paramChronology.secondOfMinute();
      case 22: 
        return paramChronology.millisOfDay();
      }
      return paramChronology.millisOfSecond();
    }
    
    public DurationFieldType getRangeDurationType()
    {
      return iRangeType;
    }
    
    public int hashCode()
    {
      return 1 << iOrdinal;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.DateTimeFieldType
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */