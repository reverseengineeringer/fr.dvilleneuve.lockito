package com.facebook.stetho.common.android;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import javax.annotation.Nullable;

@TargetApi(17)
class FragmentCompatFramework$FragmentAccessorFrameworkJellyBean
  extends FragmentCompatFramework.FragmentAccessorFrameworkHoneycomb
{
  private FragmentCompatFramework$FragmentAccessorFrameworkJellyBean()
  {
    super(null);
  }
  
  @Nullable
  public FragmentManager getChildFragmentManager(Fragment paramFragment)
  {
    return paramFragment.getChildFragmentManager();
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.android.FragmentCompatFramework.FragmentAccessorFrameworkJellyBean
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */