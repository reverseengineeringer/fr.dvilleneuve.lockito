package io.fabric.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import io.fabric.sdk.android.services.concurrency.PriorityThreadPoolExecutor;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class Fabric
{
  static final boolean DEFAULT_DEBUGGABLE = false;
  static final Logger DEFAULT_LOGGER = new DefaultLogger();
  static final String ROOT_DIR = ".Fabric";
  public static final String TAG = "Fabric";
  static volatile Fabric singleton;
  private WeakReference<Activity> activity;
  private ActivityLifecycleManager activityLifecycleManager;
  private final Context context;
  final boolean debuggable;
  private final ExecutorService executorService;
  private final IdManager idManager;
  private final InitializationCallback<Fabric> initializationCallback;
  private AtomicBoolean initialized;
  private final InitializationCallback<?> kitInitializationCallback;
  private final Map<Class<? extends Kit>, Kit> kits;
  final Logger logger;
  private final Handler mainHandler;
  
  Fabric(Context paramContext, Map<Class<? extends Kit>, Kit> paramMap, PriorityThreadPoolExecutor paramPriorityThreadPoolExecutor, Handler paramHandler, Logger paramLogger, boolean paramBoolean, InitializationCallback paramInitializationCallback, IdManager paramIdManager)
  {
    context = paramContext;
    kits = paramMap;
    executorService = paramPriorityThreadPoolExecutor;
    mainHandler = paramHandler;
    logger = paramLogger;
    debuggable = paramBoolean;
    initializationCallback = paramInitializationCallback;
    initialized = new AtomicBoolean(false);
    kitInitializationCallback = createKitInitializationCallback(paramMap.size());
    idManager = paramIdManager;
  }
  
  private static void addToKitMap(Map<Class<? extends Kit>, Kit> paramMap, Collection<? extends Kit> paramCollection)
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      Kit localKit = (Kit)paramCollection.next();
      paramMap.put(localKit.getClass(), localKit);
      if ((localKit instanceof KitGroup)) {
        addToKitMap(paramMap, ((KitGroup)localKit).getKits());
      }
    }
  }
  
  private Activity extractActivity(Context paramContext)
  {
    if ((paramContext instanceof Activity)) {
      return (Activity)paramContext;
    }
    return null;
  }
  
  public static <T extends Kit> T getKit(Class<T> paramClass)
  {
    return (Kit)singletonkits.get(paramClass);
  }
  
  private static Map<Class<? extends Kit>, Kit> getKitMap(Collection<? extends Kit> paramCollection)
  {
    HashMap localHashMap = new HashMap(paramCollection.size());
    addToKitMap(localHashMap, paramCollection);
    return localHashMap;
  }
  
  public static Logger getLogger()
  {
    if (singleton == null) {
      return DEFAULT_LOGGER;
    }
    return singletonlogger;
  }
  
  private void init()
  {
    setCurrentActivity(extractActivity(context));
    activityLifecycleManager = new ActivityLifecycleManager(context);
    activityLifecycleManager.registerCallbacks(new ActivityLifecycleManager.Callbacks()
    {
      public void onActivityCreated(Activity paramAnonymousActivity, Bundle paramAnonymousBundle)
      {
        setCurrentActivity(paramAnonymousActivity);
      }
      
      public void onActivityResumed(Activity paramAnonymousActivity)
      {
        setCurrentActivity(paramAnonymousActivity);
      }
      
      public void onActivityStarted(Activity paramAnonymousActivity)
      {
        setCurrentActivity(paramAnonymousActivity);
      }
    });
    initializeKits(context);
  }
  
  public static boolean isDebuggable()
  {
    if (singleton == null) {
      return false;
    }
    return singletondebuggable;
  }
  
  public static boolean isInitialized()
  {
    return (singleton != null) && (singletoninitialized.get());
  }
  
  private static void setFabric(Fabric paramFabric)
  {
    singleton = paramFabric;
    paramFabric.init();
  }
  
  static Fabric singleton()
  {
    if (singleton == null) {
      throw new IllegalStateException("Must Initialize Fabric before using singleton()");
    }
    return singleton;
  }
  
  public static Fabric with(Context paramContext, Kit... paramVarArgs)
  {
    if (singleton == null) {}
    try
    {
      if (singleton == null) {
        setFabric(new Builder(paramContext).kits(paramVarArgs).build());
      }
      return singleton;
    }
    finally {}
  }
  
  public static Fabric with(Fabric paramFabric)
  {
    if (singleton == null) {}
    try
    {
      if (singleton == null) {
        setFabric(paramFabric);
      }
      return singleton;
    }
    finally {}
  }
  
  void addAnnotatedDependencies(Map<Class<? extends Kit>, Kit> paramMap, Kit paramKit)
  {
    Object localObject1 = (DependsOn)paramKit.getClass().getAnnotation(DependsOn.class);
    if (localObject1 != null)
    {
      localObject1 = ((DependsOn)localObject1).value();
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        Object localObject2 = localObject1[i];
        if (((Class)localObject2).isInterface())
        {
          Iterator localIterator = paramMap.values().iterator();
          while (localIterator.hasNext())
          {
            Kit localKit = (Kit)localIterator.next();
            if (((Class)localObject2).isAssignableFrom(localKit.getClass())) {
              initializationTask.addDependency(initializationTask);
            }
          }
        }
        if ((Kit)paramMap.get(localObject2) == null) {
          throw new UnmetDependencyException("Referenced Kit was null, does the kit exist?");
        }
        initializationTask.addDependency(getinitializationTask);
        i += 1;
      }
    }
  }
  
  InitializationCallback<?> createKitInitializationCallback(final int paramInt)
  {
    new InitializationCallback()
    {
      final CountDownLatch kitInitializedLatch = new CountDownLatch(paramInt);
      
      public void failure(Exception paramAnonymousException)
      {
        initializationCallback.failure(paramAnonymousException);
      }
      
      public void success(Object paramAnonymousObject)
      {
        kitInitializedLatch.countDown();
        if (kitInitializedLatch.getCount() == 0L)
        {
          initialized.set(true);
          initializationCallback.success(Fabric.this);
        }
      }
    };
  }
  
  public ActivityLifecycleManager getActivityLifecycleManager()
  {
    return activityLifecycleManager;
  }
  
  public String getAppIdentifier()
  {
    return idManager.getAppIdentifier();
  }
  
  public String getAppInstallIdentifier()
  {
    return idManager.getAppInstallIdentifier();
  }
  
  public Activity getCurrentActivity()
  {
    if (activity != null) {
      return (Activity)activity.get();
    }
    return null;
  }
  
  public ExecutorService getExecutorService()
  {
    return executorService;
  }
  
  public String getIdentifier()
  {
    return "io.fabric.sdk.android:fabric";
  }
  
  public Collection<Kit> getKits()
  {
    return kits.values();
  }
  
  public Handler getMainHandler()
  {
    return mainHandler;
  }
  
  public String getVersion()
  {
    return "1.3.0.41";
  }
  
  void initializeKits(Context paramContext)
  {
    Object localObject1 = getKits();
    Onboarding localOnboarding = new Onboarding((Collection)localObject1);
    localObject1 = new ArrayList((Collection)localObject1);
    Collections.sort((List)localObject1);
    localOnboarding.injectParameters(paramContext, this, InitializationCallback.EMPTY, idManager);
    Object localObject2 = ((List)localObject1).iterator();
    while (((Iterator)localObject2).hasNext()) {
      ((Kit)((Iterator)localObject2).next()).injectParameters(paramContext, this, kitInitializationCallback, idManager);
    }
    localOnboarding.initialize();
    if (getLogger().isLoggable("Fabric", 3)) {}
    for (paramContext = new StringBuilder("Initializing ").append(getIdentifier()).append(" [Version: ").append(getVersion()).append("], with the following kits:\n");; paramContext = null)
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Kit)((Iterator)localObject1).next();
        initializationTask.addDependency(initializationTask);
        addAnnotatedDependencies(kits, (Kit)localObject2);
        ((Kit)localObject2).initialize();
        if (paramContext != null) {
          paramContext.append(((Kit)localObject2).getIdentifier()).append(" [Version: ").append(((Kit)localObject2).getVersion()).append("]\n");
        }
      }
    }
    if (paramContext != null) {
      getLogger().d("Fabric", paramContext.toString());
    }
  }
  
  public Fabric setCurrentActivity(Activity paramActivity)
  {
    activity = new WeakReference(paramActivity);
    return this;
  }
  
  public static class Builder
  {
    private String appIdentifier;
    private String appInstallIdentifier;
    private final Context context;
    private boolean debuggable;
    private Handler handler;
    private InitializationCallback<Fabric> initializationCallback;
    private Kit[] kits;
    private Logger logger;
    private PriorityThreadPoolExecutor threadPoolExecutor;
    
    public Builder(Context paramContext)
    {
      if (paramContext == null) {
        throw new IllegalArgumentException("Context must not be null.");
      }
      context = paramContext.getApplicationContext();
    }
    
    public Builder appIdentifier(String paramString)
    {
      if (paramString == null) {
        throw new IllegalArgumentException("appIdentifier must not be null.");
      }
      if (appIdentifier != null) {
        throw new IllegalStateException("appIdentifier already set.");
      }
      appIdentifier = paramString;
      return this;
    }
    
    public Builder appInstallIdentifier(String paramString)
    {
      if (paramString == null) {
        throw new IllegalArgumentException("appInstallIdentifier must not be null.");
      }
      if (appInstallIdentifier != null) {
        throw new IllegalStateException("appInstallIdentifier already set.");
      }
      appInstallIdentifier = paramString;
      return this;
    }
    
    public Fabric build()
    {
      if (kits == null) {
        throw new IllegalStateException("Kits must not be null.");
      }
      if (threadPoolExecutor == null) {
        threadPoolExecutor = PriorityThreadPoolExecutor.create();
      }
      if (handler == null) {
        handler = new Handler(Looper.getMainLooper());
      }
      if (logger == null) {
        if (!debuggable) {
          break label181;
        }
      }
      label181:
      for (logger = new DefaultLogger(3);; logger = new DefaultLogger())
      {
        if (appIdentifier == null) {
          appIdentifier = context.getPackageName();
        }
        if (initializationCallback == null) {
          initializationCallback = InitializationCallback.EMPTY;
        }
        Map localMap = Fabric.getKitMap(Arrays.asList(kits));
        IdManager localIdManager = new IdManager(context, appIdentifier, appInstallIdentifier, localMap.values());
        return new Fabric(context, localMap, threadPoolExecutor, handler, logger, debuggable, initializationCallback, localIdManager);
      }
    }
    
    public Builder debuggable(boolean paramBoolean)
    {
      debuggable = paramBoolean;
      return this;
    }
    
    @Deprecated
    public Builder executorService(ExecutorService paramExecutorService)
    {
      return this;
    }
    
    @Deprecated
    public Builder handler(Handler paramHandler)
    {
      return this;
    }
    
    public Builder initializationCallback(InitializationCallback<Fabric> paramInitializationCallback)
    {
      if (paramInitializationCallback == null) {
        throw new IllegalArgumentException("initializationCallback must not be null.");
      }
      if (initializationCallback != null) {
        throw new IllegalStateException("initializationCallback already set.");
      }
      initializationCallback = paramInitializationCallback;
      return this;
    }
    
    public Builder kits(Kit... paramVarArgs)
    {
      if (paramVarArgs == null) {
        throw new IllegalArgumentException("Kits must not be null.");
      }
      if (paramVarArgs.length == 0) {
        throw new IllegalArgumentException("Kits must not be empty.");
      }
      if (kits != null) {
        throw new IllegalStateException("Kits already set.");
      }
      kits = paramVarArgs;
      return this;
    }
    
    public Builder logger(Logger paramLogger)
    {
      if (paramLogger == null) {
        throw new IllegalArgumentException("Logger must not be null.");
      }
      if (logger != null) {
        throw new IllegalStateException("Logger already set.");
      }
      logger = paramLogger;
      return this;
    }
    
    public Builder threadPoolExecutor(PriorityThreadPoolExecutor paramPriorityThreadPoolExecutor)
    {
      if (paramPriorityThreadPoolExecutor == null) {
        throw new IllegalArgumentException("PriorityThreadPoolExecutor must not be null.");
      }
      if (threadPoolExecutor != null) {
        throw new IllegalStateException("PriorityThreadPoolExecutor already set.");
      }
      threadPoolExecutor = paramPriorityThreadPoolExecutor;
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.Fabric
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */