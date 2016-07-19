package com.crashlytics.android.answers;

import io.fabric.sdk.android.services.events.DisabledEventsStrategy;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;

class DisabledSessionAnalyticsManagerStrategy
  extends DisabledEventsStrategy<SessionEvent>
  implements SessionAnalyticsManagerStrategy<SessionEvent>
{
  public void setAnalyticsSettingsData(AnalyticsSettingsData paramAnalyticsSettingsData, String paramString) {}
}

/* Location:
 * Qualified Name:     com.crashlytics.android.answers.DisabledSessionAnalyticsManagerStrategy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */