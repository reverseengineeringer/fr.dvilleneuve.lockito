package com.google.android.gms.analytics.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.zzi;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzb
  extends zzd
{
  private final zzl zzcwk;
  
  public zzb(zzf paramzzf, zzg paramzzg)
  {
    super(paramzzf);
    com.google.android.gms.common.internal.zzab.zzaa(paramzzg);
    zzcwk = paramzzg.zzj(paramzzf);
  }
  
  void onServiceConnected()
  {
    zzwu();
    zzcwk.onServiceConnected();
  }
  
  public void setLocalDispatchPeriod(final int paramInt)
  {
    zzzg();
    zzb("setLocalDispatchPeriod (sec)", Integer.valueOf(paramInt));
    zzyz().zzf(new Runnable()
    {
      public void run()
      {
        zzb.zza(zzb.this).zzu(paramInt * 1000L);
      }
    });
  }
  
  public void start()
  {
    zzcwk.start();
  }
  
  public long zza(zzh paramzzh)
  {
    zzzg();
    com.google.android.gms.common.internal.zzab.zzaa(paramzzh);
    zzwu();
    long l = zzcwk.zza(paramzzh, true);
    if (l == 0L) {
      zzcwk.zzc(paramzzh);
    }
    return l;
  }
  
  public void zza(final zzab paramzzab)
  {
    com.google.android.gms.common.internal.zzab.zzaa(paramzzab);
    zzzg();
    zzb("Hit delivery requested", paramzzab);
    zzyz().zzf(new Runnable()
    {
      public void run()
      {
        zzb.zza(zzb.this).zza(paramzzab);
      }
    });
  }
  
  public void zza(final zzw paramzzw)
  {
    zzzg();
    zzyz().zzf(new Runnable()
    {
      public void run()
      {
        zzb.zza(zzb.this).zzb(paramzzw);
      }
    });
  }
  
  public void zza(final String paramString, final Runnable paramRunnable)
  {
    com.google.android.gms.common.internal.zzab.zzh(paramString, "campaign param can't be empty");
    zzyz().zzf(new Runnable()
    {
      public void run()
      {
        zzb.zza(zzb.this).zzeq(paramString);
        if (paramRunnable != null) {
          paramRunnable.run();
        }
      }
    });
  }
  
  public void zzas(final boolean paramBoolean)
  {
    zza("Network connectivity status changed", Boolean.valueOf(paramBoolean));
    zzyz().zzf(new Runnable()
    {
      public void run()
      {
        zzb.zza(zzb.this).zzas(paramBoolean);
      }
    });
  }
  
  protected void zzwv()
  {
    zzcwk.initialize();
  }
  
  public void zzyo()
  {
    zzzg();
    zzyv();
    zzyz().zzf(new Runnable()
    {
      public void run()
      {
        zzb.zza(zzb.this).zzyo();
      }
    });
  }
  
  public void zzyp()
  {
    zzzg();
    Context localContext = getContext();
    if ((zzaj.zzav(localContext)) && (zzak.zzaw(localContext)))
    {
      Intent localIntent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
      localIntent.setComponent(new ComponentName(localContext, "com.google.android.gms.analytics.AnalyticsService"));
      localContext.startService(localIntent);
      return;
    }
    zza(null);
  }
  
  public boolean zzyq()
  {
    zzzg();
    Future localFuture = zzyz().zzc(new Callable()
    {
      public Void zzcy()
        throws Exception
      {
        zzb.zza(zzb.this).zzaal();
        return null;
      }
    });
    try
    {
      localFuture.get(4L, TimeUnit.SECONDS);
      return true;
    }
    catch (InterruptedException localInterruptedException)
    {
      zzd("syncDispatchLocalHits interrupted", localInterruptedException);
      return false;
    }
    catch (ExecutionException localExecutionException)
    {
      zze("syncDispatchLocalHits failed", localExecutionException);
      return false;
    }
    catch (TimeoutException localTimeoutException)
    {
      zzd("syncDispatchLocalHits timed out", localTimeoutException);
    }
    return false;
  }
  
  public void zzyr()
  {
    zzzg();
    zzi.zzwu();
    zzcwk.zzyr();
  }
  
  public void zzys()
  {
    zzei("Radio powered up");
    zzyp();
  }
  
  void zzyt()
  {
    zzwu();
    zzcwk.zzyt();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */