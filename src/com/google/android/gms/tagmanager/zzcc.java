package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

abstract class zzcc
  extends zzch
{
  public zzcc(String paramString)
  {
    super(paramString);
  }
  
  protected boolean zza(zzai.zza paramzza1, zzai.zza paramzza2, Map<String, zzai.zza> paramMap)
  {
    paramzza1 = zzdl.zzh(paramzza1);
    paramzza2 = zzdl.zzh(paramzza2);
    if ((paramzza1 == zzdl.zzcdo()) || (paramzza2 == zzdl.zzcdo())) {
      return false;
    }
    return zza(paramzza1, paramzza2, paramMap);
  }
  
  protected abstract boolean zza(zzdk paramzzdk1, zzdk paramzzdk2, Map<String, zzai.zza> paramMap);
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzcc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */