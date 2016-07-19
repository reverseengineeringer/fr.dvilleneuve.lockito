package android.support.v4.view;

import android.os.Handler.Callback;
import android.os.Message;
import android.view.LayoutInflater;

class AsyncLayoutInflater$1
  implements Handler.Callback
{
  AsyncLayoutInflater$1(AsyncLayoutInflater paramAsyncLayoutInflater) {}
  
  public boolean handleMessage(Message paramMessage)
  {
    paramMessage = (AsyncLayoutInflater.InflateRequest)obj;
    if (view == null) {
      view = AsyncLayoutInflater.access$000(this$0).inflate(resid, parent, false);
    }
    callback.onInflateFinished(view, resid, parent);
    AsyncLayoutInflater.access$100(this$0).releaseRequest(paramMessage);
    return true;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.AsyncLayoutInflater.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */