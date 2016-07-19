package android.support.v4.media;

import android.media.browse.MediaBrowser.MediaItem;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.service.media.MediaBrowserService.Result;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MediaBrowserServiceCompatApi21$ResultWrapper<T>
{
  MediaBrowserService.Result mResultObj;
  
  MediaBrowserServiceCompatApi21$ResultWrapper(MediaBrowserService.Result paramResult)
  {
    mResultObj = paramResult;
  }
  
  public void detach()
  {
    mResultObj.detach();
  }
  
  List<MediaBrowser.MediaItem> parcelListToItemList(List<Parcel> paramList)
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
      localArrayList.add(MediaBrowser.MediaItem.CREATOR.createFromParcel(paramList));
      paramList.recycle();
    }
  }
  
  public void sendResult(T paramT)
  {
    if ((paramT instanceof List)) {
      mResultObj.sendResult(parcelListToItemList((List)paramT));
    }
    while (!(paramT instanceof Parcel)) {
      return;
    }
    mResultObj.sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel((Parcel)paramT));
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompatApi21.ResultWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */