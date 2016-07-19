package com.crashlytics.android.beta;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.settings.BetaSettingsData;

class CheckForUpdatesController
{
  static final long LAST_UPDATE_CHECK_DEFAULT = 0L;
  static final String LAST_UPDATE_CHECK_KEY = "last_update_check";
  private final Beta beta;
  private final BetaSettingsData betaSettings;
  private final BuildProperties buildProps;
  private final Context context;
  private final CurrentTimeProvider currentTimeProvider;
  private final HttpRequestFactory httpRequestFactory;
  private final IdManager idManager;
  private final PreferenceStore preferenceStore;
  
  public CheckForUpdatesController(Context paramContext, Beta paramBeta, IdManager paramIdManager, BetaSettingsData paramBetaSettingsData, BuildProperties paramBuildProperties, PreferenceStore paramPreferenceStore, CurrentTimeProvider paramCurrentTimeProvider, HttpRequestFactory paramHttpRequestFactory)
  {
    context = paramContext;
    beta = paramBeta;
    idManager = paramIdManager;
    betaSettings = paramBetaSettingsData;
    buildProps = paramBuildProperties;
    preferenceStore = paramPreferenceStore;
    currentTimeProvider = paramCurrentTimeProvider;
    httpRequestFactory = paramHttpRequestFactory;
  }
  
  public void checkForUpdates()
  {
    long l1 = currentTimeProvider.getCurrentTimeMillis();
    long l2 = betaSettings.updateSuspendDurationSeconds * 1000;
    Fabric.getLogger().d("Beta", "Check for updates delay: " + l2);
    long l3 = preferenceStore.get().getLong("last_update_check", 0L);
    Fabric.getLogger().d("Beta", "Check for updates last check time: " + l3);
    l2 = l3 + l2;
    Fabric.getLogger().d("Beta", "Check for updates current time: " + l1 + ", next check time: " + l2);
    if (l1 >= l2) {
      try
      {
        Fabric.getLogger().d("Beta", "Performing update check");
        String str1 = new ApiKey().getValue(context);
        String str2 = idManager.createIdHeaderValue(str1, buildProps.packageName);
        new CheckForUpdatesRequest(beta, beta.getOverridenSpiEndpoint(), betaSettings.updateUrl, httpRequestFactory, new CheckForUpdatesResponseTransform()).invoke(str1, str2, buildProps);
        return;
      }
      finally
      {
        preferenceStore.edit().putLong("last_update_check", l1).commit();
      }
    }
    Fabric.getLogger().d("Beta", "Check for updates next check time was not passed");
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.beta.CheckForUpdatesController
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */