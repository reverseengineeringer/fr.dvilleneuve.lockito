package com.google.android.gms.internal;

import android.os.RemoteException;

public abstract class zzud<T>
{
  private final int zzaxm;
  private final String zzaxn;
  private final T zzaxo;
  
  private zzud(int paramInt, String paramString, T paramT)
  {
    zzaxm = paramInt;
    zzaxn = paramString;
    zzaxo = paramT;
    zzuh.zzbfr().zza(this);
  }
  
  public static zza zzb(int paramInt, String paramString, Boolean paramBoolean)
  {
    return new zza(paramInt, paramString, paramBoolean);
  }
  
  public static zzb zzb(int paramInt1, String paramString, int paramInt2)
  {
    return new zzb(paramInt1, paramString, Integer.valueOf(paramInt2));
  }
  
  public static zzc zzb(int paramInt, String paramString, long paramLong)
  {
    return new zzc(paramInt, paramString, Long.valueOf(paramLong));
  }
  
  public static zzd zzc(int paramInt, String paramString1, String paramString2)
  {
    return new zzd(paramInt, paramString1, paramString2);
  }
  
  public T get()
  {
    return (T)zzuh.zzbfs().zzb(this);
  }
  
  public String getKey()
  {
    return zzaxn;
  }
  
  public int getSource()
  {
    return zzaxm;
  }
  
  protected abstract T zza(zzug paramzzug);
  
  public T zzjw()
  {
    return (T)zzaxo;
  }
  
  public static class zza
    extends zzud<Boolean>
  {
    public zza(int paramInt, String paramString, Boolean paramBoolean)
    {
      super(paramString, paramBoolean, null);
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
  
  public static class zzb
    extends zzud<Integer>
  {
    public zzb(int paramInt, String paramString, Integer paramInteger)
    {
      super(paramString, paramInteger, null);
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
  
  public static class zzc
    extends zzud<Long>
  {
    public zzc(int paramInt, String paramString, Long paramLong)
    {
      super(paramString, paramLong, null);
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
  
  public static class zzd
    extends zzud<String>
  {
    public zzd(int paramInt, String paramString1, String paramString2)
    {
      super(paramString1, paramString2, null);
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
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzud
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */