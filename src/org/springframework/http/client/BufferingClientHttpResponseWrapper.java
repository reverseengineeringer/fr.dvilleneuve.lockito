package org.springframework.http.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;

final class BufferingClientHttpResponseWrapper
  implements ClientHttpResponse
{
  private byte[] body;
  private final ClientHttpResponse response;
  
  BufferingClientHttpResponseWrapper(ClientHttpResponse paramClientHttpResponse)
  {
    response = paramClientHttpResponse;
  }
  
  public void close()
  {
    response.close();
  }
  
  public InputStream getBody()
    throws IOException
  {
    if (body == null) {
      body = FileCopyUtils.copyToByteArray(response.getBody());
    }
    return new ByteArrayInputStream(body);
  }
  
  public HttpHeaders getHeaders()
  {
    return response.getHeaders();
  }
  
  public int getRawStatusCode()
    throws IOException
  {
    return response.getRawStatusCode();
  }
  
  public HttpStatus getStatusCode()
    throws IOException
  {
    return response.getStatusCode();
  }
  
  public String getStatusText()
    throws IOException
  {
    return response.getStatusText();
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.BufferingClientHttpResponseWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */