package io.fabric.sdk.android.services.concurrency.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class RetryFuture<T>
  extends AbstractFuture<T>
  implements Runnable
{
  private final RetryThreadPoolExecutor executor;
  RetryState retryState;
  private final AtomicReference<Thread> runner;
  private final Callable<T> task;
  
  RetryFuture(Callable<T> paramCallable, RetryState paramRetryState, RetryThreadPoolExecutor paramRetryThreadPoolExecutor)
  {
    task = paramCallable;
    retryState = paramRetryState;
    executor = paramRetryThreadPoolExecutor;
    runner = new AtomicReference();
  }
  
  private Backoff getBackoff()
  {
    return retryState.getBackoff();
  }
  
  private int getRetryCount()
  {
    return retryState.getRetryCount();
  }
  
  private RetryPolicy getRetryPolicy()
  {
    return retryState.getRetryPolicy();
  }
  
  protected void interruptTask()
  {
    Thread localThread = (Thread)runner.getAndSet(null);
    if (localThread != null) {
      localThread.interrupt();
    }
  }
  
  public void run()
  {
    if ((isDone()) || (!runner.compareAndSet(null, Thread.currentThread()))) {
      return;
    }
    try
    {
      set(task.call());
      return;
    }
    catch (Throwable localThrowable)
    {
      if (getRetryPolicy().shouldRetry(getRetryCount(), localThrowable))
      {
        long l = getBackoff().getDelayMillis(getRetryCount());
        retryState = retryState.nextRetryState();
        executor.schedule(this, l, TimeUnit.MILLISECONDS);
      }
      for (;;)
      {
        return;
        setException(localThrowable);
      }
    }
    finally
    {
      runner.getAndSet(null);
    }
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.concurrency.internal.RetryFuture
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */