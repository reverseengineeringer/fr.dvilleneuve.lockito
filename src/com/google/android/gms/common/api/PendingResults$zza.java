package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.internal.zzpt;

final class PendingResults$zza<R extends Result>
  extends zzpt<R>
{
  private final R sb;
  
  public PendingResults$zza(R paramR)
  {
    super(Looper.getMainLooper());
    sb = paramR;
  }
  
  protected R zzc(Status paramStatus)
  {
    if (paramStatus.getStatusCode() != sb.getStatus().getStatusCode()) {
      throw new UnsupportedOperationException("Creating failed results is not supported");
    }
    return sb;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.api.PendingResults.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */