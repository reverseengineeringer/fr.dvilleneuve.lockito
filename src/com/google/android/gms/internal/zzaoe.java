package com.google.android.gms.internal;

public final class zzaoe
  implements zzanl
{
  private final zzans beb;
  
  public zzaoe(zzans paramzzans)
  {
    beb = paramzzans;
  }
  
  static zzank<?> zza(zzans paramzzans, zzams paramzzams, zzaoo<?> paramzzaoo, zzanm paramzzanm)
  {
    paramzzanm = paramzzanm.value();
    if (zzank.class.isAssignableFrom(paramzzanm)) {
      return (zzank)paramzzans.zzb(zzaoo.zzr(paramzzanm)).a();
    }
    if (zzanl.class.isAssignableFrom(paramzzanm)) {
      return ((zzanl)paramzzans.zzb(zzaoo.zzr(paramzzanm)).a()).zza(paramzzams, paramzzaoo);
    }
    throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter or TypeAdapterFactory reference.");
  }
  
  public <T> zzank<T> zza(zzams paramzzams, zzaoo<T> paramzzaoo)
  {
    zzanm localzzanm = (zzanm)paramzzaoo.s().getAnnotation(zzanm.class);
    if (localzzanm == null) {
      return null;
    }
    return zza(beb, paramzzams, paramzzaoo, localzzanm);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaoe
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */