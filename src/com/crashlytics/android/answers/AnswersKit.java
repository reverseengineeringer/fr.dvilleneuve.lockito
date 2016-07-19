package com.crashlytics.android.answers;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.Crash.FatalException;
import io.fabric.sdk.android.services.common.Crash.LoggedException;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.common.IdManager.DeviceIdentifierType;
import io.fabric.sdk.android.services.common.SystemCurrentTimeProvider;
import io.fabric.sdk.android.services.events.GZIPQueueFileEventStorage;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.persistence.FileStoreImpl;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import io.fabric.sdk.android.services.settings.FeaturesSettingsData;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.File;
import java.util.Map;
import java.util.UUID;

abstract class AnswersKit
  extends Kit<Boolean>
{
  static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
  static final long FIRST_LAUNCH_INTERVAL_IN_MS = 3600000L;
  static final String PREFKEY_ANALYTICS_LAUNCHED = "analytics_launched";
  static final String SESSION_ANALYTICS_FILE_EXTENSION = ".tap";
  static final String SESSION_ANALYTICS_FILE_NAME = "session_analytics.tap";
  private static final String SESSION_ANALYTICS_TO_SEND_DIR = "session_analytics_to_send";
  public static final String TAG = "Answers";
  private long installedAt;
  private PreferenceStore preferenceStore;
  SessionAnalyticsManager sessionAnalyticsManager;
  private String versionCode;
  private String versionName;
  
  @SuppressLint({"CommitPrefEdits"})
  @TargetApi(14)
  private void initializeSessionAnalytics(Context paramContext)
  {
    try
    {
      SessionAnalyticsFilesManager localSessionAnalyticsFilesManager = new SessionAnalyticsFilesManager(paramContext, new SessionEventTransform(), new SystemCurrentTimeProvider(), new GZIPQueueFileEventStorage(getContext(), getSdkDirectory(), "session_analytics.tap", "session_analytics_to_send"));
      Object localObject3 = getIdManager();
      Object localObject4 = ((IdManager)localObject3).getDeviceIdentifiers();
      Object localObject1 = paramContext.getPackageName();
      Object localObject2 = ((IdManager)localObject3).getAppInstallIdentifier();
      String str1 = (String)((Map)localObject4).get(IdManager.DeviceIdentifierType.ANDROID_ID);
      String str2 = (String)((Map)localObject4).get(IdManager.DeviceIdentifierType.ANDROID_ADVERTISING_ID);
      localObject4 = (String)((Map)localObject4).get(IdManager.DeviceIdentifierType.FONT_TOKEN);
      String str3 = CommonUtils.resolveBuildId(paramContext);
      String str4 = ((IdManager)localObject3).getOsVersionString();
      localObject3 = ((IdManager)localObject3).getModelName();
      localObject1 = new SessionEventMetadata((String)localObject1, UUID.randomUUID().toString(), (String)localObject2, str1, str2, (String)localObject4, str3, str4, (String)localObject3, versionCode, versionName);
      localObject2 = (Application)getContext().getApplicationContext();
      if ((localObject2 != null) && (Build.VERSION.SDK_INT >= 14)) {}
      for (sessionAnalyticsManager = AutoSessionAnalyticsManager.build((Application)localObject2, (SessionEventMetadata)localObject1, localSessionAnalyticsFilesManager, new DefaultHttpRequestFactory(Fabric.getLogger())); isFirstLaunch(installedAt); sessionAnalyticsManager = SessionAnalyticsManager.build(paramContext, (SessionEventMetadata)localObject1, localSessionAnalyticsFilesManager, new DefaultHttpRequestFactory(Fabric.getLogger())))
      {
        Fabric.getLogger().d("Answers", "First launch");
        sessionAnalyticsManager.onInstall();
        preferenceStore.save(preferenceStore.edit().putBoolean("analytics_launched", true));
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      CommonUtils.logControlledError(paramContext, "Crashlytics failed to initialize session analytics.", localException);
    }
  }
  
  protected Boolean doInBackground()
  {
    Context localContext = getContext();
    initializeSessionAnalytics(localContext);
    try
    {
      SettingsData localSettingsData = Settings.getInstance().awaitSettingsData();
      if (localSettingsData == null) {
        return Boolean.valueOf(false);
      }
      if (featuresData.collectAnalytics)
      {
        sessionAnalyticsManager.setAnalyticsSettingsData(analyticsSettingsData, getOverridenSpiEndpoint());
        return Boolean.valueOf(true);
      }
      CommonUtils.logControlled(localContext, "Disabling analytics collection based on settings flag value.");
      sessionAnalyticsManager.disable();
      return Boolean.valueOf(false);
    }
    catch (Exception localException)
    {
      Fabric.getLogger().e("Answers", "Error dealing with settings", localException);
    }
    return Boolean.valueOf(false);
  }
  
  boolean getAnalyticsLaunched()
  {
    return preferenceStore.get().getBoolean("analytics_launched", false);
  }
  
  public String getIdentifier()
  {
    return "com.crashlytics.sdk.android:answers";
  }
  
  String getOverridenSpiEndpoint()
  {
    return CommonUtils.getStringsFileValue(getContext(), "com.crashlytics.ApiEndpoint");
  }
  
  File getSdkDirectory()
  {
    return new FileStoreImpl(this).getFilesDir();
  }
  
  public String getVersion()
  {
    return "1.1.2.37";
  }
  
  boolean installedRecently(long paramLong)
  {
    return System.currentTimeMillis() - paramLong < 3600000L;
  }
  
  boolean isFirstLaunch(long paramLong)
  {
    return (!getAnalyticsLaunched()) && (installedRecently(paramLong));
  }
  
  void onCustom(String paramString, Map<String, Object> paramMap)
  {
    if (sessionAnalyticsManager != null) {
      sessionAnalyticsManager.onCustom(paramString, paramMap);
    }
  }
  
  public void onException(Crash.FatalException paramFatalException)
  {
    if (sessionAnalyticsManager != null) {
      sessionAnalyticsManager.onCrash(paramFatalException.getSessionId());
    }
  }
  
  public void onException(Crash.LoggedException paramLoggedException)
  {
    if (sessionAnalyticsManager != null) {
      sessionAnalyticsManager.onError(paramLoggedException.getSessionId());
    }
  }
  
  @SuppressLint({"NewApi"})
  protected boolean onPreExecute()
  {
    try
    {
      preferenceStore = new PreferenceStoreImpl(this);
      Context localContext = getContext();
      PackageInfo localPackageInfo = localContext.getPackageManager().getPackageInfo(localContext.getPackageName(), 0);
      versionCode = Integer.toString(versionCode);
      if (versionName == null) {}
      for (String str = "0.0";; str = versionName)
      {
        versionName = str;
        if (Build.VERSION.SDK_INT < 9) {
          break;
        }
        installedAt = firstInstallTime;
        break label133;
      }
      installedAt = new File(getPackageManagergetApplicationInfogetPackageName0sourceDir).lastModified();
    }
    catch (Exception localException)
    {
      Fabric.getLogger().e("Answers", "Error setting up app properties", localException);
      return false;
    }
    label133:
    return true;
  }
  
  void setAnalyticsManager(SessionAnalyticsManager paramSessionAnalyticsManager)
  {
    sessionAnalyticsManager = paramSessionAnalyticsManager;
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.answers.AnswersKit
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */