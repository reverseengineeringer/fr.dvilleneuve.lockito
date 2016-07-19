package android.support.v7.app;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.widget.DecorToolbar;
import android.view.Window.Callback;

final class ToolbarActionBar$ActionMenuPresenterCallback
  implements MenuPresenter.Callback
{
  private boolean mClosingActionMenu;
  
  private ToolbarActionBar$ActionMenuPresenterCallback(ToolbarActionBar paramToolbarActionBar) {}
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    if (mClosingActionMenu) {
      return;
    }
    mClosingActionMenu = true;
    ToolbarActionBar.access$300(this$0).dismissPopupMenus();
    if (ToolbarActionBar.access$000(this$0) != null) {
      ToolbarActionBar.access$000(this$0).onPanelClosed(108, paramMenuBuilder);
    }
    mClosingActionMenu = false;
  }
  
  public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
  {
    if (ToolbarActionBar.access$000(this$0) != null)
    {
      ToolbarActionBar.access$000(this$0).onMenuOpened(108, paramMenuBuilder);
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.ToolbarActionBar.ActionMenuPresenterCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */