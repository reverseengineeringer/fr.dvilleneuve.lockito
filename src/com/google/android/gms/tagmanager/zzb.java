package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzb
  extends zzal
{
  private static final String ID = zzaf.zzgb.toString();
  private final zza auP;
  
  public zzb(Context paramContext)
  {
    this(zza.zzdr(paramContext));
  }
  
  zzb(zza paramzza)
  {
    super(ID, new String[0]);
    auP = paramzza;
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    paramMap = auP.zzbzw();
    if (paramMap == null) {
      return zzdl.zzcdq();
    }
    return zzdl.zzar(paramMap);
  }
  
  public boolean zzcac()
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */