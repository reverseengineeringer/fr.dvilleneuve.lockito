package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzq
  extends zzal
{
  private static final String ID = zzaf.zzgk.toString();
  private final String nZ;
  
  public zzq(String paramString)
  {
    super(ID, new String[0]);
    nZ = paramString;
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    if (nZ == null) {
      return zzdl.zzcdq();
    }
    return zzdl.zzar(nZ);
  }
  
  public boolean zzcac()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */