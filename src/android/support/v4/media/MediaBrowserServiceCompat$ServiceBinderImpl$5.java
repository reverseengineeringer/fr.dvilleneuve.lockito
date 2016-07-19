package android.support.v4.media;

import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.util.Log;

class MediaBrowserServiceCompat$ServiceBinderImpl$5
  implements Runnable
{
  MediaBrowserServiceCompat$ServiceBinderImpl$5(MediaBrowserServiceCompat.ServiceBinderImpl paramServiceBinderImpl, MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks, String paramString, ResultReceiver paramResultReceiver) {}
  
  public void run()
  {
    Object localObject = val$callbacks.asBinder();
    localObject = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.access$200(this$1.this$0).get(localObject);
    if (localObject == null)
    {
      Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + val$mediaId);
      return;
    }
    MediaBrowserServiceCompat.access$1000(this$1.this$0, val$mediaId, (MediaBrowserServiceCompat.ConnectionRecord)localObject, val$receiver);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.ServiceBinderImpl.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */