package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.util.LruCache;

@TargetApi(12)
class zzbh<K, V>
  implements zzl<K, V>
{
  private LruCache<K, V> awJ;
  
  zzbh(int paramInt, final zzm.zza<K, V> paramzza)
  {
    awJ = new LruCache(paramInt)
    {
      protected int sizeOf(K paramAnonymousK, V paramAnonymousV)
      {
        return paramzza.sizeOf(paramAnonymousK, paramAnonymousV);
      }
    };
  }
  
  public V get(K paramK)
  {
    return (V)awJ.get(paramK);
  }
  
  public void zzi(K paramK, V paramV)
  {
    awJ.put(paramK, paramV);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzbh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */