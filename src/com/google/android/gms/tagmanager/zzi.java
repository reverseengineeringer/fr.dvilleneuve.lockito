package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzi
  extends zzal
{
  private static final String ID = zzaf.zzkq.toString();
  private final Context mContext;
  
  public zzi(Context paramContext)
  {
    super(ID, new String[0]);
    mContext = paramContext;
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    try
    {
      paramMap = zzdl.zzar(mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName);
      return paramMap;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      paramMap = String.valueOf(mContext.getPackageName());
      String str = String.valueOf(localNameNotFoundException.getMessage());
      zzbn.e(String.valueOf(paramMap).length() + 25 + String.valueOf(str).length() + "Package name " + paramMap + " not found. " + str);
    }
    return zzdl.zzcdq();
  }
  
  public boolean zzcac()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */