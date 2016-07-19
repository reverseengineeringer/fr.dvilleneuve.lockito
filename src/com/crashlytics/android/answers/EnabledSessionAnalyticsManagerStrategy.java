package com.crashlytics.android.answers;

import android.content.Context;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.events.EnabledEventsStrategy;
import io.fabric.sdk.android.services.events.FilesSender;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.util.concurrent.ScheduledExecutorService;

class EnabledSessionAnalyticsManagerStrategy
  extends EnabledEventsStrategy<SessionEvent>
  implements SessionAnalyticsManagerStrategy<SessionEvent>
{
  FilesSender filesSender;
  private final HttpRequestFactory httpRequestFactory;
  
  public EnabledSessionAnalyticsManagerStrategy(Context paramContext, ScheduledExecutorService paramScheduledExecutorService, SessionAnalyticsFilesManager paramSessionAnalyticsFilesManager, HttpRequestFactory paramHttpRequestFactory)
  {
    super(paramContext, paramScheduledExecutorService, paramSessionAnalyticsFilesManager);
    httpRequestFactory = paramHttpRequestFactory;
  }
  
  public FilesSender getFilesSender()
  {
    return filesSender;
  }
  
  public void setAnalyticsSettingsData(AnalyticsSettingsData paramAnalyticsSettingsData, String paramString)
  {
    filesSender = new DefaultSessionAnalyticsFilesSender(Answers.getInstance(), paramString, analyticsURL, httpRequestFactory, new ApiKey().getValue(context));
    ((SessionAnalyticsFilesManager)filesManager).setAnalyticsSettingsData(paramAnalyticsSettingsData);
    configureRollover(flushIntervalSeconds);
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.answers.EnabledSessionAnalyticsManagerStrategy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */