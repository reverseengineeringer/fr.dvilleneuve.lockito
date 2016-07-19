package com.google.android.gms.analytics.internal;

import android.os.Looper;
import com.google.android.gms.analytics.zzi;

class zzt$1
  implements Runnable
{
  zzt$1(zzt paramzzt) {}
  
  public void run()
  {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      zzt.zza(zzczl).zzyz().zzf(this);
    }
    boolean bool;
    do
    {
      return;
      bool = zzczl.zzfc();
      zzt.zza(zzczl, 0L);
    } while ((!bool) || (zzt.zzb(zzczl)));
    zzczl.run();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzt.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */