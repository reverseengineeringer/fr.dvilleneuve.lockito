package android.support.v7.widget;

import android.view.View;
import android.view.ViewParent;

class ForwardingListener$DisallowIntercept
  implements Runnable
{
  private ForwardingListener$DisallowIntercept(ForwardingListener paramForwardingListener) {}
  
  public void run()
  {
    ForwardingListener.access$200(this$0).getParent().requestDisallowInterceptTouchEvent(true);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ForwardingListener.DisallowIntercept
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */