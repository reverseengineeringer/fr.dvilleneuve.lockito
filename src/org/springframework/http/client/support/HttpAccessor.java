package org.springframework.http.client.support;

import android.os.Build.VERSION;
import android.util.Log;
import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.Assert;

public abstract class HttpAccessor
{
  private static final String TAG = HttpAccessor.class.getSimpleName();
  private ClientHttpRequestFactory requestFactory;
  
  protected HttpAccessor()
  {
    if (Build.VERSION.SDK_INT >= 9)
    {
      requestFactory = new SimpleClientHttpRequestFactory();
      return;
    }
    requestFactory = new HttpComponentsClientHttpRequestFactory();
  }
  
  protected ClientHttpRequest createRequest(URI paramURI, HttpMethod paramHttpMethod)
    throws IOException
  {
    ClientHttpRequest localClientHttpRequest = getRequestFactory().createRequest(paramURI, paramHttpMethod);
    if (Log.isLoggable(TAG, 3)) {
      Log.d(TAG, "Created " + paramHttpMethod.name() + " request for \"" + paramURI + "\"");
    }
    return localClientHttpRequest;
  }
  
  public ClientHttpRequestFactory getRequestFactory()
  {
    return requestFactory;
  }
  
  public void setRequestFactory(ClientHttpRequestFactory paramClientHttpRequestFactory)
  {
    Assert.notNull(paramClientHttpRequestFactory, "'requestFactory' must not be null");
    requestFactory = paramClientHttpRequestFactory;
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.support.HttpAccessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */