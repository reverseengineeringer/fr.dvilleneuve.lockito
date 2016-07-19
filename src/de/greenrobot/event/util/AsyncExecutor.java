package de.greenrobot.event.util;

import android.app.Activity;
import android.util.Log;
import de.greenrobot.event.EventBus;
import java.lang.reflect.Constructor;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsyncExecutor
{
  private final EventBus eventBus;
  private final Constructor<?> failureEventConstructor;
  private Object scope;
  private final Executor threadPool;
  
  private AsyncExecutor(Executor paramExecutor, EventBus paramEventBus, Class<?> paramClass, Object paramObject)
  {
    threadPool = paramExecutor;
    eventBus = paramEventBus;
    scope = paramObject;
    try
    {
      failureEventConstructor = paramClass.getConstructor(new Class[] { Throwable.class });
      return;
    }
    catch (NoSuchMethodException paramExecutor)
    {
      throw new RuntimeException("Failure event class must have a constructor with one parameter of type Throwable", paramExecutor);
    }
  }
  
  public static Builder builder()
  {
    return new Builder(null);
  }
  
  public static AsyncExecutor create()
  {
    return new Builder(null).build();
  }
  
  public void execute(final RunnableEx paramRunnableEx)
  {
    threadPool.execute(new Runnable()
    {
      public void run()
      {
        try
        {
          paramRunnableEx.run();
          return;
        }
        catch (Exception localException1)
        {
          try
          {
            Object localObject = failureEventConstructor.newInstance(new Object[] { localException1 });
            if ((localException1 instanceof HasExecutionScope)) {
              ((HasExecutionScope)localException1).setExecutionScope(scope);
            }
            eventBus.post(localObject);
            return;
          }
          catch (Exception localException2)
          {
            Log.e(EventBus.TAG, "Original exception:", localException1);
            throw new RuntimeException("Could not create failure event", localException2);
          }
        }
      }
    });
  }
  
  public static class Builder
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
  
  public static abstract interface RunnableEx
  {
    public abstract void run()
      throws Exception;
  }
}

/* Location:
 * Qualified Name:     de.greenrobot.event.util.AsyncExecutor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */