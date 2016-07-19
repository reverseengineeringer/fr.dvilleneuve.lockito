package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.IllegalInstantException;
import org.joda.time.ReadablePartial;
import org.joda.time.field.BaseDateTimeField;

final class ZonedChronology$ZonedDateTimeField
  extends BaseDateTimeField
{
  private static final long serialVersionUID = -3968986277775529794L;
  final DurationField iDurationField;
  final DateTimeField iField;
  final DurationField iLeapDurationField;
  final DurationField iRangeDurationField;
  final boolean iTimeField;
  final DateTimeZone iZone;
  
  ZonedChronology$ZonedDateTimeField(DateTimeField paramDateTimeField, DateTimeZone paramDateTimeZone, DurationField paramDurationField1, DurationField paramDurationField2, DurationField paramDurationField3)
  {
    super(paramDateTimeField.getType());
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

/* Location:
 * Qualified Name:     org.joda.time.chrono.ZonedChronology.ZonedDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */