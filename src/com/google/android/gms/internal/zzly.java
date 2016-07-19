package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import java.util.HashMap;
import java.util.Map;

public final class zzly
  extends zzg<zzly>
{
  private String zzcjj;
  private String zzcup;
  private String zzcuq;
  private String zzcur;
  
  public void setAppId(String paramString)
  {
    zzcjj = paramString;
  }
  
  public void setAppInstallerId(String paramString)
  {
    zzcur = paramString;
  }
  
  public void setAppName(String paramString)
  {
    zzcup = paramString;
  }
  
  public void setAppVersion(String paramString)
  {
    zzcuq = paramString;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("appName", zzcup);
    localHashMap.put("appVersion", zzcuq);
    localHashMap.put("appId", zzcjj);
    localHashMap.put("appInstallerId", zzcur);
    return zzk(localHashMap);
  }
  
  public void zza(zzly paramzzly)
  {
    if (!TextUtils.isEmpty(zzcup)) {
      paramzzly.setAppName(zzcup);
    }
    if (!TextUtils.isEmpty(zzcuq)) {
      paramzzly.setAppVersion(zzcuq);
    }
    if (!TextUtils.isEmpty(zzcjj)) {
      paramzzly.setAppId(zzcjj);
    }
    if (!TextUtils.isEmpty(zzcur)) {
      paramzzly.setAppInstallerId(zzcur);
    }
  }
  
  public String zzsi()
  {
    return zzcjj;
  }
  
  public String zzxb()
  {
    return zzcup;
  }
  
  public String zzxc()
  {
    return zzcuq;
  }
  
  public String zzxd()
  {
    return zzcur;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzly
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */