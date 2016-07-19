package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;

@zzir
public class zzem
  extends zzeh.zza
{
  private final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener zzbhp;
  
  public zzem(NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener paramOnCustomTemplateAdLoadedListener)
  {
    zzbhp = paramOnCustomTemplateAdLoadedListener;
  }
  
  public void zza(zzec paramzzec)
  {
    zzbhp.onCustomTemplateAdLoaded(new zzed(paramzzec));
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */