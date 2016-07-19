package org.springframework.http.client;

import java.io.IOException;
import org.springframework.http.HttpRequest;

public abstract interface ClientHttpRequestExecution
{
  public abstract ClientHttpResponse execute(HttpRequest paramHttpRequest, byte[] paramArrayOfByte)
    throws IOException;
}

/* Location:
 * Qualified Name:     org.springframework.http.client.ClientHttpRequestExecution
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */