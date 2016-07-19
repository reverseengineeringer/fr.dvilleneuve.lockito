package fr.dvilleneuve.lockito.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatSpinner;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.ads.AdView;
import fr.dvilleneuve.lockito.core.SimulationState;
import fr.dvilleneuve.lockito.core.converter.ConverterFormat;
import fr.dvilleneuve.lockito.core.helper.AdHelper_;
import fr.dvilleneuve.lockito.core.helper.GAnalyticsHelper_;
import fr.dvilleneuve.lockito.core.manager.BusManager_;
import fr.dvilleneuve.lockito.core.manager.ItineraryManager_;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager_;
import fr.dvilleneuve.lockito.core.manager.SimulationManager_;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.ui.adapter.ItineraryModeAdapter_;
import fr.dvilleneuve.lockito.ui.fragment.EnhancedMapFragment;
import java.util.List;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.BackgroundExecutor.Task;
import org.androidannotations.api.SdkVersionHelper;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ItineraryActivity_
  extends ItineraryActivity
  implements HasViews, OnViewChangedListener
{
  public static final String IMPORT_ITINERARY_EXTRA = "importItinerary";
  public static final String ITINERARY_ID_EXTRA = "itineraryId";
  public static final String ITINERARY_NAME_EXTRA = "itineraryName";
  private Handler handler_ = new Handler(Looper.getMainLooper());
  private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
  
  private android.support.v4.app.Fragment findSupportFragmentById(int paramInt)
  {
    return getSupportFragmentManager().findFragmentById(paramInt);
  }
  
  private void init_(Bundle paramBundle)
  {
    OnViewChangedNotifier.registerOnViewChangedListener(this);
    paramBundle = (NonConfigurationInstancesHolder)super.getLastCustomNonConfigurationInstance();
    if (paramBundle != null) {
      itineraryInfo = itineraryInfo;
    }
    analyticsHelper = GAnalyticsHelper_.getInstance_(this);
    itineraryManager = ItineraryManager_.getInstance_(this);
    simulationManager = SimulationManager_.getInstance_(this);
    preferenceManager = PreferenceManager_.getInstance_(this);
    itineraryModeAdapter = ItineraryModeAdapter_.getInstance_(this);
    adHelper = AdHelper_.getInstance_(this);
    busManager = BusManager_.getInstance_(this);
    injectExtras_();
  }
  
  private void injectExtras_()
  {
    Bundle localBundle = getIntent().getExtras();
    if (localBundle != null)
    {
      if (localBundle.containsKey("itineraryName")) {
        itineraryName = localBundle.getString("itineraryName");
      }
      if (localBundle.containsKey("itineraryId")) {
        itineraryId = ((Long)localBundle.getSerializable("itineraryId"));
      }
      if (localBundle.containsKey("importItinerary")) {
        importItinerary = ((Boolean)localBundle.getSerializable("importItinerary"));
      }
    }
  }
  
  public static IntentBuilder_ intent(android.app.Fragment paramFragment)
  {
    return new IntentBuilder_(paramFragment);
  }
  
  public static IntentBuilder_ intent(Context paramContext)
  {
    return new IntentBuilder_(paramContext);
  }
  
  public static IntentBuilder_ intent(android.support.v4.app.Fragment paramFragment)
  {
    return new IntentBuilder_(paramFragment);
  }
  
  public void export(final ConverterFormat paramConverterFormat, final int paramInt)
  {
    BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "")
    {
      public void execute()
      {
        try
        {
          ItineraryActivity_.this.export(paramConverterFormat, paramInt);
          return;
        }
        catch (Throwable localThrowable)
        {
          Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), localThrowable);
        }
      }
    });
  }
  
  public Object getLastCustomNonConfigurationInstance()
  {
    NonConfigurationInstancesHolder localNonConfigurationInstancesHolder = (NonConfigurationInstancesHolder)super.getLastCustomNonConfigurationInstance();
    if (localNonConfigurationInstancesHolder == null) {
      return null;
    }
    return superNonConfigurationInstance;
  }
  
  public void loadItinerary()
  {
    BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "load_itinerary")
    {
      public void execute()
      {
        try
        {
          ItineraryActivity_.this.loadItinerary();
          return;
        }
        catch (Throwable localThrowable)
        {
          Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), localThrowable);
        }
      }
    });
  }
  
  public void onCreate(Bundle paramBundle)
  {
    OnViewChangedNotifier localOnViewChangedNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
    init_(paramBundle);
    super.onCreate(paramBundle);
    OnViewChangedNotifier.replaceNotifier(localOnViewChangedNotifier);
    setContentView(2130903069);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    MenuInflater localMenuInflater = getMenuInflater();
    localMenuInflater.inflate(2131755013, paramMenu);
    localMenuInflater.inflate(2131755010, paramMenu);
    menuPause = paramMenu.findItem(2131624114);
    menuPlay = paramMenu.findItem(2131624113);
    menuDelete = paramMenu.findItem(2131624102);
    menuReset = paramMenu.findItem(2131624110);
    menuStop = paramMenu.findItem(2131624112);
    menuSave = paramMenu.findItem(2131624105);
    menuRename = paramMenu.findItem(2131624104);
    menuCenter = paramMenu.findItem(2131624106);
    menuExport = paramMenu.findItem(2131624107);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public void onItineraryLoaded()
  {
    if (Thread.currentThread() == Looper.getMainLooper().getThread())
    {
      super.onItineraryLoaded();
      return;
    }
    handler_.post(new Runnable()
    {
      public void run()
      {
        ItineraryActivity_.this.onItineraryLoaded();
      }
    });
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((SdkVersionHelper.getSdkInt() < 5) && (paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0)) {
      onBackPressed();
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (super.onOptionsItemSelected(paramMenuItem)) {
      return true;
    }
    int i = paramMenuItem.getItemId();
    if (i == 2131624105)
    {
      menuSave();
      return true;
    }
    if (i == 2131624102)
    {
      menuDelete();
      return true;
    }
    if (i == 2131624108)
    {
      menuExportGpx();
      return true;
    }
    if (i == 2131624106)
    {
      menuCenter();
      return true;
    }
    if (i == 2131624104)
    {
      menuRename();
      return true;
    }
    if (i == 2131624113)
    {
      menuPlay();
      return true;
    }
    if (i == 16908332)
    {
      menuHome();
      return true;
    }
    if (i == 2131624110)
    {
      menuReset();
      return true;
    }
    if (i == 2131624109)
    {
      menuExportKml();
      return true;
    }
    if (i == 2131624114)
    {
      menuPause();
      return true;
    }
    if (i == 2131624112)
    {
      menuStop();
      return true;
    }
    return false;
  }
  
  public NonConfigurationInstancesHolder onRetainCustomNonConfigurationInstance()
  {
    NonConfigurationInstancesHolder localNonConfigurationInstancesHolder = new NonConfigurationInstancesHolder(null);
    superNonConfigurationInstance = super.onRetainCustomNonConfigurationInstance();
    itineraryInfo = itineraryInfo;
    return localNonConfigurationInstancesHolder;
  }
  
  public void onViewChanged(HasViews paramHasViews)
  {
    detailsContainer = ((ViewGroup)paramHasViews.findViewById(2131624039));
    itineraryModeSpinner = ((AppCompatSpinner)paramHasViews.findViewById(2131624041));
    adView = ((AdView)paramHasViews.findViewById(2131624036));
    mapFragment = ((EnhancedMapFragment)findSupportFragmentById(2131624040));
    afterViews();
  }
  
  public void retrieveItineraryPoints(final List<Point> paramList)
  {
    BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "retrieve_itinerary")
    {
      public void execute()
      {
        try
        {
          ItineraryActivity_.this.retrieveItineraryPoints(paramList);
          return;
        }
        catch (Throwable localThrowable)
        {
          Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), localThrowable);
        }
      }
    });
  }
  
  public void setContentView(int paramInt)
  {
    super.setContentView(paramInt);
    onViewChangedNotifier_.notifyViewChanged(this);
  }
  
  public void setContentView(View paramView)
  {
    super.setContentView(paramView);
    onViewChangedNotifier_.notifyViewChanged(this);
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.setContentView(paramView, paramLayoutParams);
    onViewChangedNotifier_.notifyViewChanged(this);
  }
  
  public void setIntent(Intent paramIntent)
  {
    super.setIntent(paramIntent);
    injectExtras_();
  }
  
  public void showDetails(final Integer paramInteger1, final Integer paramInteger2)
  {
    if (Thread.currentThread() == Looper.getMainLooper().getThread())
    {
      super.showDetails(paramInteger1, paramInteger2);
      return;
    }
    handler_.post(new Runnable()
    {
      public void run()
      {
        ItineraryActivity_.this.showDetails(paramInteger1, paramInteger2);
      }
    });
  }
  
  public void showToast(final int paramInt1, final int paramInt2, final Object[] paramArrayOfObject)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        ItineraryActivity_.this.showToast(paramInt1, paramInt2, paramArrayOfObject);
      }
    });
  }
  
  public void showToast(final int paramInt, final String paramString)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        ItineraryActivity_.this.showToast(paramInt, paramString);
      }
    });
  }
  
  public void updateLoadingState(final boolean paramBoolean)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        ItineraryActivity_.this.updateLoadingState(paramBoolean);
      }
    });
  }
  
  public void updateSimulationMenuStates(final SimulationState paramSimulationState)
  {
    if (Thread.currentThread() == Looper.getMainLooper().getThread())
    {
      super.updateSimulationMenuStates(paramSimulationState);
      return;
    }
    handler_.post(new Runnable()
    {
      public void run()
      {
        ItineraryActivity_.this.updateSimulationMenuStates(paramSimulationState);
      }
    });
  }
  
  public static class IntentBuilder_
    extends ActivityIntentBuilder<IntentBuilder_>
  {
    private android.support.v4.app.Fragment fragmentSupport_;
    private android.app.Fragment fragment_;
    
    public IntentBuilder_(android.app.Fragment paramFragment)
    {
      super(ItineraryActivity_.class);
      fragment_ = paramFragment;
    }
    
    public IntentBuilder_(Context paramContext)
    {
      super(ItineraryActivity_.class);
    }
    
    public IntentBuilder_(android.support.v4.app.Fragment paramFragment)
    {
      super(ItineraryActivity_.class);
      fragmentSupport_ = paramFragment;
    }
    
    public IntentBuilder_ importItinerary(Boolean paramBoolean)
    {
      return (IntentBuilder_)super.extra("importItinerary", paramBoolean);
    }
    
    public IntentBuilder_ itineraryId(Long paramLong)
    {
      return (IntentBuilder_)super.extra("itineraryId", paramLong);
    }
    
    public IntentBuilder_ itineraryName(String paramString)
    {
      return (IntentBuilder_)super.extra("itineraryName", paramString);
    }
    
    public void startForResult(int paramInt)
    {
      if (fragmentSupport_ != null)
      {
        fragmentSupport_.startActivityForResult(intent, paramInt);
        return;
      }
      if (fragment_ != null)
      {
        fragment_.startActivityForResult(intent, paramInt);
        return;
      }
      super.startForResult(paramInt);
    }
  }
  
  private static class NonConfigurationInstancesHolder
  {
    public ItineraryInfo itineraryInfo;
    public Object superNonConfigurationInstance;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ItineraryActivity_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */