package com.google.android.gms.analytics.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

@Deprecated
public class zzae
{
  private static volatile Logger Z;
  
  static
  {
    setLogger(new zzs());
  }
  
  public static Logger getLogger()
  {
    return Z;
  }
  
  public static void setLogger(Logger paramLogger)
  {
    Z = paramLogger;
  }
  
  public static void v(String paramString)
  {
    Object localObject = zzaf.zzadf();
    if (localObject != null) {
      ((zzaf)localObject).zzei(paramString);
    }
    for (;;)
    {
      localObject = Z;
      if (localObject != null) {
        ((Logger)localObject).verbose(paramString);
      }
      return;
      if (zzaz(0)) {
        Log.v((String)zzy.zzczr.get(), paramString);
      }
    }
  }
  
  public static boolean zzaz(int paramInt)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (getLogger() != null)
    {
      bool1 = bool2;
      if (getLogger().getLogLevel() <= paramInt) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static void zzcx(String paramString)
  {
    Object localObject = zzaf.zzadf();
    if (localObject != null) {
      ((zzaf)localObject).zzek(paramString);
    }
    for (;;)
    {
      localObject = Z;
      if (localObject != null) {
        ((Logger)localObject).info(paramString);
      }
      return;
      if (zzaz(1)) {
        Log.i((String)zzy.zzczr.get(), paramString);
      }
    }
  }
  
  public static void zzcy(String paramString)
  {
    Object localObject = zzaf.zzadf();
    if (localObject != null) {
      ((zzaf)localObject).zzel(paramString);
    }
    for (;;)
    {
      localObject = Z;
      if (localObject != null) {
        ((Logger)localObject).warn(paramString);
      }
      return;
      if (zzaz(2)) {
        Log.w((String)zzy.zzczr.get(), paramString);
      }
    }
  }
  
  public static void zzf(String paramString, Object paramObject)
  {
    zzaf localzzaf = zzaf.zzadf();
    if (localzzaf != null) {
      localzzaf.zze(paramString, paramObject);
    }
    while (!zzaz(3))
    {
      paramObject = Z;
      if (paramObject != null) {
        ((Logger)paramObject).error(paramString);
      }
      return;
    }
    if (paramObject != null) {
      paramObject = String.valueOf(paramObject);
    }
    for (paramObject = String.valueOf(paramString).length() + 1 + String.valueOf(paramObject).length() + paramString + ":" + (String)paramObject;; paramObject = paramString)
    {
      Log.e((String)zzy.zzczr.get(), (String)paramObject);
      break;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzae
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */