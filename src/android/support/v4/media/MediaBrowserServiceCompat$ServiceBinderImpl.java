package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;

class MediaBrowserServiceCompat$ServiceBinderImpl
{
  private MediaBrowserServiceCompat$ServiceBinderImpl(MediaBrowserServiceCompat paramMediaBrowserServiceCompat) {}
  
  public void addSubscription(final String paramString, final IBinder paramIBinder, final Bundle paramBundle, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
  {
    MediaBrowserServiceCompat.access$100(this$0).postOrRun(new Runnable()
    {
      public void run()
      {
        Object localObject = paramServiceCallbacks.asBinder();
        localObject = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.access$200(this$0).get(localObject);
        if (localObject == null)
        {
          Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + paramString);
          return;
        }
        MediaBrowserServiceCompat.access$800(this$0, paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject, paramIBinder, paramBundle);
      }
    });
  }
  
  public void connect(final String paramString, final int paramInt, final Bundle paramBundle, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
  {
    if (!MediaBrowserServiceCompat.access$600(this$0, paramString, paramInt)) {
      throw new IllegalArgumentException("Package/uid mismatch: uid=" + paramInt + " package=" + paramString);
    }
    MediaBrowserServiceCompat.access$100(this$0).postOrRun(new Runnable()
    {
      public void run()
      {
        IBinder localIBinder = paramServiceCallbacks.asBinder();
        MediaBrowserServiceCompat.access$200(this$0).remove(localIBinder);
        MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = new MediaBrowserServiceCompat.ConnectionRecord(this$0, null);
        pkg = paramString;
        rootHints = paramBundle;
        callbacks = paramServiceCallbacks;
        root = this$0.onGetRoot(paramString, paramInt, paramBundle);
        if (root == null) {
          Log.i("MBServiceCompat", "No root for client " + paramString + " from service " + getClass().getName());
        }
        for (;;)
        {
          try
          {
            paramServiceCallbacks.onConnectFailed();
            return;
          }
          catch (RemoteException localRemoteException1)
          {
            Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + paramString);
            return;
          }
          try
          {
            MediaBrowserServiceCompat.access$200(this$0).put(localRemoteException1, localConnectionRecord);
            if (this$0.mSession != null)
            {
              paramServiceCallbacks.onConnect(root.getRootId(), this$0.mSession, root.getExtras());
              return;
            }
          }
          catch (RemoteException localRemoteException2)
          {
            Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + paramString);
            MediaBrowserServiceCompat.access$200(this$0).remove(localRemoteException1);
          }
        }
      }
    });
  }
  
  public void disconnect(final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
  {
    MediaBrowserServiceCompat.access$100(this$0).postOrRun(new Runnable()
    {
      public void run()
      {
        IBinder localIBinder = paramServiceCallbacks.asBinder();
        if ((MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.access$200(this$0).remove(localIBinder) != null) {}
      }
    });
  }
  
  public void getMediaItem(final String paramString, final ResultReceiver paramResultReceiver, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramResultReceiver == null)) {
      return;
    }
    MediaBrowserServiceCompat.access$100(this$0).postOrRun(new Runnable()
    {
      public void run()
      {
        Object localObject = paramServiceCallbacks.asBinder();
        localObject = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.access$200(this$0).get(localObject);
        if (localObject == null)
        {
          Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + paramString);
          return;
        }
        MediaBrowserServiceCompat.access$1000(this$0, paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject, paramResultReceiver);
      }
    });
  }
  
  public void registerCallbacks(final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks, final Bundle paramBundle)
  {
    MediaBrowserServiceCompat.access$100(this$0).postOrRun(new Runnable()
    {
      public void run()
      {
        IBinder localIBinder = paramServiceCallbacks.asBinder();
        MediaBrowserServiceCompat.access$200(this$0).remove(localIBinder);
        MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = new MediaBrowserServiceCompat.ConnectionRecord(this$0, null);
        callbacks = paramServiceCallbacks;
        rootHints = paramBundle;
        MediaBrowserServiceCompat.access$200(this$0).put(localIBinder, localConnectionRecord);
      }
    });
  }
  
  public void removeSubscription(final String paramString, final IBinder paramIBinder, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
  {
    MediaBrowserServiceCompat.access$100(this$0).postOrRun(new Runnable()
    {
      public void run()
      {
        Object localObject = paramServiceCallbacks.asBinder();
        localObject = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.access$200(this$0).get(localObject);
        if (localObject == null) {
          Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + paramString);
        }
        while (MediaBrowserServiceCompat.access$900(this$0, paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject, paramIBinder)) {
          return;
        }
        Log.w("MBServiceCompat", "removeSubscription called for " + paramString + " which is not subscribed");
      }
    });
  }
  
  public void unregisterCallbacks(final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
  {
    MediaBrowserServiceCompat.access$100(this$0).postOrRun(new Runnable()
    {
      public void run()
      {
        IBinder localIBinder = paramServiceCallbacks.asBinder();
        MediaBrowserServiceCompat.access$200(this$0).remove(localIBinder);
      }
    });
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.ServiceBinderImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */