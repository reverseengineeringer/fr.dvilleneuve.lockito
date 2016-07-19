package com.google.android.gms.internal;

import java.io.IOException;

class zzams$4
  extends zzank<Number>
{
  zzams$4(zzams paramzzams) {}
  
  public void zza(zzaor paramzzaor, Number paramNumber)
    throws IOException
  {
    if (paramNumber == null)
    {
      paramzzaor.r();
      return;
    }
    float f = paramNumber.floatValue();
    zzams.zza(bei, f);
    paramzzaor.zza(paramNumber);
  }
  
  public Float zzf(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    return Float.valueOf((float)paramzzaop.nextDouble());
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzams.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */