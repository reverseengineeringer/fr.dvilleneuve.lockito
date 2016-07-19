package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;

class MediaSessionCompatApi24$CallbackProxy<T extends MediaSessionCompatApi24.Callback>
  extends MediaSessionCompatApi23.CallbackProxy<T>
{
  public MediaSessionCompatApi24$CallbackProxy(T paramT)
  {
    super(paramT);
  }
  
  public void onPrepare()
  {
    ((MediaSessionCompatApi24.Callback)mCallback).onPrepare();
  }
  
  public void onPrepareFromMediaId(String paramString, Bundle paramBundle)
  {
    ((MediaSessionCompatApi24.Callback)mCallback).onPrepareFromMediaId(paramString, paramBundle);
  }
  
  public void onPrepareFromSearch(String paramString, Bundle paramBundle)
  {
    ((MediaSessionCompatApi24.Callback)mCallback).onPrepareFromSearch(paramString, paramBundle);
  }
  
  public void onPrepareFromUri(Uri paramUri, Bundle paramBundle)
  {
    ((MediaSessionCompatApi24.Callback)mCallback).onPrepareFromUri(paramUri, paramBundle);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompatApi24.CallbackProxy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */