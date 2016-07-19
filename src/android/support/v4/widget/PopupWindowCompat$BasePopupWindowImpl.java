package android.support.v4.widget;

import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.PopupWindow;

class PopupWindowCompat$BasePopupWindowImpl
  implements PopupWindowCompat.PopupWindowImpl
{
  public boolean getOverlapAnchor(PopupWindow paramPopupWindow)
  {
    return false;
  }
  
  public int getWindowLayoutType(PopupWindow paramPopupWindow)
  {
    return 0;
  }
  
  public void setOverlapAnchor(PopupWindow paramPopupWindow, boolean paramBoolean) {}
  
  public void setWindowLayoutType(PopupWindow paramPopupWindow, int paramInt) {}
  
  public void showAsDropDown(PopupWindow paramPopupWindow, View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt1;
    if ((GravityCompat.getAbsoluteGravity(paramInt3, ViewCompat.getLayoutDirection(paramView)) & 0x7) == 5) {
      i = paramInt1 - (paramPopupWindow.getWidth() - paramView.getWidth());
    }
    paramPopupWindow.showAsDropDown(paramView, i, paramInt2);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.PopupWindowCompat.BasePopupWindowImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */