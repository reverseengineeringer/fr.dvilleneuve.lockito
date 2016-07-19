package io.fabric.sdk.android.services.events;

public abstract interface EventsStrategy<T>
  extends FileRollOverManager, EventsManager<T>
{
  public abstract FilesSender getFilesSender();
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.events.EventsStrategy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */