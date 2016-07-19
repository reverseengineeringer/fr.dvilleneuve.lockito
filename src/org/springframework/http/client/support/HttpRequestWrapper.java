package org.springframework.http.client.support;

import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.util.Assert;

public class HttpRequestWrapper
  implements HttpRequest
{
  private final HttpRequest request;
  
  public HttpRequestWrapper(HttpRequest paramHttpRequest)
  {
    Assert.notNull(paramHttpRequest, "'request' must not be null");
    request = paramHttpRequest;
  }
  
  public HttpHeaders getHeaders()
  {
    return request.getHeaders();
  }
  
  public HttpMethod getMethod()
  {
    return request.getMethod();
  }
  
  public HttpRequest getRequest()
  {
    return request;
  }
  
  public URI getURI()
  {
    return request.getURI();
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.support.HttpRequestWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */