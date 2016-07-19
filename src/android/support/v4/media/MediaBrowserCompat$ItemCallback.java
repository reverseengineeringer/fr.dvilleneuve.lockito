package android.support.v4.media;

import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;

public abstract class MediaBrowserCompat$ItemCallback
{
  final Object mItemCallbackObj;
  
  public MediaBrowserCompat$ItemCallback()
  {
    if (Build.VERSION.SDK_INT >= 23)
    {
      mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback(new StubApi23(null));
      return;
    }
    mItemCallbackObj = null;
  }
  
  public void onError(@NonNull String paramString) {}
  
  public void onItemLoaded(MediaBrowserCompat.MediaItem paramMediaItem) {}
  
  private class StubApi23
    implements MediaBrowserCompatApi23.ItemCallback
  {
    private StubApi23() {}
    
    public void onError(@NonNull String paramString)
    {
      MediaBrowserCompat.ItemCallback.this.onError(paramString);
    }
    
    public void onItemLoaded(Parcel paramParcel)
    {
      paramParcel.setDataPosition(0);
      MediaBrowserCompat.MediaItem localMediaItem = (MediaBrowserCompat.MediaItem)MediaBrowserCompat.MediaItem.CREATOR.createFromParcel(paramParcel);
      paramParcel.recycle();
      onItemLoaded(localMediaItem);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserCompat.ItemCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */