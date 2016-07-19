package org.joda.time.tz;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.chrono.ISOChronology;

final class DateTimeZoneBuilder$OfYear
{
  final boolean iAdvance;
  final int iDayOfMonth;
  final int iDayOfWeek;
  final int iMillisOfDay;
  final char iMode;
  final int iMonthOfYear;
  
  DateTimeZoneBuilder$OfYear(char paramChar, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4)
  {
    if ((paramChar != 'u') && (paramChar != 'w') && (paramChar != 's')) {
      throw new IllegalArgumentException("Unknown mode: " + paramChar);
    }
    iMode = paramChar;
    iMonthOfYear = paramInt1;
    iDayOfMonth = paramInt2;
    iDayOfWeek = paramInt3;
    iAdvance = paramBoolean;
    iMillisOfDay = paramInt4;
  }
  
  static OfYear readFrom(DataInput paramDataInput)
    throws IOException
  {
    return new OfYear((char)paramDataInput.readUnsignedByte(), paramDataInput.readUnsignedByte(), paramDataInput.readByte(), paramDataInput.readUnsignedByte(), paramDataInput.readBoolean(), (int)DateTimeZoneBuilder.readMillis(paramDataInput));
  }
  
  private long setDayOfMonth(Chronology paramChronology, long paramLong)
  {
    if (iDayOfMonth >= 0) {
      return paramChronology.dayOfMonth().set(paramLong, iDayOfMonth);
    }
    paramLong = paramChronology.dayOfMonth().set(paramLong, 1);
    paramLong = paramChronology.monthOfYear().add(paramLong, 1);
    return paramChronology.dayOfMonth().add(paramLong, iDayOfMonth);
  }
  
  private long setDayOfMonthNext(Chronology paramChronology, long paramLong)
  {
    try
    {
      long l = setDayOfMonth(paramChronology, paramLong);
      return l;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      if ((iMonthOfYear == 2) && (iDayOfMonth == 29))
      {
        while (!paramChronology.year().isLeap(paramLong)) {
          paramLong = paramChronology.year().add(paramLong, 1);
        }
        return setDayOfMonth(paramChronology, paramLong);
      }
      throw localIllegalArgumentException;
    }
  }
  
  private long setDayOfMonthPrevious(Chronology paramChronology, long paramLong)
  {
    try
    {
      long l = setDayOfMonth(paramChronology, paramLong);
      return l;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      if ((iMonthOfYear == 2) && (iDayOfMonth == 29))
      {
        while (!paramChronology.year().isLeap(paramLong)) {
          paramLong = paramChronology.year().add(paramLong, -1);
        }
        return setDayOfMonth(paramChronology, paramLong);
      }
      throw localIllegalArgumentException;
    }
  }
  
  private long setDayOfWeek(Chronology paramChronology, long paramLong)
  {
    int i = paramChronology.dayOfWeek().get(paramLong);
    int j = iDayOfWeek - i;
    long l = paramLong;
    if (j != 0)
    {
      if (!iAdvance) {
        break label65;
      }
      i = j;
      if (j < 0) {
        i = j + 7;
      }
    }
    for (;;)
    {
      l = paramChronology.dayOfWeek().add(paramLong, i);
      return l;
      label65:
      i = j;
      if (j > 0) {
        i = j - 7;
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof OfYear)) {
        break;
      }
      paramObject = (OfYear)paramObject;
    } while ((iMode == iMode) && (iMonthOfYear == iMonthOfYear) && (iDayOfMonth == iDayOfMonth) && (iDayOfWeek == iDayOfWeek) && (iAdvance == iAdvance) && (iMillisOfDay == iMillisOfDay));
    return false;
    return false;
  }
  
  public long next(long paramLong, int paramInt1, int paramInt2)
  {
    long l2;
    ISOChronology localISOChronology;
    long l1;
    if (iMode == 'w')
    {
      paramInt1 += paramInt2;
      l2 = paramLong + paramInt1;
      localISOChronology = ISOChronology.getInstanceUTC();
      paramLong = localISOChronology.monthOfYear().set(l2, iMonthOfYear);
      paramLong = localISOChronology.millisOfDay().set(paramLong, 0);
      l1 = setDayOfMonthNext(localISOChronology, localISOChronology.millisOfDay().add(paramLong, iMillisOfDay));
      if (iDayOfWeek != 0) {
        break label130;
      }
      paramLong = l1;
      if (l1 <= l2) {
        paramLong = setDayOfMonthNext(localISOChronology, localISOChronology.year().add(l1, 1));
      }
    }
    for (;;)
    {
      return paramLong - paramInt1;
      if (iMode == 's') {
        break;
      }
      paramInt1 = 0;
      break;
      label130:
      l1 = setDayOfWeek(localISOChronology, l1);
      paramLong = l1;
      if (l1 <= l2)
      {
        paramLong = localISOChronology.year().add(l1, 1);
        paramLong = setDayOfWeek(localISOChronology, setDayOfMonthNext(localISOChronology, localISOChronology.monthOfYear().set(paramLong, iMonthOfYear)));
      }
    }
  }
  
  public long previous(long paramLong, int paramInt1, int paramInt2)
  {
    long l2;
    ISOChronology localISOChronology;
    long l1;
    if (iMode == 'w')
    {
      paramInt1 += paramInt2;
      l2 = paramLong + paramInt1;
      localISOChronology = ISOChronology.getInstanceUTC();
      paramLong = localISOChronology.monthOfYear().set(l2, iMonthOfYear);
      paramLong = localISOChronology.millisOfDay().set(paramLong, 0);
      l1 = setDayOfMonthPrevious(localISOChronology, localISOChronology.millisOfDay().add(paramLong, iMillisOfDay));
      if (iDayOfWeek != 0) {
        break label130;
      }
      paramLong = l1;
      if (l1 >= l2) {
        paramLong = setDayOfMonthPrevious(localISOChronology, localISOChronology.year().add(l1, -1));
      }
    }
    for (;;)
    {
      return paramLong - paramInt1;
      if (iMode == 's') {
        break;
      }
      paramInt1 = 0;
      break;
      label130:
      l1 = setDayOfWeek(localISOChronology, l1);
      paramLong = l1;
      if (l1 >= l2)
      {
        paramLong = localISOChronology.year().add(l1, -1);
        paramLong = setDayOfWeek(localISOChronology, setDayOfMonthPrevious(localISOChronology, localISOChronology.monthOfYear().set(paramLong, iMonthOfYear)));
      }
    }
  }
  
  public long setInstant(int paramInt1, int paramInt2, int paramInt3)
  {
    if (iMode == 'w') {
      paramInt2 += paramInt3;
    }
    for (;;)
    {
      ISOChronology localISOChronology = ISOChronology.getInstanceUTC();
      long l1 = localISOChronology.year().set(0L, paramInt1);
      l1 = localISOChronology.monthOfYear().set(l1, iMonthOfYear);
      long l2 = setDayOfMonth(localISOChronology, localISOChronology.millisOfDay().set(l1, iMillisOfDay));
      l1 = l2;
      if (iDayOfWeek != 0) {
        l1 = setDayOfWeek(localISOChronology, l2);
      }
      return l1 - paramInt2;
      if (iMode != 's') {
        paramInt2 = 0;
      }
    }
  }
  
  public void writeTo(DataOutput paramDataOutput)
    throws IOException
  {
    paramDataOutput.writeByte(iMode);
    paramDataOutput.writeByte(iMonthOfYear);
    paramDataOutput.writeByte(iDayOfMonth);
    paramDataOutput.writeByte(iDayOfWeek);
    paramDataOutput.writeBoolean(iAdvance);
    DateTimeZoneBuilder.writeMillis(paramDataOutput, iMillisOfDay);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.DateTimeZoneBuilder.OfYear
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */