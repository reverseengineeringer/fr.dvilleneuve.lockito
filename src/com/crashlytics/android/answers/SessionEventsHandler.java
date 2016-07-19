package com.crashlytics.android.answers;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.events.EventsHandler;
import io.fabric.sdk.android.services.events.EventsStrategy;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.util.concurrent.ScheduledExecutorService;

class SessionEventsHandler
  extends EventsHandler<SessionEvent>
{
  SessionEventsHandler(Context paramContext, EventsStrategy<SessionEvent> paramEventsStrategy, SessionAnalyticsFilesManager paramSessionAnalyticsFilesManager, ScheduledExecutorService paramScheduledExecutorService)
  {
    super(paramContext, paramEventsStrategy, paramSessionAnalyticsFilesManager, paramScheduledExecutorService);
  }
  
  protected EventsStrategy<SessionEvent> getDisabledEventsStrategy()
  {
    return new DisabledSessionAnalyticsManagerStrategy();
  }
  
  protected void setAnalyticsSettingsData(final AnalyticsSettingsData paramAnalyticsSettingsData, final String paramString)
  {
    super.executeAsync(new Runnable()
    {
      public void run()
      {
        try
        {
          ((SessionAnalyticsManagerStrategy)strategy).setAnalyticsSettingsData(paramAnalyticsSettingsData, paramString);
          return;
        }
        catch (Exception localException)
        {
          CommonUtils.logControlledError(Answers.getInstance().getContext(), "Crashlytics failed to set analytics settings data.", localException);
        }
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.answers.SessionEventsHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */