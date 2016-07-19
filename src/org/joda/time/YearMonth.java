package org.joda.time;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BasePartial;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.AbstractPartialFieldProperty;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class YearMonth
  extends BasePartial
  implements ReadablePartial, Serializable
{
  private static final DateTimeFieldType[] FIELD_TYPES = { DateTimeFieldType.year(), DateTimeFieldType.monthOfYear() };
  public static final int MONTH_OF_YEAR = 1;
  public static final int YEAR = 0;
  private static final long serialVersionUID = 797544782896179L;
  
  public YearMonth() {}
  
  public YearMonth(int paramInt1, int paramInt2)
  {
    this(paramInt1, paramInt2, null);
  }
  
  public YearMonth(int paramInt1, int paramInt2, Chronology paramChronology)
  {
    super(new int[] { paramInt1, paramInt2 }, paramChronology);
  }
  
  public YearMonth(long paramLong)
  {
    super(paramLong);
  }
  
  public YearMonth(long paramLong, Chronology paramChronology)
  {
    super(paramLong, paramChronology);
  }
  
  public YearMonth(Object paramObject)
  {
    super(paramObject, null, ISODateTimeFormat.localDateParser());
  }
  
  public YearMonth(Object paramObject, Chronology paramChronology)
  {
    super(paramObject, DateTimeUtils.getChronology(paramChronology), ISODateTimeFormat.localDateParser());
  }
  
  public YearMonth(Chronology paramChronology)
  {
    super(paramChronology);
  }
  
  public YearMonth(DateTimeZone paramDateTimeZone)
  {
    super(ISOChronology.getInstance(paramDateTimeZone));
  }
  
  YearMonth(YearMonth paramYearMonth, Chronology paramChronology)
  {
    super(paramYearMonth, paramChronology);
  }
  
  YearMonth(YearMonth paramYearMonth, int[] paramArrayOfInt)
  {
    super(paramYearMonth, paramArrayOfInt);
  }
  
  public static YearMonth fromCalendarFields(Calendar paramCalendar)
  {
    if (paramCalendar == null) {
      throw new IllegalArgumentException("The calendar must not be null");
    }
    return new YearMonth(paramCalendar.get(1), paramCalendar.get(2) + 1);
  }
  
  public static YearMonth fromDateFields(Date paramDate)
  {
    if (paramDate == null) {
      throw new IllegalArgumentException("The date must not be null");
    }
    return new YearMonth(paramDate.getYear() + 1900, paramDate.getMonth() + 1);
  }
  
  public static YearMonth now()
  {
    return new YearMonth();
  }
  
  public static YearMonth now(Chronology paramChronology)
  {
    if (paramChronology == null) {
      throw new NullPointerException("Chronology must not be null");
    }
    return new YearMonth(paramChronology);
  }
  
  public static YearMonth now(DateTimeZone paramDateTimeZone)
  {
    if (paramDateTimeZone == null) {
      throw new NullPointerException("Zone must not be null");
    }
    return new YearMonth(paramDateTimeZone);
  }
  
  @FromString
  public static YearMonth parse(String paramString)
  {
    return parse(paramString, ISODateTimeFormat.localDateParser());
  }
  
  public static YearMonth parse(String paramString, DateTimeFormatter paramDateTimeFormatter)
  {
    paramString = paramDateTimeFormatter.parseLocalDate(paramString);
    return new YearMonth(paramString.getYear(), paramString.getMonthOfYear());
  }
  
  private Object readResolve()
  {
    YearMonth localYearMonth = this;
    if (!DateTimeZone.UTC.equals(getChronology().getZone())) {
      localYearMonth = new YearMonth(this, getChronology().withUTC());
    }
    return localYearMonth;
  }
  
  protected DateTimeField getField(int paramInt, Chronology paramChronology)
  {
    switch (paramInt)
    {
    default: 
      throw new IndexOutOfBoundsException("Invalid index: " + paramInt);
    case 0: 
      return paramChronology.year();
    }
    return paramChronology.monthOfYear();
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
  
  public YearMonth minus(ReadablePeriod paramReadablePeriod)
  {
    return withPeriodAdded(paramReadablePeriod, -1);
  }
  
  public YearMonth minusMonths(int paramInt)
  {
    return withFieldAdded(DurationFieldType.months(), FieldUtils.safeNegate(paramInt));
  }
  
  public YearMonth minusYears(int paramInt)
  {
    return withFieldAdded(DurationFieldType.years(), FieldUtils.safeNegate(paramInt));
  }
  
  public Property monthOfYear()
  {
    return new Property(this, 1);
  }
  
  public YearMonth plus(ReadablePeriod paramReadablePeriod)
  {
    return withPeriodAdded(paramReadablePeriod, 1);
  }
  
  public YearMonth plusMonths(int paramInt)
  {
    return withFieldAdded(DurationFieldType.months(), paramInt);
  }
  
  public YearMonth plusYears(int paramInt)
  {
    return withFieldAdded(DurationFieldType.years(), paramInt);
  }
  
  public Property property(DateTimeFieldType paramDateTimeFieldType)
  {
    return new Property(this, indexOfSupported(paramDateTimeFieldType));
  }
  
  public int size()
  {
    return 2;
  }
  
  public Interval toInterval()
  {
    return toInterval(null);
  }
  
  public Interval toInterval(DateTimeZone paramDateTimeZone)
  {
    paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
    return new Interval(toLocalDate(1).toDateTimeAtStartOfDay(paramDateTimeZone), plusMonths(1).toLocalDate(1).toDateTimeAtStartOfDay(paramDateTimeZone));
  }
  
  public LocalDate toLocalDate(int paramInt)
  {
    return new LocalDate(getYear(), getMonthOfYear(), paramInt, getChronology());
  }
  
  @ToString
  public String toString()
  {
    return ISODateTimeFormat.yearMonth().print(this);
  }
  
  public String toString(String paramString)
  {
    if (paramString == null) {
      return toString();
    }
    return DateTimeFormat.forPattern(paramString).print(this);
  }
  
  public String toString(String paramString, Locale paramLocale)
    throws IllegalArgumentException
  {
    if (paramString == null) {
      return toString();
    }
    return DateTimeFormat.forPattern(paramString).withLocale(paramLocale).print(this);
  }
  
  public YearMonth withChronologyRetainFields(Chronology paramChronology)
  {
    paramChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
    if (paramChronology == getChronology()) {
      return this;
    }
    YearMonth localYearMonth = new YearMonth(this, paramChronology);
    paramChronology.validate(localYearMonth, getValues());
    return localYearMonth;
  }
  
  public YearMonth withField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
  {
    int i = indexOfSupported(paramDateTimeFieldType);
    if (paramInt == getValue(i)) {
      return this;
    }
    paramDateTimeFieldType = getValues();
    return new YearMonth(this, getField(i).set(this, i, paramDateTimeFieldType, paramInt));
  }
  
  public YearMonth withFieldAdded(DurationFieldType paramDurationFieldType, int paramInt)
  {
    int i = indexOfSupported(paramDurationFieldType);
    if (paramInt == 0) {
      return this;
    }
    paramDurationFieldType = getValues();
    return new YearMonth(this, getField(i).add(this, i, paramDurationFieldType, paramInt));
  }
  
  public YearMonth withMonthOfYear(int paramInt)
  {
    int[] arrayOfInt = getValues();
    return new YearMonth(this, getChronology().monthOfYear().set(this, 1, arrayOfInt, paramInt));
  }
  
  public YearMonth withPeriodAdded(ReadablePeriod paramReadablePeriod, int paramInt)
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
    return new YearMonth(this, (int[])localObject1);
  }
  
  public YearMonth withYear(int paramInt)
  {
    int[] arrayOfInt = getValues();
    return new YearMonth(this, getChronology().year().set(this, 0, arrayOfInt, paramInt));
  }
  
  public Property year()
  {
    return new Property(this, 0);
  }
  
  public static class Property
    extends AbstractPartialFieldProperty
    implements Serializable
  {
    private static final long serialVersionUID = 5727734012190224363L;
    private final YearMonth iBase;
    private final int iFieldIndex;
    
    Property(YearMonth paramYearMonth, int paramInt)
    {
      iBase = paramYearMonth;
      iFieldIndex = paramInt;
    }
    
    public YearMonth addToCopy(int paramInt)
    {
      int[] arrayOfInt = iBase.getValues();
      arrayOfInt = getField().add(iBase, iFieldIndex, arrayOfInt, paramInt);
      return new YearMonth(iBase, arrayOfInt);
    }
    
    public YearMonth addWrapFieldToCopy(int paramInt)
    {
      int[] arrayOfInt = iBase.getValues();
      arrayOfInt = getField().addWrapField(iBase, iFieldIndex, arrayOfInt, paramInt);
      return new YearMonth(iBase, arrayOfInt);
    }
    
    public int get()
    {
      return iBase.getValue(iFieldIndex);
    }
    
    public DateTimeField getField()
    {
      return iBase.getField(iFieldIndex);
    }
    
    protected ReadablePartial getReadablePartial()
    {
      return iBase;
    }
    
    public YearMonth getYearMonth()
    {
      return iBase;
    }
    
    public YearMonth setCopy(int paramInt)
    {
      int[] arrayOfInt = iBase.getValues();
      arrayOfInt = getField().set(iBase, iFieldIndex, arrayOfInt, paramInt);
      return new YearMonth(iBase, arrayOfInt);
    }
    
    public YearMonth setCopy(String paramString)
    {
      return setCopy(paramString, null);
    }
    
    public YearMonth setCopy(String paramString, Locale paramLocale)
    {
      int[] arrayOfInt = iBase.getValues();
      paramString = getField().set(iBase, iFieldIndex, arrayOfInt, paramString, paramLocale);
      return new YearMonth(iBase, paramString);
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.YearMonth
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */