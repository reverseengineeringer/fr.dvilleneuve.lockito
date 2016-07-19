package android.support.v7.view.menu;

import android.widget.ListView;

public abstract interface ShowableListMenu
{
  public abstract void dismiss();
  
  public abstract ListView getListView();
  
  public abstract boolean isShowing();
  
  public abstract void show();
}

/* Location:
 * Qualified Name:     android.support.v7.view.menu.ShowableListMenu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */