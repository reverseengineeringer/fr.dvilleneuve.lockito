package com.joanzapata.iconify.internal;

import android.support.v4.view.ViewCompat;
import android.widget.TextView;

public class HasOnViewAttachListener$HasOnViewAttachListenerDelegate
{
  private HasOnViewAttachListener.OnViewAttachListener listener;
  private final TextView view;
  
  public HasOnViewAttachListener$HasOnViewAttachListenerDelegate(TextView paramTextView)
  {
    view = paramTextView;
  }
  
  public void onAttachedToWindow()
  {
    if (listener != null) {
      listener.onAttach();
    }
  }
  
  public void onDetachedFromWindow()
  {
    if (listener != null) {
      listener.onDetach();
    }
  }
  
  public void setOnViewAttachListener(HasOnViewAttachListener.OnViewAttachListener paramOnViewAttachListener)
  {
    if (listener != null) {
      listener.onDetach();
    }
    listener = paramOnViewAttachListener;
    if ((ViewCompat.isAttachedToWindow(view)) && (paramOnViewAttachListener != null)) {
      paramOnViewAttachListener.onAttach();
    }
  }
}

/* Location:
 * Qualified Name:     com.joanzapata.iconify.internal.HasOnViewAttachListener.HasOnViewAttachListenerDelegate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */