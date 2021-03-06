package org.springframework.http.client;

import java.io.IOException;
import org.springframework.http.HttpRequest;

public abstract interface ClientHttpRequestInterceptor
{
  public abstract ClientHttpResponse intercept(HttpRequest paramHttpRequest, byte[] paramArrayOfByte, ClientHttpRequestExecution paramClientHttpRequestExecution)
    throws IOException;
}

/* Location:
 * Qualified Name:     org.springframework.http.client.ClientHttpRequestInterceptor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */