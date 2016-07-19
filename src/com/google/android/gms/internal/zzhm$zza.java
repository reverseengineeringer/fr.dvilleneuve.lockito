package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract class zzhm$zza
  extends Binder
  implements zzhm
{
  public zzhm$zza()
  {
    attachInterface(this, "com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
  }
  
  public static zzhm zzaq(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
    if ((localIInterface != null) && ((localIInterface instanceof zzhm))) {
      return (zzhm)localIInterface;
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
    Object localObject2 = null;
    Object localObject3 = null;
    Object localObject1 = null;
    switch (paramInt1)
    {
    default: 
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
    case 1598968902: 
      paramParcel2.writeString("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
      return true;
    case 1: 
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
      if (paramParcel1.readInt() != 0) {
        localObject1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
      }
      onCreate((Bundle)localObject1);
      paramParcel2.writeNoException();
      return true;
    case 2: 
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
      onRestart();
      paramParcel2.writeNoException();
      return true;
    case 3: 
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
      onStart();
      paramParcel2.writeNoException();
      return true;
    case 4: 
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
      onResume();
      paramParcel2.writeNoException();
      return true;
    case 5: 
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
      onPause();
      paramParcel2.writeNoException();
      return true;
    case 6: 
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
      localObject1 = localObject2;
      if (paramParcel1.readInt() != 0) {
        localObject1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
      }
      onSaveInstanceState((Bundle)localObject1);
      paramParcel2.writeNoException();
      if (localObject1 != null)
      {
        paramParcel2.writeInt(1);
        ((Bundle)localObject1).writeToParcel(paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
      return true;
    case 7: 
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
      onStop();
      paramParcel2.writeNoException();
      return true;
    case 8: 
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
      onDestroy();
      paramParcel2.writeNoException();
      return true;
    case 9: 
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
      zzdc();
      paramParcel2.writeNoException();
      return true;
    case 10: 
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
      onBackPressed();
      paramParcel2.writeNoException();
      return true;
    case 11: 
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
      boolean bool = zzny();
      paramParcel2.writeNoException();
      if (bool) {}
      for (paramInt1 = 1;; paramInt1 = 0)
      {
        paramParcel2.writeInt(paramInt1);
        return true;
      }
    }
    paramParcel1.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
    paramInt1 = paramParcel1.readInt();
    paramInt2 = paramParcel1.readInt();
    localObject1 = localObject3;
    if (paramParcel1.readInt() != 0) {
      localObject1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
    }
    onActivityResult(paramInt1, paramInt2, (Intent)localObject1);
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class zza
    implements zzhm
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
    public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
      throws RemoteException
    {
      // Byte code:
      //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore 4
      //   5: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   8: astore 5
      //   10: aload 4
      //   12: ldc 33
      //   14: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   17: aload 4
      //   19: iload_1
      //   20: invokevirtual 41	android/os/Parcel:writeInt	(I)V
      //   23: aload 4
      //   25: iload_2
      //   26: invokevirtual 41	android/os/Parcel:writeInt	(I)V
      //   29: aload_3
      //   30: ifnull +49 -> 79
      //   33: aload 4
      //   35: iconst_1
      //   36: invokevirtual 41	android/os/Parcel:writeInt	(I)V
      //   39: aload_3
      //   40: aload 4
      //   42: iconst_0
      //   43: invokevirtual 47	android/content/Intent:writeToParcel	(Landroid/os/Parcel;I)V
      //   46: aload_0
      //   47: getfield 18	com/google/android/gms/internal/zzhm$zza$zza:zzahn	Landroid/os/IBinder;
      //   50: bipush 12
      //   52: aload 4
      //   54: aload 5
      //   56: iconst_0
      //   57: invokeinterface 53 5 0
      //   62: pop
      //   63: aload 5
      //   65: invokevirtual 56	android/os/Parcel:readException	()V
      //   68: aload 5
      //   70: invokevirtual 59	android/os/Parcel:recycle	()V
      //   73: aload 4
      //   75: invokevirtual 59	android/os/Parcel:recycle	()V
      //   78: return
      //   79: aload 4
      //   81: iconst_0
      //   82: invokevirtual 41	android/os/Parcel:writeInt	(I)V
      //   85: goto -39 -> 46
      //   88: astore_3
      //   89: aload 5
      //   91: invokevirtual 59	android/os/Parcel:recycle	()V
      //   94: aload 4
      //   96: invokevirtual 59	android/os/Parcel:recycle	()V
      //   99: aload_3
      //   100: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	101	0	this	zza
      //   0	101	1	paramInt1	int
      //   0	101	2	paramInt2	int
      //   0	101	3	paramIntent	Intent
      //   3	92	4	localParcel1	Parcel
      //   8	82	5	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   10	29	88	finally
      //   33	46	88	finally
      //   46	68	88	finally
      //   79	85	88	finally
    }
    
    public void onBackPressed()
      throws RemoteException
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
        zzahn.transact(10, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    /* Error */
    public void onCreate(Bundle paramBundle)
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
      //   26: invokevirtual 66	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
      //   29: aload_0
      //   30: getfield 18	com/google/android/gms/internal/zzhm$zza$zza:zzahn	Landroid/os/IBinder;
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
      //   0	75	1	paramBundle	Bundle
      //   3	67	2	localParcel1	Parcel
      //   7	59	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	14	64	finally
      //   18	29	64	finally
      //   29	47	64	finally
      //   56	61	64	finally
    }
    
    public void onDestroy()
      throws RemoteException
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
        zzahn.transact(8, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void onPause()
      throws RemoteException
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
        zzahn.transact(5, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void onRestart()
      throws RemoteException
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
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
    
    public void onResume()
      throws RemoteException
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
        zzahn.transact(4, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    /* Error */
    public void onSaveInstanceState(Bundle paramBundle)
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
      //   15: ifnull +54 -> 69
      //   18: aload_2
      //   19: iconst_1
      //   20: invokevirtual 41	android/os/Parcel:writeInt	(I)V
      //   23: aload_1
      //   24: aload_2
      //   25: iconst_0
      //   26: invokevirtual 66	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
      //   29: aload_0
      //   30: getfield 18	com/google/android/gms/internal/zzhm$zza$zza:zzahn	Landroid/os/IBinder;
      //   33: bipush 6
      //   35: aload_2
      //   36: aload_3
      //   37: iconst_0
      //   38: invokeinterface 53 5 0
      //   43: pop
      //   44: aload_3
      //   45: invokevirtual 56	android/os/Parcel:readException	()V
      //   48: aload_3
      //   49: invokevirtual 75	android/os/Parcel:readInt	()I
      //   52: ifeq +8 -> 60
      //   55: aload_1
      //   56: aload_3
      //   57: invokevirtual 79	android/os/Bundle:readFromParcel	(Landroid/os/Parcel;)V
      //   60: aload_3
      //   61: invokevirtual 59	android/os/Parcel:recycle	()V
      //   64: aload_2
      //   65: invokevirtual 59	android/os/Parcel:recycle	()V
      //   68: return
      //   69: aload_2
      //   70: iconst_0
      //   71: invokevirtual 41	android/os/Parcel:writeInt	(I)V
      //   74: goto -45 -> 29
      //   77: astore_1
      //   78: aload_3
      //   79: invokevirtual 59	android/os/Parcel:recycle	()V
      //   82: aload_2
      //   83: invokevirtual 59	android/os/Parcel:recycle	()V
      //   86: aload_1
      //   87: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	88	0	this	zza
      //   0	88	1	paramBundle	Bundle
      //   3	80	2	localParcel1	Parcel
      //   7	72	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	14	77	finally
      //   18	29	77	finally
      //   29	60	77	finally
      //   69	74	77	finally
    }
    
    public void onStart()
      throws RemoteException
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
        zzahn.transact(3, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void onStop()
      throws RemoteException
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
        zzahn.transact(7, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void zzdc()
      throws RemoteException
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
        zzahn.transact(9, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public boolean zzny()
      throws RemoteException
    {
      boolean bool = false;
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
        zzahn.transact(11, localParcel1, localParcel2, 0);
        localParcel2.readException();
        int i = localParcel2.readInt();
        if (i != 0) {
          bool = true;
        }
        return bool;
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
 * Qualified Name:     com.google.android.gms.internal.zzhm.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */