package org.joda.time;

class DateTimeUtils$FixedMillisProvider
  implements DateTimeUtils.MillisProvider
{
  private final long iMillis;
  
  DateTimeUtils$FixedMillisProvider(long paramLong)
  {
    iMillis = paramLong;
  }
  
  public long getMillis()
  {
    return iMillis;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.DateTimeUtils.FixedMillisProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */