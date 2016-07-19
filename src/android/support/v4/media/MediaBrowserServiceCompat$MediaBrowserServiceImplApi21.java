package android.support.v4.media;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.Parcel;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class MediaBrowserServiceCompat$MediaBrowserServiceImplApi21
  implements MediaBrowserServiceCompat.MediaBrowserServiceImpl, MediaBrowserServiceCompatApi21.ServiceCompatProxy
{
  Messenger mMessenger;
  Object mServiceObj;
  
  MediaBrowserServiceCompat$MediaBrowserServiceImplApi21(MediaBrowserServiceCompat paramMediaBrowserServiceCompat) {}
  
  public Bundle getBrowserRootHints()
  {
    if (mMessenger == null) {}
    do
    {
      return null;
      if (MediaBrowserServiceCompat.access$400(this$0) == null) {
        throw new IllegalStateException("This should be called inside of onLoadChildren or onLoadItem methods");
      }
    } while (access$400this$0).rootHints == null);
    return new Bundle(access$400this$0).rootHints);
  }
  
  public void notifyChildrenChanged(final String paramString, final Bundle paramBundle)
  {
    if (mMessenger == null)
    {
      MediaBrowserServiceCompatApi21.notifyChildrenChanged(mServiceObj, paramString);
      return;
    }
    MediaBrowserServiceCompat.access$100(this$0).post(new Runnable()
    {
      public void run()
      {
        Iterator localIterator = MediaBrowserServiceCompat.access$200(this$0).keySet().iterator();
        while (localIterator.hasNext())
        {
          Object localObject1 = (IBinder)localIterator.next();
          localObject1 = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.access$200(this$0).get(localObject1);
          Object localObject2 = (List)subscriptions.get(paramString);
          if (localObject2 != null)
          {
            localObject2 = ((List)localObject2).iterator();
            while (((Iterator)localObject2).hasNext())
            {
              Pair localPair = (Pair)((Iterator)localObject2).next();
              if (MediaBrowserCompatUtils.hasDuplicatedItems(paramBundle, (Bundle)second)) {
                MediaBrowserServiceCompat.access$300(this$0, paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject1, (Bundle)second);
              }
            }
          }
        }
      }
    });
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return MediaBrowserServiceCompatApi21.onBind(mServiceObj, paramIntent);
  }
  
  public void onCreate()
  {
    mServiceObj = MediaBrowserServiceCompatApi21.createService(this$0, this);
    MediaBrowserServiceCompatApi21.onCreate(mServiceObj);
  }
  
  public MediaBrowserServiceCompatApi21.BrowserRoot onGetRoot(String paramString, int paramInt, Bundle paramBundle)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramBundle != null)
    {
      localObject1 = localObject2;
      if (paramBundle.getInt("extra_client_version", 0) != 0)
      {
        paramBundle.remove("extra_client_version");
        mMessenger = new Messenger(MediaBrowserServiceCompat.access$100(this$0));
        localObject1 = new Bundle();
        ((Bundle)localObject1).putInt("extra_service_version", 1);
        BundleCompat.putBinder((Bundle)localObject1, "extra_messenger", mMessenger.getBinder());
      }
    }
    paramBundle = this$0.onGetRoot(paramString, paramInt, paramBundle);
    if (paramBundle == null) {
      return null;
    }
    if (localObject1 == null) {
      paramString = paramBundle.getExtras();
    }
    for (;;)
    {
      return new MediaBrowserServiceCompatApi21.BrowserRoot(paramBundle.getRootId(), paramString);
      paramString = (String)localObject1;
      if (paramBundle.getExtras() != null)
      {
        ((Bundle)localObject1).putAll(paramBundle.getExtras());
        paramString = (String)localObject1;
      }
    }
  }
  
  public void onLoadChildren(String paramString, final MediaBrowserServiceCompatApi21.ResultWrapper<List<Parcel>> paramResultWrapper)
  {
    paramResultWrapper = new MediaBrowserServiceCompat.Result(paramString)
    {
      public void detach()
      {
        paramResultWrapper.detach();
      }
      
      void onResultSent(List<MediaBrowserCompat.MediaItem> paramAnonymousList, int paramAnonymousInt)
      {
        Object localObject = null;
        if (paramAnonymousList != null)
        {
          ArrayList localArrayList = new ArrayList();
          paramAnonymousList = paramAnonymousList.iterator();
          for (;;)
          {
            localObject = localArrayList;
            if (!paramAnonymousList.hasNext()) {
              break;
            }
            localObject = (MediaBrowserCompat.MediaItem)paramAnonymousList.next();
            Parcel localParcel = Parcel.obtain();
            ((MediaBrowserCompat.MediaItem)localObject).writeToParcel(localParcel, 0);
            localArrayList.add(localParcel);
          }
        }
        paramResultWrapper.sendResult(localObject);
      }
    };
    this$0.onLoadChildren(paramString, paramResultWrapper);
  }
  
  public void setSessionToken(MediaSessionCompat.Token paramToken)
  {
    MediaBrowserServiceCompatApi21.setSessionToken(mServiceObj, paramToken.getToken());
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */