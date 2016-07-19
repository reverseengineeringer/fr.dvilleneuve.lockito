package android.support.v7.view.menu;

import android.view.MenuItem;

class CascadingMenuPopup$2$1
  implements Runnable
{
  CascadingMenuPopup$2$1(CascadingMenuPopup.2 param2, CascadingMenuPopup.CascadingMenuInfo paramCascadingMenuInfo, MenuItem paramMenuItem, MenuBuilder paramMenuBuilder) {}
  
  public void run()
  {
    if (val$nextInfo != null)
    {
      CascadingMenuPopup.access$302(this$1.this$0, true);
      val$nextInfo.menu.close(false);
      CascadingMenuPopup.access$302(this$1.this$0, false);
    }
    if ((val$item.isEnabled()) && (val$item.hasSubMenu())) {
      val$menu.performItemAction(val$item, 0);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.view.menu.CascadingMenuPopup.2.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */