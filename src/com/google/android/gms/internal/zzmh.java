package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.common.internal.zzab;
import java.util.HashMap;
import java.util.Map;

public final class zzmh
  extends zzg<zzmh>
{
  private String zzcvk;
  private String zzcvl;
  private String zzcvm;
  private String zzcvn;
  private boolean zzcvo;
  private String zzcvp;
  private boolean zzcvq;
  private double zzcvr;
  
  public String getUserId()
  {
    return zzcvm;
  }
  
  public void setClientId(String paramString)
  {
    zzcvl = paramString;
  }
  
  public void setSampleRate(double paramDouble)
  {
    if ((paramDouble >= 0.0D) && (paramDouble <= 100.0D)) {}
    for (boolean bool = true;; bool = false)
    {
      zzab.zzb(bool, "Sample rate must be between 0% and 100%");
      zzcvr = paramDouble;
      return;
    }
  }
  
  public void setUserId(String paramString)
  {
    zzcvm = paramString;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("hitType", zzcvk);
    localHashMap.put("clientId", zzcvl);
    localHashMap.put("userId", zzcvm);
    localHashMap.put("androidAdId", zzcvn);
    localHashMap.put("AdTargetingEnabled", Boolean.valueOf(zzcvo));
    localHashMap.put("sessionControl", zzcvp);
    localHashMap.put("nonInteraction", Boolean.valueOf(zzcvq));
    localHashMap.put("sampleRate", Double.valueOf(zzcvr));
    return zzk(localHashMap);
  }
  
  public void zza(zzmh paramzzmh)
  {
    if (!TextUtils.isEmpty(zzcvk)) {
      paramzzmh.zzdx(zzcvk);
    }
    if (!TextUtils.isEmpty(zzcvl)) {
      paramzzmh.setClientId(zzcvl);
    }
    if (!TextUtils.isEmpty(zzcvm)) {
      paramzzmh.setUserId(zzcvm);
    }
    if (!TextUtils.isEmpty(zzcvn)) {
      paramzzmh.zzdy(zzcvn);
    }
    if (zzcvo) {
      paramzzmh.zzao(true);
    }
    if (!TextUtils.isEmpty(zzcvp)) {
      paramzzmh.zzdz(zzcvp);
    }
    if (zzcvq) {
      paramzzmh.zzap(zzcvq);
    }
    if (zzcvr != 0.0D) {
      paramzzmh.setSampleRate(zzcvr);
    }
  }
  
  public void zzao(boolean paramBoolean)
  {
    zzcvo = paramBoolean;
  }
  
  public void zzap(boolean paramBoolean)
  {
    zzcvq = paramBoolean;
  }
  
  public void zzdx(String paramString)
  {
    zzcvk = paramString;
  }
  
  public void zzdy(String paramString)
  {
    zzcvn = paramString;
  }
  
  public void zzdz(String paramString)
  {
    zzcvp = paramString;
  }
  
  public String zzwb()
  {
    return zzcvl;
  }
  
  public String zzxx()
  {
    return zzcvk;
  }
  
  public String zzxy()
  {
    return zzcvn;
  }
  
  public boolean zzxz()
  {
    return zzcvo;
  }
  
  public String zzya()
  {
    return zzcvp;
  }
  
  public boolean zzyb()
  {
    return zzcvq;
  }
  
  public double zzyc()
  {
    return zzcvr;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzmh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */