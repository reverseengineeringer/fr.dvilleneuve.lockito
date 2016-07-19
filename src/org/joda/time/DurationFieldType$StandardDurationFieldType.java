package org.joda.time;

class DurationFieldType$StandardDurationFieldType
  extends DurationFieldType
{
  private static final long serialVersionUID = 31156755687123L;
  private final byte iOrdinal;
  
  DurationFieldType$StandardDurationFieldType(String paramString, byte paramByte)
  {
    super(paramString);
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

/* Location:
 * Qualified Name:     org.joda.time.DurationFieldType.StandardDurationFieldType
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */