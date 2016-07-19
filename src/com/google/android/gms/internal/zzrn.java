package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzk;

public class zzrn
  extends zzk<zzrp>
{
  public zzrn(Context paramContext, Looper paramLooper, zzg paramzzg, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 39, paramzzg, paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  protected zzrp zzdy(IBinder paramIBinder)
  {
    return zzrp.zza.zzea(paramIBinder);
  }
  
  public String zzra()
  {
    return "com.google.android.gms.common.service.START";
  }
  
  protected String zzrb()
  {
    return "com.google.android.gms.common.internal.service.ICommonService";
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzrn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */