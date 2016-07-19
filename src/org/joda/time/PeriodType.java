package org.joda.time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.field.FieldUtils;

public class PeriodType
  implements Serializable
{
  static int DAY_INDEX = 0;
  static int HOUR_INDEX = 0;
  static int MILLI_INDEX = 7;
  static int MINUTE_INDEX = 0;
  static int MONTH_INDEX = 0;
  static int SECOND_INDEX = 0;
  static int WEEK_INDEX = 0;
  static int YEAR_INDEX = 0;
  private static PeriodType cDTime;
  private static PeriodType cDays;
  private static PeriodType cHours;
  private static PeriodType cMillis;
  private static PeriodType cMinutes;
  private static PeriodType cMonths;
  private static PeriodType cSeconds;
  private static PeriodType cStandard;
  private static PeriodType cTime;
  private static final Map<PeriodType, Object> cTypes = new HashMap(32);
  private static PeriodType cWeeks;
  private static PeriodType cYD;
  private static PeriodType cYDTime;
  private static PeriodType cYMD;
  private static PeriodType cYMDTime;
  private static PeriodType cYWD;
  private static PeriodType cYWDTime;
  private static PeriodType cYears;
  private static final long serialVersionUID = 2274324892792009998L;
  private final int[] iIndices;
  private final String iName;
  private final DurationFieldType[] iTypes;
  
  static
  {
    YEAR_INDEX = 0;
    MONTH_INDEX = 1;
    WEEK_INDEX = 2;
    DAY_INDEX = 3;
    HOUR_INDEX = 4;
    MINUTE_INDEX = 5;
    SECOND_INDEX = 6;
  }
  
  protected PeriodType(String paramString, DurationFieldType[] paramArrayOfDurationFieldType, int[] paramArrayOfInt)
  {
    iName = paramString;
    iTypes = paramArrayOfDurationFieldType;
    iIndices = paramArrayOfInt;
  }
  
  public static PeriodType dayTime()
  {
    PeriodType localPeriodType2 = cDTime;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("DayTime", new DurationFieldType[] { DurationFieldType.days(), DurationFieldType.hours(), DurationFieldType.minutes(), DurationFieldType.seconds(), DurationFieldType.millis() }, new int[] { -1, -1, -1, 0, 1, 2, 3, 4 });
      cDTime = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  public static PeriodType days()
  {
    PeriodType localPeriodType2 = cDays;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("Days", new DurationFieldType[] { DurationFieldType.days() }, new int[] { -1, -1, -1, 0, -1, -1, -1, -1 });
      cDays = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  public static PeriodType forFields(DurationFieldType[] paramArrayOfDurationFieldType)
  {
    if (paramArrayOfDurationFieldType != null) {}
    int i;
    try
    {
      if (paramArrayOfDurationFieldType.length == 0) {
        throw new IllegalArgumentException("Types array must not be null or empty");
      }
    }
    finally
    {
      throw paramArrayOfDurationFieldType;
      i = 0;
      if (i < paramArrayOfDurationFieldType.length)
      {
        if (paramArrayOfDurationFieldType[i] == null) {
          throw new IllegalArgumentException("Types array must not contain null");
        }
      }
      else
      {
        Map localMap = cTypes;
        if (localMap.isEmpty())
        {
          localMap.put(standard(), standard());
          localMap.put(yearMonthDayTime(), yearMonthDayTime());
          localMap.put(yearMonthDay(), yearMonthDay());
          localMap.put(yearWeekDayTime(), yearWeekDayTime());
          localMap.put(yearWeekDay(), yearWeekDay());
          localMap.put(yearDayTime(), yearDayTime());
          localMap.put(yearDay(), yearDay());
          localMap.put(dayTime(), dayTime());
          localMap.put(time(), time());
          localMap.put(years(), years());
          localMap.put(months(), months());
          localMap.put(weeks(), weeks());
          localMap.put(days(), days());
          localMap.put(hours(), hours());
          localMap.put(minutes(), minutes());
          localMap.put(seconds(), seconds());
          localMap.put(millis(), millis());
        }
        PeriodType localPeriodType = new PeriodType(null, paramArrayOfDurationFieldType, null);
        Object localObject1 = localMap.get(localPeriodType);
        if ((localObject1 instanceof PeriodType)) {
          paramArrayOfDurationFieldType = (PeriodType)localObject1;
        }
        for (;;)
        {
          return paramArrayOfDurationFieldType;
          if (localObject1 != null) {
            throw new IllegalArgumentException("PeriodType does not support fields: " + localObject1);
          }
          localObject1 = standard();
          ArrayList localArrayList = new ArrayList(Arrays.asList(paramArrayOfDurationFieldType));
          paramArrayOfDurationFieldType = (DurationFieldType[])localObject1;
          if (!localArrayList.remove(DurationFieldType.years())) {
            paramArrayOfDurationFieldType = ((PeriodType)localObject1).withYearsRemoved();
          }
          localObject1 = paramArrayOfDurationFieldType;
          if (!localArrayList.remove(DurationFieldType.months())) {
            localObject1 = paramArrayOfDurationFieldType.withMonthsRemoved();
          }
          paramArrayOfDurationFieldType = (DurationFieldType[])localObject1;
          if (!localArrayList.remove(DurationFieldType.weeks())) {
            paramArrayOfDurationFieldType = ((PeriodType)localObject1).withWeeksRemoved();
          }
          localObject1 = paramArrayOfDurationFieldType;
          if (!localArrayList.remove(DurationFieldType.days())) {
            localObject1 = paramArrayOfDurationFieldType.withDaysRemoved();
          }
          paramArrayOfDurationFieldType = (DurationFieldType[])localObject1;
          if (!localArrayList.remove(DurationFieldType.hours())) {
            paramArrayOfDurationFieldType = ((PeriodType)localObject1).withHoursRemoved();
          }
          localObject1 = paramArrayOfDurationFieldType;
          if (!localArrayList.remove(DurationFieldType.minutes())) {
            localObject1 = paramArrayOfDurationFieldType.withMinutesRemoved();
          }
          Object localObject2 = localObject1;
          if (!localArrayList.remove(DurationFieldType.seconds())) {
            localObject2 = ((PeriodType)localObject1).withSecondsRemoved();
          }
          paramArrayOfDurationFieldType = (DurationFieldType[])localObject2;
          if (!localArrayList.remove(DurationFieldType.millis())) {
            paramArrayOfDurationFieldType = ((PeriodType)localObject2).withMillisRemoved();
          }
          if (localArrayList.size() > 0)
          {
            localMap.put(localPeriodType, localArrayList);
            throw new IllegalArgumentException("PeriodType does not support fields: " + localArrayList);
          }
          localObject2 = new PeriodType(null, iTypes, null);
          localObject1 = (PeriodType)localMap.get(localObject2);
          if (localObject1 != null)
          {
            localMap.put(localObject2, localObject1);
            paramArrayOfDurationFieldType = (DurationFieldType[])localObject1;
          }
          else
          {
            localMap.put(localObject2, paramArrayOfDurationFieldType);
          }
        }
      }
    }
  }
  
  public static PeriodType hours()
  {
    PeriodType localPeriodType2 = cHours;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("Hours", new DurationFieldType[] { DurationFieldType.hours() }, new int[] { -1, -1, -1, -1, 0, -1, -1, -1 });
      cHours = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  public static PeriodType millis()
  {
    PeriodType localPeriodType2 = cMillis;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("Millis", new DurationFieldType[] { DurationFieldType.millis() }, new int[] { -1, -1, -1, -1, -1, -1, -1, 0 });
      cMillis = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  public static PeriodType minutes()
  {
    PeriodType localPeriodType2 = cMinutes;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("Minutes", new DurationFieldType[] { DurationFieldType.minutes() }, new int[] { -1, -1, -1, -1, -1, 0, -1, -1 });
      cMinutes = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  public static PeriodType months()
  {
    PeriodType localPeriodType2 = cMonths;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("Months", new DurationFieldType[] { DurationFieldType.months() }, new int[] { -1, 0, -1, -1, -1, -1, -1, -1 });
      cMonths = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  public static PeriodType seconds()
  {
    PeriodType localPeriodType2 = cSeconds;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("Seconds", new DurationFieldType[] { DurationFieldType.seconds() }, new int[] { -1, -1, -1, -1, -1, -1, 0, -1 });
      cSeconds = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  public static PeriodType standard()
  {
    PeriodType localPeriodType2 = cStandard;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("Standard", new DurationFieldType[] { DurationFieldType.years(), DurationFieldType.months(), DurationFieldType.weeks(), DurationFieldType.days(), DurationFieldType.hours(), DurationFieldType.minutes(), DurationFieldType.seconds(), DurationFieldType.millis() }, new int[] { 0, 1, 2, 3, 4, 5, 6, 7 });
      cStandard = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  public static PeriodType time()
  {
    PeriodType localPeriodType2 = cTime;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("Time", new DurationFieldType[] { DurationFieldType.hours(), DurationFieldType.minutes(), DurationFieldType.seconds(), DurationFieldType.millis() }, new int[] { -1, -1, -1, -1, 0, 1, 2, 3 });
      cTime = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  public static PeriodType weeks()
  {
    PeriodType localPeriodType2 = cWeeks;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("Weeks", new DurationFieldType[] { DurationFieldType.weeks() }, new int[] { -1, -1, 0, -1, -1, -1, -1, -1 });
      cWeeks = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  private PeriodType withFieldRemoved(int paramInt, String paramString)
  {
    int j = iIndices[paramInt];
    if (j == -1) {
      return this;
    }
    DurationFieldType[] arrayOfDurationFieldType = new DurationFieldType[size() - 1];
    int i = 0;
    if (i < iTypes.length)
    {
      if (i < j) {
        arrayOfDurationFieldType[i] = iTypes[i];
      }
      for (;;)
      {
        i += 1;
        break;
        if (i > j) {
          arrayOfDurationFieldType[(i - 1)] = iTypes[i];
        }
      }
    }
    int[] arrayOfInt = new int[8];
    i = 0;
    if (i < arrayOfInt.length)
    {
      if (i < paramInt) {
        arrayOfInt[i] = iIndices[i];
      }
      for (;;)
      {
        i += 1;
        break;
        if (i > paramInt)
        {
          if (iIndices[i] == -1) {}
          for (j = -1;; j = iIndices[i] - 1)
          {
            arrayOfInt[i] = j;
            break;
          }
        }
        arrayOfInt[i] = -1;
      }
    }
    return new PeriodType(getName() + paramString, arrayOfDurationFieldType, arrayOfInt);
  }
  
  public static PeriodType yearDay()
  {
    PeriodType localPeriodType2 = cYD;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("YearDay", new DurationFieldType[] { DurationFieldType.years(), DurationFieldType.days() }, new int[] { 0, -1, -1, 1, -1, -1, -1, -1 });
      cYD = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  public static PeriodType yearDayTime()
  {
    PeriodType localPeriodType2 = cYDTime;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("YearDayTime", new DurationFieldType[] { DurationFieldType.years(), DurationFieldType.days(), DurationFieldType.hours(), DurationFieldType.minutes(), DurationFieldType.seconds(), DurationFieldType.millis() }, new int[] { 0, -1, -1, 1, 2, 3, 4, 5 });
      cYDTime = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  public static PeriodType yearMonthDay()
  {
    PeriodType localPeriodType2 = cYMD;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("YearMonthDay", new DurationFieldType[] { DurationFieldType.years(), DurationFieldType.months(), DurationFieldType.days() }, new int[] { 0, 1, -1, 2, -1, -1, -1, -1 });
      cYMD = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  public static PeriodType yearMonthDayTime()
  {
    PeriodType localPeriodType2 = cYMDTime;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("YearMonthDayTime", new DurationFieldType[] { DurationFieldType.years(), DurationFieldType.months(), DurationFieldType.days(), DurationFieldType.hours(), DurationFieldType.minutes(), DurationFieldType.seconds(), DurationFieldType.millis() }, new int[] { 0, 1, -1, 2, 3, 4, 5, 6 });
      cYMDTime = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  public static PeriodType yearWeekDay()
  {
    PeriodType localPeriodType2 = cYWD;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("YearWeekDay", new DurationFieldType[] { DurationFieldType.years(), DurationFieldType.weeks(), DurationFieldType.days() }, new int[] { 0, -1, 1, 2, -1, -1, -1, -1 });
      cYWD = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  public static PeriodType yearWeekDayTime()
  {
    PeriodType localPeriodType2 = cYWDTime;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("YearWeekDayTime", new DurationFieldType[] { DurationFieldType.years(), DurationFieldType.weeks(), DurationFieldType.days(), DurationFieldType.hours(), DurationFieldType.minutes(), DurationFieldType.seconds(), DurationFieldType.millis() }, new int[] { 0, -1, 1, 2, 3, 4, 5, 6 });
      cYWDTime = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  public static PeriodType years()
  {
    PeriodType localPeriodType2 = cYears;
    PeriodType localPeriodType1 = localPeriodType2;
    if (localPeriodType2 == null)
    {
      localPeriodType1 = new PeriodType("Years", new DurationFieldType[] { DurationFieldType.years() }, new int[] { 0, -1, -1, -1, -1, -1, -1, -1 });
      cYears = localPeriodType1;
    }
    return localPeriodType1;
  }
  
  boolean addIndexedField(ReadablePeriod paramReadablePeriod, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    if (paramInt2 == 0) {
      return false;
    }
    paramInt1 = iIndices[paramInt1];
    if (paramInt1 == -1) {
      throw new UnsupportedOperationException("Field is not supported");
    }
    paramArrayOfInt[paramInt1] = FieldUtils.safeAdd(paramArrayOfInt[paramInt1], paramInt2);
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof PeriodType)) {
      return false;
    }
    paramObject = (PeriodType)paramObject;
    return Arrays.equals(iTypes, iTypes);
  }
  
  public DurationFieldType getFieldType(int paramInt)
  {
    return iTypes[paramInt];
  }
  
  int getIndexedField(ReadablePeriod paramReadablePeriod, int paramInt)
  {
    paramInt = iIndices[paramInt];
    if (paramInt == -1) {
      return 0;
    }
    return paramReadablePeriod.getValue(paramInt);
  }
  
  public String getName()
  {
    return iName;
  }
  
  public int hashCode()
  {
    int j = 0;
    int i = 0;
    while (i < iTypes.length)
    {
      j += iTypes[i].hashCode();
      i += 1;
    }
    return j;
  }
  
  public int indexOf(DurationFieldType paramDurationFieldType)
  {
    int i = 0;
    int j = size();
    while (i < j)
    {
      if (iTypes[i] == paramDurationFieldType) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public boolean isSupported(DurationFieldType paramDurationFieldType)
  {
    return indexOf(paramDurationFieldType) >= 0;
  }
  
  boolean setIndexedField(ReadablePeriod paramReadablePeriod, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    paramInt1 = iIndices[paramInt1];
    if (paramInt1 == -1) {
      throw new UnsupportedOperationException("Field is not supported");
    }
    paramArrayOfInt[paramInt1] = paramInt2;
    return true;
  }
  
  public int size()
  {
    return iTypes.length;
  }
  
  public String toString()
  {
    return "PeriodType[" + getName() + "]";
  }
  
  public PeriodType withDaysRemoved()
  {
    return withFieldRemoved(3, "NoDays");
  }
  
  public PeriodType withHoursRemoved()
  {
    return withFieldRemoved(4, "NoHours");
  }
  
  public PeriodType withMillisRemoved()
  {
    return withFieldRemoved(7, "NoMillis");
  }
  
  public PeriodType withMinutesRemoved()
  {
    return withFieldRemoved(5, "NoMinutes");
  }
  
  public PeriodType withMonthsRemoved()
  {
    return withFieldRemoved(1, "NoMonths");
  }
  
  public PeriodType withSecondsRemoved()
  {
    return withFieldRemoved(6, "NoSeconds");
  }
  
  public PeriodType withWeeksRemoved()
  {
    return withFieldRemoved(2, "NoWeeks");
  }
  
  public PeriodType withYearsRemoved()
  {
    return withFieldRemoved(0, "NoYears");
  }
}

/* Location:
 * Qualified Name:     org.joda.time.PeriodType
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */