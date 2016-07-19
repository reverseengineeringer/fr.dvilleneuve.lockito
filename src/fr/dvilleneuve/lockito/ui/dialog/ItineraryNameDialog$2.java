package fr.dvilleneuve.lockito.ui.dialog;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.Editable;
import android.widget.EditText;

class ItineraryNameDialog$2
  implements DialogInterface.OnClickListener
{
  ItineraryNameDialog$2(ItineraryNameDialog paramItineraryNameDialog) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (ItineraryNameDialog.access$000(this$0).getText().length() == 0) {}
    for (paramDialogInterface = ItineraryNameDialog.access$000(this$0).getHint().toString();; paramDialogInterface = ItineraryNameDialog.access$000(this$0).getText().toString())
    {
      ((ItineraryNameDialog.NoticeDialogListener)this$0.getActivity()).onValidateItineraryName(this$0, this$0.itineraryInfoId, paramDialogInterface);
      this$0.dismiss();
      return;
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.dialog.ItineraryNameDialog.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */