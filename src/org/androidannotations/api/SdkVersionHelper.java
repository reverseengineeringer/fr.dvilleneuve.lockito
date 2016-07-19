package org.androidannotations.api;

import android.os.Build.VERSION;

public class SdkVersionHelper
{
  public static int getSdkInt()
  {
    if (Build.VERSION.RELEASE.startsWith("1.5")) {
      return 3;
    }
    return HelperInternal.access$000();
  }
  
  private static class HelperInternal
  {
    private static int getSdkIntInternal()
    {
      return Build.VERSION.SDK_INT;
    }
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.SdkVersionHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */