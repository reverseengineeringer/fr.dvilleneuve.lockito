package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

abstract class zzdg
  extends zzch
{
  public zzdg(String paramString)
  {
    super(paramString);
  }
  
  protected boolean zza(zzai.zza paramzza1, zzai.zza paramzza2, Map<String, zzai.zza> paramMap)
  {
    paramzza1 = zzdl.zzg(paramzza1);
    paramzza2 = zzdl.zzg(paramzza2);
    if ((paramzza1 == zzdl.zzcdp()) || (paramzza2 == zzdl.zzcdp())) {
      return false;
    }
    return zza(paramzza1, paramzza2, paramMap);
  }
  
  protected abstract boolean zza(String paramString1, String paramString2, Map<String, zzai.zza> paramMap);
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzdg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */