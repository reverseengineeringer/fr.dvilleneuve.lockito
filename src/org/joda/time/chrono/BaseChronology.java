package org.joda.time.chrono;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.UnsupportedDateTimeField;
import org.joda.time.field.UnsupportedDurationField;

public abstract class BaseChronology
  extends Chronology
  implements Serializable
{
  private static final long serialVersionUID = -7310865996721419676L;
  
  public long add(long paramLong1, long paramLong2, int paramInt)
  {
    if ((paramLong2 == 0L) || (paramInt == 0)) {
      return paramLong1;
    }
    return FieldUtils.safeAdd(paramLong1, FieldUtils.safeMultiply(paramLong2, paramInt));
  }
  
  public long add(ReadablePeriod paramReadablePeriod, long paramLong, int paramInt)
  {
    long l1 = paramLong;
    if (paramInt != 0)
    {
      l1 = paramLong;
      if (paramReadablePeriod != null)
      {
        int i = 0;
        int j = paramReadablePeriod.size();
        for (;;)
        {
          l1 = paramLong;
          if (i >= j) {
            break;
          }
          long l2 = paramReadablePeriod.getValue(i);
          l1 = paramLong;
          if (l2 != 0L) {
            l1 = paramReadablePeriod.getFieldType(i).getField(this).add(paramLong, paramInt * l2);
          }
          i += 1;
          paramLong = l1;
        }
      }
    }
    return l1;
  }
  
  public DurationField centuries()
  {
    return UnsupportedDurationField.getInstance(DurationFieldType.centuries());
  }
  
  public DateTimeField centuryOfEra()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.centuryOfEra(), centuries());
  }
  
  public DateTimeField clockhourOfDay()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.clockhourOfDay(), hours());
  }
  
  public DateTimeField clockhourOfHalfday()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.clockhourOfHalfday(), hours());
  }
  
  public DateTimeField dayOfMonth()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.dayOfMonth(), days());
  }
  
  public DateTimeField dayOfWeek()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.dayOfWeek(), days());
  }
  
  public DateTimeField dayOfYear()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.dayOfYear(), days());
  }
  
  public DurationField days()
  {
    return UnsupportedDurationField.getInstance(DurationFieldType.days());
  }
  
  public DateTimeField era()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.era(), eras());
  }
  
  public DurationField eras()
  {
    return UnsupportedDurationField.getInstance(DurationFieldType.eras());
  }
  
  public int[] get(ReadablePartial paramReadablePartial, long paramLong)
  {
    int j = paramReadablePartial.size();
    int[] arrayOfInt = new int[j];
    int i = 0;
    while (i < j)
    {
      arrayOfInt[i] = paramReadablePartial.getFieldType(i).getField(this).get(paramLong);
      i += 1;
    }
    return arrayOfInt;
  }
  
  public int[] get(ReadablePeriod paramReadablePeriod, long paramLong)
  {
    int j = paramReadablePeriod.size();
    int[] arrayOfInt = new int[j];
    if (paramLong != 0L)
    {
      long l1 = 0L;
      int i = 0;
      while (i < j)
      {
        DurationField localDurationField = paramReadablePeriod.getFieldType(i).getField(this);
        long l2 = l1;
        if (localDurationField.isPrecise())
        {
          int k = localDurationField.getDifference(paramLong, l1);
          l2 = localDurationField.add(l1, k);
          arrayOfInt[i] = k;
        }
        i += 1;
        l1 = l2;
      }
    }
    return arrayOfInt;
  }
  
  public int[] get(ReadablePeriod paramReadablePeriod, long paramLong1, long paramLong2)
  {
    int j = paramReadablePeriod.size();
    int[] arrayOfInt = new int[j];
    if (paramLong1 != paramLong2)
    {
      int i = 0;
      while (i < j)
      {
        DurationField localDurationField = paramReadablePeriod.getFieldType(i).getField(this);
        int k = localDurationField.getDifference(paramLong2, paramLong1);
        paramLong1 = localDurationField.add(paramLong1, k);
        arrayOfInt[i] = k;
        i += 1;
      }
    }
    return arrayOfInt;
  }
  
  public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IllegalArgumentException
  {
    long l = year().set(0L, paramInt1);
    l = monthOfYear().set(l, paramInt2);
    l = dayOfMonth().set(l, paramInt3);
    return millisOfDay().set(l, paramInt4);
  }
  
  public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
    throws IllegalArgumentException
  {
    long l = year().set(0L, paramInt1);
    l = monthOfYear().set(l, paramInt2);
    l = dayOfMonth().set(l, paramInt3);
    l = hourOfDay().set(l, paramInt4);
    l = minuteOfHour().set(l, paramInt5);
    l = secondOfMinute().set(l, paramInt6);
    return millisOfSecond().set(l, paramInt7);
  }
  
  public long getDateTimeMillis(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IllegalArgumentException
  {
    paramLong = hourOfDay().set(paramLong, paramInt1);
    paramLong = minuteOfHour().set(paramLong, paramInt2);
    paramLong = secondOfMinute().set(paramLong, paramInt3);
    return millisOfSecond().set(paramLong, paramInt4);
  }
  
  public abstract DateTimeZone getZone();
  
  public DateTimeField halfdayOfDay()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.halfdayOfDay(), halfdays());
  }
  
  public DurationField halfdays()
  {
    return UnsupportedDurationField.getInstance(DurationFieldType.halfdays());
  }
  
  public DateTimeField hourOfDay()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.hourOfDay(), hours());
  }
  
  public DateTimeField hourOfHalfday()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.hourOfHalfday(), hours());
  }
  
  public DurationField hours()
  {
    return UnsupportedDurationField.getInstance(DurationFieldType.hours());
  }
  
  public DurationField millis()
  {
    return UnsupportedDurationField.getInstance(DurationFieldType.millis());
  }
  
  public DateTimeField millisOfDay()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.millisOfDay(), millis());
  }
  
  public DateTimeField millisOfSecond()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.millisOfSecond(), millis());
  }
  
  public DateTimeField minuteOfDay()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.minuteOfDay(), minutes());
  }
  
  public DateTimeField minuteOfHour()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.minuteOfHour(), minutes());
  }
  
  public DurationField minutes()
  {
    return UnsupportedDurationField.getInstance(DurationFieldType.minutes());
  }
  
  public DateTimeField monthOfYear()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.monthOfYear(), months());
  }
  
  public DurationField months()
  {
    return UnsupportedDurationField.getInstance(DurationFieldType.months());
  }
  
  public DateTimeField secondOfDay()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.secondOfDay(), seconds());
  }
  
  public DateTimeField secondOfMinute()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.secondOfMinute(), seconds());
  }
  
  public DurationField seconds()
  {
    return UnsupportedDurationField.getInstance(DurationFieldType.seconds());
  }
  
  public long set(ReadablePartial paramReadablePartial, long paramLong)
  {
    int i = 0;
    int j = paramReadablePartial.size();
    while (i < j)
    {
      paramLong = paramReadablePartial.getFieldType(i).getField(this).set(paramLong, paramReadablePartial.getValue(i));
      i += 1;
    }
    return paramLong;
  }
  
  public abstract String toString();
  
  public void validate(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
  {
    int j = paramReadablePartial.size();
    int i = 0;
    int k;
    DateTimeField localDateTimeField;
    while (i < j)
    {
      k = paramArrayOfInt[i];
      localDateTimeField = paramReadablePartial.getField(i);
      if (k < localDateTimeField.getMinimumValue()) {
        throw new IllegalFieldValueException(localDateTimeField.getType(), Integer.valueOf(k), Integer.valueOf(localDateTimeField.getMinimumValue()), null);
      }
      if (k > localDateTimeField.getMaximumValue()) {
        throw new IllegalFieldValueException(localDateTimeField.getType(), Integer.valueOf(k), null, Integer.valueOf(localDateTimeField.getMaximumValue()));
      }
      i += 1;
    }
    i = 0;
    while (i < j)
    {
      k = paramArrayOfInt[i];
      localDateTimeField = paramReadablePartial.getField(i);
      if (k < localDateTimeField.getMinimumValue(paramReadablePartial, paramArrayOfInt)) {
        throw new IllegalFieldValueException(localDateTimeField.getType(), Integer.valueOf(k), Integer.valueOf(localDateTimeField.getMinimumValue(paramReadablePartial, paramArrayOfInt)), null);
      }
      if (k > localDateTimeField.getMaximumValue(paramReadablePartial, paramArrayOfInt)) {
        throw new IllegalFieldValueException(localDateTimeField.getType(), Integer.valueOf(k), null, Integer.valueOf(localDateTimeField.getMaximumValue(paramReadablePartial, paramArrayOfInt)));
      }
      i += 1;
    }
  }
  
  public DateTimeField weekOfWeekyear()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.weekOfWeekyear(), weeks());
  }
  
  public DurationField weeks()
  {
    return UnsupportedDurationField.getInstance(DurationFieldType.weeks());
  }
  
  public DateTimeField weekyear()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.weekyear(), weekyears());
  }
  
  public DateTimeField weekyearOfCentury()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.weekyearOfCentury(), weekyears());
  }
  
  public DurationField weekyears()
  {
    return UnsupportedDurationField.getInstance(DurationFieldType.weekyears());
  }
  
  public abstract Chronology withUTC();
  
  public abstract Chronology withZone(DateTimeZone paramDateTimeZone);
  
  public DateTimeField year()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.year(), years());
  }
  
  public DateTimeField yearOfCentury()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.yearOfCentury(), years());
  }
  
  public DateTimeField yearOfEra()
  {
    return UnsupportedDateTimeField.getInstance(DateTimeFieldType.yearOfEra(), years());
  }
  
  public DurationField years()
  {
    return UnsupportedDurationField.getInstance(DurationFieldType.years());
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.BaseChronology
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */