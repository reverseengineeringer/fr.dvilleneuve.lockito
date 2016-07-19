package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public class zzbe
{
  static Map<String, String> awA = new HashMap();
  private static String awz;
  
  public static String zzbg(String paramString1, String paramString2)
  {
    if (paramString2 == null)
    {
      if (paramString1.length() > 0) {
        return paramString1;
      }
      return null;
    }
    paramString1 = String.valueOf(paramString1);
    if (paramString1.length() != 0) {}
    for (paramString1 = "http://hostname/?".concat(paramString1);; paramString1 = new String("http://hostname/?")) {
      return Uri.parse(paramString1).getQueryParameter(paramString2);
    }
  }
  
  public static String zzg(Context paramContext, String paramString1, String paramString2)
  {
    String str = (String)awA.get(paramString1);
    Object localObject = str;
    if (str == null)
    {
      paramContext = paramContext.getSharedPreferences("gtm_click_referrers", 0);
      if (paramContext == null) {
        break label63;
      }
    }
    label63:
    for (paramContext = paramContext.getString(paramString1, "");; paramContext = "")
    {
      awA.put(paramString1, paramContext);
      localObject = paramContext;
      return zzbg((String)localObject, paramString2);
    }
  }
  
  public static void zzog(String paramString)
  {
    try
    {
      awz = paramString;
      return;
    }
    finally {}
  }
  
  static void zzw(Context paramContext, String paramString)
  {
    zzdc.zzb(paramContext, "gtm_install_referrer", "referrer", paramString);
    zzy(paramContext, paramString);
  }
  
  /* Error */
  public static String zzx(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: getstatic 82	com/google/android/gms/tagmanager/zzbe:awz	Ljava/lang/String;
    //   3: ifnonnull +40 -> 43
    //   6: ldc 2
    //   8: monitorenter
    //   9: getstatic 82	com/google/android/gms/tagmanager/zzbe:awz	Ljava/lang/String;
    //   12: ifnonnull +28 -> 40
    //   15: aload_0
    //   16: ldc 86
    //   18: iconst_0
    //   19: invokevirtual 66	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   22: astore_0
    //   23: aload_0
    //   24: ifnull +27 -> 51
    //   27: aload_0
    //   28: ldc 88
    //   30: ldc 68
    //   32: invokeinterface 73 3 0
    //   37: putstatic 82	com/google/android/gms/tagmanager/zzbe:awz	Ljava/lang/String;
    //   40: ldc 2
    //   42: monitorexit
    //   43: getstatic 82	com/google/android/gms/tagmanager/zzbe:awz	Ljava/lang/String;
    //   46: aload_1
    //   47: invokestatic 79	com/google/android/gms/tagmanager/zzbe:zzbg	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   50: areturn
    //   51: ldc 68
    //   53: putstatic 82	com/google/android/gms/tagmanager/zzbe:awz	Ljava/lang/String;
    //   56: goto -16 -> 40
    //   59: astore_0
    //   60: ldc 2
    //   62: monitorexit
    //   63: aload_0
    //   64: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	65	0	paramContext	Context
    //   0	65	1	paramString	String
    // Exception table:
    //   from	to	target	type
    //   9	23	59	finally
    //   27	40	59	finally
    //   40	43	59	finally
    //   51	56	59	finally
    //   60	63	59	finally
  }
  
  public static void zzy(Context paramContext, String paramString)
  {
    String str = zzbg(paramString, "conv");
    if ((str != null) && (str.length() > 0))
    {
      awA.put(str, paramString);
      zzdc.zzb(paramContext, "gtm_click_referrers", str, paramString);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzbe
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */