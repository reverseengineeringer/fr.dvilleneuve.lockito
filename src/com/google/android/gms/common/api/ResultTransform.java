package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.internal.zzqx;

public abstract class ResultTransform<R extends Result, S extends Result>
{
  @NonNull
  public final PendingResult<S> createFailedResult(@NonNull Status paramStatus)
  {
    return new zzqx(paramStatus);
  }
  
  @NonNull
  public Status onFailure(@NonNull Status paramStatus)
  {
    return paramStatus;
  }
  
  @Nullable
  @WorkerThread
  public abstract PendingResult<S> onSuccess(@NonNull R paramR);
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.api.ResultTransform
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */