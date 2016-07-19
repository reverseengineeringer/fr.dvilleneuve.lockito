package org.joda.time.chrono;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DurationField;

public final class AssembledChronology$Fields
{
  public DurationField centuries;
  public DateTimeField centuryOfEra;
  public DateTimeField clockhourOfDay;
  public DateTimeField clockhourOfHalfday;
  public DateTimeField dayOfMonth;
  public DateTimeField dayOfWeek;
  public DateTimeField dayOfYear;
  public DurationField days;
  public DateTimeField era;
  public DurationField eras;
  public DateTimeField halfdayOfDay;
  public DurationField halfdays;
  public DateTimeField hourOfDay;
  public DateTimeField hourOfHalfday;
  public DurationField hours;
  public DurationField millis;
  public DateTimeField millisOfDay;
  public DateTimeField millisOfSecond;
  public DateTimeField minuteOfDay;
  public DateTimeField minuteOfHour;
  public DurationField minutes;
  public DateTimeField monthOfYear;
  public DurationField months;
  public DateTimeField secondOfDay;
  public DateTimeField secondOfMinute;
  public DurationField seconds;
  public DateTimeField weekOfWeekyear;
  public DurationField weeks;
  public DateTimeField weekyear;
  public DateTimeField weekyearOfCentury;
  public DurationField weekyears;
  public DateTimeField year;
  public DateTimeField yearOfCentury;
  public DateTimeField yearOfEra;
  public DurationField years;
  
  private static boolean isSupported(DateTimeField paramDateTimeField)
  {
    if (paramDateTimeField == null) {
      return false;
    }
    return paramDateTimeField.isSupported();
  }
  
  private static boolean isSupported(DurationField paramDurationField)
  {
    if (paramDurationField == null) {
      return false;
    }
    return paramDurationField.isSupported();
  }
  
  public void copyFieldsFrom(Chronology paramChronology)
  {
    Object localObject = paramChronology.millis();
    if (isSupported((DurationField)localObject)) {
      millis = ((DurationField)localObject);
    }
    localObject = paramChronology.seconds();
    if (isSupported((DurationField)localObject)) {
      seconds = ((DurationField)localObject);
    }
    localObject = paramChronology.minutes();
    if (isSupported((DurationField)localObject)) {
      minutes = ((DurationField)localObject);
    }
    localObject = paramChronology.hours();
    if (isSupported((DurationField)localObject)) {
      hours = ((DurationField)localObject);
    }
    localObject = paramChronology.halfdays();
    if (isSupported((DurationField)localObject)) {
      halfdays = ((DurationField)localObject);
    }
    localObject = paramChronology.days();
    if (isSupported((DurationField)localObject)) {
      days = ((DurationField)localObject);
    }
    localObject = paramChronology.weeks();
    if (isSupported((DurationField)localObject)) {
      weeks = ((DurationField)localObject);
    }
    localObject = paramChronology.weekyears();
    if (isSupported((DurationField)localObject)) {
      weekyears = ((DurationField)localObject);
    }
    localObject = paramChronology.months();
    if (isSupported((DurationField)localObject)) {
      months = ((DurationField)localObject);
    }
    localObject = paramChronology.years();
    if (isSupported((DurationField)localObject)) {
      years = ((DurationField)localObject);
    }
    localObject = paramChronology.centuries();
    if (isSupported((DurationField)localObject)) {
      centuries = ((DurationField)localObject);
    }
    localObject = paramChronology.eras();
    if (isSupported((DurationField)localObject)) {
      eras = ((DurationField)localObject);
    }
    localObject = paramChronology.millisOfSecond();
    if (isSupported((DateTimeField)localObject)) {
      millisOfSecond = ((DateTimeField)localObject);
    }
    localObject = paramChronology.millisOfDay();
    if (isSupported((DateTimeField)localObject)) {
      millisOfDay = ((DateTimeField)localObject);
    }
    localObject = paramChronology.secondOfMinute();
    if (isSupported((DateTimeField)localObject)) {
      secondOfMinute = ((DateTimeField)localObject);
    }
    localObject = paramChronology.secondOfDay();
    if (isSupported((DateTimeField)localObject)) {
      secondOfDay = ((DateTimeField)localObject);
    }
    localObject = paramChronology.minuteOfHour();
    if (isSupported((DateTimeField)localObject)) {
      minuteOfHour = ((DateTimeField)localObject);
    }
    localObject = paramChronology.minuteOfDay();
    if (isSupported((DateTimeField)localObject)) {
      minuteOfDay = ((DateTimeField)localObject);
    }
    localObject = paramChronology.hourOfDay();
    if (isSupported((DateTimeField)localObject)) {
      hourOfDay = ((DateTimeField)localObject);
    }
    localObject = paramChronology.clockhourOfDay();
    if (isSupported((DateTimeField)localObject)) {
      clockhourOfDay = ((DateTimeField)localObject);
    }
    localObject = paramChronology.hourOfHalfday();
    if (isSupported((DateTimeField)localObject)) {
      hourOfHalfday = ((DateTimeField)localObject);
    }
    localObject = paramChronology.clockhourOfHalfday();
    if (isSupported((DateTimeField)localObject)) {
      clockhourOfHalfday = ((DateTimeField)localObject);
    }
    localObject = paramChronology.halfdayOfDay();
    if (isSupported((DateTimeField)localObject)) {
      halfdayOfDay = ((DateTimeField)localObject);
    }
    localObject = paramChronology.dayOfWeek();
    if (isSupported((DateTimeField)localObject)) {
      dayOfWeek = ((DateTimeField)localObject);
    }
    localObject = paramChronology.dayOfMonth();
    if (isSupported((DateTimeField)localObject)) {
      dayOfMonth = ((DateTimeField)localObject);
    }
    localObject = paramChronology.dayOfYear();
    if (isSupported((DateTimeField)localObject)) {
      dayOfYear = ((DateTimeField)localObject);
    }
    localObject = paramChronology.weekOfWeekyear();
    if (isSupported((DateTimeField)localObject)) {
      weekOfWeekyear = ((DateTimeField)localObject);
    }
    localObject = paramChronology.weekyear();
    if (isSupported((DateTimeField)localObject)) {
      weekyear = ((DateTimeField)localObject);
    }
    localObject = paramChronology.weekyearOfCentury();
    if (isSupported((DateTimeField)localObject)) {
      weekyearOfCentury = ((DateTimeField)localObject);
    }
    localObject = paramChronology.monthOfYear();
    if (isSupported((DateTimeField)localObject)) {
      monthOfYear = ((DateTimeField)localObject);
    }
    localObject = paramChronology.year();
    if (isSupported((DateTimeField)localObject)) {
      year = ((DateTimeField)localObject);
    }
    localObject = paramChronology.yearOfEra();
    if (isSupported((DateTimeField)localObject)) {
      yearOfEra = ((DateTimeField)localObject);
    }
    localObject = paramChronology.yearOfCentury();
    if (isSupported((DateTimeField)localObject)) {
      yearOfCentury = ((DateTimeField)localObject);
    }
    localObject = paramChronology.centuryOfEra();
    if (isSupported((DateTimeField)localObject)) {
      centuryOfEra = ((DateTimeField)localObject);
    }
    paramChronology = paramChronology.era();
    if (isSupported(paramChronology)) {
      era = paramChronology;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.AssembledChronology.Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */