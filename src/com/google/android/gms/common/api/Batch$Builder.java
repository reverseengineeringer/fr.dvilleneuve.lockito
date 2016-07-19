package com.google.android.gms.common.api;

import java.util.ArrayList;
import java.util.List;

public final class Batch$Builder
{
  private GoogleApiClient hb;
  private List<PendingResult<?>> rC = new ArrayList();
  
  public Batch$Builder(GoogleApiClient paramGoogleApiClient)
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

/* Location:
 * Qualified Name:     com.google.android.gms.common.api.Batch.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */