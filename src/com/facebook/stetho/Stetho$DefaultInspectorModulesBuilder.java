package com.facebook.stetho;

import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;
import com.facebook.stetho.inspector.database.DatabaseFilesProvider;
import com.facebook.stetho.inspector.database.DefaultDatabaseFilesProvider;
import com.facebook.stetho.inspector.elements.Document;
import com.facebook.stetho.inspector.elements.DocumentProviderFactory;
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
import javax.annotation.Nullable;

public final class Stetho$DefaultInspectorModulesBuilder
{
  private final Application mContext;
  @Nullable
  private DatabaseFilesProvider mDatabaseFiles;
  private final Stetho.PluginBuilder<ChromeDevtoolsDomain> mDelegate = new Stetho.PluginBuilder(null);
  @Nullable
  private DocumentProviderFactory mDocumentProvider;
  @Nullable
  private RuntimeReplFactory mRuntimeRepl;
  
  public Stetho$DefaultInspectorModulesBuilder(Context paramContext)
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

/* Location:
 * Qualified Name:     com.facebook.stetho.Stetho.DefaultInspectorModulesBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */