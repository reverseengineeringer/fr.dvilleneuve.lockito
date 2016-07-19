package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;

class zzha$6
  implements Runnable
{
  zzha$6(zzha paramzzha) {}
  
  public void run()
  {
    try
    {
      zzha.zza(zzbpu).onAdLeftApplication();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzd("Could not call onAdLeftApplication.", localRemoteException);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzha.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */