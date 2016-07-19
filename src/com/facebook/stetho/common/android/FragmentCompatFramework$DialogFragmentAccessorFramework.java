package com.facebook.stetho.common.android;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.view.View;
import javax.annotation.Nullable;

class FragmentCompatFramework$DialogFragmentAccessorFramework
  implements DialogFragmentAccessor<DialogFragment, Fragment, FragmentManager>
{
  private final FragmentAccessor<Fragment, FragmentManager> mFragmentAccessor;
  
  public FragmentCompatFramework$DialogFragmentAccessorFramework(FragmentAccessor<Fragment, FragmentManager> paramFragmentAccessor)
  {
    mFragmentAccessor = paramFragmentAccessor;
  }
  
  @Nullable
  public FragmentManager getChildFragmentManager(Fragment paramFragment)
  {
    return (FragmentManager)mFragmentAccessor.getChildFragmentManager(paramFragment);
  }
  
  public Dialog getDialog(DialogFragment paramDialogFragment)
  {
    return paramDialogFragment.getDialog();
  }
  
  @Nullable
  public FragmentManager getFragmentManager(Fragment paramFragment)
  {
    return (FragmentManager)mFragmentAccessor.getFragmentManager(paramFragment);
  }
  
  public int getId(Fragment paramFragment)
  {
    return mFragmentAccessor.getId(paramFragment);
  }
  
  public Resources getResources(Fragment paramFragment)
  {
    return mFragmentAccessor.getResources(paramFragment);
  }
  
  @Nullable
  public String getTag(Fragment paramFragment)
  {
    return mFragmentAccessor.getTag(paramFragment);
  }
  
  @Nullable
  public View getView(Fragment paramFragment)
  {
    return mFragmentAccessor.getView(paramFragment);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.android.FragmentCompatFramework.DialogFragmentAccessorFramework
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */