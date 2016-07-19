package com.google.android.gms.tasks;

class zzd$1
  implements Runnable
{
  zzd$1(zzd paramzzd, Task paramTask) {}
  
  public void run()
  {
    synchronized (zzd.zza(aDI))
    {
      if (zzd.zzb(aDI) != null) {
        zzd.zzb(aDI).onFailure(aDC.getException());
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.zzd.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */