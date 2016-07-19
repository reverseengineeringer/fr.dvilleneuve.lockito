package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

class zzad$6
  implements Runnable
{
  zzad$6(zzad paramzzad, AtomicReference paramAtomicReference, boolean paramBoolean) {}
  
  public void run()
  {
    localAtomicReference = ant;
    for (;;)
    {
      try
      {
        localzzm = zzad.zzc(ans);
        if (localzzm == null) {
          ans.zzbsz().zzbtr().log("Failed to get user properties");
        }
      }
      catch (RemoteException localRemoteException)
      {
        zzm localzzm;
        ans.zzbsz().zzbtr().zzj("Failed to get user properties", localRemoteException);
        ant.notify();
        continue;
      }
      finally
      {
        ant.notify();
      }
      try
      {
        ant.notify();
        return;
      }
      finally {}
    }
    ant.set(localzzm.zza(ans.zzbsr().zzlw(null), ank));
    zzad.zzd(ans);
    ant.notify();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.measurement.internal.zzad.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */