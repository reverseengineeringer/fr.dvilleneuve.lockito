package com.google.android.gms.analytics.internal;

class zzf$1
  implements Thread.UncaughtExceptionHandler
{
  zzf$1(zzf paramzzf) {}
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    paramThread = zzcxj.zzzj();
    if (paramThread != null) {
      paramThread.zze("Job execution failed", paramThrowable);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzf.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */