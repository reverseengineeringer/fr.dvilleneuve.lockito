package android.support.v4.media;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class MediaBrowserServiceCompat$MediaBrowserServiceImplBase
  implements MediaBrowserServiceCompat.MediaBrowserServiceImpl
{
  private Messenger mMessenger;
  
  MediaBrowserServiceCompat$MediaBrowserServiceImplBase(MediaBrowserServiceCompat paramMediaBrowserServiceCompat) {}
  
  public Bundle getBrowserRootHints()
  {
    if (MediaBrowserServiceCompat.access$400(this$0) == null) {
      throw new IllegalStateException("This should be called inside of onLoadChildren or onLoadItem methods");
    }
    if (access$400this$0).rootHints == null) {
      return null;
    }
    return new Bundle(access$400this$0).rootHints);
  }
  
  public void notifyChildrenChanged(@NonNull final String paramString, final Bundle paramBundle)
  {
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
    if ("android.media.browse.MediaBrowserService".equals(paramIntent.getAction())) {
      return mMessenger.getBinder();
    }
    return null;
  }
  
  public void onCreate()
  {
    mMessenger = new Messenger(MediaBrowserServiceCompat.access$100(this$0));
  }
  
  public void setSessionToken(final MediaSessionCompat.Token paramToken)
  {
    MediaBrowserServiceCompat.access$100(this$0).post(new Runnable()
    {
      public void run()
      {
        Iterator localIterator = MediaBrowserServiceCompat.access$200(this$0).keySet().iterator();
        while (localIterator.hasNext())
        {
          IBinder localIBinder = (IBinder)localIterator.next();
          MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.access$200(this$0).get(localIBinder);
          try
          {
            callbacks.onConnect(root.getRootId(), paramToken, root.getExtras());
          }
          catch (RemoteException localRemoteException)
          {
            Log.w("MBServiceCompat", "Connection for " + pkg + " is no longer valid.");
            MediaBrowserServiceCompat.access$200(this$0).remove(localIBinder);
          }
        }
      }
    });
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.MediaBrowserServiceImplBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */