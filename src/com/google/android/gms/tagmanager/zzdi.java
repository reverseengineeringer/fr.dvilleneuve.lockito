package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

public class zzdi
{
  private Context mContext;
  private Tracker zzcsg;
  private GoogleAnalytics zzcsi;
  
  public zzdi(Context paramContext)
  {
    mContext = paramContext;
  }
  
  private void zzoq(String paramString)
  {
    try
    {
      if (zzcsi == null)
      {
        zzcsi = GoogleAnalytics.getInstance(mContext);
        zzcsi.setLogger(new zza());
        zzcsg = zzcsi.newTracker(paramString);
      }
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public Tracker zzop(String paramString)
  {
    zzoq(paramString);
    return zzcsg;
  }
  
  static class zza
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
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzdi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */