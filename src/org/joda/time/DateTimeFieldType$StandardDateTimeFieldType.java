package org.joda.time;

class DateTimeFieldType$StandardDateTimeFieldType
  extends DateTimeFieldType
{
  private static final long serialVersionUID = -9937958251642L;
  private final byte iOrdinal;
  private final transient DurationFieldType iRangeType;
  private final transient DurationFieldType iUnitType;
  
  DateTimeFieldType$StandardDateTimeFieldType(String paramString, byte paramByte, DurationFieldType paramDurationFieldType1, DurationFieldType paramDurationFieldType2)
  {
    super(paramString);
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
      return DateTimeFieldType.access$000();
    case 2: 
      return DateTimeFieldType.access$100();
    case 3: 
      return DateTimeFieldType.access$200();
    case 4: 
      return DateTimeFieldType.access$300();
    case 5: 
      return DateTimeFieldType.access$400();
    case 6: 
      return DateTimeFieldType.access$500();
    case 7: 
      return DateTimeFieldType.access$600();
    case 8: 
      return DateTimeFieldType.access$700();
    case 9: 
      return DateTimeFieldType.access$800();
    case 10: 
      return DateTimeFieldType.access$900();
    case 11: 
      return DateTimeFieldType.access$1000();
    case 12: 
      return DateTimeFieldType.access$1100();
    case 13: 
      return DateTimeFieldType.access$1200();
    case 14: 
      return DateTimeFieldType.access$1300();
    case 15: 
      return DateTimeFieldType.access$1400();
    case 16: 
      return DateTimeFieldType.access$1500();
    case 17: 
      return DateTimeFieldType.access$1600();
    case 18: 
      return DateTimeFieldType.access$1700();
    case 19: 
      return DateTimeFieldType.access$1800();
    case 20: 
      return DateTimeFieldType.access$1900();
    case 21: 
      return DateTimeFieldType.access$2000();
    case 22: 
      return DateTimeFieldType.access$2100();
    }
    return DateTimeFieldType.access$2200();
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

/* Location:
 * Qualified Name:     org.joda.time.DateTimeFieldType.StandardDateTimeFieldType
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */