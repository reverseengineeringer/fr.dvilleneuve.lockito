package com.google.android.gms.ads.mediation.customevent;

import android.view.View;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.mediation.MediationBannerListener;

final class CustomEventAdapter$zza
  implements CustomEventBannerListener
{
  private final CustomEventAdapter zzcqx;
  private final MediationBannerListener zzfm;
  
  public CustomEventAdapter$zza(CustomEventAdapter paramCustomEventAdapter, MediationBannerListener paramMediationBannerListener)
  {
    zzcqx = paramCustomEventAdapter;
    zzfm = paramMediationBannerListener;
  }
  
  public void onAdClicked()
  {
    zzb.zzcw("Custom event adapter called onAdClicked.");
    zzfm.onAdClicked(zzcqx);
  }
  
  public void onAdClosed()
  {
    zzb.zzcw("Custom event adapter called onAdClosed.");
    zzfm.onAdClosed(zzcqx);
  }
  
  public void onAdFailedToLoad(int paramInt)
  {
    zzb.zzcw("Custom event adapter called onAdFailedToLoad.");
    zzfm.onAdFailedToLoad(zzcqx, paramInt);
  }
  
  public void onAdLeftApplication()
  {
    zzb.zzcw("Custom event adapter called onAdLeftApplication.");
    zzfm.onAdLeftApplication(zzcqx);
  }
  
  public void onAdLoaded(View paramView)
  {
    zzb.zzcw("Custom event adapter called onAdLoaded.");
    CustomEventAdapter.zza(zzcqx, paramView);
    zzfm.onAdLoaded(zzcqx);
  }
  
  public void onAdOpened()
  {
    zzb.zzcw("Custom event adapter called onAdOpened.");
    zzfm.onAdOpened(zzcqx);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.mediation.customevent.CustomEventAdapter.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */