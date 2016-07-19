package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class zzr$zza$zza
  implements zzr
{
  private IBinder zzahn;
  
  zzr$zza$zza(IBinder paramIBinder)
  {
    zzahn = paramIBinder;
  }
  
  public IBinder asBinder()
  {
    return zzahn;
  }
  
  public void cancel()
    throws RemoteException
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      localParcel.writeInterfaceToken("com.google.android.gms.common.internal.ICancelToken");
      zzahn.transact(2, localParcel, null, 1);
      return;
    }
    finally
    {
      localParcel.recycle();
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.internal.zzr.zza.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */