package org.joda.time;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Days
  extends BaseSingleFieldPeriod
{
  public static final Days FIVE;
  public static final Days FOUR;
  public static final Days MAX_VALUE = new Days(Integer.MAX_VALUE);
  public static final Days MIN_VALUE = new Days(Integer.MIN_VALUE);
  public static final Days ONE;
  private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.days());
  public static final Days SEVEN;
  public static final Days SIX;
  public static final Days THREE;
  public static final Days TWO;
  public static final Days ZERO = new Days(0);
  private static final long serialVersionUID = 87525275727380865L;
  
  static
  {
    ONE = new Days(1);
    TWO = new Days(2);
    THREE = new Days(3);
    FOUR = new Days(4);
    FIVE = new Days(5);
    SIX = new Days(6);
    SEVEN = new Days(7);
  }
  
  private Days(int paramInt)
  {
    super(paramInt);
  }
  
  public static Days days(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return new Days(paramInt);
    case 0: 
      return ZERO;
    case 1: 
      return ONE;
    case 2: 
      return TWO;
    case 3: 
      return THREE;
    case 4: 
      return FOUR;
    case 5: 
      return FIVE;
    case 6: 
      return SIX;
    case 7: 
      return SEVEN;
    case 2147483647: 
      return MAX_VALUE;
    }
    return MIN_VALUE;
  }
  
  public static Days daysBetween(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
  {
    return days(BaseSingleFieldPeriod.between(paramReadableInstant1, paramReadableInstant2, DurationFieldType.days()));
  }
  
  public static Days daysBetween(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2)
  {
    if (((paramReadablePartial1 instanceof LocalDate)) && ((paramReadablePartial2 instanceof LocalDate))) {
      return days(DateTimeUtils.getChronology(paramReadablePartial1.getChronology()).days().getDifference(((LocalDate)paramReadablePartial2).getLocalMillis(), ((LocalDate)paramReadablePartial1).getLocalMillis()));
    }
    return days(BaseSingleFieldPeriod.between(paramReadablePartial1, paramReadablePartial2, ZERO));
  }
  
  public static Days daysIn(ReadableInterval paramReadableInterval)
  {
    if (paramReadableInterval == null) {
      return ZERO;
    }
    return days(BaseSingleFieldPeriod.between(paramReadableInterval.getStart(), paramReadableInterval.getEnd(), DurationFieldType.days()));
  }
  
  @FromString
  public static Days parseDays(String paramString)
  {
    if (paramString == null) {
      return ZERO;
    }
    return days(PARSER.parsePeriod(paramString).getDays());
  }
  
  private Object readResolve()
  {
    return days(getValue());
  }
  
  public static Days standardDaysIn(ReadablePeriod paramReadablePeriod)
  {
    return days(BaseSingleFieldPeriod.standardPeriodIn(paramReadablePeriod, 86400000L));
  }
  
  public Days dividedBy(int paramInt)
  {
    if (paramInt == 1) {
      return this;
    }
    return days(getValue() / paramInt);
  }
  
  public int getDays()
  {
    return getValue();
  }
  
  public DurationFieldType getFieldType()
  {
    return DurationFieldType.days();
  }
  
  public PeriodType getPeriodType()
  {
    return PeriodType.days();
  }
  
  public boolean isGreaterThan(Days paramDays)
  {
    if (paramDays == null) {
      if (getValue() <= 0) {}
    }
    while (getValue() > paramDays.getValue())
    {
      return true;
      return false;
    }
    return false;
  }
  
  public boolean isLessThan(Days paramDays)
  {
    if (paramDays == null) {
      if (getValue() >= 0) {}
    }
    while (getValue() < paramDays.getValue())
    {
      return true;
      return false;
    }
    return false;
  }
  
  public Days minus(int paramInt)
  {
    return plus(FieldUtils.safeNegate(paramInt));
  }
  
  public Days minus(Days paramDays)
  {
    if (paramDays == null) {
      return this;
    }
    return minus(paramDays.getValue());
  }
  
  public Days multipliedBy(int paramInt)
  {
    return days(FieldUtils.safeMultiply(getValue(), paramInt));
  }
  
  public Days negated()
  {
    return days(FieldUtils.safeNegate(getValue()));
  }
  
  public Days plus(int paramInt)
  {
    if (paramInt == 0) {
      return this;
    }
    return days(FieldUtils.safeAdd(getValue(), paramInt));
  }
  
  public Days plus(Days paramDays)
  {
    if (paramDays == null) {
      return this;
    }
    return plus(paramDays.getValue());
  }
  
  public Duration toStandardDuration()
  {
    return new Duration(86400000L * getValue());
  }
  
  public Hours toStandardHours()
  {
    return Hours.hours(FieldUtils.safeMultiply(getValue(), 24));
  }
  
  public Minutes toStandardMinutes()
  {
    return Minutes.minutes(FieldUtils.safeMultiply(getValue(), 1440));
  }
  
  public Seconds toStandardSeconds()
  {
    return Seconds.seconds(FieldUtils.safeMultiply(getValue(), 86400));
  }
  
  public Weeks toStandardWeeks()
  {
    return Weeks.weeks(getValue() / 7);
  }
  
  @ToString
  public String toString()
  {
    return "P" + String.valueOf(getValue()) + "D";
  }
}

/* Location:
 * Qualified Name:     org.joda.time.Days
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */