package org.joda.time.base;

import org.joda.convert.ToString;
import org.joda.time.DurationFieldType;
import org.joda.time.MutablePeriod;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadablePeriod;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public abstract class AbstractPeriod
  implements ReadablePeriod
{
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    for (;;)
    {
      return true;
      if (!(paramObject instanceof ReadablePeriod)) {
        return false;
      }
      paramObject = (ReadablePeriod)paramObject;
      if (size() != ((ReadablePeriod)paramObject).size()) {
        return false;
      }
      int i = 0;
      int j = size();
      while (i < j)
      {
        if ((getValue(i) != ((ReadablePeriod)paramObject).getValue(i)) || (getFieldType(i) != ((ReadablePeriod)paramObject).getFieldType(i))) {
          return false;
        }
        i += 1;
      }
    }
  }
  
  public int get(DurationFieldType paramDurationFieldType)
  {
    int i = indexOf(paramDurationFieldType);
    if (i == -1) {
      return 0;
    }
    return getValue(i);
  }
  
  public DurationFieldType getFieldType(int paramInt)
  {
    return getPeriodType().getFieldType(paramInt);
  }
  
  public DurationFieldType[] getFieldTypes()
  {
    DurationFieldType[] arrayOfDurationFieldType = new DurationFieldType[size()];
    int i = 0;
    while (i < arrayOfDurationFieldType.length)
    {
      arrayOfDurationFieldType[i] = getFieldType(i);
      i += 1;
    }
    return arrayOfDurationFieldType;
  }
  
  public int[] getValues()
  {
    int[] arrayOfInt = new int[size()];
    int i = 0;
    while (i < arrayOfInt.length)
    {
      arrayOfInt[i] = getValue(i);
      i += 1;
    }
    return arrayOfInt;
  }
  
  public int hashCode()
  {
    int j = 17;
    int i = 0;
    int k = size();
    while (i < k)
    {
      j = (j * 27 + getValue(i)) * 27 + getFieldType(i).hashCode();
      i += 1;
    }
    return j;
  }
  
  public int indexOf(DurationFieldType paramDurationFieldType)
  {
    return getPeriodType().indexOf(paramDurationFieldType);
  }
  
  public boolean isSupported(DurationFieldType paramDurationFieldType)
  {
    return getPeriodType().isSupported(paramDurationFieldType);
  }
  
  public int size()
  {
    return getPeriodType().size();
  }
  
  public MutablePeriod toMutablePeriod()
  {
    return new MutablePeriod(this);
  }
  
  public Period toPeriod()
  {
    return new Period(this);
  }
  
  @ToString
  public String toString()
  {
    return ISOPeriodFormat.standard().print(this);
  }
  
  public String toString(PeriodFormatter paramPeriodFormatter)
  {
    if (paramPeriodFormatter == null) {
      return toString();
    }
    return paramPeriodFormatter.print(this);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.base.AbstractPeriod
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */