package fr.dvilleneuve.lockito.core.helper;

import android.content.Context;
import fr.dvilleneuve.lockito.LockitoApplication_;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class GAnalyticsHelper_
  extends GAnalyticsHelper
{
  private static GAnalyticsHelper_ instance_;
  private Context context_;
  
  private GAnalyticsHelper_(Context paramContext)
  {
    context_ = paramContext;
  }
  
  public static GAnalyticsHelper_ getInstance_(Context paramContext)
  {
    if (instance_ == null)
    {
      OnViewChangedNotifier localOnViewChangedNotifier = OnViewChangedNotifier.replaceNotifier(null);
      instance_ = new GAnalyticsHelper_(paramContext.getApplicationContext());
      instance_.init_();
      OnViewChangedNotifier.replaceNotifier(localOnViewChangedNotifier);
    }
    return instance_;
  }
  
  private void init_()
  {
    application = LockitoApplication_.getInstance();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.helper.GAnalyticsHelper_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */