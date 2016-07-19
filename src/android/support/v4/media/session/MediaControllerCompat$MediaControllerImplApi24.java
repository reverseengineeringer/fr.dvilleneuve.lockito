package android.support.v4.media.session;

import android.content.Context;
import android.os.RemoteException;

class MediaControllerCompat$MediaControllerImplApi24
  extends MediaControllerCompat.MediaControllerImplApi23
{
  public MediaControllerCompat$MediaControllerImplApi24(Context paramContext, MediaSessionCompat.Token paramToken)
    throws RemoteException
  {
    super(paramContext, paramToken);
  }
  
  public MediaControllerCompat$MediaControllerImplApi24(Context paramContext, MediaSessionCompat paramMediaSessionCompat)
  {
    super(paramContext, paramMediaSessionCompat);
  }
  
  public MediaControllerCompat.TransportControls getTransportControls()
  {
    Object localObject = MediaControllerCompatApi21.getTransportControls(mControllerObj);
    if (localObject != null) {
      return new MediaControllerCompat.TransportControlsApi24(localObject);
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompat.MediaControllerImplApi24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */