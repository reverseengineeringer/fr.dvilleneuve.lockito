package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

final class Tasks$zza
  implements Tasks.zzb
{
  private final CountDownLatch zzalc = new CountDownLatch(1);
  
  public void await()
    throws InterruptedException
  {
    zzalc.await();
  }
  
  public boolean await(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException
  {
    return zzalc.await(paramLong, paramTimeUnit);
  }
  
  public void onFailure(@NonNull Exception paramException)
  {
    zzalc.countDown();
  }
  
  public void onSuccess(Object paramObject)
  {
    zzalc.countDown();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.Tasks.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */