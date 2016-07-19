package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzab
  extends zzal
{
  private static final String ID = zzaf.zzgm.toString();
  
  public zzab()
  {
    super(ID, new String[0]);
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    String str2 = Build.MANUFACTURER;
    String str1 = Build.MODEL;
    paramMap = str1;
    if (!str1.startsWith(str2))
    {
      paramMap = str1;
      if (!str2.equals("unknown")) {
        paramMap = String.valueOf(str2).length() + 1 + String.valueOf(str1).length() + str2 + " " + str1;
      }
    }
    return zzdl.zzar(paramMap);
  }
  
  public boolean zzcac()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzab
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */