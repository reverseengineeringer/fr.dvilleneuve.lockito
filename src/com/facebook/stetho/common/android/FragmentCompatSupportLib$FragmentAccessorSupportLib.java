package com.facebook.stetho.common.android;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import javax.annotation.Nullable;

class FragmentCompatSupportLib$FragmentAccessorSupportLib
  implements FragmentAccessor<Fragment, FragmentManager>
{
  @Nullable
  public FragmentManager getChildFragmentManager(Fragment paramFragment)
  {
    return paramFragment.getChildFragmentManager();
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
 * Qualified Name:     com.facebook.stetho.common.android.FragmentCompatSupportLib.FragmentAccessorSupportLib
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */