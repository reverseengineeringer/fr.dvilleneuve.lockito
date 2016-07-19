package com.facebook.stetho.common.android;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.view.View;
import javax.annotation.Nullable;

class FragmentCompatFramework$FragmentAccessorFrameworkHoneycomb
  implements FragmentAccessor<Fragment, FragmentManager>
{
  @Nullable
  public FragmentManager getChildFragmentManager(Fragment paramFragment)
  {
    return null;
  }
  
  @Nullable
  public FragmentManager getFragmentManager(Fragment paramFragment)
  {
    return paramFragment.getFragmentManager();
  }
  
  public int getId(Fragment paramFragment)
  {
    return paramFragment.getId();
  }
  
  public Resources getResources(Fragment paramFragment)
  {
    return paramFragment.getResources();
  }
  
  @Nullable
  public String getTag(Fragment paramFragment)
  {
    return paramFragment.getTag();
  }
  
  @Nullable
  public View getView(Fragment paramFragment)
  {
    return paramFragment.getView();
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.android.FragmentCompatFramework.FragmentAccessorFrameworkHoneycomb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */