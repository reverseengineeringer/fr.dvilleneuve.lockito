package com.google.android.gms.internal;

import java.io.IOException;
import java.net.InetAddress;

final class zzaon$13
  extends zzank<InetAddress>
{
  public void zza(zzaor paramzzaor, InetAddress paramInetAddress)
    throws IOException
  {
    if (paramInetAddress == null) {}
    for (paramInetAddress = null;; paramInetAddress = paramInetAddress.getHostAddress())
    {
      paramzzaor.zztb(paramInetAddress);
      return;
    }
  }
  
  public InetAddress zzy(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    return InetAddress.getByName(paramzzaop.nextString());
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.13
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */