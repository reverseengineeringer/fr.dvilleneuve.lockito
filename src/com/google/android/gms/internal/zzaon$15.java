package com.google.android.gms.internal;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

final class zzaon$15
  implements zzanl
{
  public <T> zzank<T> zza(zzams paramzzams, zzaoo<T> paramzzaoo)
  {
    if (paramzzaoo.s() != Timestamp.class) {
      return null;
    }
    new zzank()
    {
      public void zza(zzaor paramAnonymouszzaor, Timestamp paramAnonymousTimestamp)
        throws IOException
      {
        bgZ.zza(paramAnonymouszzaor, paramAnonymousTimestamp);
      }
      
      public Timestamp zzaa(zzaop paramAnonymouszzaop)
        throws IOException
      {
        paramAnonymouszzaop = (Date)bgZ.zzb(paramAnonymouszzaop);
        if (paramAnonymouszzaop != null) {
          return new Timestamp(paramAnonymouszzaop.getTime());
        }
        return null;
      }
    };
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.15
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */