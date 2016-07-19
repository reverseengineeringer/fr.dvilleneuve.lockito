package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.ExecutionException;

final class Tasks$zzc
  implements Tasks.zzb
{
  private final zzh<Void> aDN;
  private Exception aDS;
  private final int aDU;
  private int aDV;
  private int aDW;
  private final Object zzail = new Object();
  
  public Tasks$zzc(int paramInt, zzh<Void> paramzzh)
  {
    aDU = paramInt;
    aDN = paramzzh;
  }
  
  private void zzchl()
  {
    if (aDV + aDW == aDU)
    {
      if (aDS == null) {
        aDN.setResult(null);
      }
    }
    else {
      return;
    }
    zzh localzzh = aDN;
    int i = aDW;
    int j = aDU;
    localzzh.setException(new ExecutionException(54 + i + " out of " + j + " underlying tasks failed", aDS));
  }
  
  public void onFailure(@NonNull Exception paramException)
  {
    synchronized (zzail)
    {
      aDW += 1;
      aDS = paramException;
      zzchl();
      return;
    }
  }
  
  public void onSuccess(Object arg1)
  {
    synchronized (zzail)
    {
      aDV += 1;
      zzchl();
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.Tasks.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */