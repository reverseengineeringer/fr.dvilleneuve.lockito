package fr.dvilleneuve.lockito.core.manager;

import android.app.AppOpsManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Process;
import android.provider.Settings.Secure;
import fr.dvilleneuve.lockito.core.SimulationState;
import fr.dvilleneuve.lockito.core.events.SimulationStateChangedEvent;
import fr.dvilleneuve.lockito.core.helper.GAnalyticsHelper;
import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.service.SimulationService_;
import fr.dvilleneuve.lockito.core.service.SimulationService_.IntentBuilder_;
import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;
import org.androidannotations.annotations.RootContext;

@EBean(scope=EBean.Scope.Singleton)
public class SimulationManager
{
  @Bean
  GAnalyticsHelper analyticsHelper;
  @Bean
  BusManager busManager;
  @RootContext
  Context context;
  private ItineraryInfo importedItineraryInfo;
  private ItineraryInfo itineraryInfo;
  private boolean mockLocationStateForced = false;
  private SimulationState state;
  
  private void sendAction(String paramString)
  {
    Intent localIntent = SimulationService_.intent(context).get();
    localIntent.setAction(paramString);
    context.startService(localIntent);
  }
  
  public boolean forceMockLocationState(boolean paramBoolean)
  {
    try
    {
      ContentResolver localContentResolver = context.getContentResolver();
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        paramBoolean = Settings.Secure.putInt(localContentResolver, "mock_location", i);
        return paramBoolean;
      }
      return false;
    }
    catch (Exception localException)
    {
      Logger.error("Can't force mock locations", localException, new Object[0]);
    }
  }
  
  public ItineraryInfo getImportedItineraryInfo()
  {
    return importedItineraryInfo;
  }
  
  public ItineraryInfo getItineraryInfo()
  {
    return itineraryInfo;
  }
  
  public SimulationState getState()
  {
    return state;
  }
  
  @AfterInject
  void init()
  {
    busManager.register(this);
  }
  
  public boolean isLaunched()
  {
    return (isRunning()) || (isPaused());
  }
  
  public boolean isLocationMockEnabled()
  {
    if (Build.VERSION.SDK_INT >= 23)
    {
      if (((AppOpsManager)context.getSystemService("appops")).checkOpNoThrow("android:mock_location", Process.myUid(), "fr.dvilleneuve.lockito") != 0) {}
    }
    else {
      while (Settings.Secure.getString(context.getContentResolver(), "mock_location").equals("1")) {
        return true;
      }
    }
    Logger.debug("Mock locations disabled. Try to force it (in case device has been rooted)", new Object[0]);
    if ((forceMockLocationState(true)) && (forceMockLocationState(false)))
    {
      Logger.info("Forced mock locations", new Object[0]);
      mockLocationStateForced = true;
      return true;
    }
    Logger.error("Can't force mock locations", new Object[0]);
    return false;
  }
  
  public boolean isPaused()
  {
    return state == SimulationState.PAUSED;
  }
  
  public boolean isRunning()
  {
    return state == SimulationState.RUNNING;
  }
  
  @BusMethod
  public void onEventMainThread(SimulationStateChangedEvent paramSimulationStateChangedEvent)
  {
    state = state;
    analyticsHelper.eventSimulation(state);
  }
  
  public void pause()
  {
    sendAction("PAUSE");
  }
  
  public void safelyExecuteMockLocationAction(Runnable paramRunnable)
  {
    if (mockLocationStateForced)
    {
      forceMockLocationState(true);
      try
      {
        paramRunnable.run();
        return;
      }
      finally
      {
        forceMockLocationState(false);
      }
    }
    paramRunnable.run();
  }
  
  public void setImportedItineraryInfo(ItineraryInfo paramItineraryInfo)
  {
    importedItineraryInfo = paramItineraryInfo;
  }
  
  public void start()
  {
    sendAction("PLAY");
  }
  
  public void startService(ItineraryInfo paramItineraryInfo)
  {
    itineraryInfo = paramItineraryInfo;
    paramItineraryInfo = SimulationService_.intent(context).get();
    paramItineraryInfo.setAction("SET_ITINERARY");
    context.startService(paramItineraryInfo);
  }
  
  public void stop()
  {
    sendAction("STOP");
  }
  
  public void stopService()
  {
    context.stopService(SimulationService_.intent(context).get());
    itineraryInfo = null;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.manager.SimulationManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */