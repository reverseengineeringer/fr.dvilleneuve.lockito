package android.support.v7.app;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Window;

public class AppCompatDialogFragment
  extends DialogFragment
{
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    return new AppCompatDialog(getContext(), getTheme());
  }
  
  public void setupDialog(Dialog paramDialog, int paramInt)
  {
    if ((paramDialog instanceof AppCompatDialog))
    {
      AppCompatDialog localAppCompatDialog = (AppCompatDialog)paramDialog;
      switch (paramInt)
      {
      default: 
        return;
      case 3: 
        paramDialog.getWindow().addFlags(24);
      }
      localAppCompatDialog.supportRequestWindowFeature(1);
      return;
    }
    super.setupDialog(paramDialog, paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatDialogFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */