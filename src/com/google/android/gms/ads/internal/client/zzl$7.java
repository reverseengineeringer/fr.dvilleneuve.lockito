package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.reward.client.zzb;
import com.google.android.gms.ads.internal.reward.client.zzf;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgn;

class zzl$7
  extends zzl.zza<zzb>
{
  zzl$7(zzl paramzzl, Context paramContext, zzgn paramzzgn)
  {
    super(paramzzl, null);
  }
  
  public zzb zzf(zzx paramzzx)
    throws RemoteException
  {
    return paramzzx.createRewardedVideoAd(zze.zzae(zzaky), zzavg, 9256000);
  }
  
  public zzb zzir()
  {
    zzb localzzb = zzl.zzf(zzavh).zzb(zzaky, zzavg);
    if (localzzb != null) {
      return localzzb;
    }
    zzl.zza(zzavh, zzaky, "rewarded_video");
    return new zzan();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.internal.client.zzl.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */