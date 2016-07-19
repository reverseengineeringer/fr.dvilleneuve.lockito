package com.google.android.gms.tagmanager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzdd<K, V>
  implements zzl<K, V>
{
  private int ayA;
  private final Map<K, V> ayx = new HashMap();
  private final int ayy;
  private final zzm.zza<K, V> ayz;
  
  zzdd(int paramInt, zzm.zza<K, V> paramzza)
  {
    ayy = paramInt;
    ayz = paramzza;
  }
  
  public V get(K paramK)
  {
    try
    {
      paramK = ayx.get(paramK);
      return paramK;
    }
    finally
    {
      paramK = finally;
      throw paramK;
    }
  }
  
  public void zzi(K paramK, V paramV)
  {
    if ((paramK == null) || (paramV == null)) {
      try
      {
        throw new NullPointerException("key == null || value == null");
      }
      finally {}
    }
    ayA += ayz.sizeOf(paramK, paramV);
    if (ayA > ayy)
    {
      Iterator localIterator = ayx.entrySet().iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        ayA -= ayz.sizeOf(localEntry.getKey(), localEntry.getValue());
        localIterator.remove();
      } while (ayA > ayy);
    }
    ayx.put(paramK, paramV);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzdd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */