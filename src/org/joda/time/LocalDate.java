package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseLocal;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.PartialConverter;
import org.joda.time.field.AbstractReadableInstantFieldProperty;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class LocalDate
  extends BaseLocal
  implements ReadablePartial, Serializable
{
  private static final Set<DurationFieldType> DATE_DURATION_TYPES = new HashSet();
  private static final int DAY_OF_MONTH = 2;
  private static final int MONTH_OF_YEAR = 1;
  private static final int YEAR = 0;
  private static final long serialVersionUID = -8775358157899L;
  private final Chronology iChronology;
  private transient int iHash;
  private final long iLocalMillis;
  
  static
  {
    DATE_DURATION_TYPES.add(DurationFieldType.days());
    DATE_DURATION_TYPES.add(DurationFieldType.weeks());
    DATE_DURATION_TYPES.add(DurationFieldType.months());
    DATE_DURATION_TYPES.add(DurationFieldType.weekyears());
    DATE_DURATION_TYPES.add(DurationFieldType.years());
    DATE_DURATION_TYPES.add(DurationFieldType.centuries());
    DATE_DURATION_TYPES.add(DurationFieldType.eras());
  }
  
  public LocalDate()
  {
    this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
  }
  
  public LocalDate(int paramInt1, int paramInt2, int paramInt3)
  {
    this(paramInt1, paramInt2, paramInt3, ISOChronology.getInstanceUTC());
  }
  
  public LocalDate(int paramInt1, int paramInt2, int paramInt3, Chronology paramChronology)
  {
    paramChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
    long l = paramChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, 0);
    iChronology = paramChronology;
    iLocalMillis = l;
  }
  
  public LocalDate(long paramLong)
  {
    this(paramLong, ISOChronology.getInstance());
  }
  
  public LocalDate(long paramLong, Chronology paramChronology)
  {
    paramChronology = DateTimeUtils.getChronology(paramChronology);
    paramLong = paramChronology.getZone().getMillisKeepLocal(DateTimeZone.UTC, paramLong);
    paramChronology = paramChronology.withUTC();
    iLocalMillis = paramChronology.dayOfMonth().roundFloor(paramLong);
    iChronology = paramChronology;
  }
  
  public LocalDate(long paramLong, DateTimeZone paramDateTimeZone)
  {
    this(paramLong, ISOChronology.getInstance(paramDateTimeZone));
  }
  
  public LocalDate(Object paramObject)
  {
    this(paramObject, (Chronology)null);
  }
  
  public LocalDate(Object paramObject, Chronology paramChronology)
  {
    PartialConverter localPartialConverter = ConverterManager.getInstance().getPartialConverter(paramObject);
    paramChronology = DateTimeUtils.getChronology(localPartialConverter.getChronology(paramObject, paramChronology));
    iChronology = paramChronology.withUTC();
    paramObject = localPartialConverter.getPartialValues(this, paramObject, paramChronology, ISODateTimeFormat.localDateParser());
    iLocalMillis = iChronology.getDateTimeMillis(paramObject[0], paramObject[1], paramObject[2], 0);
  }
  
  public LocalDate(Object paramObject, DateTimeZone paramDateTimeZone)
  {
    PartialConverter localPartialConverter = ConverterManager.getInstance().getPartialConverter(paramObject);
    paramDateTimeZone = DateTimeUtils.getChronology(localPartialConverter.getChronology(paramObject, paramDateTimeZone));
    iChronology = paramDateTimeZone.withUTC();
    paramObject = localPartialConverter.getPartialValues(this, paramObject, paramDateTimeZone, ISODateTimeFormat.localDateParser());
    iLocalMillis = iChronology.getDateTimeMillis(paramObject[0], paramObject[1], paramObject[2], 0);
  }
  
  public LocalDate(Chronology paramChronology)
  {
    this(DateTimeUtils.currentTimeMillis(), paramChronology);
  }
  
  public LocalDate(DateTimeZone paramDateTimeZone)
  {
    this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance(paramDateTimeZone));
  }
  
  public static LocalDate fromCalendarFields(Calendar paramCalendar)
  {
    if (paramCalendar == null) {
      throw new IllegalArgumentException("The calendar must not be null");
    }
    int j = paramCalendar.get(0);
    int i = paramCalendar.get(1);
    if (j == 1) {}
    for (;;)
    {
      return new LocalDate(i, paramCalendar.get(2) + 1, paramCalendar.get(5));
      i = 1 - i;
    }
  }
  
  public static LocalDate fromDateFields(Date paramDate)
  {
    if (paramDate == null) {
      throw new IllegalArgumentException("The date must not be null");
    }
    if (paramDate.getTime() < 0L)
    {
      GregorianCalendar localGregorianCalendar = new GregorianCalendar();
      localGregorianCalendar.setTime(paramDate);
      return fromCalendarFields(localGregorianCalendar);
    }
    return new LocalDate(paramDate.getYear() + 1900, paramDate.getMonth() + 1, paramDate.getDate());
  }
  
  public static LocalDate now()
  {
    return new LocalDate();
  }
  
  public static LocalDate now(Chronology paramChronology)
  {
    if (paramChronology == null) {
      throw new NullPointerException("Chronology must not be null");
    }
    return new LocalDate(paramChronology);
  }
  
  public static LocalDate now(DateTimeZone paramDateTimeZone)
  {
    if (paramDateTimeZone == null) {
      throw new NullPointerException("Zone must not be null");
    }
    return new LocalDate(paramDateTimeZone);
  }
  
  @FromString
  public static LocalDate parse(String paramString)
  {
    return parse(paramString, ISODateTimeFormat.localDateParser());
  }
  
  public static LocalDate parse(String paramString, DateTimeFormatter paramDateTimeFormatter)
  {
    return paramDateTimeFormatter.parseLocalDate(paramString);
  }
  
  private Object readResolve()
  {
    LocalDate localLocalDate;
    if (iChronology == null) {
      localLocalDate = new LocalDate(iLocalMillis, ISOChronology.getInstanceUTC());
    }
    do
    {
      return localLocalDate;
      localLocalDate = this;
    } while (DateTimeZone.UTC.equals(iChronology.getZone()));
    return new LocalDate(iLocalMillis, iChronology.withUTC());
  }
  
  public Property centuryOfEra()
  {
    return new Property(this, getChronology().centuryOfEra());
  }
  
  public int compareTo(ReadablePartial paramReadablePartial)
  {
    if (this == paramReadablePartial) {}
    LocalDate localLocalDate;
    do
    {
      return 0;
      if (!(paramReadablePartial instanceof LocalDate)) {
        break;
      }
      localLocalDate = (LocalDate)paramReadablePartial;
      if (!iChronology.equals(iChronology)) {
        break;
      }
      if (iLocalMillis < iLocalMillis) {
        return -1;
      }
    } while (iLocalMillis == iLocalMillis);
    return 1;
    return super.compareTo(paramReadablePartial);
  }
  
  public Property dayOfMonth()
  {
    return new Property(this, getChronology().dayOfMonth());
  }
  
  public Property dayOfWeek()
  {
    return new Property(this, getChronology().dayOfWeek());
  }
  
  public Property dayOfYear()
  {
    return new Property(this, getChronology().dayOfYear());
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    LocalDate localLocalDate;
    do
    {
      return true;
      if (!(paramObject instanceof LocalDate)) {
        break;
      }
      localLocalDate = (LocalDate)paramObject;
      if (!iChronology.equals(iChronology)) {
        break;
      }
    } while (iLocalMillis == iLocalMillis);
    return false;
    return super.equals(paramObject);
  }
  
  public Property era()
  {
    return new Property(this, getChronology().era());
  }
  
  public int get(DateTimeFieldType paramDateTimeFieldType)
  {
    if (paramDateTimeFieldType == null) {
      throw new IllegalArgumentException("The DateTimeFieldType must not be null");
    }
    if (!isSupported(paramDateTimeFieldType)) {
      throw new IllegalArgumentException("Field '" + paramDateTimeFieldType + "' is not supported");
    }
    return paramDateTimeFieldType.getField(getChronology()).get(getLocalMillis());
  }
  
  public int getCenturyOfEra()
  {
    return getChronology().centuryOfEra().get(getLocalMillis());
  }
  
  public Chronology getChronology()
  {
    return iChronology;
  }
  
  public int getDayOfMonth()
  {
    return getChronology().dayOfMonth().get(getLocalMillis());
  }
  
  public int getDayOfWeek()
  {
    return getChronology().dayOfWeek().get(getLocalMillis());
  }
  
  public int getDayOfYear()
  {
    return getChronology().dayOfYear().get(getLocalMillis());
  }
  
  public int getEra()
  {
    return getChronology().era().get(getLocalMillis());
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
  
  protected long getLocalMillis()
  {
    return iLocalMillis;
  }
  
  public int getMonthOfYear()
  {
    return getChronology().monthOfYear().get(getLocalMillis());
  }
  
  public int getValue(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IndexOutOfBoundsException("Invalid index: " + paramInt);
    case 0: 
      return getChronology().year().get(getLocalMillis());
    case 1: 
      return getChronology().monthOfYear().get(getLocalMillis());
    }
    return getChronology().dayOfMonth().get(getLocalMillis());
  }
  
  public int getWeekOfWeekyear()
  {
    return getChronology().weekOfWeekyear().get(getLocalMillis());
  }
  
  public int getWeekyear()
  {
    return getChronology().weekyear().get(getLocalMillis());
  }
  
  public int getYear()
  {
    return getChronology().year().get(getLocalMillis());
  }
  
  public int getYearOfCentury()
  {
    return getChronology().yearOfCentury().get(getLocalMillis());
  }
  
  public int getYearOfEra()
  {
    return getChronology().yearOfEra().get(getLocalMillis());
  }
  
  public int hashCode()
  {
    int j = iHash;
    int i = j;
    if (j == 0)
    {
      i = super.hashCode();
      iHash = i;
    }
    return i;
  }
  
  public boolean isSupported(DateTimeFieldType paramDateTimeFieldType)
  {
    if (paramDateTimeFieldType == null) {}
    DurationFieldType localDurationFieldType;
    do
    {
      return false;
      localDurationFieldType = paramDateTimeFieldType.getDurationType();
    } while ((!DATE_DURATION_TYPES.contains(localDurationFieldType)) && (localDurationFieldType.getField(getChronology()).getUnitMillis() < getChronology().days().getUnitMillis()));
    return paramDateTimeFieldType.getField(getChronology()).isSupported();
  }
  
  public boolean isSupported(DurationFieldType paramDurationFieldType)
  {
    if (paramDurationFieldType == null) {}
    DurationField localDurationField;
    do
    {
      return false;
      localDurationField = paramDurationFieldType.getField(getChronology());
    } while ((!DATE_DURATION_TYPES.contains(paramDurationFieldType)) && (localDurationField.getUnitMillis() < getChronology().days().getUnitMillis()));
    return localDurationField.isSupported();
  }
  
  public LocalDate minus(ReadablePeriod paramReadablePeriod)
  {
    return withPeriodAdded(paramReadablePeriod, -1);
  }
  
  public LocalDate minusDays(int paramInt)
  {
    if (paramInt == 0) {
      return this;
    }
    return withLocalMillis(getChronology().days().subtract(getLocalMillis(), paramInt));
  }
  
  public LocalDate minusMonths(int paramInt)
  {
    if (paramInt == 0) {
      return this;
    }
    return withLocalMillis(getChronology().months().subtract(getLocalMillis(), paramInt));
  }
  
  public LocalDate minusWeeks(int paramInt)
  {
    if (paramInt == 0) {
      return this;
    }
    return withLocalMillis(getChronology().weeks().subtract(getLocalMillis(), paramInt));
  }
  
  public LocalDate minusYears(int paramInt)
  {
    if (paramInt == 0) {
      return this;
    }
    return withLocalMillis(getChronology().years().subtract(getLocalMillis(), paramInt));
  }
  
  public Property monthOfYear()
  {
    return new Property(this, getChronology().monthOfYear());
  }
  
  public LocalDate plus(ReadablePeriod paramReadablePeriod)
  {
    return withPeriodAdded(paramReadablePeriod, 1);
  }
  
  public LocalDate plusDays(int paramInt)
  {
    if (paramInt == 0) {
      return this;
    }
    return withLocalMillis(getChronology().days().add(getLocalMillis(), paramInt));
  }
  
  public LocalDate plusMonths(int paramInt)
  {
    if (paramInt == 0) {
      return this;
    }
    return withLocalMillis(getChronology().months().add(getLocalMillis(), paramInt));
  }
  
  public LocalDate plusWeeks(int paramInt)
  {
    if (paramInt == 0) {
      return this;
    }
    return withLocalMillis(getChronology().weeks().add(getLocalMillis(), paramInt));
  }
  
  public LocalDate plusYears(int paramInt)
  {
    if (paramInt == 0) {
      return this;
    }
    return withLocalMillis(getChronology().years().add(getLocalMillis(), paramInt));
  }
  
  public Property property(DateTimeFieldType paramDateTimeFieldType)
  {
    if (paramDateTimeFieldType == null) {
      throw new IllegalArgumentException("The DateTimeFieldType must not be null");
    }
    if (!isSupported(paramDateTimeFieldType)) {
      throw new IllegalArgumentException("Field '" + paramDateTimeFieldType + "' is not supported");
    }
    return new Property(this, paramDateTimeFieldType.getField(getChronology()));
  }
  
  public int size()
  {
    return 3;
  }
  
  public Date toDate()
  {
    int i = getDayOfMonth();
    Date localDate = new Date(getYear() - 1900, getMonthOfYear() - 1, i);
    Object localObject = fromDateFields(localDate);
    if (((LocalDate)localObject).isBefore(this))
    {
      while (!((LocalDate)localObject).equals(this))
      {
        localDate.setTime(localDate.getTime() + 3600000L);
        localObject = fromDateFields(localDate);
      }
      while (localDate.getDate() == i) {
        localDate.setTime(localDate.getTime() - 1000L);
      }
      localDate.setTime(localDate.getTime() + 1000L);
    }
    do
    {
      do
      {
        return localDate;
      } while (!((LocalDate)localObject).equals(this));
      localObject = new Date(localDate.getTime() - TimeZone.getDefault().getDSTSavings());
    } while (((Date)localObject).getDate() != i);
    return (Date)localObject;
  }
  
  @Deprecated
  public DateMidnight toDateMidnight()
  {
    return toDateMidnight(null);
  }
  
  @Deprecated
  public DateMidnight toDateMidnight(DateTimeZone paramDateTimeZone)
  {
    paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
    paramDateTimeZone = getChronology().withZone(paramDateTimeZone);
    return new DateMidnight(getYear(), getMonthOfYear(), getDayOfMonth(), paramDateTimeZone);
  }
  
  public DateTime toDateTime(LocalTime paramLocalTime)
  {
    return toDateTime(paramLocalTime, null);
  }
  
  public DateTime toDateTime(LocalTime paramLocalTime, DateTimeZone paramDateTimeZone)
  {
    if ((paramLocalTime != null) && (getChronology() != paramLocalTime.getChronology())) {
      throw new IllegalArgumentException("The chronology of the time does not match");
    }
    paramDateTimeZone = getChronology().withZone(paramDateTimeZone);
    long l2 = paramDateTimeZone.set(this, DateTimeUtils.currentTimeMillis());
    long l1 = l2;
    if (paramLocalTime != null) {
      l1 = paramDateTimeZone.set(paramLocalTime, l2);
    }
    return new DateTime(l1, paramDateTimeZone);
  }
  
  public DateTime toDateTimeAtCurrentTime()
  {
    return toDateTimeAtCurrentTime(null);
  }
  
  public DateTime toDateTimeAtCurrentTime(DateTimeZone paramDateTimeZone)
  {
    paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
    paramDateTimeZone = getChronology().withZone(paramDateTimeZone);
    return new DateTime(paramDateTimeZone.set(this, DateTimeUtils.currentTimeMillis()), paramDateTimeZone);
  }
  
  @Deprecated
  public DateTime toDateTimeAtMidnight()
  {
    return toDateTimeAtMidnight(null);
  }
  
  @Deprecated
  public DateTime toDateTimeAtMidnight(DateTimeZone paramDateTimeZone)
  {
    paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
    paramDateTimeZone = getChronology().withZone(paramDateTimeZone);
    return new DateTime(getYear(), getMonthOfYear(), getDayOfMonth(), 0, 0, 0, 0, paramDateTimeZone);
  }
  
  public DateTime toDateTimeAtStartOfDay()
  {
    return toDateTimeAtStartOfDay(null);
  }
  
  public DateTime toDateTimeAtStartOfDay(DateTimeZone paramDateTimeZone)
  {
    paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
    Chronology localChronology = getChronology().withZone(paramDateTimeZone);
    long l = paramDateTimeZone.convertLocalToUTC(getLocalMillis() + 21600000L, false);
    return new DateTime(localChronology.dayOfMonth().roundFloor(l), localChronology);
  }
  
  public Interval toInterval()
  {
    return toInterval(null);
  }
  
  public Interval toInterval(DateTimeZone paramDateTimeZone)
  {
    paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
    return new Interval(toDateTimeAtStartOfDay(paramDateTimeZone), plusDays(1).toDateTimeAtStartOfDay(paramDateTimeZone));
  }
  
  public LocalDateTime toLocalDateTime(LocalTime paramLocalTime)
  {
    if (paramLocalTime == null) {
      throw new IllegalArgumentException("The time must not be null");
    }
    if (getChronology() != paramLocalTime.getChronology()) {
      throw new IllegalArgumentException("The chronology of the time does not match");
    }
    return new LocalDateTime(getLocalMillis() + paramLocalTime.getLocalMillis(), getChronology());
  }
  
  @ToString
  public String toString()
  {
    return ISODateTimeFormat.date().print(this);
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
  
  public Property weekOfWeekyear()
  {
    return new Property(this, getChronology().weekOfWeekyear());
  }
  
  public Property weekyear()
  {
    return new Property(this, getChronology().weekyear());
  }
  
  public LocalDate withCenturyOfEra(int paramInt)
  {
    return withLocalMillis(getChronology().centuryOfEra().set(getLocalMillis(), paramInt));
  }
  
  public LocalDate withDayOfMonth(int paramInt)
  {
    return withLocalMillis(getChronology().dayOfMonth().set(getLocalMillis(), paramInt));
  }
  
  public LocalDate withDayOfWeek(int paramInt)
  {
    return withLocalMillis(getChronology().dayOfWeek().set(getLocalMillis(), paramInt));
  }
  
  public LocalDate withDayOfYear(int paramInt)
  {
    return withLocalMillis(getChronology().dayOfYear().set(getLocalMillis(), paramInt));
  }
  
  public LocalDate withEra(int paramInt)
  {
    return withLocalMillis(getChronology().era().set(getLocalMillis(), paramInt));
  }
  
  public LocalDate withField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
  {
    if (paramDateTimeFieldType == null) {
      throw new IllegalArgumentException("Field must not be null");
    }
    if (!isSupported(paramDateTimeFieldType)) {
      throw new IllegalArgumentException("Field '" + paramDateTimeFieldType + "' is not supported");
    }
    return withLocalMillis(paramDateTimeFieldType.getField(getChronology()).set(getLocalMillis(), paramInt));
  }
  
  public LocalDate withFieldAdded(DurationFieldType paramDurationFieldType, int paramInt)
  {
    if (paramDurationFieldType == null) {
      throw new IllegalArgumentException("Field must not be null");
    }
    if (!isSupported(paramDurationFieldType)) {
      throw new IllegalArgumentException("Field '" + paramDurationFieldType + "' is not supported");
    }
    if (paramInt == 0) {
      return this;
    }
    return withLocalMillis(paramDurationFieldType.getField(getChronology()).add(getLocalMillis(), paramInt));
  }
  
  public LocalDate withFields(ReadablePartial paramReadablePartial)
  {
    if (paramReadablePartial == null) {
      return this;
    }
    return withLocalMillis(getChronology().set(paramReadablePartial, getLocalMillis()));
  }
  
  LocalDate withLocalMillis(long paramLong)
  {
    paramLong = iChronology.dayOfMonth().roundFloor(paramLong);
    if (paramLong == getLocalMillis()) {
      return this;
    }
    return new LocalDate(paramLong, getChronology());
  }
  
  public LocalDate withMonthOfYear(int paramInt)
  {
    return withLocalMillis(getChronology().monthOfYear().set(getLocalMillis(), paramInt));
  }
  
  public LocalDate withPeriodAdded(ReadablePeriod paramReadablePeriod, int paramInt)
  {
    if ((paramReadablePeriod == null) || (paramInt == 0)) {
      return this;
    }
    long l1 = getLocalMillis();
    Chronology localChronology = getChronology();
    int i = 0;
    while (i < paramReadablePeriod.size())
    {
      long l3 = FieldUtils.safeMultiply(paramReadablePeriod.getValue(i), paramInt);
      DurationFieldType localDurationFieldType = paramReadablePeriod.getFieldType(i);
      long l2 = l1;
      if (isSupported(localDurationFieldType)) {
        l2 = localDurationFieldType.getField(localChronology).add(l1, l3);
      }
      i += 1;
      l1 = l2;
    }
    return withLocalMillis(l1);
  }
  
  public LocalDate withWeekOfWeekyear(int paramInt)
  {
    return withLocalMillis(getChronology().weekOfWeekyear().set(getLocalMillis(), paramInt));
  }
  
  public LocalDate withWeekyear(int paramInt)
  {
    return withLocalMillis(getChronology().weekyear().set(getLocalMillis(), paramInt));
  }
  
  public LocalDate withYear(int paramInt)
  {
    return withLocalMillis(getChronology().year().set(getLocalMillis(), paramInt));
  }
  
  public LocalDate withYearOfCentury(int paramInt)
  {
    return withLocalMillis(getChronology().yearOfCentury().set(getLocalMillis(), paramInt));
  }
  
  public LocalDate withYearOfEra(int paramInt)
  {
    return withLocalMillis(getChronology().yearOfEra().set(getLocalMillis(), paramInt));
  }
  
  public Property year()
  {
    return new Property(this, getChronology().year());
  }
  
  public Property yearOfCentury()
  {
    return new Property(this, getChronology().yearOfCentury());
  }
  
  public Property yearOfEra()
  {
    return new Property(this, getChronology().yearOfEra());
  }
  
  public static final class Property
    extends AbstractReadableInstantFieldProperty
  {
    private static final long serialVersionUID = -3193829732634L;
    private transient DateTimeField iField;
    private transient LocalDate iInstant;
    
    Property(LocalDate paramLocalDate, DateTimeField paramDateTimeField)
    {
      iInstant = paramLocalDate;
      iField = paramDateTimeField;
    }
    
    private void readObject(ObjectInputStream paramObjectInputStream)
      throws IOException, ClassNotFoundException
    {
      iInstant = ((LocalDate)paramObjectInputStream.readObject());
      iField = ((DateTimeFieldType)paramObjectInputStream.readObject()).getField(iInstant.getChronology());
    }
    
    private void writeObject(ObjectOutputStream paramObjectOutputStream)
      throws IOException
    {
      paramObjectOutputStream.writeObject(iInstant);
      paramObjectOutputStream.writeObject(iField.getType());
    }
    
    public LocalDate addToCopy(int paramInt)
    {
      return iInstant.withLocalMillis(iField.add(iInstant.getLocalMillis(), paramInt));
    }
    
    public LocalDate addWrapFieldToCopy(int paramInt)
    {
      return iInstant.withLocalMillis(iField.addWrapField(iInstant.getLocalMillis(), paramInt));
    }
    
    protected Chronology getChronology()
    {
      return iInstant.getChronology();
    }
    
    public DateTimeField getField()
    {
      return iField;
    }
    
    public LocalDate getLocalDate()
    {
      return iInstant;
    }
    
    protected long getMillis()
    {
      return iInstant.getLocalMillis();
    }
    
    public LocalDate roundCeilingCopy()
    {
      return iInstant.withLocalMillis(iField.roundCeiling(iInstant.getLocalMillis()));
    }
    
    public LocalDate roundFloorCopy()
    {
      return iInstant.withLocalMillis(iField.roundFloor(iInstant.getLocalMillis()));
    }
    
    public LocalDate roundHalfCeilingCopy()
    {
      return iInstant.withLocalMillis(iField.roundHalfCeiling(iInstant.getLocalMillis()));
    }
    
    public LocalDate roundHalfEvenCopy()
    {
      return iInstant.withLocalMillis(iField.roundHalfEven(iInstant.getLocalMillis()));
    }
    
    public LocalDate roundHalfFloorCopy()
    {
      return iInstant.withLocalMillis(iField.roundHalfFloor(iInstant.getLocalMillis()));
    }
    
    public LocalDate setCopy(int paramInt)
    {
      return iInstant.withLocalMillis(iField.set(iInstant.getLocalMillis(), paramInt));
    }
    
    public LocalDate setCopy(String paramString)
    {
      return setCopy(paramString, null);
    }
    
    public LocalDate setCopy(String paramString, Locale paramLocale)
    {
      return iInstant.withLocalMillis(iField.set(iInstant.getLocalMillis(), paramString, paramLocale));
    }
    
    public LocalDate withMaximumValue()
    {
      return setCopy(getMaximumValue());
    }
    
    public LocalDate withMinimumValue()
    {
      return setCopy(getMinimumValue());
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.LocalDate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */