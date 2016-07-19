package com.joanzapata.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public abstract class QuickAdapter<T>
  extends BaseQuickAdapter<T, BaseAdapterHelper>
{
  public QuickAdapter(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
  }
  
  public QuickAdapter(Context paramContext, int paramInt, List<T> paramList)
  {
    super(paramContext, paramInt, paramList);
  }
  
  protected BaseAdapterHelper getAdapterHelper(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return BaseAdapterHelper.get(context, paramView, paramViewGroup, layoutResId, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.joanzapata.android.QuickAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */