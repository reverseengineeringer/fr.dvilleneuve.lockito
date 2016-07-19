package com.google.android.gms.analytics;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzao;
import com.google.android.gms.analytics.internal.zzb;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzr;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzvz;

public class CampaignTrackingService
  extends Service
{
  private static Boolean zzcsb;
  private Handler mHandler;
  
  private Handler getHandler()
  {
    Handler localHandler2 = mHandler;
    Handler localHandler1 = localHandler2;
    if (localHandler2 == null)
    {
      localHandler1 = new Handler(getMainLooper());
      mHandler = localHandler1;
    }
    return localHandler1;
  }
  
  public static boolean zzaw(Context paramContext)
  {
    zzab.zzaa(paramContext);
    if (zzcsb != null) {
      return zzcsb.booleanValue();
    }
    boolean bool = zzao.zzk(paramContext, "com.google.android.gms.analytics.CampaignTrackingService");
    zzcsb = Boolean.valueOf(bool);
    return bool;
  }
  
  private void zzvw()
  {
    try
    {
      synchronized (CampaignTrackingReceiver.zzamp)
      {
        zzvz localzzvz = CampaignTrackingReceiver.zzcrz;
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
    super.onCreate();
    zzf.zzay(this).zzyx().zzei("CampaignTrackingService is starting up");
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public void onDestroy()
  {
    zzf.zzay(this).zzyx().zzei("CampaignTrackingService is shutting down");
    super.onDestroy();
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public int onStartCommand(Intent paramIntent, int paramInt1, final int paramInt2)
  {
    zzvw();
    zzf localzzf = zzf.zzay(this);
    final zzaf localzzaf = localzzf.zzyx();
    final Handler localHandler = null;
    if (localzzf.zzyy().zzabc()) {
      localzzaf.zzem("Unexpected installation campaign (package side)");
    }
    for (paramIntent = localHandler;; paramIntent = paramIntent.getStringExtra("referrer"))
    {
      localHandler = getHandler();
      if (!TextUtils.isEmpty(paramIntent)) {
        break;
      }
      if (!localzzf.zzyy().zzabc()) {
        localzzaf.zzel("No campaign found on com.android.vending.INSTALL_REFERRER \"referrer\" extra");
      }
      localzzf.zzyz().zzf(new Runnable()
      {
        public void run()
        {
          zza(localzzaf, localHandler, paramInt2);
        }
      });
      return 2;
    }
    paramInt1 = localzzf.zzyy().zzabg();
    if (paramIntent.length() <= paramInt1) {}
    for (;;)
    {
      localzzaf.zza("CampaignTrackingService called. startId, campaign", Integer.valueOf(paramInt2), paramIntent);
      localzzf.zzwd().zza(paramIntent, new Runnable()
      {
        public void run()
        {
          zza(localzzaf, localHandler, paramInt2);
        }
      });
      return 2;
      localzzaf.zzc("Campaign data exceed the maximum supported size and will be clipped. size, limit", Integer.valueOf(paramIntent.length()), Integer.valueOf(paramInt1));
      paramIntent = paramIntent.substring(0, paramInt1);
    }
  }
  
  protected void zza(final zzaf paramzzaf, Handler paramHandler, final int paramInt)
  {
    paramHandler.post(new Runnable()
    {
      public void run()
      {
        boolean bool = stopSelfResult(paramInt);
        if (bool) {
          paramzzaf.zza("Install campaign broadcast processed", Boolean.valueOf(bool));
        }
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.CampaignTrackingService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */