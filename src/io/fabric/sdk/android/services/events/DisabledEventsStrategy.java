package io.fabric.sdk.android.services.events;

public class DisabledEventsStrategy<T>
  implements EventsStrategy<T>
{
  public void cancelTimeBasedFileRollOver() {}
  
  public void deleteAllEvents() {}
  
  public FilesSender getFilesSender()
  {
    return null;
  }
  
  public void recordEvent(T paramT) {}
  
  public boolean rollFileOver()
  {
    return false;
  }
  
  public void scheduleTimeBasedRollOverIfNeeded() {}
  
  public void sendEvents() {}
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.events.DisabledEventsStrategy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */