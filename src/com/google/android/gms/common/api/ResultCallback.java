package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public abstract interface ResultCallback<R extends Result>
{
  public abstract void onResult(@NonNull R paramR);
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.api.ResultCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */