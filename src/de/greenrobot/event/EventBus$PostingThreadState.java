package de.greenrobot.event;

import java.util.ArrayList;
import java.util.List;

final class EventBus$PostingThreadState
{
  boolean canceled;
  Object event;
  List<Object> eventQueue = new ArrayList();
  boolean isMainThread;
  boolean isPosting;
  Subscription subscription;
}

/* Location:
 * Qualified Name:     de.greenrobot.event.EventBus.PostingThreadState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */