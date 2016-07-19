package io.fabric.sdk.android.services.concurrency.internal;

public abstract interface RetryPolicy
{
  public abstract boolean shouldRetry(int paramInt, Throwable paramThrowable);
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.concurrency.internal.RetryPolicy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */