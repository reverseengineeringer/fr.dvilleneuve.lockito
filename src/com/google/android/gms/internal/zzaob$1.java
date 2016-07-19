package com.google.android.gms.internal;

import java.lang.reflect.GenericArrayType;

final class zzaob$1
  implements zzanl
{
  public <T> zzank<T> zza(zzams paramzzams, zzaoo<T> paramzzaoo)
  {
    paramzzaoo = paramzzaoo.t();
    if ((!(paramzzaoo instanceof GenericArrayType)) && ((!(paramzzaoo instanceof Class)) || (!((Class)paramzzaoo).isArray()))) {
      return null;
    }
    paramzzaoo = zzanr.zzh(paramzzaoo);
    return new zzaob(paramzzams, paramzzams.zza(zzaoo.zzl(paramzzaoo)), zzanr.zzf(paramzzaoo));
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaob.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */