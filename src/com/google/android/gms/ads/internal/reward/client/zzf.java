package com.google.android.gms.ads.internal.reward.client;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;
import com.google.android.gms.internal.zzgn;
import com.google.android.gms.internal.zzir;

@zzir
public class zzf
  extends zzg<zzc>
{
  public zzf()
  {
    super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
  }
  
  public zzb zzb(Context paramContext, zzgn paramzzgn)
  {
    try
    {
      zzd localzzd = zze.zzae(paramContext);
      paramContext = zzb.zza.zzbf(((zzc)zzcr(paramContext)).zza(localzzd, paramzzgn, 9256000));
      return paramContext;
    }
    catch (RemoteException paramContext)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not get remote RewardedVideoAd.", paramContext);
      return null;
    }
    catch (zzg.zza paramContext)
    {
      for (;;) {}
    }
  }
  
  protected zzc zzbi(IBinder paramIBinder)
  {
    return zzc.zza.zzbg(paramIBinder);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.internal.reward.client.zzf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */