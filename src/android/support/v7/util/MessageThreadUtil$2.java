package android.support.v7.util;

import android.support.v4.content.ParallelExecutorCompat;
import android.util.Log;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

class MessageThreadUtil$2
  implements ThreadUtil.BackgroundCallback<T>
{
  private static final int LOAD_TILE = 3;
  private static final int RECYCLE_TILE = 4;
  private static final int REFRESH = 1;
  private static final int UPDATE_RANGE = 2;
  private Runnable mBackgroundRunnable = new Runnable()
  {
    public void run()
    {
      for (;;)
      {
        MessageThreadUtil.SyncQueueItem localSyncQueueItem = mQueue.next();
        if (localSyncQueueItem == null)
        {
          mBackgroundRunning.set(false);
          return;
        }
        switch (what)
        {
        default: 
          Log.e("ThreadUtil", "Unsupported message, what=" + what);
          break;
        case 1: 
          mQueue.removeMessages(1);
          val$callback.refresh(arg1);
          break;
        case 2: 
          mQueue.removeMessages(2);
          mQueue.removeMessages(3);
          val$callback.updateRange(arg1, arg2, arg3, arg4, arg5);
          break;
        case 3: 
          val$callback.loadTile(arg1, arg2);
          break;
        case 4: 
          val$callback.recycleTile((TileList.Tile)data);
        }
      }
    }
  };
  AtomicBoolean mBackgroundRunning = new AtomicBoolean(false);
  private final Executor mExecutor = ParallelExecutorCompat.getParallelExecutor();
  private final MessageThreadUtil.MessageQueue mQueue = new MessageThreadUtil.MessageQueue();
  
  MessageThreadUtil$2(MessageThreadUtil paramMessageThreadUtil, ThreadUtil.BackgroundCallback paramBackgroundCallback) {}
  
  private void maybeExecuteBackgroundRunnable()
  {
    if (mBackgroundRunning.compareAndSet(false, true)) {
      mExecutor.execute(mBackgroundRunnable);
    }
  }
  
  private void sendMessage(MessageThreadUtil.SyncQueueItem paramSyncQueueItem)
  {
    mQueue.sendMessage(paramSyncQueueItem);
    maybeExecuteBackgroundRunnable();
  }
  
  private void sendMessageAtFrontOfQueue(MessageThreadUtil.SyncQueueItem paramSyncQueueItem)
  {
    mQueue.sendMessageAtFrontOfQueue(paramSyncQueueItem);
    maybeExecuteBackgroundRunnable();
  }
  
  public void loadTile(int paramInt1, int paramInt2)
  {
    sendMessage(MessageThreadUtil.SyncQueueItem.obtainMessage(3, paramInt1, paramInt2));
  }
  
  public void recycleTile(TileList.Tile<T> paramTile)
  {
    sendMessage(MessageThreadUtil.SyncQueueItem.obtainMessage(4, 0, paramTile));
  }
  
  public void refresh(int paramInt)
  {
    sendMessageAtFrontOfQueue(MessageThreadUtil.SyncQueueItem.obtainMessage(1, paramInt, null));
  }
  
  public void updateRange(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    sendMessageAtFrontOfQueue(MessageThreadUtil.SyncQueueItem.obtainMessage(2, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, null));
  }
}

/* Location:
 * Qualified Name:     android.support.v7.util.MessageThreadUtil.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */