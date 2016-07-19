package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;

abstract class MenuPopup
  implements ShowableListMenu, MenuPresenter, AdapterView.OnItemClickListener
{
  private Rect mEpicenterBounds;
  
  protected static int measureIndividualMenuWidth(ListAdapter paramListAdapter, ViewGroup paramViewGroup, Context paramContext, int paramInt)
  {
    int i = 0;
    Object localObject2 = null;
    int m = 0;
    int i1 = View.MeasureSpec.makeMeasureSpec(0, 0);
    int i2 = View.MeasureSpec.makeMeasureSpec(0, 0);
    int i3 = paramListAdapter.getCount();
    int j = 0;
    Object localObject1 = paramViewGroup;
    paramViewGroup = (ViewGroup)localObject2;
    while (j < i3)
    {
      int n = paramListAdapter.getItemViewType(j);
      int k = m;
      if (n != m)
      {
        k = n;
        paramViewGroup = null;
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = new FrameLayout(paramContext);
      }
      paramViewGroup = paramListAdapter.getView(j, paramViewGroup, (ViewGroup)localObject2);
      paramViewGroup.measure(i1, i2);
      m = paramViewGroup.getMeasuredWidth();
      if (m >= paramInt) {
        return paramInt;
      }
      n = i;
      if (m > i) {
        n = m;
      }
      j += 1;
      m = k;
      i = n;
      localObject1 = localObject2;
    }
    return i;
  }
  
  protected static boolean shouldPreserveIconSpacing(MenuBuilder paramMenuBuilder)
  {
    boolean bool2 = false;
    int j = paramMenuBuilder.size();
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        MenuItem localMenuItem = paramMenuBuilder.getItem(i);
        if ((localMenuItem.isVisible()) && (localMenuItem.getIcon() != null)) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      i += 1;
    }
  }
  
  protected static MenuAdapter toMenuAdapter(ListAdapter paramListAdapter)
  {
    if ((paramListAdapter instanceof HeaderViewListAdapter)) {
      return (MenuAdapter)((HeaderViewListAdapter)paramListAdapter).getWrappedAdapter();
    }
    return (MenuAdapter)paramListAdapter;
  }
  
  public abstract void addMenu(MenuBuilder paramMenuBuilder);
  
  public boolean collapseItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  public boolean expandItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  public Rect getEpicenterBounds()
  {
    return mEpicenterBounds;
  }
  
  public int getId()
  {
    return 0;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup)
  {
    throw new UnsupportedOperationException("MenuPopups manage their own views");
  }
  
  public void initForMenu(@NonNull Context paramContext, @Nullable MenuBuilder paramMenuBuilder) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = (ListAdapter)paramAdapterView.getAdapter();
    toMenuAdaptermAdapterMenu.performItemAction((MenuItem)paramAdapterView.getItem(paramInt), 0);
  }
  
  public abstract void setAnchorView(View paramView);
  
  public void setEpicenterBounds(Rect paramRect)
  {
    mEpicenterBounds = paramRect;
  }
  
  public abstract void setForceShowIcon(boolean paramBoolean);
  
  public abstract void setGravity(int paramInt);
  
  public abstract void setHorizontalOffset(int paramInt);
  
  public abstract void setOnDismissListener(PopupWindow.OnDismissListener paramOnDismissListener);
  
  public abstract void setShowTitle(boolean paramBoolean);
  
  public abstract void setVerticalOffset(int paramInt);
}

/* Location:
 * Qualified Name:     android.support.v7.view.menu.MenuPopup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */