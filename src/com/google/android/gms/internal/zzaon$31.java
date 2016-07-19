package com.google.android.gms.internal;

import java.io.IOException;

final class zzaon$31
  extends zzank<Number>
{
  public void zza(zzaor paramzzaor, Number paramNumber)
    throws IOException
  {
    paramzzaor.zza(paramNumber);
  }
  
  public Number zzg(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    try
    {
      long l = paramzzaop.nextLong();
      return Long.valueOf(l);
    }
    catch (NumberFormatException paramzzaop)
    {
      throw new zzanh(paramzzaop);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.31
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */