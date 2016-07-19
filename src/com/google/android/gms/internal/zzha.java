package com.google.android.gms.internal;

import android.os.Handler;
import android.os.RemoteException;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;

@zzir
public final class zzha<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters>
  implements MediationBannerListener, MediationInterstitialListener
{
  private final zzgp zzbpo;
  
  public zzha(zzgp paramzzgp)
  {
    zzbpo = paramzzgp;
  }
  
  public void onClick(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    zzb.zzcw("Adapter called onClick.");
    if (!zzm.zziw().zzty())
    {
      zzb.zzcy("onClick must be called on the main UI thread.");
      zza.zzcnf.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzha.zza(zzha.this).onAdClicked();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdClicked.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      zzbpo.onAdClicked();
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      zzb.zzd("Could not call onAdClicked.", paramMediationBannerAdapter);
    }
  }
  
  public void onDismissScreen(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    zzb.zzcw("Adapter called onDismissScreen.");
    if (!zzm.zziw().zzty())
    {
      zzb.zzcy("onDismissScreen must be called on the main UI thread.");
      zza.zzcnf.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzha.zza(zzha.this).onAdClosed();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdClosed.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      zzbpo.onAdClosed();
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      zzb.zzd("Could not call onAdClosed.", paramMediationBannerAdapter);
    }
  }
  
  public void onDismissScreen(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    zzb.zzcw("Adapter called onDismissScreen.");
    if (!zzm.zziw().zzty())
    {
      zzb.zzcy("onDismissScreen must be called on the main UI thread.");
      zza.zzcnf.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzha.zza(zzha.this).onAdClosed();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdClosed.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      zzbpo.onAdClosed();
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      zzb.zzd("Could not call onAdClosed.", paramMediationInterstitialAdapter);
    }
  }
  
  public void onFailedToReceiveAd(MediationBannerAdapter<?, ?> paramMediationBannerAdapter, final AdRequest.ErrorCode paramErrorCode)
  {
    paramMediationBannerAdapter = String.valueOf(paramErrorCode);
    zzb.zzcw(String.valueOf(paramMediationBannerAdapter).length() + 47 + "Adapter called onFailedToReceiveAd with error. " + paramMediationBannerAdapter);
    if (!zzm.zziw().zzty())
    {
      zzb.zzcy("onFailedToReceiveAd must be called on the main UI thread.");
      zza.zzcnf.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzha.zza(zzha.this).onAdFailedToLoad(zzhb.zza(paramErrorCode));
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdFailedToLoad.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      zzbpo.onAdFailedToLoad(zzhb.zza(paramErrorCode));
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      zzb.zzd("Could not call onAdFailedToLoad.", paramMediationBannerAdapter);
    }
  }
  
  public void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter, final AdRequest.ErrorCode paramErrorCode)
  {
    paramMediationInterstitialAdapter = String.valueOf(paramErrorCode);
    zzb.zzcw(String.valueOf(paramMediationInterstitialAdapter).length() + 47 + "Adapter called onFailedToReceiveAd with error " + paramMediationInterstitialAdapter + ".");
    if (!zzm.zziw().zzty())
    {
      zzb.zzcy("onFailedToReceiveAd must be called on the main UI thread.");
      zza.zzcnf.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzha.zza(zzha.this).onAdFailedToLoad(zzhb.zza(paramErrorCode));
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdFailedToLoad.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      zzbpo.onAdFailedToLoad(zzhb.zza(paramErrorCode));
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      zzb.zzd("Could not call onAdFailedToLoad.", paramMediationInterstitialAdapter);
    }
  }
  
  public void onLeaveApplication(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    zzb.zzcw("Adapter called onLeaveApplication.");
    if (!zzm.zziw().zzty())
    {
      zzb.zzcy("onLeaveApplication must be called on the main UI thread.");
      zza.zzcnf.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzha.zza(zzha.this).onAdLeftApplication();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdLeftApplication.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      zzbpo.onAdLeftApplication();
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      zzb.zzd("Could not call onAdLeftApplication.", paramMediationBannerAdapter);
    }
  }
  
  public void onLeaveApplication(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    zzb.zzcw("Adapter called onLeaveApplication.");
    if (!zzm.zziw().zzty())
    {
      zzb.zzcy("onLeaveApplication must be called on the main UI thread.");
      zza.zzcnf.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzha.zza(zzha.this).onAdLeftApplication();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdLeftApplication.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      zzbpo.onAdLeftApplication();
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      zzb.zzd("Could not call onAdLeftApplication.", paramMediationInterstitialAdapter);
    }
  }
  
  public void onPresentScreen(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    zzb.zzcw("Adapter called onPresentScreen.");
    if (!zzm.zziw().zzty())
    {
      zzb.zzcy("onPresentScreen must be called on the main UI thread.");
      zza.zzcnf.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzha.zza(zzha.this).onAdOpened();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdOpened.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      zzbpo.onAdOpened();
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      zzb.zzd("Could not call onAdOpened.", paramMediationBannerAdapter);
    }
  }
  
  public void onPresentScreen(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    zzb.zzcw("Adapter called onPresentScreen.");
    if (!zzm.zziw().zzty())
    {
      zzb.zzcy("onPresentScreen must be called on the main UI thread.");
      zza.zzcnf.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzha.zza(zzha.this).onAdOpened();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdOpened.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      zzbpo.onAdOpened();
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      zzb.zzd("Could not call onAdOpened.", paramMediationInterstitialAdapter);
    }
  }
  
  public void onReceivedAd(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    zzb.zzcw("Adapter called onReceivedAd.");
    if (!zzm.zziw().zzty())
    {
      zzb.zzcy("onReceivedAd must be called on the main UI thread.");
      zza.zzcnf.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzha.zza(zzha.this).onAdLoaded();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdLoaded.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      zzbpo.onAdLoaded();
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      zzb.zzd("Could not call onAdLoaded.", paramMediationBannerAdapter);
    }
  }
  
  public void onReceivedAd(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    zzb.zzcw("Adapter called onReceivedAd.");
    if (!zzm.zziw().zzty())
    {
      zzb.zzcy("onReceivedAd must be called on the main UI thread.");
      zza.zzcnf.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzha.zza(zzha.this).onAdLoaded();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdLoaded.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      zzbpo.onAdLoaded();
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      zzb.zzd("Could not call onAdLoaded.", paramMediationInterstitialAdapter);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzha
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */