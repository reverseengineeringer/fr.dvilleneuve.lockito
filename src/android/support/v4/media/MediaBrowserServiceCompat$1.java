package android.support.v4.media;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import java.util.List;

class MediaBrowserServiceCompat$1
  extends MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>>
{
  MediaBrowserServiceCompat$1(MediaBrowserServiceCompat paramMediaBrowserServiceCompat, Object paramObject, MediaBrowserServiceCompat.ConnectionRecord paramConnectionRecord, String paramString, Bundle paramBundle)
  {
    super(paramObject);
  }
  
  void onResultSent(List<MediaBrowserCompat.MediaItem> paramList, int paramInt)
  {
    if (MediaBrowserServiceCompat.access$200(this$0).get(val$connection.callbacks.asBinder()) != val$connection)
    {
      if (MediaBrowserServiceCompat.access$1100()) {
        Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + val$connection.pkg + " id=" + val$parentId);
      }
      return;
    }
    if ((paramInt & 0x1) != 0) {
      paramList = MediaBrowserServiceCompat.access$1200(this$0, paramList, val$options);
    }
    for (;;)
    {
      try
      {
        val$connection.callbacks.onLoadChildren(val$parentId, paramList, val$options);
        return;
      }
      catch (RemoteException paramList)
      {
        Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + val$parentId + " package=" + val$connection.pkg);
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */