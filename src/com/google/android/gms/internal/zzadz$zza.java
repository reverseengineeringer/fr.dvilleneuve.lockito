package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Map;

public class zzadz$zza
{
  private final Map<String, zzai.zza> aCH;
  private final zzai.zza aya;
  
  private zzadz$zza(Map<String, zzai.zza> paramMap, zzai.zza paramzza)
  {
    aCH = paramMap;
    aya = paramzza;
  }
  
  public static zzadz.zzb zzcgu()
  {
    return new zzadz.zzb(null);
  }
  
  public String toString()
  {
    String str1 = String.valueOf(zzcft());
    String str2 = String.valueOf(aya);
    return String.valueOf(str1).length() + 32 + String.valueOf(str2).length() + "Properties: " + str1 + " pushAfterEvaluate: " + str2;
  }
  
  public void zza(String paramString, zzai.zza paramzza)
  {
    aCH.put(paramString, paramzza);
  }
  
  public zzai.zza zzccr()
  {
    return aya;
  }
  
  public Map<String, zzai.zza> zzcft()
  {
    return Collections.unmodifiableMap(aCH);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzadz.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */