package org.joda.time.tz;

import java.util.Collections;
import java.util.Set;
import org.joda.time.DateTimeZone;

public final class UTCProvider
  implements Provider
{
  public Set<String> getAvailableIDs()
  {
    return Collections.singleton("UTC");
  }
  
  public DateTimeZone getZone(String paramString)
  {
    if ("UTC".equalsIgnoreCase(paramString)) {
      return DateTimeZone.UTC;
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.UTCProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */