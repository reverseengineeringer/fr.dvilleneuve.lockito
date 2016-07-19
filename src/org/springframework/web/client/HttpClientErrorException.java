package org.springframework.web.client;

import java.nio.charset.Charset;
import org.springframework.http.HttpStatus;

public class HttpClientErrorException
  extends HttpStatusCodeException
{
  private static final long serialVersionUID = 1L;
  
  public HttpClientErrorException(HttpStatus paramHttpStatus)
  {
    super(paramHttpStatus);
  }
  
  public HttpClientErrorException(HttpStatus paramHttpStatus, String paramString)
  {
    super(paramHttpStatus, paramString);
  }
  
  public HttpClientErrorException(HttpStatus paramHttpStatus, String paramString, byte[] paramArrayOfByte, Charset paramCharset)
  {
    super(paramHttpStatus, paramString, paramArrayOfByte, paramCharset);
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.client.HttpClientErrorException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */