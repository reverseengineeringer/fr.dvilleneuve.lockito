package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzg;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zzma
  extends zzg<zzma>
{
  private Map<Integer, String> zzcuz = new HashMap(4);
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = zzcuz.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str = String.valueOf(localEntry.getKey());
      localHashMap.put(String.valueOf(str).length() + 9 + "dimension" + str, localEntry.getValue());
    }
    return zzk(localHashMap);
  }
  
  public void zza(zzma paramzzma)
  {
    zzcuz.putAll(zzcuz);
  }
  
  public Map<Integer, String> zzxk()
  {
    return Collections.unmodifiableMap(zzcuz);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzma
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */