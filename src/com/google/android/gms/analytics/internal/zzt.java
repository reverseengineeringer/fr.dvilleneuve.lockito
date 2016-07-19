package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;

abstract class zzt
{
  private static volatile Handler zzczj;
  private final zzf zzcws;
  private volatile long zzczk;
  private final Runnable zzw;
  
  zzt(zzf paramzzf)
  {
    zzab.zzaa(paramzzf);
    zzcws = paramzzf;
    zzw = new Runnable()
    {
      public void run()
      {
        if (Looper.myLooper() == Looper.getMainLooper()) {
          zzt.zza(zzt.this).zzyz().zzf(this);
        }
        boolean bool;
        do
        {
          return;
          bool = zzfc();
          zzt.zza(zzt.this, 0L);
        } while ((!bool) || (zzt.zzb(zzt.this)));
        zzt.this.run();
      }
    };
  }
  
  private Handler getHandler()
  {
    if (zzczj != null) {
      return zzczj;
    }
    try
    {
      if (zzczj == null) {
        zzczj = new Handler(zzcws.getContext().getMainLooper());
      }
      Handler localHandler = zzczj;
      return localHandler;
    }
    finally {}
  }
  
  public void cancel()
  {
    zzczk = 0L;
    getHandler().removeCallbacks(zzw);
  }
  
  public abstract void run();
  
  public long zzacj()
  {
    if (zzczk == 0L) {
      return 0L;
    }
    return Math.abs(zzcws.zzyw().currentTimeMillis() - zzczk);
  }
  
  public boolean zzfc()
  {
    return zzczk != 0L;
  }
  
  public void zzv(long paramLong)
  {
    cancel();
    if (paramLong >= 0L)
    {
      zzczk = zzcws.zzyw().currentTimeMillis();
      if (!getHandler().postDelayed(zzw, paramLong)) {
        zzcws.zzyx().zze("Failed to schedule delayed post. time", Long.valueOf(paramLong));
      }
    }
  }
  
  public void zzw(long paramLong)
  {
    long l = 0L;
    if (!zzfc()) {
      return;
    }
    if (paramLong < 0L)
    {
      cancel();
      return;
    }
    paramLong -= Math.abs(zzcws.zzyw().currentTimeMillis() - zzczk);
    if (paramLong < 0L) {
      paramLong = l;
    }
    for (;;)
    {
      getHandler().removeCallbacks(zzw);
      if (getHandler().postDelayed(zzw, paramLong)) {
        break;
      }
      zzcws.zzyx().zze("Failed to adjust delayed post. time", Long.valueOf(paramLong));
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */