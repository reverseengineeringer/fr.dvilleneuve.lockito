package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzc
  extends zzal
{
  private static final String ID = zzaf.zzgc.toString();
  private final zza auP;
  
  public zzc(Context paramContext)
  {
    this(zza.zzdr(paramContext));
  }
  
  zzc(zza paramzza)
  {
    super(ID, new String[0]);
    auP = paramzza;
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    if (!auP.isLimitAdTrackingEnabled()) {}
    for (boolean bool = true;; bool = false) {
      return zzdl.zzar(Boolean.valueOf(bool));
    }
  }
  
  public boolean zzcac()
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */