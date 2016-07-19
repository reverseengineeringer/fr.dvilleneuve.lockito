package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;

class zzha$1
  implements Runnable
{
  zzha$1(zzha paramzzha) {}
  
  public void run()
  {
    try
    {
      zzha.zza(zzbpu).onAdClicked();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzd("Could not call onAdClicked.", localRemoteException);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzha.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */