package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat.Token;
import java.util.List;

abstract interface MediaBrowserServiceCompat$ServiceCallbacks
{
  public abstract IBinder asBinder();
  
  public abstract void onConnect(String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onConnectFailed()
    throws RemoteException;
  
  public abstract void onLoadChildren(String paramString, List<MediaBrowserCompat.MediaItem> paramList, Bundle paramBundle)
    throws RemoteException;
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.ServiceCallbacks
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */