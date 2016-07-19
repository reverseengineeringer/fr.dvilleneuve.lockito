package com.facebook.stetho;

import android.content.Context;
import com.facebook.stetho.dumpapp.DumperPlugin;

final class Stetho$3
  implements DumperPluginsProvider
{
  Stetho$3(Context paramContext) {}
  
  public Iterable<DumperPlugin> get()
  {
    return new Stetho.DefaultDumperPluginsBuilder(val$context).finish();
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.Stetho.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */