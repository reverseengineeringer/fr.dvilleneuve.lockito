package android.support.v7.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuItemWrapperICS;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.reflect.Constructor;

class SupportMenuInflater$MenuState
{
  private static final int defaultGroupId = 0;
  private static final int defaultItemCategory = 0;
  private static final int defaultItemCheckable = 0;
  private static final boolean defaultItemChecked = false;
  private static final boolean defaultItemEnabled = true;
  private static final int defaultItemId = 0;
  private static final int defaultItemOrder = 0;
  private static final boolean defaultItemVisible = true;
  private int groupCategory;
  private int groupCheckable;
  private boolean groupEnabled;
  private int groupId;
  private int groupOrder;
  private boolean groupVisible;
  private ActionProvider itemActionProvider;
  private String itemActionProviderClassName;
  private String itemActionViewClassName;
  private int itemActionViewLayout;
  private boolean itemAdded;
  private char itemAlphabeticShortcut;
  private int itemCategoryOrder;
  private int itemCheckable;
  private boolean itemChecked;
  private boolean itemEnabled;
  private int itemIconResId;
  private int itemId;
  private String itemListenerMethodName;
  private char itemNumericShortcut;
  private int itemShowAsAction;
  private CharSequence itemTitle;
  private CharSequence itemTitleCondensed;
  private boolean itemVisible;
  private Menu menu;
  
  public SupportMenuInflater$MenuState(SupportMenuInflater paramSupportMenuInflater, Menu paramMenu)
  {
    menu = paramMenu;
    resetGroup();
  }
  
  private char getShortcut(String paramString)
  {
    if (paramString == null) {
      return '\000';
    }
    return paramString.charAt(0);
  }
  
  private <T> T newInstance(String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      paramArrayOfClass = SupportMenuInflater.access$100(this$0).getClassLoader().loadClass(paramString).getConstructor(paramArrayOfClass);
      paramArrayOfClass.setAccessible(true);
      paramArrayOfClass = paramArrayOfClass.newInstance(paramArrayOfObject);
      return paramArrayOfClass;
    }
    catch (Exception paramArrayOfClass)
    {
      Log.w("SupportMenuInflater", "Cannot instantiate class: " + paramString, paramArrayOfClass);
    }
    return null;
  }
  
  private void setItem(MenuItem paramMenuItem)
  {
    Object localObject = paramMenuItem.setChecked(itemChecked).setVisible(itemVisible).setEnabled(itemEnabled);
    if (itemCheckable >= 1) {}
    for (boolean bool = true;; bool = false)
    {
      ((MenuItem)localObject).setCheckable(bool).setTitleCondensed(itemTitleCondensed).setIcon(itemIconResId).setAlphabeticShortcut(itemAlphabeticShortcut).setNumericShortcut(itemNumericShortcut);
      if (itemShowAsAction >= 0) {
        MenuItemCompat.setShowAsAction(paramMenuItem, itemShowAsAction);
      }
      if (itemListenerMethodName == null) {
        break label160;
      }
      if (!SupportMenuInflater.access$100(this$0).isRestricted()) {
        break;
      }
      throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
    }
    paramMenuItem.setOnMenuItemClickListener(new SupportMenuInflater.InflatedOnMenuItemClickListener(SupportMenuInflater.access$400(this$0), itemListenerMethodName));
    label160:
    if ((paramMenuItem instanceof MenuItemImpl))
    {
      localObject = (MenuItemImpl)paramMenuItem;
      if (itemCheckable >= 2)
      {
        if (!(paramMenuItem instanceof MenuItemImpl)) {
          break label273;
        }
        ((MenuItemImpl)paramMenuItem).setExclusiveCheckable(true);
      }
      label196:
      int i = 0;
      if (itemActionViewClassName != null)
      {
        MenuItemCompat.setActionView(paramMenuItem, (View)newInstance(itemActionViewClassName, SupportMenuInflater.access$500(), SupportMenuInflater.access$600(this$0)));
        i = 1;
      }
      if (itemActionViewLayout > 0)
      {
        if (i != 0) {
          break label291;
        }
        MenuItemCompat.setActionView(paramMenuItem, itemActionViewLayout);
      }
    }
    for (;;)
    {
      if (itemActionProvider != null) {
        MenuItemCompat.setActionProvider(paramMenuItem, itemActionProvider);
      }
      return;
      break;
      label273:
      if (!(paramMenuItem instanceof MenuItemWrapperICS)) {
        break label196;
      }
      ((MenuItemWrapperICS)paramMenuItem).setExclusiveCheckable(true);
      break label196;
      label291:
      Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
    }
  }
  
  public void addItem()
  {
    itemAdded = true;
    setItem(menu.add(groupId, itemId, itemCategoryOrder, itemTitle));
  }
  
  public SubMenu addSubMenuItem()
  {
    itemAdded = true;
    SubMenu localSubMenu = menu.addSubMenu(groupId, itemId, itemCategoryOrder, itemTitle);
    setItem(localSubMenu.getItem());
    return localSubMenu;
  }
  
  public boolean hasAddedItem()
  {
    return itemAdded;
  }
  
  public void readGroup(AttributeSet paramAttributeSet)
  {
    paramAttributeSet = SupportMenuInflater.access$100(this$0).obtainStyledAttributes(paramAttributeSet, R.styleable.MenuGroup);
    groupId = paramAttributeSet.getResourceId(R.styleable.MenuGroup_android_id, 0);
    groupCategory = paramAttributeSet.getInt(R.styleable.MenuGroup_android_menuCategory, 0);
    groupOrder = paramAttributeSet.getInt(R.styleable.MenuGroup_android_orderInCategory, 0);
    groupCheckable = paramAttributeSet.getInt(R.styleable.MenuGroup_android_checkableBehavior, 0);
    groupVisible = paramAttributeSet.getBoolean(R.styleable.MenuGroup_android_visible, true);
    groupEnabled = paramAttributeSet.getBoolean(R.styleable.MenuGroup_android_enabled, true);
    paramAttributeSet.recycle();
  }
  
  public void readItem(AttributeSet paramAttributeSet)
  {
    paramAttributeSet = SupportMenuInflater.access$100(this$0).obtainStyledAttributes(paramAttributeSet, R.styleable.MenuItem);
    itemId = paramAttributeSet.getResourceId(R.styleable.MenuItem_android_id, 0);
    itemCategoryOrder = (0xFFFF0000 & paramAttributeSet.getInt(R.styleable.MenuItem_android_menuCategory, groupCategory) | 0xFFFF & paramAttributeSet.getInt(R.styleable.MenuItem_android_orderInCategory, groupOrder));
    itemTitle = paramAttributeSet.getText(R.styleable.MenuItem_android_title);
    itemTitleCondensed = paramAttributeSet.getText(R.styleable.MenuItem_android_titleCondensed);
    itemIconResId = paramAttributeSet.getResourceId(R.styleable.MenuItem_android_icon, 0);
    itemAlphabeticShortcut = getShortcut(paramAttributeSet.getString(R.styleable.MenuItem_android_alphabeticShortcut));
    itemNumericShortcut = getShortcut(paramAttributeSet.getString(R.styleable.MenuItem_android_numericShortcut));
    int i;
    if (paramAttributeSet.hasValue(R.styleable.MenuItem_android_checkable)) {
      if (paramAttributeSet.getBoolean(R.styleable.MenuItem_android_checkable, false))
      {
        i = 1;
        itemCheckable = i;
        label154:
        itemChecked = paramAttributeSet.getBoolean(R.styleable.MenuItem_android_checked, false);
        itemVisible = paramAttributeSet.getBoolean(R.styleable.MenuItem_android_visible, groupVisible);
        itemEnabled = paramAttributeSet.getBoolean(R.styleable.MenuItem_android_enabled, groupEnabled);
        itemShowAsAction = paramAttributeSet.getInt(R.styleable.MenuItem_showAsAction, -1);
        itemListenerMethodName = paramAttributeSet.getString(R.styleable.MenuItem_android_onClick);
        itemActionViewLayout = paramAttributeSet.getResourceId(R.styleable.MenuItem_actionLayout, 0);
        itemActionViewClassName = paramAttributeSet.getString(R.styleable.MenuItem_actionViewClass);
        itemActionProviderClassName = paramAttributeSet.getString(R.styleable.MenuItem_actionProviderClass);
        if (itemActionProviderClassName == null) {
          break label331;
        }
        i = 1;
        label262:
        if ((i == 0) || (itemActionViewLayout != 0) || (itemActionViewClassName != null)) {
          break label336;
        }
      }
    }
    for (itemActionProvider = ((ActionProvider)newInstance(itemActionProviderClassName, SupportMenuInflater.access$200(), SupportMenuInflater.access$300(this$0)));; itemActionProvider = null)
    {
      paramAttributeSet.recycle();
      itemAdded = false;
      return;
      i = 0;
      break;
      itemCheckable = groupCheckable;
      break label154;
      label331:
      i = 0;
      break label262;
      label336:
      if (i != 0) {
        Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
      }
    }
  }
  
  public void resetGroup()
  {
    groupId = 0;
    groupCategory = 0;
    groupOrder = 0;
    groupCheckable = 0;
    groupVisible = true;
    groupEnabled = true;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.view.SupportMenuInflater.MenuState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */