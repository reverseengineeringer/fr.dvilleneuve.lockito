package io.fabric.sdk.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.PriorityThreadPoolExecutor;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class Fabric$Builder
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
  
  public Fabric$Builder(Context paramContext)
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
      Map localMap = Fabric.access$000(Arrays.asList(kits));
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

/* Location:
 * Qualified Name:     io.fabric.sdk.android.Fabric.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */