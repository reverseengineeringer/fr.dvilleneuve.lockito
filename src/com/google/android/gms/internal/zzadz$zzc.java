package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class zzadz$zzc
{
  private final List<zzadz.zze> aCE;
  private final Map<String, List<zzadz.zza>> aCF;
  private final int aCG;
  private final String nZ;
  
  private zzadz$zzc(List<zzadz.zze> paramList, Map<String, List<zzadz.zza>> paramMap, String paramString, int paramInt)
  {
    aCE = Collections.unmodifiableList(paramList);
    aCF = Collections.unmodifiableMap(paramMap);
    nZ = paramString;
    aCG = paramInt;
  }
  
  public static zzadz.zzd zzcgw()
  {
    return new zzadz.zzd(null);
  }
  
  public String getVersion()
  {
    return nZ;
  }
  
  public String toString()
  {
    String str1 = String.valueOf(zzcfr());
    String str2 = String.valueOf(aCF);
    return String.valueOf(str1).length() + 17 + String.valueOf(str2).length() + "Rules: " + str1 + "  Macros: " + str2;
  }
  
  public List<zzadz.zze> zzcfr()
  {
    return aCE;
  }
  
  public Map<String, List<zzadz.zza>> zzcgx()
  {
    return aCF;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzadz.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */