package org.springframework.http.client;

import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

public abstract class AbstractClientHttpRequestFactoryWrapper
  implements ClientHttpRequestFactory
{
  private final ClientHttpRequestFactory requestFactory;
  
  protected AbstractClientHttpRequestFactoryWrapper(ClientHttpRequestFactory paramClientHttpRequestFactory)
  {
    Assert.notNull(paramClientHttpRequestFactory, "'requestFactory' must not be null");
    requestFactory = paramClientHttpRequestFactory;
  }
  
  public final ClientHttpRequest createRequest(URI paramURI, HttpMethod paramHttpMethod)
    throws IOException
  {
    return createRequest(paramURI, paramHttpMethod, requestFactory);
  }
  
  protected abstract ClientHttpRequest createRequest(URI paramURI, HttpMethod paramHttpMethod, ClientHttpRequestFactory paramClientHttpRequestFactory)
    throws IOException;
}

/* Location:
 * Qualified Name:     org.springframework.http.client.AbstractClientHttpRequestFactoryWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */