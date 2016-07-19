package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzadz.zza;
import com.google.android.gms.internal.zzadz.zze;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class zzcw$zzc
{
  private final Set<zzadz.zze> axQ = new HashSet();
  private final Map<zzadz.zze, List<zzadz.zza>> ayb = new HashMap();
  private final Map<zzadz.zze, List<zzadz.zza>> ayc = new HashMap();
  private final Map<zzadz.zze, List<String>> ayd = new HashMap();
  private final Map<zzadz.zze, List<String>> aye = new HashMap();
  private zzadz.zza ayf;
  
  public void zza(zzadz.zze paramzze)
  {
    axQ.add(paramzze);
  }
  
  public void zza(zzadz.zze paramzze, zzadz.zza paramzza)
  {
    List localList = (List)ayb.get(paramzze);
    Object localObject = localList;
    if (localList == null)
    {
      localObject = new ArrayList();
      ayb.put(paramzze, localObject);
    }
    ((List)localObject).add(paramzza);
  }
  
  public void zza(zzadz.zze paramzze, String paramString)
  {
    List localList = (List)ayd.get(paramzze);
    Object localObject = localList;
    if (localList == null)
    {
      localObject = new ArrayList();
      ayd.put(paramzze, localObject);
    }
    ((List)localObject).add(paramString);
  }
  
  public void zzb(zzadz.zza paramzza)
  {
    ayf = paramzza;
  }
  
  public void zzb(zzadz.zze paramzze, zzadz.zza paramzza)
  {
    List localList = (List)ayc.get(paramzze);
    Object localObject = localList;
    if (localList == null)
    {
      localObject = new ArrayList();
      ayc.put(paramzze, localObject);
    }
    ((List)localObject).add(paramzza);
  }
  
  public void zzb(zzadz.zze paramzze, String paramString)
  {
    List localList = (List)aye.get(paramzze);
    Object localObject = localList;
    if (localList == null)
    {
      localObject = new ArrayList();
      aye.put(paramzze, localObject);
    }
    ((List)localObject).add(paramString);
  }
  
  public Set<zzadz.zze> zzccs()
  {
    return axQ;
  }
  
  public Map<zzadz.zze, List<zzadz.zza>> zzcct()
  {
    return ayb;
  }
  
  public Map<zzadz.zze, List<String>> zzccu()
  {
    return ayd;
  }
  
  public Map<zzadz.zze, List<String>> zzccv()
  {
    return aye;
  }
  
  public Map<zzadz.zze, List<zzadz.zza>> zzccw()
  {
    return ayc;
  }
  
  public zzadz.zza zzccx()
  {
    return ayf;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzcw.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */