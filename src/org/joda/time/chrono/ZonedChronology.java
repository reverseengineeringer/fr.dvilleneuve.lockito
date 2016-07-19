package org.joda.time.chrono;

import java.util.HashMap;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.IllegalInstantException;
import org.joda.time.ReadablePartial;
import org.joda.time.field.BaseDateTimeField;
import org.joda.time.field.BaseDurationField;

public final class ZonedChronology
  extends AssembledChronology
{
  private static final long serialVersionUID = -1079258847191166848L;
  
  private ZonedChronology(Chronology paramChronology, DateTimeZone paramDateTimeZone)
  {
    super(paramChronology, paramDateTimeZone);
  }
  
  private DateTimeField convertField(DateTimeField paramDateTimeField, HashMap<Object, Object> paramHashMap)
  {
    if ((paramDateTimeField == null) || (!paramDateTimeField.isSupported())) {
      return paramDateTimeField;
    }
    if (paramHashMap.containsKey(paramDateTimeField)) {
      return (DateTimeField)paramHashMap.get(paramDateTimeField);
    }
    ZonedDateTimeField localZonedDateTimeField = new ZonedDateTimeField(paramDateTimeField, getZone(), convertField(paramDateTimeField.getDurationField(), paramHashMap), convertField(paramDateTimeField.getRangeDurationField(), paramHashMap), convertField(paramDateTimeField.getLeapDurationField(), paramHashMap));
    paramHashMap.put(paramDateTimeField, localZonedDateTimeField);
    return localZonedDateTimeField;
  }
  
  private DurationField convertField(DurationField paramDurationField, HashMap<Object, Object> paramHashMap)
  {
    if ((paramDurationField == null) || (!paramDurationField.isSupported())) {
      return paramDurationField;
    }
    if (paramHashMap.containsKey(paramDurationField)) {
      return (DurationField)paramHashMap.get(paramDurationField);
    }
    ZonedDurationField localZonedDurationField = new ZonedDurationField(paramDurationField, getZone());
    paramHashMap.put(paramDurationField, localZonedDurationField);
    return localZonedDurationField;
  }
  
  public static ZonedChronology getInstance(Chronology paramChronology, DateTimeZone paramDateTimeZone)
  {
    if (paramChronology == null) {
      throw new IllegalArgumentException("Must supply a chronology");
    }
    paramChronology = paramChronology.withUTC();
    if (paramChronology == null) {
      throw new IllegalArgumentException("UTC chronology must not be null");
    }
    if (paramDateTimeZone == null) {
      throw new IllegalArgumentException("DateTimeZone must not be null");
    }
    return new ZonedChronology(paramChronology, paramDateTimeZone);
  }
  
  private long localToUTC(long paramLong)
  {
    DateTimeZone localDateTimeZone = getZone();
    int i = localDateTimeZone.getOffsetFromLocal(paramLong);
    paramLong -= i;
    if (i != localDateTimeZone.getOffset(paramLong)) {
      throw new IllegalInstantException(paramLong, localDateTimeZone.getID());
    }
    return paramLong;
  }
  
  static boolean useTimeArithmetic(DurationField paramDurationField)
  {
    return (paramDurationField != null) && (paramDurationField.getUnitMillis() < 43200000L);
  }
  
  protected void assemble(AssembledChronology.Fields paramFields)
  {
    HashMap localHashMap = new HashMap();
    eras = convertField(eras, localHashMap);
    centuries = convertField(centuries, localHashMap);
    years = convertField(years, localHashMap);
    months = convertField(months, localHashMap);
    weekyears = convertField(weekyears, localHashMap);
    weeks = convertField(weeks, localHashMap);
    days = convertField(days, localHashMap);
    halfdays = convertField(halfdays, localHashMap);
    hours = convertField(hours, localHashMap);
    minutes = convertField(minutes, localHashMap);
    seconds = convertField(seconds, localHashMap);
    millis = convertField(millis, localHashMap);
    year = convertField(year, localHashMap);
    yearOfEra = convertField(yearOfEra, localHashMap);
    yearOfCentury = convertField(yearOfCentury, localHashMap);
    centuryOfEra = convertField(centuryOfEra, localHashMap);
    era = convertField(era, localHashMap);
    dayOfWeek = convertField(dayOfWeek, localHashMap);
    dayOfMonth = convertField(dayOfMonth, localHashMap);
    dayOfYear = convertField(dayOfYear, localHashMap);
    monthOfYear = convertField(monthOfYear, localHashMap);
    weekOfWeekyear = convertField(weekOfWeekyear, localHashMap);
    weekyear = convertField(weekyear, localHashMap);
    weekyearOfCentury = convertField(weekyearOfCentury, localHashMap);
    millisOfSecond = convertField(millisOfSecond, localHashMap);
    millisOfDay = convertField(millisOfDay, localHashMap);
    secondOfMinute = convertField(secondOfMinute, localHashMap);
    secondOfDay = convertField(secondOfDay, localHashMap);
    minuteOfHour = convertField(minuteOfHour, localHashMap);
    minuteOfDay = convertField(minuteOfDay, localHashMap);
    hourOfDay = convertField(hourOfDay, localHashMap);
    hourOfHalfday = convertField(hourOfHalfday, localHashMap);
    clockhourOfDay = convertField(clockhourOfDay, localHashMap);
    clockhourOfHalfday = convertField(clockhourOfHalfday, localHashMap);
    halfdayOfDay = convertField(halfdayOfDay, localHashMap);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof ZonedChronology)) {
        return false;
      }
      paramObject = (ZonedChronology)paramObject;
    } while ((getBase().equals(((ZonedChronology)paramObject).getBase())) && (getZone().equals(((ZonedChronology)paramObject).getZone())));
    return false;
  }
  
  public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IllegalArgumentException
  {
    return localToUTC(getBase().getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4));
  }
  
  public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
    throws IllegalArgumentException
  {
    return localToUTC(getBase().getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7));
  }
  
  public long getDateTimeMillis(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IllegalArgumentException
  {
    return localToUTC(getBase().getDateTimeMillis(getZone().getOffset(paramLong) + paramLong, paramInt1, paramInt2, paramInt3, paramInt4));
  }
  
  public DateTimeZone getZone()
  {
    return (DateTimeZone)getParam();
  }
  
  public int hashCode()
  {
    return 326565 + getZone().hashCode() * 11 + getBase().hashCode() * 7;
  }
  
  public String toString()
  {
    return "ZonedChronology[" + getBase() + ", " + getZone().getID() + ']';
  }
  
  public Chronology withUTC()
  {
    return getBase();
  }
  
  public Chronology withZone(DateTimeZone paramDateTimeZone)
  {
    DateTimeZone localDateTimeZone = paramDateTimeZone;
    if (paramDateTimeZone == null) {
      localDateTimeZone = DateTimeZone.getDefault();
    }
    if (localDateTimeZone == getParam()) {
      return this;
    }
    if (localDateTimeZone == DateTimeZone.UTC) {
      return getBase();
    }
    return new ZonedChronology(getBase(), localDateTimeZone);
  }
  
  static final class ZonedDateTimeField
    extends BaseDateTimeField
  {
    private static final long serialVersionUID = -3968986277775529794L;
    final DurationField iDurationField;
    final DateTimeField iField;
    final DurationField iLeapDurationField;
    final DurationField iRangeDurationField;
    final boolean iTimeField;
    final DateTimeZone iZone;
    
    ZonedDateTimeField(DateTimeField paramDateTimeField, DateTimeZone paramDateTimeZone, DurationField paramDurationField1, DurationField paramDurationField2, DurationField paramDurationField3)
    {
      super();
      if (!paramDateTimeField.isSupported()) {
        throw new IllegalArgumentException();
      }
      iField = paramDateTimeField;
      iZone = paramDateTimeZone;
      iDurationField = paramDurationField1;
      iTimeField = ZonedChronology.useTimeArithmetic(paramDurationField1);
      iRangeDurationField = paramDurationField2;
      iLeapDurationField = paramDurationField3;
    }
    
    private int getOffsetToAdd(long paramLong)
    {
      int i = iZone.getOffset(paramLong);
      if (((paramLong ^ paramLong + i) < 0L) && ((i ^ paramLong) >= 0L)) {
        throw new ArithmeticException("Adding time zone offset caused overflow");
      }
      return i;
    }
    
    public long add(long paramLong, int paramInt)
    {
      if (iTimeField)
      {
        int i = getOffsetToAdd(paramLong);
        return iField.add(i + paramLong, paramInt) - i;
      }
      long l = iZone.convertUTCToLocal(paramLong);
      l = iField.add(l, paramInt);
      return iZone.convertLocalToUTC(l, false, paramLong);
    }
    
    public long add(long paramLong1, long paramLong2)
    {
      if (iTimeField)
      {
        int i = getOffsetToAdd(paramLong1);
        return iField.add(i + paramLong1, paramLong2) - i;
      }
      long l = iZone.convertUTCToLocal(paramLong1);
      paramLong2 = iField.add(l, paramLong2);
      return iZone.convertLocalToUTC(paramLong2, false, paramLong1);
    }
    
    public long addWrapField(long paramLong, int paramInt)
    {
      if (iTimeField)
      {
        int i = getOffsetToAdd(paramLong);
        return iField.addWrapField(i + paramLong, paramInt) - i;
      }
      long l = iZone.convertUTCToLocal(paramLong);
      l = iField.addWrapField(l, paramInt);
      return iZone.convertLocalToUTC(l, false, paramLong);
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof ZonedDateTimeField)) {
          break;
        }
        paramObject = (ZonedDateTimeField)paramObject;
      } while ((iField.equals(iField)) && (iZone.equals(iZone)) && (iDurationField.equals(iDurationField)) && (iRangeDurationField.equals(iRangeDurationField)));
      return false;
      return false;
    }
    
    public int get(long paramLong)
    {
      paramLong = iZone.convertUTCToLocal(paramLong);
      return iField.get(paramLong);
    }
    
    public String getAsShortText(int paramInt, Locale paramLocale)
    {
      return iField.getAsShortText(paramInt, paramLocale);
    }
    
    public String getAsShortText(long paramLong, Locale paramLocale)
    {
      paramLong = iZone.convertUTCToLocal(paramLong);
      return iField.getAsShortText(paramLong, paramLocale);
    }
    
    public String getAsText(int paramInt, Locale paramLocale)
    {
      return iField.getAsText(paramInt, paramLocale);
    }
    
    public String getAsText(long paramLong, Locale paramLocale)
    {
      paramLong = iZone.convertUTCToLocal(paramLong);
      return iField.getAsText(paramLong, paramLocale);
    }
    
    public int getDifference(long paramLong1, long paramLong2)
    {
      int j = getOffsetToAdd(paramLong2);
      DateTimeField localDateTimeField = iField;
      if (iTimeField) {}
      for (int i = j;; i = getOffsetToAdd(paramLong1)) {
        return localDateTimeField.getDifference(i + paramLong1, j + paramLong2);
      }
    }
    
    public long getDifferenceAsLong(long paramLong1, long paramLong2)
    {
      int j = getOffsetToAdd(paramLong2);
      DateTimeField localDateTimeField = iField;
      if (iTimeField) {}
      for (int i = j;; i = getOffsetToAdd(paramLong1)) {
        return localDateTimeField.getDifferenceAsLong(i + paramLong1, j + paramLong2);
      }
    }
    
    public final DurationField getDurationField()
    {
      return iDurationField;
    }
    
    public int getLeapAmount(long paramLong)
    {
      paramLong = iZone.convertUTCToLocal(paramLong);
      return iField.getLeapAmount(paramLong);
    }
    
    public final DurationField getLeapDurationField()
    {
      return iLeapDurationField;
    }
    
    public int getMaximumShortTextLength(Locale paramLocale)
    {
      return iField.getMaximumShortTextLength(paramLocale);
    }
    
    public int getMaximumTextLength(Locale paramLocale)
    {
      return iField.getMaximumTextLength(paramLocale);
    }
    
    public int getMaximumValue()
    {
      return iField.getMaximumValue();
    }
    
    public int getMaximumValue(long paramLong)
    {
      paramLong = iZone.convertUTCToLocal(paramLong);
      return iField.getMaximumValue(paramLong);
    }
    
    public int getMaximumValue(ReadablePartial paramReadablePartial)
    {
      return iField.getMaximumValue(paramReadablePartial);
    }
    
    public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
    {
      return iField.getMaximumValue(paramReadablePartial, paramArrayOfInt);
    }
    
    public int getMinimumValue()
    {
      return iField.getMinimumValue();
    }
    
    public int getMinimumValue(long paramLong)
    {
      paramLong = iZone.convertUTCToLocal(paramLong);
      return iField.getMinimumValue(paramLong);
    }
    
    public int getMinimumValue(ReadablePartial paramReadablePartial)
    {
      return iField.getMinimumValue(paramReadablePartial);
    }
    
    public int getMinimumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
    {
      return iField.getMinimumValue(paramReadablePartial, paramArrayOfInt);
    }
    
    public final DurationField getRangeDurationField()
    {
      return iRangeDurationField;
    }
    
    public int hashCode()
    {
      return iField.hashCode() ^ iZone.hashCode();
    }
    
    public boolean isLeap(long paramLong)
    {
      paramLong = iZone.convertUTCToLocal(paramLong);
      return iField.isLeap(paramLong);
    }
    
    public boolean isLenient()
    {
      return iField.isLenient();
    }
    
    public long remainder(long paramLong)
    {
      paramLong = iZone.convertUTCToLocal(paramLong);
      return iField.remainder(paramLong);
    }
    
    public long roundCeiling(long paramLong)
    {
      if (iTimeField)
      {
        int i = getOffsetToAdd(paramLong);
        return iField.roundCeiling(i + paramLong) - i;
      }
      long l = iZone.convertUTCToLocal(paramLong);
      l = iField.roundCeiling(l);
      return iZone.convertLocalToUTC(l, false, paramLong);
    }
    
    public long roundFloor(long paramLong)
    {
      if (iTimeField)
      {
        int i = getOffsetToAdd(paramLong);
        return iField.roundFloor(i + paramLong) - i;
      }
      long l = iZone.convertUTCToLocal(paramLong);
      l = iField.roundFloor(l);
      return iZone.convertLocalToUTC(l, false, paramLong);
    }
    
    public long set(long paramLong, int paramInt)
    {
      long l = iZone.convertUTCToLocal(paramLong);
      l = iField.set(l, paramInt);
      paramLong = iZone.convertLocalToUTC(l, false, paramLong);
      if (get(paramLong) != paramInt)
      {
        IllegalInstantException localIllegalInstantException = new IllegalInstantException(l, iZone.getID());
        IllegalFieldValueException localIllegalFieldValueException = new IllegalFieldValueException(iField.getType(), Integer.valueOf(paramInt), localIllegalInstantException.getMessage());
        localIllegalFieldValueException.initCause(localIllegalInstantException);
        throw localIllegalFieldValueException;
      }
      return paramLong;
    }
    
    public long set(long paramLong, String paramString, Locale paramLocale)
    {
      long l = iZone.convertUTCToLocal(paramLong);
      l = iField.set(l, paramString, paramLocale);
      return iZone.convertLocalToUTC(l, false, paramLong);
    }
  }
  
  static class ZonedDurationField
    extends BaseDurationField
  {
    private static final long serialVersionUID = -485345310999208286L;
    final DurationField iField;
    final boolean iTimeField;
    final DateTimeZone iZone;
    
    ZonedDurationField(DurationField paramDurationField, DateTimeZone paramDateTimeZone)
    {
      super();
      if (!paramDurationField.isSupported()) {
        throw new IllegalArgumentException();
      }
      iField = paramDurationField;
      iTimeField = ZonedChronology.useTimeArithmetic(paramDurationField);
      iZone = paramDateTimeZone;
    }
    
    private long addOffset(long paramLong)
    {
      return iZone.convertUTCToLocal(paramLong);
    }
    
    private int getOffsetFromLocalToSubtract(long paramLong)
    {
      int i = iZone.getOffsetFromLocal(paramLong);
      if (((paramLong ^ paramLong - i) < 0L) && ((i ^ paramLong) < 0L)) {
        throw new ArithmeticException("Subtracting time zone offset caused overflow");
      }
      return i;
    }
    
    private int getOffsetToAdd(long paramLong)
    {
      int i = iZone.getOffset(paramLong);
      if (((paramLong ^ paramLong + i) < 0L) && ((i ^ paramLong) >= 0L)) {
        throw new ArithmeticException("Adding time zone offset caused overflow");
      }
      return i;
    }
    
    public long add(long paramLong, int paramInt)
    {
      int i = getOffsetToAdd(paramLong);
      paramLong = iField.add(i + paramLong, paramInt);
      if (iTimeField) {}
      for (paramInt = i;; paramInt = getOffsetFromLocalToSubtract(paramLong)) {
        return paramLong - paramInt;
      }
    }
    
    public long add(long paramLong1, long paramLong2)
    {
      int i = getOffsetToAdd(paramLong1);
      paramLong1 = iField.add(i + paramLong1, paramLong2);
      if (iTimeField) {}
      for (;;)
      {
        return paramLong1 - i;
        i = getOffsetFromLocalToSubtract(paramLong1);
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof ZonedDurationField)) {
          break;
        }
        paramObject = (ZonedDurationField)paramObject;
      } while ((iField.equals(iField)) && (iZone.equals(iZone)));
      return false;
      return false;
    }
    
    public int getDifference(long paramLong1, long paramLong2)
    {
      int j = getOffsetToAdd(paramLong2);
      DurationField localDurationField = iField;
      if (iTimeField) {}
      for (int i = j;; i = getOffsetToAdd(paramLong1)) {
        return localDurationField.getDifference(i + paramLong1, j + paramLong2);
      }
    }
    
    public long getDifferenceAsLong(long paramLong1, long paramLong2)
    {
      int j = getOffsetToAdd(paramLong2);
      DurationField localDurationField = iField;
      if (iTimeField) {}
      for (int i = j;; i = getOffsetToAdd(paramLong1)) {
        return localDurationField.getDifferenceAsLong(i + paramLong1, j + paramLong2);
      }
    }
    
    public long getMillis(int paramInt, long paramLong)
    {
      return iField.getMillis(paramInt, addOffset(paramLong));
    }
    
    public long getMillis(long paramLong1, long paramLong2)
    {
      return iField.getMillis(paramLong1, addOffset(paramLong2));
    }
    
    public long getUnitMillis()
    {
      return iField.getUnitMillis();
    }
    
    public int getValue(long paramLong1, long paramLong2)
    {
      return iField.getValue(paramLong1, addOffset(paramLong2));
    }
    
    public long getValueAsLong(long paramLong1, long paramLong2)
    {
      return iField.getValueAsLong(paramLong1, addOffset(paramLong2));
    }
    
    public int hashCode()
    {
      return iField.hashCode() ^ iZone.hashCode();
    }
    
    public boolean isPrecise()
    {
      if (iTimeField) {
        return iField.isPrecise();
      }
      return (iField.isPrecise()) && (iZone.isFixed());
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.ZonedChronology
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */