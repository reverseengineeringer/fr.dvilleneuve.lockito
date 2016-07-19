package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.NetworkInfo;
import java.io.IOException;
import java.io.InputStream;

class NetworkBitmapHunter
  extends BitmapHunter
{
  static final int DEFAULT_RETRY_COUNT = 2;
  private static final int MARKER = 65536;
  private final Downloader downloader;
  int retryCount;
  
  public NetworkBitmapHunter(Picasso paramPicasso, Dispatcher paramDispatcher, Cache paramCache, Stats paramStats, Action paramAction, Downloader paramDownloader)
  {
    super(paramPicasso, paramDispatcher, paramCache, paramStats, paramAction);
    downloader = paramDownloader;
    retryCount = 2;
  }
  
  private Bitmap decodeStream(InputStream paramInputStream, Request paramRequest)
    throws IOException
  {
    if (paramInputStream == null) {
      return null;
    }
    BitmapFactory.Options localOptions = null;
    InputStream localInputStream = paramInputStream;
    if (paramRequest.hasSize())
    {
      localOptions = new BitmapFactory.Options();
      inJustDecodeBounds = true;
      paramInputStream = new MarkableInputStream(paramInputStream);
      localInputStream = paramInputStream;
      long l = paramInputStream.savePosition(65536);
      BitmapFactory.decodeStream(localInputStream, null, localOptions);
      calculateInSampleSize(targetWidth, targetHeight, localOptions);
      paramInputStream.reset(l);
    }
    return BitmapFactory.decodeStream(localInputStream, null, localOptions);
  }
  
  Bitmap decode(Request paramRequest)
    throws IOException
  {
    if (retryCount == 0) {}
    Downloader.Response localResponse;
    for (boolean bool = true;; bool = false)
    {
      localResponse = downloader.load(uri, bool);
      if (localResponse != null) {
        break;
      }
      localObject = null;
      return (Bitmap)localObject;
    }
    if (cached) {}
    for (localObject = Picasso.LoadedFrom.DISK;; localObject = Picasso.LoadedFrom.NETWORK)
    {
      loadedFrom = ((Picasso.LoadedFrom)localObject);
      Bitmap localBitmap = localResponse.getBitmap();
      localObject = localBitmap;
      if (localBitmap != null) {
        break;
      }
      localObject = localResponse.getInputStream();
      try
      {
        paramRequest = decodeStream((InputStream)localObject, paramRequest);
        return paramRequest;
      }
      finally
      {
        Utils.closeQuietly((InputStream)localObject);
      }
    }
  }
  
  boolean shouldRetry(boolean paramBoolean, NetworkInfo paramNetworkInfo)
  {
    int i;
    if (retryCount > 0)
    {
      i = 1;
      if (i != 0) {
        break label20;
      }
    }
    label20:
    do
    {
      return false;
      i = 0;
      break;
      retryCount -= 1;
    } while ((paramNetworkInfo != null) && (!paramNetworkInfo.isConnectedOrConnecting()));
    return true;
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.NetworkBitmapHunter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */