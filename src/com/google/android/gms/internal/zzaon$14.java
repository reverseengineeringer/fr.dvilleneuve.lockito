package com.google.android.gms.internal;

import java.io.IOException;
import java.util.UUID;

final class zzaon$14
  extends zzank<UUID>
{
  public void zza(zzaor paramzzaor, UUID paramUUID)
    throws IOException
  {
    if (paramUUID == null) {}
    for (paramUUID = null;; paramUUID = paramUUID.toString())
    {
      paramzzaor.zztb(paramUUID);
      return;
    }
  }
  
  public UUID zzz(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    return UUID.fromString(paramzzaop.nextString());
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.14
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */