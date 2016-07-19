package org.joda.time.chrono;

import java.util.Locale;

final class GJMonthOfYearDateTimeField
  extends BasicMonthOfYearDateTimeField
{
  private static final long serialVersionUID = -4748157875845286249L;
  
  GJMonthOfYearDateTimeField(BasicChronology paramBasicChronology)
  {
    super(paramBasicChronology, 2);
  }
  
  protected int convertText(String paramString, Locale paramLocale)
  {
    return GJLocaleSymbols.forLocale(paramLocale).monthOfYearTextToValue(paramString);
  }
  
  public String getAsShortText(int paramInt, Locale paramLocale)
  {
    return GJLocaleSymbols.forLocale(paramLocale).monthOfYearValueToShortText(paramInt);
  }
  
  public String getAsText(int paramInt, Locale paramLocale)
  {
    return GJLocaleSymbols.forLocale(paramLocale).monthOfYearValueToText(paramInt);
  }
  
  public int getMaximumShortTextLength(Locale paramLocale)
  {
    return GJLocaleSymbols.forLocale(paramLocale).getMonthMaxShortTextLength();
  }
  
  public int getMaximumTextLength(Locale paramLocale)
  {
    return GJLocaleSymbols.forLocale(paramLocale).getMonthMaxTextLength();
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.GJMonthOfYearDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */