package android.support.v4.media;

import android.content.Context;
import android.media.browse.MediaBrowser.MediaItem;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.service.media.MediaBrowserService.BrowserRoot;
import android.service.media.MediaBrowserService.Result;
import java.util.List;

class MediaBrowserServiceCompatApi21$MediaBrowserServiceAdaptor
  extends MediaBrowserService
{
  final MediaBrowserServiceCompatApi21.ServiceCompatProxy mServiceProxy;
  
  MediaBrowserServiceCompatApi21$MediaBrowserServiceAdaptor(Context paramContext, MediaBrowserServiceCompatApi21.ServiceCompatProxy paramServiceCompatProxy)
  {
    attachBaseContext(paramContext);
    mServiceProxy = paramServiceCompatProxy;
  }
  
  public MediaBrowserService.BrowserRoot onGetRoot(String paramString, int paramInt, Bundle paramBundle)
  {
    paramString = mServiceProxy.onGetRoot(paramString, paramInt, paramBundle);
    if (paramString == null) {
      return null;
    }
    return new MediaBrowserService.BrowserRoot(mRootId, mExtras);
  }
  
  public void onLoadChildren(String paramString, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> paramResult)
  {
    mServiceProxy.onLoadChildren(paramString, new MediaBrowserServiceCompatApi21.ResultWrapper(paramResult));
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompatApi21.MediaBrowserServiceAdaptor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */