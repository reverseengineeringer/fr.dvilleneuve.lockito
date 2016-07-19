package android.support.v4.media;

import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.BundleCompat;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

final class MediaBrowserServiceCompat$ServiceHandler
  extends Handler
{
  private final MediaBrowserServiceCompat.ServiceBinderImpl mServiceBinderImpl = new MediaBrowserServiceCompat.ServiceBinderImpl(this$0, null);
  
  private MediaBrowserServiceCompat$ServiceHandler(MediaBrowserServiceCompat paramMediaBrowserServiceCompat) {}
  
  public void handleMessage(Message paramMessage)
  {
    Bundle localBundle = paramMessage.getData();
    switch (what)
    {
    default: 
      Log.w("MBServiceCompat", "Unhandled message: " + paramMessage + "\n  Service version: " + 1 + "\n  Client version: " + arg1);
      return;
    case 1: 
      mServiceBinderImpl.connect(localBundle.getString("data_package_name"), localBundle.getInt("data_calling_uid"), localBundle.getBundle("data_root_hints"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(this$0, replyTo));
      return;
    case 2: 
      mServiceBinderImpl.disconnect(new MediaBrowserServiceCompat.ServiceCallbacksCompat(this$0, replyTo));
      return;
    case 3: 
      mServiceBinderImpl.addSubscription(localBundle.getString("data_media_item_id"), BundleCompat.getBinder(localBundle, "data_callback_token"), localBundle.getBundle("data_options"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(this$0, replyTo));
      return;
    case 4: 
      mServiceBinderImpl.removeSubscription(localBundle.getString("data_media_item_id"), BundleCompat.getBinder(localBundle, "data_callback_token"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(this$0, replyTo));
      return;
    case 5: 
      mServiceBinderImpl.getMediaItem(localBundle.getString("data_media_item_id"), (ResultReceiver)localBundle.getParcelable("data_result_receiver"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(this$0, replyTo));
      return;
    case 6: 
      mServiceBinderImpl.registerCallbacks(new MediaBrowserServiceCompat.ServiceCallbacksCompat(this$0, replyTo), localBundle.getBundle("data_root_hints"));
      return;
    }
    mServiceBinderImpl.unregisterCallbacks(new MediaBrowserServiceCompat.ServiceCallbacksCompat(this$0, replyTo));
  }
  
  public void postOrRun(Runnable paramRunnable)
  {
    if (Thread.currentThread() == getLooper().getThread())
    {
      paramRunnable.run();
      return;
    }
    post(paramRunnable);
  }
  
  public boolean sendMessageAtTime(Message paramMessage, long paramLong)
  {
    Bundle localBundle = paramMessage.getData();
    localBundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
    localBundle.putInt("data_calling_uid", Binder.getCallingUid());
    return super.sendMessageAtTime(paramMessage, paramLong);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.ServiceHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */