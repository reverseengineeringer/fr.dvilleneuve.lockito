package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import java.util.List;

abstract interface MediaBrowserCompatApi24$SubscriptionCallback
  extends MediaBrowserCompatApi21.SubscriptionCallback
{
  public abstract void onChildrenLoaded(@NonNull String paramString, List<Parcel> paramList, @NonNull Bundle paramBundle);
  
  public abstract void onError(@NonNull String paramString, @NonNull Bundle paramBundle);
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserCompatApi24.SubscriptionCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */