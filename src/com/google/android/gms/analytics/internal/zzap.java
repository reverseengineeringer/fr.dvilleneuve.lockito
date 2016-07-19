package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public class zzap
  extends zzd
{
  protected int M;
  protected boolean aD;
  protected boolean aE;
  protected boolean zzcsm;
  protected String zzcup;
  protected String zzcuq;
  protected int zzczi;
  
  public zzap(zzf paramzzf)
  {
    super(paramzzf);
  }
  
  private static int zzfe(String paramString)
  {
    paramString = paramString.toLowerCase();
    if ("verbose".equals(paramString)) {
      return 0;
    }
    if ("info".equals(paramString)) {
      return 1;
    }
    if ("warning".equals(paramString)) {
      return 2;
    }
    if ("error".equals(paramString)) {
      return 3;
    }
    return -1;
  }
  
  public int getLogLevel()
  {
    zzzg();
    return zzczi;
  }
  
  void zza(zzaa paramzzaa)
  {
    zzei("Loading global XML config values");
    String str;
    if (paramzzaa.zzacp())
    {
      str = paramzzaa.zzxb();
      zzcup = str;
      zzb("XML config - app name", str);
    }
    if (paramzzaa.zzacq())
    {
      str = paramzzaa.zzxc();
      zzcuq = str;
      zzb("XML config - app version", str);
    }
    int i;
    if (paramzzaa.zzacr())
    {
      i = zzfe(paramzzaa.zzacs());
      if (i >= 0)
      {
        zzczi = i;
        zza("XML config - log level", Integer.valueOf(i));
      }
    }
    if (paramzzaa.zzact())
    {
      i = paramzzaa.zzacu();
      M = i;
      aD = true;
      zzb("XML config - dispatch period (sec)", Integer.valueOf(i));
    }
    if (paramzzaa.zzacv())
    {
      boolean bool = paramzzaa.zzacw();
      zzcsm = bool;
      aE = true;
      zzb("XML config - dry run", Boolean.valueOf(bool));
    }
  }
  
  public boolean zzacr()
  {
    zzzg();
    return false;
  }
  
  public boolean zzact()
  {
    zzzg();
    return aD;
  }
  
  public boolean zzacv()
  {
    zzzg();
    return aE;
  }
  
  public boolean zzacw()
  {
    zzzg();
    return zzcsm;
  }
  
  public int zzaek()
  {
    zzzg();
    return M;
  }
  
  protected void zzael()
  {
    Object localObject1 = getContext();
    try
    {
      localObject1 = ((Context)localObject1).getPackageManager().getApplicationInfo(((Context)localObject1).getPackageName(), 129);
      if (localObject1 == null)
      {
        zzel("Couldn't get ApplicationInfo to load global config");
        return;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Object localObject2;
      do
      {
        int i;
        do
        {
          do
          {
            for (;;)
            {
              zzd("PackageManager doesn't know about the app package", localNameNotFoundException);
              localObject2 = null;
            }
            localObject2 = metaData;
          } while (localObject2 == null);
          i = ((Bundle)localObject2).getInt("com.google.android.gms.analytics.globalConfigResource");
        } while (i <= 0);
        localObject2 = (zzaa)new zzz(zzyu()).zzbx(i);
      } while (localObject2 == null);
      zza((zzaa)localObject2);
    }
  }
  
  protected void zzwv()
  {
    zzael();
  }
  
  public String zzxb()
  {
    zzzg();
    return zzcup;
  }
  
  public String zzxc()
  {
    zzzg();
    return zzcuq;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */