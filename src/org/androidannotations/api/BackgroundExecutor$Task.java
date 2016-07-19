package org.androidannotations.api;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BackgroundExecutor$Task
  implements Runnable
{
  private boolean executionAsked;
  private Future<?> future;
  private String id;
  private AtomicBoolean managed = new AtomicBoolean();
  private int remainingDelay;
  private String serial;
  private long targetTimeMillis;
  
  public BackgroundExecutor$Task(String paramString1, int paramInt, String paramString2)
  {
    if (!"".equals(paramString1)) {
      id = paramString1;
    }
    if (paramInt > 0)
    {
      remainingDelay = paramInt;
      targetTimeMillis = (System.currentTimeMillis() + paramInt);
    }
    if (!"".equals(paramString2)) {
      serial = paramString2;
    }
  }
  
  private void postExecute()
  {
    if ((id == null) && (serial == null)) {
      return;
    }
    BackgroundExecutor.access$700().set(null);
    try
    {
      BackgroundExecutor.access$800().remove(this);
      if (serial != null)
      {
        Task localTask = BackgroundExecutor.access$900(serial);
        if (localTask != null)
        {
          if (remainingDelay != 0) {
            remainingDelay = Math.max(0, (int)(targetTimeMillis - System.currentTimeMillis()));
          }
          BackgroundExecutor.execute(localTask);
        }
      }
      return;
    }
    finally {}
  }
  
  public abstract void execute();
  
  public void run()
  {
    if (managed.getAndSet(true)) {
      return;
    }
    try
    {
      BackgroundExecutor.access$700().set(serial);
      execute();
      return;
    }
    finally
    {
      postExecute();
    }
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.BackgroundExecutor.Task
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */