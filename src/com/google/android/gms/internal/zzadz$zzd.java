package com.google.android.gms.internal;

import com.google.android.gms.tagmanager.zzdl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class zzadz$zzd
{
  private final List<zzadz.zze> aCE = new ArrayList();
  private final Map<String, List<zzadz.zza>> aCF = new HashMap();
  private int aCG = 0;
  private String nZ = "";
  
  public zzd zzb(zzadz.zze paramzze)
  {
    aCE.add(paramzze);
    return this;
  }
  
  public zzd zzc(zzadz.zza paramzza)
  {
    String str = zzdl.zzg((zzai.zza)paramzza.zzcft().get(zzag.zzpi.toString()));
    List localList = (List)aCF.get(str);
    Object localObject = localList;
    if (localList == null)
    {
      localObject = new ArrayList();
      aCF.put(str, localObject);
    }
    ((List)localObject).add(paramzza);
    return this;
  }
  
  public zzadz.zzc zzcgy()
  {
    return new zzadz.zzc(aCE, aCF, nZ, aCG, null);
  }
  
  public zzd zzqc(String paramString)
  {
    nZ = paramString;
    return this;
  }
  
  public zzd zzyl(int paramInt)
  {
    aCG = paramInt;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzadz.zzd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */