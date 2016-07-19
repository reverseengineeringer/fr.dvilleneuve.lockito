package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;

class zzg<TResult>
{
  private Queue<zzf<TResult>> aDL;
  private boolean aDM;
  private final Object zzail = new Object();
  
  public void zza(@NonNull Task<TResult> paramTask)
  {
    for (;;)
    {
      zzf localzzf;
      synchronized (zzail)
      {
        if ((aDL == null) || (aDM)) {
          return;
        }
        aDM = true;
        synchronized (zzail)
        {
          localzzf = (zzf)aDL.poll();
          if (localzzf == null)
          {
            aDM = false;
            return;
          }
        }
      }
      localzzf.onComplete(paramTask);
    }
  }
  
  public void zza(@NonNull zzf<TResult> paramzzf)
  {
    synchronized (zzail)
    {
      if (aDL == null) {
        aDL = new ArrayDeque();
      }
      aDL.add(paramzzf);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.zzg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */