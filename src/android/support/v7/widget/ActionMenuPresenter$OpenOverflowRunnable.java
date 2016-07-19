package android.support.v7.widget;

import android.support.v7.view.menu.MenuBuilder;
import android.view.View;

class ActionMenuPresenter$OpenOverflowRunnable
  implements Runnable
{
  private ActionMenuPresenter.OverflowPopup mPopup;
  
  public ActionMenuPresenter$OpenOverflowRunnable(ActionMenuPresenter paramActionMenuPresenter, ActionMenuPresenter.OverflowPopup paramOverflowPopup)
  {
    mPopup = paramOverflowPopup;
  }
  
  public void run()
  {
    if (ActionMenuPresenter.access$900(this$0) != null) {
      ActionMenuPresenter.access$1000(this$0).changeMenuMode();
    }
    View localView = (View)ActionMenuPresenter.access$1100(this$0);
    if ((localView != null) && (localView.getWindowToken() != null) && (mPopup.tryShow())) {
      ActionMenuPresenter.access$202(this$0, mPopup);
    }
    ActionMenuPresenter.access$302(this$0, null);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ActionMenuPresenter.OpenOverflowRunnable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */