package org.joda.time.base;

import org.joda.convert.ToString;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.ReadableDuration;
import org.joda.time.format.FormatUtils;

public abstract class AbstractDuration
  implements ReadableDuration
{
  public int compareTo(ReadableDuration paramReadableDuration)
  {
    long l1 = getMillis();
    long l2 = paramReadableDuration.getMillis();
    if (l1 < l2) {
      return -1;
    }
    if (l1 > l2) {
      return 1;
    }
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof ReadableDuration)) {
        return false;
      }
      paramObject = (ReadableDuration)paramObject;
    } while (getMillis() == ((ReadableDuration)paramObject).getMillis());
    return false;
  }
  
  public int hashCode()
  {
    long l = getMillis();
    return (int)(l >>> 32 ^ l);
  }
  
  public boolean isEqual(ReadableDuration paramReadableDuration)
  {
    Object localObject = paramReadableDuration;
    if (paramReadableDuration == null) {
      localObject = Duration.ZERO;
    }
    return compareTo((ReadableDuration)localObject) == 0;
  }
  
  public boolean isLongerThan(ReadableDuration paramReadableDuration)
  {
    Object localObject = paramReadableDuration;
    if (paramReadableDuration == null) {
      localObject = Duration.ZERO;
    }
    return compareTo((ReadableDuration)localObject) > 0;
  }
  
  public boolean isShorterThan(ReadableDuration paramReadableDuration)
  {
    Object localObject = paramReadableDuration;
    if (paramReadableDuration == null) {
      localObject = Duration.ZERO;
    }
    return compareTo((ReadableDuration)localObject) < 0;
  }
  
  public Duration toDuration()
  {
    return new Duration(getMillis());
  }
  
  public Period toPeriod()
  {
    return new Period(getMillis());
  }
  
  @ToString
  public String toString()
  {
    long l = getMillis();
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("PT");
    int i;
    if (l < 0L)
    {
      i = 1;
      FormatUtils.appendUnpaddedInteger(localStringBuffer, l);
      label39:
      int k = localStringBuffer.length();
      if (i == 0) {
        break label80;
      }
      j = 7;
      label52:
      if (k >= j) {
        break label91;
      }
      if (i == 0) {
        break label86;
      }
    }
    label80:
    label86:
    for (int j = 3;; j = 2)
    {
      localStringBuffer.insert(j, "0");
      break label39;
      i = 0;
      break;
      j = 6;
      break label52;
    }
    label91:
    if (l / 1000L * 1000L == l) {
      localStringBuffer.setLength(localStringBuffer.length() - 3);
    }
    for (;;)
    {
      localStringBuffer.append('S');
      return localStringBuffer.toString();
      localStringBuffer.insert(localStringBuffer.length() - 3, ".");
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.base.AbstractDuration
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */