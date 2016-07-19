package android.support.v4.media;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MediaBrowserServiceCompat$MediaBrowserServiceImplApi24$1
  extends MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>>
{
  MediaBrowserServiceCompat$MediaBrowserServiceImplApi24$1(MediaBrowserServiceCompat.MediaBrowserServiceImplApi24 paramMediaBrowserServiceImplApi24, Object paramObject, MediaBrowserServiceCompatApi24.ResultWrapper paramResultWrapper)
  {
    super(paramObject);
  }
  
  public void detach()
  {
    val$resultWrapper.detach();
  }
  
  void onResultSent(List<MediaBrowserCompat.MediaItem> paramList, int paramInt)
  {
    Object localObject = null;
    if (paramList != null)
    {
      ArrayList localArrayList = new ArrayList();
      paramList = paramList.iterator();
      for (;;)
      {
        localObject = localArrayList;
        if (!paramList.hasNext()) {
          break;
        }
        localObject = (MediaBrowserCompat.MediaItem)paramList.next();
        Parcel localParcel = Parcel.obtain();
        ((MediaBrowserCompat.MediaItem)localObject).writeToParcel(localParcel, 0);
        localArrayList.add(localParcel);
      }
    }
    val$resultWrapper.sendResult((List)localObject, paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi24.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */