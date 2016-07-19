package org.joda.time.chrono;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.field.LenientDateTimeField;

public final class LenientChronology
  extends AssembledChronology
{
  private static final long serialVersionUID = -3148237568046877177L;
  private transient Chronology iWithUTC;
  
  private LenientChronology(Chronology paramChronology)
  {
    super(paramChronology, null);
  }
  
  private final DateTimeField convertField(DateTimeField paramDateTimeField)
  {
    return LenientDateTimeField.getInstance(paramDateTimeField, getBase());
  }
  
  public static LenientChronology getInstance(Chronology paramChronology)
  {
    if (paramChronology == null) {
      throw new IllegalArgumentException("Must supply a chronology");
    }
    return new LenientChronology(paramChronology);
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
    if (!(paramObject instanceof LenientChronology)) {
      return false;
    }
    paramObject = (LenientChronology)paramObject;
    return getBase().equals(((LenientChronology)paramObject).getBase());
  }
  
  public int hashCode()
  {
    return 236548278 + getBase().hashCode() * 7;
  }
  
  public String toString()
  {
    return "LenientChronology[" + getBase().toString() + ']';
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
 * Qualified Name:     org.joda.time.chrono.LenientChronology
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */