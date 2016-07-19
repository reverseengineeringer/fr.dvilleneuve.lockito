package org.springframework.http.client;

import java.io.IOException;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpRequest;

public abstract interface ClientHttpRequest
  extends HttpRequest, HttpOutputMessage
{
  public abstract ClientHttpResponse execute()
    throws IOException;
}

/* Location:
 * Qualified Name:     org.springframework.http.client.ClientHttpRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */