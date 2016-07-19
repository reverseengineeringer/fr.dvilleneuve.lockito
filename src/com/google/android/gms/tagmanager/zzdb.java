package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

class zzdb
  extends zzda
{
  private static final Object ayh = new Object();
  private static zzdb ayt;
  private Context ayi;
  private zzav ayj;
  private volatile zzat ayk;
  private int ayl = 1800000;
  private boolean aym = true;
  private boolean ayn = false;
  private boolean ayo = true;
  private zzaw ayp = new zzaw()
  {
    public void zzcg(boolean paramAnonymousBoolean)
    {
      zze(paramAnonymousBoolean, zzdb.zzd(zzdb.this));
    }
  };
  private zza ayq;
  private zzbs ayr;
  private boolean ays = false;
  private boolean connected = true;
  
  private boolean isPowerSaveMode()
  {
    return (ays) || (!connected) || (ayl <= 0);
  }
  
  private void zzaam()
  {
    if (isPowerSaveMode())
    {
      ayq.cancel();
      zzbn.v("PowerSaveMode initiated.");
      return;
    }
    ayq.zzv(ayl);
    zzbn.v("PowerSaveMode terminated.");
  }
  
  public static zzdb zzccy()
  {
    if (ayt == null) {
      ayt = new zzdb();
    }
    return ayt;
  }
  
  private void zzccz()
  {
    ayr = new zzbs(this);
    ayr.zzea(ayi);
  }
  
  private void zzcda()
  {
    ayq = new zzb(null);
    if (ayl > 0) {
      ayq.zzv(ayl);
    }
  }
  
  /* Error */
  public void dispatch()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 58	com/google/android/gms/tagmanager/zzdb:ayn	Z
    //   6: ifne +16 -> 22
    //   9: ldc -126
    //   11: invokestatic 90	com/google/android/gms/tagmanager/zzbn:v	(Ljava/lang/String;)V
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield 56	com/google/android/gms/tagmanager/zzdb:aym	Z
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: aload_0
    //   23: getfield 132	com/google/android/gms/tagmanager/zzdb:ayk	Lcom/google/android/gms/tagmanager/zzat;
    //   26: new 8	com/google/android/gms/tagmanager/zzdb$2
    //   29: dup
    //   30: aload_0
    //   31: invokespecial 133	com/google/android/gms/tagmanager/zzdb$2:<init>	(Lcom/google/android/gms/tagmanager/zzdb;)V
    //   34: invokeinterface 139 2 0
    //   39: goto -20 -> 19
    //   42: astore_1
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_1
    //   46: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	47	0	this	zzdb
    //   42	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	19	42	finally
    //   22	39	42	finally
  }
  
  /* Error */
  void zza(Context paramContext, zzat paramzzat)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 74	com/google/android/gms/tagmanager/zzdb:ayi	Landroid/content/Context;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnull +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: aload_1
    //   16: invokevirtual 146	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   19: putfield 74	com/google/android/gms/tagmanager/zzdb:ayi	Landroid/content/Context;
    //   22: aload_0
    //   23: getfield 132	com/google/android/gms/tagmanager/zzdb:ayk	Lcom/google/android/gms/tagmanager/zzat;
    //   26: ifnonnull -15 -> 11
    //   29: aload_0
    //   30: aload_2
    //   31: putfield 132	com/google/android/gms/tagmanager/zzdb:ayk	Lcom/google/android/gms/tagmanager/zzat;
    //   34: goto -23 -> 11
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	42	0	this	zzdb
    //   0	42	1	paramContext	Context
    //   0	42	2	paramzzat	zzat
    //   6	2	3	localContext	Context
    // Exception table:
    //   from	to	target	type
    //   2	7	37	finally
    //   14	34	37	finally
  }
  
  zzav zzcdb()
  {
    try
    {
      if (ayj != null) {
        break label50;
      }
      if (ayi == null) {
        throw new IllegalStateException("Cant get a store unless we have a context");
      }
    }
    finally {}
    ayj = new zzcf(ayp, ayi);
    label50:
    if (ayq == null) {
      zzcda();
    }
    ayn = true;
    if (aym)
    {
      dispatch();
      aym = false;
    }
    if ((ayr == null) && (ayo)) {
      zzccz();
    }
    zzav localzzav = ayj;
    return localzzav;
  }
  
  public void zzch(boolean paramBoolean)
  {
    try
    {
      zze(ays, paramBoolean);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  void zze(boolean paramBoolean1, boolean paramBoolean2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 77	com/google/android/gms/tagmanager/zzdb:isPowerSaveMode	()Z
    //   6: istore_3
    //   7: aload_0
    //   8: iload_1
    //   9: putfield 69	com/google/android/gms/tagmanager/zzdb:ays	Z
    //   12: aload_0
    //   13: iload_2
    //   14: putfield 60	com/google/android/gms/tagmanager/zzdb:connected	Z
    //   17: aload_0
    //   18: invokespecial 77	com/google/android/gms/tagmanager/zzdb:isPowerSaveMode	()Z
    //   21: istore_1
    //   22: iload_1
    //   23: iload_3
    //   24: if_icmpne +6 -> 30
    //   27: aload_0
    //   28: monitorexit
    //   29: return
    //   30: aload_0
    //   31: invokespecial 172	com/google/android/gms/tagmanager/zzdb:zzaam	()V
    //   34: goto -7 -> 27
    //   37: astore 4
    //   39: aload_0
    //   40: monitorexit
    //   41: aload 4
    //   43: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	44	0	this	zzdb
    //   0	44	1	paramBoolean1	boolean
    //   0	44	2	paramBoolean2	boolean
    //   6	19	3	bool	boolean
    //   37	5	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	22	37	finally
    //   30	34	37	finally
  }
  
  public void zzys()
  {
    try
    {
      if (!isPowerSaveMode()) {
        ayq.zzcdd();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static abstract interface zza
  {
    public abstract void cancel();
    
    public abstract void zzcdd();
    
    public abstract void zzv(long paramLong);
  }
  
  private class zzb
    implements zzdb.zza
  {
    private Handler handler = new Handler(zzdb.zza(zzdb.this).getMainLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramAnonymousMessage)
      {
        if ((1 == what) && (zzdb.zzcdc().equals(obj)))
        {
          dispatch();
          if (!zzdb.zzb(zzdb.this)) {
            zzv(zzdb.zzc(zzdb.this));
          }
        }
        return true;
      }
    });
    
    private zzb() {}
    
    private Message obtainMessage()
    {
      return handler.obtainMessage(1, zzdb.zzcdc());
    }
    
    public void cancel()
    {
      handler.removeMessages(1, zzdb.zzcdc());
    }
    
    public void zzcdd()
    {
      handler.removeMessages(1, zzdb.zzcdc());
      handler.sendMessage(obtainMessage());
    }
    
    public void zzv(long paramLong)
    {
      handler.removeMessages(1, zzdb.zzcdc());
      handler.sendMessageDelayed(obtainMessage(), paramLong);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzdb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */