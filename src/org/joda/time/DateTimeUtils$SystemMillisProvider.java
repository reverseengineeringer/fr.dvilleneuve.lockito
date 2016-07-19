package org.joda.time;

class DateTimeUtils$SystemMillisProvider
  implements DateTimeUtils.MillisProvider
{
  public long getMillis()
  {
    return System.currentTimeMillis();
  }
}

/* Location:
 * Qualified Name:     org.joda.time.DateTimeUtils.SystemMillisProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */