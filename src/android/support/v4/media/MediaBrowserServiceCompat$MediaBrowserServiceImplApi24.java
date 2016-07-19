package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MediaBrowserServiceCompat$MediaBrowserServiceImplApi24
  extends MediaBrowserServiceCompat.MediaBrowserServiceImplApi23
  implements MediaBrowserServiceCompatApi24.ServiceCompatProxy
{
  MediaBrowserServiceCompat$MediaBrowserServiceImplApi24(MediaBrowserServiceCompat paramMediaBrowserServiceCompat)
  {
    super(paramMediaBrowserServiceCompat);
  }
  
  public Bundle getBrowserRootHints()
  {
    return MediaBrowserServiceCompatApi24.getBrowserRootHints(mServiceObj);
  }
  
  public void notifyChildrenChanged(String paramString, Bundle paramBundle)
  {
    if (paramBundle == null)
    {
      MediaBrowserServiceCompatApi21.notifyChildrenChanged(mServiceObj, paramString);
      return;
    }
    MediaBrowserServiceCompatApi24.notifyChildrenChanged(mServiceObj, paramString, paramBundle);
  }
  
  public void onCreate()
  {
    mServiceObj = MediaBrowserServiceCompatApi24.createService(this$0, this);
    MediaBrowserServiceCompatApi21.onCreate(mServiceObj);
  }
  
  public void onLoadChildren(String paramString, final MediaBrowserServiceCompatApi24.ResultWrapper paramResultWrapper, Bundle paramBundle)
  {
    paramResultWrapper = new MediaBrowserServiceCompat.Result(paramString)
    {
      public void detach()
      {
        paramResultWrapper.detach();
      }
      
      void onResultSent(List<MediaBrowserCompat.MediaItem> paramAnonymousList, int paramAnonymousInt)
      {
        Object localObject = null;
        if (paramAnonymousList != null)
        {
          ArrayList localArrayList = new ArrayList();
          paramAnonymousList = paramAnonymousList.iterator();
          for (;;)
          {
            localObject = localArrayList;
            if (!paramAnonymousList.hasNext()) {
              break;
            }
            localObject = (MediaBrowserCompat.MediaItem)paramAnonymousList.next();
            Parcel localParcel = Parcel.obtain();
            ((MediaBrowserCompat.MediaItem)localObject).writeToParcel(localParcel, 0);
            localArrayList.add(localParcel);
          }
        }
        paramResultWrapper.sendResult((List)localObject, paramAnonymousInt);
      }
    };
    this$0.onLoadChildren(paramString, paramResultWrapper, paramBundle);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */