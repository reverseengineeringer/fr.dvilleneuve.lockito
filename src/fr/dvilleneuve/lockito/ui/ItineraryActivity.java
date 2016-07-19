package fr.dvilleneuve.lockito.ui;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatSpinner;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.model.Marker;
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;
import fr.dvilleneuve.lockito.core.SimulationState;
import fr.dvilleneuve.lockito.core.converter.ConverterFormat;
import fr.dvilleneuve.lockito.core.converter.ItineraryConverter;
import fr.dvilleneuve.lockito.core.dto.GItineraryException;
import fr.dvilleneuve.lockito.core.dto.GItineraryException.GItineraryStatus;
import fr.dvilleneuve.lockito.core.events.ItineraryChangedEvent;
import fr.dvilleneuve.lockito.core.events.SimulationStateChangedEvent;
import fr.dvilleneuve.lockito.core.helper.AdHelper;
import fr.dvilleneuve.lockito.core.helper.GAnalyticsHelper;
import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.manager.BusManager;
import fr.dvilleneuve.lockito.core.manager.BusMethod;
import fr.dvilleneuve.lockito.core.manager.ItineraryManager;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager;
import fr.dvilleneuve.lockito.core.manager.SimulationManager;
import fr.dvilleneuve.lockito.core.model.ItineraryMode;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.model.entity.Itinerary;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.utils.DialogUtils;
import fr.dvilleneuve.lockito.core.utils.FileUtils;
import fr.dvilleneuve.lockito.core.utils.IOUtils;
import fr.dvilleneuve.lockito.core.utils.LocationsConverter;
import fr.dvilleneuve.lockito.ui.adapter.ItineraryModeAdapter;
import fr.dvilleneuve.lockito.ui.component.DetailsItineraryView_;
import fr.dvilleneuve.lockito.ui.component.DetailsLegView_;
import fr.dvilleneuve.lockito.ui.component.DetailsPointView_;
import fr.dvilleneuve.lockito.ui.component.DetailsView;
import fr.dvilleneuve.lockito.ui.dialog.ItineraryNameDialog;
import fr.dvilleneuve.lockito.ui.dialog.ItineraryNameDialog.NoticeDialogListener;
import fr.dvilleneuve.lockito.ui.dialog.ItineraryNameDialog_;
import fr.dvilleneuve.lockito.ui.dialog.ItineraryNameDialog_.FragmentBuilder_;
import fr.dvilleneuve.lockito.ui.fragment.EnhancedMapFragment;
import fr.dvilleneuve.lockito.ui.fragment.EnhancedMapFragment.EnhancedMapListener;
import fr.dvilleneuve.lockito.ui.fragment.EnhancedMapFragment.EnhancedMapListener.MarkerUpdateType;
import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.UiThread.Propagation;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

@EActivity(2130903069)
@OptionsMenu({2131755013, 2131755010})
public class ItineraryActivity
  extends AbstractActivity
  implements ItineraryNameDialog.NoticeDialogListener, EnhancedMapFragment.EnhancedMapListener
{
  public static final String LOAD_ITINERARY_SERIAL_ID = "load_itinerary";
  public static final String RETRIEVE_ITINERARY_SERIAL_ID = "retrieve_itinerary";
  @Bean
  AdHelper adHelper;
  @ViewById
  AdView adView;
  @Bean
  BusManager busManager;
  @ViewById
  ViewGroup detailsContainer;
  @Nullable
  private DetailsView detailsFragment;
  @Extra
  Boolean importItinerary = Boolean.valueOf(false);
  @Extra
  Long itineraryId;
  @NonConfigurationInstance
  ItineraryInfo itineraryInfo;
  @Bean
  ItineraryManager itineraryManager;
  @Bean
  ItineraryModeAdapter itineraryModeAdapter;
  @ViewById
  AppCompatSpinner itineraryModeSpinner;
  @Extra
  String itineraryName;
  @FragmentById
  EnhancedMapFragment mapFragment;
  @OptionsMenuItem
  MenuItem menuCenter;
  @OptionsMenuItem
  MenuItem menuDelete;
  @OptionsMenuItem
  MenuItem menuExport;
  @OptionsMenuItem
  MenuItem menuPause;
  @OptionsMenuItem
  MenuItem menuPlay;
  @OptionsMenuItem
  MenuItem menuRename;
  @OptionsMenuItem
  MenuItem menuReset;
  @OptionsMenuItem
  MenuItem menuSave;
  @OptionsMenuItem
  MenuItem menuStop;
  @Bean
  PreferenceManager preferenceManager;
  @Bean
  SimulationManager simulationManager;
  private SimulationState stateToSet;
  
  private void closeActivity(final Runnable paramRunnable)
  {
    if (simulationManager.isRunning())
    {
      DialogUtils.confirmDialog(this, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramRunnable.run();
        }
      }, 2131165283, 2131165282).show();
      return;
    }
    if ((preferenceManager.shouldNotifyIfUnsaved()) && (itineraryInfo != null) && (itineraryInfo.hasUnsavedChanges()))
    {
      DialogUtils.confirmDialog(this, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramRunnable.run();
        }
      }, 2131165285, 2131165284).show();
      return;
    }
    paramRunnable.run();
  }
  
  @NonNull
  private ItineraryMode getSelectedItineraryMode()
  {
    return (ItineraryMode)itineraryModeSpinner.getSelectedItem();
  }
  
  private void onMarkerChanged()
  {
    if (itineraryInfo == null) {
      return;
    }
    itineraryInfo.makHasNewChanges();
    LinkedList localLinkedList = mapFragment.getWaypointMarkers();
    BackgroundExecutor.cancelAll("retrieve_itinerary", true);
    retrieveItineraryPoints(LocationsConverter.toLocations(localLinkedList));
  }
  
  private void prepareCloseActivity()
  {
    simulationManager.stopService();
    overridePendingTransition(2130968586, 2130968589);
  }
  
  private void updateMenuState(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    MenuItem localMenuItem;
    if (menuPlay != null)
    {
      menuPlay.setEnabled(paramBoolean1);
      menuPause.setEnabled(paramBoolean2);
      menuStop.setEnabled(paramBoolean3);
      menuPlay.setVisible(paramBoolean4);
      localMenuItem = menuPause;
      if (paramBoolean4) {
        break label75;
      }
    }
    label75:
    for (paramBoolean1 = true;; paramBoolean1 = false)
    {
      localMenuItem.setVisible(paramBoolean1);
      return;
    }
  }
  
  private void updateUiStates(boolean paramBoolean)
  {
    if (menuReset != null)
    {
      menuReset.setEnabled(paramBoolean);
      menuRename.setEnabled(paramBoolean);
      menuSave.setEnabled(paramBoolean);
      menuDelete.setEnabled(paramBoolean);
      menuExport.setEnabled(paramBoolean);
      menuCenter.setEnabled(paramBoolean);
    }
    if (detailsFragment != null) {
      detailsFragment.setEnabled(paramBoolean);
    }
    if (mapFragment != null) {
      mapFragment.setEnabled(paramBoolean);
    }
  }
  
  @AfterViews
  void afterViews()
  {
    ActionBar localActionBar = getSupportActionBar();
    if (localActionBar != null)
    {
      localActionBar.setDisplayHomeAsUpEnabled(true);
      localActionBar.setHomeButtonEnabled(true);
    }
    mapFragment.setEnhancedMapListener(this);
    itineraryModeSpinner.setAdapter(itineraryModeAdapter);
    itineraryModeSpinner.setSelection(1);
    itineraryModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        ItineraryActivity.this.onMarkerChanged();
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    adHelper.loadView(adView);
  }
  
  @Background
  void export(ConverterFormat paramConverterFormat, int paramInt)
  {
    updateLoadingState(true);
    try
    {
      String str = itineraryInfo.getName() + "." + paramConverterFormat.getExtension();
      Object localObject = ItineraryConverter.exportItinerary(itineraryInfo, paramConverterFormat);
      paramConverterFormat = IOUtils.getExportFile(str);
      FileUtils.writeFile(paramConverterFormat, ((String)localObject).getBytes());
      localObject = new Intent("android.intent.action.SEND");
      ((Intent)localObject).setType("application/xml");
      ((Intent)localObject).putExtra("android.intent.extra.STREAM", Uri.fromFile(paramConverterFormat));
      ((Intent)localObject).putExtra("android.intent.extra.SUBJECT", str);
      startActivity(Intent.createChooser((Intent)localObject, getString(paramInt)));
      return;
    }
    catch (Exception paramConverterFormat)
    {
      Logger.error("Can't export itinerary", paramConverterFormat, new Object[0]);
      return;
    }
    finally
    {
      updateLoadingState(false);
    }
  }
  
  @Background(serial="load_itinerary")
  void loadItinerary()
  {
    updateLoadingState(true);
    if (itineraryInfo == null)
    {
      if (!importItinerary.booleanValue()) {
        break label84;
      }
      itineraryInfo = simulationManager.getImportedItineraryInfo();
      if (itineraryInfo != null) {
        itineraryInfo.makHasNewChanges();
      }
    }
    while (itineraryInfo == null)
    {
      Logger.error("Coulnd't load itinerary %d or create itinerary ", new Object[] { itineraryId, itineraryName });
      updateLoadingState(false);
      return;
      label84:
      itineraryInfo = itineraryManager.loadOrCreateItinerary(itineraryId, itineraryName);
    }
    onItineraryLoaded();
  }
  
  @OptionsItem
  void menuCenter()
  {
    mapFragment.centerCameraOnItinerary(itineraryInfo.getItinerary());
  }
  
  @OptionsItem
  void menuDelete()
  {
    DialogUtils.confirmDeleteItinerary(this, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = itineraryInfo.getName();
        itineraryManager.deleteItinery(itineraryInfo);
        Toast.makeText(ItineraryActivity.this, getString(2131165367, new Object[] { paramAnonymousDialogInterface }), 1).show();
        ItineraryActivity.this.prepareCloseActivity();
        finish();
      }
    }, itineraryInfo.getName()).show();
  }
  
  @OptionsItem
  void menuExportGpx()
  {
    export(ConverterFormat.GPX, 2131165298);
  }
  
  @OptionsItem
  void menuExportKml()
  {
    export(ConverterFormat.KML, 2131165299);
  }
  
  @TargetApi(11)
  @OptionsItem({16908332})
  void menuHome()
  {
    closeActivity(new Runnable()
    {
      public void run()
      {
        ItineraryActivity.this.prepareCloseActivity();
        finish();
      }
    });
  }
  
  @OptionsItem
  void menuPause()
  {
    simulationManager.pause();
  }
  
  @OptionsItem
  void menuPlay()
  {
    simulationManager.start();
  }
  
  @OptionsItem
  void menuRename()
  {
    ItineraryNameDialog localItineraryNameDialog = ItineraryNameDialog_.builder().build();
    localItineraryNameDialog.setItineraryInfo(itineraryInfo);
    localItineraryNameDialog.show(getSupportFragmentManager(), "ITINERARY_NAME_DIALOG");
  }
  
  @OptionsItem
  void menuReset()
  {
    itineraryInfo.makHasNewChanges();
    itineraryInfo.getItinerary().clear();
    mapFragment.clearMarkers();
    busManager.post(new SimulationStateChangedEvent(SimulationState.NOT_READY));
  }
  
  @OptionsItem
  void menuSave()
  {
    Dao.CreateOrUpdateStatus localCreateOrUpdateStatus = itineraryManager.save(itineraryInfo);
    if ((localCreateOrUpdateStatus.isCreated()) || (localCreateOrUpdateStatus.isUpdated()))
    {
      localObject = IOUtils.getSnapshotFile(this, itineraryInfo);
      mapFragment.saveSnapshot((File)localObject);
    }
    Object localObject = itineraryInfo.getName();
    if (localCreateOrUpdateStatus.isCreated()) {
      Toast.makeText(this, getString(2131165366, new Object[] { localObject }), 1).show();
    }
    for (;;)
    {
      itineraryInfo.clearNewChangesState();
      return;
      if (!localCreateOrUpdateStatus.isUpdated()) {
        break;
      }
      Toast.makeText(this, getString(2131165369, new Object[] { localObject }), 1).show();
    }
    throw new IllegalArgumentException("Can't create or update this itineraryInfo");
  }
  
  @OptionsItem
  void menuStop()
  {
    simulationManager.stop();
  }
  
  public void onBackPressed()
  {
    if (mapFragment.hasSelectedLeg())
    {
      mapFragment.setSelectedLegIndex(null);
      return;
    }
    closeActivity(new Runnable()
    {
      public void run()
      {
        ItineraryActivity.this.prepareCloseActivity();
        ItineraryActivity.this.onBackPressed();
      }
    });
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    busManager.register(this);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    boolean bool = super.onCreateOptionsMenu(paramMenu);
    if (stateToSet != null)
    {
      busManager.post(new SimulationStateChangedEvent(stateToSet));
      stateToSet = null;
    }
    return bool;
  }
  
  protected void onDestroy()
  {
    adView.setAdListener(null);
    adView.destroy();
    busManager.unregister(this);
    super.onDestroy();
  }
  
  @BusMethod
  public void onEventMainThread(SimulationStateChangedEvent paramSimulationStateChangedEvent)
  {
    boolean bool = true;
    paramSimulationStateChangedEvent = state;
    Logger.debug("Simulation state changed to %s", new Object[] { paramSimulationStateChangedEvent.name() });
    if (menuPlay == null)
    {
      stateToSet = paramSimulationStateChangedEvent;
      return;
    }
    updateSimulationMenuStates(paramSimulationStateChangedEvent);
    if ((paramSimulationStateChangedEvent != SimulationState.RUNNING) && (paramSimulationStateChangedEvent != SimulationState.PAUSED)) {}
    for (;;)
    {
      updateUiStates(bool);
      return;
      bool = false;
    }
  }
  
  @UiThread(propagation=UiThread.Propagation.REUSE)
  void onItineraryLoaded()
  {
    if ((isChangingConfigurations()) || (isDestroyed()) || (isFinishing())) {
      return;
    }
    Logger.info("Itinerary loaded: %d", new Object[] { Long.valueOf(itineraryInfo.getId()) });
    setTitle(itineraryInfo.getName());
    showDetails(null, null);
    mapFragment.setItineraryInfo(itineraryInfo);
    itineraryModeSpinner.setSelection(itineraryModeAdapter.indexOf(itineraryInfo.getItineraryMode()));
    simulationManager.startService(itineraryInfo);
    updateLoadingState(false);
  }
  
  public void onLegSelected(Integer paramInteger)
  {
    showDetails(paramInteger, null);
  }
  
  public void onLegUnselected(Integer paramInteger)
  {
    showDetails(null, null);
  }
  
  public void onMapReady()
  {
    BackgroundExecutor.cancelAll("load_itinerary", true);
    loadItinerary();
  }
  
  public void onMarkerUpdated(Marker paramMarker, EnhancedMapFragment.EnhancedMapListener.MarkerUpdateType paramMarkerUpdateType)
  {
    onMarkerChanged();
  }
  
  protected void onPause()
  {
    adView.pause();
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    if (!simulationManager.isLocationMockEnabled()) {
      DialogUtils.enableMock(this, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
          ItineraryActivity.this.prepareCloseActivity();
          finish();
        }
      }).show();
    }
    adView.resume();
  }
  
  public void onValidateItineraryName(DialogFragment paramDialogFragment, Long paramLong, String paramString)
  {
    if (itineraryManager.renameItinerary(itineraryInfo, paramString)) {
      setTitle(paramString);
    }
  }
  
  @Background(serial="retrieve_itinerary")
  void retrieveItineraryPoints(List<Point> paramList)
  {
    updateLoadingState(true);
    try
    {
      itineraryManager.retrieveItinerary(itineraryInfo, paramList, getSelectedItineraryMode());
      paramList = itineraryInfo.getItinerary().getLegs();
      Logger.info("Retrieved %d legs", new Object[] { Integer.valueOf(paramList.size()) });
      if (detailsFragment != null) {
        detailsFragment.updateSummary();
      }
      busManager.post(new ItineraryChangedEvent(paramList));
      updateLoadingState(false);
      return;
    }
    catch (GItineraryException paramList)
    {
      for (;;)
      {
        paramList = paramList.getStatus();
        Logger.error("Can't retrieve itinerary from Google API. Status: %s", new Object[] { paramList.name() });
        showToast(1, 2131165304, new Object[] { getString(paramList.getResId()) });
      }
    }
    catch (Exception paramList)
    {
      for (;;)
      {
        Logger.error("Can't retrieve itinerary points", paramList, new Object[0]);
        showToast(1, 2131165303, new Object[0]);
      }
    }
  }
  
  @UiThread(propagation=UiThread.Propagation.REUSE)
  public void showDetails(Integer paramInteger1, Integer paramInteger2)
  {
    boolean bool = false;
    Object localObject;
    if (paramInteger2 != null) {
      localObject = DetailsPointView_.build(this);
    }
    for (;;)
    {
      if ((detailsFragment == null) || (!localObject.getClass().equals(detailsFragment.getClass())))
      {
        detailsContainer.addView((View)localObject, 0);
        if (detailsFragment != null) {
          detailsContainer.removeView(detailsFragment);
        }
        detailsFragment = ((DetailsView)localObject);
      }
      detailsFragment.setData(itineraryInfo, paramInteger1, paramInteger2);
      paramInteger1 = detailsFragment;
      if (!simulationManager.isLaunched()) {
        bool = true;
      }
      paramInteger1.setEnabled(bool);
      return;
      if (paramInteger1 != null) {
        localObject = DetailsLegView_.build(this);
      } else {
        localObject = DetailsItineraryView_.build(this);
      }
    }
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    try
    {
      super.startActivityForResult(paramIntent, paramInt);
      return;
    }
    catch (Exception paramIntent)
    {
      Logger.warn("It seems Google play services are not installed", new Object[0]);
      Toast.makeText(this, "It seems Google play services are not installed. It's required by Google maps", 1).show();
      analyticsHelper.eventKnownError(paramIntent);
    }
  }
  
  void updateLoadingState(boolean paramBoolean)
  {
    super.updateLoadingState(paramBoolean);
    if (paramBoolean) {
      onEventMainThread(new SimulationStateChangedEvent(SimulationState.NOT_READY));
    }
    while (simulationManager.getState() == null) {
      return;
    }
    onEventMainThread(new SimulationStateChangedEvent(simulationManager.getState()));
  }
  
  @UiThread(propagation=UiThread.Propagation.REUSE)
  void updateSimulationMenuStates(SimulationState paramSimulationState)
  {
    switch (paramSimulationState)
    {
    default: 
      return;
    case ???: 
      updateMenuState(false, false, false, true);
      return;
    case ???: 
      updateMenuState(true, false, false, true);
      return;
    case ???: 
      updateMenuState(false, true, true, false);
      return;
    }
    updateMenuState(true, false, true, true);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ItineraryActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */