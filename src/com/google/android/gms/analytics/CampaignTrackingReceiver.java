package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzao;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzr;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzvz;

public class CampaignTrackingReceiver
  extends BroadcastReceiver
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
    boolean bool = zzao.zzb(paramContext, "com.google.android.gms.analytics.CampaignTrackingReceiver", true);
    zzcsa = Boolean.valueOf(bool);
    return bool;
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public void onReceive(Context paramContext, Intent arg2)
  {
    Object localObject = zzf.zzay(paramContext);
    localzzaf = ((zzf)localObject).zzyx();
    if (??? == null)
    {
      localzzaf.zzel("CampaignTrackingReceiver received null intent");
      return;
    }
    String str = ???.getStringExtra("referrer");
    ??? = ???.getAction();
    localzzaf.zza("CampaignTrackingReceiver received", ???);
    if ((!"com.android.vending.INSTALL_REFERRER".equals(???)) || (TextUtils.isEmpty(str)))
    {
      localzzaf.zzel("CampaignTrackingReceiver received unexpected intent without referrer extra");
      return;
    }
    boolean bool = CampaignTrackingService.zzaw(paramContext);
    if (!bool) {
      localzzaf.zzel("CampaignTrackingService not registered or disabled. Installation tracking not possible. See http://goo.gl/8Rd3yj for instructions.");
    }
    zzi(paramContext, str);
    if (((zzf)localObject).zzyy().zzabc())
    {
      localzzaf.zzem("Received unexpected installation campaign on package side");
      return;
    }
    ??? = zzvv();
    zzab.zzaa(???);
    localObject = new Intent(paramContext, ???);
    ((Intent)localObject).putExtra("referrer", str);
    synchronized (zzamp)
    {
      paramContext.startService((Intent)localObject);
      if (!bool) {
        return;
      }
    }
    try
    {
      if (zzcrz == null)
      {
        zzcrz = new zzvz(paramContext, 1, "Analytics campaign WakeLock");
        zzcrz.setReferenceCounted(false);
      }
      zzcrz.acquire(1000L);
    }
    catch (SecurityException paramContext)
    {
      for (;;)
      {
        localzzaf.zzel("CampaignTrackingService service at risk of not starting. For more reliable installation campaign reports, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
      }
    }
  }
  
  protected void zzi(Context paramContext, String paramString) {}
  
  protected Class<? extends CampaignTrackingService> zzvv()
  {
    return CampaignTrackingService.class;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.CampaignTrackingReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */