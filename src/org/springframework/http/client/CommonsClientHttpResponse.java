package org.springframework.http.client;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.springframework.http.HttpHeaders;

@Deprecated
final class CommonsClientHttpResponse
  extends AbstractClientHttpResponse
{
  private HttpHeaders headers;
  private final HttpMethod httpMethod;
  
  CommonsClientHttpResponse(HttpMethod paramHttpMethod)
  {
    httpMethod = paramHttpMethod;
  }
  
  protected void closeInternal()
  {
    httpMethod.releaseConnection();
  }
  
  protected InputStream getBodyInternal()
    throws IOException
  {
    return httpMethod.getResponseBodyAsStream();
  }
  
  public HttpHeaders getHeaders()
  {
    if (headers == null)
    {
      headers = new HttpHeaders();
      Header[] arrayOfHeader = httpMethod.getResponseHeaders();
      int j = arrayOfHeader.length;
      int i = 0;
      while (i < j)
      {
        Header localHeader = arrayOfHeader[i];
        headers.add(localHeader.getName(), localHeader.getValue());
        i += 1;
      }
    }
    return headers;
  }
  
  public int getRawStatusCode()
  {
    return httpMethod.getStatusCode();
  }
  
  public String getStatusText()
  {
    return httpMethod.getStatusText();
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.CommonsClientHttpResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */