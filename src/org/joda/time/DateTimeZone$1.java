package org.joda.time;

import org.joda.time.chrono.BaseChronology;

final class DateTimeZone$1
  extends BaseChronology
{
  private static final long serialVersionUID = -3128740902654445468L;
  
  public DateTimeZone getZone()
  {
    return null;
  }
  
  public String toString()
  {
    return getClass().getName();
  }
  
  public Chronology withUTC()
  {
    return this;
  }
  
  public Chronology withZone(DateTimeZone paramDateTimeZone)
  {
    return this;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.DateTimeZone.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */