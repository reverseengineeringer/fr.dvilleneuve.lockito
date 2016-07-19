package android.support.v4.media;

import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.v4.os.BuildCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class MediaBrowserCompat$SubscriptionCallback
{
  private final Object mSubscriptionCallbackObj;
  private WeakReference<MediaBrowserCompat.Subscription> mSubscriptionRef;
  private final IBinder mToken;
  
  public MediaBrowserCompat$SubscriptionCallback()
  {
    if ((Build.VERSION.SDK_INT >= 24) || (BuildCompat.isAtLeastN()))
    {
      mSubscriptionCallbackObj = MediaBrowserCompatApi24.createSubscriptionCallback(new StubApi24(null));
      mToken = null;
      return;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21(null));
      mToken = new Binder();
      return;
    }
    mSubscriptionCallbackObj = null;
    mToken = new Binder();
  }
  
  private void setSubscription(MediaBrowserCompat.Subscription paramSubscription)
  {
    mSubscriptionRef = new WeakReference(paramSubscription);
  }
  
  public void onChildrenLoaded(@NonNull String paramString, List<MediaBrowserCompat.MediaItem> paramList) {}
  
  public void onChildrenLoaded(@NonNull String paramString, List<MediaBrowserCompat.MediaItem> paramList, @NonNull Bundle paramBundle) {}
  
  public void onError(@NonNull String paramString) {}
  
  public void onError(@NonNull String paramString, @NonNull Bundle paramBundle) {}
  
  private class StubApi21
    implements MediaBrowserCompatApi21.SubscriptionCallback
  {
    private StubApi21() {}
    
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
      if (mSubscriptionRef == null) {}
      for (Object localObject = null; localObject == null; localObject = (MediaBrowserCompat.Subscription)mSubscriptionRef.get())
      {
        MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(paramString, parcelListToItemList(paramList));
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
        MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(paramString, paramList);
      }
      for (;;)
      {
        i += 1;
        break label72;
        break;
        label117:
        onChildrenLoaded(paramString, applyOptions(paramList, localBundle), localBundle);
      }
    }
    
    public void onError(@NonNull String paramString)
    {
      MediaBrowserCompat.SubscriptionCallback.this.onError(paramString);
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
  
  private class StubApi24
    extends MediaBrowserCompat.SubscriptionCallback.StubApi21
    implements MediaBrowserCompatApi24.SubscriptionCallback
  {
    private StubApi24()
    {
      super(null);
    }
    
    public void onChildrenLoaded(@NonNull String paramString, List<Parcel> paramList, @NonNull Bundle paramBundle)
    {
      MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(paramString, parcelListToItemList(paramList), paramBundle);
    }
    
    public void onError(@NonNull String paramString, @NonNull Bundle paramBundle)
    {
      MediaBrowserCompat.SubscriptionCallback.this.onError(paramString, paramBundle);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserCompat.SubscriptionCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */