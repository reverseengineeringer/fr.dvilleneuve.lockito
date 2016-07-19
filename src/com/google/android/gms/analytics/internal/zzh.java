package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzab;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzh
{
  private final Map<String, String> zzbee;
  private final String zzcvl;
  private final long zzcxl;
  private final String zzcxm;
  private final boolean zzcxn;
  private long zzcxo;
  
  public zzh(long paramLong1, String paramString1, String paramString2, boolean paramBoolean, long paramLong2, Map<String, String> paramMap)
  {
    zzab.zzhs(paramString1);
    zzab.zzhs(paramString2);
    zzcxl = paramLong1;
    zzcvl = paramString1;
    zzcxm = paramString2;
    zzcxn = paramBoolean;
    zzcxo = paramLong2;
    if (paramMap != null)
    {
      zzbee = new HashMap(paramMap);
      return;
    }
    zzbee = Collections.emptyMap();
  }
  
  public Map<String, String> zzm()
  {
    return zzbee;
  }
  
  public void zzp(long paramLong)
  {
    zzcxo = paramLong;
  }
  
  public String zzwb()
  {
    return zzcvl;
  }
  
  public long zzzo()
  {
    return zzcxl;
  }
  
  public String zzzp()
  {
    return zzcxm;
  }
  
  public boolean zzzq()
  {
    return zzcxn;
  }
  
  public long zzzr()
  {
    return zzcxo;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */