package org.springframework.http.client;

import android.os.Build.VERSION;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

final class SimpleStreamingClientHttpRequest
  extends AbstractClientHttpRequest
{
  private static final Boolean olderThanFroyo;
  private OutputStream body;
  private final int chunkSize;
  private final HttpURLConnection connection;
  
  static
  {
    if (Build.VERSION.SDK_INT < 8) {}
    for (boolean bool = true;; bool = false)
    {
      olderThanFroyo = Boolean.valueOf(bool);
      return;
    }
  }
  
  SimpleStreamingClientHttpRequest(HttpURLConnection paramHttpURLConnection, int paramInt)
  {
    connection = paramHttpURLConnection;
    chunkSize = paramInt;
    if (olderThanFroyo.booleanValue()) {
      System.setProperty("http.keepAlive", "false");
    }
  }
  
  private boolean shouldAllowConnectionReuse(String paramString1, String paramString2)
  {
    return (!olderThanFroyo.booleanValue()) || (!paramString1.equals("Connection")) || (!paramString2.equals("Keep-Alive"));
  }
  
  private void writeHeaders(HttpHeaders paramHttpHeaders)
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
        if (shouldAllowConnectionReuse(str1, str2)) {
          connection.addRequestProperty(str1, str2);
        }
      }
    }
  }
  
  protected ClientHttpResponse executeInternal(HttpHeaders paramHttpHeaders)
    throws IOException
  {
    try
    {
      if (body != null) {
        body.close();
      }
      for (;;)
      {
        return new SimpleClientHttpResponse(connection);
        writeHeaders(paramHttpHeaders);
        connection.connect();
      }
    }
    catch (IOException paramHttpHeaders)
    {
      for (;;) {}
    }
  }
  
  protected OutputStream getBodyInternal(HttpHeaders paramHttpHeaders)
    throws IOException
  {
    if (body == null)
    {
      int i = (int)paramHttpHeaders.getContentLength();
      if ((i < 0) || (olderThanFroyo.booleanValue())) {
        break label70;
      }
      connection.setFixedLengthStreamingMode(i);
    }
    for (;;)
    {
      writeHeaders(paramHttpHeaders);
      connection.connect();
      body = connection.getOutputStream();
      return new NonClosingOutputStream(body, null);
      label70:
      connection.setChunkedStreamingMode(chunkSize);
    }
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
  
  private static class NonClosingOutputStream
    extends FilterOutputStream
  {
    private NonClosingOutputStream(OutputStream paramOutputStream)
    {
      super();
    }
    
    public void close()
      throws IOException
    {}
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.SimpleStreamingClientHttpRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */