package com.facebook.stetho;

import android.content.Context;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;

final class Stetho$4
  implements InspectorModulesProvider
{
  Stetho$4(Context paramContext) {}
  
  public Iterable<ChromeDevtoolsDomain> get()
  {
    return new Stetho.DefaultInspectorModulesBuilder(val$context).finish();
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.Stetho.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */