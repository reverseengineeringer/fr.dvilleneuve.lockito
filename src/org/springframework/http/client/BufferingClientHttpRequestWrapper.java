package org.springframework.http.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;

final class BufferingClientHttpRequestWrapper
  extends AbstractBufferingClientHttpRequest
{
  private final ClientHttpRequest request;
  
  BufferingClientHttpRequestWrapper(ClientHttpRequest paramClientHttpRequest)
  {
    Assert.notNull(paramClientHttpRequest, "'request' must not be null");
    request = paramClientHttpRequest;
  }
  
  protected ClientHttpResponse executeInternal(HttpHeaders paramHttpHeaders, byte[] paramArrayOfByte)
    throws IOException
  {
    OutputStream localOutputStream = request.getBody();
    request.getHeaders().putAll(paramHttpHeaders);
    FileCopyUtils.copy(paramArrayOfByte, localOutputStream);
    return new BufferingClientHttpResponseWrapper(request.execute());
  }
  
  public HttpMethod getMethod()
  {
    return request.getMethod();
  }
  
  public URI getURI()
  {
    return request.getURI();
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.BufferingClientHttpRequestWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */