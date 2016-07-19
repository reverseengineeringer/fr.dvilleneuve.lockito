package org.springframework.http.client;

import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpMethod;

public abstract interface ClientHttpRequestFactory
{
  public abstract ClientHttpRequest createRequest(URI paramURI, HttpMethod paramHttpMethod)
    throws IOException;
}

/* Location:
 * Qualified Name:     org.springframework.http.client.ClientHttpRequestFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */