package de.greenrobot.event.util;

import android.util.Log;
import de.greenrobot.event.EventBus;
import java.lang.reflect.Constructor;

class AsyncExecutor$1
  implements Runnable
{
  AsyncExecutor$1(AsyncExecutor paramAsyncExecutor, AsyncExecutor.RunnableEx paramRunnableEx) {}
  
  public void run()
  {
    try
    {
      val$runnable.run();
      return;
    }
    catch (Exception localException1)
    {
      try
      {
        Object localObject = AsyncExecutor.access$200(this$0).newInstance(new Object[] { localException1 });
        if ((localException1 instanceof HasExecutionScope)) {
          ((HasExecutionScope)localException1).setExecutionScope(AsyncExecutor.access$300(this$0));
        }
        AsyncExecutor.access$400(this$0).post(localObject);
        return;
      }
      catch (Exception localException2)
      {
        Log.e(EventBus.TAG, "Original exception:", localException1);
        throw new RuntimeException("Could not create failure event", localException2);
      }
    }
  }
}

/* Location:
 * Qualified Name:     de.greenrobot.event.util.AsyncExecutor.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */