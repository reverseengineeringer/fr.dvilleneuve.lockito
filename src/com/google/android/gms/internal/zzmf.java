package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import java.util.HashMap;
import java.util.Map;

public final class zzmf
  extends zzg<zzmf>
{
  private String mCategory;
  private String zzcvf;
  private String zzcvg;
  private long zzcvh;
  
  public String getAction()
  {
    return zzcvf;
  }
  
  public String getCategory()
  {
    return mCategory;
  }
  
  public String getLabel()
  {
    return zzcvg;
  }
  
  public long getValue()
  {
    return zzcvh;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("category", mCategory);
    localHashMap.put("action", zzcvf);
    localHashMap.put("label", zzcvg);
    localHashMap.put("value", Long.valueOf(zzcvh));
    return zzk(localHashMap);
  }
  
  public void zza(zzmf paramzzmf)
  {
    if (!TextUtils.isEmpty(mCategory)) {
      paramzzmf.zzdu(mCategory);
    }
    if (!TextUtils.isEmpty(zzcvf)) {
      paramzzmf.zzdv(zzcvf);
    }
    if (!TextUtils.isEmpty(zzcvg)) {
      paramzzmf.zzdw(zzcvg);
    }
    if (zzcvh != 0L) {
      paramzzmf.zzo(zzcvh);
    }
  }
  
  public void zzdu(String paramString)
  {
    mCategory = paramString;
  }
  
  public void zzdv(String paramString)
  {
    zzcvf = paramString;
  }
  
  public void zzdw(String paramString)
  {
    zzcvg = paramString;
  }
  
  public void zzo(long paramLong)
  {
    zzcvh = paramLong;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzmf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */