package android.support.v4.media.session;

import android.media.session.MediaController.TransportControls;
import android.net.Uri;
import android.os.Bundle;

public class MediaControllerCompatApi24$TransportControls
  extends MediaControllerCompatApi23.TransportControls
{
  public static void prepare(Object paramObject)
  {
    ((MediaController.TransportControls)paramObject).prepare();
  }
  
  public static void prepareFromMediaId(Object paramObject, String paramString, Bundle paramBundle)
  {
    ((MediaController.TransportControls)paramObject).prepareFromMediaId(paramString, paramBundle);
  }
  
  public static void prepareFromSearch(Object paramObject, String paramString, Bundle paramBundle)
  {
    ((MediaController.TransportControls)paramObject).prepareFromSearch(paramString, paramBundle);
  }
  
  public static void prepareFromUri(Object paramObject, Uri paramUri, Bundle paramBundle)
  {
    ((MediaController.TransportControls)paramObject).prepareFromUri(paramUri, paramBundle);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompatApi24.TransportControls
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */