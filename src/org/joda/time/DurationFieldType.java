package org.joda.time;

import java.io.Serializable;

public abstract class DurationFieldType
  implements Serializable
{
  static final byte CENTURIES = 2;
  static final DurationFieldType CENTURIES_TYPE;
  static final byte DAYS = 7;
  static final DurationFieldType DAYS_TYPE;
  static final byte ERAS = 1;
  static final DurationFieldType ERAS_TYPE = new StandardDurationFieldType("eras", (byte)1);
  static final byte HALFDAYS = 8;
  static final DurationFieldType HALFDAYS_TYPE;
  static final byte HOURS = 9;
  static final DurationFieldType HOURS_TYPE;
  static final byte MILLIS = 12;
  static final DurationFieldType MILLIS_TYPE = new StandardDurationFieldType("millis", (byte)12);
  static final byte MINUTES = 10;
  static final DurationFieldType MINUTES_TYPE;
  static final byte MONTHS = 5;
  static final DurationFieldType MONTHS_TYPE;
  static final byte SECONDS = 11;
  static final DurationFieldType SECONDS_TYPE;
  static final byte WEEKS = 6;
  static final DurationFieldType WEEKS_TYPE;
  static final byte WEEKYEARS = 3;
  static final DurationFieldType WEEKYEARS_TYPE;
  static final byte YEARS = 4;
  static final DurationFieldType YEARS_TYPE;
  private static final long serialVersionUID = 8765135187319L;
  private final String iName;
  
  static
  {
    CENTURIES_TYPE = new StandardDurationFieldType("centuries", (byte)2);
    WEEKYEARS_TYPE = new StandardDurationFieldType("weekyears", (byte)3);
    YEARS_TYPE = new StandardDurationFieldType("years", (byte)4);
    MONTHS_TYPE = new StandardDurationFieldType("months", (byte)5);
    WEEKS_TYPE = new StandardDurationFieldType("weeks", (byte)6);
    DAYS_TYPE = new StandardDurationFieldType("days", (byte)7);
    HALFDAYS_TYPE = new StandardDurationFieldType("halfdays", (byte)8);
    HOURS_TYPE = new StandardDurationFieldType("hours", (byte)9);
    MINUTES_TYPE = new StandardDurationFieldType("minutes", (byte)10);
    SECONDS_TYPE = new StandardDurationFieldType("seconds", (byte)11);
  }
  
  protected DurationFieldType(String paramString)
  {
    iName = paramString;
  }
  
  public static DurationFieldType centuries()
  {
    return CENTURIES_TYPE;
  }
  
  public static DurationFieldType days()
  {
    return DAYS_TYPE;
  }
  
  public static DurationFieldType eras()
  {
    return ERAS_TYPE;
  }
  
  public static DurationFieldType halfdays()
  {
    return HALFDAYS_TYPE;
  }
  
  public static DurationFieldType hours()
  {
    return HOURS_TYPE;
  }
  
  public static DurationFieldType millis()
  {
    return MILLIS_TYPE;
  }
  
  public static DurationFieldType minutes()
  {
    return MINUTES_TYPE;
  }
  
  public static DurationFieldType months()
  {
    return MONTHS_TYPE;
  }
  
  public static DurationFieldType seconds()
  {
    return SECONDS_TYPE;
  }
  
  public static DurationFieldType weeks()
  {
    return WEEKS_TYPE;
  }
  
  public static DurationFieldType weekyears()
  {
    return WEEKYEARS_TYPE;
  }
  
  public static DurationFieldType years()
  {
    return YEARS_TYPE;
  }
  
  public abstract DurationField getField(Chronology paramChronology);
  
  public String getName()
  {
    return iName;
  }
  
  public boolean isSupported(Chronology paramChronology)
  {
    return getField(paramChronology).isSupported();
  }
  
  public String toString()
  {
    return getName();
  }
  
  private static class StandardDurationFieldType
    extends DurationFieldType
  {
    private static final long serialVersionUID = 31156755687123L;
    private final byte iOrdinal;
    
    StandardDurationFieldType(String paramString, byte paramByte)
    {
      super();
      iOrdinal = paramByte;
    }
    
    private Object readResolve()
    {
      switch (iOrdinal)
      {
      default: 
        return this;
      case 1: 
        return ERAS_TYPE;
      case 2: 
        return CENTURIES_TYPE;
      case 3: 
        return WEEKYEARS_TYPE;
      case 4: 
        return YEARS_TYPE;
      case 5: 
        return MONTHS_TYPE;
      case 6: 
        return WEEKS_TYPE;
      case 7: 
        return DAYS_TYPE;
      case 8: 
        return HALFDAYS_TYPE;
      case 9: 
        return HOURS_TYPE;
      case 10: 
        return MINUTES_TYPE;
      case 11: 
        return SECONDS_TYPE;
      }
      return MILLIS_TYPE;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof StandardDurationFieldType)) {
          break;
        }
      } while (iOrdinal == iOrdinal);
      return false;
      return false;
    }
    
    public DurationField getField(Chronology paramChronology)
    {
      paramChronology = DateTimeUtils.getChronology(paramChronology);
      switch (iOrdinal)
      {
      default: 
        throw new InternalError();
      case 1: 
        return paramChronology.eras();
      case 2: 
        return paramChronology.centuries();
      case 3: 
        return paramChronology.weekyears();
      case 4: 
        return paramChronology.years();
      case 5: 
        return paramChronology.months();
      case 6: 
        return paramChronology.weeks();
      case 7: 
        return paramChronology.days();
      case 8: 
        return paramChronology.halfdays();
      case 9: 
        return paramChronology.hours();
      case 10: 
        return paramChronology.minutes();
      case 11: 
        return paramChronology.seconds();
      }
      return paramChronology.millis();
    }
    
    public int hashCode()
    {
      return 1 << iOrdinal;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.DurationFieldType
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */