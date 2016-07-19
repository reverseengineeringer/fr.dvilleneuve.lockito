package com.google.android.gms.internal;

import java.io.IOException;

class zzams$3
  extends zzank<Number>
{
  zzams$3(zzams paramzzams) {}
  
  public void zza(zzaor paramzzaor, Number paramNumber)
    throws IOException
  {
    if (paramNumber == null)
    {
      paramzzaor.r();
      return;
    }
    double d = paramNumber.doubleValue();
    zzams.zza(bei, d);
    paramzzaor.zza(paramNumber);
  }
  
  public Double zze(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    return Double.valueOf(paramzzaop.nextDouble());
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzams.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */