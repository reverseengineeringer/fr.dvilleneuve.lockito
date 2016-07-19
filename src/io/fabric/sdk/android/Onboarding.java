package io.fabric.sdk.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.DeliveryMechanism;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.settings.AppRequestData;
import io.fabric.sdk.android.services.settings.AppSettingsData;
import io.fabric.sdk.android.services.settings.CreateAppSpiCall;
import io.fabric.sdk.android.services.settings.IconRequest;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import io.fabric.sdk.android.services.settings.UpdateAppSpiCall;
import java.util.Collection;

class Onboarding
  extends Kit<Boolean>
{
  static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
  private String applicationLabel;
  private String installerPackageName;
  private final Collection<Kit> kits;
  private PackageInfo packageInfo;
  private PackageManager packageManager;
  private String packageName;
  private final HttpRequestFactory requestFactory;
  private String targetAndroidSdkVersion;
  private String versionCode;
  private String versionName;
  
  public Onboarding(Collection<Kit> paramCollection)
  {
    kits = paramCollection;
    requestFactory = new DefaultHttpRequestFactory();
  }
  
  private AppRequestData buildAppRequest(IconRequest paramIconRequest, Collection<Kit> paramCollection)
  {
    Object localObject = getContext();
    String str = new ApiKey().getValue((Context)localObject);
    localObject = CommonUtils.createInstanceIdFrom(new String[] { CommonUtils.resolveBuildId((Context)localObject) });
    int i = DeliveryMechanism.determineFrom(installerPackageName).getId();
    return new AppRequestData(str, getIdManager().getAppIdentifier(), versionName, versionCode, (String)localObject, applicationLabel, i, targetAndroidSdkVersion, "0", paramIconRequest, paramCollection);
  }
  
  private boolean performAutoConfigure(String paramString, AppSettingsData paramAppSettingsData, Collection<Kit> paramCollection)
  {
    boolean bool = true;
    if ("new".equals(status)) {
      if (performCreateApp(paramString, paramAppSettingsData, paramCollection)) {
        bool = Settings.getInstance().loadSettingsSkippingCache();
      }
    }
    do
    {
      return bool;
      Fabric.getLogger().e("Fabric", "Failed to create app with Crashlytics service.", null);
      return false;
      if ("configured".equals(status)) {
        return Settings.getInstance().loadSettingsSkippingCache();
      }
    } while (!updateRequired);
    Fabric.getLogger().d("Fabric", "Server says an update is required - forcing a full App update.");
    performUpdateApp(paramString, paramAppSettingsData, paramCollection);
    return true;
  }
  
  private boolean performCreateApp(String paramString, AppSettingsData paramAppSettingsData, Collection<Kit> paramCollection)
  {
    paramString = buildAppRequest(IconRequest.build(getContext(), paramString), paramCollection);
    return new CreateAppSpiCall(this, getOverridenSpiEndpoint(), url, requestFactory).invoke(paramString);
  }
  
  private boolean performUpdateApp(AppSettingsData paramAppSettingsData, IconRequest paramIconRequest, Collection<Kit> paramCollection)
  {
    paramIconRequest = buildAppRequest(paramIconRequest, paramCollection);
    return new UpdateAppSpiCall(this, getOverridenSpiEndpoint(), url, requestFactory).invoke(paramIconRequest);
  }
  
  private boolean performUpdateApp(String paramString, AppSettingsData paramAppSettingsData, Collection<Kit> paramCollection)
  {
    return performUpdateApp(paramAppSettingsData, IconRequest.build(getContext(), paramString), paramCollection);
  }
  
  protected Boolean doInBackground()
  {
    str = CommonUtils.getAppIconHashOrNull(getContext());
    bool2 = false;
    localObject = null;
    try
    {
      Settings.getInstance().initialize(this, idManager, requestFactory, versionCode, versionName, getOverridenSpiEndpoint()).loadSettingsData();
      SettingsData localSettingsData = Settings.getInstance().awaitSettingsData();
      localObject = localSettingsData;
    }
    catch (Exception localException2)
    {
      try
      {
        bool1 = performAutoConfigure(str, appData, kits);
        return Boolean.valueOf(bool1);
        localException2 = localException2;
        Fabric.getLogger().e("Fabric", "Error dealing with settings", localException2);
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          Fabric.getLogger().e("Fabric", "Error performing auto configuration.", localException1);
          boolean bool1 = bool2;
        }
      }
    }
    bool1 = bool2;
    if (localObject == null) {}
  }
  
  public String getIdentifier()
  {
    return "io.fabric.sdk.android:fabric";
  }
  
  String getOverridenSpiEndpoint()
  {
    return CommonUtils.getStringsFileValue(getContext(), "com.crashlytics.ApiEndpoint");
  }
  
  public String getVersion()
  {
    return "1.3.0.41";
  }
  
  protected boolean onPreExecute()
  {
    try
    {
      installerPackageName = getIdManager().getInstallerPackageName();
      packageManager = getContext().getPackageManager();
      packageName = getContext().getPackageName();
      packageInfo = packageManager.getPackageInfo(packageName, 0);
      versionCode = Integer.toString(packageInfo.versionCode);
      if (packageInfo.versionName == null) {}
      for (String str = "0.0";; str = packageInfo.versionName)
      {
        versionName = str;
        applicationLabel = packageManager.getApplicationLabel(getContext().getApplicationInfo()).toString();
        targetAndroidSdkVersion = Integer.toString(getContextgetApplicationInfotargetSdkVersion);
        return true;
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Fabric.getLogger().e("Fabric", "Failed init", localNameNotFoundException);
    }
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.Onboarding
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */