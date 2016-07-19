package com.google.android.gms.common.api;

import com.google.android.gms.internal.zzpt;
import java.util.ArrayList;
import java.util.List;

public final class Batch
  extends zzpt<BatchResult>
{
  private final PendingResult<?>[] rA;
  private int rx;
  private boolean ry;
  private boolean rz;
  private final Object zzail = new Object();
  
  private Batch(List<PendingResult<?>> paramList, GoogleApiClient paramGoogleApiClient)
  {
    super(paramGoogleApiClient);
    rx = paramList.size();
    rA = new PendingResult[rx];
    if (paramList.isEmpty()) {
      zzc(new BatchResult(Status.sg, rA));
    }
    for (;;)
    {
      return;
      int i = 0;
      while (i < paramList.size())
      {
        paramGoogleApiClient = (PendingResult)paramList.get(i);
        rA[i] = paramGoogleApiClient;
        paramGoogleApiClient.zza(new PendingResult.zza()
        {
          public void zzv(Status paramAnonymousStatus)
          {
            for (;;)
            {
              synchronized (Batch.zza(Batch.this))
              {
                if (isCanceled()) {
                  return;
                }
                if (paramAnonymousStatus.isCanceled())
                {
                  Batch.zza(Batch.this, true);
                  Batch.zzb(Batch.this);
                  if (Batch.zzc(Batch.this) == 0)
                  {
                    if (!Batch.zzd(Batch.this)) {
                      break;
                    }
                    Batch.zze(Batch.this);
                  }
                  return;
                }
              }
              if (!paramAnonymousStatus.isSuccess()) {
                Batch.zzb(Batch.this, true);
              }
            }
            if (Batch.zzf(Batch.this)) {}
            for (paramAnonymousStatus = new Status(13);; paramAnonymousStatus = Status.sg)
            {
              zzc(new BatchResult(paramAnonymousStatus, Batch.zzg(Batch.this)));
              break;
            }
          }
        });
        i += 1;
      }
    }
  }
  
  public void cancel()
  {
    super.cancel();
    PendingResult[] arrayOfPendingResult = rA;
    int j = arrayOfPendingResult.length;
    int i = 0;
    while (i < j)
    {
      arrayOfPendingResult[i].cancel();
      i += 1;
    }
  }
  
  public BatchResult createFailedResult(Status paramStatus)
  {
    return new BatchResult(paramStatus, rA);
  }
  
  public static final class Builder
  {
    private GoogleApiClient hb;
    private List<PendingResult<?>> rC = new ArrayList();
    
    public Builder(GoogleApiClient paramGoogleApiClient)
    {
      hb = paramGoogleApiClient;
    }
    
    public <R extends Result> BatchResultToken<R> add(PendingResult<R> paramPendingResult)
    {
      BatchResultToken localBatchResultToken = new BatchResultToken(rC.size());
      rC.add(paramPendingResult);
      return localBatchResultToken;
    }
    
    public Batch build()
    {
      return new Batch(rC, hb, null);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.api.Batch
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */