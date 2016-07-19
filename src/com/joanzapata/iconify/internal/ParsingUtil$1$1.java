package com.joanzapata.iconify.internal;

import android.support.v4.view.ViewCompat;
import android.widget.TextView;

class ParsingUtil$1$1
  implements Runnable
{
  ParsingUtil$1$1(ParsingUtil.1 param1) {}
  
  public void run()
  {
    if (this$0.isAttached)
    {
      this$0.val$target.invalidate();
      ViewCompat.postOnAnimation(this$0.val$target, this);
    }
  }
}

/* Location:
 * Qualified Name:     com.joanzapata.iconify.internal.ParsingUtil.1.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */