package android.support.v4.media;

import android.os.Parcel;

class MediaBrowserServiceCompat$MediaBrowserServiceImplApi23$1
  extends MediaBrowserServiceCompat.Result<MediaBrowserCompat.MediaItem>
{
  MediaBrowserServiceCompat$MediaBrowserServiceImplApi23$1(MediaBrowserServiceCompat.MediaBrowserServiceImplApi23 paramMediaBrowserServiceImplApi23, Object paramObject, MediaBrowserServiceCompatApi21.ResultWrapper paramResultWrapper)
  {
    super(paramObject);
  }
  
  public void detach()
  {
    val$resultWrapper.detach();
  }
  
  void onResultSent(MediaBrowserCompat.MediaItem paramMediaItem, int paramInt)
  {
    Parcel localParcel = Parcel.obtain();
    paramMediaItem.writeToParcel(localParcel, 0);
    val$resultWrapper.sendResult(localParcel);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi23.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */