package com.google.android.gms.common.internal;

import android.util.Log;

public final class zzp
{
  public static final int yO = 23 - " PII_LOG".length();
  private static final String yP = null;
  private final String yQ;
  private final String yR;
  
  public zzp(String paramString)
  {
    this(paramString, null);
  }
  
  public zzp(String paramString1, String paramString2)
  {
    zzab.zzb(paramString1, "log tag cannot be null");
    if (paramString1.length() <= 23) {}
    for (boolean bool = true;; bool = false)
    {
      zzab.zzb(bool, "tag \"%s\" is longer than the %d character maximum", new Object[] { paramString1, Integer.valueOf(23) });
      yQ = paramString1;
      if ((paramString2 != null) && (paramString2.length() > 0)) {
        break;
      }
      yR = null;
      return;
    }
    yR = paramString2;
  }
  
  private String zzhq(String paramString)
  {
    if (yR == null) {
      return paramString;
    }
    return yR.concat(paramString);
  }
  
  public void zzae(String paramString1, String paramString2)
  {
    if (zzgc(3)) {
      Log.d(paramString1, zzhq(paramString2));
    }
  }
  
  public void zzaf(String paramString1, String paramString2)
  {
    if (zzgc(5)) {
      Log.w(paramString1, zzhq(paramString2));
    }
  }
  
  public void zzag(String paramString1, String paramString2)
  {
    if (zzgc(6)) {
      Log.e(paramString1, zzhq(paramString2));
    }
  }
  
  public void zzb(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zzgc(4)) {
      Log.i(paramString1, zzhq(paramString2), paramThrowable);
    }
  }
  
  public void zzc(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zzgc(5)) {
      Log.w(paramString1, zzhq(paramString2), paramThrowable);
    }
  }
  
  public void zzd(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zzgc(6)) {
      Log.e(paramString1, zzhq(paramString2), paramThrowable);
    }
  }
  
  public void zze(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zzgc(7))
    {
      Log.e(paramString1, zzhq(paramString2), paramThrowable);
      Log.wtf(paramString1, zzhq(paramString2), paramThrowable);
    }
  }
  
  public boolean zzgc(int paramInt)
  {
    return Log.isLoggable(yQ, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.internal.zzp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */