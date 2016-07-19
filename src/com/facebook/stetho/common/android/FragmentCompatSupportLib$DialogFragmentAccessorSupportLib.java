package com.facebook.stetho.common.android;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

class FragmentCompatSupportLib$DialogFragmentAccessorSupportLib
  extends FragmentCompatSupportLib.FragmentAccessorSupportLib
  implements DialogFragmentAccessor<DialogFragment, Fragment, FragmentManager>
{
  private FragmentCompatSupportLib$DialogFragmentAccessorSupportLib()
  {
    super(null);
  }
  
  public Dialog getDialog(DialogFragment paramDialogFragment)
  {
    return paramDialogFragment.getDialog();
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.android.FragmentCompatSupportLib.DialogFragmentAccessorSupportLib
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */