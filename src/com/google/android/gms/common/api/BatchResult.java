package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzab;
import java.util.concurrent.TimeUnit;

public final class BatchResult
  implements Result
{
  private final Status cc;
  private final PendingResult<?>[] rA;
  
  BatchResult(Status paramStatus, PendingResult<?>[] paramArrayOfPendingResult)
  {
    cc = paramStatus;
    rA = paramArrayOfPendingResult;
  }
  
  public Status getStatus()
  {
    return cc;
  }
  
  public <R extends Result> R take(BatchResultToken<R> paramBatchResultToken)
  {
    if (mId < rA.length) {}
    for (boolean bool = true;; bool = false)
    {
      zzab.zzb(bool, "The result token does not belong to this batch");
      return rA[mId].await(0L, TimeUnit.MILLISECONDS);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.api.BatchResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */