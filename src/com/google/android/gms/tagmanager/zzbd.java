package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzbd
  extends zzal
{
  private static final String ID = zzaf.zzhn.toString();
  private static final String auQ = zzag.zzmm.toString();
  private final Context zzagf;
  
  public zzbd(Context paramContext)
  {
    super(ID, new String[0]);
    zzagf = paramContext;
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    if ((zzai.zza)paramMap.get(auQ) != null) {}
    for (paramMap = zzdl.zzg((zzai.zza)paramMap.get(auQ));; paramMap = null)
    {
      paramMap = zzbe.zzx(zzagf, paramMap);
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
 * Qualified Name:     com.google.android.gms.tagmanager.zzbd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */