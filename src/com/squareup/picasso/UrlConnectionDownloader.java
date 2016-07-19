package com.squareup.picasso;

import android.content.Context;
import android.net.Uri;
import android.net.http.HttpResponseCache;
import android.os.Build.VERSION;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlConnectionDownloader
  implements Downloader
{
  static final String RESPONSE_SOURCE = "X-Android-Response-Source";
  static volatile Object cache;
  private static final Object lock = new Object();
  private final Context context;
  
  public UrlConnectionDownloader(Context paramContext)
  {
    context = paramContext.getApplicationContext();
  }
  
  private static void installCacheIfNeeded(Context paramContext)
  {
    if (cache == null) {
      try
      {
        synchronized (lock)
        {
          if (cache == null) {
            cache = ResponseCacheIcs.install(paramContext);
          }
          return;
        }
        return;
      }
      catch (IOException paramContext) {}
    }
  }
  
  public Downloader.Response load(Uri paramUri, boolean paramBoolean)
    throws IOException
  {
    if (Build.VERSION.SDK_INT >= 14) {
      installCacheIfNeeded(context);
    }
    paramUri = openConnection(paramUri);
    paramUri.setUseCaches(true);
    if (paramBoolean) {
      paramUri.setRequestProperty("Cache-Control", "only-if-cached;max-age=2147483647");
    }
    if (paramUri.getResponseCode() >= 300)
    {
      paramUri.disconnect();
      return null;
    }
    paramBoolean = Utils.parseResponseSourceHeader(paramUri.getHeaderField("X-Android-Response-Source"));
    return new Downloader.Response(paramUri.getInputStream(), paramBoolean);
  }
  
  protected HttpURLConnection openConnection(Uri paramUri)
    throws IOException
  {
    paramUri = (HttpURLConnection)new URL(paramUri.toString()).openConnection();
    paramUri.setConnectTimeout(15000);
    paramUri.setReadTimeout(20000);
    return paramUri;
  }
  
  private static class ResponseCacheIcs
  {
    static Object install(Context paramContext)
      throws IOException
    {
      File localFile = Utils.createDefaultCacheDir(paramContext);
      HttpResponseCache localHttpResponseCache = HttpResponseCache.getInstalled();
      paramContext = localHttpResponseCache;
      if (localHttpResponseCache == null) {
        paramContext = HttpResponseCache.install(localFile, Utils.calculateDiskCacheSize(localFile));
      }
      return paramContext;
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.UrlConnectionDownloader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */