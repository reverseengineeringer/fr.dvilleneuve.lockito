package com.crashlytics.android;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.File;

class CrashlyticsUncaughtExceptionHandler$14
  implements Runnable
{
  CrashlyticsUncaughtExceptionHandler$14(CrashlyticsUncaughtExceptionHandler paramCrashlyticsUncaughtExceptionHandler, File paramFile) {}
  
  public void run()
  {
    if (CommonUtils.canTryConnection(CrashlyticsUncaughtExceptionHandler.access$700(this$0).getContext()))
    {
      Fabric.getLogger().d("Fabric", "Attempting to send crash report at time of crash...");
      Object localObject = Settings.getInstance().awaitSettingsData();
      localObject = CrashlyticsUncaughtExceptionHandler.access$700(this$0).getCreateReportSpiCall((SettingsData)localObject);
      if (localObject != null) {
        new ReportUploader((CreateReportSpiCall)localObject).forceUpload(new SessionReport(val$toSend, CrashlyticsUncaughtExceptionHandler.access$1200()));
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.CrashlyticsUncaughtExceptionHandler.14
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */