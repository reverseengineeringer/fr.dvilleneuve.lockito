package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.reward.client.zzi;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzgm;
import com.google.android.gms.internal.zzir;

@zzir
public class zzag
{
  private static final Object zzamp = new Object();
  private static zzag zzawp;
  private zzz zzawq;
  private RewardedVideoAd zzawr;
  
  public static zzag zzjo()
  {
    synchronized (zzamp)
    {
      if (zzawp == null) {
        zzawp = new zzag();
      }
      zzag localzzag = zzawp;
      return localzzag;
    }
  }
  
  public RewardedVideoAd getRewardedVideoAdInstance(Context paramContext)
  {
    synchronized (zzamp)
    {
      if (zzawr != null)
      {
        paramContext = zzawr;
        return paramContext;
      }
      zzgm localzzgm = new zzgm();
      zzawr = new zzi(paramContext, zzm.zzix().zza(paramContext, localzzgm));
      paramContext = zzawr;
      return paramContext;
    }
  }
  
  public void setAppMuted(boolean paramBoolean)
  {
    if (zzawq != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzab.zza(bool, "MobileAds.initialize() must be called prior to setting the app volume.");
      try
      {
        zzawq.setAppMuted(paramBoolean);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzb("Unable to set app mute state.", localRemoteException);
      }
    }
  }
  
  public void setAppVolume(float paramFloat)
  {
    boolean bool2 = true;
    if ((0.0F <= paramFloat) && (paramFloat <= 1.0F))
    {
      bool1 = true;
      zzab.zzb(bool1, "The app volume must be a value between 0 and 1 inclusive.");
      if (zzawq == null) {
        break label53;
      }
    }
    label53:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzab.zza(bool1, "MobileAds.initialize() must be called prior to setting the app volume.");
      try
      {
        zzawq.setAppVolume(paramFloat);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzb("Unable to set app volume.", localRemoteException);
      }
      bool1 = false;
      break;
    }
  }
  
  public void zza(Context paramContext, String paramString, zzah arg3)
  {
    synchronized (zzamp)
    {
      if (zzawq != null) {
        return;
      }
      if (paramContext == null) {
        throw new IllegalArgumentException("Context cannot be null.");
      }
    }
    try
    {
      zzawq = zzm.zzix().zzl(paramContext);
      zzawq.initialize();
      if (paramString != null) {
        zzawq.zzu(paramString);
      }
    }
    catch (RemoteException paramContext)
    {
      for (;;)
      {
        zzb.zzd("Fail to initialize or set applicationCode on mobile ads setting manager", paramContext);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.internal.client.zzag
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */