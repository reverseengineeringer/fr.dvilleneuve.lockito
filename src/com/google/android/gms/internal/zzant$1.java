package com.google.android.gms.internal;

import java.io.IOException;

class zzant$1
  extends zzank<T>
{
  private zzank<T> bej;
  
  zzant$1(zzant paramzzant, boolean paramBoolean1, boolean paramBoolean2, zzams paramzzams, zzaoo paramzzaoo) {}
  
  private zzank<T> zzczx()
  {
    zzank localzzank = bej;
    if (localzzank != null) {
      return localzzank;
    }
    localzzank = bfc.zza(bfe, bfd);
    bej = localzzank;
    return localzzank;
  }
  
  public void zza(zzaor paramzzaor, T paramT)
    throws IOException
  {
    if (bfb)
    {
      paramzzaor.r();
      return;
    }
    zzczx().zza(paramzzaor, paramT);
  }
  
  public T zzb(zzaop paramzzaop)
    throws IOException
  {
    if (bfa)
    {
      paramzzaop.skipValue();
      return null;
    }
    return (T)zzczx().zzb(paramzzaop);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzant.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */