package com.google.android.gms.analytics;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.analytics.internal.zzak;
import com.google.android.gms.analytics.internal.zzak.zza;

public final class AnalyticsService
  extends Service
  implements zzak.zza
{
  private zzak zzcrt;
  
  private zzak zzvt()
  {
    if (zzcrt == null) {
      zzcrt = new zzak(this);
    }
    return zzcrt;
  }
  
  public boolean callServiceStopSelfResult(int paramInt)
  {
    return stopSelfResult(paramInt);
  }
  
  public Context getContext()
  {
    return this;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return zzvt().onBind(paramIntent);
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public void onCreate()
  {
    super.onCreate();
    zzvt().onCreate();
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public void onDestroy()
  {
    zzvt().onDestroy();
    super.onDestroy();
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return zzvt().onStartCommand(paramIntent, paramInt1, paramInt2);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.AnalyticsService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */