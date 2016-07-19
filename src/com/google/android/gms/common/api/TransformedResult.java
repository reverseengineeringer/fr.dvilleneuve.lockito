package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public abstract class TransformedResult<R extends Result>
{
  public abstract void andFinally(@NonNull ResultCallbacks<? super R> paramResultCallbacks);
  
  @NonNull
  public abstract <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> paramResultTransform);
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.api.TransformedResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */