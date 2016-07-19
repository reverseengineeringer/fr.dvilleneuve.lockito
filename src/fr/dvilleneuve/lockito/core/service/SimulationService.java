package fr.dvilleneuve.lockito.core.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;
import fr.dvilleneuve.lockito.core.SimulationState;
import fr.dvilleneuve.lockito.core.events.ItineraryChangedEvent;
import fr.dvilleneuve.lockito.core.events.LocationMockedEvent;
import fr.dvilleneuve.lockito.core.events.SimulationStateChangedEvent;
import fr.dvilleneuve.lockito.core.helper.GAnalyticsHelper;
import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.manager.BusManager;
import fr.dvilleneuve.lockito.core.manager.BusMethod;
import fr.dvilleneuve.lockito.core.manager.ItineraryManager;
import fr.dvilleneuve.lockito.core.manager.NotificationManager;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager;
import fr.dvilleneuve.lockito.core.manager.SimulationManager;
import fr.dvilleneuve.lockito.core.model.entity.Itinerary;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.utils.collection.ImmutableFlattenList;
import java.util.Collection;
import java.util.Timer;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.SystemService;

@EService
public class SimulationService
  extends Service
  implements SimulationTask.OnLocationMockListener
{
  public static final String ACTION_PAUSE = "PAUSE";
  public static final String ACTION_PLAY = "PLAY";
  public static final String ACTION_SET_ITINERARY = "SET_ITINERARY";
  public static final String ACTION_STOP = "STOP";
  private static final String CURRENT_PROVIDER = "gps";
  public static final String WAKE_LOCK_TAG = "LockitoService";
  @Bean
  GAnalyticsHelper analyticsHelper;
  @Bean
  BusManager busManager;
  private long distanceDone;
  private ItineraryInfo itineraryInfo;
  @Bean
  ItineraryManager itineraryManager;
  private Location lastMockedLocation;
  @SystemService
  LocationManager locationManager;
  @Bean
  NotificationManager notificationManager;
  @SystemService
  PowerManager powerManager;
  @Bean
  PreferenceManager preferenceManager;
  @Bean
  SimulationManager simulationManager;
  private SimulationTask simulationTask;
  private SimulationState state = SimulationState.UNKNOWN;
  private boolean testProviderInitialized = false;
  private Timer timer;
  private long totalDistance;
  private PowerManager.WakeLock wakeLock;
  
  private void changeState(@NonNull SimulationState paramSimulationState)
  {
    boolean bool = true;
    Logger.debug("Changing state from %s to %s", new Object[] { state, paramSimulationState });
    if (state != paramSimulationState) {
      state = paramSimulationState;
    }
    SimulationTask localSimulationTask;
    switch (paramSimulationState)
    {
    default: 
      notificationManager.showNotification(itineraryInfo, paramSimulationState, null, -1);
      fireStateChanged();
      if (simulationTask != null)
      {
        localSimulationTask = simulationTask;
        if (paramSimulationState != SimulationState.PAUSED) {
          break label130;
        }
      }
      break;
    }
    for (;;)
    {
      localSimulationTask.setPaused(bool);
      return;
      notificationManager.showNotification(itineraryInfo, paramSimulationState, lastMockedLocation, getProgession());
      break;
      label130:
      bool = false;
    }
  }
  
  private void fireStateChanged()
  {
    busManager.post(new SimulationStateChangedEvent(state));
  }
  
  private int getProgession()
  {
    if (totalDistance == 0L) {
      return -1;
    }
    return (int)((float)distanceDone / (float)totalDistance * 100.0F);
  }
  
  private void handleLocationMocked(@Nullable MockedLocation paramMockedLocation, boolean paramBoolean)
  {
    if (paramMockedLocation == null) {
      return;
    }
    final Location localLocation = paramMockedLocation.getLocation();
    try
    {
      simulationManager.safelyExecuteMockLocationAction(new Runnable()
      {
        public void run()
        {
          locationManager.setTestProviderLocation(localLocation.getProvider(), localLocation);
        }
      });
      if (lastMockedLocation != null)
      {
        if (paramBoolean) {
          distanceDone = totalDistance;
        }
      }
      else
      {
        lastMockedLocation = localLocation;
        Logger.info("Mocked location %s, seconds = %f, distance = %d, distanceDone = %d/%d", new Object[] { paramMockedLocation.getLocation(), Double.valueOf(paramMockedLocation.getSecondsAfterLast()), Long.valueOf(paramMockedLocation.getDistance()), Long.valueOf(distanceDone), Long.valueOf(totalDistance) });
        notificationManager.showNotification(itineraryInfo, state, localLocation, getProgession());
        busManager.post(new LocationMockedEvent(localLocation));
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Logger.warn("It seems GPS test provider was not set", new Object[0]);
        analyticsHelper.eventKnownError(localException);
        continue;
        distanceDone += paramMockedLocation.getDistance();
      }
    }
  }
  
  private void handleStartCommand(@Nullable Intent paramIntent)
  {
    if (paramIntent == null) {}
    do
    {
      return;
      paramIntent = paramIntent.getAction();
      Logger.info("Start service with action %s", new Object[] { paramIntent });
      if (!"SET_ITINERARY".equals(paramIntent)) {
        break;
      }
      paramIntent = state;
      setItineraryInfo(simulationManager.getItineraryInfo());
    } while (paramIntent != state);
    fireStateChanged();
    return;
    if ("PLAY".equals(paramIntent))
    {
      start();
      return;
    }
    if ("PAUSE".equals(paramIntent))
    {
      pause();
      return;
    }
    if ("STOP".equals(paramIntent))
    {
      stop();
      return;
    }
    Logger.warn("Unkown action received: %s", new Object[] { paramIntent });
  }
  
  private void pause()
  {
    Logger.info("Pause simulation", new Object[0]);
    changeState(SimulationState.PAUSED);
  }
  
  private void resume()
  {
    Logger.info("Resume simulation", new Object[0]);
    changeState(SimulationState.RUNNING);
  }
  
  private void setItineraryInfo(@Nullable ItineraryInfo paramItineraryInfo)
  {
    itineraryInfo = paramItineraryInfo;
    if (paramItineraryInfo != null)
    {
      if (paramItineraryInfo.getItinerary().getAllPoints().isEmpty())
      {
        changeState(SimulationState.NOT_READY);
        return;
      }
      if (state == SimulationState.UNKNOWN)
      {
        changeState(SimulationState.STOPPED);
        return;
      }
      changeState(state);
      return;
    }
    changeState(SimulationState.NOT_READY);
  }
  
  private void start()
  {
    Logger.info("Start simulation", new Object[0]);
    if (state == SimulationState.PAUSED)
    {
      resume();
      return;
    }
    if (state == SimulationState.RUNNING)
    {
      Logger.warn("Tried to start simulation, but there is already a running one.", new Object[0]);
      return;
    }
    if (itineraryInfo == null)
    {
      Logger.error("Something went wrong. The itinerary shouldn't be null here", new Object[0]);
      return;
    }
    int i = preferenceManager.getDelayBetweenLocations();
    totalDistance = itineraryInfo.getDistance();
    distanceDone = 0L;
    try
    {
      simulationManager.safelyExecuteMockLocationAction(new Runnable()
      {
        public void run()
        {
          if (!testProviderInitialized)
          {
            SimulationService.access$002(SimulationService.this, true);
            locationManager.addTestProvider("gps", false, true, false, false, true, true, true, 1, 1);
          }
          locationManager.setTestProviderEnabled("gps", true);
          locationManager.setTestProviderStatus("gps", 2, null, System.currentTimeMillis());
        }
      });
      itineraryManager.incrementPlayCounter(itineraryInfo);
      changeState(SimulationState.RUNNING);
      stopScheduler();
      if (preferenceManager.shouldUseWakelock()) {
        wakeLock.acquire();
      }
      if (simulationTask != null) {
        simulationTask.setListener(null);
      }
      simulationTask = new SimulationTask("gps", itineraryInfo, this);
      timer = new Timer("GPS_MOCKING_SCHEDULER", true);
      timer.schedule(simulationTask, 0L, i * 1000);
      return;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        Logger.error("Couldn't setup test provider", localSecurityException, new Object[0]);
        analyticsHelper.eventKnownError(localSecurityException);
        Toast.makeText(this, 2131165437, 1).show();
      }
    }
  }
  
  private void stop()
  {
    Logger.info("Stop simulation", new Object[0]);
    changeState(SimulationState.STOPPED);
    stopScheduler();
    if (testProviderInitialized) {}
    try
    {
      simulationManager.safelyExecuteMockLocationAction(new Runnable()
      {
        public void run()
        {
          locationManager.clearTestProviderEnabled("gps");
          locationManager.clearTestProviderStatus("gps");
          locationManager.clearTestProviderLocation("gps");
        }
      });
      return;
    }
    catch (Exception localException)
    {
      Logger.warn("It seems GPS test provider was not set", new Object[0]);
      analyticsHelper.eventKnownError(localException);
    }
  }
  
  private void stopAndClear()
  {
    Logger.debug("Stop and clear test provider configuration", new Object[0]);
    stop();
    setItineraryInfo(null);
    notificationManager.removeNotification();
    if (testProviderInitialized) {}
    try
    {
      testProviderInitialized = false;
      simulationManager.safelyExecuteMockLocationAction(new Runnable()
      {
        public void run()
        {
          locationManager.removeTestProvider("gps");
        }
      });
      return;
    }
    catch (Exception localException)
    {
      Logger.warn("It seems GPS test provider was not set", new Object[0]);
      analyticsHelper.eventKnownError(localException);
    }
  }
  
  private void stopScheduler()
  {
    if ((wakeLock != null) && (wakeLock.isHeld())) {
      wakeLock.release();
    }
    if (timer != null)
    {
      timer.cancel();
      timer = null;
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    Logger.info("Create service", new Object[0]);
    wakeLock = powerManager.newWakeLock(1, "LockitoService");
    busManager.register(this);
  }
  
  public void onDestroy()
  {
    Logger.info("Destroy service", new Object[0]);
    busManager.unregister(this);
    stopAndClear();
    super.onDestroy();
  }
  
  @BusMethod
  public void onEventBackgroundThread(ItineraryChangedEvent paramItineraryChangedEvent)
  {
    if (legs.isEmpty())
    {
      changeState(SimulationState.NOT_READY);
      return;
    }
    changeState(SimulationState.STOPPED);
  }
  
  public void onLocationFinished(MockedLocation paramMockedLocation)
  {
    Logger.info("Location finished at %s", new Object[] { paramMockedLocation.getLocation().toString() });
    handleLocationMocked(paramMockedLocation, true);
    if (preferenceManager.shouldStopWhenFinished()) {
      stop();
    }
  }
  
  public void onLocationMocked(MockedLocation paramMockedLocation)
  {
    handleLocationMocked(paramMockedLocation, false);
  }
  
  public void onLocationStarted(MockedLocation paramMockedLocation)
  {
    Logger.info("Location started at %s", new Object[] { paramMockedLocation.getLocation().toString() });
    handleLocationMocked(paramMockedLocation, false);
  }
  
  public void onStart(Intent paramIntent, int paramInt)
  {
    handleStartCommand(paramIntent);
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    handleStartCommand(paramIntent);
    return 1;
  }
  
  @TargetApi(14)
  public void onTaskRemoved(Intent paramIntent)
  {
    stopAndClear();
    stopSelf();
    super.onTaskRemoved(paramIntent);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.service.SimulationService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */