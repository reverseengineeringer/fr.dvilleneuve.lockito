package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class zzrm<R extends Result>
  extends zzpr.zza<R, zzrn>
{
  public zzrm(GoogleApiClient paramGoogleApiClient)
  {
    super(zzrj.API, paramGoogleApiClient);
  }
  
  static abstract class zza
    extends zzrm<Status>
  {
    public zza(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public Status zzb(Status paramStatus)
    {
      return paramStatus;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzrm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */