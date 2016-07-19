package org.joda.time;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Weeks
  extends BaseSingleFieldPeriod
{
  public static final Weeks MAX_VALUE = new Weeks(Integer.MAX_VALUE);
  public static final Weeks MIN_VALUE = new Weeks(Integer.MIN_VALUE);
  public static final Weeks ONE;
  private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.weeks());
  public static final Weeks THREE;
  public static final Weeks TWO;
  public static final Weeks ZERO = new Weeks(0);
  private static final long serialVersionUID = 87525275727380866L;
  
  static
  {
    ONE = new Weeks(1);
    TWO = new Weeks(2);
    THREE = new Weeks(3);
  }
  
  private Weeks(int paramInt)
  {
    super(paramInt);
  }
  
  @FromString
  public static Weeks parseWeeks(String paramString)
  {
    if (paramString == null) {
      return ZERO;
    }
    return weeks(PARSER.parsePeriod(paramString).getWeeks());
  }
  
  private Object readResolve()
  {
    return weeks(getValue());
  }
  
  public static Weeks standardWeeksIn(ReadablePeriod paramReadablePeriod)
  {
    return weeks(BaseSingleFieldPeriod.standardPeriodIn(paramReadablePeriod, 604800000L));
  }
  
  public static Weeks weeks(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return new Weeks(paramInt);
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
  
  public static Weeks weeksBetween(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
  {
    return weeks(BaseSingleFieldPeriod.between(paramReadableInstant1, paramReadableInstant2, DurationFieldType.weeks()));
  }
  
  public static Weeks weeksBetween(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2)
  {
    if (((paramReadablePartial1 instanceof LocalDate)) && ((paramReadablePartial2 instanceof LocalDate))) {
      return weeks(DateTimeUtils.getChronology(paramReadablePartial1.getChronology()).weeks().getDifference(((LocalDate)paramReadablePartial2).getLocalMillis(), ((LocalDate)paramReadablePartial1).getLocalMillis()));
    }
    return weeks(BaseSingleFieldPeriod.between(paramReadablePartial1, paramReadablePartial2, ZERO));
  }
  
  public static Weeks weeksIn(ReadableInterval paramReadableInterval)
  {
    if (paramReadableInterval == null) {
      return ZERO;
    }
    return weeks(BaseSingleFieldPeriod.between(paramReadableInterval.getStart(), paramReadableInterval.getEnd(), DurationFieldType.weeks()));
  }
  
  public Weeks dividedBy(int paramInt)
  {
    if (paramInt == 1) {
      return this;
    }
    return weeks(getValue() / paramInt);
  }
  
  public DurationFieldType getFieldType()
  {
    return DurationFieldType.weeks();
  }
  
  public PeriodType getPeriodType()
  {
    return PeriodType.weeks();
  }
  
  public int getWeeks()
  {
    return getValue();
  }
  
  public boolean isGreaterThan(Weeks paramWeeks)
  {
    if (paramWeeks == null) {
      if (getValue() <= 0) {}
    }
    while (getValue() > paramWeeks.getValue())
    {
      return true;
      return false;
    }
    return false;
  }
  
  public boolean isLessThan(Weeks paramWeeks)
  {
    if (paramWeeks == null) {
      if (getValue() >= 0) {}
    }
    while (getValue() < paramWeeks.getValue())
    {
      return true;
      return false;
    }
    return false;
  }
  
  public Weeks minus(int paramInt)
  {
    return plus(FieldUtils.safeNegate(paramInt));
  }
  
  public Weeks minus(Weeks paramWeeks)
  {
    if (paramWeeks == null) {
      return this;
    }
    return minus(paramWeeks.getValue());
  }
  
  public Weeks multipliedBy(int paramInt)
  {
    return weeks(FieldUtils.safeMultiply(getValue(), paramInt));
  }
  
  public Weeks negated()
  {
    return weeks(FieldUtils.safeNegate(getValue()));
  }
  
  public Weeks plus(int paramInt)
  {
    if (paramInt == 0) {
      return this;
    }
    return weeks(FieldUtils.safeAdd(getValue(), paramInt));
  }
  
  public Weeks plus(Weeks paramWeeks)
  {
    if (paramWeeks == null) {
      return this;
    }
    return plus(paramWeeks.getValue());
  }
  
  public Days toStandardDays()
  {
    return Days.days(FieldUtils.safeMultiply(getValue(), 7));
  }
  
  public Duration toStandardDuration()
  {
    return new Duration(604800000L * getValue());
  }
  
  public Hours toStandardHours()
  {
    return Hours.hours(FieldUtils.safeMultiply(getValue(), 168));
  }
  
  public Minutes toStandardMinutes()
  {
    return Minutes.minutes(FieldUtils.safeMultiply(getValue(), 10080));
  }
  
  public Seconds toStandardSeconds()
  {
    return Seconds.seconds(FieldUtils.safeMultiply(getValue(), 604800));
  }
  
  @ToString
  public String toString()
  {
    return "P" + String.valueOf(getValue()) + "W";
  }
}

/* Location:
 * Qualified Name:     org.joda.time.Weeks
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */