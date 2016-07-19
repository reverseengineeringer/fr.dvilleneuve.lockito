package com.google.android.gms.analytics;

import android.util.Log;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class zzi$zza
  extends ThreadPoolExecutor
{
  public zzi$zza(zzi paramzzi)
  {
    super(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
    setThreadFactory(new zzi.zzb(null));
  }
  
  protected <T> RunnableFuture<T> newTaskFor(Runnable paramRunnable, T paramT)
  {
    new FutureTask(paramRunnable, paramT)
    {
      protected void setException(Throwable paramAnonymousThrowable)
      {
        Object localObject = zzi.zzb(zzcts);
        if (localObject != null) {
          ((Thread.UncaughtExceptionHandler)localObject).uncaughtException(Thread.currentThread(), paramAnonymousThrowable);
        }
        for (;;)
        {
          super.setException(paramAnonymousThrowable);
          return;
          if (Log.isLoggable("GAv4", 6))
          {
            localObject = String.valueOf(paramAnonymousThrowable);
            Log.e("GAv4", String.valueOf(localObject).length() + 37 + "MeasurementExecutor: job failed with " + (String)localObject);
          }
        }
      }
    };
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.zzi.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */