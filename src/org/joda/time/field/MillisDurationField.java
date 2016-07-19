package org.joda.time.field;

import java.io.Serializable;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public final class MillisDurationField
  extends DurationField
  implements Serializable
{
  public static final DurationField INSTANCE = new MillisDurationField();
  private static final long serialVersionUID = 2656707858124633367L;
  
  private Object readResolve()
  {
    return INSTANCE;
  }
  
  public long add(long paramLong, int paramInt)
  {
    return FieldUtils.safeAdd(paramLong, paramInt);
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    return FieldUtils.safeAdd(paramLong1, paramLong2);
  }
  
  public int compareTo(DurationField paramDurationField)
  {
    long l1 = paramDurationField.getUnitMillis();
    long l2 = getUnitMillis();
    if (l2 == l1) {
      return 0;
    }
    if (l2 < l1) {
      return -1;
    }
    return 1;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof MillisDurationField))
    {
      bool1 = bool2;
      if (getUnitMillis() == ((MillisDurationField)paramObject).getUnitMillis()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public int getDifference(long paramLong1, long paramLong2)
  {
    return FieldUtils.safeToInt(FieldUtils.safeSubtract(paramLong1, paramLong2));
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    return FieldUtils.safeSubtract(paramLong1, paramLong2);
  }
  
  public long getMillis(int paramInt)
  {
    return paramInt;
  }
  
  public long getMillis(int paramInt, long paramLong)
  {
    return paramInt;
  }
  
  public long getMillis(long paramLong)
  {
    return paramLong;
  }
  
  public long getMillis(long paramLong1, long paramLong2)
  {
    return paramLong1;
  }
  
  public String getName()
  {
    return "millis";
  }
  
  public DurationFieldType getType()
  {
    return DurationFieldType.millis();
  }
  
  public final long getUnitMillis()
  {
    return 1L;
  }
  
  public int getValue(long paramLong)
  {
    return FieldUtils.safeToInt(paramLong);
  }
  
  public int getValue(long paramLong1, long paramLong2)
  {
    return FieldUtils.safeToInt(paramLong1);
  }
  
  public long getValueAsLong(long paramLong)
  {
    return paramLong;
  }
  
  public long getValueAsLong(long paramLong1, long paramLong2)
  {
    return paramLong1;
  }
  
  public int hashCode()
  {
    return (int)getUnitMillis();
  }
  
  public final boolean isPrecise()
  {
    return true;
  }
  
  public boolean isSupported()
  {
    return true;
  }
  
  public String toString()
  {
    return "DurationField[millis]";
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.MillisDurationField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */