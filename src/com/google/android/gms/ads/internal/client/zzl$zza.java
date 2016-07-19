package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.zzb;

abstract class zzl$zza<T>
{
  private zzl$zza(zzl paramzzl) {}
  
  @Nullable
  protected abstract T zzb(zzx paramzzx)
    throws RemoteException;
  
  @Nullable
  protected abstract T zzin();
  
  @Nullable
  protected final T zziu()
  {
    Object localObject = zzl.zza(zzavh);
    if (localObject == null)
    {
      zzb.zzcy("ClientApi class cannot be loaded.");
      return null;
    }
    try
    {
      localObject = zzb((zzx)localObject);
      return (T)localObject;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzd("Cannot invoke local loader using ClientApi class", localRemoteException);
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.internal.client.zzl.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */