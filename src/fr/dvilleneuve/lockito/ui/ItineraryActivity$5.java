package fr.dvilleneuve.lockito.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;
import fr.dvilleneuve.lockito.core.manager.ItineraryManager;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;

class ItineraryActivity$5
  implements DialogInterface.OnClickListener
{
  ItineraryActivity$5(ItineraryActivity paramItineraryActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface = this$0.itineraryInfo.getName();
    this$0.itineraryManager.deleteItinery(this$0.itineraryInfo);
    Toast.makeText(this$0, this$0.getString(2131165367, new Object[] { paramDialogInterface }), 1).show();
    ItineraryActivity.access$100(this$0);
    this$0.finish();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ItineraryActivity.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */