package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzg;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zzmb
  extends zzg<zzmb>
{
  private Map<Integer, Double> zzcva = new HashMap(4);
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = zzcva.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str = String.valueOf(localEntry.getKey());
      localHashMap.put(String.valueOf(str).length() + 6 + "metric" + str, localEntry.getValue());
    }
    return zzk(localHashMap);
  }
  
  public void zza(zzmb paramzzmb)
  {
    zzcva.putAll(zzcva);
  }
  
  public Map<Integer, Double> zzxl()
  {
    return Collections.unmodifiableMap(zzcva);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzmb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */