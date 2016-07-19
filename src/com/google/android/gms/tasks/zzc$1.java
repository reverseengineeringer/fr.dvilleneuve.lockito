package com.google.android.gms.tasks;

class zzc$1
  implements Runnable
{
  zzc$1(zzc paramzzc, Task paramTask) {}
  
  public void run()
  {
    synchronized (zzc.zza(aDG))
    {
      if (zzc.zzb(aDG) != null) {
        zzc.zzb(aDG).onComplete(aDC);
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.zzc.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */