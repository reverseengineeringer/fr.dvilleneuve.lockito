package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;

class zzl$5
  extends zzl.zza<zzz>
{
  zzl$5(zzl paramzzl, Context paramContext)
  {
    super(paramzzl, null);
  }
  
  public zzz zzd(zzx paramzzx)
    throws RemoteException
  {
    return paramzzx.getMobileAdsSettingsManagerWithClientJarVersion(zze.zzae(zzaky), 9256000);
  }
  
  public zzz zzip()
  {
    zzz localzzz = zzl.zzd(zzavh).zzm(zzaky);
    if (localzzz != null) {
      return localzzz;
    }
    zzl.zza(zzavh, zzaky, "mobile_ads_settings");
    return new zzal();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.internal.client.zzl.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */