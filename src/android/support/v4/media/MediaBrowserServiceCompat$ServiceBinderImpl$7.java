package android.support.v4.media;

import android.os.IBinder;
import android.support.v4.util.ArrayMap;

class MediaBrowserServiceCompat$ServiceBinderImpl$7
  implements Runnable
{
  MediaBrowserServiceCompat$ServiceBinderImpl$7(MediaBrowserServiceCompat.ServiceBinderImpl paramServiceBinderImpl, MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks) {}
  
  public void run()
  {
    IBinder localIBinder = val$callbacks.asBinder();
    MediaBrowserServiceCompat.access$200(this$1.this$0).remove(localIBinder);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.ServiceBinderImpl.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */