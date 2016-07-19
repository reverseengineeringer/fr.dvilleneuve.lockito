package org.joda.time.format;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

final class DateTimeFormat$1
  extends LinkedHashMap<String, DateTimeFormatter>
{
  private static final long serialVersionUID = 23L;
  
  DateTimeFormat$1(int paramInt)
  {
    super(paramInt);
  }
  
  protected boolean removeEldestEntry(Map.Entry<String, DateTimeFormatter> paramEntry)
  {
    return size() > 500;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormat.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */