package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public abstract interface OnCompleteListener<TResult>
{
  public abstract void onComplete(@NonNull Task<TResult> paramTask);
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.OnCompleteListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */