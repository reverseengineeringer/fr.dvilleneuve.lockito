package org.springframework.http.client;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.springframework.http.HttpHeaders;

final class HttpComponentsClientHttpResponse
  extends AbstractClientHttpResponse
{
  private HttpHeaders headers;
  private final HttpResponse httpResponse;
  
  HttpComponentsClientHttpResponse(HttpResponse paramHttpResponse)
  {
    httpResponse = paramHttpResponse;
  }
  
  protected void closeInternal()
  {
    HttpEntity localHttpEntity = httpResponse.getEntity();
    if (localHttpEntity != null) {}
    try
    {
      localHttpEntity.consumeContent();
      return;
    }
    catch (IOException localIOException) {}
  }
  
  protected InputStream getBodyInternal()
    throws IOException
  {
    HttpEntity localHttpEntity = httpResponse.getEntity();
    if (localHttpEntity != null) {
      return localHttpEntity.getContent();
    }
    return null;
  }
  
  public HttpHeaders getHeaders()
  {
    if (headers == null)
    {
      headers = new HttpHeaders();
      Header[] arrayOfHeader = httpResponse.getAllHeaders();
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
    throws IOException
  {
    return httpResponse.getStatusLine().getStatusCode();
  }
  
  public String getStatusText()
    throws IOException
  {
    return httpResponse.getStatusLine().getReasonPhrase();
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.HttpComponentsClientHttpResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */