package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;
import com.google.android.gms.internal.zzir;

@zzir
public class zzai
  extends zzg<zzaa>
{
  public zzai()
  {
    super("com.google.android.gms.ads.MobileAdsSettingManagerCreatorImpl");
  }
  
  public zzz zzm(Context paramContext)
  {
    try
    {
      zzd localzzd = zze.zzae(paramContext);
      paramContext = zzz.zza.zzr(((zzaa)zzcr(paramContext)).zza(localzzd, 9256000));
      return paramContext;
    }
    catch (RemoteException paramContext)
    {
      zzb.zzd("Could not get remote MobileAdsSettingManager.", paramContext);
      return null;
    }
    catch (zzg.zza paramContext)
    {
      zzb.zzd("Could not get remote MobileAdsSettingManager.", paramContext);
    }
    return null;
  }
  
  protected zzaa zzv(IBinder paramIBinder)
  {
    return zzaa.zza.zzs(paramIBinder);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.internal.client.zzai
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */