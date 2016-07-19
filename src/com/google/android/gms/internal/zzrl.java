package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public final class zzrl
  implements zzrk
{
  public PendingResult<Status> zzg(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zzd(new zzrm.zza(paramGoogleApiClient)
    {
      protected void zza(zzrn paramAnonymouszzrn)
        throws RemoteException
      {
        ((zzrp)paramAnonymouszzrn.zzarw()).zza(new zzrl.zza(this));
      }
    });
  }
  
  private static class zza
    extends zzri
  {
    private final zzpr.zzb<Status> zj;
    
    public zza(zzpr.zzb<Status> paramzzb)
    {
      zj = paramzzb;
    }
    
    public void zzgj(int paramInt)
      throws RemoteException
    {
      zj.setResult(new Status(paramInt));
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzrl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */