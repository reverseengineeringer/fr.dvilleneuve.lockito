package android.support.v4.media;

import android.os.Parcel;

class MediaBrowserServiceCompat$MediaBrowserServiceImplApi23
  extends MediaBrowserServiceCompat.MediaBrowserServiceImplApi21
  implements MediaBrowserServiceCompatApi23.ServiceCompatProxy
{
  MediaBrowserServiceCompat$MediaBrowserServiceImplApi23(MediaBrowserServiceCompat paramMediaBrowserServiceCompat)
  {
    super(paramMediaBrowserServiceCompat);
  }
  
  public void onCreate()
  {
    mServiceObj = MediaBrowserServiceCompatApi23.createService(this$0, this);
    MediaBrowserServiceCompatApi21.onCreate(mServiceObj);
  }
  
  public void onLoadItem(String paramString, final MediaBrowserServiceCompatApi21.ResultWrapper<Parcel> paramResultWrapper)
  {
    paramResultWrapper = new MediaBrowserServiceCompat.Result(paramString)
    {
      public void detach()
      {
        paramResultWrapper.detach();
      }
      
      void onResultSent(MediaBrowserCompat.MediaItem paramAnonymousMediaItem, int paramAnonymousInt)
      {
        Parcel localParcel = Parcel.obtain();
        paramAnonymousMediaItem.writeToParcel(localParcel, 0);
        paramResultWrapper.sendResult(localParcel);
      }
    };
    this$0.onLoadItem(paramString, paramResultWrapper);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi23
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */