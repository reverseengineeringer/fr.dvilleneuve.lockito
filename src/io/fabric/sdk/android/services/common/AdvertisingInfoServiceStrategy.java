package io.fabric.sdk.android.services.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class AdvertisingInfoServiceStrategy
  implements AdvertisingInfoStrategy
{
  public static final String GOOGLE_PLAY_SERVICES_INTENT = "com.google.android.gms.ads.identifier.service.START";
  public static final String GOOGLE_PLAY_SERVICES_INTENT_PACKAGE_NAME = "com.google.android.gms";
  private static final String GOOGLE_PLAY_SERVICE_PACKAGE_NAME = "com.android.vending";
  private final Context context;
  
  public AdvertisingInfoServiceStrategy(Context paramContext)
  {
    context = paramContext.getApplicationContext();
  }
  
  /* Error */
  public AdvertisingInfo getAdvertisingInfo()
  {
    // Byte code:
    //   0: invokestatic 52	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   3: invokestatic 55	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   6: if_acmpne +17 -> 23
    //   9: invokestatic 61	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   12: ldc 63
    //   14: ldc 65
    //   16: invokeinterface 71 3 0
    //   21: aconst_null
    //   22: areturn
    //   23: aload_0
    //   24: getfield 39	io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy:context	Landroid/content/Context;
    //   27: invokevirtual 75	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   30: ldc 24
    //   32: iconst_0
    //   33: invokevirtual 81	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   36: pop
    //   37: new 10	io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy$AdvertisingConnection
    //   40: dup
    //   41: aconst_null
    //   42: invokespecial 84	io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy$AdvertisingConnection:<init>	(Lio/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy$1;)V
    //   45: astore_2
    //   46: new 86	android/content/Intent
    //   49: dup
    //   50: ldc 18
    //   52: invokespecial 89	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   55: astore_3
    //   56: aload_3
    //   57: ldc 21
    //   59: invokevirtual 93	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   62: pop
    //   63: aload_0
    //   64: getfield 39	io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy:context	Landroid/content/Context;
    //   67: aload_3
    //   68: aload_2
    //   69: iconst_1
    //   70: invokevirtual 97	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   73: istore_1
    //   74: iload_1
    //   75: ifeq +108 -> 183
    //   78: new 13	io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy$AdvertisingInterface
    //   81: dup
    //   82: aload_2
    //   83: invokevirtual 101	io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy$AdvertisingConnection:getBinder	()Landroid/os/IBinder;
    //   86: invokespecial 104	io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy$AdvertisingInterface:<init>	(Landroid/os/IBinder;)V
    //   89: astore_3
    //   90: new 106	io/fabric/sdk/android/services/common/AdvertisingInfo
    //   93: dup
    //   94: aload_3
    //   95: invokevirtual 110	io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy$AdvertisingInterface:getId	()Ljava/lang/String;
    //   98: aload_3
    //   99: invokevirtual 114	io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy$AdvertisingInterface:isLimitAdTrackingEnabled	()Z
    //   102: invokespecial 117	io/fabric/sdk/android/services/common/AdvertisingInfo:<init>	(Ljava/lang/String;Z)V
    //   105: astore_3
    //   106: aload_0
    //   107: getfield 39	io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy:context	Landroid/content/Context;
    //   110: aload_2
    //   111: invokevirtual 121	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   114: aload_3
    //   115: areturn
    //   116: astore_2
    //   117: invokestatic 61	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   120: ldc 63
    //   122: ldc 123
    //   124: aload_2
    //   125: invokeinterface 126 4 0
    //   130: aconst_null
    //   131: areturn
    //   132: astore_3
    //   133: invokestatic 61	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   136: ldc 63
    //   138: ldc -128
    //   140: aload_3
    //   141: invokeinterface 131 4 0
    //   146: aload_0
    //   147: getfield 39	io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy:context	Landroid/content/Context;
    //   150: aload_2
    //   151: invokevirtual 121	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   154: aconst_null
    //   155: areturn
    //   156: astore_2
    //   157: invokestatic 61	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   160: ldc 63
    //   162: ldc -123
    //   164: aload_2
    //   165: invokeinterface 126 4 0
    //   170: aconst_null
    //   171: areturn
    //   172: astore_3
    //   173: aload_0
    //   174: getfield 39	io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy:context	Landroid/content/Context;
    //   177: aload_2
    //   178: invokevirtual 121	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   181: aload_3
    //   182: athrow
    //   183: invokestatic 61	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   186: ldc 63
    //   188: ldc -123
    //   190: invokeinterface 71 3 0
    //   195: aconst_null
    //   196: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	197	0	this	AdvertisingInfoServiceStrategy
    //   73	2	1	bool	boolean
    //   45	66	2	localAdvertisingConnection	AdvertisingConnection
    //   116	35	2	localException1	Exception
    //   156	22	2	localThrowable	Throwable
    //   55	60	3	localObject1	Object
    //   132	9	3	localException2	Exception
    //   172	10	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   23	37	116	java/lang/Exception
    //   78	106	132	java/lang/Exception
    //   63	74	156	java/lang/Throwable
    //   106	114	156	java/lang/Throwable
    //   146	154	156	java/lang/Throwable
    //   173	183	156	java/lang/Throwable
    //   183	195	156	java/lang/Throwable
    //   78	106	172	finally
    //   133	146	172	finally
  }
  
  private static final class AdvertisingConnection
    implements ServiceConnection
  {
    private static final int QUEUE_TIMEOUT_IN_MS = 200;
    private final LinkedBlockingQueue<IBinder> queue = new LinkedBlockingQueue(1);
    private boolean retrieved = false;
    
    public IBinder getBinder()
    {
      if (retrieved) {
        Fabric.getLogger().e("Fabric", "getBinder already called");
      }
      retrieved = true;
      try
      {
        IBinder localIBinder = (IBinder)queue.poll(200L, TimeUnit.MILLISECONDS);
        return localIBinder;
      }
      catch (InterruptedException localInterruptedException) {}
      return null;
    }
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      try
      {
        queue.put(paramIBinder);
        return;
      }
      catch (InterruptedException paramComponentName) {}
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      queue.clear();
    }
  }
  
  private static final class AdvertisingInterface
    implements IInterface
  {
    public static final String ADVERTISING_ID_SERVICE_INTERFACE_TOKEN = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";
    private static final int AD_TRANSACTION_CODE_ID = 1;
    private static final int AD_TRANSACTION_CODE_LIMIT_AD_TRACKING = 2;
    private static final int FLAGS_NONE = 0;
    private final IBinder binder;
    
    public AdvertisingInterface(IBinder paramIBinder)
    {
      binder = paramIBinder;
    }
    
    public IBinder asBinder()
    {
      return binder;
    }
    
    public String getId()
      throws RemoteException
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        binder.transact(1, localParcel1, localParcel2, 0);
        localParcel2.readException();
        String str = localParcel2.readString();
        return str;
      }
      catch (Exception localException)
      {
        Fabric.getLogger().d("Fabric", "Could not get parcel from Google Play Service to capture AdvertisingId");
        return null;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    /* Error */
    public boolean isLimitAdTrackingEnabled()
      throws RemoteException
    {
      // Byte code:
      //   0: invokestatic 44	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_3
      //   4: invokestatic 44	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore 4
      //   9: aload_3
      //   10: ldc 13
      //   12: invokevirtual 48	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   15: aload_3
      //   16: iconst_1
      //   17: invokevirtual 86	android/os/Parcel:writeInt	(I)V
      //   20: aload_0
      //   21: getfield 29	io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy$AdvertisingInterface:binder	Landroid/os/IBinder;
      //   24: iconst_2
      //   25: aload_3
      //   26: aload 4
      //   28: iconst_0
      //   29: invokeinterface 54 5 0
      //   34: pop
      //   35: aload 4
      //   37: invokevirtual 57	android/os/Parcel:readException	()V
      //   40: aload 4
      //   42: invokevirtual 90	android/os/Parcel:readInt	()I
      //   45: istore_1
      //   46: iload_1
      //   47: ifeq +16 -> 63
      //   50: iconst_1
      //   51: istore_2
      //   52: aload 4
      //   54: invokevirtual 63	android/os/Parcel:recycle	()V
      //   57: aload_3
      //   58: invokevirtual 63	android/os/Parcel:recycle	()V
      //   61: iload_2
      //   62: ireturn
      //   63: iconst_0
      //   64: istore_2
      //   65: goto -13 -> 52
      //   68: astore 5
      //   70: invokestatic 69	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
      //   73: ldc 71
      //   75: ldc 92
      //   77: invokeinterface 79 3 0
      //   82: aload 4
      //   84: invokevirtual 63	android/os/Parcel:recycle	()V
      //   87: aload_3
      //   88: invokevirtual 63	android/os/Parcel:recycle	()V
      //   91: iconst_0
      //   92: ireturn
      //   93: astore 5
      //   95: aload 4
      //   97: invokevirtual 63	android/os/Parcel:recycle	()V
      //   100: aload_3
      //   101: invokevirtual 63	android/os/Parcel:recycle	()V
      //   104: aload 5
      //   106: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	107	0	this	AdvertisingInterface
      //   45	2	1	i	int
      //   51	14	2	bool	boolean
      //   3	98	3	localParcel1	Parcel
      //   7	89	4	localParcel2	Parcel
      //   68	1	5	localException	Exception
      //   93	12	5	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   9	46	68	java/lang/Exception
      //   9	46	93	finally
      //   70	82	93	finally
    }
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.common.AdvertisingInfoServiceStrategy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */