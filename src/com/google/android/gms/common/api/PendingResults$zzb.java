package com.google.android.gms.common.api;

import com.google.android.gms.internal.zzpt;

final class PendingResults$zzb<R extends Result>
  extends zzpt<R>
{
  private final R sc;
  
  public PendingResults$zzb(GoogleApiClient paramGoogleApiClient, R paramR)
  {
    super(paramGoogleApiClient);
    sc = paramR;
  }
  
  protected R zzc(Status paramStatus)
  {
    return sc;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.api.PendingResults.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */