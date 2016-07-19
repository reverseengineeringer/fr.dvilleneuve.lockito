package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class TaskExecutors$1
  implements Executor
{
  public void execute(@NonNull Runnable paramRunnable)
  {
    paramRunnable.run();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.TaskExecutors.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */