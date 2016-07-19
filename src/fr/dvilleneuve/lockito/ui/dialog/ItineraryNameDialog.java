package fr.dvilleneuve.lockito.ui.dialog;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;

@EFragment
public class ItineraryNameDialog
  extends DialogFragment
{
  @InstanceState
  Long itineraryInfoId;
  @InstanceState
  String itineraryInfoName;
  private EditText nameEditText;
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    paramBundle = getActivity().getLayoutInflater().inflate(2130903077, null);
    nameEditText = ((EditText)paramBundle.findViewById(2131624062));
    if (itineraryInfoName != null) {
      nameEditText.setText(itineraryInfoName);
    }
    nameEditText.requestFocus();
    paramBundle = new AlertDialog.Builder(getActivity()).setCancelable(true).setTitle(2131165302).setView(paramBundle).setPositiveButton(2131165323, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (nameEditText.getText().length() == 0) {}
        for (paramAnonymousDialogInterface = nameEditText.getHint().toString();; paramAnonymousDialogInterface = nameEditText.getText().toString())
        {
          ((ItineraryNameDialog.NoticeDialogListener)getActivity()).onValidateItineraryName(ItineraryNameDialog.this, itineraryInfoId, paramAnonymousDialogInterface);
          dismiss();
          return;
        }
      }
    }).setNegativeButton(2131165280, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        dismiss();
      }
    }).create();
    paramBundle.getWindow().setSoftInputMode(4);
    return paramBundle;
  }
  
  public void setItineraryInfo(ItineraryInfo paramItineraryInfo)
  {
    itineraryInfoId = Long.valueOf(paramItineraryInfo.getId());
    itineraryInfoName = paramItineraryInfo.getName();
  }
  
  public static abstract interface NoticeDialogListener
  {
    public abstract void onValidateItineraryName(DialogFragment paramDialogFragment, Long paramLong, String paramString);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.dialog.ItineraryNameDialog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */