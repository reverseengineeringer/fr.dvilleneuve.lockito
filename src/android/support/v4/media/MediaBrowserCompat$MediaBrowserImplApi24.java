package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

class MediaBrowserCompat$MediaBrowserImplApi24
  extends MediaBrowserCompat.MediaBrowserImplApi23
{
  public MediaBrowserCompat$MediaBrowserImplApi24(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
  {
    super(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
  }
  
  public void getItem(@NonNull String paramString, @NonNull MediaBrowserCompat.ItemCallback paramItemCallback)
  {
    MediaBrowserCompatApi23.getItem(mBrowserObj, paramString, mItemCallbackObj);
  }
  
  public void subscribe(@NonNull String paramString, @NonNull Bundle paramBundle, @NonNull MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
  {
    if (paramBundle == null)
    {
      MediaBrowserCompatApi21.subscribe(mBrowserObj, paramString, MediaBrowserCompat.SubscriptionCallback.access$2200(paramSubscriptionCallback));
      return;
    }
    MediaBrowserCompatApi24.subscribe(mBrowserObj, paramString, paramBundle, MediaBrowserCompat.SubscriptionCallback.access$2200(paramSubscriptionCallback));
  }
  
  public void unsubscribe(@NonNull String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
  {
    if (paramSubscriptionCallback == null)
    {
      MediaBrowserCompatApi21.unsubscribe(mBrowserObj, paramString);
      return;
    }
    MediaBrowserCompatApi24.unsubscribe(mBrowserObj, paramString, MediaBrowserCompat.SubscriptionCallback.access$2200(paramSubscriptionCallback));
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */