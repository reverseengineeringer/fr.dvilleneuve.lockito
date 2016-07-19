package org.joda.time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BasePartial;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.AbstractPartialFieldProperty;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.ISODateTimeFormat;

public final class MonthDay
  extends BasePartial
  implements ReadablePartial, Serializable
{
  public static final int DAY_OF_MONTH = 1;
  private static final DateTimeFieldType[] FIELD_TYPES = { DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth() };
  public static final int MONTH_OF_YEAR = 0;
  private static final DateTimeFormatter PARSER = new DateTimeFormatterBuilder().appendOptional(ISODateTimeFormat.localDateParser().getParser()).appendOptional(DateTimeFormat.forPattern("--MM-dd").getParser()).toFormatter();
  private static final long serialVersionUID = 2954560699050434609L;
  
  public MonthDay() {}
  
  public MonthDay(int paramInt1, int paramInt2)
  {
    this(paramInt1, paramInt2, null);
  }
  
  public MonthDay(int paramInt1, int paramInt2, Chronology paramChronology)
  {
    super(new int[] { paramInt1, paramInt2 }, paramChronology);
  }
  
  public MonthDay(long paramLong)
  {
    super(paramLong);
  }
  
  public MonthDay(long paramLong, Chronology paramChronology)
  {
    super(paramLong, paramChronology);
  }
  
  public MonthDay(Object paramObject)
  {
    super(paramObject, null, ISODateTimeFormat.localDateParser());
  }
  
  public MonthDay(Object paramObject, Chronology paramChronology)
  {
    super(paramObject, DateTimeUtils.getChronology(paramChronology), ISODateTimeFormat.localDateParser());
  }
  
  public MonthDay(Chronology paramChronology)
  {
    super(paramChronology);
  }
  
  public MonthDay(DateTimeZone paramDateTimeZone)
  {
    super(ISOChronology.getInstance(paramDateTimeZone));
  }
  
  MonthDay(MonthDay paramMonthDay, Chronology paramChronology)
  {
    super(paramMonthDay, paramChronology);
  }
  
  MonthDay(MonthDay paramMonthDay, int[] paramArrayOfInt)
  {
    super(paramMonthDay, paramArrayOfInt);
  }
  
  public static MonthDay fromCalendarFields(Calendar paramCalendar)
  {
    if (paramCalendar == null) {
      throw new IllegalArgumentException("The calendar must not be null");
    }
    return new MonthDay(paramCalendar.get(2) + 1, paramCalendar.get(5));
  }
  
  public static MonthDay fromDateFields(Date paramDate)
  {
    if (paramDate == null) {
      throw new IllegalArgumentException("The date must not be null");
    }
    return new MonthDay(paramDate.getMonth() + 1, paramDate.getDate());
  }
  
  public static MonthDay now()
  {
    return new MonthDay();
  }
  
  public static MonthDay now(Chronology paramChronology)
  {
    if (paramChronology == null) {
      throw new NullPointerException("Chronology must not be null");
    }
    return new MonthDay(paramChronology);
  }
  
  public static MonthDay now(DateTimeZone paramDateTimeZone)
  {
    if (paramDateTimeZone == null) {
      throw new NullPointerException("Zone must not be null");
    }
    return new MonthDay(paramDateTimeZone);
  }
  
  @FromString
  public static MonthDay parse(String paramString)
  {
    return parse(paramString, PARSER);
  }
  
  public static MonthDay parse(String paramString, DateTimeFormatter paramDateTimeFormatter)
  {
    paramString = paramDateTimeFormatter.parseLocalDate(paramString);
    return new MonthDay(paramString.getMonthOfYear(), paramString.getDayOfMonth());
  }
  
  private Object readResolve()
  {
    MonthDay localMonthDay = this;
    if (!DateTimeZone.UTC.equals(getChronology().getZone())) {
      localMonthDay = new MonthDay(this, getChronology().withUTC());
    }
    return localMonthDay;
  }
  
  public Property dayOfMonth()
  {
    return new Property(this, 1);
  }
  
  public int getDayOfMonth()
  {
    return getValue(1);
  }
  
  protected DateTimeField getField(int paramInt, Chronology paramChronology)
  {
    switch (paramInt)
    {
    default: 
      throw new IndexOutOfBoundsException("Invalid index: " + paramInt);
    case 0: 
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
    return getValue(0);
  }
  
  public MonthDay minus(ReadablePeriod paramReadablePeriod)
  {
    return withPeriodAdded(paramReadablePeriod, -1);
  }
  
  public MonthDay minusDays(int paramInt)
  {
    return withFieldAdded(DurationFieldType.days(), FieldUtils.safeNegate(paramInt));
  }
  
  public MonthDay minusMonths(int paramInt)
  {
    return withFieldAdded(DurationFieldType.months(), FieldUtils.safeNegate(paramInt));
  }
  
  public Property monthOfYear()
  {
    return new Property(this, 0);
  }
  
  public MonthDay plus(ReadablePeriod paramReadablePeriod)
  {
    return withPeriodAdded(paramReadablePeriod, 1);
  }
  
  public MonthDay plusDays(int paramInt)
  {
    return withFieldAdded(DurationFieldType.days(), paramInt);
  }
  
  public MonthDay plusMonths(int paramInt)
  {
    return withFieldAdded(DurationFieldType.months(), paramInt);
  }
  
  public Property property(DateTimeFieldType paramDateTimeFieldType)
  {
    return new Property(this, indexOfSupported(paramDateTimeFieldType));
  }
  
  public int size()
  {
    return 2;
  }
  
  public LocalDate toLocalDate(int paramInt)
  {
    return new LocalDate(paramInt, getMonthOfYear(), getDayOfMonth(), getChronology());
  }
  
  @ToString
  public String toString()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(DateTimeFieldType.monthOfYear());
    localArrayList.add(DateTimeFieldType.dayOfMonth());
    return ISODateTimeFormat.forFields(localArrayList, true, true).print(this);
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
  
  public MonthDay withChronologyRetainFields(Chronology paramChronology)
  {
    paramChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
    if (paramChronology == getChronology()) {
      return this;
    }
    MonthDay localMonthDay = new MonthDay(this, paramChronology);
    paramChronology.validate(localMonthDay, getValues());
    return localMonthDay;
  }
  
  public MonthDay withDayOfMonth(int paramInt)
  {
    int[] arrayOfInt = getValues();
    return new MonthDay(this, getChronology().dayOfMonth().set(this, 1, arrayOfInt, paramInt));
  }
  
  public MonthDay withField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
  {
    int i = indexOfSupported(paramDateTimeFieldType);
    if (paramInt == getValue(i)) {
      return this;
    }
    paramDateTimeFieldType = getValues();
    return new MonthDay(this, getField(i).set(this, i, paramDateTimeFieldType, paramInt));
  }
  
  public MonthDay withFieldAdded(DurationFieldType paramDurationFieldType, int paramInt)
  {
    int i = indexOfSupported(paramDurationFieldType);
    if (paramInt == 0) {
      return this;
    }
    paramDurationFieldType = getValues();
    return new MonthDay(this, getField(i).add(this, i, paramDurationFieldType, paramInt));
  }
  
  public MonthDay withMonthOfYear(int paramInt)
  {
    int[] arrayOfInt = getValues();
    return new MonthDay(this, getChronology().monthOfYear().set(this, 0, arrayOfInt, paramInt));
  }
  
  public MonthDay withPeriodAdded(ReadablePeriod paramReadablePeriod, int paramInt)
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
    return new MonthDay(this, (int[])localObject1);
  }
  
  public static class Property
    extends AbstractPartialFieldProperty
    implements Serializable
  {
    private static final long serialVersionUID = 5727734012190224363L;
    private final MonthDay iBase;
    private final int iFieldIndex;
    
    Property(MonthDay paramMonthDay, int paramInt)
    {
      iBase = paramMonthDay;
      iFieldIndex = paramInt;
    }
    
    public MonthDay addToCopy(int paramInt)
    {
      int[] arrayOfInt = iBase.getValues();
      arrayOfInt = getField().add(iBase, iFieldIndex, arrayOfInt, paramInt);
      return new MonthDay(iBase, arrayOfInt);
    }
    
    public MonthDay addWrapFieldToCopy(int paramInt)
    {
      int[] arrayOfInt = iBase.getValues();
      arrayOfInt = getField().addWrapField(iBase, iFieldIndex, arrayOfInt, paramInt);
      return new MonthDay(iBase, arrayOfInt);
    }
    
    public int get()
    {
      return iBase.getValue(iFieldIndex);
    }
    
    public DateTimeField getField()
    {
      return iBase.getField(iFieldIndex);
    }
    
    public MonthDay getMonthDay()
    {
      return iBase;
    }
    
    protected ReadablePartial getReadablePartial()
    {
      return iBase;
    }
    
    public MonthDay setCopy(int paramInt)
    {
      int[] arrayOfInt = iBase.getValues();
      arrayOfInt = getField().set(iBase, iFieldIndex, arrayOfInt, paramInt);
      return new MonthDay(iBase, arrayOfInt);
    }
    
    public MonthDay setCopy(String paramString)
    {
      return setCopy(paramString, null);
    }
    
    public MonthDay setCopy(String paramString, Locale paramLocale)
    {
      int[] arrayOfInt = iBase.getValues();
      paramString = getField().set(iBase, iFieldIndex, arrayOfInt, paramString, paramLocale);
      return new MonthDay(iBase, paramString);
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.MonthDay
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */