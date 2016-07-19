package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.purchase.InAppPurchase;

@zzir
public class zzia
  implements InAppPurchase
{
  private final zzhr zzbxf;
  
  public zzia(zzhr paramzzhr)
  {
    zzbxf = paramzzhr;
  }
  
  public String getProductId()
  {
    try
    {
      String str = zzbxf.getProductId();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzd("Could not forward getProductId to InAppPurchase", localRemoteException);
    }
    return null;
  }
  
  public void recordPlayBillingResolution(int paramInt)
  {
    try
    {
      zzbxf.recordPlayBillingResolution(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzd("Could not forward recordPlayBillingResolution to InAppPurchase", localRemoteException);
    }
  }
  
  public void recordResolution(int paramInt)
  {
    try
    {
      zzbxf.recordResolution(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzd("Could not forward recordResolution to InAppPurchase", localRemoteException);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzia
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */