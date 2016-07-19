package com.google.android.gms.analytics;

import android.util.Log;
import java.util.concurrent.FutureTask;

class zzi$zza$1
  extends FutureTask<T>
{
  zzi$zza$1(zzi.zza paramzza, Runnable paramRunnable, Object paramObject)
  {
    super(paramRunnable, paramObject);
  }
  
  protected void setException(Throwable paramThrowable)
  {
    Object localObject = zzi.zzb(zzctt.zzcts);
    if (localObject != null) {
      ((Thread.UncaughtExceptionHandler)localObject).uncaughtException(Thread.currentThread(), paramThrowable);
    }
    for (;;)
    {
      super.setException(paramThrowable);
      return;
      if (Log.isLoggable("GAv4", 6))
      {
        localObject = String.valueOf(paramThrowable);
        Log.e("GAv4", String.valueOf(localObject).length() + 37 + "MeasurementExecutor: job failed with " + (String)localObject);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.zzi.zza.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */