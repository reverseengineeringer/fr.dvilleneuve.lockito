package org.springframework.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LinkedCaseInsensitiveMap<V>
  extends LinkedHashMap<String, V>
{
  private static final long serialVersionUID = 1L;
  private final Map<String, String> caseInsensitiveKeys;
  private final Locale locale;
  
  public LinkedCaseInsensitiveMap()
  {
    this(null);
  }
  
  public LinkedCaseInsensitiveMap(int paramInt)
  {
    this(paramInt, null);
  }
  
  public LinkedCaseInsensitiveMap(int paramInt, Locale paramLocale)
  {
    super(paramInt);
    caseInsensitiveKeys = new HashMap(paramInt);
    if (paramLocale != null) {}
    for (;;)
    {
      locale = paramLocale;
      return;
      paramLocale = Locale.getDefault();
    }
  }
  
  public LinkedCaseInsensitiveMap(Locale paramLocale)
  {
    caseInsensitiveKeys = new HashMap();
    if (paramLocale != null) {}
    for (;;)
    {
      locale = paramLocale;
      return;
      paramLocale = Locale.getDefault();
    }
  }
  
  public void clear()
  {
    caseInsensitiveKeys.clear();
    super.clear();
  }
  
  public boolean containsKey(Object paramObject)
  {
    return ((paramObject instanceof String)) && (caseInsensitiveKeys.containsKey(convertKey((String)paramObject)));
  }
  
  protected String convertKey(String paramString)
  {
    return paramString.toLowerCase(locale);
  }
  
  public V get(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      return (V)super.get(caseInsensitiveKeys.get(convertKey((String)paramObject)));
    }
    return null;
  }
  
  public V put(String paramString, V paramV)
  {
    caseInsensitiveKeys.put(convertKey(paramString), paramString);
    return (V)super.put(paramString, paramV);
  }
  
  public void putAll(Map<? extends String, ? extends V> paramMap)
  {
    if (paramMap.isEmpty()) {}
    for (;;)
    {
      return;
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        put((String)localEntry.getKey(), localEntry.getValue());
      }
    }
  }
  
  public V remove(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      return (V)super.remove(caseInsensitiveKeys.remove(convertKey((String)paramObject)));
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.LinkedCaseInsensitiveMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */