package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.PreciseDurationDateTimeField;

final class GJDayOfWeekDateTimeField
  extends PreciseDurationDateTimeField
{
  private static final long serialVersionUID = -3857947176719041436L;
  private final BasicChronology iChronology;
  
  GJDayOfWeekDateTimeField(BasicChronology paramBasicChronology, DurationField paramDurationField)
  {
    super(DateTimeFieldType.dayOfWeek(), paramDurationField);
    iChronology = paramBasicChronology;
  }
  
  private Object readResolve()
  {
    return iChronology.dayOfWeek();
  }
  
  protected int convertText(String paramString, Locale paramLocale)
  {
    return GJLocaleSymbols.forLocale(paramLocale).dayOfWeekTextToValue(paramString);
  }
  
  public int get(long paramLong)
  {
    return iChronology.getDayOfWeek(paramLong);
  }
  
  public String getAsShortText(int paramInt, Locale paramLocale)
  {
    return GJLocaleSymbols.forLocale(paramLocale).dayOfWeekValueToShortText(paramInt);
  }
  
  public String getAsText(int paramInt, Locale paramLocale)
  {
    return GJLocaleSymbols.forLocale(paramLocale).dayOfWeekValueToText(paramInt);
  }
  
  public int getMaximumShortTextLength(Locale paramLocale)
  {
    return GJLocaleSymbols.forLocale(paramLocale).getDayOfWeekMaxShortTextLength();
  }
  
  public int getMaximumTextLength(Locale paramLocale)
  {
    return GJLocaleSymbols.forLocale(paramLocale).getDayOfWeekMaxTextLength();
  }
  
  public int getMaximumValue()
  {
    return 7;
  }
  
  public int getMinimumValue()
  {
    return 1;
  }
  
  public DurationField getRangeDurationField()
  {
    return iChronology.weeks();
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.GJDayOfWeekDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */