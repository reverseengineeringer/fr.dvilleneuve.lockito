package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzvz;

public final class zzak
{
  private static Boolean zzcsb;
  private final zza ar;
  private final Context mContext;
  private final Handler mHandler;
  
  public zzak(zza paramzza)
  {
    mContext = paramzza.getContext();
    zzab.zzaa(mContext);
    ar = paramzza;
    mHandler = new Handler();
  }
  
  public static boolean zzaw(Context paramContext)
  {
    zzab.zzaa(paramContext);
    if (zzcsb != null) {
      return zzcsb.booleanValue();
    }
    boolean bool = zzao.zzk(paramContext, "com.google.android.gms.analytics.AnalyticsService");
    zzcsb = Boolean.valueOf(bool);
    return bool;
  }
  
  private void zzvw()
  {
    try
    {
      synchronized (zzaj.zzamp)
      {
        zzvz localzzvz = zzaj.zzcrz;
        if ((localzzvz != null) && (localzzvz.isHeld())) {
          localzzvz.release();
        }
        return;
      }
      return;
    }
    catch (SecurityException localSecurityException) {}
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public void onCreate()
  {
    zzf localzzf = zzf.zzay(mContext);
    zzaf localzzaf = localzzf.zzyx();
    if (localzzf.zzyy().zzabc())
    {
      localzzaf.zzei("Device AnalyticsService is starting up");
      return;
    }
    localzzaf.zzei("Local AnalyticsService is starting up");
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public void onDestroy()
  {
    zzf localzzf = zzf.zzay(mContext);
    zzaf localzzaf = localzzf.zzyx();
    if (localzzf.zzyy().zzabc())
    {
      localzzaf.zzei("Device AnalyticsService is shutting down");
      return;
    }
    localzzaf.zzei("Local AnalyticsService is shutting down");
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public int onStartCommand(Intent paramIntent, int paramInt1, final int paramInt2)
  {
    zzvw();
    final zzf localzzf = zzf.zzay(mContext);
    final zzaf localzzaf = localzzf.zzyx();
    if (paramIntent == null) {
      localzzaf.zzel("AnalyticsService started with null intent");
    }
    for (;;)
    {
      return 2;
      paramIntent = paramIntent.getAction();
      if (localzzf.zzyy().zzabc()) {
        localzzaf.zza("Device AnalyticsService called. startId, action", Integer.valueOf(paramInt2), paramIntent);
      }
      while ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(paramIntent))
      {
        localzzf.zzwd().zza(new zzw()
        {
          public void zzd(Throwable paramAnonymousThrowable)
          {
            zzak.zzb(zzak.this).post(new Runnable()
            {
              public void run()
              {
                if (zzak.zza(zzak.this).callServiceStopSelfResult(zzcsd))
                {
                  if (as.zzyy().zzabc()) {
                    zzcsc.zzei("Device AnalyticsService processed last dispatch request");
                  }
                }
                else {
                  return;
                }
                zzcsc.zzei("Local AnalyticsService processed last dispatch request");
              }
            });
          }
        });
        return 2;
        localzzaf.zza("Local AnalyticsService called. startId, action", Integer.valueOf(paramInt2), paramIntent);
      }
    }
  }
  
  public static abstract interface zza
  {
    public abstract boolean callServiceStopSelfResult(int paramInt);
    
    public abstract Context getContext();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzak
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */