package org.joda.time;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.joda.time.base.BasePartial;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.AbstractPartialFieldProperty;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

@Deprecated
public final class TimeOfDay
  extends BasePartial
  implements ReadablePartial, Serializable
{
  private static final DateTimeFieldType[] FIELD_TYPES = { DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond() };
  public static final int HOUR_OF_DAY = 0;
  public static final TimeOfDay MIDNIGHT = new TimeOfDay(0, 0, 0, 0);
  public static final int MILLIS_OF_SECOND = 3;
  public static final int MINUTE_OF_HOUR = 1;
  public static final int SECOND_OF_MINUTE = 2;
  private static final long serialVersionUID = 3633353405803318660L;
  
  public TimeOfDay() {}
  
  public TimeOfDay(int paramInt1, int paramInt2)
  {
    this(paramInt1, paramInt2, 0, 0, null);
  }
  
  public TimeOfDay(int paramInt1, int paramInt2, int paramInt3)
  {
    this(paramInt1, paramInt2, paramInt3, 0, null);
  }
  
  public TimeOfDay(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this(paramInt1, paramInt2, paramInt3, paramInt4, null);
  }
  
  public TimeOfDay(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Chronology paramChronology)
  {
    super(new int[] { paramInt1, paramInt2, paramInt3, paramInt4 }, paramChronology);
  }
  
  public TimeOfDay(int paramInt1, int paramInt2, int paramInt3, Chronology paramChronology)
  {
    this(paramInt1, paramInt2, paramInt3, 0, paramChronology);
  }
  
  public TimeOfDay(int paramInt1, int paramInt2, Chronology paramChronology)
  {
    this(paramInt1, paramInt2, 0, 0, paramChronology);
  }
  
  public TimeOfDay(long paramLong)
  {
    super(paramLong);
  }
  
  public TimeOfDay(long paramLong, Chronology paramChronology)
  {
    super(paramLong, paramChronology);
  }
  
  public TimeOfDay(Object paramObject)
  {
    super(paramObject, null, ISODateTimeFormat.timeParser());
  }
  
  public TimeOfDay(Object paramObject, Chronology paramChronology)
  {
    super(paramObject, DateTimeUtils.getChronology(paramChronology), ISODateTimeFormat.timeParser());
  }
  
  public TimeOfDay(Chronology paramChronology)
  {
    super(paramChronology);
  }
  
  public TimeOfDay(DateTimeZone paramDateTimeZone)
  {
    super(ISOChronology.getInstance(paramDateTimeZone));
  }
  
  TimeOfDay(TimeOfDay paramTimeOfDay, Chronology paramChronology)
  {
    super(paramTimeOfDay, paramChronology);
  }
  
  TimeOfDay(TimeOfDay paramTimeOfDay, int[] paramArrayOfInt)
  {
    super(paramTimeOfDay, paramArrayOfInt);
  }
  
  public static TimeOfDay fromCalendarFields(Calendar paramCalendar)
  {
    if (paramCalendar == null) {
      throw new IllegalArgumentException("The calendar must not be null");
    }
    return new TimeOfDay(paramCalendar.get(11), paramCalendar.get(12), paramCalendar.get(13), paramCalendar.get(14));
  }
  
  public static TimeOfDay fromDateFields(Date paramDate)
  {
    if (paramDate == null) {
      throw new IllegalArgumentException("The date must not be null");
    }
    return new TimeOfDay(paramDate.getHours(), paramDate.getMinutes(), paramDate.getSeconds(), ((int)(paramDate.getTime() % 1000L) + 1000) % 1000);
  }
  
  public static TimeOfDay fromMillisOfDay(long paramLong)
  {
    return fromMillisOfDay(paramLong, null);
  }
  
  public static TimeOfDay fromMillisOfDay(long paramLong, Chronology paramChronology)
  {
    return new TimeOfDay(paramLong, DateTimeUtils.getChronology(paramChronology).withUTC());
  }
  
  protected DateTimeField getField(int paramInt, Chronology paramChronology)
  {
    switch (paramInt)
    {
    default: 
      throw new IndexOutOfBoundsException("Invalid index: " + paramInt);
    case 0: 
      return paramChronology.hourOfDay();
    case 1: 
      return paramChronology.minuteOfHour();
    case 2: 
      return paramChronology.secondOfMinute();
    }
    return paramChronology.millisOfSecond();
  }
  
  public DateTimeFieldType getFieldType(int paramInt)
  {
    return FIELD_TYPES[paramInt];
  }
  
  public DateTimeFieldType[] getFieldTypes()
  {
    return (DateTimeFieldType[])FIELD_TYPES.clone();
  }
  
  public int getHourOfDay()
  {
    return getValue(0);
  }
  
  public int getMillisOfSecond()
  {
    return getValue(3);
  }
  
  public int getMinuteOfHour()
  {
    return getValue(1);
  }
  
  public int getSecondOfMinute()
  {
    return getValue(2);
  }
  
  public Property hourOfDay()
  {
    return new Property(this, 0);
  }
  
  public Property millisOfSecond()
  {
    return new Property(this, 3);
  }
  
  public TimeOfDay minus(ReadablePeriod paramReadablePeriod)
  {
    return withPeriodAdded(paramReadablePeriod, -1);
  }
  
  public TimeOfDay minusHours(int paramInt)
  {
    return withFieldAdded(DurationFieldType.hours(), FieldUtils.safeNegate(paramInt));
  }
  
  public TimeOfDay minusMillis(int paramInt)
  {
    return withFieldAdded(DurationFieldType.millis(), FieldUtils.safeNegate(paramInt));
  }
  
  public TimeOfDay minusMinutes(int paramInt)
  {
    return withFieldAdded(DurationFieldType.minutes(), FieldUtils.safeNegate(paramInt));
  }
  
  public TimeOfDay minusSeconds(int paramInt)
  {
    return withFieldAdded(DurationFieldType.seconds(), FieldUtils.safeNegate(paramInt));
  }
  
  public Property minuteOfHour()
  {
    return new Property(this, 1);
  }
  
  public TimeOfDay plus(ReadablePeriod paramReadablePeriod)
  {
    return withPeriodAdded(paramReadablePeriod, 1);
  }
  
  public TimeOfDay plusHours(int paramInt)
  {
    return withFieldAdded(DurationFieldType.hours(), paramInt);
  }
  
  public TimeOfDay plusMillis(int paramInt)
  {
    return withFieldAdded(DurationFieldType.millis(), paramInt);
  }
  
  public TimeOfDay plusMinutes(int paramInt)
  {
    return withFieldAdded(DurationFieldType.minutes(), paramInt);
  }
  
  public TimeOfDay plusSeconds(int paramInt)
  {
    return withFieldAdded(DurationFieldType.seconds(), paramInt);
  }
  
  public Property property(DateTimeFieldType paramDateTimeFieldType)
  {
    return new Property(this, indexOfSupported(paramDateTimeFieldType));
  }
  
  public Property secondOfMinute()
  {
    return new Property(this, 2);
  }
  
  public int size()
  {
    return 4;
  }
  
  public DateTime toDateTimeToday()
  {
    return toDateTimeToday(null);
  }
  
  public DateTime toDateTimeToday(DateTimeZone paramDateTimeZone)
  {
    paramDateTimeZone = getChronology().withZone(paramDateTimeZone);
    return new DateTime(paramDateTimeZone.set(this, DateTimeUtils.currentTimeMillis()), paramDateTimeZone);
  }
  
  public LocalTime toLocalTime()
  {
    return new LocalTime(getHourOfDay(), getMinuteOfHour(), getSecondOfMinute(), getMillisOfSecond(), getChronology());
  }
  
  public String toString()
  {
    return ISODateTimeFormat.tTime().print(this);
  }
  
  public TimeOfDay withChronologyRetainFields(Chronology paramChronology)
  {
    paramChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
    if (paramChronology == getChronology()) {
      return this;
    }
    TimeOfDay localTimeOfDay = new TimeOfDay(this, paramChronology);
    paramChronology.validate(localTimeOfDay, getValues());
    return localTimeOfDay;
  }
  
  public TimeOfDay withField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
  {
    int i = indexOfSupported(paramDateTimeFieldType);
    if (paramInt == getValue(i)) {
      return this;
    }
    paramDateTimeFieldType = getValues();
    return new TimeOfDay(this, getField(i).set(this, i, paramDateTimeFieldType, paramInt));
  }
  
  public TimeOfDay withFieldAdded(DurationFieldType paramDurationFieldType, int paramInt)
  {
    int i = indexOfSupported(paramDurationFieldType);
    if (paramInt == 0) {
      return this;
    }
    paramDurationFieldType = getValues();
    return new TimeOfDay(this, getField(i).addWrapPartial(this, i, paramDurationFieldType, paramInt));
  }
  
  public TimeOfDay withHourOfDay(int paramInt)
  {
    int[] arrayOfInt = getValues();
    return new TimeOfDay(this, getChronology().hourOfDay().set(this, 0, arrayOfInt, paramInt));
  }
  
  public TimeOfDay withMillisOfSecond(int paramInt)
  {
    int[] arrayOfInt = getValues();
    return new TimeOfDay(this, getChronology().millisOfSecond().set(this, 3, arrayOfInt, paramInt));
  }
  
  public TimeOfDay withMinuteOfHour(int paramInt)
  {
    int[] arrayOfInt = getValues();
    return new TimeOfDay(this, getChronology().minuteOfHour().set(this, 1, arrayOfInt, paramInt));
  }
  
  public TimeOfDay withPeriodAdded(ReadablePeriod paramReadablePeriod, int paramInt)
  {
    if ((paramReadablePeriod == null) || (paramInt == 0)) {
      return this;
    }
    Object localObject1 = getValues();
    int i = 0;
    while (i < paramReadablePeriod.size())
    {
      int j = indexOf(paramReadablePeriod.getFieldType(i));
      Object localObject2 = localObject1;
      if (j >= 0) {
        localObject2 = getField(j).addWrapPartial(this, j, (int[])localObject1, FieldUtils.safeMultiply(paramReadablePeriod.getValue(i), paramInt));
      }
      i += 1;
      localObject1 = localObject2;
    }
    return new TimeOfDay(this, (int[])localObject1);
  }
  
  public TimeOfDay withSecondOfMinute(int paramInt)
  {
    int[] arrayOfInt = getValues();
    return new TimeOfDay(this, getChronology().secondOfMinute().set(this, 2, arrayOfInt, paramInt));
  }
  
  @Deprecated
  public static class Property
    extends AbstractPartialFieldProperty
    implements Serializable
  {
    private static final long serialVersionUID = 5598459141741063833L;
    private final int iFieldIndex;
    private final TimeOfDay iTimeOfDay;
    
    Property(TimeOfDay paramTimeOfDay, int paramInt)
    {
      iTimeOfDay = paramTimeOfDay;
      iFieldIndex = paramInt;
    }
    
    public TimeOfDay addNoWrapToCopy(int paramInt)
    {
      int[] arrayOfInt = iTimeOfDay.getValues();
      arrayOfInt = getField().add(iTimeOfDay, iFieldIndex, arrayOfInt, paramInt);
      return new TimeOfDay(iTimeOfDay, arrayOfInt);
    }
    
    public TimeOfDay addToCopy(int paramInt)
    {
      int[] arrayOfInt = iTimeOfDay.getValues();
      arrayOfInt = getField().addWrapPartial(iTimeOfDay, iFieldIndex, arrayOfInt, paramInt);
      return new TimeOfDay(iTimeOfDay, arrayOfInt);
    }
    
    public TimeOfDay addWrapFieldToCopy(int paramInt)
    {
      int[] arrayOfInt = iTimeOfDay.getValues();
      arrayOfInt = getField().addWrapField(iTimeOfDay, iFieldIndex, arrayOfInt, paramInt);
      return new TimeOfDay(iTimeOfDay, arrayOfInt);
    }
    
    public int get()
    {
      return iTimeOfDay.getValue(iFieldIndex);
    }
    
    public DateTimeField getField()
    {
      return iTimeOfDay.getField(iFieldIndex);
    }
    
    protected ReadablePartial getReadablePartial()
    {
      return iTimeOfDay;
    }
    
    public TimeOfDay getTimeOfDay()
    {
      return iTimeOfDay;
    }
    
    public TimeOfDay setCopy(int paramInt)
    {
      int[] arrayOfInt = iTimeOfDay.getValues();
      arrayOfInt = getField().set(iTimeOfDay, iFieldIndex, arrayOfInt, paramInt);
      return new TimeOfDay(iTimeOfDay, arrayOfInt);
    }
    
    public TimeOfDay setCopy(String paramString)
    {
      return setCopy(paramString, null);
    }
    
    public TimeOfDay setCopy(String paramString, Locale paramLocale)
    {
      int[] arrayOfInt = iTimeOfDay.getValues();
      paramString = getField().set(iTimeOfDay, iFieldIndex, arrayOfInt, paramString, paramLocale);
      return new TimeOfDay(iTimeOfDay, paramString);
    }
    
    public TimeOfDay withMaximumValue()
    {
      return setCopy(getMaximumValue());
    }
    
    public TimeOfDay withMinimumValue()
    {
      return setCopy(getMinimumValue());
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.TimeOfDay
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */