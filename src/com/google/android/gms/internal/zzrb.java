package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzrb<A extends Api.zzb, TResult>
{
  protected abstract void zza(A paramA, TaskCompletionSource<TResult> paramTaskCompletionSource)
    throws RemoteException;
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzrb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */