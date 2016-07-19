package org.springframework.http.client;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.util.FileCopyUtils;

class InterceptingClientHttpRequest$RequestExecution
  implements ClientHttpRequestExecution
{
  private final Iterator<ClientHttpRequestInterceptor> iterator;
  
  private InterceptingClientHttpRequest$RequestExecution(InterceptingClientHttpRequest paramInterceptingClientHttpRequest)
  {
    iterator = InterceptingClientHttpRequest.access$100(paramInterceptingClientHttpRequest).iterator();
  }
  
  public ClientHttpResponse execute(HttpRequest paramHttpRequest, byte[] paramArrayOfByte)
    throws IOException
  {
    if (iterator.hasNext()) {
      return ((ClientHttpRequestInterceptor)iterator.next()).intercept(paramHttpRequest, paramArrayOfByte, this);
    }
    ClientHttpRequest localClientHttpRequest = InterceptingClientHttpRequest.access$200(this$0).createRequest(paramHttpRequest.getURI(), paramHttpRequest.getMethod());
    localClientHttpRequest.getHeaders().putAll(paramHttpRequest.getHeaders());
    if (paramArrayOfByte.length > 0) {
      FileCopyUtils.copy(paramArrayOfByte, localClientHttpRequest.getBody());
    }
    return localClientHttpRequest.execute();
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.InterceptingClientHttpRequest.RequestExecution
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */