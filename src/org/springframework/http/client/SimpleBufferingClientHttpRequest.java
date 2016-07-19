package org.springframework.http.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.FileCopyUtils;

final class SimpleBufferingClientHttpRequest
  extends AbstractBufferingClientHttpRequest
{
  private final HttpURLConnection connection;
  
  SimpleBufferingClientHttpRequest(HttpURLConnection paramHttpURLConnection)
  {
    connection = paramHttpURLConnection;
  }
  
  protected ClientHttpResponse executeInternal(HttpHeaders paramHttpHeaders, byte[] paramArrayOfByte)
    throws IOException
  {
    paramHttpHeaders = paramHttpHeaders.entrySet().iterator();
    while (paramHttpHeaders.hasNext())
    {
      Object localObject = (Map.Entry)paramHttpHeaders.next();
      String str1 = (String)((Map.Entry)localObject).getKey();
      localObject = ((List)((Map.Entry)localObject).getValue()).iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str2 = (String)((Iterator)localObject).next();
        connection.addRequestProperty(str1, str2);
      }
    }
    if (connection.getDoOutput()) {
      connection.setFixedLengthStreamingMode(paramArrayOfByte.length);
    }
    connection.connect();
    if (connection.getDoOutput()) {
      FileCopyUtils.copy(paramArrayOfByte, connection.getOutputStream());
    }
    return new SimpleClientHttpResponse(connection);
  }
  
  public HttpMethod getMethod()
  {
    return HttpMethod.valueOf(connection.getRequestMethod());
  }
  
  public URI getURI()
  {
    try
    {
      URI localURI = connection.getURL().toURI();
      return localURI;
    }
    catch (URISyntaxException localURISyntaxException)
    {
      throw new IllegalStateException("Could not get HttpURLConnection URI: " + localURISyntaxException.getMessage(), localURISyntaxException);
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.SimpleBufferingClientHttpRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */