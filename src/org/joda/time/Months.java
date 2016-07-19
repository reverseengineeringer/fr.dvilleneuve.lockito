package org.joda.time;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Months
  extends BaseSingleFieldPeriod
{
  public static final Months EIGHT;
  public static final Months ELEVEN;
  public static final Months FIVE;
  public static final Months FOUR;
  public static final Months MAX_VALUE = new Months(Integer.MAX_VALUE);
  public static final Months MIN_VALUE = new Months(Integer.MIN_VALUE);
  public static final Months NINE;
  public static final Months ONE;
  private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.months());
  public static final Months SEVEN;
  public static final Months SIX;
  public static final Months TEN;
  public static final Months THREE;
  public static final Months TWELVE;
  public static final Months TWO;
  public static final Months ZERO = new Months(0);
  private static final long serialVersionUID = 87525275727380867L;
  
  static
  {
    ONE = new Months(1);
    TWO = new Months(2);
    THREE = new Months(3);
    FOUR = new Months(4);
    FIVE = new Months(5);
    SIX = new Months(6);
    SEVEN = new Months(7);
    EIGHT = new Months(8);
    NINE = new Months(9);
    TEN = new Months(10);
    ELEVEN = new Months(11);
    TWELVE = new Months(12);
  }
  
  private Months(int paramInt)
  {
    super(paramInt);
  }
  
  public static Months months(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return new Months(paramInt);
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
    case 8: 
      return EIGHT;
    case 9: 
      return NINE;
    case 10: 
      return TEN;
    case 11: 
      return ELEVEN;
    case 12: 
      return TWELVE;
    case 2147483647: 
      return MAX_VALUE;
    }
    return MIN_VALUE;
  }
  
  public static Months monthsBetween(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
  {
    return months(BaseSingleFieldPeriod.between(paramReadableInstant1, paramReadableInstant2, DurationFieldType.months()));
  }
  
  public static Months monthsBetween(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2)
  {
    if (((paramReadablePartial1 instanceof LocalDate)) && ((paramReadablePartial2 instanceof LocalDate))) {
      return months(DateTimeUtils.getChronology(paramReadablePartial1.getChronology()).months().getDifference(((LocalDate)paramReadablePartial2).getLocalMillis(), ((LocalDate)paramReadablePartial1).getLocalMillis()));
    }
    return months(BaseSingleFieldPeriod.between(paramReadablePartial1, paramReadablePartial2, ZERO));
  }
  
  public static Months monthsIn(ReadableInterval paramReadableInterval)
  {
    if (paramReadableInterval == null) {
      return ZERO;
    }
    return months(BaseSingleFieldPeriod.between(paramReadableInterval.getStart(), paramReadableInterval.getEnd(), DurationFieldType.months()));
  }
  
  @FromString
  public static Months parseMonths(String paramString)
  {
    if (paramString == null) {
      return ZERO;
    }
    return months(PARSER.parsePeriod(paramString).getMonths());
  }
  
  private Object readResolve()
  {
    return months(getValue());
  }
  
  public Months dividedBy(int paramInt)
  {
    if (paramInt == 1) {
      return this;
    }
    return months(getValue() / paramInt);
  }
  
  public DurationFieldType getFieldType()
  {
    return DurationFieldType.months();
  }
  
  public int getMonths()
  {
    return getValue();
  }
  
  public PeriodType getPeriodType()
  {
    return PeriodType.months();
  }
  
  public boolean isGreaterThan(Months paramMonths)
  {
    if (paramMonths == null) {
      if (getValue() <= 0) {}
    }
    while (getValue() > paramMonths.getValue())
    {
      return true;
      return false;
    }
    return false;
  }
  
  public boolean isLessThan(Months paramMonths)
  {
    if (paramMonths == null) {
      if (getValue() >= 0) {}
    }
    while (getValue() < paramMonths.getValue())
    {
      return true;
      return false;
    }
    return false;
  }
  
  public Months minus(int paramInt)
  {
    return plus(FieldUtils.safeNegate(paramInt));
  }
  
  public Months minus(Months paramMonths)
  {
    if (paramMonths == null) {
      return this;
    }
    return minus(paramMonths.getValue());
  }
  
  public Months multipliedBy(int paramInt)
  {
    return months(FieldUtils.safeMultiply(getValue(), paramInt));
  }
  
  public Months negated()
  {
    return months(FieldUtils.safeNegate(getValue()));
  }
  
  public Months plus(int paramInt)
  {
    if (paramInt == 0) {
      return this;
    }
    return months(FieldUtils.safeAdd(getValue(), paramInt));
  }
  
  public Months plus(Months paramMonths)
  {
    if (paramMonths == null) {
      return this;
    }
    return plus(paramMonths.getValue());
  }
  
  @ToString
  public String toString()
  {
    return "P" + String.valueOf(getValue()) + "M";
  }
}

/* Location:
 * Qualified Name:     org.joda.time.Months
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */