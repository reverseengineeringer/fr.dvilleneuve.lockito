package android.support.v4.media;

import android.os.IBinder;
import android.support.v4.util.ArrayMap;

class MediaBrowserServiceCompat$ServiceBinderImpl$2
  implements Runnable
{
  MediaBrowserServiceCompat$ServiceBinderImpl$2(MediaBrowserServiceCompat.ServiceBinderImpl paramServiceBinderImpl, MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks) {}
  
  public void run()
  {
    IBinder localIBinder = val$callbacks.asBinder();
    if ((MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.access$200(this$1.this$0).remove(localIBinder) != null) {}
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.ServiceBinderImpl.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */