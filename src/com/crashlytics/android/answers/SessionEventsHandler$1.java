package com.crashlytics.android.answers;

import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;

class SessionEventsHandler$1
  implements Runnable
{
  SessionEventsHandler$1(SessionEventsHandler paramSessionEventsHandler, AnalyticsSettingsData paramAnalyticsSettingsData, String paramString) {}
  
  public void run()
  {
    try
    {
      ((SessionAnalyticsManagerStrategy)SessionEventsHandler.access$000(this$0)).setAnalyticsSettingsData(val$analyticsSettingsData, val$protocolAndHostOverride);
      return;
    }
    catch (Exception localException)
    {
      CommonUtils.logControlledError(Answers.getInstance().getContext(), "Crashlytics failed to set analytics settings data.", localException);
    }
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.answers.SessionEventsHandler.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */