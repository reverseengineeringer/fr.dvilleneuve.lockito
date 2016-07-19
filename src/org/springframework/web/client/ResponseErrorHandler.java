package org.springframework.web.client;

import java.io.IOException;
import org.springframework.http.client.ClientHttpResponse;

public abstract interface ResponseErrorHandler
{
  public abstract void handleError(ClientHttpResponse paramClientHttpResponse)
    throws IOException;
  
  public abstract boolean hasError(ClientHttpResponse paramClientHttpResponse)
    throws IOException;
}

/* Location:
 * Qualified Name:     org.springframework.web.client.ResponseErrorHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */