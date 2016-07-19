package android.support.v7.widget;

import android.widget.PopupWindow.OnDismissListener;

class PopupMenu$2
  implements PopupWindow.OnDismissListener
{
  PopupMenu$2(PopupMenu paramPopupMenu) {}
  
  public void onDismiss()
  {
    if (PopupMenu.access$100(this$0) != null) {
      PopupMenu.access$100(this$0).onDismiss(this$0);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.PopupMenu.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */