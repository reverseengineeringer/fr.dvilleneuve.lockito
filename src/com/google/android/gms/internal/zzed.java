package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.List;

@zzir
public class zzed
  implements NativeCustomTemplateAd
{
  private final zzec zzbhl;
  
  public zzed(zzec paramzzec)
  {
    zzbhl = paramzzec;
  }
  
  public List<String> getAvailableAssetNames()
  {
    try
    {
      List localList = zzbhl.getAvailableAssetNames();
      return localList;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to get available asset names.", localRemoteException);
    }
    return null;
  }
  
  public String getCustomTemplateId()
  {
    try
    {
      String str = zzbhl.getCustomTemplateId();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to get custom template id.", localRemoteException);
    }
    return null;
  }
  
  public NativeAd.Image getImage(String paramString)
  {
    try
    {
      paramString = zzbhl.zzav(paramString);
      if (paramString != null)
      {
        paramString = new zzdv(paramString);
        return paramString;
      }
    }
    catch (RemoteException paramString)
    {
      zzb.zzb("Failed to get image.", paramString);
    }
    return null;
  }
  
  public CharSequence getText(String paramString)
  {
    try
    {
      paramString = zzbhl.zzau(paramString);
      return paramString;
    }
    catch (RemoteException paramString)
    {
      zzb.zzb("Failed to get string.", paramString);
    }
    return null;
  }
  
  public void performClick(String paramString)
  {
    try
    {
      zzbhl.performClick(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      zzb.zzb("Failed to perform click.", paramString);
    }
  }
  
  public void recordImpression()
  {
    try
    {
      zzbhl.recordImpression();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to record impression.", localRemoteException);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzed
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */