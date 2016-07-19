package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.android.FragmentCompatUtil;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

final class ViewGroupDescriptor
  extends AbstractChainedDescriptor<ViewGroup>
{
  private final Map<View, Object> mViewToElementMap = Collections.synchronizedMap(new WeakHashMap());
  
  private Object getElementForView(ViewGroup paramViewGroup, View paramView)
  {
    Object localObject = mViewToElementMap.get(paramView);
    if (localObject != null)
    {
      if (paramView.getParent() == paramViewGroup) {
        return localObject;
      }
      mViewToElementMap.remove(paramView);
    }
    paramViewGroup = FragmentCompatUtil.findFragmentForView(paramView);
    if ((paramViewGroup != null) && (!FragmentCompatUtil.isDialogFragment(paramViewGroup)))
    {
      mViewToElementMap.put(paramView, paramViewGroup);
      return paramViewGroup;
    }
    mViewToElementMap.put(paramView, paramView);
    return paramView;
  }
  
  private boolean isChildVisible(View paramView)
  {
    return !(paramView instanceof DocumentHiddenView);
  }
  
  protected void onGetChildren(ViewGroup paramViewGroup, Accumulator<Object> paramAccumulator)
  {
    int i = 0;
    int j = paramViewGroup.getChildCount();
    while (i < j)
    {
      View localView = paramViewGroup.getChildAt(i);
      if (isChildVisible(localView)) {
        paramAccumulator.store(getElementForView(paramViewGroup, localView));
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewGroupDescriptor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */