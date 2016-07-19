package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import java.util.HashMap;
import java.util.Map;

public final class zzmj
  extends zzg<zzmj>
{
  public String zzcvf;
  public String zzcvz;
  public String zzcwa;
  
  public String getAction()
  {
    return zzcvf;
  }
  
  public String getTarget()
  {
    return zzcwa;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("network", zzcvz);
    localHashMap.put("action", zzcvf);
    localHashMap.put("target", zzcwa);
    return zzk(localHashMap);
  }
  
  public void zza(zzmj paramzzmj)
  {
    if (!TextUtils.isEmpty(zzcvz)) {
      paramzzmj.zzec(zzcvz);
    }
    if (!TextUtils.isEmpty(zzcvf)) {
      paramzzmj.zzdv(zzcvf);
    }
    if (!TextUtils.isEmpty(zzcwa)) {
      paramzzmj.zzed(zzcwa);
    }
  }
  
  public void zzdv(String paramString)
  {
    zzcvf = paramString;
  }
  
  public void zzec(String paramString)
  {
    zzcvz = paramString;
  }
  
  public void zzed(String paramString)
  {
    zzcwa = paramString;
  }
  
  public String zzyi()
  {
    return zzcvz;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzmj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */