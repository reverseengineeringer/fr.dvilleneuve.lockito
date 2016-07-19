package android.support.v4.media;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.MediaSessionCompat.Token;

abstract interface MediaBrowserCompat$MediaBrowserImpl
{
  public abstract void connect();
  
  public abstract void disconnect();
  
  @Nullable
  public abstract Bundle getExtras();
  
  public abstract void getItem(@NonNull String paramString, @NonNull MediaBrowserCompat.ItemCallback paramItemCallback);
  
  @NonNull
  public abstract String getRoot();
  
  public abstract ComponentName getServiceComponent();
  
  @NonNull
  public abstract MediaSessionCompat.Token getSessionToken();
  
  public abstract boolean isConnected();
  
  public abstract void subscribe(@NonNull String paramString, Bundle paramBundle, @NonNull MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback);
  
  public abstract void unsubscribe(@NonNull String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback);
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */