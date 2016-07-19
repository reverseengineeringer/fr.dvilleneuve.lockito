package com.crashlytics.android;

import android.app.Activity;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.settings.Settings.SettingsAccess;
import io.fabric.sdk.android.services.settings.SettingsData;

class Crashlytics$6
  implements Settings.SettingsAccess<Boolean>
{
  Crashlytics$6(Crashlytics paramCrashlytics) {}
  
  public Boolean usingSettings(SettingsData paramSettingsData)
  {
    boolean bool2 = true;
    Activity localActivity = this$0.getFabric().getCurrentActivity();
    boolean bool1 = bool2;
    if (localActivity != null)
    {
      bool1 = bool2;
      if (!localActivity.isFinishing())
      {
        bool1 = bool2;
        if (this$0.shouldPromptUserBeforeSendingCrashReports()) {
          bool1 = Crashlytics.access$100(this$0, localActivity, promptData);
        }
      }
    }
    return Boolean.valueOf(bool1);
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.Crashlytics.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */