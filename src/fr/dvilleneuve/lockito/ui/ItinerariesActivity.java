package fr.dvilleneuve.lockito.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.support.v4.app.DialogFragment;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.gms.ads.AdView;
import fr.dvilleneuve.lockito.core.ItinerariesOrder;
import fr.dvilleneuve.lockito.core.helper.AdHelper;
import fr.dvilleneuve.lockito.core.manager.BusManager;
import fr.dvilleneuve.lockito.core.manager.ItineraryManager;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager;
import fr.dvilleneuve.lockito.core.manager.SimulationManager;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.service.SimulationService_;
import fr.dvilleneuve.lockito.core.service.SimulationService_.IntentBuilder_;
import fr.dvilleneuve.lockito.core.utils.DialogUtils;
import fr.dvilleneuve.lockito.ui.adapter.ItinerariesAdapter;
import fr.dvilleneuve.lockito.ui.dialog.ItineraryNameDialog;
import fr.dvilleneuve.lockito.ui.dialog.ItineraryNameDialog.NoticeDialogListener;
import fr.dvilleneuve.lockito.ui.dialog.ItineraryNameDialog_;
import fr.dvilleneuve.lockito.ui.dialog.ItineraryNameDialog_.FragmentBuilder_;
import java.util.Iterator;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(2130903068)
@OptionsMenu({2131755008})
public class ItinerariesActivity
  extends AbstractActivity
  implements ItineraryNameDialog.NoticeDialogListener
{
  private ActionMode actionMode;
  @Bean
  AdHelper adHelper;
  @ViewById
  AdView adView;
  @Bean
  BusManager busManager;
  @ViewById
  View emptyView;
  @Bean
  ItinerariesAdapter itinerariesAdapter;
  @Bean
  ItineraryManager itineraryManager;
  @ViewById
  ListView listView;
  @Bean
  PreferenceManager preferenceManager;
  @Bean
  SimulationManager simulationManager;
  
  static
  {
    if (!ItinerariesActivity.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  private void deleteSelectedItineraries()
  {
    final Object localObject = itinerariesAdapter.getSelectedItems();
    if (((List)localObject).size() == 1)
    {
      localObject = (ItineraryInfo)((List)localObject).get(0);
      DialogUtils.confirmDeleteItinerary(this, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          actionMode.finish();
          paramAnonymousDialogInterface = localObject.getName();
          if (itineraryManager.deleteItinery(localObject))
          {
            Toast.makeText(ItinerariesActivity.this, getString(2131165367, new Object[] { paramAnonymousDialogInterface }), 1).show();
            loadItineraries();
          }
        }
      }, ((ItineraryInfo)localObject).getName()).show();
      return;
    }
    DialogUtils.confirmDeleteItineraries(this, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        actionMode.finish();
        paramAnonymousDialogInterface = localObject.iterator();
        while (paramAnonymousDialogInterface.hasNext())
        {
          ItineraryInfo localItineraryInfo = (ItineraryInfo)paramAnonymousDialogInterface.next();
          if (itineraryManager.deleteItinery(localItineraryInfo))
          {
            Toast.makeText(ItinerariesActivity.this, getString(2131165364, new Object[] { Integer.valueOf(localObject.size()) }), 1).show();
            loadItineraries();
          }
        }
      }
    }, ((List)localObject).size()).show();
  }
  
  private void duplicateSelectedItineraries()
  {
    final Object localObject = itinerariesAdapter.getSelectedItems();
    if (((List)localObject).size() == 1)
    {
      localObject = (ItineraryInfo)((List)localObject).get(0);
      DialogUtils.confirmDuplicateItinerary(this, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          actionMode.finish();
          itineraryManager.duplicateItinerary(localObject);
          Toast.makeText(ItinerariesActivity.this, getString(2131165368, new Object[] { localObject.getName() }), 1).show();
          loadItineraries();
        }
      }, ((ItineraryInfo)localObject).getName()).show();
      return;
    }
    DialogUtils.confirmDuplicateItineraries(this, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        actionMode.finish();
        paramAnonymousDialogInterface = localObject.iterator();
        while (paramAnonymousDialogInterface.hasNext())
        {
          ItineraryInfo localItineraryInfo = (ItineraryInfo)paramAnonymousDialogInterface.next();
          itineraryManager.duplicateItinerary(localItineraryInfo);
        }
        Toast.makeText(ItinerariesActivity.this, getString(2131165365, new Object[] { Integer.valueOf(localObject.size()) }), 1).show();
        loadItineraries();
      }
    }, ((List)localObject).size()).show();
  }
  
  private void startItineraryActivity(Long paramLong, String paramString)
  {
    assert ((paramLong != null) && (paramString != null));
    ItineraryActivity_.intent(this).itineraryId(paramLong).itineraryName(paramString).start();
    overridePendingTransition(2130968587, 2130968588);
  }
  
  private void toggleItemSelection(int paramInt)
  {
    itinerariesAdapter.toggleSelectedItem(paramInt);
    Resources localResources = getResources();
    List localList = itinerariesAdapter.getSelectedItems();
    paramInt = localList.size();
    if (paramInt == 0)
    {
      actionMode.finish();
      return;
    }
    Object localObject = null;
    if (paramInt == 1) {
      localObject = ((ItineraryInfo)localList.get(0)).getName();
    }
    actionMode.setTitle(localResources.getQuantityString(2131230720, paramInt, new Object[] { localObject, Integer.valueOf(paramInt) }));
    localObject = actionMode.getMenu().findItem(2131624104);
    if (paramInt == 1) {}
    for (boolean bool = true;; bool = false)
    {
      ((MenuItem)localObject).setVisible(bool);
      return;
    }
  }
  
  @AfterViews
  void init()
  {
    listView.setAdapter(itinerariesAdapter);
    listView.setEmptyView(emptyView);
    adHelper.loadView(adView);
  }
  
  @ItemClick
  void listViewItemClicked(int paramInt)
  {
    ItineraryInfo localItineraryInfo = itinerariesAdapter.getItem(paramInt);
    if (actionMode == null)
    {
      loadItinerary(Long.valueOf(localItineraryInfo.getId()));
      return;
    }
    toggleItemSelection(paramInt);
  }
  
  @ItemLongClick
  void listViewItemLongClicked(int paramInt)
  {
    actionMode = startSupportActionMode(new EditItineraryActionMode(null));
    toggleItemSelection(paramInt);
  }
  
  @Background
  void loadItineraries()
  {
    updateLoadingState(true);
    ItinerariesOrder localItinerariesOrder = preferenceManager.getItinerariesOrder();
    updateItineraries(itineraryManager.loadAllItineraries(localItinerariesOrder));
    updateLoadingState(false);
  }
  
  @Background
  void loadItinerary(Long paramLong)
  {
    updateLoadingState(true);
    startItineraryActivity(paramLong, null);
    updateLoadingState(false);
  }
  
  @OptionsItem
  void menuAbout()
  {
    AboutActivity_.intent(this).start();
  }
  
  @OptionsItem
  void menuAdd()
  {
    ItineraryNameDialog_.builder().build().show(getSupportFragmentManager(), "ITINERARY_NAME_DIALOG");
  }
  
  @OptionsItem
  void menuImport()
  {
    SelectFileActivity_.intent(this).start();
  }
  
  @OptionsItem
  void menuSettings()
  {
    SettingsActivity_.intent(this).start();
  }
  
  public void onBackPressed()
  {
    stopService(SimulationService_.intent(this).get());
    super.onBackPressed();
  }
  
  protected void onDestroy()
  {
    adView.setAdListener(null);
    adView.destroy();
    super.onDestroy();
  }
  
  protected void onPause()
  {
    adView.pause();
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    loadItineraries();
    if (preferenceManager.shouldDisplayChangeLog()) {
      ChangelogActivity_.intent(this).start();
    }
    adView.resume();
  }
  
  public void onValidateItineraryName(DialogFragment paramDialogFragment, Long paramLong, String paramString)
  {
    if (paramLong == null) {
      startItineraryActivity(null, paramString);
    }
    do
    {
      return;
      if (actionMode != null) {
        actionMode.finish();
      }
    } while (!itineraryManager.renameItinerary(paramLong, paramString));
    loadItineraries();
  }
  
  @UiThread
  void updateItineraries(List<ItineraryInfo> paramList)
  {
    itinerariesAdapter.setItineraries(paramList);
  }
  
  private final class EditItineraryActionMode
    implements ActionMode.Callback
  {
    private EditItineraryActionMode() {}
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      switch (paramMenuItem.getItemId())
      {
      }
      for (;;)
      {
        return true;
        paramActionMode = (ItineraryInfo)itinerariesAdapter.getSelectedItems().get(0);
        paramMenuItem = ItineraryNameDialog_.builder().build();
        paramMenuItem.setItineraryInfo(paramActionMode);
        paramMenuItem.show(getSupportFragmentManager(), "ITINERARY_NAME_DIALOG");
        continue;
        ItinerariesActivity.this.duplicateSelectedItineraries();
        continue;
        ItinerariesActivity.this.deleteSelectedItineraries();
      }
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      paramActionMode.getMenuInflater().inflate(2131755009, paramMenu);
      return true;
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      itinerariesAdapter.clearSelectedItems();
      ItinerariesActivity.access$102(ItinerariesActivity.this, null);
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return false;
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ItinerariesActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */