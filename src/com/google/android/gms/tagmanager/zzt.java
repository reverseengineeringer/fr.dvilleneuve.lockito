package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzt
  extends zzal
{
  private static final String ID = zzaf.zzgt.toString();
  private static final String auS = zzag.zzkx.toString();
  private static final String avC = zzag.zzox.toString();
  private final zza avD;
  
  public zzt(zza paramzza)
  {
    super(ID, new String[] { avC });
    avD = paramzza;
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    String str = zzdl.zzg((zzai.zza)paramMap.get(avC));
    HashMap localHashMap = new HashMap();
    paramMap = (zzai.zza)paramMap.get(auS);
    if (paramMap != null)
    {
      paramMap = zzdl.zzl(paramMap);
      if (!(paramMap instanceof Map))
      {
        zzbn.zzcy("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
        return zzdl.zzcdq();
      }
      paramMap = ((Map)paramMap).entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        localHashMap.put(localEntry.getKey().toString(), localEntry.getValue());
      }
    }
    try
    {
      paramMap = zzdl.zzar(avD.zzd(str, localHashMap));
      return paramMap;
    }
    catch (Exception paramMap)
    {
      paramMap = String.valueOf(paramMap.getMessage());
      zzbn.zzcy(String.valueOf(str).length() + 34 + String.valueOf(paramMap).length() + "Custom macro/tag " + str + " threw exception " + paramMap);
    }
    return zzdl.zzcdq();
  }
  
  public boolean zzcac()
  {
    return false;
  }
  
  public static abstract interface zza
  {
    public abstract Object zzd(String paramString, Map<String, Object> paramMap);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */