package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzb$zza
  extends Binder
  implements zzb
{
  public zzb$zza()
  {
    attachInterface(this, "com.google.android.gms.maps.internal.ICancelableCallback");
  }
  
  public static zzb zzhg(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICancelableCallback");
    if ((localIInterface != null) && ((localIInterface instanceof zzb))) {
      return (zzb)localIInterface;
    }
    return new zza(paramIBinder);
  }
  
  public IBinder asBinder()
  {
    return this;
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    switch (paramInt1)
    {
    default: 
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
    case 1598968902: 
      paramParcel2.writeString("com.google.android.gms.maps.internal.ICancelableCallback");
      return true;
    case 1: 
      paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICancelableCallback");
      onFinish();
      paramParcel2.writeNoException();
      return true;
    }
    paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICancelableCallback");
    onCancel();
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class zza
    implements zzb
  {
    private IBinder zzahn;
    
    zza(IBinder paramIBinder)
    {
      zzahn = paramIBinder;
    }
    
    public IBinder asBinder()
    {
      return zzahn;
    }
    
    public void onCancel()
      throws RemoteException
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.ICancelableCallback");
        zzahn.transact(2, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void onFinish()
      throws RemoteException
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.ICancelableCallback");
        zzahn.transact(1, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.maps.internal.zzb.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */