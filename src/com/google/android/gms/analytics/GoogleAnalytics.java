package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import com.google.android.gms.analytics.internal.zzae;
import com.google.android.gms.analytics.internal.zzam;
import com.google.android.gms.analytics.internal.zzan;
import com.google.android.gms.analytics.internal.zzap;
import com.google.android.gms.analytics.internal.zzb;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzn;
import com.google.android.gms.analytics.internal.zzy;
import com.google.android.gms.analytics.internal.zzy.zza;
import com.google.android.gms.common.internal.zzab;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class GoogleAnalytics
  extends zza
{
  private static List<Runnable> zzcsj = new ArrayList();
  private boolean zzamr;
  private Set<zza> zzcsk = new HashSet();
  private boolean zzcsl;
  private boolean zzcsm;
  private volatile boolean zzcsn;
  private boolean zzcso;
  
  public GoogleAnalytics(zzf paramzzf)
  {
    super(paramzzf);
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public static GoogleAnalytics getInstance(Context paramContext)
  {
    return zzf.zzay(paramContext).zzzk();
  }
  
  public static void zzwa()
  {
    try
    {
      if (zzcsj != null)
      {
        Iterator localIterator = zzcsj.iterator();
        while (localIterator.hasNext()) {
          ((Runnable)localIterator.next()).run();
        }
        zzcsj = null;
      }
    }
    finally {}
  }
  
  private zzb zzwd()
  {
    return zzvq().zzwd();
  }
  
  private zzap zzwe()
  {
    return zzvq().zzwe();
  }
  
  public void dispatchLocalHits()
  {
    zzwd().zzyp();
  }
  
  @TargetApi(14)
  public void enableAutoActivityReports(Application paramApplication)
  {
    if ((Build.VERSION.SDK_INT >= 14) && (!zzcsl))
    {
      paramApplication.registerActivityLifecycleCallbacks(new zzb());
      zzcsl = true;
    }
  }
  
  public boolean getAppOptOut()
  {
    return zzcsn;
  }
  
  @Deprecated
  public Logger getLogger()
  {
    return zzae.getLogger();
  }
  
  public void initialize()
  {
    zzvz();
    zzamr = true;
  }
  
  public boolean isDryRunEnabled()
  {
    return zzcsm;
  }
  
  public boolean isInitialized()
  {
    return zzamr;
  }
  
  public Tracker newTracker(int paramInt)
  {
    try
    {
      Tracker localTracker = new Tracker(zzvq(), null, null);
      if (paramInt > 0)
      {
        zzan localzzan = (zzan)new zzam(zzvq()).zzbx(paramInt);
        if (localzzan != null) {
          localTracker.zza(localzzan);
        }
      }
      localTracker.initialize();
      return localTracker;
    }
    finally {}
  }
  
  public Tracker newTracker(String paramString)
  {
    try
    {
      paramString = new Tracker(zzvq(), paramString, null);
      paramString.initialize();
      return paramString;
    }
    finally {}
  }
  
  public void reportActivityStart(Activity paramActivity)
  {
    if (!zzcsl) {
      zzm(paramActivity);
    }
  }
  
  public void reportActivityStop(Activity paramActivity)
  {
    if (!zzcsl) {
      zzn(paramActivity);
    }
  }
  
  public void setAppOptOut(boolean paramBoolean)
  {
    zzcsn = paramBoolean;
    if (zzcsn) {
      zzwd().zzyo();
    }
  }
  
  public void setDryRun(boolean paramBoolean)
  {
    zzcsm = paramBoolean;
  }
  
  public void setLocalDispatchPeriod(int paramInt)
  {
    zzwd().setLocalDispatchPeriod(paramInt);
  }
  
  @Deprecated
  public void setLogger(Logger paramLogger)
  {
    zzae.setLogger(paramLogger);
    if (!zzcso)
    {
      paramLogger = (String)zzy.zzczr.get();
      String str = (String)zzy.zzczr.get();
      Log.i(paramLogger, String.valueOf(str).length() + 112 + "GoogleAnalytics.setLogger() is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag." + str + " DEBUG");
      zzcso = true;
    }
  }
  
  void zza(zza paramzza)
  {
    zzcsk.add(paramzza);
    paramzza = zzvq().getContext();
    if ((paramzza instanceof Application)) {
      enableAutoActivityReports((Application)paramzza);
    }
  }
  
  void zzb(zza paramzza)
  {
    zzcsk.remove(paramzza);
  }
  
  void zzm(Activity paramActivity)
  {
    Iterator localIterator = zzcsk.iterator();
    while (localIterator.hasNext()) {
      ((zza)localIterator.next()).zzo(paramActivity);
    }
  }
  
  void zzn(Activity paramActivity)
  {
    Iterator localIterator = zzcsk.iterator();
    while (localIterator.hasNext()) {
      ((zza)localIterator.next()).zzp(paramActivity);
    }
  }
  
  void zzvz()
  {
    zzap localzzap = zzwe();
    if (localzzap.zzacr()) {
      getLogger().setLogLevel(localzzap.getLogLevel());
    }
    if (localzzap.zzacv()) {
      setDryRun(localzzap.zzacw());
    }
    if (localzzap.zzacr())
    {
      Logger localLogger = zzae.getLogger();
      if (localLogger != null) {
        localLogger.setLogLevel(localzzap.getLogLevel());
      }
    }
  }
  
  public String zzwb()
  {
    zzab.zzhk("getClientId can not be called from the main thread");
    return zzvq().zzzn().zzaav();
  }
  
  void zzwc()
  {
    zzwd().zzyq();
  }
  
  static abstract interface zza
  {
    public abstract void zzo(Activity paramActivity);
    
    public abstract void zzp(Activity paramActivity);
  }
  
  @TargetApi(14)
  class zzb
    implements Application.ActivityLifecycleCallbacks
  {
    zzb() {}
    
    public void onActivityCreated(Activity paramActivity, Bundle paramBundle) {}
    
    public void onActivityDestroyed(Activity paramActivity) {}
    
    public void onActivityPaused(Activity paramActivity) {}
    
    public void onActivityResumed(Activity paramActivity) {}
    
    public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
    
    public void onActivityStarted(Activity paramActivity)
    {
      zzm(paramActivity);
    }
    
    public void onActivityStopped(Activity paramActivity)
    {
      zzn(paramActivity);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.GoogleAnalytics
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */