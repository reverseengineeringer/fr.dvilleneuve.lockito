package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

final class Tasks$1
  implements Runnable
{
  Tasks$1(zzh paramzzh, Callable paramCallable) {}
  
  public void run()
  {
    try
    {
      aDT.setResult(zzclc.call());
      return;
    }
    catch (Exception localException)
    {
      aDT.setException(localException);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.Tasks.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */