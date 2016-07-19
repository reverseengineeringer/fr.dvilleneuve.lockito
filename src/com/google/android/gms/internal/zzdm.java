package com.google.android.gms.internal;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;

@zzir
public class zzdm
  implements CustomRenderedAd
{
  private final zzdn zzbel;
  
  public zzdm(zzdn paramzzdn)
  {
    zzbel = paramzzdn;
  }
  
  public String getBaseUrl()
  {
    try
    {
      String str = zzbel.zzkk();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzd("Could not delegate getBaseURL to CustomRenderedAd", localRemoteException);
    }
    return null;
  }
  
  public String getContent()
  {
    try
    {
      String str = zzbel.getContent();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzd("Could not delegate getContent to CustomRenderedAd", localRemoteException);
    }
    return null;
  }
  
  public void onAdRendered(View paramView)
  {
    try
    {
      zzdn localzzdn = zzbel;
      if (paramView != null) {}
      for (paramView = zze.zzae(paramView);; paramView = null)
      {
        localzzdn.zzi(paramView);
        return;
      }
      return;
    }
    catch (RemoteException paramView)
    {
      zzb.zzd("Could not delegate onAdRendered to CustomRenderedAd", paramView);
    }
  }
  
  public void recordClick()
  {
    try
    {
      zzbel.recordClick();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzd("Could not delegate recordClick to CustomRenderedAd", localRemoteException);
    }
  }
  
  public void recordImpression()
  {
    try
    {
      zzbel.recordImpression();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzd("Could not delegate recordImpression to CustomRenderedAd", localRemoteException);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzdm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */