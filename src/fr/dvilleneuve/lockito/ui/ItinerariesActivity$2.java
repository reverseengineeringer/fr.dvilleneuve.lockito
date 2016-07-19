package fr.dvilleneuve.lockito.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.view.ActionMode;
import android.widget.Toast;
import fr.dvilleneuve.lockito.core.manager.ItineraryManager;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import java.util.Iterator;
import java.util.List;

class ItinerariesActivity$2
  implements DialogInterface.OnClickListener
{
  ItinerariesActivity$2(ItinerariesActivity paramItinerariesActivity, List paramList) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    ItinerariesActivity.access$100(this$0).finish();
    paramDialogInterface = val$selectedItems.iterator();
    while (paramDialogInterface.hasNext())
    {
      ItineraryInfo localItineraryInfo = (ItineraryInfo)paramDialogInterface.next();
      if (this$0.itineraryManager.deleteItinery(localItineraryInfo))
      {
        Toast.makeText(this$0, this$0.getString(2131165364, new Object[] { Integer.valueOf(val$selectedItems.size()) }), 1).show();
        this$0.loadItineraries();
      }
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ItinerariesActivity.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */