package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.BuildCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.util.List;

class MediaBrowserCompat$MediaBrowserImplApi21
  implements MediaBrowserCompat.MediaBrowserImpl, MediaBrowserCompat.MediaBrowserServiceCallbackImpl, MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal
{
  protected final Object mBrowserObj;
  protected Messenger mCallbacksMessenger;
  protected final MediaBrowserCompat.CallbackHandler mHandler = new MediaBrowserCompat.CallbackHandler(this);
  protected final Bundle mRootHints;
  protected MediaBrowserCompat.ServiceBinderWrapper mServiceBinderWrapper;
  private final ArrayMap<String, MediaBrowserCompat.Subscription> mSubscriptions = new ArrayMap();
  
  public MediaBrowserCompat$MediaBrowserImplApi21(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
  {
    if ((Build.VERSION.SDK_INT < 24) && (!BuildCompat.isAtLeastN()))
    {
      Bundle localBundle = paramBundle;
      if (paramBundle == null) {
        localBundle = new Bundle();
      }
      localBundle.putInt("extra_client_version", 1);
      mRootHints = new Bundle(localBundle);
      paramConnectionCallback.setInternalConnectionCallback(this);
      mBrowserObj = MediaBrowserCompatApi21.createBrowser(paramContext, paramComponentName, mConnectionCallbackObj, mRootHints);
      return;
    }
    if (paramBundle == null) {}
    for (paramBundle = null;; paramBundle = new Bundle(paramBundle))
    {
      mRootHints = paramBundle;
      break;
    }
  }
  
  public void connect()
  {
    MediaBrowserCompatApi21.connect(mBrowserObj);
  }
  
  public void disconnect()
  {
    if ((mServiceBinderWrapper != null) && (mCallbacksMessenger != null)) {}
    try
    {
      mServiceBinderWrapper.unregisterCallbackMessenger(mCallbacksMessenger);
      MediaBrowserCompatApi21.disconnect(mBrowserObj);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
      }
    }
  }
  
  @Nullable
  public Bundle getExtras()
  {
    return MediaBrowserCompatApi21.getExtras(mBrowserObj);
  }
  
  public void getItem(@NonNull final String paramString, @NonNull final MediaBrowserCompat.ItemCallback paramItemCallback)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("mediaId is empty");
    }
    if (paramItemCallback == null) {
      throw new IllegalArgumentException("cb is null");
    }
    if (!MediaBrowserCompatApi21.isConnected(mBrowserObj))
    {
      Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
      mHandler.post(new Runnable()
      {
        public void run()
        {
          paramItemCallback.onError(paramString);
        }
      });
      return;
    }
    if (mServiceBinderWrapper == null)
    {
      mHandler.post(new Runnable()
      {
        public void run()
        {
          paramItemCallback.onItemLoaded(null);
        }
      });
      return;
    }
    MediaBrowserCompat.ItemReceiver localItemReceiver = new MediaBrowserCompat.ItemReceiver(paramString, paramItemCallback, mHandler);
    try
    {
      mServiceBinderWrapper.getMediaItem(paramString, localItemReceiver, mCallbacksMessenger);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.i("MediaBrowserCompat", "Remote error getting media item: " + paramString);
      mHandler.post(new Runnable()
      {
        public void run()
        {
          paramItemCallback.onError(paramString);
        }
      });
    }
  }
  
  @NonNull
  public String getRoot()
  {
    return MediaBrowserCompatApi21.getRoot(mBrowserObj);
  }
  
  public ComponentName getServiceComponent()
  {
    return MediaBrowserCompatApi21.getServiceComponent(mBrowserObj);
  }
  
  @NonNull
  public MediaSessionCompat.Token getSessionToken()
  {
    return MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(mBrowserObj));
  }
  
  public boolean isConnected()
  {
    return MediaBrowserCompatApi21.isConnected(mBrowserObj);
  }
  
  public void onConnected()
  {
    Object localObject = MediaBrowserCompatApi21.getExtras(mBrowserObj);
    if (localObject == null) {}
    do
    {
      return;
      localObject = BundleCompat.getBinder((Bundle)localObject, "extra_messenger");
    } while (localObject == null);
    mServiceBinderWrapper = new MediaBrowserCompat.ServiceBinderWrapper((IBinder)localObject, mRootHints);
    mCallbacksMessenger = new Messenger(mHandler);
    mHandler.setCallbacksMessenger(mCallbacksMessenger);
    try
    {
      mServiceBinderWrapper.registerCallbackMessenger(mCallbacksMessenger);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
    }
  }
  
  public void onConnectionFailed() {}
  
  public void onConnectionFailed(Messenger paramMessenger) {}
  
  public void onConnectionSuspended()
  {
    mServiceBinderWrapper = null;
    mCallbacksMessenger = null;
    mHandler.setCallbacksMessenger(null);
  }
  
  public void onLoadChildren(Messenger paramMessenger, String paramString, List paramList, Bundle paramBundle)
  {
    if (mCallbacksMessenger != paramMessenger) {}
    do
    {
      do
      {
        return;
        paramMessenger = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
        if (paramMessenger != null) {
          break;
        }
      } while (!MediaBrowserCompat.access$700());
      Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + paramString);
      return;
      paramMessenger = paramMessenger.getCallback(paramBundle);
    } while (paramMessenger == null);
    if (paramBundle == null)
    {
      paramMessenger.onChildrenLoaded(paramString, paramList);
      return;
    }
    paramMessenger.onChildrenLoaded(paramString, paramList, paramBundle);
  }
  
  public void onServiceConnected(Messenger paramMessenger, String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle) {}
  
  public void subscribe(@NonNull String paramString, Bundle paramBundle, @NonNull MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
  {
    MediaBrowserCompat.Subscription localSubscription2 = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
    MediaBrowserCompat.Subscription localSubscription1 = localSubscription2;
    if (localSubscription2 == null)
    {
      localSubscription1 = new MediaBrowserCompat.Subscription();
      mSubscriptions.put(paramString, localSubscription1);
    }
    MediaBrowserCompat.SubscriptionCallback.access$2100(paramSubscriptionCallback, localSubscription1);
    localSubscription1.putCallback(paramBundle, paramSubscriptionCallback);
    if (mServiceBinderWrapper == null)
    {
      MediaBrowserCompatApi21.subscribe(mBrowserObj, paramString, MediaBrowserCompat.SubscriptionCallback.access$2200(paramSubscriptionCallback));
      return;
    }
    try
    {
      mServiceBinderWrapper.addSubscription(paramString, MediaBrowserCompat.SubscriptionCallback.access$1200(paramSubscriptionCallback), paramBundle, mCallbacksMessenger);
      return;
    }
    catch (RemoteException paramBundle)
    {
      Log.i("MediaBrowserCompat", "Remote error subscribing media item: " + paramString);
    }
  }
  
  public void unsubscribe(@NonNull String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
  {
    MediaBrowserCompat.Subscription localSubscription = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
    if (localSubscription == null) {
      return;
    }
    if (mServiceBinderWrapper == null) {
      if (paramSubscriptionCallback == null) {
        MediaBrowserCompatApi21.unsubscribe(mBrowserObj, paramString);
      }
    }
    for (;;)
    {
      if ((localSubscription.isEmpty()) || (paramSubscriptionCallback == null))
      {
        mSubscriptions.remove(paramString);
        return;
        List localList1 = localSubscription.getCallbacks();
        localList3 = localSubscription.getOptionsList();
        i = localList1.size() - 1;
        while (i >= 0)
        {
          if (localList1.get(i) == paramSubscriptionCallback)
          {
            localList1.remove(i);
            localList3.remove(i);
          }
          i -= 1;
        }
        if (localList1.size() != 0) {
          continue;
        }
        MediaBrowserCompatApi21.unsubscribe(mBrowserObj, paramString);
        continue;
        if (paramSubscriptionCallback == null) {
          try
          {
            mServiceBinderWrapper.removeSubscription(paramString, null, mCallbacksMessenger);
          }
          catch (RemoteException localRemoteException)
          {
            Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + paramString);
          }
        }
      }
      else
      {
        break;
      }
      List localList2 = localSubscription.getCallbacks();
      List localList3 = localSubscription.getOptionsList();
      int i = localList2.size() - 1;
      while (i >= 0)
      {
        if (localList2.get(i) == paramSubscriptionCallback)
        {
          mServiceBinderWrapper.removeSubscription(paramString, MediaBrowserCompat.SubscriptionCallback.access$1200(paramSubscriptionCallback), mCallbacksMessenger);
          localList2.remove(i);
          localList3.remove(i);
        }
        i -= 1;
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */