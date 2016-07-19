package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzv
  extends zzal
{
  private static final String ID = zzaf.zzgj.toString();
  private static final String NAME = zzag.zzqh.toString();
  private static final String avN = zzag.zznn.toString();
  private final DataLayer ava;
  
  public zzv(DataLayer paramDataLayer)
  {
    super(ID, new String[] { NAME });
    ava = paramDataLayer;
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    Object localObject = ava.get(zzdl.zzg((zzai.zza)paramMap.get(NAME)));
    if (localObject == null)
    {
      paramMap = (zzai.zza)paramMap.get(avN);
      if (paramMap != null) {
        return paramMap;
      }
      return zzdl.zzcdq();
    }
    return zzdl.zzar(localObject);
  }
  
  public boolean zzcac()
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */