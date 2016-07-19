package com.google.android.gms.tagmanager;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

class zza$1
  implements zza.zza
{
  zza$1(zza paramzza) {}
  
  public AdvertisingIdClient.Info zzcab()
  {
    try
    {
      AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(zza.zza(auO));
      return localInfo;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      zzbn.zzd("IllegalStateException getting Advertising Id Info", localIllegalStateException);
      return null;
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      zzbn.zzd("GooglePlayServicesRepairableException getting Advertising Id Info", localGooglePlayServicesRepairableException);
      return null;
    }
    catch (IOException localIOException)
    {
      zzbn.zzd("IOException getting Ad Id Info", localIOException);
      return null;
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      zzbn.zzd("GooglePlayServicesNotAvailableException getting Advertising Id Info", localGooglePlayServicesNotAvailableException);
      return null;
    }
    catch (Exception localException)
    {
      zzbn.zzd("Unknown exception. Could not get the Advertising Id Info.", localException);
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zza.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */