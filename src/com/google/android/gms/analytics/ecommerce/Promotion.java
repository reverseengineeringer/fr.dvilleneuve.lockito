package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.zzg;
import com.google.android.gms.common.internal.zzab;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Promotion
{
  public static final String ACTION_CLICK = "click";
  public static final String ACTION_VIEW = "view";
  Map<String, String> zzcwd = new HashMap();
  
  void put(String paramString1, String paramString2)
  {
    zzab.zzb(paramString1, "Name should be non-null");
    zzcwd.put(paramString1, paramString2);
  }
  
  public Promotion setCreative(String paramString)
  {
    put("cr", paramString);
    return this;
  }
  
  public Promotion setId(String paramString)
  {
    put("id", paramString);
    return this;
  }
  
  public Promotion setName(String paramString)
  {
    put("nm", paramString);
    return this;
  }
  
  public Promotion setPosition(String paramString)
  {
    put("ps", paramString);
    return this;
  }
  
  public String toString()
  {
    return zzg.zzao(zzcwd);
  }
  
  public Map<String, String> zzef(String paramString)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = zzcwd.entrySet().iterator();
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str1 = String.valueOf(paramString);
      String str2 = String.valueOf((String)localEntry.getKey());
      if (str2.length() != 0) {}
      for (str1 = str1.concat(str2);; str1 = new String(str1))
      {
        localHashMap.put(str1, (String)localEntry.getValue());
        break;
      }
    }
    return localHashMap;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.ecommerce.Promotion
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */