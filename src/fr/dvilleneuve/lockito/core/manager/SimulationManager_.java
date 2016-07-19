package fr.dvilleneuve.lockito.core.manager;

import android.content.Context;
import fr.dvilleneuve.lockito.core.helper.GAnalyticsHelper_;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SimulationManager_
  extends SimulationManager
{
  private static SimulationManager_ instance_;
  private Context context_;
  
  private SimulationManager_(Context paramContext)
  {
    context_ = paramContext;
  }
  
  public static SimulationManager_ getInstance_(Context paramContext)
  {
    if (instance_ == null)
    {
      OnViewChangedNotifier localOnViewChangedNotifier = OnViewChangedNotifier.replaceNotifier(null);
      instance_ = new SimulationManager_(paramContext.getApplicationContext());
      instance_.init_();
      OnViewChangedNotifier.replaceNotifier(localOnViewChangedNotifier);
    }
    return instance_;
  }
  
  private void init_()
  {
    context = context_;
    analyticsHelper = GAnalyticsHelper_.getInstance_(context_);
    busManager = BusManager_.getInstance_(context_);
    init();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.manager.SimulationManager_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */