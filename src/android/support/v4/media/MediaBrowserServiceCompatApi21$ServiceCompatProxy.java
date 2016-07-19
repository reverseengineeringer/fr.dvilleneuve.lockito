package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcel;
import java.util.List;

public abstract interface MediaBrowserServiceCompatApi21$ServiceCompatProxy
{
  public abstract MediaBrowserServiceCompatApi21.BrowserRoot onGetRoot(String paramString, int paramInt, Bundle paramBundle);
  
  public abstract void onLoadChildren(String paramString, MediaBrowserServiceCompatApi21.ResultWrapper<List<Parcel>> paramResultWrapper);
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompatApi21.ServiceCompatProxy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */