package fr.dvilleneuve.lockito.ui;

import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.ui.adapter.ItinerariesAdapter;
import fr.dvilleneuve.lockito.ui.dialog.ItineraryNameDialog;
import fr.dvilleneuve.lockito.ui.dialog.ItineraryNameDialog_;
import fr.dvilleneuve.lockito.ui.dialog.ItineraryNameDialog_.FragmentBuilder_;
import java.util.List;

final class ItinerariesActivity$EditItineraryActionMode
  implements ActionMode.Callback
{
  private ItinerariesActivity$EditItineraryActionMode(ItinerariesActivity paramItinerariesActivity) {}
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return true;
      paramActionMode = (ItineraryInfo)this$0.itinerariesAdapter.getSelectedItems().get(0);
      paramMenuItem = ItineraryNameDialog_.builder().build();
      paramMenuItem.setItineraryInfo(paramActionMode);
      paramMenuItem.show(this$0.getSupportFragmentManager(), "ITINERARY_NAME_DIALOG");
      continue;
      ItinerariesActivity.access$200(this$0);
      continue;
      ItinerariesActivity.access$300(this$0);
    }
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    paramActionMode.getMenuInflater().inflate(2131755009, paramMenu);
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    this$0.itinerariesAdapter.clearSelectedItems();
    ItinerariesActivity.access$102(this$0, null);
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ItinerariesActivity.EditItineraryActionMode
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */