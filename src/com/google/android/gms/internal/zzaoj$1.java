package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Field;

class zzaoj$1
  extends zzaoj.zzb
{
  final zzank<?> bfV = zzaoj.zza(bga, bfW, bfX, bfY);
  
  zzaoj$1(zzaoj paramzzaoj, String paramString, boolean paramBoolean1, boolean paramBoolean2, zzams paramzzams, Field paramField, zzaoo paramzzaoo, boolean paramBoolean3)
  {
    super(paramString, paramBoolean1, paramBoolean2);
  }
  
  void zza(zzaop paramzzaop, Object paramObject)
    throws IOException, IllegalAccessException
  {
    paramzzaop = bfV.zzb(paramzzaop);
    if ((paramzzaop != null) || (!bfZ)) {
      bfX.set(paramObject, paramzzaop);
    }
  }
  
  void zza(zzaor paramzzaor, Object paramObject)
    throws IOException, IllegalAccessException
  {
    paramObject = bfX.get(paramObject);
    new zzaom(bfW, bfV, bfY.t()).zza(paramzzaor, paramObject);
  }
  
  public boolean zzcq(Object paramObject)
    throws IOException, IllegalAccessException
  {
    if (!bgc) {}
    while (bfX.get(paramObject) == paramObject) {
      return false;
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaoj.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */