package com.facebook.stetho;

import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import javax.annotation.Nullable;

class Stetho$BuilderBasedInitializer
  extends Stetho.Initializer
{
  @Nullable
  private final DumperPluginsProvider mDumperPlugins;
  @Nullable
  private final InspectorModulesProvider mInspectorModules;
  
  private Stetho$BuilderBasedInitializer(Stetho.InitializerBuilder paramInitializerBuilder)
  {
    super(mContext);
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

/* Location:
 * Qualified Name:     com.facebook.stetho.Stetho.BuilderBasedInitializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */