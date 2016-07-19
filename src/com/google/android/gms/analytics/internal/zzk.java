package com.google.android.gms.analytics.internal;

import com.google.android.gms.analytics.zzi;
import com.google.android.gms.internal.zzly;

public class zzk
  extends zzd
{
  private final zzly zzctp = new zzly();
  
  zzk(zzf paramzzf)
  {
    super(paramzzf);
  }
  
  public zzly zzaad()
  {
    zzzg();
    return zzctp;
  }
  
  public void zzvz()
  {
    Object localObject = zzwe();
    String str = ((zzap)localObject).zzxb();
    if (str != null) {
      zzctp.setAppName(str);
    }
    localObject = ((zzap)localObject).zzxc();
    if (localObject != null) {
      zzctp.setAppVersion((String)localObject);
    }
  }
  
  protected void zzwv()
  {
    zzyz().zzws().zza(zzctp);
    zzvz();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */