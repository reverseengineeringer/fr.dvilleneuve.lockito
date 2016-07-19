package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import java.util.List;

class MediaBrowserCompat$SubscriptionCallback$StubApi24
  extends MediaBrowserCompat.SubscriptionCallback.StubApi21
  implements MediaBrowserCompatApi24.SubscriptionCallback
{
  private MediaBrowserCompat$SubscriptionCallback$StubApi24(MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
  {
    super(paramSubscriptionCallback, null);
  }
  
  public void onChildrenLoaded(@NonNull String paramString, List<Parcel> paramList, @NonNull Bundle paramBundle)
  {
    this$0.onChildrenLoaded(paramString, parcelListToItemList(paramList), paramBundle);
  }
  
  public void onError(@NonNull String paramString, @NonNull Bundle paramBundle)
  {
    this$0.onError(paramString, paramBundle);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserCompat.SubscriptionCallback.StubApi24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */