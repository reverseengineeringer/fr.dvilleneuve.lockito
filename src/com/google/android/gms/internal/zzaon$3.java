package com.google.android.gms.internal;

import java.io.IOException;

final class zzaon$3
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
    zzaoq localzzaoq = paramzzaop.h();
    switch (zzaon.26.bfU[localzzaoq.ordinal()])
    {
    case 2: 
    case 3: 
    default: 
      paramzzaop = String.valueOf(localzzaoq);
      throw new zzanh(String.valueOf(paramzzaop).length() + 23 + "Expecting number, got: " + paramzzaop);
    case 4: 
      paramzzaop.nextNull();
      return null;
    }
    return new zzanv(paramzzaop.nextString());
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */