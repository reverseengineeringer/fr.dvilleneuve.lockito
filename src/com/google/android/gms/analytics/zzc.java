package com.google.android.gms.analytics;

import com.google.android.gms.analytics.internal.zzae;

public final class zzc
{
  private static String zzb(String paramString, int paramInt)
  {
    if (paramInt < 1)
    {
      zzae.zzf("index out of range for prefix", paramString);
      return "";
    }
    return String.valueOf(paramString).length() + 11 + paramString + paramInt;
  }
  
  public static String zzbc(int paramInt)
  {
    return zzb("&cd", paramInt);
  }
  
  public static String zzbd(int paramInt)
  {
    return zzb("cd", paramInt);
  }
  
  public static String zzbe(int paramInt)
  {
    return zzb("&cm", paramInt);
  }
  
  public static String zzbf(int paramInt)
  {
    return zzb("cm", paramInt);
  }
  
  public static String zzbg(int paramInt)
  {
    return zzb("&pr", paramInt);
  }
  
  public static String zzbh(int paramInt)
  {
    return zzb("pr", paramInt);
  }
  
  public static String zzbi(int paramInt)
  {
    return zzb("&promo", paramInt);
  }
  
  public static String zzbj(int paramInt)
  {
    return zzb("promo", paramInt);
  }
  
  public static String zzbk(int paramInt)
  {
    return zzb("pi", paramInt);
  }
  
  public static String zzbl(int paramInt)
  {
    return zzb("&il", paramInt);
  }
  
  public static String zzbm(int paramInt)
  {
    return zzb("il", paramInt);
  }
  
  public static String zzbn(int paramInt)
  {
    return zzb("cd", paramInt);
  }
  
  public static String zzbo(int paramInt)
  {
    return zzb("cm", paramInt);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */