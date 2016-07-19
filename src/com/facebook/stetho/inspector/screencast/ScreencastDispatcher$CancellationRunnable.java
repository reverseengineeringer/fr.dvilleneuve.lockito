package com.facebook.stetho.inspector.screencast;

import android.os.Handler;
import android.os.HandlerThread;

class ScreencastDispatcher$CancellationRunnable
  implements Runnable
{
  private ScreencastDispatcher$CancellationRunnable(ScreencastDispatcher paramScreencastDispatcher) {}
  
  public void run()
  {
    ScreencastDispatcher.access$1800(this$0).interrupt();
    ScreencastDispatcher.access$1700(this$0).removeCallbacks(ScreencastDispatcher.access$1900(this$0));
    ScreencastDispatcher.access$500(this$0).removeCallbacks(ScreencastDispatcher.access$300(this$0));
    ScreencastDispatcher.access$602(this$0, false);
    ScreencastDispatcher.access$1802(this$0, null);
    ScreencastDispatcher.access$802(this$0, null);
    ScreencastDispatcher.access$1002(this$0, null);
    ScreencastDispatcher.access$1302(this$0, null);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.screencast.ScreencastDispatcher.CancellationRunnable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */