package android.support.v7.widget;

import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.ShowableListMenu;
import android.view.View;

class PopupMenu$3
  extends ForwardingListener
{
  PopupMenu$3(PopupMenu paramPopupMenu, View paramView)
  {
    super(paramView);
  }
  
  public ShowableListMenu getPopup()
  {
    return PopupMenu.access$200(this$0).getPopup();
  }
  
  protected boolean onForwardingStarted()
  {
    this$0.show();
    return true;
  }
  
  protected boolean onForwardingStopped()
  {
    this$0.dismiss();
    return true;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.PopupMenu.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */