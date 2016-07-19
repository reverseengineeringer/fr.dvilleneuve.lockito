package com.google.android.gms.internal;

import java.io.IOException;

class zzams$zza<T>
  extends zzank<T>
{
  private zzank<T> bej;
  
  public void zza(zzank<T> paramzzank)
  {
    if (bej != null) {
      throw new AssertionError();
    }
    bej = paramzzank;
  }
  
  public void zza(zzaor paramzzaor, T paramT)
    throws IOException
  {
    if (bej == null) {
      throw new IllegalStateException();
    }
    bej.zza(paramzzaor, paramT);
  }
  
  public T zzb(zzaop paramzzaop)
    throws IOException
  {
    if (bej == null) {
      throw new IllegalStateException();
    }
    return (T)bej.zzb(paramzzaop);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzams.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */