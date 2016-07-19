package com.google.android.gms.tasks;

class zzb$1
  implements Runnable
{
  zzb$1(zzb paramzzb, Task paramTask) {}
  
  public void run()
  {
    try
    {
      Task localTask = (Task)zzb.zza(aDE).then(aDC);
      if (localTask == null)
      {
        aDE.onFailure(new NullPointerException("Continuation returned null"));
        return;
      }
    }
    catch (RuntimeExecutionException localRuntimeExecutionException)
    {
      if ((localRuntimeExecutionException.getCause() instanceof Exception))
      {
        zzb.zzb(aDE).setException((Exception)localRuntimeExecutionException.getCause());
        return;
      }
      zzb.zzb(aDE).setException(localRuntimeExecutionException);
      return;
    }
    catch (Exception localException)
    {
      zzb.zzb(aDE).setException(localException);
      return;
    }
    localException.addOnSuccessListener(TaskExecutors.aDO, aDE);
    localException.addOnFailureListener(TaskExecutors.aDO, aDE);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.zzb.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */