package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zze<TResult>
  implements zzf<TResult>
{
  private OnSuccessListener<? super TResult> aDJ;
  private final Executor avP;
  private final Object zzail = new Object();
  
  public zze(@NonNull Executor paramExecutor, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    avP = paramExecutor;
    aDJ = paramOnSuccessListener;
  }
  
  public void cancel()
  {
    synchronized (zzail)
    {
      aDJ = null;
      return;
    }
  }
  
  public void onComplete(@NonNull final Task<TResult> paramTask)
  {
    if (paramTask.isSuccessful()) {
      synchronized (zzail)
      {
        if (aDJ == null) {
          return;
        }
        avP.execute(new Runnable()
        {
          public void run()
          {
            synchronized (zze.zza(zze.this))
            {
              if (zze.zzb(zze.this) != null) {
                zze.zzb(zze.this).onSuccess(paramTask.getResult());
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
 * Qualified Name:     com.google.android.gms.tasks.zze
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */