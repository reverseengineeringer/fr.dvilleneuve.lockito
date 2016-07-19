package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;

public final class zzrj
{
  public static final Api<Api.ApiOptions.NoOptions> API = new Api("Common.API", bO, bN);
  public static final Api.zzf<zzrn> bN = new Api.zzf();
  private static final Api.zza<zzrn, Api.ApiOptions.NoOptions> bO = new Api.zza()
  {
    public zzrn zzf(Context paramAnonymousContext, Looper paramAnonymousLooper, zzg paramAnonymouszzg, Api.ApiOptions.NoOptions paramAnonymousNoOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      return new zzrn(paramAnonymousContext, paramAnonymousLooper, paramAnonymouszzg, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
    }
  };
  public static final zzrk zh = new zzrl();
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzrj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */