package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzb<TResult, TContinuationResult>
  implements OnFailureListener, OnSuccessListener<TContinuationResult>, zzf<TResult>
{
  private final Continuation<TResult, Task<TContinuationResult>> aDA;
  private final zzh<TContinuationResult> aDB;
  private final Executor avP;
  
  public zzb(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation, @NonNull zzh<TContinuationResult> paramzzh)
  {
    avP = paramExecutor;
    aDA = paramContinuation;
    aDB = paramzzh;
  }
  
  public void cancel()
  {
    throw new UnsupportedOperationException();
  }
  
  public void onComplete(@NonNull final Task<TResult> paramTask)
  {
    avP.execute(new Runnable()
    {
      public void run()
      {
        try
        {
          Task localTask = (Task)zzb.zza(zzb.this).then(paramTask);
          if (localTask == null)
          {
            onFailure(new NullPointerException("Continuation returned null"));
            return;
          }
        }
        catch (RuntimeExecutionException localRuntimeExecutionException)
        {
          if ((localRuntimeExecutionException.getCause() instanceof Exception))
          {
            zzb.zzb(zzb.this).setException((Exception)localRuntimeExecutionException.getCause());
            return;
          }
          zzb.zzb(zzb.this).setException(localRuntimeExecutionException);
          return;
        }
        catch (Exception localException)
        {
          zzb.zzb(zzb.this).setException(localException);
          return;
        }
        localException.addOnSuccessListener(TaskExecutors.aDO, zzb.this);
        localException.addOnFailureListener(TaskExecutors.aDO, zzb.this);
      }
    });
  }
  
  public void onFailure(@NonNull Exception paramException)
  {
    aDB.setException(paramException);
  }
  
  public void onSuccess(TContinuationResult paramTContinuationResult)
  {
    aDB.setResult(paramTContinuationResult);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */