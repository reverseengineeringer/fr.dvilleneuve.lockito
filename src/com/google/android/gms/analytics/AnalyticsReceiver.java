package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.analytics.internal.zzaj;

public final class AnalyticsReceiver
  extends BroadcastReceiver
{
  private zzaj zzcrs;
  
  private zzaj zzvs()
  {
    if (zzcrs == null) {
      zzcrs = new zzaj();
    }
    return zzcrs;
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    zzvs().onReceive(paramContext, paramIntent);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.AnalyticsReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */