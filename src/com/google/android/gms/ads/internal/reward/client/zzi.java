package com.google.android.gms.ads.internal.reward.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.internal.client.zzh;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzir;

@zzir
public class zzi
  implements RewardedVideoAd
{
  private final Context mContext;
  private final Object zzail = new Object();
  private final zzb zzchp;
  private RewardedVideoAdListener zzfh;
  
  public zzi(Context paramContext, zzb paramzzb)
  {
    zzchp = paramzzb;
    mContext = paramContext;
  }
  
  public void destroy()
  {
    destroy(null);
  }
  
  public void destroy(Context paramContext)
  {
    synchronized (zzail)
    {
      if (zzchp == null) {
        return;
      }
    }
    try
    {
      zzchp.zzh(zze.zzae(paramContext));
      return;
      paramContext = finally;
      throw paramContext;
    }
    catch (RemoteException paramContext)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not forward destroy to RewardedVideoAd", paramContext);
      }
    }
  }
  
  public RewardedVideoAdListener getRewardedVideoAdListener()
  {
    synchronized (zzail)
    {
      RewardedVideoAdListener localRewardedVideoAdListener = zzfh;
      return localRewardedVideoAdListener;
    }
  }
  
  public String getUserId()
  {
    com.google.android.gms.ads.internal.util.client.zzb.zzcy("RewardedVideoAd.getUserId() is deprecated. Please do not call this method.");
    return null;
  }
  
  public boolean isLoaded()
  {
    boolean bool;
    synchronized (zzail)
    {
      if (zzchp == null) {
        return false;
      }
    }
    return false;
  }
  
  public void loadAd(String paramString, AdRequest paramAdRequest)
  {
    synchronized (zzail)
    {
      if (zzchp == null) {
        return;
      }
    }
    try
    {
      zzchp.zza(zzh.zzih().zza(mContext, paramAdRequest.zzdd(), paramString));
      return;
      paramString = finally;
      throw paramString;
    }
    catch (RemoteException paramString)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not forward loadAd to RewardedVideoAd", paramString);
      }
    }
  }
  
  public void pause()
  {
    pause(null);
  }
  
  public void pause(Context paramContext)
  {
    synchronized (zzail)
    {
      if (zzchp == null) {
        return;
      }
    }
    try
    {
      zzchp.zzf(zze.zzae(paramContext));
      return;
      paramContext = finally;
      throw paramContext;
    }
    catch (RemoteException paramContext)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not forward pause to RewardedVideoAd", paramContext);
      }
    }
  }
  
  public void resume()
  {
    resume(null);
  }
  
  public void resume(Context paramContext)
  {
    synchronized (zzail)
    {
      if (zzchp == null) {
        return;
      }
    }
    try
    {
      zzchp.zzg(zze.zzae(paramContext));
      return;
      paramContext = finally;
      throw paramContext;
    }
    catch (RemoteException paramContext)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not forward resume to RewardedVideoAd", paramContext);
      }
    }
  }
  
  public void setRewardedVideoAdListener(RewardedVideoAdListener paramRewardedVideoAdListener)
  {
    synchronized (zzail)
    {
      zzfh = paramRewardedVideoAdListener;
      zzb localzzb = zzchp;
      if (localzzb != null) {}
      try
      {
        zzchp.zza(new zzg(paramRewardedVideoAdListener));
        return;
      }
      catch (RemoteException paramRewardedVideoAdListener)
      {
        for (;;)
        {
          com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not forward setRewardedVideoAdListener to RewardedVideoAd", paramRewardedVideoAdListener);
        }
      }
    }
  }
  
  public void setUserId(String paramString)
  {
    com.google.android.gms.ads.internal.util.client.zzb.zzcy("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
  }
  
  public void show()
  {
    synchronized (zzail)
    {
      if (zzchp == null) {
        return;
      }
    }
    try
    {
      zzchp.show();
      return;
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not forward show to RewardedVideoAd", localRemoteException);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.internal.reward.client.zzi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */