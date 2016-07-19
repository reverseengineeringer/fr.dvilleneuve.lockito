package org.springframework.http.client;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.util.FileCopyUtils;

class InterceptingClientHttpRequest
  extends AbstractBufferingClientHttpRequest
{
  private final List<ClientHttpRequestInterceptor> interceptors;
  private HttpMethod method;
  private final ClientHttpRequestFactory requestFactory;
  private URI uri;
  
  protected InterceptingClientHttpRequest(ClientHttpRequestFactory paramClientHttpRequestFactory, List<ClientHttpRequestInterceptor> paramList, URI paramURI, HttpMethod paramHttpMethod)
  {
    requestFactory = paramClientHttpRequestFactory;
    interceptors = paramList;
    method = paramHttpMethod;
    uri = paramURI;
  }
  
  protected final ClientHttpResponse executeInternal(HttpHeaders paramHttpHeaders, byte[] paramArrayOfByte)
    throws IOException
  {
    return new RequestExecution(null).execute(this, paramArrayOfByte);
  }
  
  public HttpMethod getMethod()
  {
    return method;
  }
  
  public URI getURI()
  {
    return uri;
  }
  
  private class RequestExecution
    implements ClientHttpRequestExecution
  {
    private final Iterator<ClientHttpRequestInterceptor> iterator = interceptors.iterator();
    
    private RequestExecution() {}
    
    public ClientHttpResponse execute(HttpRequest paramHttpRequest, byte[] paramArrayOfByte)
      throws IOException
    {
      if (iterator.hasNext()) {
        return ((ClientHttpRequestInterceptor)iterator.next()).intercept(paramHttpRequest, paramArrayOfByte, this);
      }
      ClientHttpRequest localClientHttpRequest = requestFactory.createRequest(paramHttpRequest.getURI(), paramHttpRequest.getMethod());
      localClientHttpRequest.getHeaders().putAll(paramHttpRequest.getHeaders());
      if (paramArrayOfByte.length > 0) {
        FileCopyUtils.copy(paramArrayOfByte, localClientHttpRequest.getBody());
      }
      return localClientHttpRequest.execute();
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.InterceptingClientHttpRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */