package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.FirebaseException;

public final class zzpn$zzb<TResult>
  extends zzpn
{
  private static final Status sr = new Status(8, "Connection to Google Play services was lost while executing the API call.");
  private final zzrb<Api.zzb, TResult> sp;
  private final TaskCompletionSource<TResult> sq;
  
  public zzpn$zzb(int paramInt1, int paramInt2, zzrb<Api.zzb, TResult> paramzzrb, TaskCompletionSource<TResult> paramTaskCompletionSource)
  {
    super(paramInt1, paramInt2);
    sq = paramTaskCompletionSource;
    sp = paramzzrb;
  }
  
  public void zzb(Api.zzb paramzzb)
    throws DeadObjectException
  {
    try
    {
      sp.zza(paramzzb, sq);
      return;
    }
    catch (DeadObjectException paramzzb)
    {
      zzx(sr);
      throw paramzzb;
    }
    catch (RemoteException paramzzb)
    {
      zzx(sr);
    }
  }
  
  public void zzx(@NonNull Status paramStatus)
  {
    if (paramStatus.getStatusCode() == 8)
    {
      sq.setException(new FirebaseException(paramStatus.getStatusMessage()));
      return;
    }
    sq.setException(new FirebaseApiNotAvailableException(paramStatus.getStatusMessage()));
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzpn.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */