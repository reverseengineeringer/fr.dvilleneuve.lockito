package de.greenrobot.event;

import java.util.concurrent.ExecutorService;

class AsyncPoster
  implements Runnable
{
  private final EventBus eventBus;
  private final PendingPostQueue queue;
  
  AsyncPoster(EventBus paramEventBus)
  {
    eventBus = paramEventBus;
    queue = new PendingPostQueue();
  }
  
  public void enqueue(Subscription paramSubscription, Object paramObject)
  {
    paramSubscription = PendingPost.obtainPendingPost(paramSubscription, paramObject);
    queue.enqueue(paramSubscription);
    EventBus.executorService.execute(this);
  }
  
  public void run()
  {
    PendingPost localPendingPost = queue.poll();
    if (localPendingPost == null) {
      throw new IllegalStateException("No pending post available");
    }
    eventBus.invokeSubscriber(localPendingPost);
  }
}

/* Location:
 * Qualified Name:     de.greenrobot.event.AsyncPoster
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */