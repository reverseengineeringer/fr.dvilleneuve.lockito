package de.greenrobot.event;

import java.util.List;

abstract interface EventBus$PostCallback
{
  public abstract void onPostCompleted(List<SubscriberExceptionEvent> paramList);
}

/* Location:
 * Qualified Name:     de.greenrobot.event.EventBus.PostCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */