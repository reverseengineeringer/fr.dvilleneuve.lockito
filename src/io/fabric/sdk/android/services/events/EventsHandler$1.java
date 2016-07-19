package io.fabric.sdk.android.services.events;

import io.fabric.sdk.android.services.common.CommonUtils;

class EventsHandler$1
  implements Runnable
{
  EventsHandler$1(EventsHandler paramEventsHandler, Object paramObject, boolean paramBoolean) {}
  
  public void run()
  {
    try
    {
      this$0.strategy.recordEvent(val$event);
      if (val$sendImmediately) {
        this$0.strategy.rollFileOver();
      }
      return;
    }
    catch (Exception localException)
    {
      CommonUtils.logControlledError(this$0.context, "Failed to record event.", localException);
    }
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.events.EventsHandler.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */