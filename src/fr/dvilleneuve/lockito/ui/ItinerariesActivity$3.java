package fr.dvilleneuve.lockito.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.view.ActionMode;
import android.widget.Toast;
import fr.dvilleneuve.lockito.core.manager.ItineraryManager;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;

class ItinerariesActivity$3
  implements DialogInterface.OnClickListener
{
  ItinerariesActivity$3(ItinerariesActivity paramItinerariesActivity, ItineraryInfo paramItineraryInfo) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    ItinerariesActivity.access$100(this$0).finish();
    this$0.itineraryManager.duplicateItinerary(val$itineraryInfo);
    Toast.makeText(this$0, this$0.getString(2131165368, new Object[] { val$itineraryInfo.getName() }), 1).show();
    this$0.loadItineraries();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ItinerariesActivity.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */