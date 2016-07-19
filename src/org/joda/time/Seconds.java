package org.joda.time;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Seconds
  extends BaseSingleFieldPeriod
{
  public static final Seconds MAX_VALUE = new Seconds(Integer.MAX_VALUE);
  public static final Seconds MIN_VALUE = new Seconds(Integer.MIN_VALUE);
  public static final Seconds ONE;
  private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.seconds());
  public static final Seconds THREE;
  public static final Seconds TWO;
  public static final Seconds ZERO = new Seconds(0);
  private static final long serialVersionUID = 87525275727380862L;
  
  static
  {
    ONE = new Seconds(1);
    TWO = new Seconds(2);
    THREE = new Seconds(3);
  }
  
  private Seconds(int paramInt)
  {
    super(paramInt);
  }
  
  @FromString
  public static Seconds parseSeconds(String paramString)
  {
    if (paramString == null) {
      return ZERO;
    }
    return seconds(PARSER.parsePeriod(paramString).getSeconds());
  }
  
  private Object readResolve()
  {
    return seconds(getValue());
  }
  
  public static Seconds seconds(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return new Seconds(paramInt);
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
  
  public static Seconds secondsBetween(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
  {
    return seconds(BaseSingleFieldPeriod.between(paramReadableInstant1, paramReadableInstant2, DurationFieldType.seconds()));
  }
  
  public static Seconds secondsBetween(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2)
  {
    if (((paramReadablePartial1 instanceof LocalTime)) && ((paramReadablePartial2 instanceof LocalTime))) {
      return seconds(DateTimeUtils.getChronology(paramReadablePartial1.getChronology()).seconds().getDifference(((LocalTime)paramReadablePartial2).getLocalMillis(), ((LocalTime)paramReadablePartial1).getLocalMillis()));
    }
    return seconds(BaseSingleFieldPeriod.between(paramReadablePartial1, paramReadablePartial2, ZERO));
  }
  
  public static Seconds secondsIn(ReadableInterval paramReadableInterval)
  {
    if (paramReadableInterval == null) {
      return ZERO;
    }
    return seconds(BaseSingleFieldPeriod.between(paramReadableInterval.getStart(), paramReadableInterval.getEnd(), DurationFieldType.seconds()));
  }
  
  public static Seconds standardSecondsIn(ReadablePeriod paramReadablePeriod)
  {
    return seconds(BaseSingleFieldPeriod.standardPeriodIn(paramReadablePeriod, 1000L));
  }
  
  public Seconds dividedBy(int paramInt)
  {
    if (paramInt == 1) {
      return this;
    }
    return seconds(getValue() / paramInt);
  }
  
  public DurationFieldType getFieldType()
  {
    return DurationFieldType.seconds();
  }
  
  public PeriodType getPeriodType()
  {
    return PeriodType.seconds();
  }
  
  public int getSeconds()
  {
    return getValue();
  }
  
  public boolean isGreaterThan(Seconds paramSeconds)
  {
    if (paramSeconds == null) {
      if (getValue() <= 0) {}
    }
    while (getValue() > paramSeconds.getValue())
    {
      return true;
      return false;
    }
    return false;
  }
  
  public boolean isLessThan(Seconds paramSeconds)
  {
    if (paramSeconds == null) {
      if (getValue() >= 0) {}
    }
    while (getValue() < paramSeconds.getValue())
    {
      return true;
      return false;
    }
    return false;
  }
  
  public Seconds minus(int paramInt)
  {
    return plus(FieldUtils.safeNegate(paramInt));
  }
  
  public Seconds minus(Seconds paramSeconds)
  {
    if (paramSeconds == null) {
      return this;
    }
    return minus(paramSeconds.getValue());
  }
  
  public Seconds multipliedBy(int paramInt)
  {
    return seconds(FieldUtils.safeMultiply(getValue(), paramInt));
  }
  
  public Seconds negated()
  {
    return seconds(FieldUtils.safeNegate(getValue()));
  }
  
  public Seconds plus(int paramInt)
  {
    if (paramInt == 0) {
      return this;
    }
    return seconds(FieldUtils.safeAdd(getValue(), paramInt));
  }
  
  public Seconds plus(Seconds paramSeconds)
  {
    if (paramSeconds == null) {
      return this;
    }
    return plus(paramSeconds.getValue());
  }
  
  public Days toStandardDays()
  {
    return Days.days(getValue() / 86400);
  }
  
  public Duration toStandardDuration()
  {
    return new Duration(1000L * getValue());
  }
  
  public Hours toStandardHours()
  {
    return Hours.hours(getValue() / 3600);
  }
  
  public Minutes toStandardMinutes()
  {
    return Minutes.minutes(getValue() / 60);
  }
  
  public Weeks toStandardWeeks()
  {
    return Weeks.weeks(getValue() / 604800);
  }
  
  @ToString
  public String toString()
  {
    return "PT" + String.valueOf(getValue()) + "S";
  }
}

/* Location:
 * Qualified Name:     org.joda.time.Seconds
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */