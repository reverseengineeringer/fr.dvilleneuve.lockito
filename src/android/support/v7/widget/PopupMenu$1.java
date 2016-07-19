package android.support.v7.widget;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.view.MenuItem;

class PopupMenu$1
  implements MenuBuilder.Callback
{
  PopupMenu$1(PopupMenu paramPopupMenu) {}
  
  public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
  {
    if (PopupMenu.access$000(this$0) != null) {
      return PopupMenu.access$000(this$0).onMenuItemClick(paramMenuItem);
    }
    return false;
  }
  
  public void onMenuModeChange(MenuBuilder paramMenuBuilder) {}
}

/* Location:
 * Qualified Name:     android.support.v7.widget.PopupMenu.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */