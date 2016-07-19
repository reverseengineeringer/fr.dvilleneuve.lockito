package com.google.android.gms.analytics;

import com.google.android.gms.common.internal.zzab;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class zzh<T extends zzh>
{
  private final zzi zzcti;
  protected final zze zzctj;
  private final List<zzf> zzctk;
  
  protected zzh(zzi paramzzi, com.google.android.gms.common.util.zze paramzze)
  {
    zzab.zzaa(paramzzi);
    zzcti = paramzzi;
    zzctk = new ArrayList();
    paramzzi = new zze(this, paramzze);
    paramzzi.zzwp();
    zzctj = paramzzi;
  }
  
  protected void zza(zze paramzze) {}
  
  protected void zzd(zze paramzze)
  {
    Iterator localIterator = zzctk.iterator();
    while (localIterator.hasNext()) {
      ((zzf)localIterator.next()).zza(this, paramzze);
    }
  }
  
  public zze zzvr()
  {
    zze localzze = zzctj.zzwf();
    zzd(localzze);
    return localzze;
  }
  
  protected zzi zzwn()
  {
    return zzcti;
  }
  
  public zze zzwq()
  {
    return zzctj;
  }
  
  public List<zzk> zzwr()
  {
    return zzctj.zzwh();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.zzh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */