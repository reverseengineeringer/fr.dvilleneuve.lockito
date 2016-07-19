package org.joda.time.chrono;

import java.io.IOException;
import java.io.ObjectInputStream;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;

public abstract class AssembledChronology
  extends BaseChronology
{
  private static final long serialVersionUID = -6728465968995518215L;
  private final Chronology iBase;
  private transient int iBaseFlags;
  private transient DurationField iCenturies;
  private transient DateTimeField iCenturyOfEra;
  private transient DateTimeField iClockhourOfDay;
  private transient DateTimeField iClockhourOfHalfday;
  private transient DateTimeField iDayOfMonth;
  private transient DateTimeField iDayOfWeek;
  private transient DateTimeField iDayOfYear;
  private transient DurationField iDays;
  private transient DateTimeField iEra;
  private transient DurationField iEras;
  private transient DateTimeField iHalfdayOfDay;
  private transient DurationField iHalfdays;
  private transient DateTimeField iHourOfDay;
  private transient DateTimeField iHourOfHalfday;
  private transient DurationField iHours;
  private transient DurationField iMillis;
  private transient DateTimeField iMillisOfDay;
  private transient DateTimeField iMillisOfSecond;
  private transient DateTimeField iMinuteOfDay;
  private transient DateTimeField iMinuteOfHour;
  private transient DurationField iMinutes;
  private transient DateTimeField iMonthOfYear;
  private transient DurationField iMonths;
  private final Object iParam;
  private transient DateTimeField iSecondOfDay;
  private transient DateTimeField iSecondOfMinute;
  private transient DurationField iSeconds;
  private transient DateTimeField iWeekOfWeekyear;
  private transient DurationField iWeeks;
  private transient DateTimeField iWeekyear;
  private transient DateTimeField iWeekyearOfCentury;
  private transient DurationField iWeekyears;
  private transient DateTimeField iYear;
  private transient DateTimeField iYearOfCentury;
  private transient DateTimeField iYearOfEra;
  private transient DurationField iYears;
  
  protected AssembledChronology(Chronology paramChronology, Object paramObject)
  {
    iBase = paramChronology;
    iParam = paramObject;
    setFields();
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    setFields();
  }
  
  private void setFields()
  {
    int m = 0;
    Fields localFields = new Fields();
    if (iBase != null) {
      localFields.copyFieldsFrom(iBase);
    }
    assemble(localFields);
    Object localObject = millis;
    if (localObject != null)
    {
      iMillis = ((DurationField)localObject);
      localObject = seconds;
      if (localObject == null) {
        break label688;
      }
      label64:
      iSeconds = ((DurationField)localObject);
      localObject = minutes;
      if (localObject == null) {
        break label697;
      }
      label82:
      iMinutes = ((DurationField)localObject);
      localObject = hours;
      if (localObject == null) {
        break label706;
      }
      label100:
      iHours = ((DurationField)localObject);
      localObject = halfdays;
      if (localObject == null) {
        break label715;
      }
      label118:
      iHalfdays = ((DurationField)localObject);
      localObject = days;
      if (localObject == null) {
        break label724;
      }
      label136:
      iDays = ((DurationField)localObject);
      localObject = weeks;
      if (localObject == null) {
        break label733;
      }
      label154:
      iWeeks = ((DurationField)localObject);
      localObject = weekyears;
      if (localObject == null) {
        break label742;
      }
      label172:
      iWeekyears = ((DurationField)localObject);
      localObject = months;
      if (localObject == null) {
        break label751;
      }
      label190:
      iMonths = ((DurationField)localObject);
      localObject = years;
      if (localObject == null) {
        break label760;
      }
      label208:
      iYears = ((DurationField)localObject);
      localObject = centuries;
      if (localObject == null) {
        break label769;
      }
      label226:
      iCenturies = ((DurationField)localObject);
      localObject = eras;
      if (localObject == null) {
        break label778;
      }
      label244:
      iEras = ((DurationField)localObject);
      localObject = millisOfSecond;
      if (localObject == null) {
        break label787;
      }
      label262:
      iMillisOfSecond = ((DateTimeField)localObject);
      localObject = millisOfDay;
      if (localObject == null) {
        break label796;
      }
      label280:
      iMillisOfDay = ((DateTimeField)localObject);
      localObject = secondOfMinute;
      if (localObject == null) {
        break label805;
      }
      label298:
      iSecondOfMinute = ((DateTimeField)localObject);
      localObject = secondOfDay;
      if (localObject == null) {
        break label814;
      }
      label316:
      iSecondOfDay = ((DateTimeField)localObject);
      localObject = minuteOfHour;
      if (localObject == null) {
        break label823;
      }
      label334:
      iMinuteOfHour = ((DateTimeField)localObject);
      localObject = minuteOfDay;
      if (localObject == null) {
        break label832;
      }
      label352:
      iMinuteOfDay = ((DateTimeField)localObject);
      localObject = hourOfDay;
      if (localObject == null) {
        break label841;
      }
      label370:
      iHourOfDay = ((DateTimeField)localObject);
      localObject = clockhourOfDay;
      if (localObject == null) {
        break label850;
      }
      label388:
      iClockhourOfDay = ((DateTimeField)localObject);
      localObject = hourOfHalfday;
      if (localObject == null) {
        break label859;
      }
      label406:
      iHourOfHalfday = ((DateTimeField)localObject);
      localObject = clockhourOfHalfday;
      if (localObject == null) {
        break label868;
      }
      label424:
      iClockhourOfHalfday = ((DateTimeField)localObject);
      localObject = halfdayOfDay;
      if (localObject == null) {
        break label877;
      }
      label442:
      iHalfdayOfDay = ((DateTimeField)localObject);
      localObject = dayOfWeek;
      if (localObject == null) {
        break label886;
      }
      label460:
      iDayOfWeek = ((DateTimeField)localObject);
      localObject = dayOfMonth;
      if (localObject == null) {
        break label895;
      }
      label478:
      iDayOfMonth = ((DateTimeField)localObject);
      localObject = dayOfYear;
      if (localObject == null) {
        break label904;
      }
      label496:
      iDayOfYear = ((DateTimeField)localObject);
      localObject = weekOfWeekyear;
      if (localObject == null) {
        break label913;
      }
      label514:
      iWeekOfWeekyear = ((DateTimeField)localObject);
      localObject = weekyear;
      if (localObject == null) {
        break label922;
      }
      label532:
      iWeekyear = ((DateTimeField)localObject);
      localObject = weekyearOfCentury;
      if (localObject == null) {
        break label931;
      }
      label550:
      iWeekyearOfCentury = ((DateTimeField)localObject);
      localObject = monthOfYear;
      if (localObject == null) {
        break label940;
      }
      label568:
      iMonthOfYear = ((DateTimeField)localObject);
      localObject = year;
      if (localObject == null) {
        break label949;
      }
      label586:
      iYear = ((DateTimeField)localObject);
      localObject = yearOfEra;
      if (localObject == null) {
        break label958;
      }
      label604:
      iYearOfEra = ((DateTimeField)localObject);
      localObject = yearOfCentury;
      if (localObject == null) {
        break label967;
      }
      label622:
      iYearOfCentury = ((DateTimeField)localObject);
      localObject = centuryOfEra;
      if (localObject == null) {
        break label976;
      }
      label640:
      iCenturyOfEra = ((DateTimeField)localObject);
      localObject = era;
      if (localObject == null) {
        break label985;
      }
    }
    int i;
    for (;;)
    {
      iEra = ((DateTimeField)localObject);
      if (iBase != null) {
        break label994;
      }
      i = 0;
      iBaseFlags = i;
      return;
      localObject = super.millis();
      break;
      label688:
      localObject = super.seconds();
      break label64;
      label697:
      localObject = super.minutes();
      break label82;
      label706:
      localObject = super.hours();
      break label100;
      label715:
      localObject = super.halfdays();
      break label118;
      label724:
      localObject = super.days();
      break label136;
      label733:
      localObject = super.weeks();
      break label154;
      label742:
      localObject = super.weekyears();
      break label172;
      label751:
      localObject = super.months();
      break label190;
      label760:
      localObject = super.years();
      break label208;
      label769:
      localObject = super.centuries();
      break label226;
      label778:
      localObject = super.eras();
      break label244;
      label787:
      localObject = super.millisOfSecond();
      break label262;
      label796:
      localObject = super.millisOfDay();
      break label280;
      label805:
      localObject = super.secondOfMinute();
      break label298;
      label814:
      localObject = super.secondOfDay();
      break label316;
      label823:
      localObject = super.minuteOfHour();
      break label334;
      label832:
      localObject = super.minuteOfDay();
      break label352;
      label841:
      localObject = super.hourOfDay();
      break label370;
      label850:
      localObject = super.clockhourOfDay();
      break label388;
      label859:
      localObject = super.hourOfHalfday();
      break label406;
      label868:
      localObject = super.clockhourOfHalfday();
      break label424;
      label877:
      localObject = super.halfdayOfDay();
      break label442;
      label886:
      localObject = super.dayOfWeek();
      break label460;
      label895:
      localObject = super.dayOfMonth();
      break label478;
      label904:
      localObject = super.dayOfYear();
      break label496;
      label913:
      localObject = super.weekOfWeekyear();
      break label514;
      label922:
      localObject = super.weekyear();
      break label532;
      label931:
      localObject = super.weekyearOfCentury();
      break label550;
      label940:
      localObject = super.monthOfYear();
      break label568;
      label949:
      localObject = super.year();
      break label586;
      label958:
      localObject = super.yearOfEra();
      break label604;
      label967:
      localObject = super.yearOfCentury();
      break label622;
      label976:
      localObject = super.centuryOfEra();
      break label640;
      label985:
      localObject = super.era();
    }
    label994:
    if ((iHourOfDay == iBase.hourOfDay()) && (iMinuteOfHour == iBase.minuteOfHour()) && (iSecondOfMinute == iBase.secondOfMinute()) && (iMillisOfSecond == iBase.millisOfSecond()))
    {
      i = 1;
      label1052:
      if (iMillisOfDay != iBase.millisOfDay()) {
        break label1135;
      }
    }
    label1135:
    for (int j = 2;; j = 0)
    {
      int k = m;
      if (iYear == iBase.year())
      {
        k = m;
        if (iMonthOfYear == iBase.monthOfYear())
        {
          k = m;
          if (iDayOfMonth == iBase.dayOfMonth()) {
            k = 4;
          }
        }
      }
      i = i | j | k;
      break;
      i = 0;
      break label1052;
    }
  }
  
  protected abstract void assemble(Fields paramFields);
  
  public final DurationField centuries()
  {
    return iCenturies;
  }
  
  public final DateTimeField centuryOfEra()
  {
    return iCenturyOfEra;
  }
  
  public final DateTimeField clockhourOfDay()
  {
    return iClockhourOfDay;
  }
  
  public final DateTimeField clockhourOfHalfday()
  {
    return iClockhourOfHalfday;
  }
  
  public final DateTimeField dayOfMonth()
  {
    return iDayOfMonth;
  }
  
  public final DateTimeField dayOfWeek()
  {
    return iDayOfWeek;
  }
  
  public final DateTimeField dayOfYear()
  {
    return iDayOfYear;
  }
  
  public final DurationField days()
  {
    return iDays;
  }
  
  public final DateTimeField era()
  {
    return iEra;
  }
  
  public final DurationField eras()
  {
    return iEras;
  }
  
  protected final Chronology getBase()
  {
    return iBase;
  }
  
  public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IllegalArgumentException
  {
    Chronology localChronology = iBase;
    if ((localChronology != null) && ((iBaseFlags & 0x6) == 6)) {
      return localChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    return super.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
    throws IllegalArgumentException
  {
    Chronology localChronology = iBase;
    if ((localChronology != null) && ((iBaseFlags & 0x5) == 5)) {
      return localChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
    }
    return super.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
  }
  
  public long getDateTimeMillis(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IllegalArgumentException
  {
    Chronology localChronology = iBase;
    if ((localChronology != null) && ((iBaseFlags & 0x1) == 1)) {
      return localChronology.getDateTimeMillis(paramLong, paramInt1, paramInt2, paramInt3, paramInt4);
    }
    return super.getDateTimeMillis(paramLong, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  protected final Object getParam()
  {
    return iParam;
  }
  
  public DateTimeZone getZone()
  {
    Chronology localChronology = iBase;
    if (localChronology != null) {
      return localChronology.getZone();
    }
    return null;
  }
  
  public final DateTimeField halfdayOfDay()
  {
    return iHalfdayOfDay;
  }
  
  public final DurationField halfdays()
  {
    return iHalfdays;
  }
  
  public final DateTimeField hourOfDay()
  {
    return iHourOfDay;
  }
  
  public final DateTimeField hourOfHalfday()
  {
    return iHourOfHalfday;
  }
  
  public final DurationField hours()
  {
    return iHours;
  }
  
  public final DurationField millis()
  {
    return iMillis;
  }
  
  public final DateTimeField millisOfDay()
  {
    return iMillisOfDay;
  }
  
  public final DateTimeField millisOfSecond()
  {
    return iMillisOfSecond;
  }
  
  public final DateTimeField minuteOfDay()
  {
    return iMinuteOfDay;
  }
  
  public final DateTimeField minuteOfHour()
  {
    return iMinuteOfHour;
  }
  
  public final DurationField minutes()
  {
    return iMinutes;
  }
  
  public final DateTimeField monthOfYear()
  {
    return iMonthOfYear;
  }
  
  public final DurationField months()
  {
    return iMonths;
  }
  
  public final DateTimeField secondOfDay()
  {
    return iSecondOfDay;
  }
  
  public final DateTimeField secondOfMinute()
  {
    return iSecondOfMinute;
  }
  
  public final DurationField seconds()
  {
    return iSeconds;
  }
  
  public final DateTimeField weekOfWeekyear()
  {
    return iWeekOfWeekyear;
  }
  
  public final DurationField weeks()
  {
    return iWeeks;
  }
  
  public final DateTimeField weekyear()
  {
    return iWeekyear;
  }
  
  public final DateTimeField weekyearOfCentury()
  {
    return iWeekyearOfCentury;
  }
  
  public final DurationField weekyears()
  {
    return iWeekyears;
  }
  
  public final DateTimeField year()
  {
    return iYear;
  }
  
  public final DateTimeField yearOfCentury()
  {
    return iYearOfCentury;
  }
  
  public final DateTimeField yearOfEra()
  {
    return iYearOfEra;
  }
  
  public final DurationField years()
  {
    return iYears;
  }
  
  public static final class Fields
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
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.AssembledChronology
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */