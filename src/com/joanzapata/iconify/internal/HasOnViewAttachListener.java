package com.joanzapata.iconify.internal;

import android.support.v4.view.ViewCompat;
import android.widget.TextView;

public abstract interface HasOnViewAttachListener
{
  public abstract void setOnViewAttachListener(OnViewAttachListener paramOnViewAttachListener);
  
  public static class HasOnViewAttachListenerDelegate
  {
    private HasOnViewAttachListener.OnViewAttachListener listener;
    private final TextView view;
    
    public HasOnViewAttachListenerDelegate(TextView paramTextView)
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
  
  public static abstract interface OnViewAttachListener
  {
    public abstract void onAttach();
    
    public abstract void onDetach();
  }
}

/* Location:
 * Qualified Name:     com.joanzapata.iconify.internal.HasOnViewAttachListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */