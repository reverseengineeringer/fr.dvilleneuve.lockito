package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzc<TResult>
  implements zzf<TResult>
{
  private OnCompleteListener<TResult> aDF;
  private final Executor avP;
  private final Object zzail = new Object();
  
  public zzc(@NonNull Executor paramExecutor, @NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    avP = paramExecutor;
    aDF = paramOnCompleteListener;
  }
  
  public void cancel()
  {
    synchronized (zzail)
    {
      aDF = null;
      return;
    }
  }
  
  public void onComplete(@NonNull final Task<TResult> paramTask)
  {
    synchronized (zzail)
    {
      if (aDF == null) {
        return;
      }
      avP.execute(new Runnable()
      {
        public void run()
        {
          synchronized (zzc.zza(zzc.this))
          {
            if (zzc.zzb(zzc.this) != null) {
              zzc.zzb(zzc.this).onComplete(paramTask);
            }
            return;
          }
        }
      });
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */