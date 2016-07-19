package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdw;
import com.google.android.gms.internal.zzei;

class zzl$6
  extends zzl.zza<zzdw>
{
  zzl$6(zzl paramzzl, FrameLayout paramFrameLayout1, FrameLayout paramFrameLayout2, Context paramContext)
  {
    super(paramzzl, null);
  }
  
  public zzdw zze(zzx paramzzx)
    throws RemoteException
  {
    return paramzzx.createNativeAdViewDelegate(zze.zzae(zzavi), zze.zzae(zzavj));
  }
  
  public zzdw zziq()
  {
    zzdw localzzdw = zzl.zze(zzavh).zzb(zzaky, zzavi, zzavj);
    if (localzzdw != null) {
      return localzzdw;
    }
    zzl.zza(zzavh, zzaky, "native_ad_view_delegate");
    return new zzam();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.internal.client.zzl.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */