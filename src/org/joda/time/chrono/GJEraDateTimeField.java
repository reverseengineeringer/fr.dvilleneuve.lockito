package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.field.BaseDateTimeField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.UnsupportedDurationField;

final class GJEraDateTimeField
  extends BaseDateTimeField
{
  private static final long serialVersionUID = 4240986525305515528L;
  private final BasicChronology iChronology;
  
  GJEraDateTimeField(BasicChronology paramBasicChronology)
  {
    super(DateTimeFieldType.era());
    iChronology = paramBasicChronology;
  }
  
  private Object readResolve()
  {
    return iChronology.era();
  }
  
  public int get(long paramLong)
  {
    if (iChronology.getYear(paramLong) <= 0) {
      return 0;
    }
    return 1;
  }
  
  public String getAsText(int paramInt, Locale paramLocale)
  {
    return GJLocaleSymbols.forLocale(paramLocale).eraValueToText(paramInt);
  }
  
  public DurationField getDurationField()
  {
    return UnsupportedDurationField.getInstance(DurationFieldType.eras());
  }
  
  public int getMaximumTextLength(Locale paramLocale)
  {
    return GJLocaleSymbols.forLocale(paramLocale).getEraMaxTextLength();
  }
  
  public int getMaximumValue()
  {
    return 1;
  }
  
  public int getMinimumValue()
  {
    return 0;
  }
  
  public DurationField getRangeDurationField()
  {
    return null;
  }
  
  public boolean isLenient()
  {
    return false;
  }
  
  public long roundCeiling(long paramLong)
  {
    if (get(paramLong) == 0) {
      return iChronology.setYear(0L, 1);
    }
    return Long.MAX_VALUE;
  }
  
  public long roundFloor(long paramLong)
  {
    if (get(paramLong) == 1) {
      return iChronology.setYear(0L, 1);
    }
    return Long.MIN_VALUE;
  }
  
  public long roundHalfCeiling(long paramLong)
  {
    return roundFloor(paramLong);
  }
  
  public long roundHalfEven(long paramLong)
  {
    return roundFloor(paramLong);
  }
  
  public long roundHalfFloor(long paramLong)
  {
    return roundFloor(paramLong);
  }
  
  public long set(long paramLong, int paramInt)
  {
    FieldUtils.verifyValueBounds(this, paramInt, 0, 1);
    long l = paramLong;
    if (get(paramLong) != paramInt)
    {
      paramInt = iChronology.getYear(paramLong);
      l = iChronology.setYear(paramLong, -paramInt);
    }
    return l;
  }
  
  public long set(long paramLong, String paramString, Locale paramLocale)
  {
    return set(paramLong, GJLocaleSymbols.forLocale(paramLocale).eraTextToValue(paramString));
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.GJEraDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */