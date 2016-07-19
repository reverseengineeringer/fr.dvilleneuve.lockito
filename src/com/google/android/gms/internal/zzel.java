package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;

@zzir
public class zzel
  extends zzeg.zza
{
  private final NativeCustomTemplateAd.OnCustomClickListener zzbho;
  
  public zzel(NativeCustomTemplateAd.OnCustomClickListener paramOnCustomClickListener)
  {
    zzbho = paramOnCustomClickListener;
  }
  
  public void zza(zzec paramzzec, String paramString)
  {
    zzbho.onCustomClick(new zzed(paramzzec), paramString);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */