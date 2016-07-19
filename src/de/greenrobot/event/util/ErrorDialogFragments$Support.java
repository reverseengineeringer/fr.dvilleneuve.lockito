package de.greenrobot.event.util;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ErrorDialogFragments$Support
  extends DialogFragment
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    ErrorDialogFragments.handleOnClick(paramDialogInterface, paramInt, getActivity(), getArguments());
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    return ErrorDialogFragments.createDialog(getActivity(), getArguments(), this);
  }
}

/* Location:
 * Qualified Name:     de.greenrobot.event.util.ErrorDialogFragments.Support
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */