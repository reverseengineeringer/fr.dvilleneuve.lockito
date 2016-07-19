package com.google.android.gms.internal;

import java.io.IOException;

class zzams$5
  extends zzank<Number>
{
  zzams$5(zzams paramzzams) {}
  
  public void zza(zzaor paramzzaor, Number paramNumber)
    throws IOException
  {
    if (paramNumber == null)
    {
      paramzzaor.r();
      return;
    }
    paramzzaor.zztb(paramNumber.toString());
  }
  
  public Number zzg(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    return Long.valueOf(paramzzaop.nextLong());
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzams.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */