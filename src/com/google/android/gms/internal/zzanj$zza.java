package com.google.android.gms.internal;

class zzanj$zza
  implements zzanl
{
  private final zzaoo<?> beD;
  private final boolean beE;
  private final Class<?> beF;
  private final zzang<?> bey;
  private final zzamx<?> bez;
  
  private zzanj$zza(Object paramObject, zzaoo<?> paramzzaoo, boolean paramBoolean, Class<?> paramClass)
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

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzanj.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */