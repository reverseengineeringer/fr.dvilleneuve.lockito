package android.support.v7.view.menu;

import android.support.v7.widget.MenuPopupWindow;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import java.util.Iterator;
import java.util.List;

class CascadingMenuPopup$1
  implements ViewTreeObserver.OnGlobalLayoutListener
{
  CascadingMenuPopup$1(CascadingMenuPopup paramCascadingMenuPopup) {}
  
  public void onGlobalLayout()
  {
    Object localObject;
    if ((this$0.isShowing()) && (CascadingMenuPopup.access$000(this$0).size() > 0) && (!access$000this$0).get(0)).window.isModal()))
    {
      localObject = CascadingMenuPopup.access$100(this$0);
      if ((localObject != null) && (((View)localObject).isShown())) {
        break label77;
      }
      this$0.dismiss();
    }
    for (;;)
    {
      return;
      label77:
      localObject = CascadingMenuPopup.access$000(this$0).iterator();
      while (((Iterator)localObject).hasNext()) {
        nextwindow.show();
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.view.menu.CascadingMenuPopup.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */