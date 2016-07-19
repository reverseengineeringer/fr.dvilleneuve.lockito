package com.google.ads.mediation.customevent;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.internal.util.client.zzb;

class CustomEventAdapter$zzb
  implements CustomEventInterstitialListener
{
  private final CustomEventAdapter zzfx;
  private final MediationInterstitialListener zzfz;
  
  public CustomEventAdapter$zzb(CustomEventAdapter paramCustomEventAdapter1, CustomEventAdapter paramCustomEventAdapter2, MediationInterstitialListener paramMediationInterstitialListener)
  {
    zzfx = paramCustomEventAdapter2;
    zzfz = paramMediationInterstitialListener;
  }
  
  public void onDismissScreen()
  {
    zzb.zzcw("Custom event adapter called onDismissScreen.");
    zzfz.onDismissScreen(zzfx);
  }
  
  public void onFailedToReceiveAd()
  {
    zzb.zzcw("Custom event adapter called onFailedToReceiveAd.");
    zzfz.onFailedToReceiveAd(zzfx, AdRequest.ErrorCode.NO_FILL);
  }
  
  public void onLeaveApplication()
  {
    zzb.zzcw("Custom event adapter called onLeaveApplication.");
    zzfz.onLeaveApplication(zzfx);
  }
  
  public void onPresentScreen()
  {
    zzb.zzcw("Custom event adapter called onPresentScreen.");
    zzfz.onPresentScreen(zzfx);
  }
  
  public void onReceivedAd()
  {
    zzb.zzcw("Custom event adapter called onReceivedAd.");
    zzfz.onReceivedAd(zzga);
  }
}

/* Location:
 * Qualified Name:     com.google.ads.mediation.customevent.CustomEventAdapter.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */