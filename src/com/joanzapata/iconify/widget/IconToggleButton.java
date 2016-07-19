package com.joanzapata.iconify.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView.BufferType;
import android.widget.ToggleButton;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.internal.HasOnViewAttachListener;
import com.joanzapata.iconify.internal.HasOnViewAttachListener.HasOnViewAttachListenerDelegate;
import com.joanzapata.iconify.internal.HasOnViewAttachListener.OnViewAttachListener;

public class IconToggleButton
  extends ToggleButton
  implements HasOnViewAttachListener
{
  private HasOnViewAttachListener.HasOnViewAttachListenerDelegate delegate;
  
  public IconToggleButton(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  public IconToggleButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  public IconToggleButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
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
    super.setText(Iconify.compute(getContext(), paramCharSequence, this), TextView.BufferType.NORMAL);
  }
}

/* Location:
 * Qualified Name:     com.joanzapata.iconify.widget.IconToggleButton
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */