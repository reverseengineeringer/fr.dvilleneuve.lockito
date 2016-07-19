package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.DateTimeFieldType;
import org.joda.time.field.PreciseDateTimeField;

class BasicChronology$HalfdayField
  extends PreciseDateTimeField
{
  private static final long serialVersionUID = 581601443656929254L;
  
  BasicChronology$HalfdayField()
  {
    super(DateTimeFieldType.halfdayOfDay(), BasicChronology.access$000(), BasicChronology.access$100());
  }
  
  public String getAsText(int paramInt, Locale paramLocale)
  {
    return GJLocaleSymbols.forLocale(paramLocale).halfdayValueToText(paramInt);
  }
  
  public int getMaximumTextLength(Locale paramLocale)
  {
    return GJLocaleSymbols.forLocale(paramLocale).getHalfdayMaxTextLength();
  }
  
  public long set(long paramLong, String paramString, Locale paramLocale)
  {
    return set(paramLong, GJLocaleSymbols.forLocale(paramLocale).halfdayTextToValue(paramString));
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.BasicChronology.HalfdayField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */