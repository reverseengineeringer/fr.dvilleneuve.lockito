package org.joda.time;

class DateTimeUtils$OffsetMillisProvider
  implements DateTimeUtils.MillisProvider
{
  private final long iMillis;
  
  DateTimeUtils$OffsetMillisProvider(long paramLong)
  {
    iMillis = paramLong;
  }
  
  public long getMillis()
  {
    return System.currentTimeMillis() + iMillis;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.DateTimeUtils.OffsetMillisProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */