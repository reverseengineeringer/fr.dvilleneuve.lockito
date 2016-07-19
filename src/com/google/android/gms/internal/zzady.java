package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import java.util.HashMap;
import java.util.Map;

public class zzady
{
  Map<String, Object> aCf = new HashMap();
  private final Map<String, Object> aCg;
  private final zzaea aDr;
  private String avB = null;
  private final Context mContext;
  private final zze zzaoa;
  
  public zzady(Context paramContext)
  {
    this(paramContext, new HashMap(), new zzaea(paramContext), zzh.zzavi());
  }
  
  zzady(Context paramContext, Map<String, Object> paramMap, zzaea paramzzaea, zze paramzze)
  {
    mContext = paramContext;
    zzaoa = paramzze;
    aDr = paramzzaea;
    aCg = paramMap;
  }
  
  public void zzps(String paramString)
  {
    avB = paramString;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzady
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */