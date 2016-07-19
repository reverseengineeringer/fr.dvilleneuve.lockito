package com.google.android.gms.tasks;

class zza$1
  implements Runnable
{
  zza$1(zza paramzza, Task paramTask) {}
  
  public void run()
  {
    try
    {
      Object localObject = zza.zza(aDD).then(aDC);
      zza.zzb(aDD).setResult(localObject);
      return;
    }
    catch (RuntimeExecutionException localRuntimeExecutionException)
    {
      if ((localRuntimeExecutionException.getCause() instanceof Exception))
      {
        zza.zzb(aDD).setException((Exception)localRuntimeExecutionException.getCause());
        return;
      }
      zza.zzb(aDD).setException(localRuntimeExecutionException);
      return;
    }
    catch (Exception localException)
    {
      zza.zzb(aDD).setException(localException);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.zza.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */