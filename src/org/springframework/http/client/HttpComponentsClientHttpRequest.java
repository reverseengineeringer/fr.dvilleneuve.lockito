package org.springframework.http.client;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

final class HttpComponentsClientHttpRequest
  extends AbstractBufferingClientHttpRequest
{
  private final HttpClient httpClient;
  private final HttpContext httpContext;
  private final HttpUriRequest httpRequest;
  
  public HttpComponentsClientHttpRequest(HttpClient paramHttpClient, HttpUriRequest paramHttpUriRequest, HttpContext paramHttpContext)
  {
    httpClient = paramHttpClient;
    httpRequest = paramHttpUriRequest;
    httpContext = paramHttpContext;
  }
  
  public ClientHttpResponse executeInternal(HttpHeaders paramHttpHeaders, byte[] paramArrayOfByte)
    throws IOException
  {
    paramHttpHeaders = paramHttpHeaders.entrySet().iterator();
    while (paramHttpHeaders.hasNext())
    {
      Object localObject = (Map.Entry)paramHttpHeaders.next();
      String str1 = (String)((Map.Entry)localObject).getKey();
      if ((!str1.equalsIgnoreCase("Content-Length")) && (!str1.equalsIgnoreCase("Transfer-Encoding")))
      {
        localObject = ((List)((Map.Entry)localObject).getValue()).iterator();
        while (((Iterator)localObject).hasNext())
        {
          String str2 = (String)((Iterator)localObject).next();
          httpRequest.addHeader(str1, str2);
        }
      }
    }
    if ((httpRequest instanceof HttpEntityEnclosingRequest)) {
      ((HttpEntityEnclosingRequest)httpRequest).setEntity(new ByteArrayEntity(paramArrayOfByte));
    }
    return new HttpComponentsClientHttpResponse(httpClient.execute(httpRequest, httpContext));
  }
  
  public HttpMethod getMethod()
  {
    return HttpMethod.valueOf(httpRequest.getMethod());
  }
  
  public URI getURI()
  {
    return httpRequest.getURI();
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.HttpComponentsClientHttpRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */