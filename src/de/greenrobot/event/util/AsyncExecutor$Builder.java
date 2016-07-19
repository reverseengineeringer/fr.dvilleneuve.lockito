package de.greenrobot.event.util;

import android.app.Activity;
import de.greenrobot.event.EventBus;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsyncExecutor$Builder
{
  private EventBus eventBus;
  private Class<?> failureEventType;
  private Executor threadPool;
  
  public AsyncExecutor build()
  {
    return buildForScope(null);
  }
  
  public AsyncExecutor buildForActivityScope(Activity paramActivity)
  {
    return buildForScope(paramActivity.getClass());
  }
  
  public AsyncExecutor buildForScope(Object paramObject)
  {
    if (eventBus == null) {
      eventBus = EventBus.getDefault();
    }
    if (threadPool == null) {
      threadPool = Executors.newCachedThreadPool();
    }
    if (failureEventType == null) {
      failureEventType = ThrowableFailureEvent.class;
    }
    return new AsyncExecutor(threadPool, eventBus, failureEventType, paramObject, null);
  }
  
  public Builder eventBus(EventBus paramEventBus)
  {
    eventBus = paramEventBus;
    return this;
  }
  
  public Builder failureEventType(Class<?> paramClass)
  {
    failureEventType = paramClass;
    return this;
  }
  
  public Builder threadPool(Executor paramExecutor)
  {
    threadPool = paramExecutor;
    return this;
  }
}

/* Location:
 * Qualified Name:     de.greenrobot.event.util.AsyncExecutor.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */