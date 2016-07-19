package io.fabric.sdk.android.services.events;

import io.fabric.sdk.android.services.common.CommonUtils;

class EventsHandler$3
  implements Runnable
{
  EventsHandler$3(EventsHandler paramEventsHandler) {}
  
  public void run()
  {
    try
    {
      this$0.strategy.sendEvents();
      return;
    }
    catch (Exception localException)
    {
      CommonUtils.logControlledError(this$0.context, "Failed to send events files.", localException);
    }
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.events.EventsHandler.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */