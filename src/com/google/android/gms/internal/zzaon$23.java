package com.google.android.gms.internal;

import java.io.IOException;

final class zzaon$23
  extends zzank<Boolean>
{
  public void zza(zzaor paramzzaor, Boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean == null)
    {
      paramzzaor.r();
      return;
    }
    paramzzaor.zzcz(paramBoolean.booleanValue());
  }
  
  public Boolean zzae(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    if (paramzzaop.h() == zzaoq.bhE) {
      return Boolean.valueOf(Boolean.parseBoolean(paramzzaop.nextString()));
    }
    return Boolean.valueOf(paramzzaop.nextBoolean());
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.23
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */