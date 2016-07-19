package com.squareup.picasso;

import android.content.Context;
import android.net.Uri;
import com.squareup.okhttp.HttpResponseCache;
import com.squareup.okhttp.OkHttpClient;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class OkHttpDownloader
  implements Downloader
{
  static final String RESPONSE_SOURCE_ANDROID = "X-Android-Response-Source";
  static final String RESPONSE_SOURCE_OKHTTP = "OkHttp-Response-Source";
  private final OkHttpClient client;
  
  public OkHttpDownloader(Context paramContext)
  {
    this(Utils.createDefaultCacheDir(paramContext));
  }
  
  public OkHttpDownloader(Context paramContext, long paramLong)
  {
    this(Utils.createDefaultCacheDir(paramContext), paramLong);
  }
  
  public OkHttpDownloader(OkHttpClient paramOkHttpClient)
  {
    client = paramOkHttpClient;
  }
  
  public OkHttpDownloader(File paramFile)
  {
    this(paramFile, Utils.calculateDiskCacheSize(paramFile));
  }
  
  public OkHttpDownloader(File paramFile, long paramLong)
  {
    this(new OkHttpClient());
    try
    {
      client.setResponseCache(new HttpResponseCache(paramFile, paramLong));
      return;
    }
    catch (IOException paramFile) {}
  }
  
  protected OkHttpClient getClient()
  {
    return client;
  }
  
  public Downloader.Response load(Uri paramUri, boolean paramBoolean)
    throws IOException
  {
    HttpURLConnection localHttpURLConnection = openConnection(paramUri);
    localHttpURLConnection.setUseCaches(true);
    if (paramBoolean) {
      localHttpURLConnection.setRequestProperty("Cache-Control", "only-if-cached;max-age=2147483647");
    }
    if (localHttpURLConnection.getResponseCode() >= 300)
    {
      localHttpURLConnection.disconnect();
      return null;
    }
    String str = localHttpURLConnection.getHeaderField("OkHttp-Response-Source");
    paramUri = str;
    if (str == null) {
      paramUri = localHttpURLConnection.getHeaderField("X-Android-Response-Source");
    }
    paramBoolean = Utils.parseResponseSourceHeader(paramUri);
    return new Downloader.Response(localHttpURLConnection.getInputStream(), paramBoolean);
  }
  
  protected HttpURLConnection openConnection(Uri paramUri)
    throws IOException
  {
    paramUri = client.open(new URL(paramUri.toString()));
    paramUri.setConnectTimeout(15000);
    paramUri.setReadTimeout(20000);
    return paramUri;
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.OkHttpDownloader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */