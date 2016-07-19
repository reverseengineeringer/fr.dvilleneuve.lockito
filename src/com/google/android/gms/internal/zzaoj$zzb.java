package com.google.android.gms.internal;

import java.io.IOException;

abstract class zzaoj$zzb
{
  final boolean bgc;
  final boolean bgd;
  final String name;
  
  protected zzaoj$zzb(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    name = paramString;
    bgc = paramBoolean1;
    bgd = paramBoolean2;
  }
  
  abstract void zza(zzaop paramzzaop, Object paramObject)
    throws IOException, IllegalAccessException;
  
  abstract void zza(zzaor paramzzaor, Object paramObject)
    throws IOException, IllegalAccessException;
  
  abstract boolean zzcq(Object paramObject)
    throws IOException, IllegalAccessException;
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaoj.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */