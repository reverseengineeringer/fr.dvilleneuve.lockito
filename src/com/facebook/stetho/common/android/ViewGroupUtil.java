package com.facebook.stetho.common.android;

import android.view.View;
import android.view.ViewGroup;

public final class ViewGroupUtil
{
  public static int findChildIndex(ViewGroup paramViewGroup, View paramView)
  {
    int j = paramViewGroup.getChildCount();
    int i = 0;
    while (i < j)
    {
      if (paramViewGroup.getChildAt(i) == paramView) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.android.ViewGroupUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */