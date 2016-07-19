package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import java.util.HashMap;
import java.util.Map;

public final class zzmk
  extends zzg<zzmk>
{
  public String mCategory;
  public String zzcvg;
  public String zzcwb;
  public long zzcwc;
  
  public String getCategory()
  {
    return mCategory;
  }
  
  public String getLabel()
  {
    return zzcvg;
  }
  
  public long getTimeInMillis()
  {
    return zzcwc;
  }
  
  public void setTimeInMillis(long paramLong)
  {
    zzcwc = paramLong;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("variableName", zzcwb);
    localHashMap.put("timeInMillis", Long.valueOf(zzcwc));
    localHashMap.put("category", mCategory);
    localHashMap.put("label", zzcvg);
    return zzk(localHashMap);
  }
  
  public void zza(zzmk paramzzmk)
  {
    if (!TextUtils.isEmpty(zzcwb)) {
      paramzzmk.zzee(zzcwb);
    }
    if (zzcwc != 0L) {
      paramzzmk.setTimeInMillis(zzcwc);
    }
    if (!TextUtils.isEmpty(mCategory)) {
      paramzzmk.zzdu(mCategory);
    }
    if (!TextUtils.isEmpty(zzcvg)) {
      paramzzmk.zzdw(zzcvg);
    }
  }
  
  public void zzdu(String paramString)
  {
    mCategory = paramString;
  }
  
  public void zzdw(String paramString)
  {
    zzcvg = paramString;
  }
  
  public void zzee(String paramString)
  {
    zzcwb = paramString;
  }
  
  public String zzyj()
  {
    return zzcwb;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzmk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */