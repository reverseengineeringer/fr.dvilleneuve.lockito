package org.springframework.http.client;

import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpMethod;

public class BufferingClientHttpRequestFactory
  extends AbstractClientHttpRequestFactoryWrapper
{
  public BufferingClientHttpRequestFactory(ClientHttpRequestFactory paramClientHttpRequestFactory)
  {
    super(paramClientHttpRequestFactory);
  }
  
  protected ClientHttpRequest createRequest(URI paramURI, HttpMethod paramHttpMethod, ClientHttpRequestFactory paramClientHttpRequestFactory)
    throws IOException
  {
    ClientHttpRequest localClientHttpRequest = paramClientHttpRequestFactory.createRequest(paramURI, paramHttpMethod);
    paramClientHttpRequestFactory = localClientHttpRequest;
    if (shouldBuffer(paramURI, paramHttpMethod)) {
      paramClientHttpRequestFactory = new BufferingClientHttpRequestWrapper(localClientHttpRequest);
    }
    return paramClientHttpRequestFactory;
  }
  
  protected boolean shouldBuffer(URI paramURI, HttpMethod paramHttpMethod)
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.BufferingClientHttpRequestFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */