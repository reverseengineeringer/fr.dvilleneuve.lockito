package com.google.android.gms.internal;

import java.io.IOException;

final class zzaon$5
  extends zzank<String>
{
  public void zza(zzaor paramzzaor, String paramString)
    throws IOException
  {
    paramzzaor.zztb(paramString);
  }
  
  public String zzq(zzaop paramzzaop)
    throws IOException
  {
    zzaoq localzzaoq = paramzzaop.h();
    if (localzzaoq == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    if (localzzaoq == zzaoq.bhG) {
      return Boolean.toString(paramzzaop.nextBoolean());
    }
    return paramzzaop.nextString();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */