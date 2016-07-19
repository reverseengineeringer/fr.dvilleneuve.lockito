package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.util.ArrayMap;
import android.util.Log;

class MediaBrowserServiceCompat$ServiceBinderImpl$3
  implements Runnable
{
  MediaBrowserServiceCompat$ServiceBinderImpl$3(MediaBrowserServiceCompat.ServiceBinderImpl paramServiceBinderImpl, MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks, String paramString, IBinder paramIBinder, Bundle paramBundle) {}
  
  public void run()
  {
    Object localObject = val$callbacks.asBinder();
    localObject = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.access$200(this$1.this$0).get(localObject);
    if (localObject == null)
    {
      Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + val$id);
      return;
    }
    MediaBrowserServiceCompat.access$800(this$1.this$0, val$id, (MediaBrowserServiceCompat.ConnectionRecord)localObject, val$token, val$options);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.ServiceBinderImpl.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */