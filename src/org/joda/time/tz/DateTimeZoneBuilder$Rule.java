package org.joda.time.tz;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.chrono.ISOChronology;

final class DateTimeZoneBuilder$Rule
{
  final int iFromYear;
  final DateTimeZoneBuilder.Recurrence iRecurrence;
  final int iToYear;
  
  DateTimeZoneBuilder$Rule(DateTimeZoneBuilder.Recurrence paramRecurrence, int paramInt1, int paramInt2)
  {
    iRecurrence = paramRecurrence;
    iFromYear = paramInt1;
    iToYear = paramInt2;
  }
  
  public int getFromYear()
  {
    return iFromYear;
  }
  
  public String getNameKey()
  {
    return iRecurrence.getNameKey();
  }
  
  public DateTimeZoneBuilder.OfYear getOfYear()
  {
    return iRecurrence.getOfYear();
  }
  
  public int getSaveMillis()
  {
    return iRecurrence.getSaveMillis();
  }
  
  public int getToYear()
  {
    return iToYear;
  }
  
  public long next(long paramLong, int paramInt1, int paramInt2)
  {
    ISOChronology localISOChronology = ISOChronology.getInstanceUTC();
    int j = paramInt1 + paramInt2;
    long l1 = paramLong;
    if (paramLong == Long.MIN_VALUE) {}
    for (int i = Integer.MIN_VALUE;; i = localISOChronology.year().get(j + paramLong))
    {
      if (i < iFromYear) {
        l1 = localISOChronology.year().set(0L, iFromYear) - j - 1L;
      }
      long l2 = iRecurrence.next(l1, paramInt1, paramInt2);
      l1 = l2;
      if (l2 > paramLong)
      {
        l1 = l2;
        if (localISOChronology.year().get(j + l2) > iToYear) {
          l1 = paramLong;
        }
      }
      return l1;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.DateTimeZoneBuilder.Rule
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */