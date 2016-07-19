package com.facebook.stetho;

import android.content.Context;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.dumpapp.plugins.CrashDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.FilesDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.HprofDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.SharedPreferencesDumperPlugin;

public final class Stetho$DefaultDumperPluginsBuilder
{
  private final Context mContext;
  private final Stetho.PluginBuilder<DumperPlugin> mDelegate = new Stetho.PluginBuilder(null);
  
  public Stetho$DefaultDumperPluginsBuilder(Context paramContext)
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

/* Location:
 * Qualified Name:     com.facebook.stetho.Stetho.DefaultDumperPluginsBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */