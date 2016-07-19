package org.joda.time.convert;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;

final class CalendarConverter
  extends AbstractConverter
  implements InstantConverter, PartialConverter
{
  static final CalendarConverter INSTANCE = new CalendarConverter();
  
  public Chronology getChronology(Object paramObject, Chronology paramChronology)
  {
    if (paramChronology != null) {
      return paramChronology;
    }
    paramChronology = (Calendar)paramObject;
    try
    {
      paramObject = DateTimeZone.forTimeZone(paramChronology.getTimeZone());
      return getChronology(paramChronology, (DateTimeZone)paramObject);
    }
    catch (IllegalArgumentException paramObject)
    {
      for (;;)
      {
        paramObject = DateTimeZone.getDefault();
      }
    }
  }
  
  public Chronology getChronology(Object paramObject, DateTimeZone paramDateTimeZone)
  {
    if (paramObject.getClass().getName().endsWith(".BuddhistCalendar")) {
      return BuddhistChronology.getInstance(paramDateTimeZone);
    }
    if ((paramObject instanceof GregorianCalendar))
    {
      long l = ((GregorianCalendar)paramObject).getGregorianChange().getTime();
      if (l == Long.MIN_VALUE) {
        return GregorianChronology.getInstance(paramDateTimeZone);
      }
      if (l == Long.MAX_VALUE) {
        return JulianChronology.getInstance(paramDateTimeZone);
      }
      return GJChronology.getInstance(paramDateTimeZone, l, 4);
    }
    return ISOChronology.getInstance(paramDateTimeZone);
  }
  
  public long getInstantMillis(Object paramObject, Chronology paramChronology)
  {
    return ((Calendar)paramObject).getTime().getTime();
  }
  
  public Class<?> getSupportedType()
  {
    return Calendar.class;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.convert.CalendarConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */