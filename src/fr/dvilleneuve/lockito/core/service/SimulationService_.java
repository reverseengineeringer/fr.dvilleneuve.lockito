package fr.dvilleneuve.lockito.core.service;

import android.content.Context;
import android.location.LocationManager;
import android.os.PowerManager;
import fr.dvilleneuve.lockito.core.helper.GAnalyticsHelper_;
import fr.dvilleneuve.lockito.core.manager.BusManager_;
import fr.dvilleneuve.lockito.core.manager.ItineraryManager_;
import fr.dvilleneuve.lockito.core.manager.NotificationManager_;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager_;
import fr.dvilleneuve.lockito.core.manager.SimulationManager_;
import org.androidannotations.api.builder.ServiceIntentBuilder;

public final class SimulationService_
  extends SimulationService
{
  private void init_()
  {
    locationManager = ((LocationManager)getSystemService("location"));
    powerManager = ((PowerManager)getSystemService("power"));
    itineraryManager = ItineraryManager_.getInstance_(this);
    busManager = BusManager_.getInstance_(this);
    simulationManager = SimulationManager_.getInstance_(this);
    analyticsHelper = GAnalyticsHelper_.getInstance_(this);
    preferenceManager = PreferenceManager_.getInstance_(this);
    notificationManager = NotificationManager_.getInstance_(this);
  }
  
  public static IntentBuilder_ intent(Context paramContext)
  {
    return new IntentBuilder_(paramContext);
  }
  
  public void onCreate()
  {
    init_();
    super.onCreate();
  }
  
  public static class IntentBuilder_
    extends ServiceIntentBuilder<IntentBuilder_>
  {
    public IntentBuilder_(Context paramContext)
    {
      super(SimulationService_.class);
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.service.SimulationService_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */