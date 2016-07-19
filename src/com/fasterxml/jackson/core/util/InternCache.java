package com.fasterxml.jackson.core.util;

import java.util.concurrent.ConcurrentHashMap;

public final class InternCache
  extends ConcurrentHashMap<String, String>
{
  private static final int MAX_ENTRIES = 180;
  private static final Object _flushLock = new Object();
  public static final InternCache instance = new InternCache();
  
  private InternCache()
  {
    super(180, 0.8F, 4);
  }
  
  public String intern(String paramString)
  {
    ??? = (String)get(paramString);
    if (??? != null) {
      return (String)???;
    }
    if (size() >= 180) {}
    synchronized (_flushLock)
    {
      if (size() >= 180) {
        clear();
      }
      paramString = paramString.intern();
      put(paramString, paramString);
      return paramString;
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.util.InternCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */