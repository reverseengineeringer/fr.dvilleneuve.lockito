package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

class MediaBrowserCompat$MediaBrowserImplBase
  implements MediaBrowserCompat.MediaBrowserImpl, MediaBrowserCompat.MediaBrowserServiceCallbackImpl
{
  private static final int CONNECT_STATE_CONNECTED = 2;
  private static final int CONNECT_STATE_CONNECTING = 1;
  private static final int CONNECT_STATE_DISCONNECTED = 0;
  private static final int CONNECT_STATE_SUSPENDED = 3;
  private final MediaBrowserCompat.ConnectionCallback mCallback;
  private Messenger mCallbacksMessenger;
  private final Context mContext;
  private Bundle mExtras;
  private final MediaBrowserCompat.CallbackHandler mHandler = new MediaBrowserCompat.CallbackHandler(this);
  private MediaSessionCompat.Token mMediaSessionToken;
  private final Bundle mRootHints;
  private String mRootId;
  private MediaBrowserCompat.ServiceBinderWrapper mServiceBinderWrapper;
  private final ComponentName mServiceComponent;
  private MediaServiceConnection mServiceConnection;
  private int mState = 0;
  private final ArrayMap<String, MediaBrowserCompat.Subscription> mSubscriptions = new ArrayMap();
  
  public MediaBrowserCompat$MediaBrowserImplBase(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context must not be null");
    }
    if (paramComponentName == null) {
      throw new IllegalArgumentException("service component must not be null");
    }
    if (paramConnectionCallback == null) {
      throw new IllegalArgumentException("connection callback must not be null");
    }
    mContext = paramContext;
    mServiceComponent = paramComponentName;
    mCallback = paramConnectionCallback;
    if (paramBundle == null) {}
    for (paramContext = null;; paramContext = new Bundle(paramBundle))
    {
      mRootHints = paramContext;
      return;
    }
  }
  
  private void forceCloseConnection()
  {
    if (mServiceConnection != null) {
      mContext.unbindService(mServiceConnection);
    }
    mState = 0;
    mServiceConnection = null;
    mServiceBinderWrapper = null;
    mCallbacksMessenger = null;
    mHandler.setCallbacksMessenger(null);
    mRootId = null;
    mMediaSessionToken = null;
  }
  
  private static String getStateLabel(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "UNKNOWN/" + paramInt;
    case 0: 
      return "CONNECT_STATE_DISCONNECTED";
    case 1: 
      return "CONNECT_STATE_CONNECTING";
    case 2: 
      return "CONNECT_STATE_CONNECTED";
    }
    return "CONNECT_STATE_SUSPENDED";
  }
  
  private boolean isCurrent(Messenger paramMessenger, String paramString)
  {
    if (mCallbacksMessenger != paramMessenger)
    {
      if (mState != 0) {
        Log.i("MediaBrowserCompat", paramString + " for " + mServiceComponent + " with mCallbacksMessenger=" + mCallbacksMessenger + " this=" + this);
      }
      return false;
    }
    return true;
  }
  
  public void connect()
  {
    if (mState != 0) {
      throw new IllegalStateException("connect() called while not disconnected (state=" + getStateLabel(mState) + ")");
    }
    if ((MediaBrowserCompat.access$700()) && (mServiceConnection != null)) {
      throw new RuntimeException("mServiceConnection should be null. Instead it is " + mServiceConnection);
    }
    if (mServiceBinderWrapper != null) {
      throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + mServiceBinderWrapper);
    }
    if (mCallbacksMessenger != null) {
      throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + mCallbacksMessenger);
    }
    mState = 1;
    Intent localIntent = new Intent("android.media.browse.MediaBrowserService");
    localIntent.setComponent(mServiceComponent);
    final MediaServiceConnection localMediaServiceConnection = new MediaServiceConnection(null);
    mServiceConnection = localMediaServiceConnection;
    int i = 0;
    try
    {
      boolean bool = mContext.bindService(localIntent, mServiceConnection, 1);
      i = bool;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("MediaBrowserCompat", "Failed binding to service " + mServiceComponent);
      }
    }
    if (i == 0) {
      mHandler.post(new Runnable()
      {
        public void run()
        {
          if (localMediaServiceConnection == mServiceConnection)
          {
            MediaBrowserCompat.MediaBrowserImplBase.this.forceCloseConnection();
            mCallback.onConnectionFailed();
          }
        }
      });
    }
    if (MediaBrowserCompat.access$700())
    {
      Log.d("MediaBrowserCompat", "connect...");
      dump();
    }
  }
  
  public void disconnect()
  {
    if (mCallbacksMessenger != null) {}
    try
    {
      mServiceBinderWrapper.disconnect(mCallbacksMessenger);
      forceCloseConnection();
      if (MediaBrowserCompat.access$700())
      {
        Log.d("MediaBrowserCompat", "disconnect...");
        dump();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        Log.w("MediaBrowserCompat", "RemoteException during connect for " + mServiceComponent);
      }
    }
  }
  
  void dump()
  {
    Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
    Log.d("MediaBrowserCompat", "  mServiceComponent=" + mServiceComponent);
    Log.d("MediaBrowserCompat", "  mCallback=" + mCallback);
    Log.d("MediaBrowserCompat", "  mRootHints=" + mRootHints);
    Log.d("MediaBrowserCompat", "  mState=" + getStateLabel(mState));
    Log.d("MediaBrowserCompat", "  mServiceConnection=" + mServiceConnection);
    Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + mServiceBinderWrapper);
    Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + mCallbacksMessenger);
    Log.d("MediaBrowserCompat", "  mRootId=" + mRootId);
    Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + mMediaSessionToken);
  }
  
  @Nullable
  public Bundle getExtras()
  {
    if (!isConnected()) {
      throw new IllegalStateException("getExtras() called while not connected (state=" + getStateLabel(mState) + ")");
    }
    return mExtras;
  }
  
  public void getItem(@NonNull final String paramString, @NonNull final MediaBrowserCompat.ItemCallback paramItemCallback)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("mediaId is empty");
    }
    if (paramItemCallback == null) {
      throw new IllegalArgumentException("cb is null");
    }
    if (mState != 2)
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
    MediaBrowserCompat.ItemReceiver localItemReceiver = new MediaBrowserCompat.ItemReceiver(paramString, paramItemCallback, mHandler);
    try
    {
      mServiceBinderWrapper.getMediaItem(paramString, localItemReceiver, mCallbacksMessenger);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.i("MediaBrowserCompat", "Remote error getting media item.");
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
    if (!isConnected()) {
      throw new IllegalStateException("getRoot() called while not connected(state=" + getStateLabel(mState) + ")");
    }
    return mRootId;
  }
  
  @NonNull
  public ComponentName getServiceComponent()
  {
    if (!isConnected()) {
      throw new IllegalStateException("getServiceComponent() called while not connected (state=" + mState + ")");
    }
    return mServiceComponent;
  }
  
  @NonNull
  public MediaSessionCompat.Token getSessionToken()
  {
    if (!isConnected()) {
      throw new IllegalStateException("getSessionToken() called while not connected(state=" + mState + ")");
    }
    return mMediaSessionToken;
  }
  
  public boolean isConnected()
  {
    return mState == 2;
  }
  
  public void onConnectionFailed(Messenger paramMessenger)
  {
    Log.e("MediaBrowserCompat", "onConnectFailed for " + mServiceComponent);
    if (!isCurrent(paramMessenger, "onConnectFailed")) {
      return;
    }
    if (mState != 1)
    {
      Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(mState) + "... ignoring");
      return;
    }
    forceCloseConnection();
    mCallback.onConnectionFailed();
  }
  
  public void onLoadChildren(Messenger paramMessenger, String paramString, List paramList, Bundle paramBundle)
  {
    if (!isCurrent(paramMessenger, "onLoadChildren")) {}
    do
    {
      do
      {
        return;
        if (MediaBrowserCompat.access$700()) {
          Log.d("MediaBrowserCompat", "onLoadChildren for " + mServiceComponent + " id=" + paramString);
        }
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
  
  public void onServiceConnected(Messenger paramMessenger, String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
  {
    if (!isCurrent(paramMessenger, "onConnect")) {}
    for (;;)
    {
      return;
      if (mState != 1)
      {
        Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(mState) + "... ignoring");
        return;
      }
      mRootId = paramString;
      mMediaSessionToken = paramToken;
      mExtras = paramBundle;
      mState = 2;
      if (MediaBrowserCompat.access$700())
      {
        Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
        dump();
      }
      mCallback.onConnected();
      try
      {
        paramMessenger = mSubscriptions.entrySet().iterator();
        while (paramMessenger.hasNext())
        {
          paramToken = (Map.Entry)paramMessenger.next();
          paramString = (String)paramToken.getKey();
          paramBundle = (MediaBrowserCompat.Subscription)paramToken.getValue();
          paramToken = paramBundle.getCallbacks();
          paramBundle = paramBundle.getOptionsList();
          int i = 0;
          while (i < paramToken.size())
          {
            mServiceBinderWrapper.addSubscription(paramString, MediaBrowserCompat.SubscriptionCallback.access$1200((MediaBrowserCompat.SubscriptionCallback)paramToken.get(i)), (Bundle)paramBundle.get(i), mCallbacksMessenger);
            i += 1;
          }
        }
        return;
      }
      catch (RemoteException paramMessenger)
      {
        Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
      }
    }
  }
  
  public void subscribe(@NonNull String paramString, Bundle paramBundle, @NonNull MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
  {
    MediaBrowserCompat.Subscription localSubscription2 = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
    MediaBrowserCompat.Subscription localSubscription1 = localSubscription2;
    if (localSubscription2 == null)
    {
      localSubscription1 = new MediaBrowserCompat.Subscription();
      mSubscriptions.put(paramString, localSubscription1);
    }
    localSubscription1.putCallback(paramBundle, paramSubscriptionCallback);
    if (mState == 2) {}
    try
    {
      mServiceBinderWrapper.addSubscription(paramString, MediaBrowserCompat.SubscriptionCallback.access$1200(paramSubscriptionCallback), paramBundle, mCallbacksMessenger);
      return;
    }
    catch (RemoteException paramBundle)
    {
      Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + paramString);
    }
  }
  
  public void unsubscribe(@NonNull String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
  {
    MediaBrowserCompat.Subscription localSubscription = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
    if (localSubscription == null) {}
    for (;;)
    {
      return;
      if (paramSubscriptionCallback == null) {}
      try
      {
        if (mState == 2) {
          mServiceBinderWrapper.removeSubscription(paramString, null, mCallbacksMessenger);
        }
        while ((localSubscription.isEmpty()) || (paramSubscriptionCallback == null))
        {
          mSubscriptions.remove(paramString);
          return;
          List localList1 = localSubscription.getCallbacks();
          List localList2 = localSubscription.getOptionsList();
          int i = localList1.size() - 1;
          while (i >= 0)
          {
            if (localList1.get(i) == paramSubscriptionCallback)
            {
              if (mState == 2) {
                mServiceBinderWrapper.removeSubscription(paramString, MediaBrowserCompat.SubscriptionCallback.access$1200(paramSubscriptionCallback), mCallbacksMessenger);
              }
              localList1.remove(i);
              localList2.remove(i);
            }
            i -= 1;
          }
        }
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + paramString);
        }
      }
    }
  }
  
  private class MediaServiceConnection
    implements ServiceConnection
  {
    private MediaServiceConnection() {}
    
    private boolean isCurrent(String paramString)
    {
      if (mServiceConnection != this)
      {
        if (mState != 0) {
          Log.i("MediaBrowserCompat", paramString + " for " + mServiceComponent + " with mServiceConnection=" + mServiceConnection + " this=" + this);
        }
        return false;
      }
      return true;
    }
    
    private void postOrRun(Runnable paramRunnable)
    {
      if (Thread.currentThread() == mHandler.getLooper().getThread())
      {
        paramRunnable.run();
        return;
      }
      mHandler.post(paramRunnable);
    }
    
    public void onServiceConnected(final ComponentName paramComponentName, final IBinder paramIBinder)
    {
      postOrRun(new Runnable()
      {
        public void run()
        {
          if (MediaBrowserCompat.access$700())
          {
            Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + paramComponentName + " binder=" + paramIBinder);
            dump();
          }
          if (!MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this.isCurrent("onServiceConnected")) {}
          do
          {
            return;
            MediaBrowserCompat.MediaBrowserImplBase.access$1402(MediaBrowserCompat.MediaBrowserImplBase.this, new MediaBrowserCompat.ServiceBinderWrapper(paramIBinder, mRootHints));
            MediaBrowserCompat.MediaBrowserImplBase.access$1602(MediaBrowserCompat.MediaBrowserImplBase.this, new Messenger(mHandler));
            mHandler.setCallbacksMessenger(mCallbacksMessenger);
            MediaBrowserCompat.MediaBrowserImplBase.access$1802(MediaBrowserCompat.MediaBrowserImplBase.this, 1);
            try
            {
              if (MediaBrowserCompat.access$700())
              {
                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                dump();
              }
              mServiceBinderWrapper.connect(mContext, mCallbacksMessenger);
              return;
            }
            catch (RemoteException localRemoteException)
            {
              Log.w("MediaBrowserCompat", "RemoteException during connect for " + mServiceComponent);
            }
          } while (!MediaBrowserCompat.access$700());
          Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
          dump();
        }
      });
    }
    
    public void onServiceDisconnected(final ComponentName paramComponentName)
    {
      postOrRun(new Runnable()
      {
        public void run()
        {
          if (MediaBrowserCompat.access$700())
          {
            Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceDisconnected name=" + paramComponentName + " this=" + this + " mServiceConnection=" + mServiceConnection);
            dump();
          }
          if (!MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this.isCurrent("onServiceDisconnected")) {
            return;
          }
          MediaBrowserCompat.MediaBrowserImplBase.access$1402(MediaBrowserCompat.MediaBrowserImplBase.this, null);
          MediaBrowserCompat.MediaBrowserImplBase.access$1602(MediaBrowserCompat.MediaBrowserImplBase.this, null);
          mHandler.setCallbacksMessenger(null);
          MediaBrowserCompat.MediaBrowserImplBase.access$1802(MediaBrowserCompat.MediaBrowserImplBase.this, 3);
          mCallback.onConnectionSuspended();
        }
      });
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */