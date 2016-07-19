package io.fabric.sdk.android.services.events;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public abstract class EnabledEventsStrategy<T>
  implements EventsStrategy<T>
{
  protected static final int UNDEFINED_ROLLOVER_INTERVAL_SECONDS = -1;
  protected final Context context;
  protected final ScheduledExecutorService executorService;
  protected final EventsFilesManager<T> filesManager;
  protected volatile int rolloverIntervalSeconds = -1;
  protected final AtomicReference<ScheduledFuture<?>> scheduledRolloverFutureRef;
  
  public EnabledEventsStrategy(Context paramContext, ScheduledExecutorService paramScheduledExecutorService, EventsFilesManager<T> paramEventsFilesManager)
  {
    context = paramContext;
    executorService = paramScheduledExecutorService;
    filesManager = paramEventsFilesManager;
    scheduledRolloverFutureRef = new AtomicReference();
  }
  
  public void cancelTimeBasedFileRollOver()
  {
    if (scheduledRolloverFutureRef.get() != null)
    {
      CommonUtils.logControlled(context, "Cancelling time-based rollover because no events are currently being generated.");
      ((ScheduledFuture)scheduledRolloverFutureRef.get()).cancel(false);
      scheduledRolloverFutureRef.set(null);
    }
  }
  
  protected void configureRollover(int paramInt)
  {
    rolloverIntervalSeconds = paramInt;
    scheduleTimeBasedFileRollOver(0, rolloverIntervalSeconds);
  }
  
  public void deleteAllEvents()
  {
    filesManager.deleteAllEventsFiles();
  }
  
  public void recordEvent(T paramT)
  {
    CommonUtils.logControlled(context, paramT.toString());
    try
    {
      filesManager.writeEvent(paramT);
      scheduleTimeBasedRollOverIfNeeded();
      return;
    }
    catch (IOException paramT)
    {
      for (;;)
      {
        CommonUtils.logControlledError(context, "Failed to write event.", paramT);
      }
    }
  }
  
  public boolean rollFileOver()
  {
    try
    {
      boolean bool = filesManager.rollFileOver();
      return bool;
    }
    catch (IOException localIOException)
    {
      CommonUtils.logControlledError(context, "Failed to roll file over.", localIOException);
    }
    return false;
  }
  
  protected void scheduleTimeBasedFileRollOver(int paramInt1, int paramInt2)
  {
    try
    {
      TimeBasedFileRollOverRunnable localTimeBasedFileRollOverRunnable = new TimeBasedFileRollOverRunnable(context, this);
      CommonUtils.logControlled(context, "Scheduling time based file roll over every " + paramInt2 + " seconds");
      scheduledRolloverFutureRef.set(executorService.scheduleAtFixedRate(localTimeBasedFileRollOverRunnable, paramInt1, paramInt2, TimeUnit.SECONDS));
      return;
    }
    catch (RejectedExecutionException localRejectedExecutionException)
    {
      CommonUtils.logControlledError(context, "Failed to schedule time based file roll over", localRejectedExecutionException);
    }
  }
  
  public void scheduleTimeBasedRollOverIfNeeded()
  {
    int i;
    if (rolloverIntervalSeconds != -1)
    {
      i = 1;
      if (scheduledRolloverFutureRef.get() != null) {
        break label48;
      }
    }
    label48:
    for (int j = 1;; j = 0)
    {
      if ((i != 0) && (j != 0)) {
        scheduleTimeBasedFileRollOver(rolloverIntervalSeconds, rolloverIntervalSeconds);
      }
      return;
      i = 0;
      break;
    }
  }
  
  void sendAndCleanUpIfSuccess()
  {
    FilesSender localFilesSender = getFilesSender();
    if (localFilesSender == null)
    {
      CommonUtils.logControlled(context, "skipping files send because we don't yet know the target endpoint");
      return;
    }
    CommonUtils.logControlled(context, "Sending all files");
    i = 0;
    int j = 0;
    List localList = filesManager.getBatchOfFilesToSend();
    for (;;)
    {
      try
      {
        CommonUtils.logControlled(context, String.format(Locale.US, "attempt to send batch of %d files", new Object[] { Integer.valueOf(localList.size()) }));
        k = j;
        i = j;
        if (localList.size() > 0)
        {
          i = j;
          boolean bool = localFilesSender.send(localList);
          k = j;
          if (bool)
          {
            i = j;
            k = j + localList.size();
            i = k;
            filesManager.deleteSentFiles(localList);
          }
          if (bool) {
            continue;
          }
        }
      }
      catch (Exception localException)
      {
        CommonUtils.logControlledError(context, "Failed to send batch of analytics files to server: " + localException.getMessage(), localException);
        int k = i;
        continue;
      }
      if (k != 0) {
        break;
      }
      filesManager.deleteOldestInRollOverIfOverMax();
      return;
      i = k;
      localList = filesManager.getBatchOfFilesToSend();
      j = k;
    }
  }
  
  public void sendEvents()
  {
    sendAndCleanUpIfSuccess();
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.events.EnabledEventsStrategy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */