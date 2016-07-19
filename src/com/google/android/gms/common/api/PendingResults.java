package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzqv;
import com.google.android.gms.internal.zzqz;

public final class PendingResults
{
  public static PendingResult<Status> canceledPendingResult()
  {
    zzqz localzzqz = new zzqz(Looper.getMainLooper());
    localzzqz.cancel();
    return localzzqz;
  }
  
  public static <R extends Result> PendingResult<R> canceledPendingResult(R paramR)
  {
    zzab.zzb(paramR, "Result must not be null");
    if (paramR.getStatus().getStatusCode() == 16) {}
    for (boolean bool = true;; bool = false)
    {
      zzab.zzb(bool, "Status code must be CommonStatusCodes.CANCELED");
      paramR = new zza(paramR);
      paramR.cancel();
      return paramR;
    }
  }
  
  public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R paramR)
  {
    zzab.zzb(paramR, "Result must not be null");
    zzc localzzc = new zzc(null);
    localzzc.zzc(paramR);
    return new zzqv(localzzc);
  }
  
  public static PendingResult<Status> immediatePendingResult(Status paramStatus)
  {
    zzab.zzb(paramStatus, "Result must not be null");
    zzqz localzzqz = new zzqz(Looper.getMainLooper());
    localzzqz.zzc(paramStatus);
    return localzzqz;
  }
  
  public static <R extends Result> PendingResult<R> zza(R paramR, GoogleApiClient paramGoogleApiClient)
  {
    zzab.zzb(paramR, "Result must not be null");
    if (!paramR.getStatus().isSuccess()) {}
    for (boolean bool = true;; bool = false)
    {
      zzab.zzb(bool, "Status code must not be SUCCESS");
      paramGoogleApiClient = new zzb(paramGoogleApiClient, paramR);
      paramGoogleApiClient.zzc(paramR);
      return paramGoogleApiClient;
    }
  }
  
  public static PendingResult<Status> zza(Status paramStatus, GoogleApiClient paramGoogleApiClient)
  {
    zzab.zzb(paramStatus, "Result must not be null");
    paramGoogleApiClient = new zzqz(paramGoogleApiClient);
    paramGoogleApiClient.zzc(paramStatus);
    return paramGoogleApiClient;
  }
  
  public static <R extends Result> OptionalPendingResult<R> zzb(R paramR, GoogleApiClient paramGoogleApiClient)
  {
    zzab.zzb(paramR, "Result must not be null");
    paramGoogleApiClient = new zzc(paramGoogleApiClient);
    paramGoogleApiClient.zzc(paramR);
    return new zzqv(paramGoogleApiClient);
  }
  
  private static final class zza<R extends Result>
    extends zzpt<R>
  {
    private final R sb;
    
    public zza(R paramR)
    {
      super();
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
  
  private static final class zzb<R extends Result>
    extends zzpt<R>
  {
    private final R sc;
    
    public zzb(GoogleApiClient paramGoogleApiClient, R paramR)
    {
      super();
      sc = paramR;
    }
    
    protected R zzc(Status paramStatus)
    {
      return sc;
    }
  }
  
  private static final class zzc<R extends Result>
    extends zzpt<R>
  {
    public zzc(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    protected R zzc(Status paramStatus)
    {
      throw new UnsupportedOperationException("Creating failed results is not supported");
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.api.PendingResults
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */