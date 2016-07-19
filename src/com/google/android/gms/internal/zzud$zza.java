package com.google.android.gms.internal;

import android.os.RemoteException;

public class zzud$zza
  extends zzud<Boolean>
{
  public zzud$zza(int paramInt, String paramString, Boolean paramBoolean)
  {
    super(paramInt, paramString, paramBoolean, null);
  }
  
  public Boolean zzb(zzug paramzzug)
  {
    try
    {
      boolean bool = paramzzug.getBooleanFlagValue(getKey(), ((Boolean)zzjw()).booleanValue(), getSource());
      return Boolean.valueOf(bool);
    }
    catch (RemoteException paramzzug) {}
    return (Boolean)zzjw();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzud.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */