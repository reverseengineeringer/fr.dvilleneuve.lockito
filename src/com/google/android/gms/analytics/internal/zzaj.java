package com.google.android.gms.analytics.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzvz;

public final class zzaj
{
  static Object zzamp = new Object();
  static zzvz zzcrz;
  static Boolean zzcsa;
  
  public static boolean zzav(Context paramContext)
  {
    zzab.zzaa(paramContext);
    if (zzcsa != null) {
      return zzcsa.booleanValue();
    }
    boolean bool = zzao.zzb(paramContext, "com.google.android.gms.analytics.AnalyticsReceiver", false);
    zzcsa = Boolean.valueOf(bool);
    return bool;
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public void onReceive(Context paramContext, Intent arg2)
  {
    Object localObject = zzf.zzay(paramContext);
    localzzaf = ((zzf)localObject).zzyx();
    if (??? == null) {
      localzzaf.zzel("AnalyticsReceiver called with null intent");
    }
    for (;;)
    {
      return;
      ??? = ???.getAction();
      if (((zzf)localObject).zzyy().zzabc()) {
        localzzaf.zza("Device AnalyticsReceiver got", ???);
      }
      while ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(???))
      {
        boolean bool = zzak.zzaw(paramContext);
        localObject = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        ((Intent)localObject).setComponent(new ComponentName(paramContext, "com.google.android.gms.analytics.AnalyticsService"));
        ((Intent)localObject).setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        synchronized (zzamp)
        {
          paramContext.startService((Intent)localObject);
          if (bool) {
            break label134;
          }
          return;
        }
        localzzaf.zza("Local AnalyticsReceiver got", ???);
      }
    }
    try
    {
      label134:
      if (zzcrz == null)
      {
        zzcrz = new zzvz(paramContext, 1, "Analytics WakeLock");
        zzcrz.setReferenceCounted(false);
      }
      zzcrz.acquire(1000L);
    }
    catch (SecurityException paramContext)
    {
      for (;;)
      {
        localzzaf.zzel("Analytics service at risk of not starting. For more reliable analytics, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzaj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */