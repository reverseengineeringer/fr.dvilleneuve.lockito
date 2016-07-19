package com.google.android.gms.analytics.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;

public class zzv
  extends zzd
{
  private boolean zzczm;
  private boolean zzczn;
  private AlarmManager zzczo = (AlarmManager)getContext().getSystemService("alarm");
  
  protected zzv(zzf paramzzf)
  {
    super(paramzzf);
  }
  
  private PendingIntent zzacn()
  {
    Intent localIntent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
    localIntent.setComponent(new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"));
    return PendingIntent.getBroadcast(getContext(), 0, localIntent, 0);
  }
  
  public void cancel()
  {
    zzzg();
    zzczn = false;
    zzczo.cancel(zzacn());
  }
  
  public void schedule()
  {
    zzzg();
    zzab.zza(zzacm(), "Receiver not registered");
    long l1 = zzyy().zzabl();
    if (l1 > 0L)
    {
      cancel();
      long l2 = zzyw().elapsedRealtime();
      zzczn = true;
      zzczo.setInexactRepeating(2, l2 + l1, 0L, zzacn());
    }
  }
  
  public boolean zzacm()
  {
    return zzczm;
  }
  
  public boolean zzfc()
  {
    return zzczn;
  }
  
  protected void zzwv()
  {
    try
    {
      zzczo.cancel(zzacn());
      if (zzyy().zzabl() > 0L)
      {
        ActivityInfo localActivityInfo = getContext().getPackageManager().getReceiverInfo(new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"), 2);
        if ((localActivityInfo != null) && (enabled))
        {
          zzei("Receiver registered. Using alarm for local dispatch.");
          zzczm = true;
        }
      }
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */