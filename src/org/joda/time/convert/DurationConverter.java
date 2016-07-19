package org.joda.time.convert;

public abstract interface DurationConverter
  extends Converter
{
  public abstract long getDurationMillis(Object paramObject);
}

/* Location:
 * Qualified Name:     org.joda.time.convert.DurationConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */