package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public class TaskCompletionSource<TResult>
{
  private final zzh<TResult> aDN = new zzh();
  
  @NonNull
  public Task<TResult> getTask()
  {
    return aDN;
  }
  
  public void setException(@NonNull Exception paramException)
  {
    aDN.setException(paramException);
  }
  
  public void setResult(TResult paramTResult)
  {
    aDN.setResult(paramTResult);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.TaskCompletionSource
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */