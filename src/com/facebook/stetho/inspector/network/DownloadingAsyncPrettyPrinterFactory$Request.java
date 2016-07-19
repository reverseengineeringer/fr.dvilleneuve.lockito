package com.facebook.stetho.inspector.network;

import com.facebook.stetho.common.Util;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

class DownloadingAsyncPrettyPrinterFactory$Request
  implements Callable<String>
{
  private URL url;
  
  public DownloadingAsyncPrettyPrinterFactory$Request(URL paramURL)
  {
    url = paramURL;
  }
  
  public String call()
    throws IOException
  {
    Object localObject1 = (HttpURLConnection)url.openConnection();
    int i = ((HttpURLConnection)localObject1).getResponseCode();
    if (i != 200) {
      throw new IOException("Got status code: " + i + " while downloading " + "schema with url: " + url.toString());
    }
    localObject1 = ((HttpURLConnection)localObject1).getInputStream();
    try
    {
      String str = Util.readAsUTF8((InputStream)localObject1);
      return str;
    }
    finally
    {
      ((InputStream)localObject1).close();
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.DownloadingAsyncPrettyPrinterFactory.Request
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */