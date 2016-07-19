package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public class zzadz$zzb
{
  private final Map<String, zzai.zza> aCH = new HashMap();
  private zzai.zza aya;
  
  public zzb zzb(String paramString, zzai.zza paramzza)
  {
    aCH.put(paramString, paramzza);
    return this;
  }
  
  public zzadz.zza zzcgv()
  {
    return new zzadz.zza(aCH, aya, null);
  }
  
  public zzb zzq(zzai.zza paramzza)
  {
    aya = paramzza;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzadz.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */