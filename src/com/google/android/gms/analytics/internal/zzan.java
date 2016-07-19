package com.google.android.gms.analytics.internal;

import android.app.Activity;
import java.util.HashMap;
import java.util.Map;

public class zzan
  implements zzp
{
  public int aA = -1;
  public Map<String, String> aB = new HashMap();
  public double aw = -1.0D;
  public int ax = -1;
  public int ay = -1;
  public int az = -1;
  public String zzcrv;
  
  public int getSessionTimeout()
  {
    return ax;
  }
  
  public String getTrackingId()
  {
    return zzcrv;
  }
  
  public boolean zzaeb()
  {
    return zzcrv != null;
  }
  
  public boolean zzaec()
  {
    return aw >= 0.0D;
  }
  
  public double zzaed()
  {
    return aw;
  }
  
  public boolean zzaee()
  {
    return ax >= 0;
  }
  
  public boolean zzaef()
  {
    return ay != -1;
  }
  
  public boolean zzaeg()
  {
    return ay == 1;
  }
  
  public boolean zzaeh()
  {
    return az != -1;
  }
  
  public boolean zzaei()
  {
    return az == 1;
  }
  
  public boolean zzaej()
  {
    return aA == 1;
  }
  
  public String zzex(String paramString)
  {
    String str = (String)aB.get(paramString);
    if (str != null) {
      return str;
    }
    return paramString;
  }
  
  public String zzr(Activity paramActivity)
  {
    return zzex(paramActivity.getClass().getCanonicalName());
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzan
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */