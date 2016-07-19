package android.support.v4.media;

import android.media.browse.MediaBrowser.MediaItem;
import android.os.Bundle;
import android.support.annotation.NonNull;
import java.util.List;

class MediaBrowserCompatApi24$SubscriptionCallbackProxy<T extends MediaBrowserCompatApi24.SubscriptionCallback>
  extends MediaBrowserCompatApi21.SubscriptionCallbackProxy<T>
{
  public MediaBrowserCompatApi24$SubscriptionCallbackProxy(T paramT)
  {
    super(paramT);
  }
  
  public void onChildrenLoaded(@NonNull String paramString, List<MediaBrowser.MediaItem> paramList, @NonNull Bundle paramBundle)
  {
    ((MediaBrowserCompatApi24.SubscriptionCallback)mSubscriptionCallback).onChildrenLoaded(paramString, itemListToParcelList(paramList), paramBundle);
  }
  
  public void onError(@NonNull String paramString, @NonNull Bundle paramBundle)
  {
    ((MediaBrowserCompatApi24.SubscriptionCallback)mSubscriptionCallback).onError(paramString, paramBundle);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserCompatApi24.SubscriptionCallbackProxy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */