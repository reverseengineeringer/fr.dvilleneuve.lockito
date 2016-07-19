package com.joanzapata.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseQuickAdapter<T, H extends BaseAdapterHelper>
  extends BaseAdapter
{
  protected static final String TAG = BaseQuickAdapter.class.getSimpleName();
  protected final Context context;
  protected final List<T> data;
  protected boolean displayIndeterminateProgress = false;
  protected final int layoutResId;
  
  public BaseQuickAdapter(Context paramContext, int paramInt)
  {
    this(paramContext, paramInt, null);
  }
  
  public BaseQuickAdapter(Context paramContext, int paramInt, List<T> paramList)
  {
    if (paramList == null) {}
    for (paramList = new ArrayList();; paramList = new ArrayList(paramList))
    {
      data = paramList;
      context = paramContext;
      layoutResId = paramInt;
      return;
    }
  }
  
  private View createIndeterminateProgressView(View paramView, ViewGroup paramViewGroup)
  {
    paramViewGroup = paramView;
    if (paramView == null)
    {
      paramViewGroup = new FrameLayout(context);
      paramViewGroup.setForegroundGravity(17);
      paramViewGroup.addView(new ProgressBar(context));
    }
    return paramViewGroup;
  }
  
  public void add(T paramT)
  {
    data.add(paramT);
    notifyDataSetChanged();
  }
  
  public void addAll(List<T> paramList)
  {
    data.addAll(paramList);
    notifyDataSetChanged();
  }
  
  public void clear()
  {
    data.clear();
    notifyDataSetChanged();
  }
  
  public boolean contains(T paramT)
  {
    return data.contains(paramT);
  }
  
  protected abstract void convert(H paramH, T paramT);
  
  protected abstract H getAdapterHelper(int paramInt, View paramView, ViewGroup paramViewGroup);
  
  public int getCount()
  {
    if (displayIndeterminateProgress) {}
    for (int i = 1;; i = 0) {
      return data.size() + i;
    }
  }
  
  public T getItem(int paramInt)
  {
    if (paramInt >= data.size()) {
      return null;
    }
    return (T)data.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public int getItemViewType(int paramInt)
  {
    if (paramInt >= data.size()) {
      return 1;
    }
    return 0;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (getItemViewType(paramInt) == 0)
    {
      paramView = getAdapterHelper(paramInt, paramView, paramViewGroup);
      convert(paramView, getItem(paramInt));
      return paramView.getView();
    }
    return createIndeterminateProgressView(paramView, paramViewGroup);
  }
  
  public int getViewTypeCount()
  {
    return 2;
  }
  
  public boolean isEnabled(int paramInt)
  {
    return paramInt < data.size();
  }
  
  public void remove(int paramInt)
  {
    data.remove(paramInt);
    notifyDataSetChanged();
  }
  
  public void remove(T paramT)
  {
    data.remove(paramT);
    notifyDataSetChanged();
  }
  
  public void replaceAll(List<T> paramList)
  {
    data.clear();
    data.addAll(paramList);
    notifyDataSetChanged();
  }
  
  public void set(int paramInt, T paramT)
  {
    data.set(paramInt, paramT);
    notifyDataSetChanged();
  }
  
  public void set(T paramT1, T paramT2)
  {
    set(data.indexOf(paramT1), paramT2);
  }
  
  public void showIndeterminateProgress(boolean paramBoolean)
  {
    if (paramBoolean == displayIndeterminateProgress) {
      return;
    }
    displayIndeterminateProgress = paramBoolean;
    notifyDataSetChanged();
  }
}

/* Location:
 * Qualified Name:     com.joanzapata.android.BaseQuickAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */