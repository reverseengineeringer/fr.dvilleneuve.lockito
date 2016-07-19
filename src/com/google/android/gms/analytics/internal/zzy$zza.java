package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzre;

public final class zzy$zza<V>
{
  private final V I;
  private final zzre<V> J;
  
  private zzy$zza(zzre<V> paramzzre, V paramV)
  {
    zzab.zzaa(paramzzre);
    J = paramzzre;
    I = paramV;
  }
  
  static zza<Float> zza(String paramString, float paramFloat)
  {
    return zza(paramString, paramFloat, paramFloat);
  }
  
  static zza<Float> zza(String paramString, float paramFloat1, float paramFloat2)
  {
    return new zza(zzre.zza(paramString, Float.valueOf(paramFloat2)), Float.valueOf(paramFloat1));
  }
  
  static zza<Integer> zza(String paramString, int paramInt1, int paramInt2)
  {
    return new zza(zzre.zza(paramString, Integer.valueOf(paramInt2)), Integer.valueOf(paramInt1));
  }
  
  static zza<Long> zza(String paramString, long paramLong1, long paramLong2)
  {
    return new zza(zzre.zza(paramString, Long.valueOf(paramLong2)), Long.valueOf(paramLong1));
  }
  
  static zza<Boolean> zza(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    return new zza(zzre.zzm(paramString, paramBoolean2), Boolean.valueOf(paramBoolean1));
  }
  
  static zza<Long> zzb(String paramString, long paramLong)
  {
    return zza(paramString, paramLong, paramLong);
  }
  
  static zza<Integer> zzd(String paramString, int paramInt)
  {
    return zza(paramString, paramInt, paramInt);
  }
  
  static zza<String> zze(String paramString1, String paramString2, String paramString3)
  {
    return new zza(zzre.zzab(paramString1, paramString3), paramString2);
  }
  
  static zza<Boolean> zzh(String paramString, boolean paramBoolean)
  {
    return zza(paramString, paramBoolean, paramBoolean);
  }
  
  static zza<String> zzr(String paramString1, String paramString2)
  {
    return zze(paramString1, paramString2, paramString2);
  }
  
  public V get()
  {
    return (V)I;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzy.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */