package com.squareup.picasso;

import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class Dispatcher$DispatcherHandler
  extends Handler
{
  public Dispatcher$DispatcherHandler(Dispatcher paramDispatcher, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    boolean bool = true;
    switch (what)
    {
    case 3: 
    case 8: 
    default: 
      throw new AssertionError("Unknown handler message received: " + what);
    case 1: 
      paramMessage = (Action)obj;
      this$0.performSubmit(paramMessage);
      return;
    case 2: 
      paramMessage = (Action)obj;
      this$0.performCancel(paramMessage);
      return;
    case 4: 
      paramMessage = (BitmapHunter)obj;
      this$0.performComplete(paramMessage);
      return;
    case 5: 
      paramMessage = (BitmapHunter)obj;
      this$0.performRetry(paramMessage);
      return;
    case 6: 
      paramMessage = (BitmapHunter)obj;
      this$0.performError(paramMessage);
      return;
    case 7: 
      this$0.performBatchComplete();
      return;
    case 9: 
      paramMessage = (NetworkInfo)obj;
      this$0.performNetworkStateChange(paramMessage);
      return;
    }
    Dispatcher localDispatcher = this$0;
    if (arg1 == 1) {}
    for (;;)
    {
      localDispatcher.performAirplaneModeChange(bool);
      return;
      bool = false;
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.Dispatcher.DispatcherHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */