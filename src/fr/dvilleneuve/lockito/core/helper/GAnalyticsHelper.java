package fr.dvilleneuve.lockito.core.helper;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.Tracker;
import fr.dvilleneuve.lockito.LockitoApplication;
import fr.dvilleneuve.lockito.core.SimulationState;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;

@EBean(scope=EBean.Scope.Singleton)
public class GAnalyticsHelper
{
  @App
  LockitoApplication application;
  private Tracker tracker;
  
  private Tracker getTracker()
  {
    try
    {
      if (tracker == null)
      {
        localObject1 = GoogleAnalytics.getInstance(application);
        ((GoogleAnalytics)localObject1).enableAutoActivityReports(application);
        tracker = ((GoogleAnalytics)localObject1).newTracker(2131034113);
      }
      Object localObject1 = tracker;
      return (Tracker)localObject1;
    }
    finally {}
  }
  
  public void eventKnownError(Exception paramException)
  {
    getTracker().send(new HitBuilders.EventBuilder().setCategory("known_error").setAction("exception").setLabel(paramException.getMessage()).build());
  }
  
  public void eventSimulation(SimulationState paramSimulationState)
  {
    getTracker().send(new HitBuilders.EventBuilder().setCategory("simulation_action").setAction("change_state").setLabel(paramSimulationState.name()).build());
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.helper.GAnalyticsHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */