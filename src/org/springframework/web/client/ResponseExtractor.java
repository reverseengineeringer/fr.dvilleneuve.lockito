package org.springframework.web.client;

import java.io.IOException;
import org.springframework.http.client.ClientHttpResponse;

public abstract interface ResponseExtractor<T>
{
  public abstract T extractData(ClientHttpResponse paramClientHttpResponse)
    throws IOException;
}

/* Location:
 * Qualified Name:     org.springframework.web.client.ResponseExtractor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */