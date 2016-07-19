package org.joda.time.chrono;

import java.util.HashMap;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadableDateTime;
import org.joda.time.field.DecoratedDateTimeField;
import org.joda.time.field.DecoratedDurationField;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class LimitChronology
  extends AssembledChronology
{
  private static final long serialVersionUID = 7670866536893052522L;
  final DateTime iLowerLimit;
  final DateTime iUpperLimit;
  private transient LimitChronology iWithUTC;
  
  private LimitChronology(Chronology paramChronology, DateTime paramDateTime1, DateTime paramDateTime2)
  {
    super(paramChronology, null);
    iLowerLimit = paramDateTime1;
    iUpperLimit = paramDateTime2;
  }
  
  private DateTimeField convertField(DateTimeField paramDateTimeField, HashMap<Object, Object> paramHashMap)
  {
    if ((paramDateTimeField == null) || (!paramDateTimeField.isSupported())) {
      return paramDateTimeField;
    }
    if (paramHashMap.containsKey(paramDateTimeField)) {
      return (DateTimeField)paramHashMap.get(paramDateTimeField);
    }
    LimitDateTimeField localLimitDateTimeField = new LimitDateTimeField(paramDateTimeField, convertField(paramDateTimeField.getDurationField(), paramHashMap), convertField(paramDateTimeField.getRangeDurationField(), paramHashMap), convertField(paramDateTimeField.getLeapDurationField(), paramHashMap));
    paramHashMap.put(paramDateTimeField, localLimitDateTimeField);
    return localLimitDateTimeField;
  }
  
  private DurationField convertField(DurationField paramDurationField, HashMap<Object, Object> paramHashMap)
  {
    if ((paramDurationField == null) || (!paramDurationField.isSupported())) {
      return paramDurationField;
    }
    if (paramHashMap.containsKey(paramDurationField)) {
      return (DurationField)paramHashMap.get(paramDurationField);
    }
    LimitDurationField localLimitDurationField = new LimitDurationField(paramDurationField);
    paramHashMap.put(paramDurationField, localLimitDurationField);
    return localLimitDurationField;
  }
  
  public static LimitChronology getInstance(Chronology paramChronology, ReadableDateTime paramReadableDateTime1, ReadableDateTime paramReadableDateTime2)
  {
    if (paramChronology == null) {
      throw new IllegalArgumentException("Must supply a chronology");
    }
    if (paramReadableDateTime1 == null)
    {
      paramReadableDateTime1 = null;
      if (paramReadableDateTime2 != null) {
        break label64;
      }
    }
    label64:
    for (paramReadableDateTime2 = null;; paramReadableDateTime2 = paramReadableDateTime2.toDateTime())
    {
      if ((paramReadableDateTime1 == null) || (paramReadableDateTime2 == null) || (paramReadableDateTime1.isBefore(paramReadableDateTime2))) {
        break label74;
      }
      throw new IllegalArgumentException("The lower limit must be come before than the upper limit");
      paramReadableDateTime1 = paramReadableDateTime1.toDateTime();
      break;
    }
    label74:
    return new LimitChronology(paramChronology, (DateTime)paramReadableDateTime1, (DateTime)paramReadableDateTime2);
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
  
  void checkLimits(long paramLong, String paramString)
  {
    DateTime localDateTime = iLowerLimit;
    if ((localDateTime != null) && (paramLong < localDateTime.getMillis())) {
      throw new LimitException(paramString, true);
    }
    localDateTime = iUpperLimit;
    if ((localDateTime != null) && (paramLong >= localDateTime.getMillis())) {
      throw new LimitException(paramString, false);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof LimitChronology)) {
        return false;
      }
      paramObject = (LimitChronology)paramObject;
    } while ((getBase().equals(((LimitChronology)paramObject).getBase())) && (FieldUtils.equals(getLowerLimit(), ((LimitChronology)paramObject).getLowerLimit())) && (FieldUtils.equals(getUpperLimit(), ((LimitChronology)paramObject).getUpperLimit())));
    return false;
  }
  
  public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IllegalArgumentException
  {
    long l = getBase().getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4);
    checkLimits(l, "resulting");
    return l;
  }
  
  public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
    throws IllegalArgumentException
  {
    long l = getBase().getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
    checkLimits(l, "resulting");
    return l;
  }
  
  public long getDateTimeMillis(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IllegalArgumentException
  {
    checkLimits(paramLong, null);
    paramLong = getBase().getDateTimeMillis(paramLong, paramInt1, paramInt2, paramInt3, paramInt4);
    checkLimits(paramLong, "resulting");
    return paramLong;
  }
  
  public DateTime getLowerLimit()
  {
    return iLowerLimit;
  }
  
  public DateTime getUpperLimit()
  {
    return iUpperLimit;
  }
  
  public int hashCode()
  {
    int j = 0;
    if (getLowerLimit() != null) {}
    for (int i = getLowerLimit().hashCode();; i = 0)
    {
      if (getUpperLimit() != null) {
        j = getUpperLimit().hashCode();
      }
      return 317351877 + i + j + getBase().hashCode() * 7;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("LimitChronology[").append(getBase().toString()).append(", ");
    if (getLowerLimit() == null)
    {
      str = "NoLimit";
      localStringBuilder = localStringBuilder.append(str).append(", ");
      if (getUpperLimit() != null) {
        break label89;
      }
    }
    label89:
    for (String str = "NoLimit";; str = getUpperLimit().toString())
    {
      return str + ']';
      str = getLowerLimit().toString();
      break;
    }
  }
  
  public Chronology withUTC()
  {
    return withZone(DateTimeZone.UTC);
  }
  
  public Chronology withZone(DateTimeZone paramDateTimeZone)
  {
    DateTimeZone localDateTimeZone = paramDateTimeZone;
    if (paramDateTimeZone == null) {
      localDateTimeZone = DateTimeZone.getDefault();
    }
    if (localDateTimeZone == getZone()) {
      return this;
    }
    if ((localDateTimeZone == DateTimeZone.UTC) && (iWithUTC != null)) {
      return iWithUTC;
    }
    Object localObject = iLowerLimit;
    paramDateTimeZone = (DateTimeZone)localObject;
    if (localObject != null)
    {
      paramDateTimeZone = ((DateTime)localObject).toMutableDateTime();
      paramDateTimeZone.setZoneRetainFields(localDateTimeZone);
      paramDateTimeZone = paramDateTimeZone.toDateTime();
    }
    DateTime localDateTime = iUpperLimit;
    localObject = localDateTime;
    if (localDateTime != null)
    {
      localObject = localDateTime.toMutableDateTime();
      ((MutableDateTime)localObject).setZoneRetainFields(localDateTimeZone);
      localObject = ((MutableDateTime)localObject).toDateTime();
    }
    paramDateTimeZone = getInstance(getBase().withZone(localDateTimeZone), paramDateTimeZone, (ReadableDateTime)localObject);
    if (localDateTimeZone == DateTimeZone.UTC) {
      iWithUTC = paramDateTimeZone;
    }
    return paramDateTimeZone;
  }
  
  private class LimitDateTimeField
    extends DecoratedDateTimeField
  {
    private static final long serialVersionUID = -2435306746995699312L;
    private final DurationField iDurationField;
    private final DurationField iLeapDurationField;
    private final DurationField iRangeDurationField;
    
    LimitDateTimeField(DateTimeField paramDateTimeField, DurationField paramDurationField1, DurationField paramDurationField2, DurationField paramDurationField3)
    {
      super(paramDateTimeField.getType());
      iDurationField = paramDurationField1;
      iRangeDurationField = paramDurationField2;
      iLeapDurationField = paramDurationField3;
    }
    
    public long add(long paramLong, int paramInt)
    {
      checkLimits(paramLong, null);
      paramLong = getWrappedField().add(paramLong, paramInt);
      checkLimits(paramLong, "resulting");
      return paramLong;
    }
    
    public long add(long paramLong1, long paramLong2)
    {
      checkLimits(paramLong1, null);
      paramLong1 = getWrappedField().add(paramLong1, paramLong2);
      checkLimits(paramLong1, "resulting");
      return paramLong1;
    }
    
    public long addWrapField(long paramLong, int paramInt)
    {
      checkLimits(paramLong, null);
      paramLong = getWrappedField().addWrapField(paramLong, paramInt);
      checkLimits(paramLong, "resulting");
      return paramLong;
    }
    
    public int get(long paramLong)
    {
      checkLimits(paramLong, null);
      return getWrappedField().get(paramLong);
    }
    
    public String getAsShortText(long paramLong, Locale paramLocale)
    {
      checkLimits(paramLong, null);
      return getWrappedField().getAsShortText(paramLong, paramLocale);
    }
    
    public String getAsText(long paramLong, Locale paramLocale)
    {
      checkLimits(paramLong, null);
      return getWrappedField().getAsText(paramLong, paramLocale);
    }
    
    public int getDifference(long paramLong1, long paramLong2)
    {
      checkLimits(paramLong1, "minuend");
      checkLimits(paramLong2, "subtrahend");
      return getWrappedField().getDifference(paramLong1, paramLong2);
    }
    
    public long getDifferenceAsLong(long paramLong1, long paramLong2)
    {
      checkLimits(paramLong1, "minuend");
      checkLimits(paramLong2, "subtrahend");
      return getWrappedField().getDifferenceAsLong(paramLong1, paramLong2);
    }
    
    public final DurationField getDurationField()
    {
      return iDurationField;
    }
    
    public int getLeapAmount(long paramLong)
    {
      checkLimits(paramLong, null);
      return getWrappedField().getLeapAmount(paramLong);
    }
    
    public final DurationField getLeapDurationField()
    {
      return iLeapDurationField;
    }
    
    public int getMaximumShortTextLength(Locale paramLocale)
    {
      return getWrappedField().getMaximumShortTextLength(paramLocale);
    }
    
    public int getMaximumTextLength(Locale paramLocale)
    {
      return getWrappedField().getMaximumTextLength(paramLocale);
    }
    
    public int getMaximumValue(long paramLong)
    {
      checkLimits(paramLong, null);
      return getWrappedField().getMaximumValue(paramLong);
    }
    
    public int getMinimumValue(long paramLong)
    {
      checkLimits(paramLong, null);
      return getWrappedField().getMinimumValue(paramLong);
    }
    
    public final DurationField getRangeDurationField()
    {
      return iRangeDurationField;
    }
    
    public boolean isLeap(long paramLong)
    {
      checkLimits(paramLong, null);
      return getWrappedField().isLeap(paramLong);
    }
    
    public long remainder(long paramLong)
    {
      checkLimits(paramLong, null);
      paramLong = getWrappedField().remainder(paramLong);
      checkLimits(paramLong, "resulting");
      return paramLong;
    }
    
    public long roundCeiling(long paramLong)
    {
      checkLimits(paramLong, null);
      paramLong = getWrappedField().roundCeiling(paramLong);
      checkLimits(paramLong, "resulting");
      return paramLong;
    }
    
    public long roundFloor(long paramLong)
    {
      checkLimits(paramLong, null);
      paramLong = getWrappedField().roundFloor(paramLong);
      checkLimits(paramLong, "resulting");
      return paramLong;
    }
    
    public long roundHalfCeiling(long paramLong)
    {
      checkLimits(paramLong, null);
      paramLong = getWrappedField().roundHalfCeiling(paramLong);
      checkLimits(paramLong, "resulting");
      return paramLong;
    }
    
    public long roundHalfEven(long paramLong)
    {
      checkLimits(paramLong, null);
      paramLong = getWrappedField().roundHalfEven(paramLong);
      checkLimits(paramLong, "resulting");
      return paramLong;
    }
    
    public long roundHalfFloor(long paramLong)
    {
      checkLimits(paramLong, null);
      paramLong = getWrappedField().roundHalfFloor(paramLong);
      checkLimits(paramLong, "resulting");
      return paramLong;
    }
    
    public long set(long paramLong, int paramInt)
    {
      checkLimits(paramLong, null);
      paramLong = getWrappedField().set(paramLong, paramInt);
      checkLimits(paramLong, "resulting");
      return paramLong;
    }
    
    public long set(long paramLong, String paramString, Locale paramLocale)
    {
      checkLimits(paramLong, null);
      paramLong = getWrappedField().set(paramLong, paramString, paramLocale);
      checkLimits(paramLong, "resulting");
      return paramLong;
    }
  }
  
  private class LimitDurationField
    extends DecoratedDurationField
  {
    private static final long serialVersionUID = 8049297699408782284L;
    
    LimitDurationField(DurationField paramDurationField)
    {
      super(paramDurationField.getType());
    }
    
    public long add(long paramLong, int paramInt)
    {
      checkLimits(paramLong, null);
      paramLong = getWrappedField().add(paramLong, paramInt);
      checkLimits(paramLong, "resulting");
      return paramLong;
    }
    
    public long add(long paramLong1, long paramLong2)
    {
      checkLimits(paramLong1, null);
      paramLong1 = getWrappedField().add(paramLong1, paramLong2);
      checkLimits(paramLong1, "resulting");
      return paramLong1;
    }
    
    public int getDifference(long paramLong1, long paramLong2)
    {
      checkLimits(paramLong1, "minuend");
      checkLimits(paramLong2, "subtrahend");
      return getWrappedField().getDifference(paramLong1, paramLong2);
    }
    
    public long getDifferenceAsLong(long paramLong1, long paramLong2)
    {
      checkLimits(paramLong1, "minuend");
      checkLimits(paramLong2, "subtrahend");
      return getWrappedField().getDifferenceAsLong(paramLong1, paramLong2);
    }
    
    public long getMillis(int paramInt, long paramLong)
    {
      checkLimits(paramLong, null);
      return getWrappedField().getMillis(paramInt, paramLong);
    }
    
    public long getMillis(long paramLong1, long paramLong2)
    {
      checkLimits(paramLong2, null);
      return getWrappedField().getMillis(paramLong1, paramLong2);
    }
    
    public int getValue(long paramLong1, long paramLong2)
    {
      checkLimits(paramLong2, null);
      return getWrappedField().getValue(paramLong1, paramLong2);
    }
    
    public long getValueAsLong(long paramLong1, long paramLong2)
    {
      checkLimits(paramLong2, null);
      return getWrappedField().getValueAsLong(paramLong1, paramLong2);
    }
  }
  
  private class LimitException
    extends IllegalArgumentException
  {
    private static final long serialVersionUID = -5924689995607498581L;
    private final boolean iIsLow;
    
    LimitException(String paramString, boolean paramBoolean)
    {
      super();
      iIsLow = paramBoolean;
    }
    
    public String getMessage()
    {
      StringBuffer localStringBuffer = new StringBuffer(85);
      localStringBuffer.append("The");
      Object localObject = super.getMessage();
      if (localObject != null)
      {
        localStringBuffer.append(' ');
        localStringBuffer.append((String)localObject);
      }
      localStringBuffer.append(" instant is ");
      localObject = ISODateTimeFormat.dateTime().withChronology(getBase());
      if (iIsLow)
      {
        localStringBuffer.append("below the supported minimum of ");
        ((DateTimeFormatter)localObject).printTo(localStringBuffer, getLowerLimit().getMillis());
      }
      for (;;)
      {
        localStringBuffer.append(" (");
        localStringBuffer.append(getBase());
        localStringBuffer.append(')');
        return localStringBuffer.toString();
        localStringBuffer.append("above the supported maximum of ");
        ((DateTimeFormatter)localObject).printTo(localStringBuffer, getUpperLimit().getMillis());
      }
    }
    
    public String toString()
    {
      return "IllegalArgumentException: " + getMessage();
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.LimitChronology
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */