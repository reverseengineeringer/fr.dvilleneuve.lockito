package com.joanzapata.iconify.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.internal.HasOnViewAttachListener;
import com.joanzapata.iconify.internal.HasOnViewAttachListener.HasOnViewAttachListenerDelegate;
import com.joanzapata.iconify.internal.HasOnViewAttachListener.OnViewAttachListener;

public class IconTextView
  extends TextView
  implements HasOnViewAttachListener
{
  private HasOnViewAttachListener.HasOnViewAttachListenerDelegate delegate;
  
  public IconTextView(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  public IconTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  public IconTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  private void init()
  {
    setTransformationMethod(null);
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    delegate.onAttachedToWindow();
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    delegate.onDetachedFromWindow();
  }
  
  public void setOnViewAttachListener(HasOnViewAttachListener.OnViewAttachListener paramOnViewAttachListener)
  {
    if (delegate == null) {
      delegate = new HasOnViewAttachListener.HasOnViewAttachListenerDelegate(this);
    }
    delegate.setOnViewAttachListener(paramOnViewAttachListener);
  }
  
  public void setText(CharSequence paramCharSequence, TextView.BufferType paramBufferType)
  {
    super.setText(Iconify.compute(getContext(), paramCharSequence, this), paramBufferType);
  }
}

/* Location:
 * Qualified Name:     com.joanzapata.iconify.widget.IconTextView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */