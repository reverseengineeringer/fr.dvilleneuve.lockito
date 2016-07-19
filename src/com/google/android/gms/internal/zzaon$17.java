package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;

final class zzaon$17
  extends zzank<Locale>
{
  public void zza(zzaor paramzzaor, Locale paramLocale)
    throws IOException
  {
    if (paramLocale == null) {}
    for (paramLocale = null;; paramLocale = paramLocale.toString())
    {
      paramzzaor.zztb(paramLocale);
      return;
    }
  }
  
  public Locale zzac(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    Object localObject = new StringTokenizer(paramzzaop.nextString(), "_");
    if (((StringTokenizer)localObject).hasMoreElements()) {}
    for (paramzzaop = ((StringTokenizer)localObject).nextToken();; paramzzaop = null)
    {
      if (((StringTokenizer)localObject).hasMoreElements()) {}
      for (String str = ((StringTokenizer)localObject).nextToken();; str = null)
      {
        if (((StringTokenizer)localObject).hasMoreElements()) {}
        for (localObject = ((StringTokenizer)localObject).nextToken();; localObject = null)
        {
          if ((str == null) && (localObject == null)) {
            return new Locale(paramzzaop);
          }
          if (localObject == null) {
            return new Locale(paramzzaop, str);
          }
          return new Locale(paramzzaop, str, (String)localObject);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.17
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */