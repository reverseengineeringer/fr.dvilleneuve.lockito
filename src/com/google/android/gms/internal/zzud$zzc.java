package com.google.android.gms.internal;

import android.os.RemoteException;

public class zzud$zzc
  extends zzud<Long>
{
  public zzud$zzc(int paramInt, String paramString, Long paramLong)
  {
    super(paramInt, paramString, paramLong, null);
  }
  
  public Long zzd(zzug paramzzug)
  {
    try
    {
      long l = paramzzug.getLongFlagValue(getKey(), ((Long)zzjw()).longValue(), getSource());
      return Long.valueOf(l);
    }
    catch (RemoteException paramzzug) {}
    return (Long)zzjw();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzud.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */