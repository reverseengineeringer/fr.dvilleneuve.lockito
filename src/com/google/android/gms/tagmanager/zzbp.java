package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzbp
  extends zzal
{
  private static final String ID = zzaf.zzhs.toString();
  private static final String awj = zzag.zzlk.toString();
  
  public zzbp()
  {
    super(ID, new String[] { awj });
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    return zzdl.zzar(zzdl.zzg((zzai.zza)paramMap.get(awj)).toLowerCase());
  }
  
  public boolean zzcac()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzbp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */