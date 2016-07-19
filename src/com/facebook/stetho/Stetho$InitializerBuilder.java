package com.facebook.stetho;

import android.content.Context;
import com.facebook.stetho.common.Util;
import javax.annotation.Nullable;

public class Stetho$InitializerBuilder
{
  final Context mContext;
  @Nullable
  DumperPluginsProvider mDumperPlugins;
  @Nullable
  InspectorModulesProvider mInspectorModules;
  
  private Stetho$InitializerBuilder(Context paramContext)
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

/* Location:
 * Qualified Name:     com.facebook.stetho.Stetho.InitializerBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */