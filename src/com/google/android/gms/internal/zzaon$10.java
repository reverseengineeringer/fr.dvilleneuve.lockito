package com.google.android.gms.internal;

import java.io.IOException;
import java.net.URL;

final class zzaon$10
  extends zzank<URL>
{
  public void zza(zzaor paramzzaor, URL paramURL)
    throws IOException
  {
    if (paramURL == null) {}
    for (paramURL = null;; paramURL = paramURL.toExternalForm())
    {
      paramzzaor.zztb(paramURL);
      return;
    }
  }
  
  public URL zzv(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH) {
      paramzzaop.nextNull();
    }
    do
    {
      return null;
      paramzzaop = paramzzaop.nextString();
    } while ("null".equals(paramzzaop));
    return new URL(paramzzaop);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.10
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */