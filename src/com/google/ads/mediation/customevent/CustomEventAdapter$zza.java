package com.google.ads.mediation.customevent;

import android.view.View;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.internal.util.client.zzb;

final class CustomEventAdapter$zza
  implements CustomEventBannerListener
{
  private final CustomEventAdapter zzfx;
  private final MediationBannerListener zzfy;
  
  public CustomEventAdapter$zza(CustomEventAdapter paramCustomEventAdapter, MediationBannerListener paramMediationBannerListener)
  {
    zzfx = paramCustomEventAdapter;
    zzfy = paramMediationBannerListener;
  }
  
  public void onClick()
  {
    zzb.zzcw("Custom event adapter called onFailedToReceiveAd.");
    zzfy.onClick(zzfx);
  }
  
  public void onDismissScreen()
  {
    zzb.zzcw("Custom event adapter called onFailedToReceiveAd.");
    zzfy.onDismissScreen(zzfx);
  }
  
  public void onFailedToReceiveAd()
  {
    zzb.zzcw("Custom event adapter called onFailedToReceiveAd.");
    zzfy.onFailedToReceiveAd(zzfx, AdRequest.ErrorCode.NO_FILL);
  }
  
  public void onLeaveApplication()
  {
    zzb.zzcw("Custom event adapter called onFailedToReceiveAd.");
    zzfy.onLeaveApplication(zzfx);
  }
  
  public void onPresentScreen()
  {
    zzb.zzcw("Custom event adapter called onFailedToReceiveAd.");
    zzfy.onPresentScreen(zzfx);
  }
  
  public void onReceivedAd(View paramView)
  {
    zzb.zzcw("Custom event adapter called onReceivedAd.");
    CustomEventAdapter.zza(zzfx, paramView);
    zzfy.onReceivedAd(zzfx);
  }
}

/* Location:
 * Qualified Name:     com.google.ads.mediation.customevent.CustomEventAdapter.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */