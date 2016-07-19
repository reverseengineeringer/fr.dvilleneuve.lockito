package com.google.android.gms.analytics;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.gms.analytics.internal.zzao;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzly;
import com.google.android.gms.internal.zzmd;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzi
{
  private static volatile zzi zzctl;
  private final Context mContext;
  private final List<zzj> zzctm;
  private final zzd zzctn;
  private final zza zzcto;
  private volatile zzly zzctp;
  private Thread.UncaughtExceptionHandler zzctq;
  
  zzi(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext();
    zzab.zzaa(paramContext);
    mContext = paramContext;
    zzcto = new zza();
    zzctm = new CopyOnWriteArrayList();
    zzctn = new zzd();
  }
  
  public static zzi zzax(Context paramContext)
  {
    zzab.zzaa(paramContext);
    if (zzctl == null) {}
    try
    {
      if (zzctl == null) {
        zzctl = new zzi(paramContext);
      }
      return zzctl;
    }
    finally {}
  }
  
  private void zzb(zze paramzze)
  {
    zzab.zzhk("deliver should be called from worker thread");
    zzab.zzb(paramzze.zzwk(), "Measurement must be submitted");
    Object localObject = paramzze.zzwh();
    if (((List)localObject).isEmpty()) {}
    for (;;)
    {
      return;
      HashSet localHashSet = new HashSet();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        zzk localzzk = (zzk)((Iterator)localObject).next();
        Uri localUri = localzzk.zzvu();
        if (!localHashSet.contains(localUri))
        {
          localHashSet.add(localUri);
          localzzk.zzb(paramzze);
        }
      }
    }
  }
  
  public static void zzwu()
  {
    if (!(Thread.currentThread() instanceof zzc)) {
      throw new IllegalStateException("Call expected from worker thread");
    }
  }
  
  public Context getContext()
  {
    return mContext;
  }
  
  public void zza(Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler)
  {
    zzctq = paramUncaughtExceptionHandler;
  }
  
  public <V> Future<V> zzc(Callable<V> paramCallable)
  {
    zzab.zzaa(paramCallable);
    if ((Thread.currentThread() instanceof zzc))
    {
      paramCallable = new FutureTask(paramCallable);
      paramCallable.run();
      return paramCallable;
    }
    return zzcto.submit(paramCallable);
  }
  
  void zze(final zze paramzze)
  {
    if (paramzze.zzwo()) {
      throw new IllegalStateException("Measurement prototype can't be submitted");
    }
    if (paramzze.zzwk()) {
      throw new IllegalStateException("Measurement can only be submitted once");
    }
    paramzze = paramzze.zzwf();
    paramzze.zzwl();
    zzcto.execute(new Runnable()
    {
      public void run()
      {
        paramzze.zzwm().zza(paramzze);
        Iterator localIterator = zzi.zza(zzi.this).iterator();
        while (localIterator.hasNext()) {
          ((zzj)localIterator.next()).zza(paramzze);
        }
        zzi.zza(zzi.this, paramzze);
      }
    });
  }
  
  public void zzf(Runnable paramRunnable)
  {
    zzab.zzaa(paramRunnable);
    zzcto.submit(paramRunnable);
  }
  
  public zzly zzws()
  {
    if (zzctp == null) {}
    label204:
    for (;;)
    {
      try
      {
        zzly localzzly;
        PackageManager localPackageManager;
        String str3;
        Object localObject5;
        Object localObject3;
        if (zzctp == null)
        {
          localzzly = new zzly();
          localPackageManager = mContext.getPackageManager();
          str3 = mContext.getPackageName();
          localzzly.setAppId(str3);
          localzzly.setAppInstallerId(localPackageManager.getInstallerPackageName(str3));
          localObject5 = null;
          localObject3 = str3;
        }
        try
        {
          PackageInfo localPackageInfo = localPackageManager.getPackageInfo(mContext.getPackageName(), 0);
          localObject4 = localObject5;
          String str1 = str3;
          if (localPackageInfo != null)
          {
            localObject3 = str3;
            localObject4 = localPackageManager.getApplicationLabel(applicationInfo);
            str1 = str3;
            localObject3 = str3;
            if (!TextUtils.isEmpty((CharSequence)localObject4))
            {
              localObject3 = str3;
              str1 = ((CharSequence)localObject4).toString();
            }
            localObject3 = str1;
            localObject4 = versionName;
          }
          localzzly.setAppName(str1);
          localzzly.setAppVersion((String)localObject4);
          zzctp = localzzly;
          return zzctp;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          localObject1 = String.valueOf(localObject3);
          if (((String)localObject1).length() == 0) {
            break label204;
          }
        }
        Object localObject1 = "Error retrieving package info: appName set to ".concat((String)localObject1);
        Log.e("GAv4", (String)localObject1);
        Object localObject4 = localObject5;
        localObject1 = localObject3;
        continue;
        String str2 = new String("Error retrieving package info: appName set to ");
      }
      finally {}
    }
  }
  
  public zzmd zzwt()
  {
    DisplayMetrics localDisplayMetrics = mContext.getResources().getDisplayMetrics();
    zzmd localzzmd = new zzmd();
    localzzmd.setLanguage(zzao.zza(Locale.getDefault()));
    localzzmd.zzbq(widthPixels);
    localzzmd.zzbr(heightPixels);
    return localzzmd;
  }
  
  private class zza
    extends ThreadPoolExecutor
  {
    public zza()
    {
      super(1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
      setThreadFactory(new zzi.zzb(null));
    }
    
    protected <T> RunnableFuture<T> newTaskFor(Runnable paramRunnable, T paramT)
    {
      new FutureTask(paramRunnable, paramT)
      {
        protected void setException(Throwable paramAnonymousThrowable)
        {
          Object localObject = zzi.zzb(zzi.this);
          if (localObject != null) {
            ((Thread.UncaughtExceptionHandler)localObject).uncaughtException(Thread.currentThread(), paramAnonymousThrowable);
          }
          for (;;)
          {
            super.setException(paramAnonymousThrowable);
            return;
            if (Log.isLoggable("GAv4", 6))
            {
              localObject = String.valueOf(paramAnonymousThrowable);
              Log.e("GAv4", String.valueOf(localObject).length() + 37 + "MeasurementExecutor: job failed with " + (String)localObject);
            }
          }
        }
      };
    }
  }
  
  private static class zzb
    implements ThreadFactory
  {
    private static final AtomicInteger zzctu = new AtomicInteger();
    
    public Thread newThread(Runnable paramRunnable)
    {
      int i = zzctu.incrementAndGet();
      return new zzi.zzc(paramRunnable, 23 + "measurement-" + i);
    }
  }
  
  private static class zzc
    extends Thread
  {
    zzc(Runnable paramRunnable, String paramString)
    {
      super(paramString);
    }
    
    public void run()
    {
      Process.setThreadPriority(10);
      super.run();
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.zzi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */