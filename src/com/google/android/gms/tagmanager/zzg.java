package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzg
  extends zzal
{
  private static final String ID = zzaf.zzge.toString();
  private final Context mContext;
  
  public zzg(Context paramContext)
  {
    super(ID, new String[0]);
    mContext = paramContext;
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    try
    {
      paramMap = mContext.getPackageManager();
      paramMap = zzdl.zzar(paramMap.getApplicationLabel(paramMap.getApplicationInfo(mContext.getPackageName(), 0)).toString());
      return paramMap;
    }
    catch (PackageManager.NameNotFoundException paramMap)
    {
      zzbn.zzb("App name is not found.", paramMap);
    }
    return zzdl.zzcdq();
  }
  
  public boolean zzcac()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */