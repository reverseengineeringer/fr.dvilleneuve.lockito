package io.fabric.sdk.android.services.concurrency.internal;

public class RetryState
{
  private final Backoff backoff;
  private final int retryCount;
  private final RetryPolicy retryPolicy;
  
  public RetryState(int paramInt, Backoff paramBackoff, RetryPolicy paramRetryPolicy)
  {
    retryCount = paramInt;
    backoff = paramBackoff;
    retryPolicy = paramRetryPolicy;
  }
  
  public RetryState(Backoff paramBackoff, RetryPolicy paramRetryPolicy)
  {
    this(0, paramBackoff, paramRetryPolicy);
  }
  
  public Backoff getBackoff()
  {
    return backoff;
  }
  
  public int getRetryCount()
  {
    return retryCount;
  }
  
  public RetryPolicy getRetryPolicy()
  {
    return retryPolicy;
  }
  
  protected RetryState nextRetryState()
  {
    return new RetryState(retryCount + 1, backoff, retryPolicy);
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.concurrency.internal.RetryState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */