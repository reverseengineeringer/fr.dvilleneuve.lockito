package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;

public abstract interface MediaSessionCompatApi24$Callback
  extends MediaSessionCompatApi23.Callback
{
  public abstract void onPrepare();
  
  public abstract void onPrepareFromMediaId(String paramString, Bundle paramBundle);
  
  public abstract void onPrepareFromSearch(String paramString, Bundle paramBundle);
  
  public abstract void onPrepareFromUri(Uri paramUri, Bundle paramBundle);
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompatApi24.Callback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */