package android.support.v4.media;

import android.content.ComponentName;
import android.util.Log;

class MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$2
  implements Runnable
{
  MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$2(MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection paramMediaServiceConnection, ComponentName paramComponentName) {}
  
  public void run()
  {
    if (MediaBrowserCompat.access$700())
    {
      Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceDisconnected name=" + val$name + " this=" + this + " mServiceConnection=" + MediaBrowserCompat.MediaBrowserImplBase.access$900(this$1.this$0));
      this$1.this$0.dump();
    }
    if (!MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.access$1300(this$1, "onServiceDisconnected")) {
      return;
    }
    MediaBrowserCompat.MediaBrowserImplBase.access$1402(this$1.this$0, null);
    MediaBrowserCompat.MediaBrowserImplBase.access$1602(this$1.this$0, null);
    MediaBrowserCompat.MediaBrowserImplBase.access$1700(this$1.this$0).setCallbacksMessenger(null);
    MediaBrowserCompat.MediaBrowserImplBase.access$1802(this$1.this$0, 3);
    MediaBrowserCompat.MediaBrowserImplBase.access$1100(this$1.this$0).onConnectionSuspended();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */