package com.google.android.gms.tagmanager;

import com.google.android.gms.analytics.Logger;

class zzdi$zza
  implements Logger
{
  private static int zzyf(int paramInt)
  {
    switch (paramInt)
    {
    case 6: 
    default: 
      return 3;
    case 5: 
      return 2;
    case 3: 
    case 4: 
      return 1;
    }
    return 0;
  }
  
  public void error(Exception paramException)
  {
    zzbn.zzb("", paramException);
  }
  
  public void error(String paramString)
  {
    zzbn.e(paramString);
  }
  
  public int getLogLevel()
  {
    return zzyf(zzbn.getLogLevel());
  }
  
  public void info(String paramString)
  {
    zzbn.zzcx(paramString);
  }
  
  public void setLogLevel(int paramInt)
  {
    zzbn.zzcy("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
  }
  
  public void verbose(String paramString)
  {
    zzbn.v(paramString);
  }
  
  public void warn(String paramString)
  {
    zzbn.zzcy(paramString);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzdi.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */