package com.google.android.gms.internal;

import android.os.RemoteException;

public class zzud$zzb
  extends zzud<Integer>
{
  public zzud$zzb(int paramInt, String paramString, Integer paramInteger)
  {
    super(paramInt, paramString, paramInteger, null);
  }
  
  public Integer zzc(zzug paramzzug)
  {
    try
    {
      int i = paramzzug.getIntFlagValue(getKey(), ((Integer)zzjw()).intValue(), getSource());
      return Integer.valueOf(i);
    }
    catch (RemoteException paramzzug) {}
    return (Integer)zzjw();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzud.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */