package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class MediaBrowserCompat$SubscriptionCallback$StubApi21
  implements MediaBrowserCompatApi21.SubscriptionCallback
{
  private MediaBrowserCompat$SubscriptionCallback$StubApi21(MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback) {}
  
  List<MediaBrowserCompat.MediaItem> applyOptions(List<MediaBrowserCompat.MediaItem> paramList, Bundle paramBundle)
  {
    if (paramList == null) {
      paramBundle = null;
    }
    int m;
    do
    {
      return paramBundle;
      i = paramBundle.getInt("android.media.browse.extra.PAGE", -1);
      m = paramBundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
      if (i != -1) {
        break;
      }
      paramBundle = paramList;
    } while (m == -1);
    int k = m * i;
    int j = k + m;
    if ((i < 0) || (m < 1) || (k >= paramList.size())) {
      return Collections.EMPTY_LIST;
    }
    int i = j;
    if (j > paramList.size()) {
      i = paramList.size();
    }
    return paramList.subList(k, i);
  }
  
  public void onChildrenLoaded(@NonNull String paramString, List<Parcel> paramList)
  {
    if (MediaBrowserCompat.SubscriptionCallback.access$500(this$0) == null) {}
    for (Object localObject = null; localObject == null; localObject = (MediaBrowserCompat.Subscription)MediaBrowserCompat.SubscriptionCallback.access$500(this$0).get())
    {
      this$0.onChildrenLoaded(paramString, parcelListToItemList(paramList));
      return;
    }
    paramList = parcelListToItemList(paramList);
    List localList = ((MediaBrowserCompat.Subscription)localObject).getCallbacks();
    localObject = ((MediaBrowserCompat.Subscription)localObject).getOptionsList();
    int i = 0;
    label72:
    Bundle localBundle;
    if (i < localList.size())
    {
      localBundle = (Bundle)((List)localObject).get(i);
      if (localBundle != null) {
        break label117;
      }
      this$0.onChildrenLoaded(paramString, paramList);
    }
    for (;;)
    {
      i += 1;
      break label72;
      break;
      label117:
      this$0.onChildrenLoaded(paramString, applyOptions(paramList, localBundle), localBundle);
    }
  }
  
  public void onError(@NonNull String paramString)
  {
    this$0.onError(paramString);
  }
  
  List<MediaBrowserCompat.MediaItem> parcelListToItemList(List<Parcel> paramList)
  {
    if (paramList == null)
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
      paramList = (Parcel)localIterator.next();
      paramList.setDataPosition(0);
      localArrayList.add(MediaBrowserCompat.MediaItem.CREATOR.createFromParcel(paramList));
      paramList.recycle();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserCompat.SubscriptionCallback.StubApi21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */