package com.facebook.stetho.common.android;

import android.app.Activity;
import android.app.FragmentManager;
import javax.annotation.Nullable;

class FragmentCompatFramework$FragmentActivityAccessorFramework
  implements FragmentActivityAccessor<Activity, FragmentManager>
{
  @Nullable
  public FragmentManager getFragmentManager(Activity paramActivity)
  {
    return paramActivity.getFragmentManager();
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.android.FragmentCompatFramework.FragmentActivityAccessorFramework
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */