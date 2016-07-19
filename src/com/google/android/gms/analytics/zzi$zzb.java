package com.google.android.gms.analytics;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class zzi$zzb
  implements ThreadFactory
{
  private static final AtomicInteger zzctu = new AtomicInteger();
  
  public Thread newThread(Runnable paramRunnable)
  {
    int i = zzctu.incrementAndGet();
    return new zzi.zzc(paramRunnable, 23 + "measurement-" + i);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.zzi.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */