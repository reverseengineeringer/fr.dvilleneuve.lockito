package android.support.v7.widget;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;

class ListPopupWindow$PopupTouchInterceptor
  implements View.OnTouchListener
{
  private ListPopupWindow$PopupTouchInterceptor(ListPopupWindow paramListPopupWindow) {}
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    int j = (int)paramMotionEvent.getX();
    int k = (int)paramMotionEvent.getY();
    if ((i == 0) && (this$0.mPopup != null) && (this$0.mPopup.isShowing()) && (j >= 0) && (j < this$0.mPopup.getWidth()) && (k >= 0) && (k < this$0.mPopup.getHeight())) {
      ListPopupWindow.access$700(this$0).postDelayed(ListPopupWindow.access$600(this$0), 250L);
    }
    for (;;)
    {
      return false;
      if (i == 1) {
        ListPopupWindow.access$700(this$0).removeCallbacks(ListPopupWindow.access$600(this$0));
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ListPopupWindow.PopupTouchInterceptor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */