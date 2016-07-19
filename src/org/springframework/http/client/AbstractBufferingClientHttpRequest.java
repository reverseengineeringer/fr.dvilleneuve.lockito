package org.springframework.http.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.springframework.http.HttpHeaders;

abstract class AbstractBufferingClientHttpRequest
  extends AbstractClientHttpRequest
{
  private ByteArrayOutputStream bufferedOutput = new ByteArrayOutputStream();
  
  protected ClientHttpResponse executeInternal(HttpHeaders paramHttpHeaders)
    throws IOException
  {
    byte[] arrayOfByte = bufferedOutput.toByteArray();
    if (paramHttpHeaders.getContentLength() == -1L) {
      paramHttpHeaders.setContentLength(arrayOfByte.length);
    }
    paramHttpHeaders = executeInternal(paramHttpHeaders, arrayOfByte);
    bufferedOutput = null;
    return paramHttpHeaders;
  }
  
  protected abstract ClientHttpResponse executeInternal(HttpHeaders paramHttpHeaders, byte[] paramArrayOfByte)
    throws IOException;
  
  protected OutputStream getBodyInternal(HttpHeaders paramHttpHeaders)
    throws IOException
  {
    return bufferedOutput;
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.AbstractBufferingClientHttpRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */