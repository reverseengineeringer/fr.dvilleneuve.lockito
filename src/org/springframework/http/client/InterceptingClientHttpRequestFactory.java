package org.springframework.http.client;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

public class InterceptingClientHttpRequestFactory
  extends AbstractClientHttpRequestFactoryWrapper
{
  private final List<ClientHttpRequestInterceptor> interceptors;
  
  public InterceptingClientHttpRequestFactory(ClientHttpRequestFactory paramClientHttpRequestFactory, List<ClientHttpRequestInterceptor> paramList)
  {
    super(paramClientHttpRequestFactory);
    Assert.notNull(paramClientHttpRequestFactory, "'requestFactory' must not be null");
    if (paramList != null) {}
    for (;;)
    {
      interceptors = paramList;
      return;
      paramList = Collections.emptyList();
    }
  }
  
  protected ClientHttpRequest createRequest(URI paramURI, HttpMethod paramHttpMethod, ClientHttpRequestFactory paramClientHttpRequestFactory)
  {
    return new InterceptingClientHttpRequest(paramClientHttpRequestFactory, interceptors, paramURI, paramHttpMethod);
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.InterceptingClientHttpRequestFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */