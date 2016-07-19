package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzah.zzj;

class zzp$zzc
  implements zzbm<zzah.zzj>
{
  private zzp$zzc(zzp paramzzp) {}
  
  public void zza(zzbm.zza arg1)
  {
    synchronized (avz)
    {
      if (!avz.isReady())
      {
        if (zzp.zzb(avz) != null) {
          avz.zzc(zzp.zzb(avz));
        }
      }
      else
      {
        zzp.zza(avz, 3600000L);
        return;
      }
      avz.zzc(avz.zzec(Status.sj));
    }
  }
  
  public void zzb(zzah.zzj paramzzj)
  {
    synchronized (avz)
    {
      if (zzwq == null)
      {
        if (zzeavz).zzwq == null)
        {
          zzbn.e("Current resource is null; network resource is also null");
          zzp.zza(avz, 3600000L);
          return;
        }
        zzwq = zzeavz).zzwq;
      }
      zzp.zza(avz, paramzzj, zzp.zzc(avz).currentTimeMillis(), false);
      long l = zzp.zzf(avz);
      zzbn.v(58 + "setting refresh time to current time: " + l);
      if (!zzp.zzg(avz)) {
        zzp.zza(avz, paramzzj);
      }
      return;
    }
  }
  
  public void zzcaq() {}
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzp.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */