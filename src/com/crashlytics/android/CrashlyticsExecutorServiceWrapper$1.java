package com.crashlytics.android;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;

class CrashlyticsExecutorServiceWrapper$1
  implements Runnable
{
  CrashlyticsExecutorServiceWrapper$1(CrashlyticsExecutorServiceWrapper paramCrashlyticsExecutorServiceWrapper, Runnable paramRunnable) {}
  
  public void run()
  {
    try
    {
      val$runnable.run();
      return;
    }
    catch (Exception localException)
    {
      Fabric.getLogger().e("Fabric", "Failed to execute task.", localException);
    }
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.CrashlyticsExecutorServiceWrapper.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */