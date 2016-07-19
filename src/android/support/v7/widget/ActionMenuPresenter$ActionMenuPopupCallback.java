package android.support.v7.widget;

import android.support.v7.view.menu.ActionMenuItemView.PopupCallback;
import android.support.v7.view.menu.ShowableListMenu;

class ActionMenuPresenter$ActionMenuPopupCallback
  extends ActionMenuItemView.PopupCallback
{
  private ActionMenuPresenter$ActionMenuPopupCallback(ActionMenuPresenter paramActionMenuPresenter) {}
  
  public ShowableListMenu getPopup()
  {
    if (ActionMenuPresenter.access$800(this$0) != null) {
      return ActionMenuPresenter.access$800(this$0).getPopup();
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ActionMenuPresenter.ActionMenuPopupCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */