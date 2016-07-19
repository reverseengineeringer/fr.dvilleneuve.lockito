package org.joda.time;

public abstract class DurationField
  implements Comparable<DurationField>
{
  public abstract long add(long paramLong, int paramInt);
  
  public abstract long add(long paramLong1, long paramLong2);
  
  public abstract int getDifference(long paramLong1, long paramLong2);
  
  public abstract long getDifferenceAsLong(long paramLong1, long paramLong2);
  
  public abstract long getMillis(int paramInt);
  
  public abstract long getMillis(int paramInt, long paramLong);
  
  public abstract long getMillis(long paramLong);
  
  public abstract long getMillis(long paramLong1, long paramLong2);
  
  public abstract String getName();
  
  public abstract DurationFieldType getType();
  
  public abstract long getUnitMillis();
  
  public abstract int getValue(long paramLong);
  
  public abstract int getValue(long paramLong1, long paramLong2);
  
  public abstract long getValueAsLong(long paramLong);
  
  public abstract long getValueAsLong(long paramLong1, long paramLong2);
  
  public abstract boolean isPrecise();
  
  public abstract boolean isSupported();
  
  public long subtract(long paramLong, int paramInt)
  {
    if (paramInt == Integer.MIN_VALUE) {
      return subtract(paramLong, paramInt);
    }
    return add(paramLong, -paramInt);
  }
  
  public long subtract(long paramLong1, long paramLong2)
  {
    if (paramLong2 == Long.MIN_VALUE) {
      throw new ArithmeticException("Long.MIN_VALUE cannot be negated");
    }
    return add(paramLong1, -paramLong2);
  }
  
  public abstract String toString();
}

/* Location:
 * Qualified Name:     org.joda.time.DurationField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */