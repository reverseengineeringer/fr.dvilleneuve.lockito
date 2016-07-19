package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class TaskExecutors$zza
  implements Executor
{
  private final Handler mHandler = new Handler(Looper.getMainLooper());
  
  public void execute(@NonNull Runnable paramRunnable)
  {
    mHandler.post(paramRunnable);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.TaskExecutors.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */