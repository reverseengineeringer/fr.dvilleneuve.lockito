package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;

class zzha$3
  implements Runnable
{
  zzha$3(zzha paramzzha) {}
  
  public void run()
  {
    try
    {
      zzha.zza(zzbpu).onAdLoaded();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzd("Could not call onAdLoaded.", localRemoteException);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzha.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */