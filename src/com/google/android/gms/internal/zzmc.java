package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzg;
import com.google.android.gms.common.internal.zzab;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzmc
  extends zzg<zzmc>
{
  private final Map<String, Object> zzbee = new HashMap();
  
  private String zzdt(String paramString)
  {
    zzab.zzhs(paramString);
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.startsWith("&")) {
        str = paramString.substring(1);
      }
    }
    zzab.zzh(str, "Name can not be empty or \"&\"");
    return str;
  }
  
  public void set(String paramString1, String paramString2)
  {
    paramString1 = zzdt(paramString1);
    zzbee.put(paramString1, paramString2);
  }
  
  public String toString()
  {
    return zzk(zzbee);
  }
  
  public void zza(zzmc paramzzmc)
  {
    zzab.zzaa(paramzzmc);
    zzbee.putAll(zzbee);
  }
  
  public Map<String, Object> zzxm()
  {
    return Collections.unmodifiableMap(zzbee);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzmc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */