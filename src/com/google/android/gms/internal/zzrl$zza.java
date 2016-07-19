package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

class zzrl$zza
  extends zzri
{
  private final zzpr.zzb<Status> zj;
  
  public zzrl$zza(zzpr.zzb<Status> paramzzb)
  {
    zj = paramzzb;
  }
  
  public void zzgj(int paramInt)
    throws RemoteException
  {
    zj.setResult(new Status(paramInt));
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzrl.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */