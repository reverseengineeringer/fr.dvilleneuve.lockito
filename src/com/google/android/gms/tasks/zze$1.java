package com.google.android.gms.tasks;

class zze$1
  implements Runnable
{
  zze$1(zze paramzze, Task paramTask) {}
  
  public void run()
  {
    synchronized (zze.zza(aDK))
    {
      if (zze.zzb(aDK) != null) {
        zze.zzb(aDK).onSuccess(aDC.getResult());
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.zze.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */