package org.springframework.http.client.support;

import java.util.List;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;

public abstract class InterceptingHttpAccessor
  extends HttpAccessor
{
  private List<ClientHttpRequestInterceptor> interceptors;
  
  public List<ClientHttpRequestInterceptor> getInterceptors()
  {
    return interceptors;
  }
  
  public ClientHttpRequestFactory getRequestFactory()
  {
    ClientHttpRequestFactory localClientHttpRequestFactory = super.getRequestFactory();
    Object localObject = localClientHttpRequestFactory;
    if (!CollectionUtils.isEmpty(getInterceptors())) {
      localObject = new InterceptingClientHttpRequestFactory(localClientHttpRequestFactory, getInterceptors());
    }
    return (ClientHttpRequestFactory)localObject;
  }
  
  public void setInterceptors(List<ClientHttpRequestInterceptor> paramList)
  {
    interceptors = paramList;
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.support.InterceptingHttpAccessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */