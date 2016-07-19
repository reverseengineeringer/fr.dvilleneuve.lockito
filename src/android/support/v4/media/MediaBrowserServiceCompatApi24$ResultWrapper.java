package android.support.v4.media;

import android.media.browse.MediaBrowser.MediaItem;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.service.media.MediaBrowserService.Result;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MediaBrowserServiceCompatApi24$ResultWrapper
{
  MediaBrowserService.Result mResultObj;
  
  MediaBrowserServiceCompatApi24$ResultWrapper(MediaBrowserService.Result paramResult)
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
  
  public void sendResult(List<Parcel> paramList, int paramInt)
  {
    try
    {
      MediaBrowserServiceCompatApi24.access$000().setInt(mResultObj, paramInt);
      mResultObj.sendResult(parcelListToItemList(paramList));
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        Log.w("MBSCompatApi24", localIllegalAccessException);
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompatApi24.ResultWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */