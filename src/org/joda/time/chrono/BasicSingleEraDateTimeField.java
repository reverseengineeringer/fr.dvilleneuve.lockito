package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.field.BaseDateTimeField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.UnsupportedDurationField;

final class BasicSingleEraDateTimeField
  extends BaseDateTimeField
{
  private static final int ERA_VALUE = 1;
  private final String iEraText;
  
  BasicSingleEraDateTimeField(String paramString)
  {
    super(DateTimeFieldType.era());
    iEraText = paramString;
  }
  
  public int get(long paramLong)
  {
    return 1;
  }
  
  public String getAsText(int paramInt, Locale paramLocale)
  {
    return iEraText;
  }
  
  public DurationField getDurationField()
  {
    return UnsupportedDurationField.getInstance(DurationFieldType.eras());
  }
  
  public int getMaximumTextLength(Locale paramLocale)
  {
    return iEraText.length();
  }
  
  public int getMaximumValue()
  {
    return 1;
  }
  
  public int getMinimumValue()
  {
    return 1;
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
    return Long.MAX_VALUE;
  }
  
  public long roundFloor(long paramLong)
  {
    return Long.MIN_VALUE;
  }
  
  public long roundHalfCeiling(long paramLong)
  {
    return Long.MIN_VALUE;
  }
  
  public long roundHalfEven(long paramLong)
  {
    return Long.MIN_VALUE;
  }
  
  public long roundHalfFloor(long paramLong)
  {
    return Long.MIN_VALUE;
  }
  
  public long set(long paramLong, int paramInt)
  {
    FieldUtils.verifyValueBounds(this, paramInt, 1, 1);
    return paramLong;
  }
  
  public long set(long paramLong, String paramString, Locale paramLocale)
  {
    if ((!iEraText.equals(paramString)) && (!"1".equals(paramString))) {
      throw new IllegalFieldValueException(DateTimeFieldType.era(), paramString);
    }
    return paramLong;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.BasicSingleEraDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */