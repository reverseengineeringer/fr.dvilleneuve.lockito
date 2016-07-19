package android.support.v4.media;

import android.os.IBinder;
import android.support.v4.util.ArrayMap;
import android.util.Log;

class MediaBrowserServiceCompat$ServiceBinderImpl$4
  implements Runnable
{
  MediaBrowserServiceCompat$ServiceBinderImpl$4(MediaBrowserServiceCompat.ServiceBinderImpl paramServiceBinderImpl, MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks, String paramString, IBinder paramIBinder) {}
  
  public void run()
  {
    Object localObject = val$callbacks.asBinder();
    localObject = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.access$200(this$1.this$0).get(localObject);
    if (localObject == null) {
      Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + val$id);
    }
    while (MediaBrowserServiceCompat.access$900(this$1.this$0, val$id, (MediaBrowserServiceCompat.ConnectionRecord)localObject, val$token)) {
      return;
    }
    Log.w("MBServiceCompat", "removeSubscription called for " + val$id + " which is not subscribed");
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.ServiceBinderImpl.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */