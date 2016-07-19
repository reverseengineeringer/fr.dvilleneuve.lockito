package org.springframework.http.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public abstract class AbstractClientHttpResponse
  implements ClientHttpResponse
{
  private InputStream compressedBody;
  
  private InputStream getCompressedBody(InputStream paramInputStream)
    throws IOException
  {
    if (compressedBody == null) {
      compressedBody = new GZIPInputStream(paramInputStream);
    }
    return compressedBody;
  }
  
  private boolean isCompressed()
  {
    Iterator localIterator = getHeaders().getContentEncoding().iterator();
    while (localIterator.hasNext()) {
      if (((ContentCodingType)localIterator.next()).equals(ContentCodingType.GZIP)) {
        return true;
      }
    }
    return false;
  }
  
  public void close()
  {
    if (compressedBody != null) {}
    try
    {
      compressedBody.close();
      closeInternal();
      return;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  protected abstract void closeInternal();
  
  public InputStream getBody()
    throws IOException
  {
    InputStream localInputStream2 = getBodyInternal();
    InputStream localInputStream1 = localInputStream2;
    if (isCompressed()) {
      localInputStream1 = getCompressedBody(localInputStream2);
    }
    return localInputStream1;
  }
  
  protected abstract InputStream getBodyInternal()
    throws IOException;
  
  public HttpStatus getStatusCode()
    throws IOException
  {
    return HttpStatus.valueOf(getRawStatusCode());
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.AbstractClientHttpResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */