package com.joanzapata.iconify.internal;

import android.support.v4.view.ViewCompat;
import android.widget.TextView;

final class ParsingUtil$1
  implements HasOnViewAttachListener.OnViewAttachListener
{
  boolean isAttached = false;
  
  ParsingUtil$1(TextView paramTextView) {}
  
  public void onAttach()
  {
    isAttached = true;
    ViewCompat.postOnAnimation(val$target, new Runnable()
    {
      public void run()
      {
        if (isAttached)
        {
          val$target.invalidate();
          ViewCompat.postOnAnimation(val$target, this);
        }
      }
    });
  }
  
  public void onDetach()
  {
    isAttached = false;
  }
}

/* Location:
 * Qualified Name:     com.joanzapata.iconify.internal.ParsingUtil.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */