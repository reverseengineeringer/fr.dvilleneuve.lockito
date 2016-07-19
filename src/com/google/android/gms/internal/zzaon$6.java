package com.google.android.gms.internal;

import java.io.IOException;
import java.math.BigDecimal;

final class zzaon$6
  extends zzank<BigDecimal>
{
  public void zza(zzaor paramzzaor, BigDecimal paramBigDecimal)
    throws IOException
  {
    paramzzaor.zza(paramBigDecimal);
  }
  
  public BigDecimal zzr(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    try
    {
      paramzzaop = new BigDecimal(paramzzaop.nextString());
      return paramzzaop;
    }
    catch (NumberFormatException paramzzaop)
    {
      throw new zzanh(paramzzaop);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */