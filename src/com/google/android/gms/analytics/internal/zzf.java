package com.google.android.gms.analytics.internal;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zzh;

public class zzf
{
  private static zzf zzcwv;
  private final Context mContext;
  private final com.google.android.gms.common.util.zze zzaoa;
  private final Context zzcww;
  private final zzr zzcwx;
  private final zzaf zzcwy;
  private final zzi zzcwz;
  private final zzb zzcxa;
  private final zzv zzcxb;
  private final zzap zzcxc;
  private final zzai zzcxd;
  private final GoogleAnalytics zzcxe;
  private final zzn zzcxf;
  private final zza zzcxg;
  private final zzk zzcxh;
  private final zzu zzcxi;
  
  protected zzf(zzg paramzzg)
  {
    Object localObject1 = paramzzg.getApplicationContext();
    zzab.zzb(localObject1, "Application context can't be null");
    Object localObject2 = paramzzg.zzzi();
    zzab.zzaa(localObject2);
    mContext = ((Context)localObject1);
    zzcww = ((Context)localObject2);
    zzaoa = paramzzg.zzh(this);
    zzcwx = paramzzg.zzg(this);
    localObject2 = paramzzg.zzf(this);
    ((zzaf)localObject2).initialize();
    zzcwy = ((zzaf)localObject2);
    Object localObject3;
    if (zzyy().zzabc())
    {
      localObject2 = zzyx();
      localObject3 = zze.VERSION;
      ((zzaf)localObject2).zzek(String.valueOf(localObject3).length() + 33 + "Google Analytics " + (String)localObject3 + " is starting up.");
    }
    for (;;)
    {
      localObject2 = paramzzg.zzq(this);
      ((zzai)localObject2).initialize();
      zzcxd = ((zzai)localObject2);
      localObject2 = paramzzg.zze(this);
      ((zzap)localObject2).initialize();
      zzcxc = ((zzap)localObject2);
      localObject2 = paramzzg.zzl(this);
      localObject3 = paramzzg.zzd(this);
      zza localzza = paramzzg.zzc(this);
      zzk localzzk = paramzzg.zzb(this);
      zzu localzzu = paramzzg.zza(this);
      localObject1 = paramzzg.zzaz((Context)localObject1);
      ((zzi)localObject1).zza(zzzh());
      zzcwz = ((zzi)localObject1);
      localObject1 = paramzzg.zzi(this);
      ((zzn)localObject3).initialize();
      zzcxf = ((zzn)localObject3);
      localzza.initialize();
      zzcxg = localzza;
      localzzk.initialize();
      zzcxh = localzzk;
      localzzu.initialize();
      zzcxi = localzzu;
      paramzzg = paramzzg.zzp(this);
      paramzzg.initialize();
      zzcxb = paramzzg;
      ((zzb)localObject2).initialize();
      zzcxa = ((zzb)localObject2);
      if (zzyy().zzabc()) {
        zzyx().zzb("Device AnalyticsService version", zze.VERSION);
      }
      ((GoogleAnalytics)localObject1).initialize();
      zzcxe = ((GoogleAnalytics)localObject1);
      ((zzb)localObject2).start();
      return;
      localObject2 = zzyx();
      localObject3 = zze.VERSION;
      ((zzaf)localObject2).zzek(String.valueOf(localObject3).length() + 134 + "Google Analytics " + (String)localObject3 + " is starting up. To enable debug logging on a device run:\n  adb shell setprop log.tag.GAv4 DEBUG\n  adb logcat -s GAv4");
    }
  }
  
  private void zza(zzd paramzzd)
  {
    zzab.zzb(paramzzd, "Analytics service not created/initialized");
    zzab.zzb(paramzzd.isInitialized(), "Analytics service not initialized");
  }
  
  public static zzf zzay(Context paramContext)
  {
    zzab.zzaa(paramContext);
    if (zzcwv == null) {}
    try
    {
      if (zzcwv == null)
      {
        com.google.android.gms.common.util.zze localzze = zzh.zzavi();
        long l1 = localzze.elapsedRealtime();
        paramContext = new zzf(new zzg(paramContext));
        zzcwv = paramContext;
        GoogleAnalytics.zzwa();
        l1 = localzze.elapsedRealtime() - l1;
        long l2 = ((Long)zzy.H.get()).longValue();
        if (l1 > l2) {
          paramContext.zzyx().zzc("Slow initialization (ms)", Long.valueOf(l1), Long.valueOf(l2));
        }
      }
      return zzcwv;
    }
    finally {}
  }
  
  public Context getContext()
  {
    return mContext;
  }
  
  public zzb zzwd()
  {
    zza(zzcxa);
    return zzcxa;
  }
  
  public zzap zzwe()
  {
    zza(zzcxc);
    return zzcxc;
  }
  
  public void zzwu() {}
  
  public com.google.android.gms.common.util.zze zzyw()
  {
    return zzaoa;
  }
  
  public zzaf zzyx()
  {
    zza(zzcwy);
    return zzcwy;
  }
  
  public zzr zzyy()
  {
    return zzcwx;
  }
  
  public zzi zzyz()
  {
    zzab.zzaa(zzcwz);
    return zzcwz;
  }
  
  public zzv zzza()
  {
    zza(zzcxb);
    return zzcxb;
  }
  
  public zzai zzzb()
  {
    zza(zzcxd);
    return zzcxd;
  }
  
  public zzk zzze()
  {
    zza(zzcxh);
    return zzcxh;
  }
  
  public zzu zzzf()
  {
    return zzcxi;
  }
  
  protected Thread.UncaughtExceptionHandler zzzh()
  {
    new Thread.UncaughtExceptionHandler()
    {
      public void uncaughtException(Thread paramAnonymousThread, Throwable paramAnonymousThrowable)
      {
        paramAnonymousThread = zzzj();
        if (paramAnonymousThread != null) {
          paramAnonymousThread.zze("Job execution failed", paramAnonymousThrowable);
        }
      }
    };
  }
  
  public Context zzzi()
  {
    return zzcww;
  }
  
  public zzaf zzzj()
  {
    return zzcwy;
  }
  
  public GoogleAnalytics zzzk()
  {
    zzab.zzaa(zzcxe);
    zzab.zzb(zzcxe.isInitialized(), "Analytics instance not initialized");
    return zzcxe;
  }
  
  public zzai zzzl()
  {
    if ((zzcxd == null) || (!zzcxd.isInitialized())) {
      return null;
    }
    return zzcxd;
  }
  
  public zza zzzm()
  {
    zza(zzcxg);
    return zzcxg;
  }
  
  public zzn zzzn()
  {
    zza(zzcxf);
    return zzcxf;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */