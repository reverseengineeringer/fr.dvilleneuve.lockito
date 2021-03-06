package com.google.android.gms.maps.internal;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd.zza;

public abstract class zzac$zza
  extends Binder
  implements zzac
{
  public zzac$zza()
  {
    attachInterface(this, "com.google.android.gms.maps.internal.ISnapshotReadyCallback");
  }
  
  public static zzac zzim(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
    if ((localIInterface != null) && ((localIInterface instanceof zzac))) {
      return (zzac)localIInterface;
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
      paramParcel2.writeString("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
      return true;
    case 1: 
      paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
      if (paramParcel1.readInt() != 0) {}
      for (paramParcel1 = (Bitmap)Bitmap.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        onSnapshotReady(paramParcel1);
        paramParcel2.writeNoException();
        return true;
      }
    }
    paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
    zzaf(zzd.zza.zzfc(paramParcel1.readStrongBinder()));
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class zza
    implements zzac
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
    
    /* Error */
    public void onSnapshotReady(Bitmap paramBitmap)
      throws RemoteException
    {
      // Byte code:
      //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 33
      //   11: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_1
      //   15: ifnull +41 -> 56
      //   18: aload_2
      //   19: iconst_1
      //   20: invokevirtual 41	android/os/Parcel:writeInt	(I)V
      //   23: aload_1
      //   24: aload_2
      //   25: iconst_0
      //   26: invokevirtual 47	android/graphics/Bitmap:writeToParcel	(Landroid/os/Parcel;I)V
      //   29: aload_0
      //   30: getfield 18	com/google/android/gms/maps/internal/zzac$zza$zza:zzahn	Landroid/os/IBinder;
      //   33: iconst_1
      //   34: aload_2
      //   35: aload_3
      //   36: iconst_0
      //   37: invokeinterface 53 5 0
      //   42: pop
      //   43: aload_3
      //   44: invokevirtual 56	android/os/Parcel:readException	()V
      //   47: aload_3
      //   48: invokevirtual 59	android/os/Parcel:recycle	()V
      //   51: aload_2
      //   52: invokevirtual 59	android/os/Parcel:recycle	()V
      //   55: return
      //   56: aload_2
      //   57: iconst_0
      //   58: invokevirtual 41	android/os/Parcel:writeInt	(I)V
      //   61: goto -32 -> 29
      //   64: astore_1
      //   65: aload_3
      //   66: invokevirtual 59	android/os/Parcel:recycle	()V
      //   69: aload_2
      //   70: invokevirtual 59	android/os/Parcel:recycle	()V
      //   73: aload_1
      //   74: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	75	0	this	zza
      //   0	75	1	paramBitmap	Bitmap
      //   3	67	2	localParcel1	Parcel
      //   7	59	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	14	64	finally
      //   18	29	64	finally
      //   29	47	64	finally
      //   56	61	64	finally
    }
    
    /* Error */
    public void zzaf(com.google.android.gms.dynamic.zzd paramzzd)
      throws RemoteException
    {
      // Byte code:
      //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 33
      //   11: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_1
      //   15: ifnull +42 -> 57
      //   18: aload_1
      //   19: invokeinterface 66 1 0
      //   24: astore_1
      //   25: aload_2
      //   26: aload_1
      //   27: invokevirtual 69	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
      //   30: aload_0
      //   31: getfield 18	com/google/android/gms/maps/internal/zzac$zza$zza:zzahn	Landroid/os/IBinder;
      //   34: iconst_2
      //   35: aload_2
      //   36: aload_3
      //   37: iconst_0
      //   38: invokeinterface 53 5 0
      //   43: pop
      //   44: aload_3
      //   45: invokevirtual 56	android/os/Parcel:readException	()V
      //   48: aload_3
      //   49: invokevirtual 59	android/os/Parcel:recycle	()V
      //   52: aload_2
      //   53: invokevirtual 59	android/os/Parcel:recycle	()V
      //   56: return
      //   57: aconst_null
      //   58: astore_1
      //   59: goto -34 -> 25
      //   62: astore_1
      //   63: aload_3
      //   64: invokevirtual 59	android/os/Parcel:recycle	()V
      //   67: aload_2
      //   68: invokevirtual 59	android/os/Parcel:recycle	()V
      //   71: aload_1
      //   72: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	73	0	this	zza
      //   0	73	1	paramzzd	com.google.android.gms.dynamic.zzd
      //   3	65	2	localParcel1	Parcel
      //   7	57	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	14	62	finally
      //   18	25	62	finally
      //   25	48	62	finally
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.maps.internal.zzac.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */