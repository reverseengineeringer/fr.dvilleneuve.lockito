package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DurationField;
import org.joda.time.field.DecoratedDateTimeField;

class LimitChronology$LimitDateTimeField
  extends DecoratedDateTimeField
{
  private static final long serialVersionUID = -2435306746995699312L;
  private final DurationField iDurationField;
  private final DurationField iLeapDurationField;
  private final DurationField iRangeDurationField;
  
  LimitChronology$LimitDateTimeField(LimitChronology paramLimitChronology, DateTimeField paramDateTimeField, DurationField paramDurationField1, DurationField paramDurationField2, DurationField paramDurationField3)
  {
    super(paramDateTimeField, paramDateTimeField.getType());
    iDurationField = paramDurationField1;
    iRangeDurationField = paramDurationField2;
    iLeapDurationField = paramDurationField3;
  }
  
  public long add(long paramLong, int paramInt)
  {
    this$0.checkLimits(paramLong, null);
    paramLong = getWrappedField().add(paramLong, paramInt);
    this$0.checkLimits(paramLong, "resulting");
    return paramLong;
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    this$0.checkLimits(paramLong1, null);
    paramLong1 = getWrappedField().add(paramLong1, paramLong2);
    this$0.checkLimits(paramLong1, "resulting");
    return paramLong1;
  }
  
  public long addWrapField(long paramLong, int paramInt)
  {
    this$0.checkLimits(paramLong, null);
    paramLong = getWrappedField().addWrapField(paramLong, paramInt);
    this$0.checkLimits(paramLong, "resulting");
    return paramLong;
  }
  
  public int get(long paramLong)
  {
    this$0.checkLimits(paramLong, null);
    return getWrappedField().get(paramLong);
  }
  
  public String getAsShortText(long paramLong, Locale paramLocale)
  {
    this$0.checkLimits(paramLong, null);
    return getWrappedField().getAsShortText(paramLong, paramLocale);
  }
  
  public String getAsText(long paramLong, Locale paramLocale)
  {
    this$0.checkLimits(paramLong, null);
    return getWrappedField().getAsText(paramLong, paramLocale);
  }
  
  public int getDifference(long paramLong1, long paramLong2)
  {
    this$0.checkLimits(paramLong1, "minuend");
    this$0.checkLimits(paramLong2, "subtrahend");
    return getWrappedField().getDifference(paramLong1, paramLong2);
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    this$0.checkLimits(paramLong1, "minuend");
    this$0.checkLimits(paramLong2, "subtrahend");
    return getWrappedField().getDifferenceAsLong(paramLong1, paramLong2);
  }
  
  public final DurationField getDurationField()
  {
    return iDurationField;
  }
  
  public int getLeapAmount(long paramLong)
  {
    this$0.checkLimits(paramLong, null);
    return getWrappedField().getLeapAmount(paramLong);
  }
  
  public final DurationField getLeapDurationField()
  {
    return iLeapDurationField;
  }
  
  public int getMaximumShortTextLength(Locale paramLocale)
  {
    return getWrappedField().getMaximumShortTextLength(paramLocale);
  }
  
  public int getMaximumTextLength(Locale paramLocale)
  {
    return getWrappedField().getMaximumTextLength(paramLocale);
  }
  
  public int getMaximumValue(long paramLong)
  {
    this$0.checkLimits(paramLong, null);
    return getWrappedField().getMaximumValue(paramLong);
  }
  
  public int getMinimumValue(long paramLong)
  {
    this$0.checkLimits(paramLong, null);
    return getWrappedField().getMinimumValue(paramLong);
  }
  
  public final DurationField getRangeDurationField()
  {
    return iRangeDurationField;
  }
  
  public boolean isLeap(long paramLong)
  {
    this$0.checkLimits(paramLong, null);
    return getWrappedField().isLeap(paramLong);
  }
  
  public long remainder(long paramLong)
  {
    this$0.checkLimits(paramLong, null);
    paramLong = getWrappedField().remainder(paramLong);
    this$0.checkLimits(paramLong, "resulting");
    return paramLong;
  }
  
  public long roundCeiling(long paramLong)
  {
    this$0.checkLimits(paramLong, null);
    paramLong = getWrappedField().roundCeiling(paramLong);
    this$0.checkLimits(paramLong, "resulting");
    return paramLong;
  }
  
  public long roundFloor(long paramLong)
  {
    this$0.checkLimits(paramLong, null);
    paramLong = getWrappedField().roundFloor(paramLong);
    this$0.checkLimits(paramLong, "resulting");
    return paramLong;
  }
  
  public long roundHalfCeiling(long paramLong)
  {
    this$0.checkLimits(paramLong, null);
    paramLong = getWrappedField().roundHalfCeiling(paramLong);
    this$0.checkLimits(paramLong, "resulting");
    return paramLong;
  }
  
  public long roundHalfEven(long paramLong)
  {
    this$0.checkLimits(paramLong, null);
    paramLong = getWrappedField().roundHalfEven(paramLong);
    this$0.checkLimits(paramLong, "resulting");
    return paramLong;
  }
  
  public long roundHalfFloor(long paramLong)
  {
    this$0.checkLimits(paramLong, null);
    paramLong = getWrappedField().roundHalfFloor(paramLong);
    this$0.checkLimits(paramLong, "resulting");
    return paramLong;
  }
  
  public long set(long paramLong, int paramInt)
  {
    this$0.checkLimits(paramLong, null);
    paramLong = getWrappedField().set(paramLong, paramInt);
    this$0.checkLimits(paramLong, "resulting");
    return paramLong;
  }
  
  public long set(long paramLong, String paramString, Locale paramLocale)
  {
    this$0.checkLimits(paramLong, null);
    paramLong = getWrappedField().set(paramLong, paramString, paramLocale);
    this$0.checkLimits(paramLong, "resulting");
    return paramLong;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.LimitChronology.LimitDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */