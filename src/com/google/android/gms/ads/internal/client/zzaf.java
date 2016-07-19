package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.internal.reward.client.zzg;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.internal.zzdp;
import com.google.android.gms.internal.zzgm;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzib;
import com.google.android.gms.internal.zzir;

@zzir
public class zzaf
{
  private final Context mContext;
  private final zzh zzahz;
  private String zzall;
  private zza zzati;
  private AdListener zzatj;
  private AppEventListener zzauv;
  private final zzgm zzavz = new zzgm();
  private Correlator zzawd;
  private zzu zzawe;
  private InAppPurchaseListener zzawf;
  private OnCustomRenderedAdLoadedListener zzawg;
  private PlayStorePurchaseListener zzawh;
  private String zzawj;
  private PublisherInterstitialAd zzawn;
  private boolean zzawo;
  private RewardedVideoAdListener zzfh;
  
  public zzaf(Context paramContext)
  {
    this(paramContext, zzh.zzih(), null);
  }
  
  public zzaf(Context paramContext, PublisherInterstitialAd paramPublisherInterstitialAd)
  {
    this(paramContext, zzh.zzih(), paramPublisherInterstitialAd);
  }
  
  public zzaf(Context paramContext, zzh paramzzh, PublisherInterstitialAd paramPublisherInterstitialAd)
  {
    mContext = paramContext;
    zzahz = paramzzh;
    zzawn = paramPublisherInterstitialAd;
  }
  
  private void zzam(String paramString)
    throws RemoteException
  {
    if (zzall == null) {
      zzan(paramString);
    }
    if (zzawo) {}
    for (paramString = AdSizeParcel.zzii();; paramString = new AdSizeParcel())
    {
      zzawe = zzm.zzix().zzb(mContext, paramString, zzall, zzavz);
      if (zzatj != null) {
        zzawe.zza(new zzc(zzatj));
      }
      if (zzati != null) {
        zzawe.zza(new zzb(zzati));
      }
      if (zzauv != null) {
        zzawe.zza(new zzj(zzauv));
      }
      if (zzawf != null) {
        zzawe.zza(new zzhx(zzawf));
      }
      if (zzawh != null) {
        zzawe.zza(new zzib(zzawh), zzawj);
      }
      if (zzawg != null) {
        zzawe.zza(new zzdp(zzawg));
      }
      if (zzawd != null) {
        zzawe.zza(zzawd.zzde());
      }
      if (zzfh != null) {
        zzawe.zza(new zzg(zzfh));
      }
      return;
    }
  }
  
  private void zzan(String paramString)
  {
    if (zzawe == null) {
      throw new IllegalStateException(String.valueOf(paramString).length() + 63 + "The ad unit ID must be set on InterstitialAd before " + paramString + " is called.");
    }
  }
  
  public AdListener getAdListener()
  {
    return zzatj;
  }
  
  public String getAdUnitId()
  {
    return zzall;
  }
  
  public AppEventListener getAppEventListener()
  {
    return zzauv;
  }
  
  public InAppPurchaseListener getInAppPurchaseListener()
  {
    return zzawf;
  }
  
  public String getMediationAdapterClassName()
  {
    try
    {
      if (zzawe != null)
      {
        String str = zzawe.getMediationAdapterClassName();
        return str;
      }
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to get the mediation adapter class name.", localRemoteException);
    }
    return null;
  }
  
  public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener()
  {
    return zzawg;
  }
  
  public boolean isLoaded()
  {
    try
    {
      if (zzawe == null) {
        return false;
      }
      boolean bool = zzawe.isReady();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to check if ad is ready.", localRemoteException);
    }
    return false;
  }
  
  public boolean isLoading()
  {
    try
    {
      if (zzawe == null) {
        return false;
      }
      boolean bool = zzawe.isLoading();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to check if ad is loading.", localRemoteException);
    }
    return false;
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    try
    {
      zzatj = paramAdListener;
      zzu localzzu;
      if (zzawe != null)
      {
        localzzu = zzawe;
        if (paramAdListener == null) {
          break label38;
        }
      }
      label38:
      for (paramAdListener = new zzc(paramAdListener);; paramAdListener = null)
      {
        localzzu.zza(paramAdListener);
        return;
      }
      return;
    }
    catch (RemoteException paramAdListener)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AdListener.", paramAdListener);
    }
  }
  
  public void setAdUnitId(String paramString)
  {
    if (zzall != null) {
      throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
    }
    zzall = paramString;
  }
  
  public void setAppEventListener(AppEventListener paramAppEventListener)
  {
    try
    {
      zzauv = paramAppEventListener;
      zzu localzzu;
      if (zzawe != null)
      {
        localzzu = zzawe;
        if (paramAppEventListener == null) {
          break label38;
        }
      }
      label38:
      for (paramAppEventListener = new zzj(paramAppEventListener);; paramAppEventListener = null)
      {
        localzzu.zza(paramAppEventListener);
        return;
      }
      return;
    }
    catch (RemoteException paramAppEventListener)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AppEventListener.", paramAppEventListener);
    }
  }
  
  public void setCorrelator(Correlator paramCorrelator)
  {
    zzawd = paramCorrelator;
    try
    {
      if (zzawe != null)
      {
        zzu localzzu = zzawe;
        if (zzawd == null) {}
        for (paramCorrelator = null;; paramCorrelator = zzawd.zzde())
        {
          localzzu.zza(paramCorrelator);
          return;
        }
      }
      return;
    }
    catch (RemoteException paramCorrelator)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set correlator.", paramCorrelator);
    }
  }
  
  public void setInAppPurchaseListener(InAppPurchaseListener paramInAppPurchaseListener)
  {
    if (zzawh != null) {
      throw new IllegalStateException("Play store purchase parameter has already been set.");
    }
    try
    {
      zzawf = paramInAppPurchaseListener;
      zzu localzzu;
      if (zzawe != null)
      {
        localzzu = zzawe;
        if (paramInAppPurchaseListener == null) {
          break label56;
        }
      }
      label56:
      for (paramInAppPurchaseListener = new zzhx(paramInAppPurchaseListener);; paramInAppPurchaseListener = null)
      {
        localzzu.zza(paramInAppPurchaseListener);
        return;
      }
      return;
    }
    catch (RemoteException paramInAppPurchaseListener)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the InAppPurchaseListener.", paramInAppPurchaseListener);
    }
  }
  
  public void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener paramOnCustomRenderedAdLoadedListener)
  {
    try
    {
      zzawg = paramOnCustomRenderedAdLoadedListener;
      zzu localzzu;
      if (zzawe != null)
      {
        localzzu = zzawe;
        if (paramOnCustomRenderedAdLoadedListener == null) {
          break label38;
        }
      }
      label38:
      for (paramOnCustomRenderedAdLoadedListener = new zzdp(paramOnCustomRenderedAdLoadedListener);; paramOnCustomRenderedAdLoadedListener = null)
      {
        localzzu.zza(paramOnCustomRenderedAdLoadedListener);
        return;
      }
      return;
    }
    catch (RemoteException paramOnCustomRenderedAdLoadedListener)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the OnCustomRenderedAdLoadedListener.", paramOnCustomRenderedAdLoadedListener);
    }
  }
  
  public void setPlayStorePurchaseParams(PlayStorePurchaseListener paramPlayStorePurchaseListener, String paramString)
  {
    if (zzawf != null) {
      throw new IllegalStateException("In app purchase parameter has already been set.");
    }
    try
    {
      zzawh = paramPlayStorePurchaseListener;
      zzawj = paramString;
      zzu localzzu;
      if (zzawe != null)
      {
        localzzu = zzawe;
        if (paramPlayStorePurchaseListener == null) {
          break label62;
        }
      }
      label62:
      for (paramPlayStorePurchaseListener = new zzib(paramPlayStorePurchaseListener);; paramPlayStorePurchaseListener = null)
      {
        localzzu.zza(paramPlayStorePurchaseListener, paramString);
        return;
      }
      return;
    }
    catch (RemoteException paramPlayStorePurchaseListener)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the play store purchase parameter.", paramPlayStorePurchaseListener);
    }
  }
  
  public void setRewardedVideoAdListener(RewardedVideoAdListener paramRewardedVideoAdListener)
  {
    try
    {
      zzfh = paramRewardedVideoAdListener;
      zzu localzzu;
      if (zzawe != null)
      {
        localzzu = zzawe;
        if (paramRewardedVideoAdListener == null) {
          break label38;
        }
      }
      label38:
      for (paramRewardedVideoAdListener = new zzg(paramRewardedVideoAdListener);; paramRewardedVideoAdListener = null)
      {
        localzzu.zza(paramRewardedVideoAdListener);
        return;
      }
      return;
    }
    catch (RemoteException paramRewardedVideoAdListener)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AdListener.", paramRewardedVideoAdListener);
    }
  }
  
  public void show()
  {
    try
    {
      zzan("show");
      zzawe.showInterstitial();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to show interstitial.", localRemoteException);
    }
  }
  
  public void zza(zza paramzza)
  {
    try
    {
      zzati = paramzza;
      zzu localzzu;
      if (zzawe != null)
      {
        localzzu = zzawe;
        if (paramzza == null) {
          break label38;
        }
      }
      label38:
      for (paramzza = new zzb(paramzza);; paramzza = null)
      {
        localzzu.zza(paramzza);
        return;
      }
      return;
    }
    catch (RemoteException paramzza)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AdClickListener.", paramzza);
    }
  }
  
  public void zza(zzad paramzzad)
  {
    try
    {
      if (zzawe == null) {
        zzam("loadAd");
      }
      if (zzawe.zzb(zzahz.zza(mContext, paramzzad))) {
        zzavz.zzh(paramzzad.zzjg());
      }
      return;
    }
    catch (RemoteException paramzzad)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to load ad.", paramzzad);
    }
  }
  
  public void zzd(boolean paramBoolean)
  {
    zzawo = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.internal.client.zzaf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */