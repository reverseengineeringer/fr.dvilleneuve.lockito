package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Process;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import java.io.IOException;

public class zza
{
  private static Object auM = new Object();
  private static zza auN;
  private volatile long auG = 900000L;
  private volatile long auH = 30000L;
  private volatile long auI;
  private volatile long auJ;
  private final Object auK = new Object();
  private zza auL = new zza()
  {
    public AdvertisingIdClient.Info zzcab()
    {
      try
      {
        AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(zza.zza(zza.this));
        return localInfo;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        zzbn.zzd("IllegalStateException getting Advertising Id Info", localIllegalStateException);
        return null;
      }
      catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
      {
        zzbn.zzd("GooglePlayServicesRepairableException getting Advertising Id Info", localGooglePlayServicesRepairableException);
        return null;
      }
      catch (IOException localIOException)
      {
        zzbn.zzd("IOException getting Ad Id Info", localIOException);
        return null;
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
      {
        zzbn.zzd("GooglePlayServicesNotAvailableException getting Advertising Id Info", localGooglePlayServicesNotAvailableException);
        return null;
      }
      catch (Exception localException)
      {
        zzbn.zzd("Unknown exception. Could not get the Advertising Id Info.", localException);
      }
      return null;
    }
  };
  private volatile boolean mClosed = false;
  private final Context mContext;
  private final zze zzaoa;
  private final Thread zzcko;
  private volatile AdvertisingIdClient.Info zzcwf;
  
  private zza(Context paramContext)
  {
    this(paramContext, null, zzh.zzavi());
  }
  
  public zza(Context paramContext, zza paramzza, zze paramzze)
  {
    zzaoa = paramzze;
    if (paramContext != null) {}
    for (mContext = paramContext.getApplicationContext();; mContext = paramContext)
    {
      if (paramzza != null) {
        auL = paramzza;
      }
      auI = zzaoa.currentTimeMillis();
      zzcko = new Thread(new Runnable()
      {
        public void run()
        {
          zza.zzb(zza.this);
        }
      });
      return;
    }
  }
  
  /* Error */
  private void zzbzx()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 105	com/google/android/gms/tagmanager/zza:zzbzy	()V
    //   6: aload_0
    //   7: ldc2_w 106
    //   10: invokevirtual 111	java/lang/Object:wait	(J)V
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: athrow
    //   21: astore_1
    //   22: goto -9 -> 13
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	25	0	this	zza
    //   16	4	1	localObject	Object
    //   21	1	1	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   2	13	16	finally
    //   13	15	16	finally
    //   17	19	16	finally
    //   2	13	21	java/lang/InterruptedException
  }
  
  private void zzbzy()
  {
    if (zzaoa.currentTimeMillis() - auI > auH) {}
    synchronized (auK)
    {
      auK.notify();
      auI = zzaoa.currentTimeMillis();
      return;
    }
  }
  
  private void zzbzz()
  {
    if (zzaoa.currentTimeMillis() - auJ > 3600000L) {
      zzcwf = null;
    }
  }
  
  private void zzcaa()
  {
    Process.setThreadPriority(10);
    while (!mClosed)
    {
      ??? = auL.zzcab();
      if (??? != null)
      {
        zzcwf = ((AdvertisingIdClient.Info)???);
        auJ = zzaoa.currentTimeMillis();
        zzbn.zzcx("Obtained fresh AdvertisingId info from GmsCore.");
      }
      try
      {
        notifyAll();
        try
        {
          synchronized (auK)
          {
            auK.wait(auG);
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          zzbn.zzcx("sleep interrupted in AdvertiserDataPoller thread; continuing");
        }
        return;
      }
      finally {}
    }
  }
  
  public static zza zzdr(Context paramContext)
  {
    if (auN == null) {}
    synchronized (auM)
    {
      if (auN == null)
      {
        auN = new zza(paramContext);
        auN.start();
      }
      return auN;
    }
  }
  
  public boolean isLimitAdTrackingEnabled()
  {
    if (zzcwf == null) {
      zzbzx();
    }
    for (;;)
    {
      zzbzz();
      if (zzcwf != null) {
        break;
      }
      return true;
      zzbzy();
    }
    return zzcwf.isLimitAdTrackingEnabled();
  }
  
  public void start()
  {
    zzcko.start();
  }
  
  public String zzbzw()
  {
    if (zzcwf == null) {
      zzbzx();
    }
    for (;;)
    {
      zzbzz();
      if (zzcwf != null) {
        break;
      }
      return null;
      zzbzy();
    }
    return zzcwf.getId();
  }
  
  public static abstract interface zza
  {
    public abstract AdvertisingIdClient.Info zzcab();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */