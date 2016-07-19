package com.google.android.gms.common.api;

class Batch$1
  implements PendingResult.zza
{
  Batch$1(Batch paramBatch) {}
  
  public void zzv(Status paramStatus)
  {
    for (;;)
    {
      synchronized (Batch.zza(rB))
      {
        if (rB.isCanceled()) {
          return;
        }
        if (paramStatus.isCanceled())
        {
          Batch.zza(rB, true);
          Batch.zzb(rB);
          if (Batch.zzc(rB) == 0)
          {
            if (!Batch.zzd(rB)) {
              break;
            }
            Batch.zze(rB);
          }
          return;
        }
      }
      if (!paramStatus.isSuccess()) {
        Batch.zzb(rB, true);
      }
    }
    if (Batch.zzf(rB)) {}
    for (paramStatus = new Status(13);; paramStatus = Status.sg)
    {
      rB.zzc(new BatchResult(paramStatus, Batch.zzg(rB)));
      break;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.api.Batch.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */