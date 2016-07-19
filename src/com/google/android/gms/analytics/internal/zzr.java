package com.google.android.gms.analytics.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzab;
import java.util.HashSet;
import java.util.Set;

public class zzr
{
  private final zzf zzcrq;
  private volatile Boolean zzczf;
  private String zzczg;
  private Set<Integer> zzczh;
  
  protected zzr(zzf paramzzf)
  {
    zzab.zzaa(paramzzf);
    zzcrq = paramzzf;
  }
  
  public boolean zzabc()
  {
    return false;
  }
  
  /* Error */
  public boolean zzabd()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 32	com/google/android/gms/analytics/internal/zzr:zzczf	Ljava/lang/Boolean;
    //   4: ifnonnull +119 -> 123
    //   7: aload_0
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 32	com/google/android/gms/analytics/internal/zzr:zzczf	Ljava/lang/Boolean;
    //   13: ifnonnull +108 -> 121
    //   16: aload_0
    //   17: getfield 26	com/google/android/gms/analytics/internal/zzr:zzcrq	Lcom/google/android/gms/analytics/internal/zzf;
    //   20: invokevirtual 38	com/google/android/gms/analytics/internal/zzf:getContext	()Landroid/content/Context;
    //   23: invokevirtual 44	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   26: astore_3
    //   27: invokestatic 50	com/google/android/gms/common/util/zzt:zzavv	()Ljava/lang/String;
    //   30: astore_2
    //   31: aload_3
    //   32: ifnull +30 -> 62
    //   35: aload_3
    //   36: getfield 55	android/content/pm/ApplicationInfo:processName	Ljava/lang/String;
    //   39: astore_3
    //   40: aload_3
    //   41: ifnull +90 -> 131
    //   44: aload_3
    //   45: aload_2
    //   46: invokevirtual 61	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   49: ifeq +82 -> 131
    //   52: iconst_1
    //   53: istore_1
    //   54: aload_0
    //   55: iload_1
    //   56: invokestatic 67	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   59: putfield 32	com/google/android/gms/analytics/internal/zzr:zzczf	Ljava/lang/Boolean;
    //   62: aload_0
    //   63: getfield 32	com/google/android/gms/analytics/internal/zzr:zzczf	Ljava/lang/Boolean;
    //   66: ifnull +13 -> 79
    //   69: aload_0
    //   70: getfield 32	com/google/android/gms/analytics/internal/zzr:zzczf	Ljava/lang/Boolean;
    //   73: invokevirtual 70	java/lang/Boolean:booleanValue	()Z
    //   76: ifne +19 -> 95
    //   79: ldc 72
    //   81: aload_2
    //   82: invokevirtual 61	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   85: ifeq +10 -> 95
    //   88: aload_0
    //   89: getstatic 75	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   92: putfield 32	com/google/android/gms/analytics/internal/zzr:zzczf	Ljava/lang/Boolean;
    //   95: aload_0
    //   96: getfield 32	com/google/android/gms/analytics/internal/zzr:zzczf	Ljava/lang/Boolean;
    //   99: ifnonnull +22 -> 121
    //   102: aload_0
    //   103: getstatic 75	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   106: putfield 32	com/google/android/gms/analytics/internal/zzr:zzczf	Ljava/lang/Boolean;
    //   109: aload_0
    //   110: getfield 26	com/google/android/gms/analytics/internal/zzr:zzcrq	Lcom/google/android/gms/analytics/internal/zzf;
    //   113: invokevirtual 79	com/google/android/gms/analytics/internal/zzf:zzyx	()Lcom/google/android/gms/analytics/internal/zzaf;
    //   116: ldc 81
    //   118: invokevirtual 87	com/google/android/gms/analytics/internal/zzaf:zzem	(Ljava/lang/String;)V
    //   121: aload_0
    //   122: monitorexit
    //   123: aload_0
    //   124: getfield 32	com/google/android/gms/analytics/internal/zzr:zzczf	Ljava/lang/Boolean;
    //   127: invokevirtual 70	java/lang/Boolean:booleanValue	()Z
    //   130: ireturn
    //   131: iconst_0
    //   132: istore_1
    //   133: goto -79 -> 54
    //   136: astore_2
    //   137: aload_0
    //   138: monitorexit
    //   139: aload_2
    //   140: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	141	0	this	zzr
    //   53	80	1	bool	boolean
    //   30	52	2	str	String
    //   136	4	2	localObject1	Object
    //   26	19	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   9	31	136	finally
    //   35	40	136	finally
    //   44	52	136	finally
    //   54	62	136	finally
    //   62	79	136	finally
    //   79	95	136	finally
    //   95	121	136	finally
    //   121	123	136	finally
    //   137	139	136	finally
  }
  
  public boolean zzabe()
  {
    return ((Boolean)zzy.zzczq.get()).booleanValue();
  }
  
  public int zzabf()
  {
    return ((Integer)zzy.j.get()).intValue();
  }
  
  public int zzabg()
  {
    return ((Integer)zzy.n.get()).intValue();
  }
  
  public int zzabh()
  {
    return ((Integer)zzy.o.get()).intValue();
  }
  
  public int zzabi()
  {
    return ((Integer)zzy.p.get()).intValue();
  }
  
  public long zzabj()
  {
    return ((Long)zzy.zzczy.get()).longValue();
  }
  
  public long zzabk()
  {
    return ((Long)zzy.zzczx.get()).longValue();
  }
  
  public long zzabl()
  {
    return ((Long)zzy.b.get()).longValue();
  }
  
  public long zzabm()
  {
    return ((Long)zzy.c.get()).longValue();
  }
  
  public int zzabn()
  {
    return ((Integer)zzy.d.get()).intValue();
  }
  
  public int zzabo()
  {
    return ((Integer)zzy.e.get()).intValue();
  }
  
  public long zzabp()
  {
    return ((Integer)zzy.r.get()).intValue();
  }
  
  public String zzabq()
  {
    return (String)zzy.g.get();
  }
  
  public String zzabr()
  {
    return (String)zzy.f.get();
  }
  
  public String zzabs()
  {
    return (String)zzy.h.get();
  }
  
  public String zzabt()
  {
    return (String)zzy.i.get();
  }
  
  public zzm zzabu()
  {
    return zzm.zzer((String)zzy.k.get());
  }
  
  public zzo zzabv()
  {
    return zzo.zzes((String)zzy.l.get());
  }
  
  public Set<Integer> zzabw()
  {
    String str1 = (String)zzy.q.get();
    String[] arrayOfString;
    HashSet localHashSet;
    int j;
    int i;
    if ((zzczh == null) || (zzczg == null) || (!zzczg.equals(str1)))
    {
      arrayOfString = TextUtils.split(str1, ",");
      localHashSet = new HashSet();
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      String str2;
      if (i < j) {
        str2 = arrayOfString[i];
      }
      try
      {
        localHashSet.add(Integer.valueOf(Integer.parseInt(str2)));
        i += 1;
        continue;
        zzczg = str1;
        zzczh = localHashSet;
        return zzczh;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        for (;;) {}
      }
    }
  }
  
  public long zzabx()
  {
    return ((Long)zzy.B.get()).longValue();
  }
  
  public long zzaby()
  {
    return ((Long)zzy.C.get()).longValue();
  }
  
  public long zzabz()
  {
    return ((Long)zzy.F.get()).longValue();
  }
  
  public int zzaca()
  {
    return ((Integer)zzy.zzczu.get()).intValue();
  }
  
  public int zzacb()
  {
    return ((Integer)zzy.zzczw.get()).intValue();
  }
  
  public String zzacc()
  {
    return "google_analytics_v4.db";
  }
  
  public String zzacd()
  {
    return "google_analytics2_v4.db";
  }
  
  public long zzace()
  {
    return 86400000L;
  }
  
  public int zzacf()
  {
    return ((Integer)zzy.t.get()).intValue();
  }
  
  public int zzacg()
  {
    return ((Integer)zzy.u.get()).intValue();
  }
  
  public long zzach()
  {
    return ((Long)zzy.v.get()).longValue();
  }
  
  public long zzaci()
  {
    return ((Long)zzy.G.get()).longValue();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */