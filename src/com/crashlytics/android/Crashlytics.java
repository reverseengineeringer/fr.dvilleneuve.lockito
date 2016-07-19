package com.crashlytics.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.ScrollView;
import android.widget.TextView;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.beta.Beta;
import com.crashlytics.android.internal.CrashEventDataProvider;
import com.crashlytics.android.internal.models.SessionEventData;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.KitGroup;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.Crash.FatalException;
import io.fabric.sdk.android.services.common.Crash.LoggedException;
import io.fabric.sdk.android.services.common.ExecutorUtils;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import io.fabric.sdk.android.services.concurrency.Priority;
import io.fabric.sdk.android.services.concurrency.PriorityCallable;
import io.fabric.sdk.android.services.concurrency.Task;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.persistence.FileStoreImpl;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import io.fabric.sdk.android.services.settings.AppSettingsData;
import io.fabric.sdk.android.services.settings.FeaturesSettingsData;
import io.fabric.sdk.android.services.settings.PromptSettingsData;
import io.fabric.sdk.android.services.settings.SessionSettingsData;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.Settings.SettingsAccess;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.net.ssl.HttpsURLConnection;

@DependsOn({CrashEventDataProvider.class})
public class Crashlytics
  extends Kit<Void>
  implements KitGroup
{
  static final float CLS_DEFAULT_PROCESS_DELAY = 1.0F;
  static final String COLLECT_CUSTOM_KEYS = "com.crashlytics.CollectCustomKeys";
  static final String COLLECT_CUSTOM_LOGS = "com.crashlytics.CollectCustomLogs";
  static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
  static final String CRASHLYTICS_REQUIRE_BUILD_ID = "com.crashlytics.RequireBuildId";
  static final boolean CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT = true;
  static final int DEFAULT_MAIN_HANDLER_TIMEOUT_SEC = 4;
  private static final String INITIALIZATION_MARKER_FILE_NAME = "initialization_marker";
  static final int MAX_ATTRIBUTES = 64;
  static final int MAX_ATTRIBUTE_SIZE = 1024;
  private static final String PREF_ALWAYS_SEND_REPORTS_KEY = "always_send_reports_opt_in";
  private static final boolean SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT = false;
  public static final String TAG = "Fabric";
  private final ConcurrentHashMap<String, String> attributes = new ConcurrentHashMap();
  private String buildId;
  private float delay;
  private boolean disabled;
  private CrashlyticsExecutorServiceWrapper executorServiceWrapper;
  private CrashEventDataProvider externalCrashEventDataProvider;
  private CrashlyticsUncaughtExceptionHandler handler;
  private HttpRequestFactory httpRequestFactory;
  private File initializationMarkerFile;
  private String installerPackageName;
  private final Collection<Kit<Boolean>> kits;
  private CrashlyticsListener listener;
  private String packageName;
  private final PinningInfoProvider pinningInfo;
  private final long startTime = System.currentTimeMillis();
  private String userEmail = null;
  private String userId = null;
  private String userName = null;
  private String versionCode;
  private String versionName;
  
  public Crashlytics()
  {
    this(1.0F, null, null, false);
  }
  
  Crashlytics(float paramFloat, CrashlyticsListener paramCrashlyticsListener, PinningInfoProvider paramPinningInfoProvider, boolean paramBoolean)
  {
    this(paramFloat, paramCrashlyticsListener, paramPinningInfoProvider, paramBoolean, ExecutorUtils.buildSingleThreadExecutorService("Crashlytics Exception Handler"));
  }
  
  Crashlytics(float paramFloat, CrashlyticsListener paramCrashlyticsListener, PinningInfoProvider paramPinningInfoProvider, boolean paramBoolean, ExecutorService paramExecutorService)
  {
    delay = paramFloat;
    listener = paramCrashlyticsListener;
    pinningInfo = paramPinningInfoProvider;
    disabled = paramBoolean;
    executorServiceWrapper = new CrashlyticsExecutorServiceWrapper(paramExecutorService);
    kits = Collections.unmodifiableCollection(Arrays.asList(new Kit[] { new Answers(), new Beta() }));
  }
  
  private int dipsToPixels(float paramFloat, int paramInt)
  {
    return (int)(paramInt * paramFloat);
  }
  
  private static void doLog(int paramInt, String paramString1, String paramString2)
  {
    if (isDisabled()) {}
    Crashlytics localCrashlytics;
    do
    {
      return;
      localCrashlytics = getInstance();
    } while (!ensureFabricWithCalled("prior to logging messages.", localCrashlytics));
    long l1 = System.currentTimeMillis();
    long l2 = startTime;
    handler.writeToLog(l1 - l2, formatLogMessage(paramInt, paramString1, paramString2));
  }
  
  private static boolean ensureFabricWithCalled(String paramString, Crashlytics paramCrashlytics)
  {
    if ((paramCrashlytics == null) || (handler == null))
    {
      Fabric.getLogger().e("Fabric", "Crashlytics must be initialized by calling Fabric.with(Context) " + paramString, null);
      return false;
    }
    return true;
  }
  
  private void finishInitSynchronously()
  {
    Object localObject = new PriorityCallable()
    {
      public Void call()
        throws Exception
      {
        return doInBackground();
      }
      
      public Priority getPriority()
      {
        return Priority.IMMEDIATE;
      }
    };
    Iterator localIterator = getDependencies().iterator();
    while (localIterator.hasNext()) {
      ((PriorityCallable)localObject).addDependency((Task)localIterator.next());
    }
    localObject = getFabric().getExecutorService().submit((Callable)localObject);
    Fabric.getLogger().d("Fabric", "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
    try
    {
      ((Future)localObject).get(4L, TimeUnit.SECONDS);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      Fabric.getLogger().e("Fabric", "Crashlytics was interrupted during initialization.", localInterruptedException);
      return;
    }
    catch (ExecutionException localExecutionException)
    {
      Fabric.getLogger().e("Fabric", "Problem encountered during Crashlytics initialization.", localExecutionException);
      return;
    }
    catch (TimeoutException localTimeoutException)
    {
      Fabric.getLogger().e("Fabric", "Crashlytics timed out during initialization.", localTimeoutException);
    }
  }
  
  private static String formatLogMessage(int paramInt, String paramString1, String paramString2)
  {
    return CommonUtils.logPriorityToString(paramInt) + "/" + paramString1 + " " + paramString2;
  }
  
  public static Crashlytics getInstance()
  {
    try
    {
      Crashlytics localCrashlytics = (Crashlytics)Fabric.getKit(Crashlytics.class);
      return localCrashlytics;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      Fabric.getLogger().e("Fabric", "Crashlytics must be initialized by calling Fabric.with(Context) prior to calling Crashlytics.getInstance()", null);
      throw localIllegalStateException;
    }
  }
  
  public static PinningInfoProvider getPinningInfoProvider()
  {
    if (!isDisabled()) {
      return getInstancepinningInfo;
    }
    return null;
  }
  
  private boolean getSendDecisionFromUser(final Activity paramActivity, final PromptSettingsData paramPromptSettingsData)
  {
    final DialogStringResolver localDialogStringResolver = new DialogStringResolver(paramActivity, paramPromptSettingsData);
    final OptInLatch localOptInLatch = new OptInLatch(null);
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
        Object localObject = new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            val$latch.setOptIn(true);
            paramAnonymous2DialogInterface.dismiss();
          }
        };
        float f = paramActivitygetResourcesgetDisplayMetricsdensity;
        int i = Crashlytics.this.dipsToPixels(f, 5);
        TextView localTextView = new TextView(paramActivity);
        localTextView.setAutoLinkMask(15);
        localTextView.setText(localDialogStringResolver.getMessage());
        localTextView.setTextAppearance(paramActivity, 16973892);
        localTextView.setPadding(i, i, i, i);
        localTextView.setFocusable(false);
        ScrollView localScrollView = new ScrollView(paramActivity);
        localScrollView.setPadding(Crashlytics.this.dipsToPixels(f, 14), Crashlytics.this.dipsToPixels(f, 2), Crashlytics.this.dipsToPixels(f, 10), Crashlytics.this.dipsToPixels(f, 12));
        localScrollView.addView(localTextView);
        localBuilder.setView(localScrollView).setTitle(localDialogStringResolver.getTitle()).setCancelable(false).setNeutralButton(localDialogStringResolver.getSendButtonTitle(), (DialogInterface.OnClickListener)localObject);
        if (paramPromptSettingsDatashowCancelButton)
        {
          localObject = new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              val$latch.setOptIn(false);
              paramAnonymous2DialogInterface.dismiss();
            }
          };
          localBuilder.setNegativeButton(localDialogStringResolver.getCancelButtonTitle(), (DialogInterface.OnClickListener)localObject);
        }
        if (paramPromptSettingsDatashowAlwaysSendButton)
        {
          localObject = new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              setShouldSendUserReportsWithoutPrompting(true);
              val$latch.setOptIn(true);
              paramAnonymous2DialogInterface.dismiss();
            }
          };
          localBuilder.setPositiveButton(localDialogStringResolver.getAlwaysSendButtonTitle(), (DialogInterface.OnClickListener)localObject);
        }
        localBuilder.show();
      }
    });
    Fabric.getLogger().d("Fabric", "Waiting for user opt-in.");
    localOptInLatch.await();
    return localOptInLatch.getOptIn();
  }
  
  private static boolean isDisabled()
  {
    Crashlytics localCrashlytics = getInstance();
    return (localCrashlytics == null) || (disabled);
  }
  
  private boolean isRequiringBuildId(Context paramContext)
  {
    return CommonUtils.getBooleanResourceValue(paramContext, "com.crashlytics.RequireBuildId", true);
  }
  
  public static void log(int paramInt, String paramString1, String paramString2)
  {
    doLog(paramInt, paramString1, paramString2);
    Fabric.getLogger().log(paramInt, "" + paramString1, "" + paramString2, true);
  }
  
  public static void log(String paramString)
  {
    doLog(3, "Fabric", paramString);
  }
  
  public static void logException(Throwable paramThrowable)
  {
    if (isDisabled()) {}
    Crashlytics localCrashlytics;
    do
    {
      return;
      localCrashlytics = getInstance();
    } while (!ensureFabricWithCalled("prior to logging exceptions.", localCrashlytics));
    if (paramThrowable == null)
    {
      Fabric.getLogger().log(5, "Fabric", "Crashlytics is ignoring a request to log a null exception.");
      return;
    }
    handler.writeNonFatalException(Thread.currentThread(), paramThrowable);
  }
  
  static void recordFatalExceptionEvent(String paramString)
  {
    Answers localAnswers = (Answers)Fabric.getKit(Answers.class);
    if (localAnswers != null) {
      localAnswers.onException(new Crash.FatalException(paramString));
    }
  }
  
  static void recordLoggedExceptionEvent(String paramString)
  {
    Answers localAnswers = (Answers)Fabric.getKit(Answers.class);
    if (localAnswers != null) {
      localAnswers.onException(new Crash.LoggedException(paramString));
    }
  }
  
  private static String sanitizeAttribute(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      paramString = paramString.trim();
      str = paramString;
      if (paramString.length() > 1024) {
        str = paramString.substring(0, 1024);
      }
    }
    return str;
  }
  
  private void setAndValidateKitProperties(Context paramContext, String paramString)
  {
    Object localObject;
    if (pinningInfo != null)
    {
      localObject = new CrashlyticsPinningInfoProvider(pinningInfo);
      httpRequestFactory = new DefaultHttpRequestFactory(Fabric.getLogger());
      httpRequestFactory.setPinningInfoProvider((io.fabric.sdk.android.services.network.PinningInfoProvider)localObject);
    }
    for (;;)
    {
      try
      {
        packageName = paramContext.getPackageName();
        installerPackageName = getIdManager().getInstallerPackageName();
        Fabric.getLogger().d("Fabric", "Installer package name is: " + installerPackageName);
        localObject = paramContext.getPackageManager().getPackageInfo(packageName, 0);
        versionCode = Integer.toString(versionCode);
        if (versionName != null) {
          continue;
        }
        localObject = "0.0";
        versionName = ((String)localObject);
        buildId = CommonUtils.resolveBuildId(paramContext);
      }
      catch (Exception localException)
      {
        Fabric.getLogger().e("Fabric", "Error setting up app properties", localException);
        continue;
      }
      getIdManager().getBluetoothMacAddress();
      getBuildIdValidator(buildId, isRequiringBuildId(paramContext)).validate(paramString, packageName);
      return;
      localObject = null;
      break;
      localObject = versionName;
    }
  }
  
  public static void setBool(String paramString, boolean paramBoolean)
  {
    setString(paramString, Boolean.toString(paramBoolean));
  }
  
  public static void setDouble(String paramString, double paramDouble)
  {
    setString(paramString, Double.toString(paramDouble));
  }
  
  public static void setFloat(String paramString, float paramFloat)
  {
    setString(paramString, Float.toString(paramFloat));
  }
  
  public static void setInt(String paramString, int paramInt)
  {
    setString(paramString, Integer.toString(paramInt));
  }
  
  public static void setLong(String paramString, long paramLong)
  {
    setString(paramString, Long.toString(paramLong));
  }
  
  @Deprecated
  public static void setPinningInfoProvider(PinningInfoProvider paramPinningInfoProvider)
  {
    Fabric.getLogger().w("Fabric", "Use of Crashlytics.setPinningInfoProvider is deprecated");
  }
  
  public static void setString(String paramString1, String paramString2)
  {
    if (isDisabled()) {
      return;
    }
    if (paramString1 == null)
    {
      if ((getInstance().getContext() != null) && (CommonUtils.isAppDebuggable(getInstance().getContext()))) {
        throw new IllegalArgumentException("Custom attribute key must not be null.");
      }
      Fabric.getLogger().e("Fabric", "Attempting to set custom attribute with null key, ignoring.", null);
      return;
    }
    String str = sanitizeAttribute(paramString1);
    if ((getInstanceattributes.size() < 64) || (getInstanceattributes.containsKey(str)))
    {
      if (paramString2 == null) {}
      for (paramString1 = "";; paramString1 = sanitizeAttribute(paramString2))
      {
        getInstanceattributes.put(str, paramString1);
        return;
      }
    }
    Fabric.getLogger().d("Fabric", "Exceeded maximum number of custom attributes (64)");
  }
  
  public static void setUserEmail(String paramString)
  {
    if (isDisabled()) {
      return;
    }
    getInstanceuserEmail = sanitizeAttribute(paramString);
  }
  
  public static void setUserIdentifier(String paramString)
  {
    if (isDisabled()) {
      return;
    }
    getInstanceuserId = sanitizeAttribute(paramString);
  }
  
  public static void setUserName(String paramString)
  {
    if (isDisabled()) {
      return;
    }
    getInstanceuserName = sanitizeAttribute(paramString);
  }
  
  boolean canSendWithUserApproval()
  {
    ((Boolean)Settings.getInstance().withSettings(new Settings.SettingsAccess()
    {
      public Boolean usingSettings(SettingsData paramAnonymousSettingsData)
      {
        boolean bool2 = true;
        Activity localActivity = getFabric().getCurrentActivity();
        boolean bool1 = bool2;
        if (localActivity != null)
        {
          bool1 = bool2;
          if (!localActivity.isFinishing())
          {
            bool1 = bool2;
            if (shouldPromptUserBeforeSendingCrashReports()) {
              bool1 = Crashlytics.this.getSendDecisionFromUser(localActivity, promptData);
            }
          }
        }
        return Boolean.valueOf(bool1);
      }
    }, Boolean.valueOf(true))).booleanValue();
  }
  
  public void crash()
  {
    new CrashTest().indexOutOfBounds();
  }
  
  boolean didPreviousInitializationComplete()
  {
    ((Boolean)executorServiceWrapper.executeSyncLoggingException(new Callable()
    {
      public Boolean call()
        throws Exception
      {
        return Boolean.valueOf(initializationMarkerFile.exists());
      }
    })).booleanValue();
  }
  
  protected Void doInBackground()
  {
    markInitializationStarted();
    handler.cleanInvalidTempFiles();
    int k = 1;
    j = 1;
    i = k;
    for (;;)
    {
      try
      {
        Object localObject1 = Settings.getInstance().awaitSettingsData();
        if (localObject1 == null)
        {
          i = k;
          Fabric.getLogger().w("Fabric", "Received null settings, skipping initialization!");
          return null;
        }
        i = k;
        if (featuresData.collectReports)
        {
          k = 0;
          j = 0;
          i = k;
          handler.finalizeSessions();
          i = k;
          localObject1 = getCreateReportSpiCall((SettingsData)localObject1);
          if (localObject1 == null) {
            continue;
          }
          i = k;
          new ReportUploader((CreateReportSpiCall)localObject1).uploadReports(delay);
        }
      }
      catch (Exception localException1)
      {
        Fabric.getLogger().e("Fabric", "Error dealing with settings", localException1);
        j = i;
        continue;
      }
      finally
      {
        markInitializationComplete();
      }
      if (j != 0) {}
      try
      {
        Fabric.getLogger().d("Fabric", "Crash reporting disabled.");
        markInitializationComplete();
        return null;
      }
      catch (Exception localException2)
      {
        Fabric.getLogger().e("Fabric", "Problem encountered during Crashlytics initialization.", localException2);
        markInitializationComplete();
        return null;
      }
      i = k;
      Fabric.getLogger().w("Fabric", "Unable to create a call to upload reports.");
    }
  }
  
  Map<String, String> getAttributes()
  {
    return Collections.unmodifiableMap(attributes);
  }
  
  String getBuildId()
  {
    return buildId;
  }
  
  BuildIdValidator getBuildIdValidator(String paramString, boolean paramBoolean)
  {
    return new BuildIdValidator(paramString, paramBoolean);
  }
  
  CreateReportSpiCall getCreateReportSpiCall(SettingsData paramSettingsData)
  {
    if (paramSettingsData != null) {
      return new DefaultCreateReportSpiCall(this, getOverridenSpiEndpoint(), appData.reportsUrl, httpRequestFactory);
    }
    return null;
  }
  
  @Deprecated
  public boolean getDebugMode()
  {
    Fabric.getLogger().w("Fabric", "Use of Crashlytics.getDebugMode is deprecated.");
    getFabric();
    return Fabric.isDebuggable();
  }
  
  SessionEventData getExternalCrashEventData()
  {
    SessionEventData localSessionEventData = null;
    if (externalCrashEventDataProvider != null) {
      localSessionEventData = externalCrashEventDataProvider.getCrashEventData();
    }
    return localSessionEventData;
  }
  
  CrashlyticsUncaughtExceptionHandler getHandler()
  {
    return handler;
  }
  
  public String getIdentifier()
  {
    return "com.crashlytics.sdk.android:crashlytics";
  }
  
  String getInstallerPackageName()
  {
    return installerPackageName;
  }
  
  public Collection<? extends Kit> getKits()
  {
    return kits;
  }
  
  String getOverridenSpiEndpoint()
  {
    return CommonUtils.getStringsFileValue(getInstance().getContext(), "com.crashlytics.ApiEndpoint");
  }
  
  String getPackageName()
  {
    return packageName;
  }
  
  File getSdkDirectory()
  {
    return new FileStoreImpl(this).getFilesDir();
  }
  
  SessionSettingsData getSessionSettingsData()
  {
    SettingsData localSettingsData = Settings.getInstance().awaitSettingsData();
    if (localSettingsData == null) {
      return null;
    }
    return sessionData;
  }
  
  String getUserEmail()
  {
    if (getIdManager().canCollectUserIds()) {
      return userEmail;
    }
    return null;
  }
  
  String getUserIdentifier()
  {
    if (getIdManager().canCollectUserIds()) {
      return userId;
    }
    return null;
  }
  
  String getUserName()
  {
    if (getIdManager().canCollectUserIds()) {
      return userName;
    }
    return null;
  }
  
  public String getVersion()
  {
    return "2.2.3.41";
  }
  
  String getVersionCode()
  {
    return versionCode;
  }
  
  String getVersionName()
  {
    return versionName;
  }
  
  boolean internalVerifyPinning(URL paramURL)
  {
    boolean bool = false;
    if (getPinningInfoProvider() != null)
    {
      paramURL = httpRequestFactory.buildHttpRequest(HttpMethod.GET, paramURL.toString());
      ((HttpsURLConnection)paramURL.getConnection()).setInstanceFollowRedirects(false);
      paramURL.code();
      bool = true;
    }
    return bool;
  }
  
  void markInitializationComplete()
  {
    executorServiceWrapper.executeAsync(new Callable()
    {
      public Boolean call()
        throws Exception
      {
        try
        {
          boolean bool = initializationMarkerFile.delete();
          Fabric.getLogger().d("Fabric", "Initialization marker file removed: " + bool);
          return Boolean.valueOf(bool);
        }
        catch (Exception localException)
        {
          Fabric.getLogger().e("Fabric", "Problem encountered deleting Crashlytics initialization marker.", localException);
        }
        return Boolean.valueOf(false);
      }
    });
  }
  
  void markInitializationStarted()
  {
    executorServiceWrapper.executeSyncLoggingException(new Callable()
    {
      public Void call()
        throws Exception
      {
        initializationMarkerFile.createNewFile();
        Fabric.getLogger().d("Fabric", "Initialization marker file created.");
        return null;
      }
    });
  }
  
  protected boolean onPreExecute()
  {
    return onPreExecute(super.getContext());
  }
  
  boolean onPreExecute(Context paramContext)
  {
    if (disabled) {
      return false;
    }
    Object localObject = new ApiKey().getValue(paramContext);
    if (localObject == null) {
      return false;
    }
    Fabric.getLogger().i("Fabric", "Initializing Crashlytics " + getVersion());
    initializationMarkerFile = new File(getSdkDirectory(), "initialization_marker");
    boolean bool2 = false;
    try
    {
      setAndValidateKitProperties(paramContext, (String)localObject);
      boolean bool1 = bool2;
      try
      {
        localObject = new SessionDataWriter(getContext(), buildId, getPackageName());
        bool1 = bool2;
        Fabric.getLogger().d("Fabric", "Installing exception handler...");
        bool1 = bool2;
        handler = new CrashlyticsUncaughtExceptionHandler(Thread.getDefaultUncaughtExceptionHandler(), listener, executorServiceWrapper, getIdManager(), (SessionDataWriter)localObject, this);
        bool1 = bool2;
        bool2 = didPreviousInitializationComplete();
        bool1 = bool2;
        handler.ensureOpenSessionExists();
        bool1 = bool2;
        Thread.setDefaultUncaughtExceptionHandler(handler);
        bool1 = bool2;
        Fabric.getLogger().d("Fabric", "Successfully installed exception handler.");
        bool1 = bool2;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Fabric.getLogger().e("Fabric", "There was a problem installing the exception handler.", localException);
        }
      }
      if ((bool1) && (CommonUtils.canTryConnection(paramContext)))
      {
        finishInitSynchronously();
        return false;
      }
    }
    catch (CrashlyticsMissingDependencyException paramContext)
    {
      throw new UnmetDependencyException(paramContext);
      return true;
    }
    catch (Exception paramContext)
    {
      Fabric.getLogger().e("Fabric", "Crashlytics was not started due to an exception during initialization", paramContext);
    }
    return false;
  }
  
  @Deprecated
  public void setDebugMode(boolean paramBoolean)
  {
    Fabric.getLogger().w("Fabric", "Use of Crashlytics.setDebugMode is deprecated.");
  }
  
  void setExternalCrashEventDataProvider(CrashEventDataProvider paramCrashEventDataProvider)
  {
    externalCrashEventDataProvider = paramCrashEventDataProvider;
  }
  
  @Deprecated
  public void setListener(CrashlyticsListener paramCrashlyticsListener)
  {
    try
    {
      Fabric.getLogger().w("Fabric", "Use of Crashlytics.setListener is deprecated.");
      if (paramCrashlyticsListener == null) {
        throw new IllegalArgumentException("listener must not be null.");
      }
    }
    finally {}
    listener = paramCrashlyticsListener;
  }
  
  @SuppressLint({"CommitPrefEdits"})
  void setShouldSendUserReportsWithoutPrompting(boolean paramBoolean)
  {
    PreferenceStoreImpl localPreferenceStoreImpl = new PreferenceStoreImpl(this);
    localPreferenceStoreImpl.save(localPreferenceStoreImpl.edit().putBoolean("always_send_reports_opt_in", paramBoolean));
  }
  
  boolean shouldPromptUserBeforeSendingCrashReports()
  {
    ((Boolean)Settings.getInstance().withSettings(new Settings.SettingsAccess()
    {
      public Boolean usingSettings(SettingsData paramAnonymousSettingsData)
      {
        boolean bool = false;
        if (featuresData.promptEnabled)
        {
          if (!shouldSendReportsWithoutPrompting()) {
            bool = true;
          }
          return Boolean.valueOf(bool);
        }
        return Boolean.valueOf(false);
      }
    }, Boolean.valueOf(false))).booleanValue();
  }
  
  boolean shouldSendReportsWithoutPrompting()
  {
    return new PreferenceStoreImpl(this).get().getBoolean("always_send_reports_opt_in", false);
  }
  
  public boolean verifyPinning(URL paramURL)
  {
    try
    {
      boolean bool = internalVerifyPinning(paramURL);
      return bool;
    }
    catch (Exception paramURL)
    {
      Fabric.getLogger().e("Fabric", "Could not verify SSL pinning", paramURL);
    }
    return false;
  }
  
  public static class Builder
  {
    private float delay = -1.0F;
    private boolean disabled = false;
    private CrashlyticsListener listener;
    private PinningInfoProvider pinningInfoProvider;
    
    public Crashlytics build()
    {
      if (delay < 0.0F) {
        delay = 1.0F;
      }
      return new Crashlytics(delay, listener, pinningInfoProvider, disabled);
    }
    
    public Builder delay(float paramFloat)
    {
      if (paramFloat <= 0.0F) {
        throw new IllegalArgumentException("delay must be greater than 0");
      }
      if (delay > 0.0F) {
        throw new IllegalStateException("delay already set.");
      }
      delay = paramFloat;
      return this;
    }
    
    public Builder disabled(boolean paramBoolean)
    {
      disabled = paramBoolean;
      return this;
    }
    
    public Builder listener(CrashlyticsListener paramCrashlyticsListener)
    {
      if (paramCrashlyticsListener == null) {
        throw new IllegalArgumentException("listener must not be null.");
      }
      if (listener != null) {
        throw new IllegalStateException("listener already set.");
      }
      listener = paramCrashlyticsListener;
      return this;
    }
    
    @Deprecated
    public Builder pinningInfo(PinningInfoProvider paramPinningInfoProvider)
    {
      if (paramPinningInfoProvider == null) {
        throw new IllegalArgumentException("pinningInfoProvider must not be null.");
      }
      if (pinningInfoProvider != null) {
        throw new IllegalStateException("pinningInfoProvider already set.");
      }
      pinningInfoProvider = paramPinningInfoProvider;
      return this;
    }
  }
  
  private class OptInLatch
  {
    private final CountDownLatch latch = new CountDownLatch(1);
    private boolean send = false;
    
    private OptInLatch() {}
    
    void await()
    {
      try
      {
        latch.await();
        return;
      }
      catch (InterruptedException localInterruptedException) {}
    }
    
    boolean getOptIn()
    {
      return send;
    }
    
    void setOptIn(boolean paramBoolean)
    {
      send = paramBoolean;
      latch.countDown();
    }
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.Crashlytics
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */