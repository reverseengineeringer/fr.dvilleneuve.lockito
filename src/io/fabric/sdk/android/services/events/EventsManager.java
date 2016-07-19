package io.fabric.sdk.android.services.events;

public abstract interface EventsManager<T>
{
  public abstract void deleteAllEvents();
  
  public abstract void recordEvent(T paramT);
  
  public abstract void sendEvents();
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.events.EventsManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */