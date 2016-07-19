package io.fabric.sdk.android.services.events;

import io.fabric.sdk.android.services.common.CommonUtils;

class EventsHandler$4
  implements Runnable
{
  EventsHandler$4(EventsHandler paramEventsHandler) {}
  
  public void run()
  {
    try
    {
      EventsStrategy localEventsStrategy = this$0.strategy;
      this$0.strategy = this$0.getDisabledEventsStrategy();
      localEventsStrategy.deleteAllEvents();
      return;
    }
    catch (Exception localException)
    {
      CommonUtils.logControlledError(this$0.context, "Failed to disable events.", localException);
    }
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.events.EventsHandler.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */