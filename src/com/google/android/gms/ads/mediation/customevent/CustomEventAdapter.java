package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
public final class CustomEventAdapter
  implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter
{
  CustomEventBanner zzcqu;
  CustomEventInterstitial zzcqv;
  CustomEventNative zzcqw;
  private View zzfu;
  
  private void zza(View paramView)
  {
    zzfu = paramView;
  }
  
  private static <T> T zzj(String paramString)
  {
    try
    {
      Object localObject = Class.forName(paramString).newInstance();
      return (T)localObject;
    }
    catch (Throwable localThrowable)
    {
      String str = String.valueOf(localThrowable.getMessage());
      zzb.zzcy(String.valueOf(paramString).length() + 46 + String.valueOf(str).length() + "Could not instantiate custom event adapter: " + paramString + ". " + str);
    }
    return null;
  }
  
  public View getBannerView()
  {
    return zzfu;
  }
  
  public void onDestroy()
  {
    if (zzcqu != null) {
      zzcqu.onDestroy();
    }
    if (zzcqv != null) {
      zzcqv.onDestroy();
    }
    if (zzcqw != null) {
      zzcqw.onDestroy();
    }
  }
  
  public void onPause()
  {
    if (zzcqu != null) {
      zzcqu.onPause();
    }
    if (zzcqv != null) {
      zzcqv.onPause();
    }
    if (zzcqw != null) {
      zzcqw.onPause();
    }
  }
  
  public void onResume()
  {
    if (zzcqu != null) {
      zzcqu.onResume();
    }
    if (zzcqv != null) {
      zzcqv.onResume();
    }
    if (zzcqw != null) {
      zzcqw.onResume();
    }
  }
  
  public void requestBannerAd(Context paramContext, MediationBannerListener paramMediationBannerListener, Bundle paramBundle1, AdSize paramAdSize, MediationAdRequest paramMediationAdRequest, Bundle paramBundle2)
  {
    zzcqu = ((CustomEventBanner)zzj(paramBundle1.getString("class_name")));
    if (zzcqu == null)
    {
      paramMediationBannerListener.onAdFailedToLoad(this, 0);
      return;
    }
    if (paramBundle2 == null) {}
    for (paramBundle2 = null;; paramBundle2 = paramBundle2.getBundle(paramBundle1.getString("class_name")))
    {
      zzcqu.requestBannerAd(paramContext, new zza(this, paramMediationBannerListener), paramBundle1.getString("parameter"), paramAdSize, paramMediationAdRequest, paramBundle2);
      return;
    }
  }
  
  public void requestInterstitialAd(Context paramContext, MediationInterstitialListener paramMediationInterstitialListener, Bundle paramBundle1, MediationAdRequest paramMediationAdRequest, Bundle paramBundle2)
  {
    zzcqv = ((CustomEventInterstitial)zzj(paramBundle1.getString("class_name")));
    if (zzcqv == null)
    {
      paramMediationInterstitialListener.onAdFailedToLoad(this, 0);
      return;
    }
    if (paramBundle2 == null) {}
    for (paramBundle2 = null;; paramBundle2 = paramBundle2.getBundle(paramBundle1.getString("class_name")))
    {
      zzcqv.requestInterstitialAd(paramContext, zza(paramMediationInterstitialListener), paramBundle1.getString("parameter"), paramMediationAdRequest, paramBundle2);
      return;
    }
  }
  
  public void requestNativeAd(Context paramContext, MediationNativeListener paramMediationNativeListener, Bundle paramBundle1, NativeMediationAdRequest paramNativeMediationAdRequest, Bundle paramBundle2)
  {
    zzcqw = ((CustomEventNative)zzj(paramBundle1.getString("class_name")));
    if (zzcqw == null)
    {
      paramMediationNativeListener.onAdFailedToLoad(this, 0);
      return;
    }
    if (paramBundle2 == null) {}
    for (paramBundle2 = null;; paramBundle2 = paramBundle2.getBundle(paramBundle1.getString("class_name")))
    {
      zzcqw.requestNativeAd(paramContext, new zzc(this, paramMediationNativeListener), paramBundle1.getString("parameter"), paramNativeMediationAdRequest, paramBundle2);
      return;
    }
  }
  
  public void showInterstitial()
  {
    zzcqv.showInterstitial();
  }
  
  zzb zza(MediationInterstitialListener paramMediationInterstitialListener)
  {
    return new zzb(this, paramMediationInterstitialListener);
  }
  
  static final class zza
    implements CustomEventBannerListener
  {
    private final CustomEventAdapter zzcqx;
    private final MediationBannerListener zzfm;
    
    public zza(CustomEventAdapter paramCustomEventAdapter, MediationBannerListener paramMediationBannerListener)
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
  
  class zzb
    implements CustomEventInterstitialListener
  {
    private final CustomEventAdapter zzcqx;
    private final MediationInterstitialListener zzfn;
    
    public zzb(CustomEventAdapter paramCustomEventAdapter, MediationInterstitialListener paramMediationInterstitialListener)
    {
      zzcqx = paramCustomEventAdapter;
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
      zzfn.onAdLoaded(CustomEventAdapter.this);
    }
    
    public void onAdOpened()
    {
      zzb.zzcw("Custom event adapter called onAdOpened.");
      zzfn.onAdOpened(zzcqx);
    }
  }
  
  static class zzc
    implements CustomEventNativeListener
  {
    private final CustomEventAdapter zzcqx;
    private final MediationNativeListener zzfo;
    
    public zzc(CustomEventAdapter paramCustomEventAdapter, MediationNativeListener paramMediationNativeListener)
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
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.mediation.customevent.CustomEventAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */