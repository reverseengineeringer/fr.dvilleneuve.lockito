package org.springframework.http.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

final class SimpleClientHttpResponse
  extends AbstractClientHttpResponse
{
  private static final String AUTH_ERROR = "Received authentication challenge is null";
  private static final String AUTH_ERROR_JELLY_BEAN = "No authentication challenges found";
  private static final String PROXY_AUTH_ERROR = "Received HTTP_PROXY_AUTH (407) code while not using proxy";
  private final HttpURLConnection connection;
  private HttpHeaders headers;
  
  SimpleClientHttpResponse(HttpURLConnection paramHttpURLConnection)
  {
    connection = paramHttpURLConnection;
  }
  
  private int handleIOException(IOException paramIOException)
    throws IOException
  {
    if (("Received authentication challenge is null".equals(paramIOException.getMessage())) || ("No authentication challenges found".equals(paramIOException.getMessage()))) {
      return HttpStatus.UNAUTHORIZED.value();
    }
    if ("Received HTTP_PROXY_AUTH (407) code while not using proxy".equals(paramIOException.getMessage())) {
      return HttpStatus.PROXY_AUTHENTICATION_REQUIRED.value();
    }
    throw paramIOException;
  }
  
  protected void closeInternal()
  {
    connection.disconnect();
  }
  
  protected InputStream getBodyInternal()
    throws IOException
  {
    InputStream localInputStream = connection.getErrorStream();
    if (localInputStream != null) {
      return localInputStream;
    }
    return connection.getInputStream();
  }
  
  public HttpHeaders getHeaders()
  {
    String str;
    int i;
    if (headers == null)
    {
      headers = new HttpHeaders();
      str = connection.getHeaderFieldKey(0);
      if (StringUtils.hasLength(str)) {
        headers.add(str, connection.getHeaderField(0));
      }
      i = 1;
    }
    for (;;)
    {
      str = connection.getHeaderFieldKey(i);
      if (!StringUtils.hasLength(str)) {
        return headers;
      }
      headers.add(str, connection.getHeaderField(i));
      i += 1;
    }
  }
  
  public int getRawStatusCode()
    throws IOException
  {
    try
    {
      int i = connection.getResponseCode();
      return i;
    }
    catch (IOException localIOException)
    {
      return handleIOException(localIOException);
    }
  }
  
  public HttpStatus getStatusCode()
    throws IOException
  {
    return HttpStatus.valueOf(getRawStatusCode());
  }
  
  public String getStatusText()
    throws IOException
  {
    try
    {
      String str = connection.getResponseMessage();
      return str;
    }
    catch (IOException localIOException)
    {
      return HttpStatus.valueOf(handleIOException(localIOException)).getReasonPhrase();
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.SimpleClientHttpResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */