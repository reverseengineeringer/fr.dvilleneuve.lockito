package com.google.android.gms.ads.internal.client;

import android.os.Handler;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.reward.client.zzb.zza;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;

public class zzan
  extends zzb.zza
{
  private com.google.android.gms.ads.internal.reward.client.zzd zzawv;
  
  public void destroy()
    throws RemoteException
  {}
  
  public boolean isLoaded()
    throws RemoteException
  {
    return false;
  }
  
  public void pause()
    throws RemoteException
  {}
  
  public void resume()
    throws RemoteException
  {}
  
  public void setUserId(String paramString)
    throws RemoteException
  {}
  
  public void show()
    throws RemoteException
  {}
  
  public void zza(RewardedVideoAdRequestParcel paramRewardedVideoAdRequestParcel)
    throws RemoteException
  {
    zzb.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
    zza.zzcnf.post(new Runnable()
    {
      public void run()
      {
        if (zzan.zza(zzan.this) != null) {}
        try
        {
          zzan.zza(zzan.this).onRewardedVideoAdFailedToLoad(1);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          zzb.zzd("Could not notify onRewardedVideoAdFailedToLoad event.", localRemoteException);
        }
      }
    });
  }
  
  public void zza(com.google.android.gms.ads.internal.reward.client.zzd paramzzd)
    throws RemoteException
  {
    zzawv = paramzzd;
  }
  
  public void zzf(com.google.android.gms.dynamic.zzd paramzzd)
    throws RemoteException
  {}
  
  public void zzg(com.google.android.gms.dynamic.zzd paramzzd)
    throws RemoteException
  {}
  
  public void zzh(com.google.android.gms.dynamic.zzd paramzzd)
    throws RemoteException
  {}
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.internal.client.zzan
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */