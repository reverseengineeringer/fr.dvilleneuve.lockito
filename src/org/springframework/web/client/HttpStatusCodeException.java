package org.springframework.web.client;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.springframework.http.HttpStatus;

public abstract class HttpStatusCodeException
  extends RestClientException
{
  private static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");
  private static final long serialVersionUID = 1L;
  private final byte[] responseBody;
  private final Charset responseCharset;
  private final HttpStatus statusCode;
  private final String statusText;
  
  protected HttpStatusCodeException(HttpStatus paramHttpStatus)
  {
    this(paramHttpStatus, paramHttpStatus.name(), null, null);
  }
  
  protected HttpStatusCodeException(HttpStatus paramHttpStatus, String paramString)
  {
    this(paramHttpStatus, paramString, null, null);
  }
  
  protected HttpStatusCodeException(HttpStatus paramHttpStatus, String paramString, byte[] paramArrayOfByte, Charset paramCharset)
  {
    super(paramHttpStatus.value() + " " + paramString);
    statusCode = paramHttpStatus;
    statusText = paramString;
    if (paramArrayOfByte != null)
    {
      responseBody = paramArrayOfByte;
      if (paramCharset == null) {
        break label68;
      }
    }
    for (;;)
    {
      responseCharset = paramCharset;
      return;
      paramArrayOfByte = new byte[0];
      break;
      label68:
      paramCharset = DEFAULT_CHARSET;
    }
  }
  
  public byte[] getResponseBodyAsByteArray()
  {
    return responseBody;
  }
  
  public String getResponseBodyAsString()
  {
    try
    {
      String str = new String(responseBody, responseCharset.name());
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new InternalError(localUnsupportedEncodingException.getMessage());
    }
  }
  
  public HttpStatus getStatusCode()
  {
    return statusCode;
  }
  
  public String getStatusText()
  {
    return statusText;
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.client.HttpStatusCodeException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */