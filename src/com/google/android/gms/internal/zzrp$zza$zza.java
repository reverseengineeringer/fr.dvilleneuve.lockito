package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class zzrp$zza$zza
  implements zzrp
{
  private IBinder zzahn;
  
  zzrp$zza$zza(IBinder paramIBinder)
  {
    zzahn = paramIBinder;
  }
  
  public IBinder asBinder()
  {
    return zzahn;
  }
  
  public void zza(zzro paramzzro)
    throws RemoteException
  {
    IBinder localIBinder = null;
    Parcel localParcel = Parcel.obtain();
    try
    {
      localParcel.writeInterfaceToken("com.google.android.gms.common.internal.service.ICommonService");
      if (paramzzro != null) {
        localIBinder = paramzzro.asBinder();
      }
      localParcel.writeStrongBinder(localIBinder);
      zzahn.transact(1, localParcel, null, 1);
      return;
    }
    finally
    {
      localParcel.recycle();
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzrp.zza.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */