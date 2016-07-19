package com.facebook.stetho.common.android;

import android.app.Activity;
import android.view.View;
import java.util.List;
import javax.annotation.Nullable;

public final class FragmentCompatUtil
{
  @Nullable
  public static Object findFragmentForView(View paramView)
  {
    Activity localActivity = ViewUtil.tryGetActivity(paramView);
    if (localActivity == null) {
      return null;
    }
    return findFragmentForViewInActivity(localActivity, paramView);
  }
  
  @Nullable
  private static Object findFragmentForViewInActivity(Activity paramActivity, View paramView)
  {
    Object localObject = FragmentCompat.getSupportLibInstance();
    if ((localObject != null) && (((FragmentCompat)localObject).getFragmentActivityClass().isInstance(paramActivity)))
    {
      localObject = findFragmentForViewInActivity((FragmentCompat)localObject, paramActivity, paramView);
      if (localObject != null) {
        paramActivity = (Activity)localObject;
      }
    }
    do
    {
      return paramActivity;
      localObject = FragmentCompat.getFrameworkInstance();
      if (localObject == null) {
        break;
      }
      paramView = findFragmentForViewInActivity((FragmentCompat)localObject, paramActivity, paramView);
      paramActivity = paramView;
    } while (paramView != null);
    return null;
  }
  
  private static Object findFragmentForViewInActivity(FragmentCompat paramFragmentCompat, Activity paramActivity, View paramView)
  {
    paramActivity = paramFragmentCompat.forFragmentActivity().getFragmentManager(paramActivity);
    if (paramActivity != null) {
      return findFragmentForViewInFragmentManager(paramFragmentCompat, paramActivity, paramView);
    }
    return null;
  }
  
  @Nullable
  private static Object findFragmentForViewInFragment(FragmentCompat paramFragmentCompat, Object paramObject, View paramView)
  {
    FragmentAccessor localFragmentAccessor = paramFragmentCompat.forFragment();
    if (localFragmentAccessor.getView(paramObject) == paramView) {
      return paramObject;
    }
    paramObject = localFragmentAccessor.getChildFragmentManager(paramObject);
    if (paramObject != null) {
      return findFragmentForViewInFragmentManager(paramFragmentCompat, paramObject, paramView);
    }
    return null;
  }
  
  @Nullable
  private static Object findFragmentForViewInFragmentManager(FragmentCompat paramFragmentCompat, Object paramObject, View paramView)
  {
    paramObject = paramFragmentCompat.forFragmentManager().getAddedFragments(paramObject);
    if (paramObject != null)
    {
      int i = 0;
      int j = ((List)paramObject).size();
      while (i < j)
      {
        Object localObject = findFragmentForViewInFragment(paramFragmentCompat, ((List)paramObject).get(i), paramView);
        if (localObject != null) {
          return localObject;
        }
        i += 1;
      }
    }
    return null;
  }
  
  public static boolean isDialogFragment(Object paramObject)
  {
    FragmentCompat localFragmentCompat = FragmentCompat.getSupportLibInstance();
    if ((localFragmentCompat != null) && (localFragmentCompat.getDialogFragmentClass().isInstance(paramObject))) {}
    do
    {
      return true;
      localFragmentCompat = FragmentCompat.getFrameworkInstance();
    } while ((localFragmentCompat != null) && (localFragmentCompat.getDialogFragmentClass().isInstance(paramObject)));
    return false;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.android.FragmentCompatUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */