package com.google.android.gms.analytics.internal;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;

public class zzg
{
  private final Context zzaqj;
  private final Context zzcxk;
  
  public zzg(Context paramContext)
  {
    zzab.zzaa(paramContext);
    paramContext = paramContext.getApplicationContext();
    zzab.zzb(paramContext, "Application context can't be null");
    zzaqj = paramContext;
    zzcxk = paramContext;
  }
  
  public Context getApplicationContext()
  {
    return zzaqj;
  }
  
  protected zzu zza(zzf paramzzf)
  {
    return new zzu(paramzzf);
  }
  
  protected com.google.android.gms.analytics.zzi zzaz(Context paramContext)
  {
    return com.google.android.gms.analytics.zzi.zzax(paramContext);
  }
  
  protected zzk zzb(zzf paramzzf)
  {
    return new zzk(paramzzf);
  }
  
  protected zza zzc(zzf paramzzf)
  {
    return new zza(paramzzf);
  }
  
  protected zzn zzd(zzf paramzzf)
  {
    return new zzn(paramzzf);
  }
  
  protected zzap zze(zzf paramzzf)
  {
    return new zzap(paramzzf);
  }
  
  protected zzaf zzf(zzf paramzzf)
  {
    return new zzaf(paramzzf);
  }
  
  protected zzr zzg(zzf paramzzf)
  {
    return new zzr(paramzzf);
  }
  
  protected zze zzh(zzf paramzzf)
  {
    return zzh.zzavi();
  }
  
  protected GoogleAnalytics zzi(zzf paramzzf)
  {
    return new GoogleAnalytics(paramzzf);
  }
  
  zzl zzj(zzf paramzzf)
  {
    return new zzl(paramzzf, this);
  }
  
  zzag zzk(zzf paramzzf)
  {
    return new zzag(paramzzf);
  }
  
  protected zzb zzl(zzf paramzzf)
  {
    return new zzb(paramzzf, this);
  }
  
  public zzj zzm(zzf paramzzf)
  {
    return new zzj(paramzzf);
  }
  
  public zzah zzn(zzf paramzzf)
  {
    return new zzah(paramzzf);
  }
  
  public zzi zzo(zzf paramzzf)
  {
    return new zzi(paramzzf);
  }
  
  public zzv zzp(zzf paramzzf)
  {
    return new zzv(paramzzf);
  }
  
  public zzai zzq(zzf paramzzf)
  {
    return new zzai(paramzzf);
  }
  
  public Context zzzi()
  {
    return zzcxk;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */