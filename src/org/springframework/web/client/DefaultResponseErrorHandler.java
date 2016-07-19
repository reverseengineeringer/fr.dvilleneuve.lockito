package org.springframework.web.client;

import java.io.IOException;
import java.nio.charset.Charset;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;

public class DefaultResponseErrorHandler
  implements ResponseErrorHandler
{
  private byte[] getResponseBody(ClientHttpResponse paramClientHttpResponse)
  {
    try
    {
      paramClientHttpResponse = paramClientHttpResponse.getBody();
      if (paramClientHttpResponse != null)
      {
        paramClientHttpResponse = FileCopyUtils.copyToByteArray(paramClientHttpResponse);
        return paramClientHttpResponse;
      }
    }
    catch (IOException paramClientHttpResponse) {}
    return new byte[0];
  }
  
  public void handleError(ClientHttpResponse paramClientHttpResponse)
    throws IOException
  {
    HttpStatus localHttpStatus = paramClientHttpResponse.getStatusCode();
    Object localObject = paramClientHttpResponse.getHeaders().getContentType();
    if (localObject != null) {}
    byte[] arrayOfByte;
    for (localObject = ((MediaType)localObject).getCharSet();; localObject = null)
    {
      arrayOfByte = getResponseBody(paramClientHttpResponse);
      switch (localHttpStatus.series())
      {
      default: 
        throw new RestClientException("Unknown status code [" + localHttpStatus + "]");
      }
    }
    throw new HttpClientErrorException(localHttpStatus, paramClientHttpResponse.getStatusText(), arrayOfByte, (Charset)localObject);
    throw new HttpServerErrorException(localHttpStatus, paramClientHttpResponse.getStatusText(), arrayOfByte, (Charset)localObject);
  }
  
  protected boolean hasError(HttpStatus paramHttpStatus)
  {
    return (paramHttpStatus.series() == HttpStatus.Series.CLIENT_ERROR) || (paramHttpStatus.series() == HttpStatus.Series.SERVER_ERROR);
  }
  
  public boolean hasError(ClientHttpResponse paramClientHttpResponse)
    throws IOException
  {
    return hasError(paramClientHttpResponse.getStatusCode());
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.client.DefaultResponseErrorHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */