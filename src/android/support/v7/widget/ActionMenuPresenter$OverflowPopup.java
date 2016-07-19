package android.support.v7.widget;

import android.content.Context;
import android.support.v7.appcompat.R.attr;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.view.View;

class ActionMenuPresenter$OverflowPopup
  extends MenuPopupHelper
{
  public ActionMenuPresenter$OverflowPopup(ActionMenuPresenter paramActionMenuPresenter, Context paramContext, MenuBuilder paramMenuBuilder, View paramView, boolean paramBoolean)
  {
    super(paramContext, paramMenuBuilder, paramView, paramBoolean, R.attr.actionOverflowMenuStyle);
    setGravity(8388613);
    setPresenterCallback(mPopupPresenterCallback);
  }
  
  protected void onDismiss()
  {
    if (ActionMenuPresenter.access$400(this$0) != null) {
      ActionMenuPresenter.access$500(this$0).close();
    }
    ActionMenuPresenter.access$202(this$0, null);
    super.onDismiss();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ActionMenuPresenter.OverflowPopup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */