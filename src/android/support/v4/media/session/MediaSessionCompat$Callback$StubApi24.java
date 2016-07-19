package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;

class MediaSessionCompat$Callback$StubApi24
  extends MediaSessionCompat.Callback.StubApi23
  implements MediaSessionCompatApi24.Callback
{
  private MediaSessionCompat$Callback$StubApi24(MediaSessionCompat.Callback paramCallback)
  {
    super(paramCallback, null);
  }
  
  public void onPrepare()
  {
    this$0.onPrepare();
  }
  
  public void onPrepareFromMediaId(String paramString, Bundle paramBundle)
  {
    this$0.onPrepareFromMediaId(paramString, paramBundle);
  }
  
  public void onPrepareFromSearch(String paramString, Bundle paramBundle)
  {
    this$0.onPrepareFromSearch(paramString, paramBundle);
  }
  
  public void onPrepareFromUri(Uri paramUri, Bundle paramBundle)
  {
    this$0.onPrepareFromUri(paramUri, paramBundle);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompat.Callback.StubApi24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */