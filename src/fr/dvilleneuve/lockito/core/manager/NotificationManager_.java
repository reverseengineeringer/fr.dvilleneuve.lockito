package fr.dvilleneuve.lockito.core.manager;

import android.content.Context;
import fr.dvilleneuve.lockito.core.helper.StringFormatHelper_;

public final class NotificationManager_
  extends NotificationManager
{
  private Context context_;
  
  private NotificationManager_(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
  
  public static NotificationManager_ getInstance_(Context paramContext)
  {
    return new NotificationManager_(paramContext);
  }
  
  private void init_()
  {
    notificationManager = ((android.app.NotificationManager)context_.getSystemService("notification"));
    context = context_;
    stringFormatHelper = StringFormatHelper_.getInstance_(context_);
    simulationManager = SimulationManager_.getInstance_(context_);
  }
  
  public void rebind(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.manager.NotificationManager_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */