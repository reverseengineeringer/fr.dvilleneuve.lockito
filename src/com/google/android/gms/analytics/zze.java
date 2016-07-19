package com.google.android.gms.analytics;

import com.google.android.gms.common.internal.zzab;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zze
{
  private final com.google.android.gms.common.util.zze zzaoa;
  private final zzh zzcsy;
  private boolean zzcsz;
  private long zzcta;
  private long zzctb;
  private long zzctc;
  private long zzctd;
  private long zzcte;
  private boolean zzctf;
  private final Map<Class<? extends zzg>, zzg> zzctg;
  private final List<zzk> zzcth;
  
  zze(zze paramzze)
  {
    zzcsy = zzcsy;
    zzaoa = zzaoa;
    zzcta = zzcta;
    zzctb = zzctb;
    zzctc = zzctc;
    zzctd = zzctd;
    zzcte = zzcte;
    zzcth = new ArrayList(zzcth);
    zzctg = new HashMap(zzctg.size());
    paramzze = zzctg.entrySet().iterator();
    while (paramzze.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramzze.next();
      zzg localzzg = zzc((Class)localEntry.getKey());
      ((zzg)localEntry.getValue()).zzb(localzzg);
      zzctg.put((Class)localEntry.getKey(), localzzg);
    }
  }
  
  zze(zzh paramzzh, com.google.android.gms.common.util.zze paramzze)
  {
    zzab.zzaa(paramzzh);
    zzab.zzaa(paramzze);
    zzcsy = paramzzh;
    zzaoa = paramzze;
    zzctd = 1800000L;
    zzcte = 3024000000L;
    zzctg = new HashMap();
    zzcth = new ArrayList();
  }
  
  private static <T extends zzg> T zzc(Class<T> paramClass)
  {
    try
    {
      paramClass = (zzg)paramClass.newInstance();
      return paramClass;
    }
    catch (InstantiationException paramClass)
    {
      throw new IllegalArgumentException("dataType doesn't have default constructor", paramClass);
    }
    catch (IllegalAccessException paramClass)
    {
      throw new IllegalArgumentException("dataType default constructor is not accessible", paramClass);
    }
  }
  
  public <T extends zzg> T zza(Class<T> paramClass)
  {
    return (zzg)zzctg.get(paramClass);
  }
  
  public void zza(zzg paramzzg)
  {
    zzab.zzaa(paramzzg);
    Class localClass = paramzzg.getClass();
    if (localClass.getSuperclass() != zzg.class) {
      throw new IllegalArgumentException();
    }
    paramzzg.zzb(zzb(localClass));
  }
  
  public <T extends zzg> T zzb(Class<T> paramClass)
  {
    zzg localzzg2 = (zzg)zzctg.get(paramClass);
    zzg localzzg1 = localzzg2;
    if (localzzg2 == null)
    {
      localzzg1 = zzc(paramClass);
      zzctg.put(paramClass, localzzg1);
    }
    return localzzg1;
  }
  
  public void zzn(long paramLong)
  {
    zzctb = paramLong;
  }
  
  public zze zzwf()
  {
    return new zze(this);
  }
  
  public Collection<zzg> zzwg()
  {
    return zzctg.values();
  }
  
  public List<zzk> zzwh()
  {
    return zzcth;
  }
  
  public long zzwi()
  {
    return zzcta;
  }
  
  public void zzwj()
  {
    zzwn().zze(this);
  }
  
  public boolean zzwk()
  {
    return zzcsz;
  }
  
  void zzwl()
  {
    zzctc = zzaoa.elapsedRealtime();
    if (zzctb != 0L) {}
    for (zzcta = zzctb;; zzcta = zzaoa.currentTimeMillis())
    {
      zzcsz = true;
      return;
    }
  }
  
  zzh zzwm()
  {
    return zzcsy;
  }
  
  zzi zzwn()
  {
    return zzcsy.zzwn();
  }
  
  boolean zzwo()
  {
    return zzctf;
  }
  
  void zzwp()
  {
    zzctf = true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.zze
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */