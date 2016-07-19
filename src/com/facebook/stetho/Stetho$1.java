package com.facebook.stetho;

import android.content.Context;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;

final class Stetho$1
  extends Stetho.Initializer
{
  Stetho$1(Context paramContext1, Context paramContext2)
  {
    super(paramContext1);
  }
  
  protected Iterable<DumperPlugin> getDumperPlugins()
  {
    return new Stetho.DefaultDumperPluginsBuilder(val$context).finish();
  }
  
  protected Iterable<ChromeDevtoolsDomain> getInspectorModules()
  {
    return new Stetho.DefaultInspectorModulesBuilder(val$context).finish();
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.Stetho.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */