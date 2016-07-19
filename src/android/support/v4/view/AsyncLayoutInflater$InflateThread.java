package android.support.v4.view;

import android.support.v4.util.Pools.SynchronizedPool;
import java.util.concurrent.ArrayBlockingQueue;

class AsyncLayoutInflater$InflateThread
  extends Thread
{
  private static final InflateThread sInstance = new InflateThread();
  private ArrayBlockingQueue<AsyncLayoutInflater.InflateRequest> mQueue = new ArrayBlockingQueue(10);
  private Pools.SynchronizedPool<AsyncLayoutInflater.InflateRequest> mRequestPool = new Pools.SynchronizedPool(10);
  
  static
  {
    sInstance.start();
  }
  
  public static InflateThread getInstance()
  {
    return sInstance;
  }
  
  public void enqueue(AsyncLayoutInflater.InflateRequest paramInflateRequest)
  {
    try
    {
      mQueue.put(paramInflateRequest);
      return;
    }
    catch (InterruptedException paramInflateRequest)
    {
      throw new RuntimeException("Failed to enqueue async inflate request", paramInflateRequest);
    }
  }
  
  public AsyncLayoutInflater.InflateRequest obtainRequest()
  {
    AsyncLayoutInflater.InflateRequest localInflateRequest2 = (AsyncLayoutInflater.InflateRequest)mRequestPool.acquire();
    AsyncLayoutInflater.InflateRequest localInflateRequest1 = localInflateRequest2;
    if (localInflateRequest2 == null) {
      localInflateRequest1 = new AsyncLayoutInflater.InflateRequest(null);
    }
    return localInflateRequest1;
  }
  
  public void releaseRequest(AsyncLayoutInflater.InflateRequest paramInflateRequest)
  {
    callback = null;
    inflater = null;
    parent = null;
    resid = 0;
    view = null;
    mRequestPool.release(paramInflateRequest);
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 34	android/support/v4/view/AsyncLayoutInflater$InflateThread:mQueue	Ljava/util/concurrent/ArrayBlockingQueue;
    //   4: invokevirtual 96	java/util/concurrent/ArrayBlockingQueue:take	()Ljava/lang/Object;
    //   7: checkcast 64	android/support/v4/view/AsyncLayoutInflater$InflateRequest
    //   10: astore_1
    //   11: aload_1
    //   12: aload_1
    //   13: getfield 76	android/support/v4/view/AsyncLayoutInflater$InflateRequest:inflater	Landroid/support/v4/view/AsyncLayoutInflater;
    //   16: invokestatic 100	android/support/v4/view/AsyncLayoutInflater:access$000	(Landroid/support/v4/view/AsyncLayoutInflater;)Landroid/view/LayoutInflater;
    //   19: aload_1
    //   20: getfield 84	android/support/v4/view/AsyncLayoutInflater$InflateRequest:resid	I
    //   23: aload_1
    //   24: getfield 80	android/support/v4/view/AsyncLayoutInflater$InflateRequest:parent	Landroid/view/ViewGroup;
    //   27: iconst_0
    //   28: invokevirtual 106	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;Z)Landroid/view/View;
    //   31: putfield 88	android/support/v4/view/AsyncLayoutInflater$InflateRequest:view	Landroid/view/View;
    //   34: aload_1
    //   35: getfield 76	android/support/v4/view/AsyncLayoutInflater$InflateRequest:inflater	Landroid/support/v4/view/AsyncLayoutInflater;
    //   38: invokestatic 110	android/support/v4/view/AsyncLayoutInflater:access$200	(Landroid/support/v4/view/AsyncLayoutInflater;)Landroid/os/Handler;
    //   41: iconst_0
    //   42: aload_1
    //   43: invokestatic 116	android/os/Message:obtain	(Landroid/os/Handler;ILjava/lang/Object;)Landroid/os/Message;
    //   46: invokevirtual 119	android/os/Message:sendToTarget	()V
    //   49: goto -49 -> 0
    //   52: astore_1
    //   53: ldc 121
    //   55: aload_1
    //   56: invokestatic 127	android/util/Log:w	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   59: pop
    //   60: goto -60 -> 0
    //   63: astore_2
    //   64: ldc 121
    //   66: ldc -127
    //   68: aload_2
    //   69: invokestatic 132	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   72: pop
    //   73: goto -39 -> 34
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	76	0	this	InflateThread
    //   10	33	1	localInflateRequest	AsyncLayoutInflater.InflateRequest
    //   52	4	1	localInterruptedException	InterruptedException
    //   63	6	2	localRuntimeException	RuntimeException
    // Exception table:
    //   from	to	target	type
    //   0	11	52	java/lang/InterruptedException
    //   11	34	63	java/lang/RuntimeException
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.AsyncLayoutInflater.InflateThread
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */