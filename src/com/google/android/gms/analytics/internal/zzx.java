package com.google.android.gms.analytics.internal;

import android.os.Build.VERSION;
import java.io.File;

public class zzx
{
  public static int version()
  {
    try
    {
      int i = Integer.parseInt(Build.VERSION.SDK);
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      zzae.zzf("Invalid version number", Build.VERSION.SDK);
    }
    return 0;
  }
  
  public static boolean zzet(String paramString)
  {
    if (version() < 9) {
      return false;
    }
    paramString = new File(paramString);
    paramString.setReadable(false, false);
    paramString.setWritable(false, false);
    paramString.setReadable(true, true);
    paramString.setWritable(true, true);
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */