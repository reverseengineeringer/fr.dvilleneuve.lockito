package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzcj
  extends zzal
{
  private static final String ID = zzaf.zzha.toString();
  private static final String axr = zzag.zzqf.toString();
  private static final String axs = zzag.zzqd.toString();
  
  public zzcj()
  {
    super(ID, new String[0]);
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    Object localObject = (zzai.zza)paramMap.get(axr);
    paramMap = (zzai.zza)paramMap.get(axs);
    double d2;
    double d1;
    if ((localObject != null) && (localObject != zzdl.zzcdq()) && (paramMap != null) && (paramMap != zzdl.zzcdq()))
    {
      localObject = zzdl.zzh((zzai.zza)localObject);
      paramMap = zzdl.zzh(paramMap);
      if ((localObject != zzdl.zzcdo()) && (paramMap != zzdl.zzcdo()))
      {
        d2 = ((zzdk)localObject).doubleValue();
        d1 = paramMap.doubleValue();
        if (d2 > d1) {}
      }
    }
    for (;;)
    {
      return zzdl.zzar(Long.valueOf(Math.round((d1 - d2) * Math.random() + d2)));
      d1 = 2.147483647E9D;
      d2 = 0.0D;
    }
  }
  
  public boolean zzcac()
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzcj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */