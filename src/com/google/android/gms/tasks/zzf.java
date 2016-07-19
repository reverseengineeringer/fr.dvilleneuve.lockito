package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

abstract interface zzf<TResult>
{
  public abstract void cancel();
  
  public abstract void onComplete(@NonNull Task<TResult> paramTask);
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.zzf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */