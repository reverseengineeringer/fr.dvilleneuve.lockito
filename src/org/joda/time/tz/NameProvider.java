package org.joda.time.tz;

import java.util.Locale;

public abstract interface NameProvider
{
  public abstract String getName(Locale paramLocale, String paramString1, String paramString2);
  
  public abstract String getShortName(Locale paramLocale, String paramString1, String paramString2);
}

/* Location:
 * Qualified Name:     org.joda.time.tz.NameProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */