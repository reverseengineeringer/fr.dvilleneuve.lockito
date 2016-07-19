package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zza<TResult, TContinuationResult>
  implements zzf<TResult>
{
  private final Continuation<TResult, TContinuationResult> aDA;
  private final zzh<TContinuationResult> aDB;
  private final Executor avP;
  
  public zza(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, TContinuationResult> paramContinuation, @NonNull zzh<TContinuationResult> paramzzh)
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
          Object localObject = zza.zza(zza.this).then(paramTask);
          zza.zzb(zza.this).setResult(localObject);
          return;
        }
        catch (RuntimeExecutionException localRuntimeExecutionException)
        {
          if ((localRuntimeExecutionException.getCause() instanceof Exception))
          {
            zza.zzb(zza.this).setException((Exception)localRuntimeExecutionException.getCause());
            return;
          }
          zza.zzb(zza.this).setException(localRuntimeExecutionException);
          return;
        }
        catch (Exception localException)
        {
          zza.zzb(zza.this).setException(localException);
        }
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */