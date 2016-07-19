package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.util.client.zzb;

class zzan$1
  implements Runnable
{
  zzan$1(zzan paramzzan) {}
  
  public void run()
  {
    if (zzan.zza(zzaww) != null) {}
    try
    {
      zzan.zza(zzaww).onRewardedVideoAdFailedToLoad(1);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzd("Could not notify onRewardedVideoAdFailedToLoad event.", localRemoteException);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.internal.client.zzan.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */