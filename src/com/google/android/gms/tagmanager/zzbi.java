package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Locale;
import java.util.Map;

public class zzbi
  extends zzal
{
  private static final String ID = zzaf.zzgw.toString();
  
  public zzbi()
  {
    super(ID, new String[0]);
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    paramMap = Locale.getDefault();
    if (paramMap == null) {
      return zzdl.zzcdq();
    }
    paramMap = paramMap.getLanguage();
    if (paramMap == null) {
      return zzdl.zzcdq();
    }
    return zzdl.zzar(paramMap.toLowerCase());
  }
  
  public boolean zzcac()
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzbi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */