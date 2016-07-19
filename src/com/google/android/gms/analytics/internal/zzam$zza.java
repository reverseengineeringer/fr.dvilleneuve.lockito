package com.google.android.gms.analytics.internal;

import java.util.Map;

class zzam$zza
  extends zzc
  implements zzq.zza<zzan>
{
  private final zzan av = new zzan();
  
  public zzam$zza(zzf paramzzf)
  {
    super(paramzzf);
  }
  
  public zzan zzaea()
  {
    return av;
  }
  
  public void zzc(String paramString, int paramInt)
  {
    if ("ga_sessionTimeout".equals(paramString))
    {
      av.ax = paramInt;
      return;
    }
    zzd("int configuration name not recognized", paramString);
  }
  
  public void zzg(String paramString, boolean paramBoolean)
  {
    int j = 1;
    int k = 1;
    int i = 1;
    if ("ga_autoActivityTracking".equals(paramString))
    {
      paramString = av;
      if (paramBoolean) {}
      for (;;)
      {
        ay = i;
        return;
        i = 0;
      }
    }
    if ("ga_anonymizeIp".equals(paramString))
    {
      paramString = av;
      if (paramBoolean) {}
      for (i = j;; i = 0)
      {
        az = i;
        return;
      }
    }
    if ("ga_reportUncaughtExceptions".equals(paramString))
    {
      paramString = av;
      if (paramBoolean) {}
      for (i = k;; i = 0)
      {
        aA = i;
        return;
      }
    }
    zzd("bool configuration name not recognized", paramString);
  }
  
  public void zzp(String paramString1, String paramString2)
  {
    av.aB.put(paramString1, paramString2);
  }
  
  public void zzq(String paramString1, String paramString2)
  {
    if ("ga_trackingId".equals(paramString1))
    {
      av.zzcrv = paramString2;
      return;
    }
    if ("ga_sampleFrequency".equals(paramString1)) {
      try
      {
        av.aw = Double.parseDouble(paramString2);
        return;
      }
      catch (NumberFormatException paramString1)
      {
        zzc("Error parsing ga_sampleFrequency value", paramString2, paramString1);
        return;
      }
    }
    zzd("string configuration name not recognized", paramString1);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzam.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */