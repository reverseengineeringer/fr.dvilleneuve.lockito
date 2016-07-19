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
public final class YearMonthDay
  extends BasePartial
  implements ReadablePartial, Serializable
{
  public static final int DAY_OF_MONTH = 2;
  private static final DateTimeFieldType[] FIELD_TYPES = { DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth() };
  public static final int MONTH_OF_YEAR = 1;
  public static final int YEAR = 0;
  private static final long serialVersionUID = 797544782896179L;
  
  public YearMonthDay() {}
  
  public YearMonthDay(int paramInt1, int paramInt2, int paramInt3)
  {
    this(paramInt1, paramInt2, paramInt3, null);
  }
  
  public YearMonthDay(int paramInt1, int paramInt2, int paramInt3, Chronology paramChronology)
  {
    super(new int[] { paramInt1, paramInt2, paramInt3 }, paramChronology);
  }
  
  public YearMonthDay(long paramLong)
  {
    super(paramLong);
  }
  
  public YearMonthDay(long paramLong, Chronology paramChronology)
  {
    super(paramLong, paramChronology);
  }
  
  public YearMonthDay(Object paramObject)
  {
    super(paramObject, null, ISODateTimeFormat.dateOptionalTimeParser());
  }
  
  public YearMonthDay(Object paramObject, Chronology paramChronology)
  {
    super(paramObject, DateTimeUtils.getChronology(paramChronology), ISODateTimeFormat.dateOptionalTimeParser());
  }
  
  public YearMonthDay(Chronology paramChronology)
  {
    super(paramChronology);
  }
  
  public YearMonthDay(DateTimeZone paramDateTimeZone)
  {
    super(ISOChronology.getInstance(paramDateTimeZone));
  }
  
  YearMonthDay(YearMonthDay paramYearMonthDay, Chronology paramChronology)
  {
    super(paramYearMonthDay, paramChronology);
  }
  
  YearMonthDay(YearMonthDay paramYearMonthDay, int[] paramArrayOfInt)
  {
    super(paramYearMonthDay, paramArrayOfInt);
  }
  
  public static YearMonthDay fromCalendarFields(Calendar paramCalendar)
  {
    if (paramCalendar == null) {
      throw new IllegalArgumentException("The calendar must not be null");
    }
    return new YearMonthDay(paramCalendar.get(1), paramCalendar.get(2) + 1, paramCalendar.get(5));
  }
  
  public static YearMonthDay fromDateFields(Date paramDate)
  {
    if (paramDate == null) {
      throw new IllegalArgumentException("The date must not be null");
    }
    return new YearMonthDay(paramDate.getYear() + 1900, paramDate.getMonth() + 1, paramDate.getDate());
  }
  
  public Property dayOfMonth()
  {
    return new Property(this, 2);
  }
  
  public int getDayOfMonth()
  {
    return getValue(2);
  }
  
  protected DateTimeField getField(int paramInt, Chronology paramChronology)
  {
    switch (paramInt)
    {
    default: 
      throw new IndexOutOfBoundsException("Invalid index: " + paramInt);
    case 0: 
      return paramChronology.year();
    case 1: 
      return paramChronology.monthOfYear();
    }
    return paramChronology.dayOfMonth();
  }
  
  public DateTimeFieldType getFieldType(int paramInt)
  {
    return FIELD_TYPES[paramInt];
  }
  
  public DateTimeFieldType[] getFieldTypes()
  {
    return (DateTimeFieldType[])FIELD_TYPES.clone();
  }
  
  public int getMonthOfYear()
  {
    return getValue(1);
  }
  
  public int getYear()
  {
    return getValue(0);
  }
  
  public YearMonthDay minus(ReadablePeriod paramReadablePeriod)
  {
    return withPeriodAdded(paramReadablePeriod, -1);
  }
  
  public YearMonthDay minusDays(int paramInt)
  {
    return withFieldAdded(DurationFieldType.days(), FieldUtils.safeNegate(paramInt));
  }
  
  public YearMonthDay minusMonths(int paramInt)
  {
    return withFieldAdded(DurationFieldType.months(), FieldUtils.safeNegate(paramInt));
  }
  
  public YearMonthDay minusYears(int paramInt)
  {
    return withFieldAdded(DurationFieldType.years(), FieldUtils.safeNegate(paramInt));
  }
  
  public Property monthOfYear()
  {
    return new Property(this, 1);
  }
  
  public YearMonthDay plus(ReadablePeriod paramReadablePeriod)
  {
    return withPeriodAdded(paramReadablePeriod, 1);
  }
  
  public YearMonthDay plusDays(int paramInt)
  {
    return withFieldAdded(DurationFieldType.days(), paramInt);
  }
  
  public YearMonthDay plusMonths(int paramInt)
  {
    return withFieldAdded(DurationFieldType.months(), paramInt);
  }
  
  public YearMonthDay plusYears(int paramInt)
  {
    return withFieldAdded(DurationFieldType.years(), paramInt);
  }
  
  public Property property(DateTimeFieldType paramDateTimeFieldType)
  {
    return new Property(this, indexOfSupported(paramDateTimeFieldType));
  }
  
  public int size()
  {
    return 3;
  }
  
  public DateMidnight toDateMidnight()
  {
    return toDateMidnight(null);
  }
  
  public DateMidnight toDateMidnight(DateTimeZone paramDateTimeZone)
  {
    paramDateTimeZone = getChronology().withZone(paramDateTimeZone);
    return new DateMidnight(getYear(), getMonthOfYear(), getDayOfMonth(), paramDateTimeZone);
  }
  
  public DateTime toDateTime(TimeOfDay paramTimeOfDay)
  {
    return toDateTime(paramTimeOfDay, null);
  }
  
  public DateTime toDateTime(TimeOfDay paramTimeOfDay, DateTimeZone paramDateTimeZone)
  {
    paramDateTimeZone = getChronology().withZone(paramDateTimeZone);
    long l2 = paramDateTimeZone.set(this, DateTimeUtils.currentTimeMillis());
    long l1 = l2;
    if (paramTimeOfDay != null) {
      l1 = paramDateTimeZone.set(paramTimeOfDay, l2);
    }
    return new DateTime(l1, paramDateTimeZone);
  }
  
  public DateTime toDateTimeAtCurrentTime()
  {
    return toDateTimeAtCurrentTime(null);
  }
  
  public DateTime toDateTimeAtCurrentTime(DateTimeZone paramDateTimeZone)
  {
    paramDateTimeZone = getChronology().withZone(paramDateTimeZone);
    return new DateTime(paramDateTimeZone.set(this, DateTimeUtils.currentTimeMillis()), paramDateTimeZone);
  }
  
  public DateTime toDateTimeAtMidnight()
  {
    return toDateTimeAtMidnight(null);
  }
  
  public DateTime toDateTimeAtMidnight(DateTimeZone paramDateTimeZone)
  {
    paramDateTimeZone = getChronology().withZone(paramDateTimeZone);
    return new DateTime(getYear(), getMonthOfYear(), getDayOfMonth(), 0, 0, 0, 0, paramDateTimeZone);
  }
  
  public Interval toInterval()
  {
    return toInterval(null);
  }
  
  public Interval toInterval(DateTimeZone paramDateTimeZone)
  {
    return toDateMidnight(DateTimeUtils.getZone(paramDateTimeZone)).toInterval();
  }
  
  public LocalDate toLocalDate()
  {
    return new LocalDate(getYear(), getMonthOfYear(), getDayOfMonth(), getChronology());
  }
  
  public String toString()
  {
    return ISODateTimeFormat.yearMonthDay().print(this);
  }
  
  public YearMonthDay withChronologyRetainFields(Chronology paramChronology)
  {
    paramChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
    if (paramChronology == getChronology()) {
      return this;
    }
    YearMonthDay localYearMonthDay = new YearMonthDay(this, paramChronology);
    paramChronology.validate(localYearMonthDay, getValues());
    return localYearMonthDay;
  }
  
  public YearMonthDay withDayOfMonth(int paramInt)
  {
    int[] arrayOfInt = getValues();
    return new YearMonthDay(this, getChronology().dayOfMonth().set(this, 2, arrayOfInt, paramInt));
  }
  
  public YearMonthDay withField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
  {
    int i = indexOfSupported(paramDateTimeFieldType);
    if (paramInt == getValue(i)) {
      return this;
    }
    paramDateTimeFieldType = getValues();
    return new YearMonthDay(this, getField(i).set(this, i, paramDateTimeFieldType, paramInt));
  }
  
  public YearMonthDay withFieldAdded(DurationFieldType paramDurationFieldType, int paramInt)
  {
    int i = indexOfSupported(paramDurationFieldType);
    if (paramInt == 0) {
      return this;
    }
    paramDurationFieldType = getValues();
    return new YearMonthDay(this, getField(i).add(this, i, paramDurationFieldType, paramInt));
  }
  
  public YearMonthDay withMonthOfYear(int paramInt)
  {
    int[] arrayOfInt = getValues();
    return new YearMonthDay(this, getChronology().monthOfYear().set(this, 1, arrayOfInt, paramInt));
  }
  
  public YearMonthDay withPeriodAdded(ReadablePeriod paramReadablePeriod, int paramInt)
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
        localObject2 = getField(j).add(this, j, (int[])localObject1, FieldUtils.safeMultiply(paramReadablePeriod.getValue(i), paramInt));
      }
      i += 1;
      localObject1 = localObject2;
    }
    return new YearMonthDay(this, (int[])localObject1);
  }
  
  public YearMonthDay withYear(int paramInt)
  {
    int[] arrayOfInt = getValues();
    return new YearMonthDay(this, getChronology().year().set(this, 0, arrayOfInt, paramInt));
  }
  
  public Property year()
  {
    return new Property(this, 0);
  }
  
  @Deprecated
  public static class Property
    extends AbstractPartialFieldProperty
    implements Serializable
  {
    private static final long serialVersionUID = 5727734012190224363L;
    private final int iFieldIndex;
    private final YearMonthDay iYearMonthDay;
    
    Property(YearMonthDay paramYearMonthDay, int paramInt)
    {
      iYearMonthDay = paramYearMonthDay;
      iFieldIndex = paramInt;
    }
    
    public YearMonthDay addToCopy(int paramInt)
    {
      int[] arrayOfInt = iYearMonthDay.getValues();
      arrayOfInt = getField().add(iYearMonthDay, iFieldIndex, arrayOfInt, paramInt);
      return new YearMonthDay(iYearMonthDay, arrayOfInt);
    }
    
    public YearMonthDay addWrapFieldToCopy(int paramInt)
    {
      int[] arrayOfInt = iYearMonthDay.getValues();
      arrayOfInt = getField().addWrapField(iYearMonthDay, iFieldIndex, arrayOfInt, paramInt);
      return new YearMonthDay(iYearMonthDay, arrayOfInt);
    }
    
    public int get()
    {
      return iYearMonthDay.getValue(iFieldIndex);
    }
    
    public DateTimeField getField()
    {
      return iYearMonthDay.getField(iFieldIndex);
    }
    
    protected ReadablePartial getReadablePartial()
    {
      return iYearMonthDay;
    }
    
    public YearMonthDay getYearMonthDay()
    {
      return iYearMonthDay;
    }
    
    public YearMonthDay setCopy(int paramInt)
    {
      int[] arrayOfInt = iYearMonthDay.getValues();
      arrayOfInt = getField().set(iYearMonthDay, iFieldIndex, arrayOfInt, paramInt);
      return new YearMonthDay(iYearMonthDay, arrayOfInt);
    }
    
    public YearMonthDay setCopy(String paramString)
    {
      return setCopy(paramString, null);
    }
    
    public YearMonthDay setCopy(String paramString, Locale paramLocale)
    {
      int[] arrayOfInt = iYearMonthDay.getValues();
      paramString = getField().set(iYearMonthDay, iFieldIndex, arrayOfInt, paramString, paramLocale);
      return new YearMonthDay(iYearMonthDay, paramString);
    }
    
    public YearMonthDay withMaximumValue()
    {
      return setCopy(getMaximumValue());
    }
    
    public YearMonthDay withMinimumValue()
    {
      return setCopy(getMinimumValue());
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.YearMonthDay
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */