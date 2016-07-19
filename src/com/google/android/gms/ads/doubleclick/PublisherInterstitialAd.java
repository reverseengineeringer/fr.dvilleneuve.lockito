package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.internal.client.zzaf;

public final class PublisherInterstitialAd
{
  private final zzaf zzaij = new zzaf(paramContext, this);
  
  public PublisherInterstitialAd(Context paramContext) {}
  
  public AdListener getAdListener()
  {
    return zzaij.getAdListener();
  }
  
  public String getAdUnitId()
  {
    return zzaij.getAdUnitId();
  }
  
  public AppEventListener getAppEventListener()
  {
    return zzaij.getAppEventListener();
  }
  
  public String getMediationAdapterClassName()
  {
    return zzaij.getMediationAdapterClassName();
  }
  
  public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener()
  {
    return zzaij.getOnCustomRenderedAdLoadedListener();
  }
  
  public boolean isLoaded()
  {
    return zzaij.isLoaded();
  }
  
  public boolean isLoading()
  {
    return zzaij.isLoading();
  }
  
  @RequiresPermission("android.permission.INTERNET")
  public void loadAd(PublisherAdRequest paramPublisherAdRequest)
  {
    zzaij.zza(paramPublisherAdRequest.zzdd());
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    zzaij.setAdListener(paramAdListener);
  }
  
  public void setAdUnitId(String paramString)
  {
    zzaij.setAdUnitId(paramString);
  }
  
  public void setAppEventListener(AppEventListener paramAppEventListener)
  {
    zzaij.setAppEventListener(paramAppEventListener);
  }
  
  public void setCorrelator(Correlator paramCorrelator)
  {
    zzaij.setCorrelator(paramCorrelator);
  }
  
  public void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener paramOnCustomRenderedAdLoadedListener)
  {
    zzaij.setOnCustomRenderedAdLoadedListener(paramOnCustomRenderedAdLoadedListener);
  }
  
  public void show()
  {
    zzaij.show();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.doubleclick.PublisherInterstitialAd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */