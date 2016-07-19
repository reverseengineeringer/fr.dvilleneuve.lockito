package com.google.android.gms.internal;

import android.os.RemoteException;

public class zzud$zzd
  extends zzud<String>
{
  public zzud$zzd(int paramInt, String paramString1, String paramString2)
  {
    super(paramInt, paramString1, paramString2, null);
  }
  
  public String zze(zzug paramzzug)
  {
    try
    {
      paramzzug = paramzzug.getStringFlagValue(getKey(), (String)zzjw(), getSource());
      return paramzzug;
    }
    catch (RemoteException paramzzug) {}
    return (String)zzjw();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzud.zzd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */