package io.fabric.sdk.android.services.concurrency.internal;

public class ExponentialBackoff
  implements Backoff
{
  private final long constant;
  
  public ExponentialBackoff(long paramLong)
  {
    constant = paramLong;
  }
  
  public long getDelayMillis(int paramInt)
  {
    return (constant * Math.pow(2.0D, paramInt));
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.concurrency.internal.ExponentialBackoff
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */