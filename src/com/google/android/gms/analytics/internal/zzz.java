package com.google.android.gms.analytics.internal;

public class zzz
  extends zzq<zzaa>
{
  public zzz(zzf paramzzf)
  {
    super(paramzzf, new zza(paramzzf));
  }
  
  private static class zza
    implements zzq.zza<zzaa>
  {
    private final zzaa K;
    private final zzf zzcws;
    
    public zza(zzf paramzzf)
    {
      zzcws = paramzzf;
      K = new zzaa();
    }
    
    public zzaa zzaco()
    {
      return K;
    }
    
    public void zzc(String paramString, int paramInt)
    {
      if ("ga_dispatchPeriod".equals(paramString))
      {
        K.M = paramInt;
        return;
      }
      zzcws.zzyx().zzd("Int xml configuration name not recognized", paramString);
    }
    
    public void zzg(String paramString, boolean paramBoolean)
    {
      if ("ga_dryRun".equals(paramString))
      {
        paramString = K;
        if (paramBoolean) {}
        for (int i = 1;; i = 0)
        {
          N = i;
          return;
        }
      }
      zzcws.zzyx().zzd("Bool xml configuration name not recognized", paramString);
    }
    
    public void zzp(String paramString1, String paramString2) {}
    
    public void zzq(String paramString1, String paramString2)
    {
      if ("ga_appName".equals(paramString1))
      {
        K.zzcup = paramString2;
        return;
      }
      if ("ga_appVersion".equals(paramString1))
      {
        K.zzcuq = paramString2;
        return;
      }
      if ("ga_logLevel".equals(paramString1))
      {
        K.L = paramString2;
        return;
      }
      zzcws.zzyx().zzd("String xml configuration name not recognized", paramString1);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */