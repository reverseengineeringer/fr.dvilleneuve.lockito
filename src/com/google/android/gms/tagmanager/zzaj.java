package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzaj
  extends zzal
{
  private static final String ID = zzaf.zzgs.toString();
  private final zzcw avb;
  
  public zzaj(zzcw paramzzcw)
  {
    super(ID, new String[0]);
    avb = paramzzcw;
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    paramMap = avb.zzcco();
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
 * Qualified Name:     com.google.android.gms.tagmanager.zzaj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */