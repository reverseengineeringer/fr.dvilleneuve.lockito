package com.google.android.gms.internal;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

final class zzaon$11
  extends zzank<URI>
{
  public void zza(zzaor paramzzaor, URI paramURI)
    throws IOException
  {
    if (paramURI == null) {}
    for (paramURI = null;; paramURI = paramURI.toASCIIString())
    {
      paramzzaor.zztb(paramURI);
      return;
    }
  }
  
  public URI zzw(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH) {
      paramzzaop.nextNull();
    }
    for (;;)
    {
      return null;
      try
      {
        paramzzaop = paramzzaop.nextString();
        if ("null".equals(paramzzaop)) {
          continue;
        }
        paramzzaop = new URI(paramzzaop);
        return paramzzaop;
      }
      catch (URISyntaxException paramzzaop)
      {
        throw new zzamz(paramzzaop);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.11
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */