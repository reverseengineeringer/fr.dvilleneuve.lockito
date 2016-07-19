package android.support.v4.media;

import android.content.Context;
import android.media.browse.MediaBrowser.MediaItem;
import android.os.Bundle;
import android.service.media.MediaBrowserService.Result;
import java.util.List;

class MediaBrowserServiceCompatApi24$MediaBrowserServiceAdaptor
  extends MediaBrowserServiceCompatApi23.MediaBrowserServiceAdaptor
{
  MediaBrowserServiceCompatApi24$MediaBrowserServiceAdaptor(Context paramContext, MediaBrowserServiceCompatApi24.ServiceCompatProxy paramServiceCompatProxy)
  {
    super(paramContext, paramServiceCompatProxy);
  }
  
  public void onLoadChildren(String paramString, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> paramResult, Bundle paramBundle)
  {
    ((MediaBrowserServiceCompatApi24.ServiceCompatProxy)mServiceProxy).onLoadChildren(paramString, new MediaBrowserServiceCompatApi24.ResultWrapper(paramResult), paramBundle);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompatApi24.MediaBrowserServiceAdaptor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */