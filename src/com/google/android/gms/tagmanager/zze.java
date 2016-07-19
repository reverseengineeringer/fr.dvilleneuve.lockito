package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zze
  extends zzal
{
  private static final String ID = zzaf.zzhi.toString();
  private static final String auQ = zzag.zzmm.toString();
  private static final String auR = zzag.zzmp.toString();
  private final Context zzagf;
  
  public zze(Context paramContext)
  {
    super(ID, new String[] { auR });
    zzagf = paramContext;
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    Object localObject = (zzai.zza)paramMap.get(auR);
    if (localObject == null) {
      return zzdl.zzcdq();
    }
    localObject = zzdl.zzg((zzai.zza)localObject);
    paramMap = (zzai.zza)paramMap.get(auQ);
    if (paramMap != null) {}
    for (paramMap = zzdl.zzg(paramMap);; paramMap = null)
    {
      paramMap = zzbe.zzg(zzagf, (String)localObject, paramMap);
      if (paramMap == null) {
        break;
      }
      return zzdl.zzar(paramMap);
    }
    return zzdl.zzcdq();
  }
  
  public boolean zzcac()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zze
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */