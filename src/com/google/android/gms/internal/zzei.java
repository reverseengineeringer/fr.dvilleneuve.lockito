package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;

@zzir
public class zzei
  extends zzg<zzdx>
{
  public zzei()
  {
    super("com.google.android.gms.ads.NativeAdViewDelegateCreatorImpl");
  }
  
  protected zzdx zzai(IBinder paramIBinder)
  {
    return zzdx.zza.zzaa(paramIBinder);
  }
  
  public zzdw zzb(Context paramContext, FrameLayout paramFrameLayout1, FrameLayout paramFrameLayout2)
  {
    try
    {
      zzd localzzd = zze.zzae(paramContext);
      paramFrameLayout1 = zze.zzae(paramFrameLayout1);
      paramFrameLayout2 = zze.zzae(paramFrameLayout2);
      paramContext = zzdw.zza.zzz(((zzdx)zzcr(paramContext)).zza(localzzd, paramFrameLayout1, paramFrameLayout2, 9256000));
      return paramContext;
    }
    catch (RemoteException paramContext)
    {
      zzb.zzd("Could not create remote NativeAdViewDelegate.", paramContext);
      return null;
    }
    catch (zzg.zza paramContext)
    {
      for (;;) {}
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzei
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */