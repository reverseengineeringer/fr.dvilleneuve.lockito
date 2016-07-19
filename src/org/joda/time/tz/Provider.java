package org.joda.time.tz;

import java.util.Set;
import org.joda.time.DateTimeZone;

public abstract interface Provider
{
  public abstract Set<String> getAvailableIDs();
  
  public abstract DateTimeZone getZone(String paramString);
}

/* Location:
 * Qualified Name:     org.joda.time.tz.Provider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */