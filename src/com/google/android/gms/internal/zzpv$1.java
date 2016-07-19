package com.google.android.gms.internal;

import java.util.concurrent.locks.Lock;

class zzpv$1
  implements Runnable
{
  zzpv$1(zzpv paramzzpv) {}
  
  public void run()
  {
    zzpv.zza(tj).lock();
    try
    {
      zzpv.zzb(tj);
      return;
    }
    finally
    {
      zzpv.zza(tj).unlock();
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzpv.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */