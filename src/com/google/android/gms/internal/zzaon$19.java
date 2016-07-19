package com.google.android.gms.internal;

final class zzaon$19
  implements zzanl
{
  public <T> zzank<T> zza(zzams paramzzams, zzaoo<T> paramzzaoo)
  {
    paramzzaoo = paramzzaoo.s();
    if ((!Enum.class.isAssignableFrom(paramzzaoo)) || (paramzzaoo == Enum.class)) {
      return null;
    }
    paramzzams = paramzzaoo;
    if (!paramzzaoo.isEnum()) {
      paramzzams = paramzzaoo.getSuperclass();
    }
    return new zzaon.zza(paramzzams);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.19
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */