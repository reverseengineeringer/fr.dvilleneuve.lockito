package com.google.android.gms.ads;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;
import com.google.android.gms.ads.internal.client.zzad;
import com.google.android.gms.ads.internal.client.zzc;
import com.google.android.gms.ads.internal.client.zzh;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.client.zzr;
import com.google.android.gms.ads.internal.client.zzs;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzej;
import com.google.android.gms.internal.zzek;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzem;
import com.google.android.gms.internal.zzgm;

public class AdLoader
{
  private final Context mContext;
  private final zzh zzahz;
  private final zzr zzaia;
  
  AdLoader(Context paramContext, zzr paramzzr)
  {
    this(paramContext, paramzzr, zzh.zzih());
  }
  
  AdLoader(Context paramContext, zzr paramzzr, zzh paramzzh)
  {
    mContext = paramContext;
    zzaia = paramzzr;
    zzahz = paramzzh;
  }
  
  private void zza(zzad paramzzad)
  {
    try
    {
      zzaia.zzf(zzahz.zza(mContext, paramzzad));
      return;
    }
    catch (RemoteException paramzzad)
    {
      zzb.zzb("Failed to load ad.", paramzzad);
    }
  }
  
  public String getMediationAdapterClassName()
  {
    try
    {
      String str = zzaia.getMediationAdapterClassName();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzd("Failed to get the mediation adapter class name.", localRemoteException);
    }
    return null;
  }
  
  public boolean isLoading()
  {
    try
    {
      boolean bool = zzaia.isLoading();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzd("Failed to check if ad is loading.", localRemoteException);
    }
    return false;
  }
  
  @RequiresPermission("android.permission.INTERNET")
  public void loadAd(AdRequest paramAdRequest)
  {
    zza(paramAdRequest.zzdd());
  }
  
  public void loadAd(PublisherAdRequest paramPublisherAdRequest)
  {
    zza(paramPublisherAdRequest.zzdd());
  }
  
  public static class Builder
  {
    private final Context mContext;
    private final zzs zzaib;
    
    Builder(Context paramContext, zzs paramzzs)
    {
      mContext = paramContext;
      zzaib = paramzzs;
    }
    
    public Builder(Context paramContext, String paramString)
    {
      this((Context)zzab.zzb(paramContext, "context cannot be null"), zzm.zzix().zzb(paramContext, paramString, new zzgm()));
    }
    
    public AdLoader build()
    {
      try
      {
        AdLoader localAdLoader = new AdLoader(mContext, zzaib.zzes());
        return localAdLoader;
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzb("Failed to build AdLoader.", localRemoteException);
      }
      return null;
    }
    
    public Builder forAppInstallAd(NativeAppInstallAd.OnAppInstallAdLoadedListener paramOnAppInstallAdLoadedListener)
    {
      try
      {
        zzaib.zza(new zzej(paramOnAppInstallAdLoadedListener));
        return this;
      }
      catch (RemoteException paramOnAppInstallAdLoadedListener)
      {
        zzb.zzd("Failed to add app install ad listener", paramOnAppInstallAdLoadedListener);
      }
      return this;
    }
    
    public Builder forContentAd(NativeContentAd.OnContentAdLoadedListener paramOnContentAdLoadedListener)
    {
      try
      {
        zzaib.zza(new zzek(paramOnContentAdLoadedListener));
        return this;
      }
      catch (RemoteException paramOnContentAdLoadedListener)
      {
        zzb.zzd("Failed to add content ad listener", paramOnContentAdLoadedListener);
      }
      return this;
    }
    
    public Builder forCustomTemplateAd(String paramString, NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener paramOnCustomTemplateAdLoadedListener, NativeCustomTemplateAd.OnCustomClickListener paramOnCustomClickListener)
    {
      try
      {
        zzs localzzs = zzaib;
        zzem localzzem = new zzem(paramOnCustomTemplateAdLoadedListener);
        if (paramOnCustomClickListener == null) {}
        for (paramOnCustomTemplateAdLoadedListener = null;; paramOnCustomTemplateAdLoadedListener = new zzel(paramOnCustomClickListener))
        {
          localzzs.zza(paramString, localzzem, paramOnCustomTemplateAdLoadedListener);
          return this;
        }
        return this;
      }
      catch (RemoteException paramString)
      {
        zzb.zzd("Failed to add custom template ad listener", paramString);
      }
    }
    
    public Builder withAdListener(AdListener paramAdListener)
    {
      try
      {
        zzaib.zzb(new zzc(paramAdListener));
        return this;
      }
      catch (RemoteException paramAdListener)
      {
        zzb.zzd("Failed to set AdListener.", paramAdListener);
      }
      return this;
    }
    
    public Builder withCorrelator(@NonNull Correlator paramCorrelator)
    {
      zzab.zzaa(paramCorrelator);
      try
      {
        zzaib.zzb(paramCorrelator.zzde());
        return this;
      }
      catch (RemoteException paramCorrelator)
      {
        zzb.zzd("Failed to set correlator.", paramCorrelator);
      }
      return this;
    }
    
    public Builder withNativeAdOptions(NativeAdOptions paramNativeAdOptions)
    {
      try
      {
        zzaib.zza(new NativeAdOptionsParcel(paramNativeAdOptions));
        return this;
      }
      catch (RemoteException paramNativeAdOptions)
      {
        zzb.zzd("Failed to specify native ad options", paramNativeAdOptions);
      }
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.AdLoader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */