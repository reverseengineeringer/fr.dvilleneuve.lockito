package android.support.v7.view.menu;

import android.support.annotation.NonNull;
import android.support.v7.widget.MenuPopupWindow;
import android.widget.ListView;

class CascadingMenuPopup$CascadingMenuInfo
{
  public final MenuBuilder menu;
  public final int position;
  public final MenuPopupWindow window;
  
  public CascadingMenuPopup$CascadingMenuInfo(@NonNull MenuPopupWindow paramMenuPopupWindow, @NonNull MenuBuilder paramMenuBuilder, int paramInt)
  {
    window = paramMenuPopupWindow;
    menu = paramMenuBuilder;
    position = paramInt;
  }
  
  public ListView getListView()
  {
    return window.getListView();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.view.menu.CascadingMenuPopup.CascadingMenuInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */