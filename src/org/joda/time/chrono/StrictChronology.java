package org.joda.time.chrono;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.field.StrictDateTimeField;

public final class StrictChronology
  extends AssembledChronology
{
  private static final long serialVersionUID = 6633006628097111960L;
  private transient Chronology iWithUTC;
  
  private StrictChronology(Chronology paramChronology)
  {
    super(paramChronology, null);
  }
  
  private static final DateTimeField convertField(DateTimeField paramDateTimeField)
  {
    return StrictDateTimeField.getInstance(paramDateTimeField);
  }
  
  public static StrictChronology getInstance(Chronology paramChronology)
  {
    if (paramChronology == null) {
      throw new IllegalArgumentException("Must supply a chronology");
    }
    return new StrictChronology(paramChronology);
  }
  
  protected void assemble(AssembledChronology.Fields paramFields)
  {
    year = convertField(year);
    yearOfEra = convertField(yearOfEra);
    yearOfCentury = convertField(yearOfCentury);
    centuryOfEra = convertField(centuryOfEra);
    era = convertField(era);
    dayOfWeek = convertField(dayOfWeek);
    dayOfMonth = convertField(dayOfMonth);
    dayOfYear = convertField(dayOfYear);
    monthOfYear = convertField(monthOfYear);
    weekOfWeekyear = convertField(weekOfWeekyear);
    weekyear = convertField(weekyear);
    weekyearOfCentury = convertField(weekyearOfCentury);
    millisOfSecond = convertField(millisOfSecond);
    millisOfDay = convertField(millisOfDay);
    secondOfMinute = convertField(secondOfMinute);
    secondOfDay = convertField(secondOfDay);
    minuteOfHour = convertField(minuteOfHour);
    minuteOfDay = convertField(minuteOfDay);
    hourOfDay = convertField(hourOfDay);
    hourOfHalfday = convertField(hourOfHalfday);
    clockhourOfDay = convertField(clockhourOfDay);
    clockhourOfHalfday = convertField(clockhourOfHalfday);
    halfdayOfDay = convertField(halfdayOfDay);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof StrictChronology)) {
      return false;
    }
    paramObject = (StrictChronology)paramObject;
    return getBase().equals(((StrictChronology)paramObject).getBase());
  }
  
  public int hashCode()
  {
    return 352831696 + getBase().hashCode() * 7;
  }
  
  public String toString()
  {
    return "StrictChronology[" + getBase().toString() + ']';
  }
  
  public Chronology withUTC()
  {
    if (iWithUTC == null) {
      if (getZone() != DateTimeZone.UTC) {
        break label27;
      }
    }
    label27:
    for (iWithUTC = this;; iWithUTC = getInstance(getBase().withUTC())) {
      return iWithUTC;
    }
  }
  
  public Chronology withZone(DateTimeZone paramDateTimeZone)
  {
    DateTimeZone localDateTimeZone = paramDateTimeZone;
    if (paramDateTimeZone == null) {
      localDateTimeZone = DateTimeZone.getDefault();
    }
    if (localDateTimeZone == DateTimeZone.UTC) {
      paramDateTimeZone = withUTC();
    }
    do
    {
      return paramDateTimeZone;
      paramDateTimeZone = this;
    } while (localDateTimeZone == getZone());
    return getInstance(getBase().withZone(localDateTimeZone));
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.StrictChronology
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */