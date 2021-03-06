package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;

public class MenuPopupWindow$MenuDropDownListView
  extends DropDownListView
{
  final int mAdvanceKey;
  private MenuItemHoverListener mHoverListener;
  private MenuItem mHoveredMenuItem;
  final int mRetreatKey;
  
  public MenuPopupWindow$MenuDropDownListView(Context paramContext, boolean paramBoolean)
  {
    super(paramContext, paramBoolean);
    paramContext = paramContext.getResources().getConfiguration();
    if ((Build.VERSION.SDK_INT >= 17) && (1 == paramContext.getLayoutDirection()))
    {
      mAdvanceKey = 21;
      mRetreatKey = 22;
      return;
    }
    mAdvanceKey = 22;
    mRetreatKey = 21;
  }
  
  public void clearSelection()
  {
    setSelection(-1);
  }
  
  public boolean onHoverEvent(MotionEvent paramMotionEvent)
  {
    int i;
    if (mHoverListener != null)
    {
      localObject1 = getAdapter();
      if (!(localObject1 instanceof HeaderViewListAdapter)) {
        break label185;
      }
      localObject1 = (HeaderViewListAdapter)localObject1;
      i = ((HeaderViewListAdapter)localObject1).getHeadersCount();
    }
    for (Object localObject1 = (MenuAdapter)((HeaderViewListAdapter)localObject1).getWrappedAdapter();; localObject1 = (MenuAdapter)localObject1)
    {
      MenuItem localMenuItem = null;
      Object localObject2 = localMenuItem;
      if (paramMotionEvent.getAction() != 10)
      {
        int j = pointToPosition((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
        localObject2 = localMenuItem;
        if (j != -1)
        {
          i = j - i;
          localObject2 = localMenuItem;
          if (i >= 0)
          {
            localObject2 = localMenuItem;
            if (i < ((MenuAdapter)localObject1).getCount()) {
              localObject2 = ((MenuAdapter)localObject1).getItem(i);
            }
          }
        }
      }
      localMenuItem = mHoveredMenuItem;
      if (localMenuItem != localObject2)
      {
        localObject1 = ((MenuAdapter)localObject1).getAdapterMenu();
        if (localMenuItem != null) {
          mHoverListener.onItemHoverExit((MenuBuilder)localObject1, localMenuItem);
        }
        mHoveredMenuItem = ((MenuItem)localObject2);
        if (localObject2 != null) {
          mHoverListener.onItemHoverEnter((MenuBuilder)localObject1, (MenuItem)localObject2);
        }
      }
      return super.onHoverEvent(paramMotionEvent);
      label185:
      i = 0;
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    ListMenuItemView localListMenuItemView = (ListMenuItemView)getSelectedView();
    if ((localListMenuItemView != null) && (paramInt == mAdvanceKey))
    {
      if ((localListMenuItemView.isEnabled()) && (localListMenuItemView.getItemData().hasSubMenu())) {
        performItemClick(localListMenuItemView, getSelectedItemPosition(), getSelectedItemId());
      }
      return true;
    }
    if ((localListMenuItemView != null) && (paramInt == mRetreatKey))
    {
      setSelection(-1);
      ((MenuAdapter)getAdapter()).getAdapterMenu().close(false);
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public void setHoverListener(MenuItemHoverListener paramMenuItemHoverListener)
  {
    mHoverListener = paramMenuItemHoverListener;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.MenuPopupWindow.MenuDropDownListView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */