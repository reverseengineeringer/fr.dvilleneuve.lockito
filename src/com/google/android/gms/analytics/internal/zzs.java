package com.google.android.gms.analytics.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

class zzs
  implements Logger
{
  private boolean zzcso;
  private int zzczi = 2;
  
  public void error(Exception paramException) {}
  
  public void error(String paramString) {}
  
  public int getLogLevel()
  {
    return zzczi;
  }
  
  public void info(String paramString) {}
  
  public void setLogLevel(int paramInt)
  {
    zzczi = paramInt;
    if (!zzcso)
    {
      String str1 = (String)zzy.zzczr.get();
      String str2 = (String)zzy.zzczr.get();
      Log.i(str1, String.valueOf(str2).length() + 91 + "Logger is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag." + str2 + " DEBUG");
      zzcso = true;
    }
  }
  
  public void verbose(String paramString) {}
  
  public void warn(String paramString) {}
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */