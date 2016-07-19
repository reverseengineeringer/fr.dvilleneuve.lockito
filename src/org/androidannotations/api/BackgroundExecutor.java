package org.androidannotations.api;

import android.os.Looper;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class BackgroundExecutor
{
  public static Executor DEFAULT_EXECUTOR = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 2);
  public static final WrongThreadListener DEFAULT_WRONG_THREAD_LISTENER;
  private static final String TAG = "BackgroundExecutor";
  private static final ThreadLocal<String> currentSerial = new ThreadLocal();
  private static Executor executor = DEFAULT_EXECUTOR;
  private static final List<Task> tasks;
  private static WrongThreadListener wrongThreadListener;
  
  static
  {
    DEFAULT_WRONG_THREAD_LISTENER = new WrongThreadListener()
    {
      public void onBgExpected(String... paramAnonymousVarArgs)
      {
        if (paramAnonymousVarArgs.length == 0) {
          throw new IllegalStateException("Method invocation is expected from a background thread, but it was called from the UI thread");
        }
        throw new IllegalStateException("Method invocation is expected from one of serials " + Arrays.toString(paramAnonymousVarArgs) + ", but it was called from the UI thread");
      }
      
      public void onUiExpected()
      {
        throw new IllegalStateException("Method invocation is expected from the UI thread");
      }
      
      public void onWrongBgSerial(String paramAnonymousString, String... paramAnonymousVarArgs)
      {
        String str = paramAnonymousString;
        if (paramAnonymousString == null) {
          str = "anonymous";
        }
        throw new IllegalStateException("Method invocation is expected from one of serials " + Arrays.toString(paramAnonymousVarArgs) + ", but it was called from " + str + " serial");
      }
    };
    wrongThreadListener = DEFAULT_WRONG_THREAD_LISTENER;
    tasks = new ArrayList();
  }
  
  public static void cancelAll(String paramString, boolean paramBoolean)
  {
    for (;;)
    {
      int i;
      try
      {
        i = tasks.size() - 1;
        if (i < 0) {
          break label140;
        }
        Task localTask = (Task)tasks.get(i);
        if (!paramString.equals(id)) {
          break label144;
        }
        if (future != null)
        {
          future.cancel(paramBoolean);
          if (!managed.getAndSet(true)) {
            localTask.postExecute();
          }
        }
        else if (executionAsked)
        {
          Log.w("BackgroundExecutor", "A task with id " + id + " cannot be cancelled (the executor set does not support it)");
        }
      }
      finally {}
      tasks.remove(i);
      break label144;
      label140:
      return;
      label144:
      i -= 1;
    }
  }
  
  public static void checkBgThread(String... paramVarArgs)
  {
    if (paramVarArgs.length == 0)
    {
      if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
        wrongThreadListener.onBgExpected(paramVarArgs);
      }
      return;
    }
    String str = (String)currentSerial.get();
    if (str == null)
    {
      wrongThreadListener.onWrongBgSerial(null, paramVarArgs);
      return;
    }
    int j = paramVarArgs.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label79;
      }
      if (paramVarArgs[i].equals(str)) {
        break;
      }
      i += 1;
    }
    label79:
    wrongThreadListener.onWrongBgSerial(str, paramVarArgs);
  }
  
  public static void checkUiThread()
  {
    if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
      wrongThreadListener.onUiExpected();
    }
  }
  
  private static Future<?> directExecute(Runnable paramRunnable, int paramInt)
  {
    if (paramInt > 0)
    {
      if (!(executor instanceof ScheduledExecutorService)) {
        throw new IllegalArgumentException("The executor set does not support scheduling");
      }
      return ((ScheduledExecutorService)executor).schedule(paramRunnable, paramInt, TimeUnit.MILLISECONDS);
    }
    if ((executor instanceof ExecutorService)) {
      return ((ExecutorService)executor).submit(paramRunnable);
    }
    executor.execute(paramRunnable);
    return null;
  }
  
  public static void execute(Runnable paramRunnable)
  {
    directExecute(paramRunnable, 0);
  }
  
  public static void execute(Runnable paramRunnable, int paramInt)
  {
    directExecute(paramRunnable, paramInt);
  }
  
  public static void execute(final Runnable paramRunnable, String paramString1, int paramInt, String paramString2)
  {
    execute(new Task(paramString1, paramInt, paramString2)
    {
      public void execute()
      {
        paramRunnable.run();
      }
    });
  }
  
  public static void execute(Runnable paramRunnable, String paramString1, String paramString2)
  {
    execute(paramRunnable, paramString1, 0, paramString2);
  }
  
  public static void execute(Task paramTask)
  {
    Future localFuture = null;
    try
    {
      if ((serial == null) || (!hasSerialRunning(serial)))
      {
        Task.access$102(paramTask, true);
        localFuture = directExecute(paramTask, remainingDelay);
      }
      if ((id != null) || (serial != null))
      {
        Task.access$402(paramTask, localFuture);
        tasks.add(paramTask);
      }
      return;
    }
    finally {}
  }
  
  private static boolean hasSerialRunning(String paramString)
  {
    Iterator localIterator = tasks.iterator();
    while (localIterator.hasNext())
    {
      Task localTask = (Task)localIterator.next();
      if ((executionAsked) && (paramString.equals(serial))) {
        return true;
      }
    }
    return false;
  }
  
  public static void setExecutor(Executor paramExecutor)
  {
    executor = paramExecutor;
  }
  
  public static void setWrongThreadListener(WrongThreadListener paramWrongThreadListener)
  {
    wrongThreadListener = paramWrongThreadListener;
  }
  
  private static Task take(String paramString)
  {
    int j = tasks.size();
    int i = 0;
    while (i < j)
    {
      if (paramString.equals(tasksgetserial)) {
        return (Task)tasks.remove(i);
      }
      i += 1;
    }
    return null;
  }
  
  public static abstract class Task
    implements Runnable
  {
    private boolean executionAsked;
    private Future<?> future;
    private String id;
    private AtomicBoolean managed = new AtomicBoolean();
    private int remainingDelay;
    private String serial;
    private long targetTimeMillis;
    
    public Task(String paramString1, int paramInt, String paramString2)
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
      BackgroundExecutor.currentSerial.set(null);
      try
      {
        BackgroundExecutor.tasks.remove(this);
        if (serial != null)
        {
          Task localTask = BackgroundExecutor.take(serial);
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
        BackgroundExecutor.currentSerial.set(serial);
        execute();
        return;
      }
      finally
      {
        postExecute();
      }
    }
  }
  
  public static abstract interface WrongThreadListener
  {
    public abstract void onBgExpected(String... paramVarArgs);
    
    public abstract void onUiExpected();
    
    public abstract void onWrongBgSerial(String paramString, String... paramVarArgs);
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.BackgroundExecutor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */