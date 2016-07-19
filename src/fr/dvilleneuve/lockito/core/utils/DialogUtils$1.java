package fr.dvilleneuve.lockito.core.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

final class DialogUtils$1
  implements DialogInterface.OnClickListener
{
  DialogUtils$1(Context paramContext) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.dismiss();
    try
    {
      val$context.startActivity(new Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS"));
      return;
    }
    catch (ActivityNotFoundException paramDialogInterface)
    {
      val$context.startActivity(new Intent("com.android.settings.APPLICATION_DEVELOPMENT_SETTINGS"));
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.DialogUtils.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */