package com.crashlytics.android;

import com.crashlytics.android.internal.models.SessionEventData;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

class CrashlyticsUncaughtExceptionHandler$11
  implements Callable<Boolean>
{
  CrashlyticsUncaughtExceptionHandler$11(CrashlyticsUncaughtExceptionHandler paramCrashlyticsUncaughtExceptionHandler) {}
  
  public Boolean call()
    throws Exception
  {
    if (!CrashlyticsUncaughtExceptionHandler.access$300(this$0).get())
    {
      SessionEventData localSessionEventData = CrashlyticsUncaughtExceptionHandler.access$700(this$0).getExternalCrashEventData();
      if (localSessionEventData != null) {
        CrashlyticsUncaughtExceptionHandler.access$800(this$0, localSessionEventData);
      }
      CrashlyticsUncaughtExceptionHandler.access$900(this$0);
      CrashlyticsUncaughtExceptionHandler.access$600(this$0);
      Fabric.getLogger().d("Fabric", "Open sessions were closed and a new session was opened.");
      return Boolean.valueOf(true);
    }
    Fabric.getLogger().d("Fabric", "Skipping session finalization because a crash has already occurred.");
    return Boolean.valueOf(false);
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.CrashlyticsUncaughtExceptionHandler.11
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */