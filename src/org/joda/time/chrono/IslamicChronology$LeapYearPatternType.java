package org.joda.time.chrono;

import java.io.Serializable;

public class IslamicChronology$LeapYearPatternType
  implements Serializable
{
  private static final long serialVersionUID = 26581275372698L;
  final byte index;
  final int pattern;
  
  IslamicChronology$LeapYearPatternType(int paramInt1, int paramInt2)
  {
    index = ((byte)paramInt1);
    pattern = paramInt2;
  }
  
  private Object readResolve()
  {
    switch (index)
    {
    default: 
      return this;
    case 0: 
      return IslamicChronology.LEAP_YEAR_15_BASED;
    case 1: 
      return IslamicChronology.LEAP_YEAR_16_BASED;
    case 2: 
      return IslamicChronology.LEAP_YEAR_INDIAN;
    }
    return IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof LeapYearPatternType))
    {
      bool1 = bool2;
      if (index == index) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public int hashCode()
  {
    return index;
  }
  
  boolean isLeapYear(int paramInt)
  {
    return (pattern & 1 << paramInt % 30) > 0;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.IslamicChronology.LeapYearPatternType
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */