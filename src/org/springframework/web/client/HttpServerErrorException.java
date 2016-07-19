package org.springframework.web.client;

import java.nio.charset.Charset;
import org.springframework.http.HttpStatus;

public class HttpServerErrorException
  extends HttpStatusCodeException
{
  private static final long serialVersionUID = 1L;
  
  public HttpServerErrorException(HttpStatus paramHttpStatus)
  {
    super(paramHttpStatus);
  }
  
  public HttpServerErrorException(HttpStatus paramHttpStatus, String paramString)
  {
    super(paramHttpStatus, paramString);
  }
  
  public HttpServerErrorException(HttpStatus paramHttpStatus, String paramString, byte[] paramArrayOfByte, Charset paramCharset)
  {
    super(paramHttpStatus, paramString, paramArrayOfByte, paramCharset);
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.client.HttpServerErrorException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */