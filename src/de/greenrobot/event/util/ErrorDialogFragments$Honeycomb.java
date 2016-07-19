package de.greenrobot.event.util;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

@TargetApi(11)
public class ErrorDialogFragments$Honeycomb
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
 * Qualified Name:     de.greenrobot.event.util.ErrorDialogFragments.Honeycomb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */