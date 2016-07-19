package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzah.zzj;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class zzct
  implements zzp.zze
{
  private final String auZ;
  private String avw;
  private zzs axA;
  private final zza axC;
  private ScheduledFuture<?> axD;
  private zzbm<zzah.zzj> axz;
  private boolean mClosed;
  private final Context mContext;
  private final ScheduledExecutorService qv;
  
  public zzct(Context paramContext, String paramString, zzs paramzzs)
  {
    this(paramContext, paramString, paramzzs, null, null);
  }
  
  zzct(Context paramContext, String paramString, zzs paramzzs, zzb paramzzb, zza paramzza)
  {
    axA = paramzzs;
    mContext = paramContext;
    auZ = paramString;
    paramContext = paramzzb;
    if (paramzzb == null) {
      paramContext = new zzb()
      {
        public ScheduledExecutorService zzccl()
        {
          return Executors.newSingleThreadScheduledExecutor();
        }
      };
    }
    qv = paramContext.zzccl();
    if (paramzza == null)
    {
      axC = new zza()
      {
        public zzcs zza(zzs paramAnonymouszzs)
        {
          return new zzcs(zzct.zza(zzct.this), zzct.zzb(zzct.this), paramAnonymouszzs);
        }
      };
      return;
    }
    axC = paramzza;
  }
  
  private void zzcck()
  {
    try
    {
      if (mClosed) {
        throw new IllegalStateException("called method after closed");
      }
    }
    finally {}
  }
  
  private zzcs zzol(String paramString)
  {
    zzcs localzzcs = axC.zza(axA);
    localzzcs.zza(axz);
    localzzcs.zznv(avw);
    localzzcs.zzok(paramString);
    return localzzcs;
  }
  
  public void release()
  {
    try
    {
      zzcck();
      if (axD != null) {
        axD.cancel(false);
      }
      qv.shutdown();
      mClosed = true;
      return;
    }
    finally {}
  }
  
  public void zza(zzbm<zzah.zzj> paramzzbm)
  {
    try
    {
      zzcck();
      axz = paramzzbm;
      return;
    }
    finally
    {
      paramzzbm = finally;
      throw paramzzbm;
    }
  }
  
  public void zzf(long paramLong, String paramString)
  {
    try
    {
      String str = auZ;
      zzbn.v(String.valueOf(str).length() + 55 + "loadAfterDelay: containerId=" + str + " delay=" + paramLong);
      zzcck();
      if (axz == null) {
        throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
      }
    }
    finally {}
    if (axD != null) {
      axD.cancel(false);
    }
    axD = qv.schedule(zzol(paramString), paramLong, TimeUnit.MILLISECONDS);
  }
  
  public void zznv(String paramString)
  {
    try
    {
      zzcck();
      avw = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  static abstract interface zza
  {
    public abstract zzcs zza(zzs paramzzs);
  }
  
  static abstract interface zzb
  {
    public abstract ScheduledExecutorService zzccl();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzct
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */