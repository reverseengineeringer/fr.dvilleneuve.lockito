package com.google.android.gms.internal;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

class zzaon$15$1
  extends zzank<Timestamp>
{
  zzaon$15$1(zzaon.15 param15, zzank paramzzank) {}
  
  public void zza(zzaor paramzzaor, Timestamp paramTimestamp)
    throws IOException
  {
    bgZ.zza(paramzzaor, paramTimestamp);
  }
  
  public Timestamp zzaa(zzaop paramzzaop)
    throws IOException
  {
    paramzzaop = (Date)bgZ.zzb(paramzzaop);
    if (paramzzaop != null) {
      return new Timestamp(paramzzaop.getTime());
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.15.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */