package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzank<T>
{
  public abstract void zza(zzaor paramzzaor, T paramT)
    throws IOException;
  
  public abstract T zzb(zzaop paramzzaop)
    throws IOException;
  
  public final zzamy zzcl(T paramT)
  {
    try
    {
      zzaog localzzaog = new zzaog();
      zza(localzzaog, paramT);
      paramT = localzzaog.l();
      return paramT;
    }
    catch (IOException paramT)
    {
      throw new zzamz(paramT);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzank
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */