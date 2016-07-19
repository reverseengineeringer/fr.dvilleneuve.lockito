package de.greenrobot.event;

public final class NoSubscriberEvent
{
  public final EventBus eventBus;
  public final Object originalEvent;
  
  public NoSubscriberEvent(EventBus paramEventBus, Object paramObject)
  {
    eventBus = paramEventBus;
    originalEvent = paramObject;
  }
}

/* Location:
 * Qualified Name:     de.greenrobot.event.NoSubscriberEvent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */