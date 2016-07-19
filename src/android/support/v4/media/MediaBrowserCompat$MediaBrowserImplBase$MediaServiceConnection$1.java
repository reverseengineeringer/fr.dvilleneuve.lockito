package android.support.v4.media;

import android.content.ComponentName;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

class MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1
  implements Runnable
{
  MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$1(MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection paramMediaServiceConnection, ComponentName paramComponentName, IBinder paramIBinder) {}
  
  public void run()
  {
    if (MediaBrowserCompat.access$700())
    {
      Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + val$name + " binder=" + val$binder);
      this$1.this$0.dump();
    }
    if (!MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.access$1300(this$1, "onServiceConnected")) {}
    do
    {
      return;
      MediaBrowserCompat.MediaBrowserImplBase.access$1402(this$1.this$0, new MediaBrowserCompat.ServiceBinderWrapper(val$binder, MediaBrowserCompat.MediaBrowserImplBase.access$1500(this$1.this$0)));
      MediaBrowserCompat.MediaBrowserImplBase.access$1602(this$1.this$0, new Messenger(MediaBrowserCompat.MediaBrowserImplBase.access$1700(this$1.this$0)));
      MediaBrowserCompat.MediaBrowserImplBase.access$1700(this$1.this$0).setCallbacksMessenger(MediaBrowserCompat.MediaBrowserImplBase.access$1600(this$1.this$0));
      MediaBrowserCompat.MediaBrowserImplBase.access$1802(this$1.this$0, 1);
      try
      {
        if (MediaBrowserCompat.access$700())
        {
          Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
          this$1.this$0.dump();
        }
        MediaBrowserCompat.MediaBrowserImplBase.access$1400(this$1.this$0).connect(MediaBrowserCompat.MediaBrowserImplBase.access$1900(this$1.this$0), MediaBrowserCompat.MediaBrowserImplBase.access$1600(this$1.this$0));
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.w("MediaBrowserCompat", "RemoteException during connect for " + MediaBrowserCompat.MediaBrowserImplBase.access$2000(this$1.this$0));
      }
    } while (!MediaBrowserCompat.access$700());
    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
    this$1.this$0.dump();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */