package android.support.v7.view.menu;

import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.widget.MenuItemHoverListener;
import android.view.MenuItem;
import java.util.List;

class CascadingMenuPopup$2
  implements MenuItemHoverListener
{
  CascadingMenuPopup$2(CascadingMenuPopup paramCascadingMenuPopup) {}
  
  public void onItemHoverEnter(@NonNull final MenuBuilder paramMenuBuilder, @NonNull final MenuItem paramMenuItem)
  {
    CascadingMenuPopup.access$200(this$0).removeCallbacksAndMessages(null);
    int k = -1;
    int i = 0;
    int m = CascadingMenuPopup.access$000(this$0).size();
    int j;
    for (;;)
    {
      j = k;
      if (i < m)
      {
        if (paramMenuBuilder == access$000this$0).get(i)).menu) {
          j = i;
        }
      }
      else
      {
        if (j != -1) {
          break;
        }
        return;
      }
      i += 1;
    }
    i = j + 1;
    if (i < CascadingMenuPopup.access$000(this$0).size()) {}
    for (final CascadingMenuPopup.CascadingMenuInfo localCascadingMenuInfo = (CascadingMenuPopup.CascadingMenuInfo)CascadingMenuPopup.access$000(this$0).get(i);; localCascadingMenuInfo = null)
    {
      paramMenuItem = new Runnable()
      {
        public void run()
        {
          if (localCascadingMenuInfo != null)
          {
            CascadingMenuPopup.access$302(this$0, true);
            localCascadingMenuInfomenu.close(false);
            CascadingMenuPopup.access$302(this$0, false);
          }
          if ((paramMenuItem.isEnabled()) && (paramMenuItem.hasSubMenu())) {
            paramMenuBuilder.performItemAction(paramMenuItem, 0);
          }
        }
      };
      long l = SystemClock.uptimeMillis();
      CascadingMenuPopup.access$200(this$0).postAtTime(paramMenuItem, paramMenuBuilder, l + 200L);
      return;
    }
  }
  
  public void onItemHoverExit(@NonNull MenuBuilder paramMenuBuilder, @NonNull MenuItem paramMenuItem)
  {
    CascadingMenuPopup.access$200(this$0).removeCallbacksAndMessages(paramMenuBuilder);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.view.menu.CascadingMenuPopup.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */