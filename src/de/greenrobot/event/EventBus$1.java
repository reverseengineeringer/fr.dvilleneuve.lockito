package de.greenrobot.event;

class EventBus$1
  extends ThreadLocal<EventBus.PostingThreadState>
{
  EventBus$1(EventBus paramEventBus) {}
  
  protected EventBus.PostingThreadState initialValue()
  {
    return new EventBus.PostingThreadState();
  }
}

/* Location:
 * Qualified Name:     de.greenrobot.event.EventBus.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */