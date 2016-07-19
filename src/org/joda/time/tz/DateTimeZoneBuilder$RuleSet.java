package org.joda.time.tz;

import java.util.ArrayList;
import java.util.Iterator;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeUtils;
import org.joda.time.chrono.ISOChronology;

final class DateTimeZoneBuilder$RuleSet
{
  private static final int YEAR_LIMIT;
  private String iInitialNameKey;
  private int iInitialSaveMillis;
  private ArrayList<DateTimeZoneBuilder.Rule> iRules;
  private int iStandardOffset;
  private DateTimeZoneBuilder.OfYear iUpperOfYear;
  private int iUpperYear;
  
  static
  {
    long l = DateTimeUtils.currentTimeMillis();
    YEAR_LIMIT = ISOChronology.getInstanceUTC().year().get(l) + 100;
  }
  
  DateTimeZoneBuilder$RuleSet()
  {
    iRules = new ArrayList(10);
    iUpperYear = Integer.MAX_VALUE;
  }
  
  DateTimeZoneBuilder$RuleSet(RuleSet paramRuleSet)
  {
    iStandardOffset = iStandardOffset;
    iRules = new ArrayList(iRules);
    iInitialNameKey = iInitialNameKey;
    iInitialSaveMillis = iInitialSaveMillis;
    iUpperYear = iUpperYear;
    iUpperOfYear = iUpperOfYear;
  }
  
  public void addRule(DateTimeZoneBuilder.Rule paramRule)
  {
    if (!iRules.contains(paramRule)) {
      iRules.add(paramRule);
    }
  }
  
  public DateTimeZoneBuilder.DSTZone buildTailZone(String paramString)
  {
    if (iRules.size() == 2)
    {
      DateTimeZoneBuilder.Rule localRule1 = (DateTimeZoneBuilder.Rule)iRules.get(0);
      DateTimeZoneBuilder.Rule localRule2 = (DateTimeZoneBuilder.Rule)iRules.get(1);
      if ((localRule1.getToYear() == Integer.MAX_VALUE) && (localRule2.getToYear() == Integer.MAX_VALUE)) {
        return new DateTimeZoneBuilder.DSTZone(paramString, iStandardOffset, iRecurrence, iRecurrence);
      }
    }
    return null;
  }
  
  public DateTimeZoneBuilder.Transition firstTransition(long paramLong)
  {
    if (iInitialNameKey != null) {
      return new DateTimeZoneBuilder.Transition(paramLong, iInitialNameKey, iStandardOffset + iInitialSaveMillis, iStandardOffset);
    }
    ArrayList localArrayList = new ArrayList(iRules);
    long l = Long.MIN_VALUE;
    int i = 0;
    DateTimeZoneBuilder.Transition localTransition1 = null;
    for (;;)
    {
      DateTimeZoneBuilder.Transition localTransition2 = nextTransition(l, i);
      Object localObject1 = localTransition1;
      if (localTransition2 != null)
      {
        l = localTransition2.getMillis();
        if (l != paramLong) {
          break label109;
        }
        localObject1 = new DateTimeZoneBuilder.Transition(paramLong, localTransition2);
      }
      for (;;)
      {
        iRules = localArrayList;
        return (DateTimeZoneBuilder.Transition)localObject1;
        label109:
        if (l <= paramLong) {
          break;
        }
        Object localObject2 = localTransition1;
        if (localTransition1 == null)
        {
          localObject1 = localArrayList.iterator();
          do
          {
            localObject2 = localTransition1;
            if (!((Iterator)localObject1).hasNext()) {
              break;
            }
            localObject2 = (DateTimeZoneBuilder.Rule)((Iterator)localObject1).next();
          } while (((DateTimeZoneBuilder.Rule)localObject2).getSaveMillis() != 0);
          localObject2 = new DateTimeZoneBuilder.Transition(paramLong, (DateTimeZoneBuilder.Rule)localObject2, iStandardOffset);
        }
        localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = new DateTimeZoneBuilder.Transition(paramLong, localTransition2.getNameKey(), iStandardOffset, iStandardOffset);
        }
      }
      localTransition1 = new DateTimeZoneBuilder.Transition(paramLong, localTransition2);
      i = localTransition2.getSaveMillis();
    }
  }
  
  public int getStandardOffset()
  {
    return iStandardOffset;
  }
  
  public long getUpperLimit(int paramInt)
  {
    if (iUpperYear == Integer.MAX_VALUE) {
      return Long.MAX_VALUE;
    }
    return iUpperOfYear.setInstant(iUpperYear, iStandardOffset, paramInt);
  }
  
  public DateTimeZoneBuilder.Transition nextTransition(long paramLong, int paramInt)
  {
    ISOChronology localISOChronology = ISOChronology.getInstanceUTC();
    Object localObject = null;
    long l1 = Long.MAX_VALUE;
    Iterator localIterator = iRules.iterator();
    while (localIterator.hasNext())
    {
      DateTimeZoneBuilder.Rule localRule = (DateTimeZoneBuilder.Rule)localIterator.next();
      long l2 = localRule.next(paramLong, iStandardOffset, paramInt);
      if (l2 <= paramLong)
      {
        localIterator.remove();
      }
      else if (l2 <= l1)
      {
        localObject = localRule;
        l1 = l2;
      }
    }
    if (localObject == null) {
      return null;
    }
    if (localISOChronology.year().get(l1) >= YEAR_LIMIT) {
      return null;
    }
    if ((iUpperYear < Integer.MAX_VALUE) && (l1 >= iUpperOfYear.setInstant(iUpperYear, iStandardOffset, paramInt))) {
      return null;
    }
    return new DateTimeZoneBuilder.Transition(l1, (DateTimeZoneBuilder.Rule)localObject, iStandardOffset);
  }
  
  public void setFixedSavings(String paramString, int paramInt)
  {
    iInitialNameKey = paramString;
    iInitialSaveMillis = paramInt;
  }
  
  public void setStandardOffset(int paramInt)
  {
    iStandardOffset = paramInt;
  }
  
  public void setUpperLimit(int paramInt, DateTimeZoneBuilder.OfYear paramOfYear)
  {
    iUpperYear = paramInt;
    iUpperOfYear = paramOfYear;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.DateTimeZoneBuilder.RuleSet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */