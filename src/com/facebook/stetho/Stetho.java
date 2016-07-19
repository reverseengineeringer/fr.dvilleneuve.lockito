package com.facebook.stetho;

import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.dumpapp.Dumper;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.dumpapp.RawDumpappHandler;
import com.facebook.stetho.dumpapp.StreamingDumpappHandler;
import com.facebook.stetho.dumpapp.plugins.CrashDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.FilesDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.HprofDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.SharedPreferencesDumperPlugin;
import com.facebook.stetho.inspector.ChromeDevtoolsServer;
import com.facebook.stetho.inspector.ChromeDiscoveryHandler;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;
import com.facebook.stetho.inspector.database.DatabaseFilesProvider;
import com.facebook.stetho.inspector.database.DefaultDatabaseFilesProvider;
import com.facebook.stetho.inspector.elements.Document;
import com.facebook.stetho.inspector.elements.DocumentProviderFactory;
import com.facebook.stetho.inspector.elements.android.ActivityTracker;
import com.facebook.stetho.inspector.elements.android.AndroidDocumentProviderFactory;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.module.CSS;
import com.facebook.stetho.inspector.protocol.module.Console;
import com.facebook.stetho.inspector.protocol.module.DOM;
import com.facebook.stetho.inspector.protocol.module.DOMStorage;
import com.facebook.stetho.inspector.protocol.module.Database;
import com.facebook.stetho.inspector.protocol.module.Debugger;
import com.facebook.stetho.inspector.protocol.module.HeapProfiler;
import com.facebook.stetho.inspector.protocol.module.Inspector;
import com.facebook.stetho.inspector.protocol.module.Network;
import com.facebook.stetho.inspector.protocol.module.Page;
import com.facebook.stetho.inspector.protocol.module.Profiler;
import com.facebook.stetho.inspector.protocol.module.Runtime;
import com.facebook.stetho.inspector.protocol.module.Worker;
import com.facebook.stetho.inspector.runtime.RhinoDetectingRuntimeReplFactory;
import com.facebook.stetho.server.LocalSocketHttpServer;
import com.facebook.stetho.server.RegistryInitializer;
import com.facebook.stetho.websocket.WebSocketHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.HttpRequestHandlerRegistry;

public class Stetho
{
  private static final String LISTENER_THREAD_NAME = "Stetho-Listener";
  
  public static DumperPluginsProvider defaultDumperPluginsProvider(Context paramContext)
  {
    new DumperPluginsProvider()
    {
      public Iterable<DumperPlugin> get()
      {
        return new Stetho.DefaultDumperPluginsBuilder(val$context).finish();
      }
    };
  }
  
  public static InspectorModulesProvider defaultInspectorModulesProvider(Context paramContext)
  {
    new InspectorModulesProvider()
    {
      public Iterable<ChromeDevtoolsDomain> get()
      {
        return new Stetho.DefaultInspectorModulesBuilder(val$context).finish();
      }
    };
  }
  
  public static void initialize(final Initializer paramInitializer)
  {
    if (!ActivityTracker.get().beginTrackingIfPossible((Application)mContext.getApplicationContext())) {
      LogUtil.w("Automatic activity tracking not available on this API level, caller must invoke ActivityTracker methods manually!");
    }
    new Thread("Stetho-Listener")
    {
      public void run()
      {
        LocalSocketHttpServer localLocalSocketHttpServer = new LocalSocketHttpServer(paramInitializer);
        try
        {
          localLocalSocketHttpServer.run();
          return;
        }
        catch (IOException localIOException)
        {
          LogUtil.e(localIOException, "Could not start Stetho");
        }
      }
    }.start();
  }
  
  public static void initializeWithDefaults(final Context paramContext)
  {
    initialize(new Initializer(paramContext)
    {
      protected Iterable<DumperPlugin> getDumperPlugins()
      {
        return new Stetho.DefaultDumperPluginsBuilder(paramContext).finish();
      }
      
      protected Iterable<ChromeDevtoolsDomain> getInspectorModules()
      {
        return new Stetho.DefaultInspectorModulesBuilder(paramContext).finish();
      }
    });
  }
  
  public static InitializerBuilder newInitializerBuilder(Context paramContext)
  {
    return new InitializerBuilder(paramContext, null);
  }
  
  private static class BuilderBasedInitializer
    extends Stetho.Initializer
  {
    @Nullable
    private final DumperPluginsProvider mDumperPlugins;
    @Nullable
    private final InspectorModulesProvider mInspectorModules;
    
    private BuilderBasedInitializer(Stetho.InitializerBuilder paramInitializerBuilder)
    {
      super();
      mDumperPlugins = mDumperPlugins;
      mInspectorModules = mInspectorModules;
    }
    
    @Nullable
    protected Iterable<DumperPlugin> getDumperPlugins()
    {
      if (mDumperPlugins != null) {
        return mDumperPlugins.get();
      }
      return null;
    }
    
    @Nullable
    protected Iterable<ChromeDevtoolsDomain> getInspectorModules()
    {
      if (mInspectorModules != null) {
        return mInspectorModules.get();
      }
      return null;
    }
  }
  
  public static final class DefaultDumperPluginsBuilder
  {
    private final Context mContext;
    private final Stetho.PluginBuilder<DumperPlugin> mDelegate = new Stetho.PluginBuilder(null);
    
    public DefaultDumperPluginsBuilder(Context paramContext)
    {
      mContext = paramContext;
    }
    
    private DefaultDumperPluginsBuilder provideIfDesired(DumperPlugin paramDumperPlugin)
    {
      mDelegate.provideIfDesired(paramDumperPlugin.getName(), paramDumperPlugin);
      return this;
    }
    
    public Iterable<DumperPlugin> finish()
    {
      provideIfDesired(new HprofDumperPlugin(mContext));
      provideIfDesired(new SharedPreferencesDumperPlugin(mContext));
      provideIfDesired(new CrashDumperPlugin());
      provideIfDesired(new FilesDumperPlugin(mContext));
      return mDelegate.finish();
    }
    
    public DefaultDumperPluginsBuilder provide(DumperPlugin paramDumperPlugin)
    {
      mDelegate.provide(paramDumperPlugin.getName(), paramDumperPlugin);
      return this;
    }
    
    public DefaultDumperPluginsBuilder remove(String paramString)
    {
      mDelegate.remove(paramString);
      return this;
    }
  }
  
  public static final class DefaultInspectorModulesBuilder
  {
    private final Application mContext;
    @Nullable
    private DatabaseFilesProvider mDatabaseFiles;
    private final Stetho.PluginBuilder<ChromeDevtoolsDomain> mDelegate = new Stetho.PluginBuilder(null);
    @Nullable
    private DocumentProviderFactory mDocumentProvider;
    @Nullable
    private RuntimeReplFactory mRuntimeRepl;
    
    public DefaultInspectorModulesBuilder(Context paramContext)
    {
      mContext = ((Application)paramContext.getApplicationContext());
    }
    
    private DefaultInspectorModulesBuilder provideIfDesired(ChromeDevtoolsDomain paramChromeDevtoolsDomain)
    {
      mDelegate.provideIfDesired(paramChromeDevtoolsDomain.getClass().getName(), paramChromeDevtoolsDomain);
      return this;
    }
    
    @Nullable
    private DocumentProviderFactory resolveDocumentProvider()
    {
      if (mDocumentProvider != null) {
        return mDocumentProvider;
      }
      if (Build.VERSION.SDK_INT >= 14) {
        return new AndroidDocumentProviderFactory(mContext);
      }
      return null;
    }
    
    public DefaultInspectorModulesBuilder databaseFiles(DatabaseFilesProvider paramDatabaseFilesProvider)
    {
      mDatabaseFiles = paramDatabaseFilesProvider;
      return this;
    }
    
    public DefaultInspectorModulesBuilder documentProvider(DocumentProviderFactory paramDocumentProviderFactory)
    {
      mDocumentProvider = paramDocumentProviderFactory;
      return this;
    }
    
    public Iterable<ChromeDevtoolsDomain> finish()
    {
      provideIfDesired(new Console());
      provideIfDesired(new Debugger());
      Object localObject = resolveDocumentProvider();
      if (localObject != null)
      {
        localObject = new Document((DocumentProviderFactory)localObject);
        provideIfDesired(new DOM((Document)localObject));
        provideIfDesired(new CSS((Document)localObject));
      }
      provideIfDesired(new DOMStorage(mContext));
      provideIfDesired(new HeapProfiler());
      provideIfDesired(new Inspector());
      provideIfDesired(new Network(mContext));
      provideIfDesired(new Page(mContext));
      provideIfDesired(new Profiler());
      Application localApplication;
      if (mRuntimeRepl != null)
      {
        localObject = mRuntimeRepl;
        provideIfDesired(new Runtime((RuntimeReplFactory)localObject));
        provideIfDesired(new Worker());
        if (Build.VERSION.SDK_INT >= 11)
        {
          localApplication = mContext;
          if (mDatabaseFiles == null) {
            break label251;
          }
        }
      }
      label251:
      for (localObject = mDatabaseFiles;; localObject = new DefaultDatabaseFilesProvider(mContext))
      {
        provideIfDesired(new Database(localApplication, (DatabaseFilesProvider)localObject));
        return mDelegate.finish();
        localObject = new RhinoDetectingRuntimeReplFactory(mContext);
        break;
      }
    }
    
    @Deprecated
    public DefaultInspectorModulesBuilder provide(ChromeDevtoolsDomain paramChromeDevtoolsDomain)
    {
      mDelegate.provide(paramChromeDevtoolsDomain.getClass().getName(), paramChromeDevtoolsDomain);
      return this;
    }
    
    @Deprecated
    public DefaultInspectorModulesBuilder remove(String paramString)
    {
      mDelegate.remove(paramString);
      return this;
    }
    
    public DefaultInspectorModulesBuilder runtimeRepl(RuntimeReplFactory paramRuntimeReplFactory)
    {
      mRuntimeRepl = paramRuntimeReplFactory;
      return this;
    }
  }
  
  public static abstract class Initializer
    implements RegistryInitializer
  {
    private final Context mContext;
    
    protected Initializer(Context paramContext)
    {
      mContext = paramContext.getApplicationContext();
    }
    
    protected void addCustomEntries(HttpRequestHandlerRegistry paramHttpRequestHandlerRegistry) {}
    
    @Nullable
    protected abstract Iterable<DumperPlugin> getDumperPlugins();
    
    @Nullable
    protected abstract Iterable<ChromeDevtoolsDomain> getInspectorModules();
    
    public final HttpRequestHandlerRegistry getRegistry()
    {
      HttpRequestHandlerRegistry localHttpRequestHandlerRegistry = new HttpRequestHandlerRegistry();
      Object localObject = getDumperPlugins();
      if (localObject != null)
      {
        localObject = new Dumper((Iterable)localObject);
        localHttpRequestHandlerRegistry.register("/dumpapp", new StreamingDumpappHandler(mContext, (Dumper)localObject));
        localHttpRequestHandlerRegistry.register("/dumpapp-raw", new RawDumpappHandler(mContext, (Dumper)localObject));
      }
      localObject = getInspectorModules();
      if (localObject != null)
      {
        new ChromeDiscoveryHandler(mContext, "/inspector").register(localHttpRequestHandlerRegistry);
        localHttpRequestHandlerRegistry.register("/inspector", new WebSocketHandler(mContext, new ChromeDevtoolsServer((Iterable)localObject)));
      }
      addCustomEntries(localHttpRequestHandlerRegistry);
      localHttpRequestHandlerRegistry.register("/*", new LoggingCatchAllHandler(null));
      return localHttpRequestHandlerRegistry;
    }
    
    private static class LoggingCatchAllHandler
      implements HttpRequestHandler
    {
      public void handle(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
        throws HttpException, IOException
      {
        LogUtil.w("Unsupported request received: " + paramHttpRequest.getRequestLine());
        paramHttpResponse.setStatusCode(404);
        paramHttpResponse.setReasonPhrase("Not Found");
        paramHttpResponse.setEntity(new StringEntity("Endpoint not implemented\n", "UTF-8"));
      }
    }
  }
  
  public static class InitializerBuilder
  {
    final Context mContext;
    @Nullable
    DumperPluginsProvider mDumperPlugins;
    @Nullable
    InspectorModulesProvider mInspectorModules;
    
    private InitializerBuilder(Context paramContext)
    {
      mContext = paramContext.getApplicationContext();
    }
    
    public Stetho.Initializer build()
    {
      return new Stetho.BuilderBasedInitializer(this, null);
    }
    
    public InitializerBuilder enableDumpapp(DumperPluginsProvider paramDumperPluginsProvider)
    {
      mDumperPlugins = ((DumperPluginsProvider)Util.throwIfNull(paramDumperPluginsProvider));
      return this;
    }
    
    public InitializerBuilder enableWebKitInspector(InspectorModulesProvider paramInspectorModulesProvider)
    {
      mInspectorModules = paramInspectorModulesProvider;
      return this;
    }
  }
  
  private static class PluginBuilder<T>
  {
    private boolean mFinished;
    private final ArrayList<T> mPlugins = new ArrayList();
    private final Set<String> mProvidedNames = new HashSet();
    private final Set<String> mRemovedNames = new HashSet();
    
    private void throwIfFinished()
    {
      if (mFinished) {
        throw new IllegalStateException("Must not continue to build after finish()");
      }
    }
    
    public Iterable<T> finish()
    {
      mFinished = true;
      return mPlugins;
    }
    
    public void provide(String paramString, T paramT)
    {
      throwIfFinished();
      mPlugins.add(paramT);
      mProvidedNames.add(paramString);
    }
    
    public void provideIfDesired(String paramString, T paramT)
    {
      throwIfFinished();
      if ((!mRemovedNames.contains(paramString)) && (mProvidedNames.add(paramString))) {
        mPlugins.add(paramT);
      }
    }
    
    public void remove(String paramString)
    {
      throwIfFinished();
      mRemovedNames.remove(paramString);
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.Stetho
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */