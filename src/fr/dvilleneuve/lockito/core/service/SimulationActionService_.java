package fr.dvilleneuve.lockito.core.service;

import android.content.Context;
import fr.dvilleneuve.lockito.core.manager.ItineraryManager_;
import fr.dvilleneuve.lockito.core.manager.NotificationManager_;
import fr.dvilleneuve.lockito.core.manager.SimulationManager_;
import org.androidannotations.api.builder.ServiceIntentBuilder;

public final class SimulationActionService_
  extends SimulationActionService
{
  private void init_()
  {
    notificationManager = NotificationManager_.getInstance_(this);
    simulationManager = SimulationManager_.getInstance_(this);
    itineraryManager = ItineraryManager_.getInstance_(this);
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
      super(SimulationActionService_.class);
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.service.SimulationActionService_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */