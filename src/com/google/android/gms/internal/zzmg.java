package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import java.util.HashMap;
import java.util.Map;

public final class zzmg
  extends zzg<zzmg>
{
  public String zzcvi;
  public boolean zzcvj;
  
  public String getDescription()
  {
    return zzcvi;
  }
  
  public void setDescription(String paramString)
  {
    zzcvi = paramString;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("description", zzcvi);
    localHashMap.put("fatal", Boolean.valueOf(zzcvj));
    return zzk(localHashMap);
  }
  
  public void zza(zzmg paramzzmg)
  {
    if (!TextUtils.isEmpty(zzcvi)) {
      paramzzmg.setDescription(zzcvi);
    }
    if (zzcvj) {
      paramzzmg.zzan(zzcvj);
    }
  }
  
  public void zzan(boolean paramBoolean)
  {
    zzcvj = paramBoolean;
  }
  
  public boolean zzxw()
  {
    return zzcvj;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzmg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */