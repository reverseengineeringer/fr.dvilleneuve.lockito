package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import java.util.HashMap;
import java.util.Map;

public final class zzlz
  extends zzg<zzlz>
{
  private String mName;
  private String zzbek;
  private String zzbgk;
  private String zzcus;
  private String zzcut;
  private String zzcuu;
  private String zzcuv;
  private String zzcuw;
  private String zzcux;
  private String zzcuy;
  
  public String getContent()
  {
    return zzbek;
  }
  
  public String getId()
  {
    return zzbgk;
  }
  
  public String getName()
  {
    return mName;
  }
  
  public String getSource()
  {
    return zzcus;
  }
  
  public void setName(String paramString)
  {
    mName = paramString;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("name", mName);
    localHashMap.put("source", zzcus);
    localHashMap.put("medium", zzcut);
    localHashMap.put("keyword", zzcuu);
    localHashMap.put("content", zzbek);
    localHashMap.put("id", zzbgk);
    localHashMap.put("adNetworkId", zzcuv);
    localHashMap.put("gclid", zzcuw);
    localHashMap.put("dclid", zzcux);
    localHashMap.put("aclid", zzcuy);
    return zzk(localHashMap);
  }
  
  public void zza(zzlz paramzzlz)
  {
    if (!TextUtils.isEmpty(mName)) {
      paramzzlz.setName(mName);
    }
    if (!TextUtils.isEmpty(zzcus)) {
      paramzzlz.zzdk(zzcus);
    }
    if (!TextUtils.isEmpty(zzcut)) {
      paramzzlz.zzdl(zzcut);
    }
    if (!TextUtils.isEmpty(zzcuu)) {
      paramzzlz.zzdm(zzcuu);
    }
    if (!TextUtils.isEmpty(zzbek)) {
      paramzzlz.zzdn(zzbek);
    }
    if (!TextUtils.isEmpty(zzbgk)) {
      paramzzlz.zzdo(zzbgk);
    }
    if (!TextUtils.isEmpty(zzcuv)) {
      paramzzlz.zzdp(zzcuv);
    }
    if (!TextUtils.isEmpty(zzcuw)) {
      paramzzlz.zzdq(zzcuw);
    }
    if (!TextUtils.isEmpty(zzcux)) {
      paramzzlz.zzdr(zzcux);
    }
    if (!TextUtils.isEmpty(zzcuy)) {
      paramzzlz.zzds(zzcuy);
    }
  }
  
  public void zzdk(String paramString)
  {
    zzcus = paramString;
  }
  
  public void zzdl(String paramString)
  {
    zzcut = paramString;
  }
  
  public void zzdm(String paramString)
  {
    zzcuu = paramString;
  }
  
  public void zzdn(String paramString)
  {
    zzbek = paramString;
  }
  
  public void zzdo(String paramString)
  {
    zzbgk = paramString;
  }
  
  public void zzdp(String paramString)
  {
    zzcuv = paramString;
  }
  
  public void zzdq(String paramString)
  {
    zzcuw = paramString;
  }
  
  public void zzdr(String paramString)
  {
    zzcux = paramString;
  }
  
  public void zzds(String paramString)
  {
    zzcuy = paramString;
  }
  
  public String zzxe()
  {
    return zzcut;
  }
  
  public String zzxf()
  {
    return zzcuu;
  }
  
  public String zzxg()
  {
    return zzcuv;
  }
  
  public String zzxh()
  {
    return zzcuw;
  }
  
  public String zzxi()
  {
    return zzcux;
  }
  
  public String zzxj()
  {
    return zzcuy;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzlz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */