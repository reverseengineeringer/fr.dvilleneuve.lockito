package android.support.v4.media;

import android.media.browse.MediaBrowser.MediaItem;
import android.media.browse.MediaBrowser.SubscriptionCallback;
import android.os.Parcel;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MediaBrowserCompatApi21$SubscriptionCallbackProxy<T extends MediaBrowserCompatApi21.SubscriptionCallback>
  extends MediaBrowser.SubscriptionCallback
{
  protected final T mSubscriptionCallback;
  
  public MediaBrowserCompatApi21$SubscriptionCallbackProxy(T paramT)
  {
    mSubscriptionCallback = paramT;
  }
  
  static List<Parcel> itemListToParcelList(List<MediaBrowser.MediaItem> paramList)
  {
    if ((paramList == null) || ((paramList.size() == 1) && (((MediaBrowser.MediaItem)paramList.get(0)).getMediaId().equals("android.support.v4.media.MediaBrowserCompat.NULL_MEDIA_ITEM"))))
    {
      paramList = null;
      return paramList;
    }
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    for (;;)
    {
      paramList = localArrayList;
      if (!localIterator.hasNext()) {
        break;
      }
      paramList = (MediaBrowser.MediaItem)localIterator.next();
      Parcel localParcel = Parcel.obtain();
      paramList.writeToParcel(localParcel, 0);
      localArrayList.add(localParcel);
    }
  }
  
  public void onChildrenLoaded(@NonNull String paramString, List<MediaBrowser.MediaItem> paramList)
  {
    mSubscriptionCallback.onChildrenLoaded(paramString, itemListToParcelList(paramList));
  }
  
  public void onError(@NonNull String paramString)
  {
    mSubscriptionCallback.onError(paramString);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserCompatApi21.SubscriptionCallbackProxy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */