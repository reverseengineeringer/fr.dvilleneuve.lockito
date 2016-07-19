package com.crashlytics.android;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import java.io.File;
import java.util.concurrent.Callable;

class Crashlytics$2
  implements Callable<Void>
{
  Crashlytics$2(Crashlytics paramCrashlytics) {}
  
  public Void call()
    throws Exception
  {
    Crashlytics.access$000(this$0).createNewFile();
    Fabric.getLogger().d("Fabric", "Initialization marker file created.");
    return null;
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.Crashlytics.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */