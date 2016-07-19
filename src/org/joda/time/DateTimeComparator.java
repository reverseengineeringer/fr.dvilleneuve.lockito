package org.joda.time;

import java.io.Serializable;
import java.util.Comparator;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.InstantConverter;

public class DateTimeComparator
  implements Comparator<Object>, Serializable
{
  private static final DateTimeComparator ALL_INSTANCE = new DateTimeComparator(null, null);
  private static final DateTimeComparator DATE_INSTANCE = new DateTimeComparator(DateTimeFieldType.dayOfYear(), null);
  private static final DateTimeComparator TIME_INSTANCE = new DateTimeComparator(null, DateTimeFieldType.dayOfYear());
  private static final long serialVersionUID = -6097339773320178364L;
  private final DateTimeFieldType iLowerLimit;
  private final DateTimeFieldType iUpperLimit;
  
  protected DateTimeComparator(DateTimeFieldType paramDateTimeFieldType1, DateTimeFieldType paramDateTimeFieldType2)
  {
    iLowerLimit = paramDateTimeFieldType1;
    iUpperLimit = paramDateTimeFieldType2;
  }
  
  public static DateTimeComparator getDateOnlyInstance()
  {
    return DATE_INSTANCE;
  }
  
  public static DateTimeComparator getInstance()
  {
    return ALL_INSTANCE;
  }
  
  public static DateTimeComparator getInstance(DateTimeFieldType paramDateTimeFieldType)
  {
    return getInstance(paramDateTimeFieldType, null);
  }
  
  public static DateTimeComparator getInstance(DateTimeFieldType paramDateTimeFieldType1, DateTimeFieldType paramDateTimeFieldType2)
  {
    if ((paramDateTimeFieldType1 == null) && (paramDateTimeFieldType2 == null)) {
      return ALL_INSTANCE;
    }
    if ((paramDateTimeFieldType1 == DateTimeFieldType.dayOfYear()) && (paramDateTimeFieldType2 == null)) {
      return DATE_INSTANCE;
    }
    if ((paramDateTimeFieldType1 == null) && (paramDateTimeFieldType2 == DateTimeFieldType.dayOfYear())) {
      return TIME_INSTANCE;
    }
    return new DateTimeComparator(paramDateTimeFieldType1, paramDateTimeFieldType2);
  }
  
  public static DateTimeComparator getTimeOnlyInstance()
  {
    return TIME_INSTANCE;
  }
  
  private Object readResolve()
  {
    return getInstance(iLowerLimit, iUpperLimit);
  }
  
  public int compare(Object paramObject1, Object paramObject2)
  {
    Object localObject = ConverterManager.getInstance().getInstantConverter(paramObject1);
    Chronology localChronology = ((InstantConverter)localObject).getChronology(paramObject1, (Chronology)null);
    long l4 = ((InstantConverter)localObject).getInstantMillis(paramObject1, localChronology);
    paramObject1 = ConverterManager.getInstance().getInstantConverter(paramObject2);
    localObject = ((InstantConverter)paramObject1).getChronology(paramObject2, (Chronology)null);
    long l3 = ((InstantConverter)paramObject1).getInstantMillis(paramObject2, (Chronology)localObject);
    long l2 = l4;
    long l1 = l3;
    if (iLowerLimit != null)
    {
      l2 = iLowerLimit.getField(localChronology).roundFloor(l4);
      l1 = iLowerLimit.getField((Chronology)localObject).roundFloor(l3);
    }
    l4 = l2;
    l3 = l1;
    if (iUpperLimit != null)
    {
      l4 = iUpperLimit.getField(localChronology).remainder(l2);
      l3 = iUpperLimit.getField((Chronology)localObject).remainder(l1);
    }
    if (l4 < l3) {
      return -1;
    }
    if (l4 > l3) {
      return 1;
    }
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof DateTimeComparator))
    {
      paramObject = (DateTimeComparator)paramObject;
      if (iLowerLimit != ((DateTimeComparator)paramObject).getLowerLimit())
      {
        bool1 = bool2;
        if (iLowerLimit != null)
        {
          bool1 = bool2;
          if (!iLowerLimit.equals(((DateTimeComparator)paramObject).getLowerLimit())) {}
        }
      }
      else if (iUpperLimit != ((DateTimeComparator)paramObject).getUpperLimit())
      {
        bool1 = bool2;
        if (iUpperLimit != null)
        {
          bool1 = bool2;
          if (!iUpperLimit.equals(((DateTimeComparator)paramObject).getUpperLimit())) {}
        }
      }
      else
      {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public DateTimeFieldType getLowerLimit()
  {
    return iLowerLimit;
  }
  
  public DateTimeFieldType getUpperLimit()
  {
    return iUpperLimit;
  }
  
  public int hashCode()
  {
    int j = 0;
    int i;
    if (iLowerLimit == null)
    {
      i = 0;
      if (iUpperLimit != null) {
        break label36;
      }
    }
    for (;;)
    {
      return i + j * 123;
      i = iLowerLimit.hashCode();
      break;
      label36:
      j = iUpperLimit.hashCode();
    }
  }
  
  public String toString()
  {
    if (iLowerLimit == iUpperLimit)
    {
      localStringBuilder = new StringBuilder().append("DateTimeComparator[");
      if (iLowerLimit == null) {}
      for (str = "";; str = iLowerLimit.getName()) {
        return str + "]";
      }
    }
    StringBuilder localStringBuilder = new StringBuilder().append("DateTimeComparator[");
    if (iLowerLimit == null)
    {
      str = "";
      localStringBuilder = localStringBuilder.append(str).append("-");
      if (iUpperLimit != null) {
        break label128;
      }
    }
    label128:
    for (String str = "";; str = iUpperLimit.getName())
    {
      return str + "]";
      str = iLowerLimit.getName();
      break;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.DateTimeComparator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */