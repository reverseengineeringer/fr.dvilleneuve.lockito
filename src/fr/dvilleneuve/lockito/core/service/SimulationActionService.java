package fr.dvilleneuve.lockito.core.service;

import android.app.IntentService;
import android.content.Intent;
import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.manager.ItineraryManager;
import fr.dvilleneuve.lockito.core.manager.NotificationManager;
import fr.dvilleneuve.lockito.core.manager.SimulationManager;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EService;

@EService
public class SimulationActionService
  extends IntentService
{
  public static final String ACTION_LOAD = "load";
  public static final String ACTION_PAUSE = "pause";
  public static final String ACTION_PLAY = "play";
  public static final String ACTION_STOP = "stop";
  public static final String ACTION_UNLOAD = "unload";
  public static final String EXTRA_SIMULATION_ID = "simulationId";
  @Bean
  ItineraryManager itineraryManager;
  @Bean
  NotificationManager notificationManager;
  @Bean
  SimulationManager simulationManager;
  
  public SimulationActionService()
  {
    super(SimulationActionService.class.getSimpleName());
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    String str = paramIntent.getAction();
    Logger.info("Receive action order %s", new Object[] { str });
    if ("load".equals(str))
    {
      paramIntent = itineraryManager.loadItinerary(Long.valueOf(paramIntent.getLongExtra("simulationId", 0L)));
      if (paramIntent != null) {
        simulationManager.startService(paramIntent);
      }
    }
    do
    {
      return;
      if ("unload".equals(str))
      {
        simulationManager.stopService();
        return;
      }
      if ("play".equals(str))
      {
        simulationManager.start();
        return;
      }
      if ("pause".equals(str))
      {
        simulationManager.pause();
        return;
      }
    } while (!"stop".equals(str));
    simulationManager.stop();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.service.SimulationActionService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */