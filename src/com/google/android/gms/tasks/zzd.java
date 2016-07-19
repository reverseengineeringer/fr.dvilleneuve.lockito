package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzd<TResult>
  implements zzf<TResult>
{
  private OnFailureListener aDH;
  private final Executor avP;
  private final Object zzail = new Object();
  
  public zzd(@NonNull Executor paramExecutor, @NonNull OnFailureListener paramOnFailureListener)
  {
    avP = paramExecutor;
    aDH = paramOnFailureListener;
  }
  
  public void cancel()
  {
    synchronized (zzail)
    {
      aDH = null;
      return;
    }
  }
  
  public void onComplete(@NonNull final Task<TResult> paramTask)
  {
    if (!paramTask.isSuccessful()) {
      synchronized (zzail)
      {
        if (aDH == null) {
          return;
        }
        avP.execute(new Runnable()
        {
          public void run()
          {
            synchronized (zzd.zza(zzd.this))
            {
              if (zzd.zzb(zzd.this) != null) {
                zzd.zzb(zzd.this).onFailure(paramTask.getException());
              }
              return;
            }
          }
        });
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.zzd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */