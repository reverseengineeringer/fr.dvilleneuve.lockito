package com.google.android.gms.ads.mediation.customevent;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;

class CustomEventAdapter$zzc
  implements CustomEventNativeListener
{
  private final CustomEventAdapter zzcqx;
  private final MediationNativeListener zzfo;
  
  public CustomEventAdapter$zzc(CustomEventAdapter paramCustomEventAdapter, MediationNativeListener paramMediationNativeListener)
  {
    zzcqx = paramCustomEventAdapter;
    zzfo = paramMediationNativeListener;
  }
  
  public void onAdClicked()
  {
    zzb.zzcw("Custom event adapter called onAdClicked.");
    zzfo.onAdClicked(zzcqx);
  }
  
  public void onAdClosed()
  {
    zzb.zzcw("Custom event adapter called onAdClosed.");
    zzfo.onAdClosed(zzcqx);
  }
  
  public void onAdFailedToLoad(int paramInt)
  {
    zzb.zzcw("Custom event adapter called onAdFailedToLoad.");
    zzfo.onAdFailedToLoad(zzcqx, paramInt);
  }
  
  public void onAdImpression()
  {
    zzb.zzcw("Custom event adapter called onAdImpression.");
    zzfo.onAdImpression(zzcqx);
  }
  
  public void onAdLeftApplication()
  {
    zzb.zzcw("Custom event adapter called onAdLeftApplication.");
    zzfo.onAdLeftApplication(zzcqx);
  }
  
  public void onAdLoaded(NativeAdMapper paramNativeAdMapper)
  {
    zzb.zzcw("Custom event adapter called onAdLoaded.");
    zzfo.onAdLoaded(zzcqx, paramNativeAdMapper);
  }
  
  public void onAdOpened()
  {
    zzb.zzcw("Custom event adapter called onAdOpened.");
    zzfo.onAdOpened(zzcqx);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.mediation.customevent.CustomEventAdapter.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */