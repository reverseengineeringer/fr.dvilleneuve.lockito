package com.google.android.gms.internal;

import java.io.IOException;

final class zzanj<T>
  extends zzank<T>
{
  private final zzams beA;
  private final zzaoo<T> beB;
  private final zzanl beC;
  private zzank<T> bej;
  private final zzang<T> bey;
  private final zzamx<T> bez;
  
  private zzanj(zzang<T> paramzzang, zzamx<T> paramzzamx, zzams paramzzams, zzaoo<T> paramzzaoo, zzanl paramzzanl)
  {
    bey = paramzzang;
    bez = paramzzamx;
    beA = paramzzams;
    beB = paramzzaoo;
    beC = paramzzanl;
  }
  
  public static zzanl zza(zzaoo<?> paramzzaoo, Object paramObject)
  {
    return new zza(paramObject, paramzzaoo, false, null, null);
  }
  
  public static zzanl zzb(zzaoo<?> paramzzaoo, Object paramObject)
  {
    if (paramzzaoo.t() == paramzzaoo.s()) {}
    for (boolean bool = true;; bool = false) {
      return new zza(paramObject, paramzzaoo, bool, null, null);
    }
  }
  
  private zzank<T> zzczx()
  {
    zzank localzzank = bej;
    if (localzzank != null) {
      return localzzank;
    }
    localzzank = beA.zza(beC, beB);
    bej = localzzank;
    return localzzank;
  }
  
  public void zza(zzaor paramzzaor, T paramT)
    throws IOException
  {
    if (bey == null)
    {
      zzczx().zza(paramzzaor, paramT);
      return;
    }
    if (paramT == null)
    {
      paramzzaor.r();
      return;
    }
    zzanz.zzb(bey.zza(paramT, beB.t(), beA.beh), paramzzaor);
  }
  
  public T zzb(zzaop paramzzaop)
    throws IOException
  {
    if (bez == null) {
      return (T)zzczx().zzb(paramzzaop);
    }
    paramzzaop = zzanz.zzh(paramzzaop);
    if (paramzzaop.zzczp()) {
      return null;
    }
    try
    {
      paramzzaop = bez.zzb(paramzzaop, beB.t(), beA.beg);
      return paramzzaop;
    }
    catch (zzanc paramzzaop)
    {
      throw paramzzaop;
    }
    catch (Exception paramzzaop)
    {
      throw new zzanc(paramzzaop);
    }
  }
  
  private static class zza
    implements zzanl
  {
    private final zzaoo<?> beD;
    private final boolean beE;
    private final Class<?> beF;
    private final zzang<?> bey;
    private final zzamx<?> bez;
    
    private zza(Object paramObject, zzaoo<?> paramzzaoo, boolean paramBoolean, Class<?> paramClass)
    {
      zzang localzzang;
      if ((paramObject instanceof zzang))
      {
        localzzang = (zzang)paramObject;
        bey = localzzang;
        if (!(paramObject instanceof zzamx)) {
          break label85;
        }
        paramObject = (zzamx)paramObject;
        label35:
        bez = ((zzamx)paramObject);
        if ((bey == null) && (bez == null)) {
          break label90;
        }
      }
      label85:
      label90:
      for (boolean bool = true;; bool = false)
      {
        zzanq.zzbn(bool);
        beD = paramzzaoo;
        beE = paramBoolean;
        beF = paramClass;
        return;
        localzzang = null;
        break;
        paramObject = null;
        break label35;
      }
    }
    
    public <T> zzank<T> zza(zzams paramzzams, zzaoo<T> paramzzaoo)
    {
      boolean bool;
      if (beD != null) {
        if ((beD.equals(paramzzaoo)) || ((beE) && (beD.t() == paramzzaoo.s()))) {
          bool = true;
        }
      }
      while (bool)
      {
        return new zzanj(bey, bez, paramzzams, paramzzaoo, this, null);
        bool = false;
        continue;
        bool = beF.isAssignableFrom(paramzzaoo.s());
      }
      return null;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzanj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */