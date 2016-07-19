package android.support.v7.view.menu;

import android.support.v7.widget.MenuPopupWindow;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class StandardMenuPopup$1
  implements ViewTreeObserver.OnGlobalLayoutListener
{
  StandardMenuPopup$1(StandardMenuPopup paramStandardMenuPopup) {}
  
  public void onGlobalLayout()
  {
    if ((this$0.isShowing()) && (!StandardMenuPopup.access$000(this$0).isModal()))
    {
      View localView = StandardMenuPopup.access$100(this$0);
      if ((localView == null) || (!localView.isShown())) {
        this$0.dismiss();
      }
    }
    else
    {
      return;
    }
    StandardMenuPopup.access$000(this$0).show();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.view.menu.StandardMenuPopup.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */