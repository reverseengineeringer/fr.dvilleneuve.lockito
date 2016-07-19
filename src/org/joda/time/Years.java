package org.joda.time;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Years
  extends BaseSingleFieldPeriod
{
  public static final Years MAX_VALUE = new Years(Integer.MAX_VALUE);
  public static final Years MIN_VALUE = new Years(Integer.MIN_VALUE);
  public static final Years ONE;
  private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.years());
  public static final Years THREE;
  public static final Years TWO;
  public static final Years ZERO = new Years(0);
  private static final long serialVersionUID = 87525275727380868L;
  
  static
  {
    ONE = new Years(1);
    TWO = new Years(2);
    THREE = new Years(3);
  }
  
  private Years(int paramInt)
  {
    super(paramInt);
  }
  
  @FromString
  public static Years parseYears(String paramString)
  {
    if (paramString == null) {
      return ZERO;
    }
    return years(PARSER.parsePeriod(paramString).getYears());
  }
  
  private Object readResolve()
  {
    return years(getValue());
  }
  
  public static Years years(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return new Years(paramInt);
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
  
  public static Years yearsBetween(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
  {
    return years(BaseSingleFieldPeriod.between(paramReadableInstant1, paramReadableInstant2, DurationFieldType.years()));
  }
  
  public static Years yearsBetween(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2)
  {
    if (((paramReadablePartial1 instanceof LocalDate)) && ((paramReadablePartial2 instanceof LocalDate))) {
      return years(DateTimeUtils.getChronology(paramReadablePartial1.getChronology()).years().getDifference(((LocalDate)paramReadablePartial2).getLocalMillis(), ((LocalDate)paramReadablePartial1).getLocalMillis()));
    }
    return years(BaseSingleFieldPeriod.between(paramReadablePartial1, paramReadablePartial2, ZERO));
  }
  
  public static Years yearsIn(ReadableInterval paramReadableInterval)
  {
    if (paramReadableInterval == null) {
      return ZERO;
    }
    return years(BaseSingleFieldPeriod.between(paramReadableInterval.getStart(), paramReadableInterval.getEnd(), DurationFieldType.years()));
  }
  
  public Years dividedBy(int paramInt)
  {
    if (paramInt == 1) {
      return this;
    }
    return years(getValue() / paramInt);
  }
  
  public DurationFieldType getFieldType()
  {
    return DurationFieldType.years();
  }
  
  public PeriodType getPeriodType()
  {
    return PeriodType.years();
  }
  
  public int getYears()
  {
    return getValue();
  }
  
  public boolean isGreaterThan(Years paramYears)
  {
    if (paramYears == null) {
      if (getValue() <= 0) {}
    }
    while (getValue() > paramYears.getValue())
    {
      return true;
      return false;
    }
    return false;
  }
  
  public boolean isLessThan(Years paramYears)
  {
    if (paramYears == null) {
      if (getValue() >= 0) {}
    }
    while (getValue() < paramYears.getValue())
    {
      return true;
      return false;
    }
    return false;
  }
  
  public Years minus(int paramInt)
  {
    return plus(FieldUtils.safeNegate(paramInt));
  }
  
  public Years minus(Years paramYears)
  {
    if (paramYears == null) {
      return this;
    }
    return minus(paramYears.getValue());
  }
  
  public Years multipliedBy(int paramInt)
  {
    return years(FieldUtils.safeMultiply(getValue(), paramInt));
  }
  
  public Years negated()
  {
    return years(FieldUtils.safeNegate(getValue()));
  }
  
  public Years plus(int paramInt)
  {
    if (paramInt == 0) {
      return this;
    }
    return years(FieldUtils.safeAdd(getValue(), paramInt));
  }
  
  public Years plus(Years paramYears)
  {
    if (paramYears == null) {
      return this;
    }
    return plus(paramYears.getValue());
  }
  
  @ToString
  public String toString()
  {
    return "P" + String.valueOf(getValue()) + "Y";
  }
}

/* Location:
 * Qualified Name:     org.joda.time.Years
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */