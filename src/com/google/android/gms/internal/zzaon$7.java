package com.google.android.gms.internal;

import java.io.IOException;
import java.math.BigInteger;

final class zzaon$7
  extends zzank<BigInteger>
{
  public void zza(zzaor paramzzaor, BigInteger paramBigInteger)
    throws IOException
  {
    paramzzaor.zza(paramBigInteger);
  }
  
  public BigInteger zzs(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    try
    {
      paramzzaop = new BigInteger(paramzzaop.nextString());
      return paramzzaop;
    }
    catch (NumberFormatException paramzzaop)
    {
      throw new zzanh(paramzzaop);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */