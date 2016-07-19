package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.internal.zzadx.zza;
import com.google.android.gms.internal.zzady;
import com.google.android.gms.internal.zzadz.zzc;
import com.google.android.gms.internal.zzah.zzf;
import com.google.android.gms.internal.zzah.zzj;
import com.google.android.gms.internal.zzpt;

public class zzp
  extends zzpt<ContainerHolder>
{
  private final String auZ;
  private long ave;
  private final TagManager avl;
  private final zzd avo;
  private final zzck avp;
  private final int avq;
  private zzf avr;
  private zzady avs;
  private volatile zzo avt;
  private volatile boolean avu;
  private zzah.zzj avv;
  private String avw;
  private zze avx;
  private zza avy;
  private final Context mContext;
  private final Looper zzahv;
  private final zze zzaoa;
  
  zzp(Context paramContext, TagManager paramTagManager, Looper paramLooper, String paramString, int paramInt, zzf paramzzf, zze paramzze, zzady paramzzady, zze paramzze1, zzck paramzzck) {}
  
  public zzp(Context paramContext, TagManager paramTagManager, Looper paramLooper, String paramString, int paramInt, zzs paramzzs)
  {
    this(paramContext, paramTagManager, paramLooper, paramString, paramInt, new zzcu(paramContext, paramString), new zzct(paramContext, paramString, paramzzs), new zzady(paramContext), zzh.zzavi(), new zzbl(30, 900000L, 5000L, "refreshing", zzh.zzavi()));
    avs.zzps(paramzzs.zzcas());
  }
  
  private void zza(zzah.zzj paramzzj)
  {
    try
    {
      if (avr != null)
      {
        zzadx.zza localzza = new zzadx.zza();
        aDp = ave;
        zzwq = new zzah.zzf();
        aDq = paramzzj;
        avr.zzb(localzza);
      }
      return;
    }
    finally
    {
      paramzzj = finally;
      throw paramzzj;
    }
  }
  
  /* Error */
  private void zza(zzah.zzj paramzzj, long paramLong, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iload 4
    //   4: ifeq +9 -> 13
    //   7: aload_0
    //   8: getfield 193	com/google/android/gms/tagmanager/zzp:avu	Z
    //   11: istore 4
    //   13: aload_0
    //   14: invokevirtual 196	com/google/android/gms/tagmanager/zzp:isReady	()Z
    //   17: ifeq +10 -> 27
    //   20: aload_0
    //   21: getfield 198	com/google/android/gms/tagmanager/zzp:avt	Lcom/google/android/gms/tagmanager/zzo;
    //   24: ifnonnull +3 -> 27
    //   27: aload_0
    //   28: aload_1
    //   29: putfield 101	com/google/android/gms/tagmanager/zzp:avv	Lcom/google/android/gms/internal/zzah$zzj;
    //   32: aload_0
    //   33: lload_2
    //   34: putfield 174	com/google/android/gms/tagmanager/zzp:ave	J
    //   37: aload_0
    //   38: lconst_0
    //   39: ldc2_w 199
    //   42: aload_0
    //   43: getfield 174	com/google/android/gms/tagmanager/zzp:ave	J
    //   46: ldc2_w 199
    //   49: ladd
    //   50: aload_0
    //   51: getfield 103	com/google/android/gms/tagmanager/zzp:zzaoa	Lcom/google/android/gms/common/util/zze;
    //   54: invokeinterface 206 1 0
    //   59: lsub
    //   60: invokestatic 212	java/lang/Math:min	(JJ)J
    //   63: invokestatic 215	java/lang/Math:max	(JJ)J
    //   66: invokespecial 219	com/google/android/gms/tagmanager/zzp:zzbq	(J)V
    //   69: new 221	com/google/android/gms/tagmanager/Container
    //   72: dup
    //   73: aload_0
    //   74: getfield 75	com/google/android/gms/tagmanager/zzp:mContext	Landroid/content/Context;
    //   77: aload_0
    //   78: getfield 77	com/google/android/gms/tagmanager/zzp:avl	Lcom/google/android/gms/tagmanager/TagManager;
    //   81: invokevirtual 227	com/google/android/gms/tagmanager/TagManager:getDataLayer	()Lcom/google/android/gms/tagmanager/DataLayer;
    //   84: aload_0
    //   85: getfield 81	com/google/android/gms/tagmanager/zzp:auZ	Ljava/lang/String;
    //   88: lload_2
    //   89: aload_1
    //   90: invokespecial 230	com/google/android/gms/tagmanager/Container:<init>	(Landroid/content/Context;Lcom/google/android/gms/tagmanager/DataLayer;Ljava/lang/String;JLcom/google/android/gms/internal/zzah$zzj;)V
    //   93: astore_1
    //   94: aload_0
    //   95: getfield 198	com/google/android/gms/tagmanager/zzp:avt	Lcom/google/android/gms/tagmanager/zzo;
    //   98: ifnonnull +58 -> 156
    //   101: aload_0
    //   102: new 232	com/google/android/gms/tagmanager/zzo
    //   105: dup
    //   106: aload_0
    //   107: getfield 77	com/google/android/gms/tagmanager/zzp:avl	Lcom/google/android/gms/tagmanager/TagManager;
    //   110: aload_0
    //   111: getfield 79	com/google/android/gms/tagmanager/zzp:zzahv	Landroid/os/Looper;
    //   114: aload_1
    //   115: aload_0
    //   116: getfield 94	com/google/android/gms/tagmanager/zzp:avo	Lcom/google/android/gms/tagmanager/zzp$zzd;
    //   119: invokespecial 235	com/google/android/gms/tagmanager/zzo:<init>	(Lcom/google/android/gms/tagmanager/TagManager;Landroid/os/Looper;Lcom/google/android/gms/tagmanager/Container;Lcom/google/android/gms/tagmanager/zzo$zza;)V
    //   122: putfield 198	com/google/android/gms/tagmanager/zzp:avt	Lcom/google/android/gms/tagmanager/zzo;
    //   125: aload_0
    //   126: invokevirtual 196	com/google/android/gms/tagmanager/zzp:isReady	()Z
    //   129: ifne +24 -> 153
    //   132: aload_0
    //   133: getfield 237	com/google/android/gms/tagmanager/zzp:avy	Lcom/google/android/gms/tagmanager/zzp$zza;
    //   136: aload_1
    //   137: invokeinterface 240 2 0
    //   142: ifeq +11 -> 153
    //   145: aload_0
    //   146: aload_0
    //   147: getfield 198	com/google/android/gms/tagmanager/zzp:avt	Lcom/google/android/gms/tagmanager/zzo;
    //   150: invokevirtual 243	com/google/android/gms/tagmanager/zzp:zzc	(Lcom/google/android/gms/common/api/Result;)V
    //   153: aload_0
    //   154: monitorexit
    //   155: return
    //   156: aload_0
    //   157: getfield 198	com/google/android/gms/tagmanager/zzp:avt	Lcom/google/android/gms/tagmanager/zzo;
    //   160: aload_1
    //   161: invokevirtual 246	com/google/android/gms/tagmanager/zzo:zza	(Lcom/google/android/gms/tagmanager/Container;)V
    //   164: goto -39 -> 125
    //   167: astore_1
    //   168: aload_0
    //   169: monitorexit
    //   170: aload_1
    //   171: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	172	0	this	zzp
    //   0	172	1	paramzzj	zzah.zzj
    //   0	172	2	paramLong	long
    //   0	172	4	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   7	13	167	finally
    //   13	27	167	finally
    //   27	125	167	finally
    //   125	153	167	finally
    //   156	164	167	finally
  }
  
  /* Error */
  private void zzbq(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 87	com/google/android/gms/tagmanager/zzp:avx	Lcom/google/android/gms/tagmanager/zzp$zze;
    //   6: ifnonnull +12 -> 18
    //   9: ldc_w 256
    //   12: invokestatic 261	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: aload_0
    //   19: getfield 87	com/google/android/gms/tagmanager/zzp:avx	Lcom/google/android/gms/tagmanager/zzp$zze;
    //   22: lload_1
    //   23: aload_0
    //   24: getfield 101	com/google/android/gms/tagmanager/zzp:avv	Lcom/google/android/gms/internal/zzah$zzj;
    //   27: getfield 264	com/google/android/gms/internal/zzah$zzj:zzwr	Ljava/lang/String;
    //   30: invokeinterface 267 4 0
    //   35: goto -20 -> 15
    //   38: astore_3
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_3
    //   42: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	this	zzp
    //   0	43	1	paramLong	long
    //   38	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	15	38	finally
    //   18	35	38	finally
  }
  
  private boolean zzcap()
  {
    zzci localzzci = zzci.zzcce();
    return ((localzzci.zzccf() == zzci.zza.axo) || (localzzci.zzccf() == zzci.zza.axp)) && (auZ.equals(localzzci.getContainerId()));
  }
  
  private void zzcf(final boolean paramBoolean)
  {
    avr.zza(new zzb(null));
    avx.zza(new zzc(null));
    zzadz.zzc localzzc = avr.zzxw(avq);
    if (localzzc != null) {
      avt = new zzo(avl, zzahv, new Container(mContext, avl.getDataLayer(), auZ, 0L, localzzc), avo);
    }
    avy = new zza()
    {
      public boolean zzb(Container paramAnonymousContainer)
      {
        if (paramBoolean) {
          if (paramAnonymousContainer.getLastRefreshTime() + 43200000L < zzp.zzc(zzp.this).currentTimeMillis()) {}
        }
        while (!paramAnonymousContainer.isDefault())
        {
          return true;
          return false;
        }
        return false;
      }
    };
    if (zzcap())
    {
      avx.zzf(0L, "");
      return;
    }
    avr.zzcar();
  }
  
  String zzcaj()
  {
    try
    {
      String str = avw;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void zzcam()
  {
    Object localObject = avr.zzxw(avq);
    if (localObject != null)
    {
      localObject = new Container(mContext, avl.getDataLayer(), auZ, 0L, (zzadz.zzc)localObject);
      zzc(new zzo(avl, zzahv, (Container)localObject, new zzo.zza()
      {
        public String zzcaj()
        {
          return zzp.this.zzcaj();
        }
        
        public void zzcal()
        {
          zzbn.zzcy("Refresh ignored: container loaded as default only.");
        }
        
        public void zzns(String paramAnonymousString)
        {
          zzp.this.zzns(paramAnonymousString);
        }
      }));
    }
    for (;;)
    {
      avx = null;
      avr = null;
      return;
      zzbn.e("Default was requested, but no default container was found");
      zzc(zzec(new Status(10, "Default was requested, but no default container was found", null)));
    }
  }
  
  public void zzcan()
  {
    zzcf(false);
  }
  
  public void zzcao()
  {
    zzcf(true);
  }
  
  protected ContainerHolder zzec(Status paramStatus)
  {
    if (avt != null) {
      return avt;
    }
    if (paramStatus == Status.sj) {
      zzbn.e("timer expired: setting result to failure");
    }
    return new zzo(paramStatus);
  }
  
  void zzns(String paramString)
  {
    try
    {
      avw = paramString;
      if (avx != null) {
        avx.zznv(paramString);
      }
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
    public abstract boolean zzb(Container paramContainer);
  }
  
  private class zzb
    implements zzbm<zzadx.zza>
  {
    private zzb() {}
    
    public void zza(zzadx.zza paramzza)
    {
      zzah.zzj localzzj;
      if (aDq != null) {
        localzzj = aDq;
      }
      for (;;)
      {
        zzp.zza(zzp.this, localzzj, aDp, true);
        return;
        zzah.zzf localzzf = zzwq;
        localzzj = new zzah.zzj();
        zzwq = localzzf;
        zzwp = null;
        zzwr = version;
      }
    }
    
    public void zza(zzbm.zza paramzza)
    {
      if (!zzp.zzd(zzp.this)) {
        zzp.zza(zzp.this, 0L);
      }
    }
    
    public void zzcaq() {}
  }
  
  private class zzc
    implements zzbm<zzah.zzj>
  {
    private zzc() {}
    
    public void zza(zzbm.zza arg1)
    {
      synchronized (zzp.this)
      {
        if (!isReady())
        {
          if (zzp.zzb(zzp.this) != null) {
            zzc(zzp.zzb(zzp.this));
          }
        }
        else
        {
          zzp.zza(zzp.this, 3600000L);
          return;
        }
        zzc(zzec(Status.sj));
      }
    }
    
    public void zzb(zzah.zzj paramzzj)
    {
      synchronized (zzp.this)
      {
        if (zzwq == null)
        {
          if (zzezzwq == null)
          {
            zzbn.e("Current resource is null; network resource is also null");
            zzp.zza(zzp.this, 3600000L);
            return;
          }
          zzwq = zzezzwq;
        }
        zzp.zza(zzp.this, paramzzj, zzp.zzc(zzp.this).currentTimeMillis(), false);
        long l = zzp.zzf(zzp.this);
        zzbn.v(58 + "setting refresh time to current time: " + l);
        if (!zzp.zzg(zzp.this)) {
          zzp.zza(zzp.this, paramzzj);
        }
        return;
      }
    }
    
    public void zzcaq() {}
  }
  
  private class zzd
    implements zzo.zza
  {
    private zzd() {}
    
    public String zzcaj()
    {
      return zzp.this.zzcaj();
    }
    
    public void zzcal()
    {
      if (zzp.zza(zzp.this).zzade()) {
        zzp.zza(zzp.this, 0L);
      }
    }
    
    public void zzns(String paramString)
    {
      zzp.this.zzns(paramString);
    }
  }
  
  static abstract interface zze
    extends Releasable
  {
    public abstract void zza(zzbm<zzah.zzj> paramzzbm);
    
    public abstract void zzf(long paramLong, String paramString);
    
    public abstract void zznv(String paramString);
  }
  
  static abstract interface zzf
    extends Releasable
  {
    public abstract void zza(zzbm<zzadx.zza> paramzzbm);
    
    public abstract void zzb(zzadx.zza paramzza);
    
    public abstract void zzcar();
    
    public abstract zzadz.zzc zzxw(int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */