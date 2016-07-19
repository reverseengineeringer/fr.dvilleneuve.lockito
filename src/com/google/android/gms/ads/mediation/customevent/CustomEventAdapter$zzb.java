package com.google.android.gms.ads.mediation.customevent;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;

class CustomEventAdapter$zzb
  implements CustomEventInterstitialListener
{
  private final CustomEventAdapter zzcqx;
  private final MediationInterstitialListener zzfn;
  
  public CustomEventAdapter$zzb(CustomEventAdapter paramCustomEventAdapter1, CustomEventAdapter paramCustomEventAdapter2, MediationInterstitialListener paramMediationInterstitialListener)
  {
    zzcqx = paramCustomEventAdapter2;
    zzfn = paramMediationInterstitialListener;
  }
  
  public void onAdClicked()
  {
    zzb.zzcw("Custom event adapter called onAdClicked.");
    zzfn.onAdClicked(zzcqx);
  }
  
  public void onAdClosed()
  {
    zzb.zzcw("Custom event adapter called onAdClosed.");
    zzfn.onAdClosed(zzcqx);
  }
  
  public void onAdFailedToLoad(int paramInt)
  {
    zzb.zzcw("Custom event adapter called onFailedToReceiveAd.");
    zzfn.onAdFailedToLoad(zzcqx, paramInt);
  }
  
  public void onAdLeftApplication()
  {
    zzb.zzcw("Custom event adapter called onAdLeftApplication.");
    zzfn.onAdLeftApplication(zzcqx);
  }
  
  public void onAdLoaded()
  {
    zzb.zzcw("Custom event adapter called onReceivedAd.");
    zzfn.onAdLoaded(zzcqy);
  }
  
  public void onAdOpened()
  {
    zzb.zzcw("Custom event adapter called onAdOpened.");
    zzfn.onAdOpened(zzcqx);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.mediation.customevent.CustomEventAdapter.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */