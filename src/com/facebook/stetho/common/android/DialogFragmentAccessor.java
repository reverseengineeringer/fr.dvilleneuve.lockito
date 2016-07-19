package com.facebook.stetho.common.android;

import android.app.Dialog;

public abstract interface DialogFragmentAccessor<DIALOG_FRAGMENT, FRAGMENT, FRAGMENT_MANAGER>
  extends FragmentAccessor<FRAGMENT, FRAGMENT_MANAGER>
{
  public abstract Dialog getDialog(DIALOG_FRAGMENT paramDIALOG_FRAGMENT);
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.android.DialogFragmentAccessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */