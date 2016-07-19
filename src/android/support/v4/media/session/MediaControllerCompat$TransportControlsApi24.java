package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;

class MediaControllerCompat$TransportControlsApi24
  extends MediaControllerCompat.TransportControlsApi23
{
  public MediaControllerCompat$TransportControlsApi24(Object paramObject)
  {
    super(paramObject);
  }
  
  public void prepare()
  {
    MediaControllerCompatApi24.TransportControls.prepare(mControlsObj);
  }
  
  public void prepareFromMediaId(String paramString, Bundle paramBundle)
  {
    MediaControllerCompatApi24.TransportControls.prepareFromMediaId(mControlsObj, paramString, paramBundle);
  }
  
  public void prepareFromSearch(String paramString, Bundle paramBundle)
  {
    MediaControllerCompatApi24.TransportControls.prepareFromSearch(mControlsObj, paramString, paramBundle);
  }
  
  public void prepareFromUri(Uri paramUri, Bundle paramBundle)
  {
    MediaControllerCompatApi24.TransportControls.prepareFromUri(mControlsObj, paramUri, paramBundle);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompat.TransportControlsApi24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */