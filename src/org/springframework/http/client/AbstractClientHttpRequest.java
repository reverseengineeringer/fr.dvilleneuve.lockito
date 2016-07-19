package org.springframework.http.client;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;

public abstract class AbstractClientHttpRequest
  implements ClientHttpRequest
{
  private OutputStream compressedBody;
  private boolean executed = false;
  private final HttpHeaders headers = new HttpHeaders();
  
  private void checkExecuted()
  {
    if (!executed) {}
    for (boolean bool = true;; bool = false)
    {
      Assert.state(bool, "ClientHttpRequest already executed");
      return;
    }
  }
  
  private OutputStream getCompressedBody(OutputStream paramOutputStream)
    throws IOException
  {
    if (compressedBody == null) {
      compressedBody = new GZIPOutputStream(paramOutputStream);
    }
    return compressedBody;
  }
  
  private boolean shouldCompress()
  {
    Iterator localIterator = headers.getContentEncoding().iterator();
    while (localIterator.hasNext()) {
      if (((ContentCodingType)localIterator.next()).equals(ContentCodingType.GZIP)) {
        return true;
      }
    }
    return false;
  }
  
  public final ClientHttpResponse execute()
    throws IOException
  {
    checkExecuted();
    if (compressedBody != null) {
      compressedBody.close();
    }
    ClientHttpResponse localClientHttpResponse = executeInternal(headers);
    executed = true;
    return localClientHttpResponse;
  }
  
  protected abstract ClientHttpResponse executeInternal(HttpHeaders paramHttpHeaders)
    throws IOException;
  
  public final OutputStream getBody()
    throws IOException
  {
    checkExecuted();
    OutputStream localOutputStream2 = getBodyInternal(headers);
    OutputStream localOutputStream1 = localOutputStream2;
    if (shouldCompress()) {
      localOutputStream1 = getCompressedBody(localOutputStream2);
    }
    return localOutputStream1;
  }
  
  protected abstract OutputStream getBodyInternal(HttpHeaders paramHttpHeaders)
    throws IOException;
  
  public final HttpHeaders getHeaders()
  {
    if (executed) {
      return HttpHeaders.readOnlyHttpHeaders(headers);
    }
    return headers;
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.AbstractClientHttpRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */