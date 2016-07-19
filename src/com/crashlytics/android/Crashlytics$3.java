package com.crashlytics.android;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import java.io.File;
import java.util.concurrent.Callable;

class Crashlytics$3
  implements Callable<Boolean>
{
  Crashlytics$3(Crashlytics paramCrashlytics) {}
  
  public Boolean call()
    throws Exception
  {
    try
    {
      boolean bool = Crashlytics.access$000(this$0).delete();
      Fabric.getLogger().d("Fabric", "Initialization marker file removed: " + bool);
      return Boolean.valueOf(bool);
    }
    catch (Exception localException)
    {
      Fabric.getLogger().e("Fabric", "Problem encountered deleting Crashlytics initialization marker.", localException);
    }
    return Boolean.valueOf(false);
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.Crashlytics.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */