package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.widget.PopupWindow;

class ListPopupWindow$ResizePopupRunnable
  implements Runnable
{
  private ListPopupWindow$ResizePopupRunnable(ListPopupWindow paramListPopupWindow) {}
  
  public void run()
  {
    if ((ListPopupWindow.access$500(this$0) != null) && (ViewCompat.isAttachedToWindow(ListPopupWindow.access$500(this$0))) && (ListPopupWindow.access$500(this$0).getCount() > ListPopupWindow.access$500(this$0).getChildCount()) && (ListPopupWindow.access$500(this$0).getChildCount() <= this$0.mListItemExpandMaximum))
    {
      this$0.mPopup.setInputMethodMode(2);
      this$0.show();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ListPopupWindow.ResizePopupRunnable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */