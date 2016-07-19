package org.joda.time;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Minutes
  extends BaseSingleFieldPeriod
{
  public static final Minutes MAX_VALUE = new Minutes(Integer.MAX_VALUE);
  public static final Minutes MIN_VALUE = new Minutes(Integer.MIN_VALUE);
  public static final Minutes ONE;
  private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.minutes());
  public static final Minutes THREE;
  public static final Minutes TWO;
  public static final Minutes ZERO = new Minutes(0);
  private static final long serialVersionUID = 87525275727380863L;
  
  static
  {
    ONE = new Minutes(1);
    TWO = new Minutes(2);
    THREE = new Minutes(3);
  }
  
  private Minutes(int paramInt)
  {
    super(paramInt);
  }
  
  public static Minutes minutes(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return new Minutes(paramInt);
    case 0: 
      return ZERO;
    case 1: 
      return ONE;
    case 2: 
      return TWO;
    case 3: 
      return THREE;
    case 2147483647: 
      return MAX_VALUE;
    }
    return MIN_VALUE;
  }
  
  public static Minutes minutesBetween(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
  {
    return minutes(BaseSingleFieldPeriod.between(paramReadableInstant1, paramReadableInstant2, DurationFieldType.minutes()));
  }
  
  public static Minutes minutesBetween(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2)
  {
    if (((paramReadablePartial1 instanceof LocalTime)) && ((paramReadablePartial2 instanceof LocalTime))) {
      return minutes(DateTimeUtils.getChronology(paramReadablePartial1.getChronology()).minutes().getDifference(((LocalTime)paramReadablePartial2).getLocalMillis(), ((LocalTime)paramReadablePartial1).getLocalMillis()));
    }
    return minutes(BaseSingleFieldPeriod.between(paramReadablePartial1, paramReadablePartial2, ZERO));
  }
  
  public static Minutes minutesIn(ReadableInterval paramReadableInterval)
  {
    if (paramReadableInterval == null) {
      return ZERO;
    }
    return minutes(BaseSingleFieldPeriod.between(paramReadableInterval.getStart(), paramReadableInterval.getEnd(), DurationFieldType.minutes()));
  }
  
  @FromString
  public static Minutes parseMinutes(String paramString)
  {
    if (paramString == null) {
      return ZERO;
    }
    return minutes(PARSER.parsePeriod(paramString).getMinutes());
  }
  
  private Object readResolve()
  {
    return minutes(getValue());
  }
  
  public static Minutes standardMinutesIn(ReadablePeriod paramReadablePeriod)
  {
    return minutes(BaseSingleFieldPeriod.standardPeriodIn(paramReadablePeriod, 60000L));
  }
  
  public Minutes dividedBy(int paramInt)
  {
    if (paramInt == 1) {
      return this;
    }
    return minutes(getValue() / paramInt);
  }
  
  public DurationFieldType getFieldType()
  {
    return DurationFieldType.minutes();
  }
  
  public int getMinutes()
  {
    return getValue();
  }
  
  public PeriodType getPeriodType()
  {
    return PeriodType.minutes();
  }
  
  public boolean isGreaterThan(Minutes paramMinutes)
  {
    if (paramMinutes == null) {
      if (getValue() <= 0) {}
    }
    while (getValue() > paramMinutes.getValue())
    {
      return true;
      return false;
    }
    return false;
  }
  
  public boolean isLessThan(Minutes paramMinutes)
  {
    if (paramMinutes == null) {
      if (getValue() >= 0) {}
    }
    while (getValue() < paramMinutes.getValue())
    {
      return true;
      return false;
    }
    return false;
  }
  
  public Minutes minus(int paramInt)
  {
    return plus(FieldUtils.safeNegate(paramInt));
  }
  
  public Minutes minus(Minutes paramMinutes)
  {
    if (paramMinutes == null) {
      return this;
    }
    return minus(paramMinutes.getValue());
  }
  
  public Minutes multipliedBy(int paramInt)
  {
    return minutes(FieldUtils.safeMultiply(getValue(), paramInt));
  }
  
  public Minutes negated()
  {
    return minutes(FieldUtils.safeNegate(getValue()));
  }
  
  public Minutes plus(int paramInt)
  {
    if (paramInt == 0) {
      return this;
    }
    return minutes(FieldUtils.safeAdd(getValue(), paramInt));
  }
  
  public Minutes plus(Minutes paramMinutes)
  {
    if (paramMinutes == null) {
      return this;
    }
    return plus(paramMinutes.getValue());
  }
  
  public Days toStandardDays()
  {
    return Days.days(getValue() / 1440);
  }
  
  public Duration toStandardDuration()
  {
    return new Duration(60000L * getValue());
  }
  
  public Hours toStandardHours()
  {
    return Hours.hours(getValue() / 60);
  }
  
  public Seconds toStandardSeconds()
  {
    return Seconds.seconds(FieldUtils.safeMultiply(getValue(), 60));
  }
  
  public Weeks toStandardWeeks()
  {
    return Weeks.weeks(getValue() / 10080);
  }
  
  @ToString
  public String toString()
  {
    return "PT" + String.valueOf(getValue()) + "M";
  }
}

/* Location:
 * Qualified Name:     org.joda.time.Minutes
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */