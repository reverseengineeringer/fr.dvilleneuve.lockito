package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.internal.zzp;

public class zzrq
{
  private final String mTag;
  private final String yR;
  private final zzp zk;
  private final int zzczi;
  
  private zzrq(String paramString1, String paramString2)
  {
    yR = paramString2;
    mTag = paramString1;
    zk = new zzp(paramString1);
    zzczi = getLogLevel();
  }
  
  public zzrq(String paramString, String... paramVarArgs)
  {
    this(paramString, zzc(paramVarArgs));
  }
  
  private int getLogLevel()
  {
    int i = 2;
    while ((7 >= i) && (!Log.isLoggable(mTag, i))) {
      i += 1;
    }
    return i;
  }
  
  private static String zzc(String... paramVarArgs)
  {
    if (paramVarArgs.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[');
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      String str = paramVarArgs[i];
      if (localStringBuilder.length() > 1) {
        localStringBuilder.append(",");
      }
      localStringBuilder.append(str);
      i += 1;
    }
    localStringBuilder.append(']').append(' ');
    return localStringBuilder.toString();
  }
  
  protected String format(String paramString, Object... paramVarArgs)
  {
    String str = paramString;
    if (paramVarArgs != null)
    {
      str = paramString;
      if (paramVarArgs.length > 0) {
        str = String.format(paramString, paramVarArgs);
      }
    }
    return yR.concat(str);
  }
  
  public void zza(String paramString, Object... paramVarArgs)
  {
    if (zzaz(2)) {
      Log.v(mTag, format(paramString, paramVarArgs));
    }
  }
  
  public boolean zzaz(int paramInt)
  {
    return zzczi <= paramInt;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzrq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */