package fr.dvilleneuve.lockito.ui.fragment;

import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.android.gms.maps.model.Marker;

final class EnhancedMapFragment$EditMarkerActionMode
  implements ActionMode.Callback
{
  private Marker marker;
  
  public EnhancedMapFragment$EditMarkerActionMode(EnhancedMapFragment paramEnhancedMapFragment, Marker paramMarker)
  {
    marker = paramMarker;
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return true;
      paramMenuItem = EnhancedMapFragment.access$100(this$0, marker);
      if (paramMenuItem != null)
      {
        this$0.onMarkerClick(paramMenuItem);
        paramMenuItem.showInfoWindow();
      }
      paramActionMode.finish();
    }
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    paramActionMode.getMenuInflater().inflate(2131755012, paramMenu);
    paramActionMode.setTitle(marker.getTitle());
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    marker.hideInfoWindow();
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.fragment.EnhancedMapFragment.EditMarkerActionMode
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */