package com.facebook.stetho.common.android;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import javax.annotation.Nullable;

class FragmentCompatSupportLib$FragmentActivityAccessorSupportLib
  implements FragmentActivityAccessor<FragmentActivity, FragmentManager>
{
  @Nullable
  public FragmentManager getFragmentManager(FragmentActivity paramFragmentActivity)
  {
    return paramFragmentActivity.getSupportFragmentManager();
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.android.FragmentCompatSupportLib.FragmentActivityAccessorSupportLib
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */