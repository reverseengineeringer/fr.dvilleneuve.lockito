package org.springframework.http.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

public class SimpleClientHttpRequestFactory
  implements ClientHttpRequestFactory
{
  private static final int DEFAULT_CHUNK_SIZE = 0;
  private boolean bufferRequestBody = true;
  private int chunkSize = 0;
  private int connectTimeout = -1;
  private Proxy proxy;
  private int readTimeout = -1;
  
  public ClientHttpRequest createRequest(URI paramURI, HttpMethod paramHttpMethod)
    throws IOException
  {
    paramURI = openConnection(paramURI.toURL(), proxy);
    prepareConnection(paramURI, paramHttpMethod.name());
    if (bufferRequestBody) {
      return new SimpleBufferingClientHttpRequest(paramURI);
    }
    return new SimpleStreamingClientHttpRequest(paramURI, chunkSize);
  }
  
  protected HttpURLConnection openConnection(URL paramURL, Proxy paramProxy)
    throws IOException
  {
    if (paramProxy != null) {}
    for (paramURL = paramURL.openConnection(paramProxy);; paramURL = paramURL.openConnection())
    {
      Assert.isInstanceOf(HttpURLConnection.class, paramURL);
      return (HttpURLConnection)paramURL;
    }
  }
  
  protected void prepareConnection(HttpURLConnection paramHttpURLConnection, String paramString)
    throws IOException
  {
    if (connectTimeout >= 0) {
      paramHttpURLConnection.setConnectTimeout(connectTimeout);
    }
    if (readTimeout >= 0) {
      paramHttpURLConnection.setReadTimeout(readTimeout);
    }
    paramHttpURLConnection.setDoInput(true);
    if ("GET".equals(paramString))
    {
      paramHttpURLConnection.setInstanceFollowRedirects(true);
      if ((!"PUT".equals(paramString)) && (!"POST".equals(paramString))) {
        break label86;
      }
      paramHttpURLConnection.setDoOutput(true);
    }
    for (;;)
    {
      paramHttpURLConnection.setRequestMethod(paramString);
      return;
      paramHttpURLConnection.setInstanceFollowRedirects(false);
      break;
      label86:
      paramHttpURLConnection.setDoOutput(false);
    }
  }
  
  public void setBufferRequestBody(boolean paramBoolean)
  {
    bufferRequestBody = paramBoolean;
  }
  
  public void setChunkSize(int paramInt)
  {
    chunkSize = paramInt;
  }
  
  public void setConnectTimeout(int paramInt)
  {
    connectTimeout = paramInt;
  }
  
  public void setProxy(Proxy paramProxy)
  {
    proxy = paramProxy;
  }
  
  public void setReadTimeout(int paramInt)
  {
    readTimeout = paramInt;
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.SimpleClientHttpRequestFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */