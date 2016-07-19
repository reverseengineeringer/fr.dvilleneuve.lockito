package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzrt;
import com.google.android.gms.internal.zzru;

public class zzd
{
  public static int zza(PackageInfo paramPackageInfo)
  {
    if ((paramPackageInfo == null) || (applicationInfo == null)) {}
    do
    {
      return -1;
      paramPackageInfo = applicationInfo.metaData;
    } while (paramPackageInfo == null);
    return paramPackageInfo.getInt("com.google.android.gms.version", -1);
  }
  
  public static boolean zzabc()
  {
    return false;
  }
  
  public static int zzo(Context paramContext, String paramString)
  {
    return zza(zzp(paramContext, paramString));
  }
  
  @Nullable
  public static PackageInfo zzp(Context paramContext, String paramString)
  {
    try
    {
      paramContext = zzru.zzcq(paramContext).getPackageInfo(paramString, 128);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  @TargetApi(12)
  public static boolean zzq(Context paramContext, String paramString)
  {
    if (!zzs.zzavk()) {}
    for (;;)
    {
      return false;
      if ((!"com.google.android.gms".equals(paramString)) || (!zzabc())) {
        try
        {
          int i = zzcqgetApplicationInfo0flags;
          if ((i & 0x200000) != 0) {
            return true;
          }
        }
        catch (PackageManager.NameNotFoundException paramContext) {}
      }
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.util.zzd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */