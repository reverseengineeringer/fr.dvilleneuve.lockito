package android.support.v7.widget;

import android.content.Context;
import android.support.v7.appcompat.R.attr;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.SubMenuBuilder;
import android.view.View;

class ActionMenuPresenter$ActionButtonSubmenu
  extends MenuPopupHelper
{
  public ActionMenuPresenter$ActionButtonSubmenu(ActionMenuPresenter paramActionMenuPresenter, Context paramContext, SubMenuBuilder paramSubMenuBuilder, View paramView)
  {
    super(paramContext, paramSubMenuBuilder, paramView, false, R.attr.actionOverflowMenuStyle);
    if (!((MenuItemImpl)paramSubMenuBuilder.getItem()).isActionButton()) {
      if (ActionMenuPresenter.access$600(paramActionMenuPresenter) != null) {
        break label59;
      }
    }
    label59:
    for (paramContext = (View)ActionMenuPresenter.access$700(paramActionMenuPresenter);; paramContext = ActionMenuPresenter.access$600(paramActionMenuPresenter))
    {
      setAnchorView(paramContext);
      setPresenterCallback(mPopupPresenterCallback);
      return;
    }
  }
  
  protected void onDismiss()
  {
    ActionMenuPresenter.access$802(this$0, null);
    this$0.mOpenSubMenuId = 0;
    super.onDismiss();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ActionMenuPresenter.ActionButtonSubmenu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */