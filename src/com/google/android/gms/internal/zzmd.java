package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import java.util.HashMap;
import java.util.Map;

public final class zzmd
  extends zzg<zzmd>
{
  public int zzbrj;
  public int zzbrk;
  private String zzcvb;
  public int zzcvc;
  public int zzcvd;
  public int zzcve;
  
  public String getLanguage()
  {
    return zzcvb;
  }
  
  public void setLanguage(String paramString)
  {
    zzcvb = paramString;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("language", zzcvb);
    localHashMap.put("screenColors", Integer.valueOf(zzcvc));
    localHashMap.put("screenWidth", Integer.valueOf(zzbrj));
    localHashMap.put("screenHeight", Integer.valueOf(zzbrk));
    localHashMap.put("viewportWidth", Integer.valueOf(zzcvd));
    localHashMap.put("viewportHeight", Integer.valueOf(zzcve));
    return zzk(localHashMap);
  }
  
  public void zza(zzmd paramzzmd)
  {
    if (zzcvc != 0) {
      paramzzmd.zzbp(zzcvc);
    }
    if (zzbrj != 0) {
      paramzzmd.zzbq(zzbrj);
    }
    if (zzbrk != 0) {
      paramzzmd.zzbr(zzbrk);
    }
    if (zzcvd != 0) {
      paramzzmd.zzbs(zzcvd);
    }
    if (zzcve != 0) {
      paramzzmd.zzbt(zzcve);
    }
    if (!TextUtils.isEmpty(zzcvb)) {
      paramzzmd.setLanguage(zzcvb);
    }
  }
  
  public void zzbp(int paramInt)
  {
    zzcvc = paramInt;
  }
  
  public void zzbq(int paramInt)
  {
    zzbrj = paramInt;
  }
  
  public void zzbr(int paramInt)
  {
    zzbrk = paramInt;
  }
  
  public void zzbs(int paramInt)
  {
    zzcvd = paramInt;
  }
  
  public void zzbt(int paramInt)
  {
    zzcve = paramInt;
  }
  
  public int zzxn()
  {
    return zzcvc;
  }
  
  public int zzxo()
  {
    return zzbrj;
  }
  
  public int zzxp()
  {
    return zzbrk;
  }
  
  public int zzxq()
  {
    return zzcvd;
  }
  
  public int zzxr()
  {
    return zzcve;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzmd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */