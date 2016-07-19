package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;

class zzm<K, V>
{
  final zza<K, V> auX = new zza()
  {
    public int sizeOf(K paramAnonymousK, V paramAnonymousV)
    {
      return 1;
    }
  };
  
  public zzl<K, V> zza(int paramInt, zza<K, V> paramzza)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("maxSize <= 0");
    }
    if (zzcae() < 12) {
      return new zzdd(paramInt, paramzza);
    }
    return new zzbh(paramInt, paramzza);
  }
  
  int zzcae()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static abstract interface zza<K, V>
  {
    public abstract int sizeOf(K paramK, V paramV);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */