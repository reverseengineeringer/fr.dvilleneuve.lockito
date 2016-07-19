package io.fabric.sdk.android.services.events;

import io.fabric.sdk.android.services.common.CommonUtils;

class EventsHandler$2
  implements Runnable
{
  EventsHandler$2(EventsHandler paramEventsHandler, Object paramObject) {}
  
  public void run()
  {
    try
    {
      this$0.strategy.recordEvent(val$event);
      return;
    }
    catch (Exception localException)
    {
      CommonUtils.logControlledError(this$0.context, "Crashlytics failed to record event", localException);
    }
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.events.EventsHandler.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */