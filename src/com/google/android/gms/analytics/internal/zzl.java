package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.CampaignTrackingService;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.internal.zzly;
import com.google.android.gms.internal.zzlz;
import com.google.android.gms.internal.zzmc;
import com.google.android.gms.internal.zzmh;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzl
  extends zzd
{
  private boolean mStarted;
  private final zzj zzcyf;
  private final zzah zzcyg;
  private final zzag zzcyh;
  private final zzi zzcyi;
  private long zzcyj;
  private final zzt zzcyk;
  private final zzt zzcyl;
  private final zzal zzcym;
  private long zzcyn;
  private boolean zzcyo;
  
  protected zzl(zzf paramzzf, zzg paramzzg)
  {
    super(paramzzf);
    com.google.android.gms.common.internal.zzab.zzaa(paramzzg);
    zzcyj = Long.MIN_VALUE;
    zzcyh = paramzzg.zzk(paramzzf);
    zzcyf = paramzzg.zzm(paramzzf);
    zzcyg = paramzzg.zzn(paramzzf);
    zzcyi = paramzzg.zzo(paramzzf);
    zzcym = new zzal(zzyw());
    zzcyk = new zzt(paramzzf)
    {
      public void run()
      {
        zzl.zza(zzl.this);
      }
    };
    zzcyl = new zzt(paramzzf)
    {
      public void run()
      {
        zzl.zzb(zzl.this);
      }
    };
  }
  
  private void zza(zzh paramzzh, zzlz paramzzlz)
  {
    com.google.android.gms.common.internal.zzab.zzaa(paramzzh);
    com.google.android.gms.common.internal.zzab.zzaa(paramzzlz);
    Object localObject1 = new zza(zzyu());
    ((zza)localObject1).zzdh(paramzzh.zzzp());
    ((zza)localObject1).enableAdvertisingIdCollection(paramzzh.zzzq());
    localObject1 = ((zza)localObject1).zzvr();
    zzmh localzzmh = (zzmh)((com.google.android.gms.analytics.zze)localObject1).zzb(zzmh.class);
    localzzmh.zzdx("data");
    localzzmh.zzap(true);
    ((com.google.android.gms.analytics.zze)localObject1).zza(paramzzlz);
    zzmc localzzmc = (zzmc)((com.google.android.gms.analytics.zze)localObject1).zzb(zzmc.class);
    zzly localzzly = (zzly)((com.google.android.gms.analytics.zze)localObject1).zzb(zzly.class);
    Iterator localIterator = paramzzh.zzm().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = (Map.Entry)localIterator.next();
      String str = (String)((Map.Entry)localObject2).getKey();
      localObject2 = (String)((Map.Entry)localObject2).getValue();
      if ("an".equals(str)) {
        localzzly.setAppName((String)localObject2);
      } else if ("av".equals(str)) {
        localzzly.setAppVersion((String)localObject2);
      } else if ("aid".equals(str)) {
        localzzly.setAppId((String)localObject2);
      } else if ("aiid".equals(str)) {
        localzzly.setAppInstallerId((String)localObject2);
      } else if ("uid".equals(str)) {
        localzzmh.setUserId((String)localObject2);
      } else {
        localzzmc.set(str, (String)localObject2);
      }
    }
    zzb("Sending installation campaign to", paramzzh.zzzp(), paramzzlz);
    ((com.google.android.gms.analytics.zze)localObject1).zzn(zzzb().zzadn());
    ((com.google.android.gms.analytics.zze)localObject1).zzwj();
  }
  
  private void zzaae()
  {
    zzwu();
    Context localContext = zzyu().getContext();
    if (!zzaj.zzav(localContext)) {
      zzel("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
    }
    do
    {
      while (!CampaignTrackingReceiver.zzav(localContext))
      {
        zzel("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        return;
        if (!zzak.zzaw(localContext)) {
          zzem("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
      }
    } while (CampaignTrackingService.zzaw(localContext));
    zzel("CampaignTrackingService is not registered or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
  }
  
  private void zzaag()
  {
    zzb(new zzw()
    {
      public void zzd(Throwable paramAnonymousThrowable)
      {
        zzaam();
      }
    });
  }
  
  private void zzaah()
  {
    try
    {
      zzcyf.zzzy();
      zzaam();
      zzcyl.zzv(zzyy().zzace());
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;)
      {
        zzd("Failed to delete stale hits", localSQLiteException);
      }
    }
  }
  
  private boolean zzaan()
  {
    if (zzcyo) {}
    while (((zzyy().zzabc()) && (!zzyy().zzabd())) || (zzaat() <= 0L)) {
      return false;
    }
    return true;
  }
  
  private void zzaao()
  {
    zzv localzzv = zzza();
    if (!localzzv.zzacm()) {}
    long l;
    do
    {
      do
      {
        return;
      } while (localzzv.zzfc());
      l = zzzz();
    } while ((l == 0L) || (Math.abs(zzyw().currentTimeMillis() - l) > zzyy().zzabm()));
    zza("Dispatch alarm scheduled (ms)", Long.valueOf(zzyy().zzabl()));
    localzzv.schedule();
  }
  
  private void zzaap()
  {
    zzaao();
    long l2 = zzaat();
    long l1 = zzzb().zzadp();
    if (l1 != 0L)
    {
      l1 = l2 - Math.abs(zzyw().currentTimeMillis() - l1);
      if (l1 <= 0L) {}
    }
    for (;;)
    {
      zza("Dispatch scheduled (ms)", Long.valueOf(l1));
      if (!zzcyk.zzfc()) {
        break;
      }
      l1 = Math.max(1L, l1 + zzcyk.zzacj());
      zzcyk.zzw(l1);
      return;
      l1 = Math.min(zzyy().zzabj(), l2);
      continue;
      l1 = Math.min(zzyy().zzabj(), l2);
    }
    zzcyk.zzv(l1);
  }
  
  private void zzaaq()
  {
    zzaar();
    zzaas();
  }
  
  private void zzaar()
  {
    if (zzcyk.zzfc()) {
      zzei("All hits dispatched or no network/service. Going to power save mode");
    }
    zzcyk.cancel();
  }
  
  private void zzaas()
  {
    zzv localzzv = zzza();
    if (localzzv.zzfc()) {
      localzzv.cancel();
    }
  }
  
  private boolean zzep(String paramString)
  {
    return getContext().checkCallingOrSelfPermission(paramString) == 0;
  }
  
  protected void onServiceConnected()
  {
    zzwu();
    if (!zzyy().zzabc()) {
      zzaaj();
    }
  }
  
  void start()
  {
    zzzg();
    if (!mStarted) {}
    for (boolean bool = true;; bool = false)
    {
      com.google.android.gms.common.internal.zzab.zza(bool, "Analytics backend already started");
      mStarted = true;
      zzyz().zzf(new Runnable()
      {
        public void run()
        {
          zzaaf();
        }
      });
      return;
    }
  }
  
  /* Error */
  public long zza(zzh paramzzh, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 44	com/google/android/gms/common/internal/zzab:zzaa	(Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: aload_0
    //   6: invokevirtual 437	com/google/android/gms/analytics/internal/zzl:zzzg	()V
    //   9: aload_0
    //   10: invokevirtual 245	com/google/android/gms/analytics/internal/zzl:zzwu	()V
    //   13: aload_0
    //   14: getfield 62	com/google/android/gms/analytics/internal/zzl:zzcyf	Lcom/google/android/gms/analytics/internal/zzj;
    //   17: invokevirtual 459	com/google/android/gms/analytics/internal/zzj:beginTransaction	()V
    //   20: aload_0
    //   21: getfield 62	com/google/android/gms/analytics/internal/zzl:zzcyf	Lcom/google/android/gms/analytics/internal/zzj;
    //   24: aload_1
    //   25: invokevirtual 462	com/google/android/gms/analytics/internal/zzh:zzzo	()J
    //   28: aload_1
    //   29: invokevirtual 465	com/google/android/gms/analytics/internal/zzh:zzwb	()Ljava/lang/String;
    //   32: invokevirtual 468	com/google/android/gms/analytics/internal/zzj:zza	(JLjava/lang/String;)V
    //   35: aload_0
    //   36: getfield 62	com/google/android/gms/analytics/internal/zzl:zzcyf	Lcom/google/android/gms/analytics/internal/zzj;
    //   39: aload_1
    //   40: invokevirtual 462	com/google/android/gms/analytics/internal/zzh:zzzo	()J
    //   43: aload_1
    //   44: invokevirtual 465	com/google/android/gms/analytics/internal/zzh:zzwb	()Ljava/lang/String;
    //   47: aload_1
    //   48: invokevirtual 109	com/google/android/gms/analytics/internal/zzh:zzzp	()Ljava/lang/String;
    //   51: invokevirtual 471	com/google/android/gms/analytics/internal/zzj:zzb	(JLjava/lang/String;Ljava/lang/String;)J
    //   54: lstore_3
    //   55: iload_2
    //   56: ifne +32 -> 88
    //   59: aload_1
    //   60: lload_3
    //   61: invokevirtual 474	com/google/android/gms/analytics/internal/zzh:zzp	(J)V
    //   64: aload_0
    //   65: getfield 62	com/google/android/gms/analytics/internal/zzl:zzcyf	Lcom/google/android/gms/analytics/internal/zzj;
    //   68: aload_1
    //   69: invokevirtual 477	com/google/android/gms/analytics/internal/zzj:zzb	(Lcom/google/android/gms/analytics/internal/zzh;)V
    //   72: aload_0
    //   73: getfield 62	com/google/android/gms/analytics/internal/zzl:zzcyf	Lcom/google/android/gms/analytics/internal/zzj;
    //   76: invokevirtual 480	com/google/android/gms/analytics/internal/zzj:setTransactionSuccessful	()V
    //   79: aload_0
    //   80: getfield 62	com/google/android/gms/analytics/internal/zzl:zzcyf	Lcom/google/android/gms/analytics/internal/zzj;
    //   83: invokevirtual 483	com/google/android/gms/analytics/internal/zzj:endTransaction	()V
    //   86: lload_3
    //   87: lreturn
    //   88: aload_1
    //   89: lconst_1
    //   90: lload_3
    //   91: ladd
    //   92: invokevirtual 474	com/google/android/gms/analytics/internal/zzh:zzp	(J)V
    //   95: goto -31 -> 64
    //   98: astore_1
    //   99: aload_0
    //   100: ldc_w 485
    //   103: aload_1
    //   104: invokevirtual 488	com/google/android/gms/analytics/internal/zzl:zze	(Ljava/lang/String;Ljava/lang/Object;)V
    //   107: aload_0
    //   108: getfield 62	com/google/android/gms/analytics/internal/zzl:zzcyf	Lcom/google/android/gms/analytics/internal/zzj;
    //   111: invokevirtual 483	com/google/android/gms/analytics/internal/zzj:endTransaction	()V
    //   114: ldc2_w 489
    //   117: lreturn
    //   118: astore_1
    //   119: aload_0
    //   120: ldc_w 492
    //   123: aload_1
    //   124: invokevirtual 488	com/google/android/gms/analytics/internal/zzl:zze	(Ljava/lang/String;Ljava/lang/Object;)V
    //   127: lload_3
    //   128: lreturn
    //   129: astore_1
    //   130: aload_0
    //   131: ldc_w 492
    //   134: aload_1
    //   135: invokevirtual 488	com/google/android/gms/analytics/internal/zzl:zze	(Ljava/lang/String;Ljava/lang/Object;)V
    //   138: goto -24 -> 114
    //   141: astore_1
    //   142: aload_0
    //   143: getfield 62	com/google/android/gms/analytics/internal/zzl:zzcyf	Lcom/google/android/gms/analytics/internal/zzj;
    //   146: invokevirtual 483	com/google/android/gms/analytics/internal/zzj:endTransaction	()V
    //   149: aload_1
    //   150: athrow
    //   151: astore 5
    //   153: aload_0
    //   154: ldc_w 492
    //   157: aload 5
    //   159: invokevirtual 488	com/google/android/gms/analytics/internal/zzl:zze	(Ljava/lang/String;Ljava/lang/Object;)V
    //   162: goto -13 -> 149
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	165	0	this	zzl
    //   0	165	1	paramzzh	zzh
    //   0	165	2	paramBoolean	boolean
    //   54	74	3	l	long
    //   151	7	5	localSQLiteException	SQLiteException
    // Exception table:
    //   from	to	target	type
    //   13	55	98	android/database/sqlite/SQLiteException
    //   59	64	98	android/database/sqlite/SQLiteException
    //   64	79	98	android/database/sqlite/SQLiteException
    //   88	95	98	android/database/sqlite/SQLiteException
    //   79	86	118	android/database/sqlite/SQLiteException
    //   107	114	129	android/database/sqlite/SQLiteException
    //   13	55	141	finally
    //   59	64	141	finally
    //   64	79	141	finally
    //   88	95	141	finally
    //   99	107	141	finally
    //   142	149	151	android/database/sqlite/SQLiteException
  }
  
  public void zza(zzab paramzzab)
  {
    com.google.android.gms.common.internal.zzab.zzaa(paramzzab);
    com.google.android.gms.analytics.zzi.zzwu();
    zzzg();
    if (zzcyo) {
      zzej("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
    }
    for (;;)
    {
      paramzzab = zzf(paramzzab);
      zzaai();
      if (!zzcyi.zzb(paramzzab)) {
        break;
      }
      zzej("Hit sent to the device AnalyticsService for delivery");
      return;
      zza("Delivering hit", paramzzab);
    }
    if (zzyy().zzabc())
    {
      zzyx().zza(paramzzab, "Service unavailable on package side");
      return;
    }
    try
    {
      zzcyf.zzc(paramzzab);
      zzaam();
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      zze("Delivery failed to save hit to a database", localSQLiteException);
      zzyx().zza(paramzzab, "deliver: failed to insert hit to database");
    }
  }
  
  public void zza(final zzw paramzzw, final long paramLong)
  {
    com.google.android.gms.analytics.zzi.zzwu();
    zzzg();
    long l1 = -1L;
    long l2 = zzzb().zzadp();
    if (l2 != 0L) {
      l1 = Math.abs(zzyw().currentTimeMillis() - l2);
    }
    zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(l1));
    if (!zzyy().zzabc()) {
      zzaai();
    }
    try
    {
      if (zzaak())
      {
        zzyz().zzf(new Runnable()
        {
          public void run()
          {
            zza(paramzzw, paramLong);
          }
        });
        return;
      }
      zzzb().zzadq();
      zzaam();
      if (paramzzw != null) {
        paramzzw.zzd(null);
      }
      if (zzcyn != paramLong)
      {
        zzcyh.zzadi();
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      zze("Local dispatch failed", localThrowable);
      zzzb().zzadq();
      zzaam();
      if (paramzzw != null) {
        paramzzw.zzd(localThrowable);
      }
    }
  }
  
  protected void zzaaf()
  {
    zzzg();
    if (!zzyy().zzabc()) {
      zzaae();
    }
    zzzb().zzadn();
    if (!zzep("android.permission.ACCESS_NETWORK_STATE"))
    {
      zzem("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
      zzaau();
    }
    if (!zzep("android.permission.INTERNET"))
    {
      zzem("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
      zzaau();
    }
    if (zzak.zzaw(getContext())) {
      zzei("AnalyticsService registered in the app manifest and enabled");
    }
    for (;;)
    {
      if ((!zzcyo) && (!zzyy().zzabc()) && (!zzcyf.isEmpty())) {
        zzaai();
      }
      zzaam();
      return;
      if (zzyy().zzabc()) {
        zzem("Device AnalyticsService not registered! Hits will not be delivered reliably.");
      } else {
        zzel("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
      }
    }
  }
  
  protected void zzaai()
  {
    if (zzcyo) {}
    do
    {
      long l;
      do
      {
        do
        {
          return;
        } while ((!zzyy().zzabe()) || (zzcyi.isConnected()));
        l = zzyy().zzabz();
      } while (!zzcym.zzx(l));
      zzcym.start();
      zzei("Connecting to service");
    } while (!zzcyi.connect());
    zzei("Connected to service");
    zzcym.clear();
    onServiceConnected();
  }
  
  public void zzaaj()
  {
    com.google.android.gms.analytics.zzi.zzwu();
    zzzg();
    zzyv();
    if (!zzyy().zzabe()) {
      zzel("Service client disabled. Can't dispatch local hits to device AnalyticsService");
    }
    if (!zzcyi.isConnected()) {
      zzei("Service not connected");
    }
    while (zzcyf.isEmpty()) {
      return;
    }
    zzei("Dispatching local hits to device AnalyticsService");
    for (;;)
    {
      try
      {
        List localList = zzcyf.zzr(zzyy().zzabn());
        if (!localList.isEmpty()) {
          break label126;
        }
        zzaam();
        return;
      }
      catch (SQLiteException localSQLiteException1)
      {
        zze("Failed to read hits from store", localSQLiteException1);
        zzaaq();
        return;
      }
      label107:
      Object localObject;
      localSQLiteException1.remove(localObject);
      try
      {
        zzcyf.zzs(((zzab)localObject).zzacy());
        label126:
        if (!localSQLiteException1.isEmpty())
        {
          localObject = (zzab)localSQLiteException1.get(0);
          if (zzcyi.zzb((zzab)localObject)) {
            break label107;
          }
          zzaam();
          return;
        }
      }
      catch (SQLiteException localSQLiteException2)
      {
        zze("Failed to remove hit that was send for delivery", localSQLiteException2);
        zzaaq();
      }
    }
  }
  
  protected boolean zzaak()
  {
    int j = 1;
    com.google.android.gms.analytics.zzi.zzwu();
    zzzg();
    zzei("Dispatching a batch of local hits");
    int i;
    if ((!zzcyi.isConnected()) && (!zzyy().zzabc()))
    {
      i = 1;
      if (zzcyg.zzadj()) {
        break label70;
      }
    }
    for (;;)
    {
      if ((i == 0) || (j == 0)) {
        break label75;
      }
      zzei("No network or service available. Will retry later");
      return false;
      i = 0;
      break;
      label70:
      j = 0;
    }
    label75:
    long l3 = Math.max(zzyy().zzabn(), zzyy().zzabo());
    ArrayList localArrayList = new ArrayList();
    l1 = 0L;
    for (;;)
    {
      try
      {
        zzcyf.beginTransaction();
        localArrayList.clear();
        try
        {
          localList = zzcyf.zzr(l3);
          if (localList.isEmpty())
          {
            zzei("Store is empty, nothing to dispatch");
            zzaaq();
            try
            {
              zzcyf.setTransactionSuccessful();
              zzcyf.endTransaction();
              return false;
            }
            catch (SQLiteException localSQLiteException1)
            {
              zze("Failed to commit local dispatch transaction", localSQLiteException1);
              zzaaq();
              return false;
            }
          }
          zza("Hits loaded from store. count", Integer.valueOf(localList.size()));
          localObject2 = localList.iterator();
          if (((Iterator)localObject2).hasNext())
          {
            if (((zzab)((Iterator)localObject2).next()).zzacy() != l1) {
              continue;
            }
            zzd("Database contains successfully uploaded hit", Long.valueOf(l1), Integer.valueOf(localList.size()));
            zzaaq();
            try
            {
              zzcyf.setTransactionSuccessful();
              zzcyf.endTransaction();
              return false;
            }
            catch (SQLiteException localSQLiteException2)
            {
              zze("Failed to commit local dispatch transaction", localSQLiteException2);
              zzaaq();
              return false;
            }
          }
          l2 = l1;
        }
        catch (SQLiteException localSQLiteException3)
        {
          zzd("Failed to read hits from persisted store", localSQLiteException3);
          zzaaq();
          try
          {
            zzcyf.setTransactionSuccessful();
            zzcyf.endTransaction();
            return false;
          }
          catch (SQLiteException localSQLiteException4)
          {
            zze("Failed to commit local dispatch transaction", localSQLiteException4);
            zzaaq();
            return false;
          }
          l2 = l1;
          if (!zzcyi.isConnected()) {
            continue;
          }
        }
        if (zzyy().zzabc()) {
          continue;
        }
        zzei("Service connected, sending hits to the service");
        l2 = l1;
        if (localList.isEmpty()) {
          continue;
        }
        localObject2 = (zzab)localList.get(0);
        if (zzcyi.zzb((zzab)localObject2)) {
          continue;
        }
      }
      finally
      {
        long l2;
        try
        {
          List localList;
          Object localObject2;
          zzcyf.setTransactionSuccessful();
          zzcyf.endTransaction();
          throw ((Throwable)localObject1);
        }
        catch (SQLiteException localSQLiteException11)
        {
          zze("Failed to commit local dispatch transaction", localSQLiteException11);
          zzaaq();
          return false;
        }
        l1 = l2;
        continue;
      }
      l2 = l1;
      if (zzcyg.zzadj())
      {
        localList = zzcyg.zzs(localList);
        localObject2 = localList.iterator();
        if (((Iterator)localObject2).hasNext())
        {
          l1 = Math.max(l1, ((Long)((Iterator)localObject2).next()).longValue());
          continue;
          l1 = Math.max(l1, ((zzab)localObject2).zzacy());
          localList.remove(localObject2);
          zzb("Hit sent do device AnalyticsService for delivery", localObject2);
          try
          {
            zzcyf.zzs(((zzab)localObject2).zzacy());
            localSQLiteException4.add(Long.valueOf(((zzab)localObject2).zzacy()));
          }
          catch (SQLiteException localSQLiteException5)
          {
            zze("Failed to remove hit that was send for delivery", localSQLiteException5);
            zzaaq();
            try
            {
              zzcyf.setTransactionSuccessful();
              zzcyf.endTransaction();
              return false;
            }
            catch (SQLiteException localSQLiteException6)
            {
              zze("Failed to commit local dispatch transaction", localSQLiteException6);
              zzaaq();
              return false;
            }
          }
        }
      }
      try
      {
        zzcyf.zzq(localList);
        localSQLiteException6.addAll(localList);
        l2 = l1;
        boolean bool = localSQLiteException6.isEmpty();
        if (bool) {
          try
          {
            zzcyf.setTransactionSuccessful();
            zzcyf.endTransaction();
            return false;
          }
          catch (SQLiteException localSQLiteException7)
          {
            zze("Failed to commit local dispatch transaction", localSQLiteException7);
            zzaaq();
            return false;
          }
        }
      }
      catch (SQLiteException localSQLiteException8)
      {
        zze("Failed to remove successfully uploaded hits", localSQLiteException8);
        zzaaq();
        try
        {
          zzcyf.setTransactionSuccessful();
          zzcyf.endTransaction();
          return false;
        }
        catch (SQLiteException localSQLiteException9)
        {
          zze("Failed to commit local dispatch transaction", localSQLiteException9);
          zzaaq();
          return false;
        }
        try
        {
          zzcyf.setTransactionSuccessful();
          zzcyf.endTransaction();
          l1 = l2;
        }
        catch (SQLiteException localSQLiteException10)
        {
          zze("Failed to commit local dispatch transaction", localSQLiteException10);
          zzaaq();
          return false;
        }
      }
    }
  }
  
  public void zzaal()
  {
    com.google.android.gms.analytics.zzi.zzwu();
    zzzg();
    zzej("Sync dispatching local hits");
    long l = zzcyn;
    if (!zzyy().zzabc()) {
      zzaai();
    }
    try
    {
      while (zzaak()) {}
      zzzb().zzadq();
      zzaam();
      if (zzcyn != l) {
        zzcyh.zzadi();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      zze("Sync local dispatch failed", localThrowable);
      zzaam();
    }
  }
  
  public void zzaam()
  {
    zzyu().zzwu();
    zzzg();
    if (!zzaan())
    {
      zzcyh.unregister();
      zzaaq();
      return;
    }
    if (zzcyf.isEmpty())
    {
      zzcyh.unregister();
      zzaaq();
      return;
    }
    if (!((Boolean)zzy.A.get()).booleanValue()) {
      zzcyh.zzadg();
    }
    for (boolean bool = zzcyh.isConnected(); bool; bool = true)
    {
      zzaap();
      return;
    }
    zzaaq();
    zzaao();
  }
  
  public long zzaat()
  {
    long l;
    if (zzcyj != Long.MIN_VALUE) {
      l = zzcyj;
    }
    do
    {
      return l;
      l = zzyy().zzabk();
    } while (!zzwe().zzact());
    return zzwe().zzaek() * 1000L;
  }
  
  public void zzaau()
  {
    zzzg();
    zzwu();
    zzcyo = true;
    zzcyi.disconnect();
    zzaam();
  }
  
  public void zzas(boolean paramBoolean)
  {
    zzaam();
  }
  
  public void zzb(zzw paramzzw)
  {
    zza(paramzzw, zzcyn);
  }
  
  protected void zzc(zzh paramzzh)
  {
    zzwu();
    zzb("Sending first hit to property", paramzzh.zzzp());
    if (zzzb().zzado().zzx(zzyy().zzach())) {}
    do
    {
      return;
      localObject = zzzb().zzadr();
    } while (TextUtils.isEmpty((CharSequence)localObject));
    Object localObject = zzao.zza(zzyx(), (String)localObject);
    zzb("Found relevant installation campaign", localObject);
    zza(paramzzh, (zzlz)localObject);
  }
  
  public void zzeq(String paramString)
  {
    com.google.android.gms.common.internal.zzab.zzhs(paramString);
    zzwu();
    zzyv();
    zzlz localzzlz = zzao.zza(zzyx(), paramString);
    if (localzzlz == null) {
      zzd("Parsing failed. Ignoring invalid campaign data", paramString);
    }
    for (;;)
    {
      return;
      String str = zzzb().zzadr();
      if (paramString.equals(str))
      {
        zzel("Ignoring duplicate install campaign");
        return;
      }
      if (!TextUtils.isEmpty(str))
      {
        zzd("Ignoring multiple install campaigns. original, new", str, paramString);
        return;
      }
      zzzb().zzev(paramString);
      if (zzzb().zzado().zzx(zzyy().zzach()))
      {
        zzd("Campaign received too late, ignoring", localzzlz);
        return;
      }
      zzb("Received installation campaign", localzzlz);
      paramString = zzcyf.zzt(0L).iterator();
      while (paramString.hasNext()) {
        zza((zzh)paramString.next(), localzzlz);
      }
    }
  }
  
  zzab zzf(zzab paramzzab)
  {
    if (!TextUtils.isEmpty(paramzzab.zzadd())) {}
    do
    {
      return paramzzab;
      localObject2 = zzzb().zzads().zzadv();
    } while (localObject2 == null);
    Object localObject1 = (Long)second;
    Object localObject2 = (String)first;
    localObject1 = String.valueOf(localObject1);
    localObject1 = String.valueOf(localObject1).length() + 1 + String.valueOf(localObject2).length() + (String)localObject1 + ":" + (String)localObject2;
    localObject2 = new HashMap(paramzzab.zzm());
    ((Map)localObject2).put("_m", localObject1);
    return zzab.zza(this, paramzzab, (Map)localObject2);
  }
  
  public void zzu(long paramLong)
  {
    com.google.android.gms.analytics.zzi.zzwu();
    zzzg();
    long l = paramLong;
    if (paramLong < 0L) {
      l = 0L;
    }
    zzcyj = l;
    zzaam();
  }
  
  protected void zzwv()
  {
    zzcyf.initialize();
    zzcyg.initialize();
    zzcyi.initialize();
  }
  
  public void zzyo()
  {
    com.google.android.gms.analytics.zzi.zzwu();
    zzzg();
    if (!zzyy().zzabc()) {
      zzei("Delete all hits from local store");
    }
    try
    {
      zzcyf.zzzw();
      zzcyf.zzzx();
      zzaam();
      zzaai();
      if (zzcyi.zzzs()) {
        zzei("Device service unavailable. Can't clear hits stored on the device service.");
      }
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;)
      {
        zzd("Failed to delete hits from store", localSQLiteException);
      }
    }
  }
  
  public void zzyr()
  {
    com.google.android.gms.analytics.zzi.zzwu();
    zzzg();
    zzei("Service disconnected");
  }
  
  void zzyt()
  {
    zzwu();
    zzcyn = zzyw().currentTimeMillis();
  }
  
  public long zzzz()
  {
    com.google.android.gms.analytics.zzi.zzwu();
    zzzg();
    try
    {
      long l = zzcyf.zzzz();
      return l;
    }
    catch (SQLiteException localSQLiteException)
    {
      zze("Failed to get min/max hit times from local store", localSQLiteException);
    }
    return 0L;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */