package org.joda.time.format;

import java.util.Locale;
import org.joda.time.ReadWritablePeriod;

public abstract interface PeriodParser
{
  public abstract int parseInto(ReadWritablePeriod paramReadWritablePeriod, String paramString, int paramInt, Locale paramLocale);
}

/* Location:
 * Qualified Name:     org.joda.time.format.PeriodParser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */